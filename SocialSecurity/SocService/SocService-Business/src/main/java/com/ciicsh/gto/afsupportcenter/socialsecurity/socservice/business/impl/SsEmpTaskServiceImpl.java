package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.TaskCommonUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsRequestDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.SSPItemDTO;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 本地社保的雇员任务单 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpTaskServiceImpl extends ServiceImpl<SsEmpTaskMapper, SsEmpTask> implements SsEmpTaskService {
    @Autowired
    SsEmpTaskPeriodService ssEmpTaskPeriodService;

    @Autowired
    SsEmpBasePeriodService ssEmpBasePeriodService;

    @Autowired
    SsEmpBaseDetailService ssEmpBaseDetailService;

    @Autowired
    SsEmpArchiveService ssEmpArchiveService;

    @Autowired
    SsEmpTaskFrontService ssEmpTaskFrontService;
    @Autowired
    SsEmpBaseAdjustService ssEmpBaseAdjustService;
    @Autowired
    SsEmpBaseAdjustDetailService ssEmpBaseAdjustDetailService;
    @Autowired
    SsEmpRefundService ssEmpRefundService;
    @Autowired
    CommonApiUtils commonApiUtils;
    @Autowired
    SsMonthChargeService ssMonthChargeService;
    @Autowired
    SsMonthChargeItemService ssMonthChargeItemService;
    @Autowired
    SsComAccountService ssComAccountService;

    //个人进位方式
    private final static String PERSONROUNDTYPE = "personRoundType";
    //公司进位方式
    private final static String COMPANYROUNDTYPE = "companyRoundType";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    @Override
    public PageRows<SsEmpTaskBO> employeeOperatorQuery(PageInfo pageInfo, String userId) {
        SsEmpTaskBO dto = pageInfo.toJavaObject(SsEmpTaskBO.class);
        dto.setUserId(userId);
        handleTaskCategory(dto);
        if (2 == dto.getOperatorType()) {
            return PageKit.doSelectPage(pageInfo, () -> baseMapper.employeeSpecialOperatorQuery(dto));
        } else {
            return PageKit.doSelectPage(pageInfo, () -> baseMapper.employeeDailyOperatorQuery(dto));
        }
    }

    /**
     * 雇员日常操作盘片导出
     */
    @Override
    public <T> PageRows<T> employeeDailyOperatorQueryForDisk(PageInfo pageInfo, String userId, boolean isRollIn) {
        SsEmpTaskBO ssEmpTaskBO = pageInfo.toJavaObject(SsEmpTaskBO.class);
        ssEmpTaskBO.setUserId(userId);
        return PageKit.doSelectPage(pageInfo, () -> {
            List<T> rollInBOs = null;
            List<SsEmpTaskBO> list = baseMapper.employeeDailyOperatorQuery(ssEmpTaskBO);
            if (list != null) {
                rollInBOs = new ArrayList<>();

                if (isRollIn) {
                    for (SsEmpTaskBO bo : list) {
                        SsEmpTaskRollInBO ssEmpTaskRollInBO = new SsEmpTaskRollInBO();
                        ssEmpTaskRollInBO.setSsSerial(bo.getEmpSsSerial());
                        ssEmpTaskRollInBO.setIdNum(bo.getIdNum());
                        ssEmpTaskRollInBO.setEmployeeName(bo.getEmployeeName());
                        ssEmpTaskRollInBO.setStartMonth(bo.getStartMonth());
                        ssEmpTaskRollInBO.setSalary(bo.getSalary());
                        rollInBOs.add((T) ssEmpTaskRollInBO);
                    }
                } else {
                    for (SsEmpTaskBO bo : list) {
                        SsEmpTaskRollOutBO ssEmpTaskRollOutBO = new SsEmpTaskRollOutBO();
                        ssEmpTaskRollOutBO.setIdNum(bo.getIdNum());
                        ssEmpTaskRollOutBO.setEmployeeName(bo.getEmployeeName());
                        ssEmpTaskRollOutBO.setSalary(bo.getSalary());
                        rollInBOs.add((T) ssEmpTaskRollOutBO);
                    }
                }
            }
            return rollInBOs;
        });
    }

    @Override
    public List<SsEmpTask> queryTaskByEmpArchiveId(String empArchiveId) {
        return baseMapper.queryTaskByEmpArchiveId(empArchiveId);
    }

    /**
     * 雇员日常操作
     *
     * @param bo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveHandleData(SsEmpTaskBO bo, boolean isBatch) {
        //办理类型1、未办 2 、已办  3 已做 4、批退 5、不需处理
        int taskStatus = bo.getTaskStatus();
        int taskCategory = bo.getTaskCategory();
        // 更新任务单费用段
        List<SsEmpTaskPeriod> periods = bo.getEmpTaskPeriods();
        if (periods != null) {
            //表示有时间段
            ssEmpTaskPeriodService.saveForEmpTaskId(periods, bo.getEmpTaskId());
            periods = ssEmpTaskPeriodService.queryByEmpTaskId(bo.getEmpTaskId());
            bo.setEmpTaskPeriods(periods);
        }
        SsEmpTask ssEmpTask = selectById(bo.getEmpTaskId());
        bo.setCompanyId(ssEmpTask.getCompanyId());
        bo.setEmployeeId(ssEmpTask.getEmployeeId());
        bo.setWelfareUnit(ssEmpTask.getWelfareUnit());
        bo.setServiceCenterId(ssEmpTask.getServiceCenterId());
        bo.setServiceCenter(ssEmpTask.getServiceCenter());
        bo.setIsChange(ssEmpTask.getIsChange());
        bo.setOldCityCode(ssEmpTask.getOldCityCode());
        bo.setNewCityCode(ssEmpTask.getNewCityCode());
        bo.setSocialStartAndStop(ssEmpTask.getSocialStartAndStop());

        if (bo.getEmpArchiveId() == null) {
            Wrapper<SsEmpArchive> wrapper = new EntityWrapper<>();
            wrapper.where("company_id={0} AND employee_id={1} AND archive_status<3 AND is_active=1", ssEmpTask.getCompanyId(), ssEmpTask.getEmployeeId());
            List<SsEmpArchive> ssEmpArchiveList = ssEmpArchiveService.selectList(wrapper);
            if (CollectionUtils.isNotEmpty(ssEmpArchiveList)) {
                if (ssEmpArchiveList.size() > 1) {
                    throw new BusinessException("该雇员存在多个未转出的雇员档案，数据不正确");
                }
                SsEmpArchive ssEmpArchive = ssEmpArchiveList.get(0);
                bo.setEmpArchiveId(ssEmpArchive.getEmpArchiveId());
            }
        }

        // 更新雇员任务信息
        // 备注时间
        LocalDate now = LocalDate.now();
        bo.setHandleRemarkDate(now);
        bo.setRejectionRemarkDate(now);
        bo.setModifiedTime(LocalDateTime.now());

        // 处理中，正式把数据写入到 ss_emp_base_period and ss_emp_base_detail(雇员社)
        if (TaskStatusConst.PROCESSING == taskStatus || TaskStatusConst.FINISH == taskStatus) {
            if (TaskTypeConst.NEW == taskCategory || TaskTypeConst.INTO == taskCategory || TaskTypeConst.FLOPNEW == taskCategory || TaskTypeConst.FLOPINTO == taskCategory) {
                //新进和转入 翻牌新进 翻牌转入
                newOrChangeInto(bo, isBatch);
                //任务单完成 回调
            } else if (TaskTypeConst.ADJUSTMENT == taskCategory) {
                //调整
                handleAdjustmentTask(bo, isBatch);
            } else if (TaskTypeConst.BACK == taskCategory) {
                //补缴
                handleBackTask(bo, isBatch);
            } else if (TaskTypeConst.TURNOUT == taskCategory || TaskTypeConst.FLOPTURNOUT == taskCategory) {
                //转出 翻牌转出
                handleTurnOutTask(bo, isBatch);
            } else if (TaskTypeConst.SEALED == taskCategory || TaskTypeConst.FLOPSEALED == taskCategory) {
                //封存 翻牌封存
                handleSealedTask(bo, isBatch);
            } else if (TaskTypeConst.REFUNDACCOUNT == taskCategory) {
                //退账
                handleRefundAccountTask(bo, isBatch);
            }
            //任务单完成 回调
            taskCompletCallBack(bo);
        } else {
            //更新雇员任务信息
            baseMapper.updateMyselfColumnById(bo);
            //批退回调
            if (TaskStatusConst.REJECTION == taskCategory) {
                //任务单 回调 数据
                bo.setCompanyConfirmAmount(new BigDecimal(0));
                bo.setPersonalConfirmAmount(new BigDecimal(0));
                //任务单完成 回调
                taskCompletCallBack(bo);
            }
        }
        return true;
    }

    @Override
    @Deprecated
    public String selectMaxSsSerialByTaskId(Long empTaskId) {
        return baseMapper.selectMaxSsSerialByTaskId(empTaskId);
    }

    @Override
    public Long getSerial(Long comAccountId) {
        Long ssSerial = ssComAccountService.getSerialByComAccountId(comAccountId);
        if (ssSerial != null && ssSerial == 9999999999L) {
            throw new BusinessException("当前账号下社保序列已经到达最大值，无法取值");
        }

        //社保序号增1
        ssComAccountService.addSerial(comAccountId);
        //取出序号
        ssSerial = ssComAccountService.getSerialByComAccountId(comAccountId);
        return ssSerial;
    }

    @Override
    public List<SsEmpTaskBO> queryBatchEmpArchiveByEmpTaskIds(SsEmpTaskBO ssEmpTaskBO) {
        return baseMapper.queryBatchEmpArchiveByEmpTaskIds(ssEmpTaskBO);
    }

    @Override
    public List<SsEmpTaskBO> queryBatchTaskByCondition(SsEmpTaskBO ssEmpTaskBO) {
        return baseMapper.queryBatchTaskByCondition(ssEmpTaskBO);
    }

    /**
     * 调整
     *
     * @param bo
     */
    private void handleAdjustmentTask(SsEmpTaskBO bo, boolean isBatch) {
//        if (isBatch) {
        //查询企业 是否开户
        queryCompanyIsOpenAccount(bo);
        //查询 雇员是否新进
        queryEmployeeIsnewOrChangeInto(bo);
//        }
        //修改任务单详细
        baseMapper.updateMyselfColumnById(bo);

        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setEmpArchiveId(bo.getEmpArchiveId());
        setEmpArchiveStatus(ssEmpArchive, bo.getTaskCategory());
        ssEmpArchive.setModifiedBy(UserContext.getUserId());
        ssEmpArchive.setModifiedTime(LocalDateTime.now());
        ssEmpArchiveService.updateById(ssEmpArchive);

        //获得进位方式
        getRoundType(bo.getPolicyDetailId(), bo.getWelfareUnit(), bo.getStartMonth(), bo);
        //获得前端输入的缴纳费用段
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null || taskPeriods.size() == 0) {
            SsEmpTaskPeriod ssEmpTaskPeriod = getSsEmpTaskObjWhenHasNot(bo);
            taskPeriods.add(ssEmpTaskPeriod);
        }

        //查询既存缴纳费用段
        List<SsEmpBasePeriod> ssEmpBasePeriodList = getPeriodsByEmployeeIdAndCompanyId(bo);

        adjustmentStartForTaskPeriods(taskPeriods, ssEmpBasePeriodList, bo);
    }

    /**
     * 任务单未完成 再次办理时 需要把之前SsEmpBasePeriod 表中任务单对应的时间段删除
     * 同时SsEmpBasePeriod 对应的detail 时间段也要删除
     *
     * @param bo
     */
    private void deleteForTask(SsEmpTaskBO bo, Integer type) {
        //查任务单对应的SsEmpBasePeriod时间段
        EntityWrapper<SsEmpBasePeriod> ew1 = new EntityWrapper();
        ew1.where("emp_task_id={0}", bo.getEmpTaskId());
        List<SsEmpBasePeriod> periods = getOldSsEmpBasePeriod(ew1);
        periods.forEach(p -> {
            SsEmpBaseDetail detail = new SsEmpBaseDetail();
            detail.setEmpBasePeriodId(p.getEmpBasePeriodId());
            //删除 改任务对应详细表
            ssEmpBaseDetailService.delete(new EntityWrapper(detail));
            //删除对应时间段表
            ssEmpBasePeriodService.deleteById(p.getEmpBasePeriodId());
        });
        //更新之前设置的
        //大于零表示有将原来的endMonth 设置时间
        //只有调整才有将endMonth截止
        if (periods.size() > 0 && type == TaskPeriodConst.ADJUSTMENTTYPE) {
            EntityWrapper<SsEmpBasePeriod> ew = new EntityWrapper();
            ew.where("emp_task_id!={0}", bo.getEmpTaskId()).and("emp_archive_id={0}", bo.getEmpArchiveId()).
                orderBy("start_month", false).and("is_active=1");
            List<SsEmpBasePeriod> oldPeriod = getOldSsEmpBasePeriod(ew);
            SsEmpBasePeriod ssEmpBasePeriod = oldPeriod.get(0);
            ssEmpBasePeriodService.updateEndMonthById(ssEmpBasePeriod);
        }
        //删除调整差异表
        EntityWrapper<SsEmpBaseAdjust> ew2 = new EntityWrapper();
        ew2.where("emp_task_id={0}", bo.getEmpTaskId());
        List<SsEmpBaseAdjust> ssEmpBaseAdjustList = ssEmpBaseAdjustService.selectList(ew2);
        ssEmpBaseAdjustList.forEach(p -> {
            //删除差异详细表
            SsEmpBaseAdjustDetail ssEmpBaseAdjustDetail = new SsEmpBaseAdjustDetail();
            ssEmpBaseAdjustDetail.setEmpBaseAdjustId(p.getEmpBaseAdjustId());
            ssEmpBaseAdjustDetailService.delete(new EntityWrapper(ssEmpBaseAdjustDetail));
            //删除差异主表
            ssEmpBaseAdjustService.deleteById(p.getEmpBaseAdjustId());
        });
    }

    List<SsEmpBasePeriod> getOldSsEmpBasePeriod(EntityWrapper<SsEmpBasePeriod> ew) {
        return ssEmpBasePeriodService.selectList(ew);
    }

    /**
     * 调整
     *
     * @param taskPeriods
     * @param ssEmpBasePeriodList
     * @param bo
     */
    private void adjustmentStartForTaskPeriods(List<SsEmpTaskPeriod> taskPeriods, List<SsEmpBasePeriod> ssEmpBasePeriodList, SsEmpTaskBO bo) {
        //获得组装map
        Map map = getSsEmpTaskPeriod(taskPeriods);
        //获得前端传递 startMonth 最小的那条数据
        SsEmpTaskPeriod ssEmpTaskPeriod = (SsEmpTaskPeriod) map.get(TaskPeriodConst.SSEMPTASKPERIOD);
        //前端传过来的最小startDate
        Integer minStartDateTask = Integer.valueOf(ssEmpTaskPeriod.getStartMonth());
        //原来数据库历史数据 最大的时间段
        SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(ssEmpBasePeriodList.size() - 1);
        //判断是否大于办理月
//        LocalDate nowDate = LocalDate.now();
//        StringBuffer nowDateStr = TaskCommonUtils.getMonthStr(nowDate);
//        Integer currentYearMonth = Integer.valueOf(nowDateStr.toString());
        Integer handleMonth = Integer.valueOf(bo.getHandleMonth());

        //通过各自的开始时间进行比较 判断是否有交叉
        if (minStartDateTask >= handleMonth) {
            // 顺调
            // 添加 新添加的时间段
            List<SsEmpBasePeriod> newEmpBasePeriodList = taskPeriodTranserEmpBase(taskPeriods, bo, TaskPeriodConst.ADJUSTMENTTYPE);
            //截上 之前的endMonth
            String endMonth = TaskCommonUtils.getLastMonth(minStartDateTask);
            ssEmpBasePeriod.setEndMonth(endMonth);
            ssEmpBasePeriod.setModifiedTime(bo.getModifiedTime());
            ssEmpBasePeriod.setModifiedBy(bo.getModifiedBy());

            if (YearMonth.parse(ssEmpBasePeriod.getStartMonth(), formatter).isAfter(YearMonth.parse(ssEmpBasePeriod.getEndMonth(), formatter))) {
                ssEmpBasePeriod.setActive(false);

                SsEmpBaseDetail ssEmpBaseDetail = new SsEmpBaseDetail();
                ssEmpBaseDetail.setActive(false);
                ssEmpBaseDetail.setModifiedTime(LocalDateTime.now());
                ssEmpBaseDetail.setModifiedBy(bo.getModifiedBy());
                Wrapper<SsEmpBaseDetail> wrapper = new EntityWrapper<>();
                wrapper.eq("emp_base_period_id", ssEmpBasePeriod.getEmpBasePeriodId());
                ssEmpBaseDetailService.saveForSsEmpBaseDetail(null, ssEmpBaseDetail, wrapper);
            }
            ssEmpBasePeriodService.saveAdjustmentPeriod(ssEmpBasePeriod, newEmpBasePeriodList);
            // 险种的数据段 （前道传递过来的）
            List<SsEmpTaskFront> empSocials = getEmpSocials(bo);
            if (empSocials.size() == 0) {
                throw new BusinessException("前道传递险种详细信息为空");
            }
            // 添加明细
            addEmpBaseDetail(newEmpBasePeriodList, empSocials, bo.getEmpArchiveId(), bo.getModifiedBy(), bo.getRoundTypeMap());
            bo.setListEmpBasePeriod(newEmpBasePeriodList);
            { //顺调的逻辑
                bo.setAdustType(1);
                SsEmpBasePeriod ssEmpBasePeriodAdd = newEmpBasePeriodList.get(0);
                createNonstandardData(bo, ssEmpBasePeriodAdd, null, null, null);
            }
        } else {
            // 含逆调
            handleAdjustment(ssEmpBasePeriodList, ssEmpTaskPeriod, bo, 1);
        }
    }

    /**
     * 含逆调调整处理
     *
     * @param ssEmpBasePeriodList
     * @param ssEmpTaskPeriod
     * @param type
     */
    private void handleAdjustment(List<SsEmpBasePeriod> ssEmpBasePeriodList, SsEmpTaskPeriod ssEmpTaskPeriod, SsEmpTaskBO bo, int type) {
        //添加调整交叉的时间段(差额补缴，即逆调)
        List<SsEmpBasePeriod> overlappingPeriodList = new ArrayList<>();
        //最后一段 当前月到永远
        List<SsEmpBasePeriod> addPeriodList = new ArrayList<>();
        //调整 走补缴(全额补缴)
        List<SsEmpBasePeriod> supplementPayList = new ArrayList<>();

        overlappingPeriodList.clear();
        addPeriodList.clear();
        supplementPayList.clear();
        //当前月时间
//        LocalDate now = LocalDate.now();
        String handleMonth = bo.getHandleMonth();
        YearMonth handleMonthDate = YearMonth.parse(handleMonth, formatter);

        //前月时间
        String lastMonth = handleMonthDate.minusMonths(1).format(formatter);

        //表示前端 输入的endMonth为空,即调整 输入月份到将来
        if (StringUtils.isBlank(ssEmpTaskPeriod.getEndMonth())) {
            //找到需要调整的时间段
            SsEmpBasePeriod basePeriod = Adapter.ssEmpBasePeriod(ssEmpTaskPeriod, bo);
            by(basePeriod);
            //默认当前月份在 最大startMonth 之后
            //将最大startMonth切成两段
            SsEmpBasePeriod addPeriodObj = TaskCommonUtils.cloneObjet(basePeriod, SsEmpBasePeriod.class);
            addPeriodObj = setValueForEmpBasePeriod(addPeriodObj, ssEmpTaskPeriod, handleMonth);
            addPeriodObj.setEndMonth(null);
            addPeriodObj.setStartMonth(handleMonth); // 顺调时切段，新费用段，应该是从当前月（办理月）至将来

            SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(ssEmpBasePeriodList.size() - 1);
            ssEmpBasePeriod.setEndMonth(lastMonth);
            SsEmpBasePeriod updatePeriod = new SsEmpBasePeriod();
            updatePeriod.setEmpBasePeriodId(ssEmpBasePeriod.getEmpBasePeriodId());
            updatePeriod.setModifiedTime(bo.getModifiedTime());
            updatePeriod.setModifiedBy(bo.getModifiedBy());
            updatePeriod.setEndMonth(lastMonth);
            if (YearMonth.parse(ssEmpBasePeriod.getStartMonth(), formatter).isAfter(YearMonth.parse(ssEmpBasePeriod.getEndMonth(), formatter))) {
                updatePeriod.setActive(false);
                ssEmpBasePeriodService.updateById(updatePeriod);

                SsEmpBaseDetail ssEmpBaseDetail = new SsEmpBaseDetail();
                ssEmpBaseDetail.setActive(false);
                ssEmpBaseDetail.setModifiedTime(LocalDateTime.now());
                ssEmpBaseDetail.setModifiedBy(bo.getModifiedBy());
                Wrapper<SsEmpBaseDetail> wrapper = new EntityWrapper<>();
                wrapper.eq("emp_base_period_id", ssEmpBasePeriod.getEmpBasePeriodId());
                ssEmpBaseDetailService.saveForSsEmpBaseDetail(null, ssEmpBaseDetail, wrapper);
            } else {
                ssEmpBasePeriodService.updateById(updatePeriod);
            }

            //当前月到永远的那段 （即如果调整是到永远的话，则从下月设置到永远）
            addPeriodList.add(addPeriodObj);
            SsEmpBasePeriod needAdjustObj = TaskCommonUtils.cloneObjet(basePeriod, SsEmpBasePeriod.class);
            needAdjustObj.setEndMonth(lastMonth);

            adjustOrSupplement(
                ssEmpTaskPeriod.getStartMonth(),
                lastMonth,
                handleMonth,
                ssEmpBasePeriodList,
                needAdjustObj,
                supplementPayList,
                overlappingPeriodList);
        }

        //将有交叉的调整段
        Map newData = new HashMap();
        newData.put(TaskPeriodConst.OVERLAPPING, overlappingPeriodList);
        //保存前端传过来的时间段 保存原始的起始 截止日期 调整基数
        newData.put(TaskPeriodConst.OLDBASE, ssEmpTaskPeriod);
        //用于添加，表示当前月到将来
        newData.put(TaskPeriodConst.ADJUSTADDLIST, addPeriodList);
        newData.put(TaskPeriodConst.SUPPLEMENTARYPAY, supplementPayList);

        //获得需要处理的集合 进行处理
        handleAdjustmentResult(newData, bo);
    }

    /**
     * 处理调整或补缴逻辑
     *
     * @param taskPeriodStartMonth  任务单起始年月
     * @param taskPeriodEndMonth    任务单截止年月
     * @param taskSsMonth           办理年月
     * @param ssEmpBasePeriodList   既存费用段列表
     * @param ssEmpBasePeriod       根据任务单信息转换后的费用段
     * @param supplementPayList     全额补缴费用段列表
     * @param overlappingPeriodList 差额补缴（逆调）费用段列表
     */
    private void adjustOrSupplement(String taskPeriodStartMonth,
                                    String taskPeriodEndMonth,
                                    String taskSsMonth,
                                    List<SsEmpBasePeriod> ssEmpBasePeriodList,
                                    SsEmpBasePeriod ssEmpBasePeriod,
                                    List<SsEmpBasePeriod> supplementPayList,
                                    List<SsEmpBasePeriod> overlappingPeriodList) {
        // 将雇员费用段连续的时间段进行组合（由于存在全额补缴，可能将原本不连续的时间段重新连接起来，但是费用段本身还是多条记录）
        List<ComposedEmpBasePeriodBO> composedEmpBasePeriodBOList = composeEmpBasePeriod(ssEmpBasePeriodList.stream().filter(
            e -> e.getActive()
        ).collect(Collectors.toList()));
        YearMonth repairStartMonth = YearMonth.parse(taskPeriodStartMonth, formatter);
        YearMonth repairEndMonth = YearMonth.parse(taskPeriodEndMonth, formatter);
        YearMonth ssMonth = YearMonth.parse(taskSsMonth, formatter);
        for (ComposedEmpBasePeriodBO composedEmpBasePeriodBO : composedEmpBasePeriodBOList) {
            YearMonth startMonth = composedEmpBasePeriodBO.getStartMonth();
            YearMonth endMonth = composedEmpBasePeriodBO.getEndMonth();
            if (endMonth == null) {
                endMonth = ssMonth;
            }
            List<SsEmpBasePeriod> containsSsEmpBasePeriodList;

            // 如果补缴起始年月小于费用段起始月
            if (repairStartMonth.isBefore(startMonth)) {
                // 补缴截止年月小于费用段起始月，说明整个补缴段都不存在，则全额补缴
                if (repairEndMonth.isBefore(startMonth)) {
                    SsEmpBasePeriod supplementPayObj = TaskCommonUtils.cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                    supplementPayObj.setStartMonth(repairStartMonth.format(formatter));
                    supplementPayObj.setEndMonth(repairEndMonth.format(formatter));
                    supplementPayObj.setRemitWay(2);
                    supplementPayList.add(supplementPayObj);
                    break;
                } else { // 补缴截止年月大于等于费用段起始月，说明补缴段部分在费用段中，部分全额补缴，部分差额补缴
                    // 此时肯定有一段全额补缴，一段差额补缴
                    SsEmpBasePeriod supplementPayObj = TaskCommonUtils.cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                    supplementPayObj.setStartMonth(repairStartMonth.format(formatter));
                    supplementPayObj.setEndMonth(startMonth.minusMonths(1).format(formatter));   // 全额补缴段：从补缴起始年月到费用段起始年月
                    supplementPayObj.setRemitWay(2);
                    supplementPayList.add(supplementPayObj);

                    SsEmpBasePeriod adjustPayObj = TaskCommonUtils.cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                    // 差额补缴段：从费用段起始年月到补缴截止年月
                    adjustPayObj.setStartMonth(startMonth.format(formatter));
                    // 补缴截止年月小于等于费用段截止月时，说明只有一段全额补缴，一段差额补缴
                    if (repairEndMonth.isBefore(endMonth) || repairEndMonth.equals(endMonth)) {
                        adjustPayObj.setEndMonth(repairEndMonth.format(formatter));
                    } else { // 补缴截止年月大于费用段截止年月时，说明需判断下一个连续费用段
                        // 补缴截止年月大于费用段截止年月时，后面从当前费用段截止年月次月开始判断
                        adjustPayObj.setEndMonth(endMonth.format(formatter));
                        repairStartMonth = endMonth.plusMonths(1);
                    }
                    containsSsEmpBasePeriodList = composedEmpBasePeriodBO.getContainSsEmpBasePeriods(); // 某连续费用段所包含的费用段记录

                    for (SsEmpBasePeriod subObj : containsSsEmpBasePeriodList) {
                        String basePeriodStartMonth = subObj.getStartMonth();
                        String basePeriodEndMonth = subObj.getEndMonth();

                        YearMonth startMonthDate = YearMonth.parse(adjustPayObj.getStartMonth(), formatter);
                        YearMonth endMonthDate = YearMonth.parse(adjustPayObj.getEndMonth(), formatter);
                        SsEmpBasePeriod subAdjustPayObj = TaskCommonUtils.cloneObjet(adjustPayObj, SsEmpBasePeriod.class);

                        if (StringUtils.isNotEmpty(basePeriodEndMonth)) {
                            YearMonth basePeriodEndMonthDate = YearMonth.parse(basePeriodEndMonth, formatter);

                            if (basePeriodEndMonthDate.isBefore(startMonthDate)) {
                                continue;
                            } else if (basePeriodEndMonthDate.isBefore(endMonthDate)) {
                                subAdjustPayObj.setEndMonth(basePeriodEndMonth);
                            }
                        }
                        YearMonth basePeriodStartMonthDate = YearMonth.parse(basePeriodStartMonth, formatter);

                        if (basePeriodStartMonthDate.isAfter(endMonthDate)) {
                            continue;
                        } else if (basePeriodStartMonthDate.isAfter(startMonthDate)) {
                            subAdjustPayObj.setStartMonth(basePeriodStartMonth);
                        }
                        subAdjustPayObj.setEmpBasePeriodId(subObj.getEmpBasePeriodId());
                        overlappingPeriodList.add(subAdjustPayObj);
                    }
                }
            } else { // 如果补缴起始年月大于等于费用段起始年月
                // 补缴起始年月小于等于费用段截止年月时
                if (repairStartMonth.isBefore(endMonth) || repairStartMonth.equals(endMonth)) {
                    // 此时肯定有一段差额补缴
                    SsEmpBasePeriod adjustPayObj = TaskCommonUtils.cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                    // 差额补缴段：从补缴起始年月到补缴截止年月
                    adjustPayObj.setStartMonth(repairStartMonth.format(formatter));
                    // 补缴截止年月小于等于费用段截止月时，说明只有一段全额补缴，一段差额补缴
                    if (repairEndMonth.isBefore(endMonth) || repairEndMonth.equals(endMonth)) {
                        adjustPayObj.setEndMonth(repairEndMonth.format(formatter));
                    } else { // 补缴截止年月大于费用段截止年月时，说明需判断下一个连续费用段
                        // 补缴截止年月大于费用段截止年月时，后面从当前费用段截止年月次月开始判断
                        adjustPayObj.setEndMonth(endMonth.format(formatter));
                        repairStartMonth = endMonth.plusMonths(1);
                    }
                    containsSsEmpBasePeriodList = composedEmpBasePeriodBO.getContainSsEmpBasePeriods(); // 某连续费用段所包含的费用段记录

                    for (SsEmpBasePeriod subObj : containsSsEmpBasePeriodList) {
                        String basePeriodStartMonth = subObj.getStartMonth();
                        String basePeriodEndMonth = subObj.getEndMonth();

                        YearMonth startMonthDate = YearMonth.parse(adjustPayObj.getStartMonth(), formatter);
                        YearMonth endMonthDate = YearMonth.parse(adjustPayObj.getEndMonth(), formatter);
                        SsEmpBasePeriod subAdjustPayObj = TaskCommonUtils.cloneObjet(adjustPayObj, SsEmpBasePeriod.class);

                        if (StringUtils.isNotEmpty(basePeriodEndMonth)) {
                            YearMonth basePeriodEndMonthDate = YearMonth.parse(basePeriodEndMonth, formatter);

                            if (basePeriodEndMonthDate.isBefore(startMonthDate)) {
                                continue;
                            } else if (basePeriodEndMonthDate.isBefore(endMonthDate)) {
                                subAdjustPayObj.setEndMonth(basePeriodEndMonth);
                            }
                        }
                        YearMonth basePeriodStartMonthDate = YearMonth.parse(basePeriodStartMonth, formatter);

                        if (basePeriodStartMonthDate.isAfter(endMonthDate)) {
                            continue;
                        } else if (basePeriodStartMonthDate.isAfter(startMonthDate)) {
                            subAdjustPayObj.setStartMonth(basePeriodStartMonth);
                        }
                        subAdjustPayObj.setEmpBasePeriodId(subObj.getEmpBasePeriodId());
                        overlappingPeriodList.add(subAdjustPayObj);
                    }
                }

                // 补缴起始年月大于费用段截止年月时，直接判断下一个连续费用段
            }
        }
    }

    private SsEmpBasePeriod setValueForEmpBasePeriod(SsEmpBasePeriod needAdjustObj, SsEmpTaskPeriod ssEmpTaskPeriod, String ssMonth) {
        needAdjustObj.setRemitWay(ssEmpTaskPeriod.getRemitWay());
        needAdjustObj.setEmpTaskId(ssEmpTaskPeriod.getEmpTaskId());
        needAdjustObj.setActive(true);
        needAdjustObj.setBaseAmount(ssEmpTaskPeriod.getBaseAmount());
        needAdjustObj.setSsMonthStop(null);
        LocalDateTime nowTime = LocalDateTime.now();
        needAdjustObj.setSsMonth(ssMonth);
        needAdjustObj.setCreatedTime(nowTime);
        needAdjustObj.setModifiedTime(nowTime);
        by(needAdjustObj);
        return needAdjustObj;
    }

    private void handleAdjustmentResult(Map newData, SsEmpTaskBO bo) {
        //获得需要调整的时间段 与之前有交叉的
        List<SsEmpBasePeriod> overlappingPeriodList = (List<SsEmpBasePeriod>) newData.get(TaskPeriodConst.OVERLAPPING);

        //原任务单
        SsEmpTaskPeriod ssEmpTaskPeriod = (SsEmpTaskPeriod) newData.get(TaskPeriodConst.OLDBASE);
        //需要添加的时间段 表示前端 调整无 endMonth
        List<SsEmpBasePeriod> addPeriodList = (List<SsEmpBasePeriod>) newData.get(TaskPeriodConst.ADJUSTADDLIST);
        //如果没有调整本月之后的 就没有数据 否则 进行添加修改
        if (CollectionUtils.isNotEmpty(addPeriodList)) {
            addPeriodAndUpdateEndMoth(addPeriodList);

            //通过时间段ID 查询详细表的信息 (前道传递)
            EntityWrapper ew = new EntityWrapper<SsEmpTaskFront>();
            ew.where("emp_task_id={0}", bo.getEmpTaskId()).and("is_active=1");
            List<SsEmpTaskFront> ssTaskEmpBaseDetailList = ssEmpTaskFrontService.selectList(ew);
            addEmpBaseDetail(addPeriodList, ssTaskEmpBaseDetailList, bo.getEmpArchiveId(), bo.getModifiedBy(), bo.getRoundTypeMap());

            bo.setAdustType(1);
            createNonstandardData(bo, addPeriodList.get(0), null, null, null);
        }
        // 逆调（差额补缴）费用段
        transferAndSave(overlappingPeriodList, ssEmpTaskPeriod, bo);

        // 全额补缴费用段
        List<SsEmpBasePeriod> supplementPayList = (List<SsEmpBasePeriod>) newData.get(TaskPeriodConst.SUPPLEMENTARYPAY);
        if (CollectionUtils.isNotEmpty(supplementPayList)) {
            addPeriodAndUpdateEndMoth(supplementPayList);

            //通过时间段ID 查询详细表的信息 (前道传递)
            EntityWrapper ew = new EntityWrapper<SsEmpTaskFront>();
            ew.where("emp_task_id={0}", bo.getEmpTaskId()).and("is_active=1");
            List<SsEmpTaskFront> ssTaskEmpBaseDetailList = ssEmpTaskFrontService.selectList(ew);
            addEmpBaseDetail(supplementPayList, ssTaskEmpBaseDetailList, bo.getEmpArchiveId(), bo.getModifiedBy(), bo.getRoundTypeMap());
            bo.setTaskCategory(Integer.valueOf(SocialSecurityConst.TASK_TYPE_4));
            bo.setAdustType(1);
            createNonstandardData(bo, supplementPayList.get(0), null, null, null);
        }

        bo.setListEmpBasePeriod(addPeriodList);
        if (CollectionUtils.isNotEmpty(overlappingPeriodList)) {
            if (bo.getListEmpBasePeriod() != null) {
                bo.getListEmpBasePeriod().addAll(overlappingPeriodList);
            } else {
                bo.setListEmpBasePeriod(overlappingPeriodList);
            }
        }

        if (CollectionUtils.isNotEmpty(supplementPayList)) {
            if (bo.getListEmpBasePeriod() != null) {
                bo.getListEmpBasePeriod().addAll(supplementPayList);
            } else {
                bo.setListEmpBasePeriod(supplementPayList);
            }
        }
        bo.setTaskCategory(Integer.valueOf(SocialSecurityConst.TASK_TYPE_3));
    }

    /**
     * 调整 添加新的时间段
     *
     * @param addPeriodList
     */
    private void addPeriodAndUpdateEndMoth(List<SsEmpBasePeriod> addPeriodList) {
        //添加只有一段
        SsEmpBasePeriod ssEmpBasePeriod = addPeriodList.get(0);
        ssEmpBasePeriod.setEmpBasePeriodId(null);
        ssEmpBasePeriodService.insert(ssEmpBasePeriod);
    }

    private void transferAndSave(List<SsEmpBasePeriod> overlappingPeriodList, SsEmpTaskPeriod ssEmpTaskPeriod, SsEmpTaskBO bo) {
        overlappingPeriodList.forEach(p -> {
            SsEmpBasePeriod ssEmpBasePeriod = new SsEmpBasePeriod();
            ssEmpBasePeriod.setEmpBasePeriodId(p.getEmpBasePeriodId());
            ssEmpBasePeriod.setModifiedTime(bo.getModifiedTime());
            ssEmpBasePeriod.setModifiedBy(bo.getModifiedBy());
            ssEmpBasePeriod.setEndMonth(p.getEndMonth());

            // 如果费用段起始年月大于截止年月，说明该费用段无效，逻辑删除，并删除其关联的费用段明细数据
            if (StringUtils.isNotEmpty(p.getStartMonth()) &&
                StringUtils.isNotEmpty(p.getEndMonth()) &&
                YearMonth.parse(p.getStartMonth(), formatter).isAfter(YearMonth.parse(p.getEndMonth(), formatter))) {
                ssEmpBasePeriod.setActive(false);
                ssEmpBasePeriodService.updateById(ssEmpBasePeriod);

                SsEmpBaseDetail ssEmpBaseDetail = new SsEmpBaseDetail();
                ssEmpBaseDetail.setActive(false);
                ssEmpBaseDetail.setModifiedTime(LocalDateTime.now());
                ssEmpBaseDetail.setModifiedBy(bo.getModifiedBy());
                Wrapper<SsEmpBaseDetail> wrapper = new EntityWrapper<>();
                wrapper.eq("emp_base_period_id", p.getEmpBasePeriodId());
                ssEmpBaseDetailService.saveForSsEmpBaseDetail(null, ssEmpBaseDetail, wrapper);
            } else {
                ssEmpBasePeriodService.updateById(ssEmpBasePeriod);

                //获得计算的差额
                Map<String, Object> resultMap = getSsEmpBaseAdjust(p, ssEmpTaskPeriod, bo);
                SsEmpBaseAdjust ssEmpBaseAdjust = (SsEmpBaseAdjust) resultMap.get(TaskPeriodConst.SSEMPBASEADJUST);
                List<SsEmpBaseAdjustDetail> ssEmpBaseAdjustDetailList = (List<SsEmpBaseAdjustDetail>) resultMap.get(TaskPeriodConst.SSEMPBASEADJUSTDETAILLIST);
                if (null == ssEmpBaseAdjust || null == ssEmpBaseAdjustDetailList || ssEmpBaseAdjustDetailList.size() == 0) {
                    throw new BusinessException("转换差异详情异常");
                }
                //保存
                saveAdjustAndDetail(ssEmpBaseAdjust, ssEmpBaseAdjustDetailList);
                //非标数据
                //逆调
                bo.setAdustType(0);
                createNonstandardData(bo, null, ssEmpBaseAdjust, ssEmpBaseAdjustDetailList, null);
            }
        });
    }

    public void saveAdjustAndDetail(SsEmpBaseAdjust ssEmpBaseAdjust, List<SsEmpBaseAdjustDetail> ssEmpBaseAdjustDetailList) {
        ssEmpBaseAdjustService.insert(ssEmpBaseAdjust);
        ssEmpBaseAdjustDetailList.forEach(p -> {
            p.setEmpBaseAdjustId(ssEmpBaseAdjust.getEmpBaseAdjustId());
        });
        ssEmpBaseAdjustDetailService.insertBatch(ssEmpBaseAdjustDetailList);
    }

    private Map<String, Object> getSsEmpBaseAdjust(SsEmpBasePeriod p, SsEmpTaskPeriod ssEmpTaskPeriod, SsEmpTaskBO bo) {
        Map<String, Map<String, Integer>> roundTypeMap = bo.getRoundTypeMap();
        Map<String, Object> map = new HashMap();
        SsEmpBaseAdjust ssEmpBaseAdjust = new SsEmpBaseAdjust();
        ssEmpBaseAdjust.setEmpTaskId(ssEmpTaskPeriod.getEmpTaskId());
        ssEmpBaseAdjust.setEmpArchiveId(p.getEmpArchiveId());
        ssEmpBaseAdjust.setProcessWay(bo.getHandleWay());
        ssEmpBaseAdjust.setNewBaseAmount(ssEmpTaskPeriod.getBaseAmount());
        ssEmpBaseAdjust.setSsMonth(bo.getHandleMonth());
        ssEmpBaseAdjust.setStartMonth(p.getStartMonth());
        ssEmpBaseAdjust.setEndMonth(p.getEndMonth());
        //通过时间段ID 查询详细表的信息 (原缴纳段的数据)
        EntityWrapper ew = new EntityWrapper<SsEmpBasePeriod>();
        ew.where("emp_base_period_id={0}", p.getEmpBasePeriodId()).and("is_active=1");
        List<SsEmpBaseDetail> ssEmpBaseDetailList = ssEmpBaseDetailService.selectList(ew);

        //通过时间段ID 查询详细表的信息 (前道传递)
        EntityWrapper ew1 = new EntityWrapper<SsEmpTaskFront>();
        ew1.where("emp_task_id={0}", bo.getEmpTaskId()).and("is_active=1");
        List<SsEmpTaskFront> ssTaskEmpBaseDetailList = ssEmpTaskFrontService.selectList(ew1);

        List<SsEmpBaseAdjustDetail> ssEmpBaseAdjustDetailList = new ArrayList();
        LocalDateTime now = LocalDateTime.now();
        //企业差额合计
        BigDecimal comDiffSumAmount = new BigDecimal(0);
        //雇员差额合计
        BigDecimal empDiffSumAmount = new BigDecimal(0);
        //总差额合计
        BigDecimal comempDiffSumAmount = new BigDecimal(0);
        for (int j = 0; j < ssTaskEmpBaseDetailList.size(); j++) {
            SsEmpTaskFront ssEmpTaskFront = ssTaskEmpBaseDetailList.get(j);
            for (int i = 0; i < ssEmpBaseDetailList.size(); i++) {
                SsEmpBaseDetail ssEmpBaseDetail = ssEmpBaseDetailList.get(i);
                if (ssEmpTaskFront.getItemDicId().equals(ssEmpBaseDetail.getSsType())) {
                    SsEmpBaseAdjustDetail ssEmpBaseAdjustDetail = new SsEmpBaseAdjustDetail();
                    ssEmpBaseAdjustDetail.setEmpArchiveId(p.getEmpArchiveId());
                    ssEmpBaseAdjustDetail.setSsType(ssEmpBaseDetail.getSsType());
                    ssEmpBaseAdjustDetail.setSsTypeName(ssEmpBaseDetail.getSsTypeName());
                    ssEmpBaseAdjustDetail.setComPolicyItemId(ssEmpBaseDetail.getComPolicyItemId());
                    ssEmpBaseAdjustDetail.setEmpCssPolicyItemId(ssEmpBaseDetail.getEmpPolicyItemId());
                    ssEmpBaseAdjustDetail.setComBase(ssEmpTaskFront.getCompanyBase());
                    ssEmpBaseAdjustDetail.setEmpBase(ssEmpTaskFront.getPersonalBase());
                    ssEmpBaseAdjustDetail.setComRatio(ssEmpTaskFront.getCompanyRatio());
                    ssEmpBaseAdjustDetail.setEmpRatio(ssEmpTaskFront.getPersonalRatio());
                    //企业部分总额
                    //BigDecimal base, BigDecimal ratio, BigDecimal fixedAmount, Integer calculateMethod, String roundType
                    //通过进位方式进行 计算(原数据)
                    //如果调用为空 则 默认为 见分进角
                    BigDecimal comAmount = CalculateSocialUtils.calculateAmount(ssEmpBaseDetail.getComBase(), ssEmpBaseDetail.getComRatio(), null, 2, null == roundTypeMap ? 1 : roundTypeMap.get(ssEmpBaseDetail.getSsType()).get(COMPANYROUNDTYPE));
//                    System.out.println(ssEmpBaseDetail.getSsType()+"企业部分原数据额"+comAmount);
                    //企业部分金额
                    //通过进位方式进行 计算(前道传递)
                    BigDecimal frontComAmount = CalculateSocialUtils.calculateAmount(ssEmpTaskFront.getCompanyBase(), ssEmpTaskFront.getCompanyRatio(), null, 2, null == roundTypeMap ? 1 : roundTypeMap.get(ssEmpBaseDetail.getSsType()).get(COMPANYROUNDTYPE));
//                    System.out.println(ssEmpBaseDetail.getSsType()+"企业部分前道数据额"+frontComAmount);
                    ssEmpBaseAdjustDetail.setComAmount(frontComAmount);
                    //雇员总额(原数据)
                    BigDecimal empAmount = CalculateSocialUtils.calculateAmount(ssEmpBaseDetail.getEmpBase(), ssEmpBaseDetail.getEmpRatio(), null, 2, null == roundTypeMap ? 1 : roundTypeMap.get(ssEmpBaseDetail.getSsType()).get(PERSONROUNDTYPE));
//                    System.out.println(ssEmpBaseDetail.getSsType()+"雇员部分原数据额"+empAmount);
                    //雇员总额(前道传递)
                    BigDecimal frontEmpAmount = CalculateSocialUtils.calculateAmount(ssEmpBaseAdjustDetail.getEmpBase(), ssEmpBaseAdjustDetail.getEmpRatio(), null, 2, null == roundTypeMap ? 1 : roundTypeMap.get(ssEmpBaseDetail.getSsType()).get(PERSONROUNDTYPE));
//                    System.out.println(ssEmpBaseDetail.getSsType()+"雇员部分前道数据额"+frontEmpAmount);
                    ssEmpBaseAdjustDetail.setEmpAmount(frontEmpAmount);
                    //企业+雇员
                    ssEmpBaseAdjustDetail.setComempAmount(frontComAmount.add(frontEmpAmount));
//                    System.out.println(ssEmpBaseDetail.getSsType()+"q前道总额"+ssEmpBaseAdjustDetail.getComempAmount());
                    //调整后减去原来 企业部分差额
                    ssEmpBaseAdjustDetail.setComDiffAmount(frontComAmount.subtract(comAmount));
//                    System.out.println("企业差额="+ssEmpBaseAdjustDetail.getComDiffAmount());
                    //调整减原来    雇员部分差额
                    ssEmpBaseAdjustDetail.setEmpDiffAmount(frontEmpAmount.subtract(empAmount));
//                    System.out.println("雇员差额="+ssEmpBaseAdjustDetail.getEmpDiffAmount());
                    //总差额
                    ssEmpBaseAdjustDetail.setComempDiffAmount(ssEmpBaseAdjustDetail.getComDiffAmount().add(ssEmpBaseAdjustDetail.getEmpDiffAmount()));
//                    System.out.println("总差额="+ssEmpBaseAdjustDetail.getComempDiffAmount());
                    ssEmpBaseAdjustDetail.setCreatedTime(now);
                    ssEmpBaseAdjustDetail.setModifiedTime(now);
                    by(ssEmpBaseAdjustDetail);
                    comDiffSumAmount = comDiffSumAmount.add(ssEmpBaseAdjustDetail.getComDiffAmount());
//                    System.out.println(ssEmpBaseDetail.getSsType()+":----comDiff["+ssEmpBaseAdjustDetail.getComDiffAmount()+"]");
                    empDiffSumAmount = empDiffSumAmount.add(ssEmpBaseAdjustDetail.getEmpDiffAmount());
//                    System.out.println(ssEmpBaseDetail.getSsType()+":----empDiff["+ssEmpBaseAdjustDetail.getEmpDiffAmount()+"]");
                    comempDiffSumAmount = comempDiffSumAmount.add(ssEmpBaseAdjustDetail.getComempDiffAmount());
//                    System.out.println(ssEmpBaseDetail.getSsType()+":----totalDiff["+ssEmpBaseAdjustDetail.getComempDiffAmount()+"]");
                    ssEmpBaseAdjustDetailList.add(ssEmpBaseAdjustDetail);
                    break;
                }
            }

        }

        ssEmpBaseAdjust.setComDiffSumAmount(comDiffSumAmount);
        ssEmpBaseAdjust.setEmpDiffSumAmount(empDiffSumAmount);
        ssEmpBaseAdjust.setComempDiffAmount(comempDiffSumAmount);

        by(ssEmpBaseAdjust);
        //用map来存储 一个表示主表信息  另一个代表从表信息
        map.put(TaskPeriodConst.SSEMPBASEADJUST, ssEmpBaseAdjust);
        map.put(TaskPeriodConst.SSEMPBASEADJUSTDETAILLIST, ssEmpBaseAdjustDetailList);

        return map;
    }

    /**
     * 补缴
     *
     * @param taskPeriods
     * @param ssEmpBasePeriodList
     * @param bo
     */
    private void backStartForTaskPeriods(List<SsEmpTaskPeriod> taskPeriods, List<SsEmpBasePeriod> ssEmpBasePeriodList, SsEmpTaskBO bo) {
        //判断前端传递 时间段是一条还是多条 多条是降序还是升序
        Map map = getSsEmpTaskPeriod(taskPeriods);
        SsEmpTaskPeriod ssEmpTaskPeriod = (SsEmpTaskPeriod) map.get(TaskPeriodConst.SSEMPTASKPERIOD);
        if (StringUtils.isBlank(ssEmpTaskPeriod.getEndMonth())) {
            throw new BusinessException("截止时间不能为空");
        }
        //当前月 (判断补缴只能补半年之内的)

//        LocalDate now = LocalDate.now();
        String handleMonth = bo.getHandleMonth();
        int endMonth = Integer.parseInt(ssEmpTaskPeriod.getEndMonth());
        if (Integer.parseInt(handleMonth) < endMonth) {
            throw new BusinessException("补缴的时间段的截止月份需在当前月之前。");
        }

        List<SsEmpBasePeriod> newEmpBasePeriodList = taskPeriodTranserEmpBase(taskPeriods, bo, TaskPeriodConst.SUPPLEMENTARYPAYTYPE);
        SsEmpBasePeriod ssEmpBasePeriod = newEmpBasePeriodList.get(0);
        //添加调整交叉的时间段(差额补缴，即逆调)
        List<SsEmpBasePeriod> overlappingPeriodList = new ArrayList<>();
        //调整 走补缴(全额补缴)
        List<SsEmpBasePeriod> supplementPayList = new ArrayList<>();

        adjustOrSupplement(
            ssEmpTaskPeriod.getStartMonth(),
            ssEmpTaskPeriod.getEndMonth(),
            handleMonth,
            ssEmpBasePeriodList,
            ssEmpBasePeriod,
            supplementPayList,
            overlappingPeriodList
        );

        // 差额补缴费用段
        if (CollectionUtils.isNotEmpty(overlappingPeriodList)) {
            transferAndSave(overlappingPeriodList, ssEmpTaskPeriod, bo);
        }

        // 全额补缴费用段
        if (CollectionUtils.isNotEmpty(supplementPayList)) {
            addPeriodAndUpdateEndMoth(supplementPayList);

            //通过时间段ID 查询详细表的信息 (前道传递)
            EntityWrapper ew = new EntityWrapper<SsEmpTaskFront>();
            ew.where("emp_task_id={0}", bo.getEmpTaskId()).and("is_active=1");
            List<SsEmpTaskFront> ssTaskEmpBaseDetailList = ssEmpTaskFrontService.selectList(ew);
            addEmpBaseDetail(supplementPayList, ssTaskEmpBaseDetailList, bo.getEmpArchiveId(), bo.getModifiedBy(), bo.getRoundTypeMap());
            bo.setAdustType(1);
            createNonstandardData(bo, supplementPayList.get(0), null, null, null);
        }

        if (CollectionUtils.isNotEmpty(overlappingPeriodList)) {
            if (bo.getListEmpBasePeriod() != null) {
                bo.getListEmpBasePeriod().addAll(overlappingPeriodList);
            } else {
                bo.setListEmpBasePeriod(overlappingPeriodList);
            }
        }

        if (CollectionUtils.isNotEmpty(supplementPayList)) {
            if (bo.getListEmpBasePeriod() != null) {
                bo.getListEmpBasePeriod().addAll(supplementPayList);
            } else {
                bo.setListEmpBasePeriod(supplementPayList);
            }
        }
    }

    /**
     * 表示之前步骤都符合补缴条件
     * 正式做补缴逻辑
     *
     * @param taskPeriods 任务单时间段
     * @param bo          task bo对象
     */
    private void supplementaryPayment(List<SsEmpTaskPeriod> taskPeriods, SsEmpTaskBO bo) {
        List<SsEmpBasePeriod> newEmpBasePeriodList = taskPeriodTranserEmpBase(taskPeriods, bo, TaskPeriodConst.SUPPLEMENTARYPAYTYPE);
        ssEmpBasePeriodService.saveBackPeriod(newEmpBasePeriodList);
        // 险种
        // 更新雇员社保汇缴基数明细
        // 险种的数据段 （前道传递过来的）
        List<SsEmpTaskFront> empSocials = getEmpSocials(bo);
        if (empSocials.size() == 0) {
            throw new BusinessException("前道传递险种详细信息为空");
        }
        //添加明细 （养 医 失 工 生育 险种）
        addEmpBaseDetail(newEmpBasePeriodList, empSocials, bo.getEmpArchiveId(), bo.getModifiedBy(), bo.getRoundTypeMap());

        //创建非标 数据
        SsEmpBasePeriod ssEmpBasePeriod = newEmpBasePeriodList.get(0);
        createNonstandardData(bo, ssEmpBasePeriod, null, null, null);

        if (bo.getListEmpBasePeriod() != null) {
            bo.getListEmpBasePeriod().addAll(newEmpBasePeriodList);
        } else {
            bo.setListEmpBasePeriod(newEmpBasePeriodList);
        }
    }

    /**
     * 前端任务时间段转enmBasePeriods
     *
     * @param taskPeriods
     * @param
     */
    private List<SsEmpBasePeriod> taskPeriodTranserEmpBase(List<SsEmpTaskPeriod> taskPeriods, SsEmpTaskBO bo, int type) {
        List<SsEmpBasePeriod> newEmpBasePeriodList = new ArrayList();
        //如果为降序则最大下标为最小startDate
        //int size = "DESC".equals(sort) ? taskPeriods.size() - 1 : 0;
        for (int i = 0; i < taskPeriods.size(); i++) {
            SsEmpTaskPeriod ssEmpTaskPeriod = taskPeriods.get(i);
            if (type == TaskPeriodConst.ADJUSTMENTTYPE) {
                ssEmpTaskPeriod.setRemitWay(3);
            } else if (type == TaskPeriodConst.SUPPLEMENTARYPAYTYPE) {
                ssEmpTaskPeriod.setRemitWay(2);
            } else {
                ssEmpTaskPeriod.setRemitWay(1);
            }
            //将前端emptask 转empBase
            SsEmpBasePeriod basePeriod = Adapter.ssEmpBasePeriod(ssEmpTaskPeriod, bo);
            by(basePeriod);
            //用于更新原来时间段和添加新时间段
            newEmpBasePeriodList.add(basePeriod);
        }
        return newEmpBasePeriodList;
    }

    /**
     * 获得前端时间段的信息
     *
     * @param taskPeriods
     * @return
     */
    private Map getSsEmpTaskPeriod(List<SsEmpTaskPeriod> taskPeriods) {
        SsEmpTaskPeriod ssEmpTaskPeriod;
        //List 是否只有一个size
        boolean sizeIsOne = false;
        String SORT = "";
        if (1 == taskPeriods.size()) {
            ssEmpTaskPeriod = taskPeriods.get(0);
            sizeIsOne = true;
        } else {
            //认为前端传递时间段 已经是排序状态
            SsEmpTaskPeriod maxObject = taskPeriods.get(taskPeriods.size() - 1);
            Integer maxObjStartDate = Integer.valueOf(maxObject.getStartMonth());

            SsEmpTaskPeriod minObject = taskPeriods.get(0);
            Integer minObjStartDate = Integer.valueOf(minObject.getStartMonth());
            //前端穿过来的时间段升序还是降序
            if (maxObjStartDate < minObjStartDate) {
                SORT = "DESC";
                ssEmpTaskPeriod = maxObject;
            } else {
                SORT = "ASC";
                ssEmpTaskPeriod = minObject;
            }
        }
        HashMap map = new HashMap();
        //前端是否是一条数据
        map.put(TaskPeriodConst.SIZEISONE, sizeIsOne);
        map.put(TaskPeriodConst.SSEMPTASKPERIOD, ssEmpTaskPeriod);
        //升序还是降序
        map.put(TaskPeriodConst.SORT, SORT);
        return map;
    }

    /**
     * 补缴
     *
     * @param bo
     */
    private void handleBackTask(SsEmpTaskBO bo, boolean isBatch) {
//        if (isBatch) {
        //查询企业 是否开户
        queryCompanyIsOpenAccount(bo);
        //查询 雇员是否新进
        queryEmployeeIsnewOrChangeInto(bo);
//        }
        //修改任务单详细
        baseMapper.updateMyselfColumnById(bo);

        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setEmpArchiveId(bo.getEmpArchiveId());
        setEmpArchiveStatus(ssEmpArchive, bo.getTaskCategory());
        ssEmpArchive.setModifiedBy(UserContext.getUserId());
        ssEmpArchive.setModifiedTime(LocalDateTime.now());
        ssEmpArchiveService.updateById(ssEmpArchive);

        //获得进位方式
        getRoundType(bo.getPolicyDetailId(), bo.getWelfareUnit(), bo.getStartMonth(), bo);
        //获得前端输入的补缴费用段
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null || taskPeriods.size() == 0) {
            SsEmpTaskPeriod ssEmpTaskPeriod = getSsEmpTaskObjWhenHasNot(bo);
            taskPeriods.add(ssEmpTaskPeriod);
        }

        //查询既存缴纳费用段
        List<SsEmpBasePeriod> ssEmpBasePeriodList = getPeriodsByEmployeeIdAndCompanyId(bo);

        //补缴的前提条件
        backStartForTaskPeriods(taskPeriods, ssEmpBasePeriodList, bo);
    }

    /**
     * 转出
     *
     * @param bo
     */
    private void handleTurnOutTask(SsEmpTaskBO bo, boolean isBatch) {
//        if (isBatch) {
        //查询企业 是否开户
        queryCompanyIsOpenAccount(bo);
        //查询 雇员是否新进
        queryEmployeeIsnewOrChangeInto(bo);

        checkEndMonth(bo);
//        }
        //更新雇员任务信息
        baseMapper.updateMyselfColumnById(bo);

        //修改档案表的离职时间和缴纳截止时间
        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setEmpArchiveId(bo.getEmpArchiveId());
        setEmpArchiveStatus(ssEmpArchive, bo.getTaskCategory());
        ssEmpArchive.setEndMonth(bo.getEndMonth());
        ssEmpArchive.setOutDate(bo.getOutDate());
        ssEmpArchive.setModifiedBy(UserContext.getUserId());
        ssEmpArchive.setModifiedTime(LocalDateTime.now());
        ssEmpArchiveService.updateById(ssEmpArchive);

        List<SsEmpBasePeriod> ssEmpBasePeriodList = getNormalPeriod(bo);
        if (ssEmpBasePeriodList.size() > 0) {
            //有可能是再次办理 先将endMonth 和 ss_month_stop
            SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(0);
//            //还原之前修改 （再次办理的时候 ss_month_stop end_month 还原到为修改的状态）
//            Integer result = ssEmpBasePeriodService.updateReductionById(ssEmpBasePeriod);
//            if (result == 0) throw new BusinessException("数据库修改不成功.");
            ssEmpBasePeriod.setSsMonthStop(bo.getHandleMonth());
            ssEmpBasePeriod.setEndMonth(bo.getEndMonth());
            if (YearMonth.parse(ssEmpBasePeriod.getStartMonth(), formatter)
                .isAfter(YearMonth.parse(ssEmpBasePeriod.getEndMonth(), formatter))) {
                ssEmpBasePeriod.setActive(false);
            }
            ssEmpBasePeriod.setModifiedBy(UserContext.getUserId());
            ssEmpBasePeriod.setModifiedTime(LocalDateTime.now());
            //修改 没有截止时间时间段的截止时间和停缴月份
            ssEmpBasePeriodService.updateEndMonAndHandleMon(ssEmpBasePeriod);

            //创建非标数据
            createNonstandardData(bo, ssEmpBasePeriod, null, null, null);
            Long empBasePeriodId = ssEmpBasePeriod.getEmpBasePeriodId();
            //通过empBasePeriodId 获得明细
            Wrapper<SsEmpBaseDetail> ew = new EntityWrapper<>();
            ew.where("emp_base_period_id={0}", empBasePeriodId).and("is_active=1");
            List<SsEmpBaseDetail> ssEmpBaseDetailList = ssEmpBaseDetailService.selectList(ew);
            if (ssEmpBaseDetailList.size() == 0) {
                throw new BusinessException("费用段明细数据不正确");
            }
            ssEmpBasePeriod.setListEmpBaseDetail(ssEmpBaseDetailList);
            bo.setListEmpBasePeriod(ssEmpBasePeriodList);
        } else {
            throw new BusinessException("数据库没有缴纳时间段");
        }
    }

    /**
     * 封存
     *
     * @param bo
     */
    private void handleSealedTask(SsEmpTaskBO bo, boolean isBatch) {
        //封存也是转出
        handleTurnOutTask(bo, isBatch);
    }

    /**
     * 退账
     *
     * @param bo
     */
    private void handleRefundAccountTask(SsEmpTaskBO bo, boolean isBatch) {
//        if (isBatch) {
        //查询企业 是否开户
        queryCompanyIsOpenAccount(bo);
        //查询 雇员是否新进
        queryEmployeeIsnewOrChangeInto(bo);
//        }
        //更新雇员任务信息
        baseMapper.updateMyselfColumnById(bo);

        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setEmpArchiveId(bo.getEmpArchiveId());
        setEmpArchiveStatus(ssEmpArchive, bo.getTaskCategory());
        ssEmpArchive.setModifiedBy(UserContext.getUserId());
        ssEmpArchive.setModifiedTime(LocalDateTime.now());
        ssEmpArchiveService.updateById(ssEmpArchive);

        //删除(有可能是再次办理)
        EntityWrapper ew = new EntityWrapper();
        ew.where("emp_task_id={0}", bo.getEmpTaskId());
        ssEmpRefundService.delete(ew);
        SsEmpRefund ssEmpRefund = provideSsEmpRefund(bo);
        LocalDateTime now = LocalDateTime.now();
        by(ssEmpRefund);
        ssEmpRefund.setCreatedTime(now);
        ssEmpRefund.setModifiedTime(now);
        ssEmpRefundService.insert(ssEmpRefund);
        //退账生成非标数据
        createNonstandardData(bo, null, null, null, ssEmpRefund);
    }

    /**
     * 返回退账对象
     *
     * @param bo
     */
    private SsEmpRefund provideSsEmpRefund(SsEmpTaskBO bo) {
        SsEmpRefund ssEmpRefund = new SsEmpRefund();
        ssEmpRefund.setAmount(bo.getRefundAmount());
        ssEmpRefund.setStartMonth(bo.getStartMonth());
        ssEmpRefund.setEndMonth(bo.getEndMonth());
        ssEmpRefund.setEmpTaskId(bo.getEmpTaskId());
        ssEmpRefund.setEmpArchiveId(bo.getEmpArchiveId());
        ssEmpRefund.setProcessWay(bo.getHandleWay());
        ssEmpRefund.setSsMonth(bo.getHandleMonth());
        return ssEmpRefund;
    }

    /**
     * 处理雇员任务单数据
     *
     * @param dto
     */
    private void handleTaskCategory(SsEmpTaskBO dto) {
        // 操作类型，用于区分操作。1 日常操作、2 特殊操作， 默认日常操作
        Integer operatorType = Optional.ofNullable(dto.getOperatorType()).orElse(1);
        dto.setOperatorType(operatorType);
        // 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账
        {
            // 任务处理类型
            Integer taskCategory = dto.getTaskCategory();
            Integer[] taskCategories;
            if (taskCategory == null) {
                switch (operatorType) {
                    // 日常操作
                    case 1:
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15};
                        break;
                    // 特殊操作
                    case 2:
                        taskCategories = null;//现在特殊任务只有状态为9的 后面sql已经写死为9
                        break;
                    default:// 日常操作
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15};
                }
            } else {
                taskCategories = new Integer[]{taskCategory};
            }
            dto.setTaskCategories(taskCategories);
            //bo.setTaskCategory(null);
        }
    }

    /**
     * 雇员任务单新进或者转入办理
     *
     * @param bo
     */
    private void newOrChangeInto(SsEmpTaskBO bo, boolean isBatch) {
        //如果是批量办理 则查看当前 企业是否开户
//        if (isBatch) {
        queryCompanyIsOpenAccount(bo);
        if (bo.getIsChange() == 0) {
            //查询雇员 是否已经新进了
            queryEmployeeIsnewOrChangeInto(bo);
        }
        // 起缴月份必须小于或者等于办理月份
        checkStartMonth(bo);
//        }

        // 如果新开（转入，含翻牌）时的更正，需先撤销之前办理的任务单
        if (bo.getIsChange() == 1) {
            Integer[] inArray = new Integer[]{TaskTypeConst.NEW, TaskTypeConst.INTO};
            Integer[] flopInArray = new Integer[]{TaskTypeConst.FLOPNEW, TaskTypeConst.FLOPINTO};

//            Map<String, Object> condition = new HashMap<>();
            Wrapper<SsEmpTask> wrapper = new EntityWrapper<>();
            wrapper.eq("company_id", bo.getCompanyId());
            wrapper.eq("employee_id", bo.getEmployeeId());
            wrapper.eq("emp_archive_id", bo.getEmpArchiveId());
            if (ArrayUtils.contains(inArray, bo.getTaskCategory())) {
                wrapper.in("task_category", inArray);
            } else if (ArrayUtils.contains(flopInArray, bo.getTaskCategory())) {
                wrapper.in("task_category", flopInArray);
            }
//            wrapper.eq("is_change", 0);
            wrapper.in("task_status", new Integer[]{TaskStatusConst.PROCESSING, TaskStatusConst.FINISH});
            wrapper.eq("is_active", 1);
            List<SsEmpTask> ssEmpTaskList = this.selectList(wrapper);

            if (CollectionUtils.isNotEmpty(ssEmpTaskList)) {
                if (ssEmpTaskList.size() > 1) {
                    throw new BusinessException("相同雇员的雇员新增任务单已办理多次，数据不正确");
                }

                SsEmpTask oldSsEmpTask = ssEmpTaskList.get(0);
                if (oldSsEmpTask.getTaskStatus() == TaskStatusConst.FINISH) {
                    throw new BusinessException("该雇员的雇员新增任务单已完成，不能更正");
                }

                // 撤销报表及其明细数据
                inactiveMonthChargeData(oldSsEmpTask.getEmpTaskId(), bo.getModifiedBy());
                // 撤销差额补缴（逆调）费用段数据及其明细数据
                inactiveBaseAdjustData(oldSsEmpTask.getEmpTaskId(), bo.getModifiedBy());
                // 撤销雇员费用段数据及其明细数据
                inactiveBasePeriodData(oldSsEmpTask.getEmpTaskId(), bo.getModifiedBy());
                // 撤销雇员档案数据
                inactiveEmpArchive(oldSsEmpTask.getCompanyId(), oldSsEmpTask.getEmployeeId(), bo.getEmpArchiveId(), bo.getModifiedBy());

                bo.setOldSsEmpTask(oldSsEmpTask);
            }

            queryEmployeeIsnewOrChangeInto(bo);
        }

        //检查社保序号是否有重复
        //根据企业社保账户Id 和 employeeId 判断重复
        boolean serialCheckFlag = ssEmpArchiveService.checkSerial(bo.getComAccountId(), bo.getEmployeeId(), bo.getEmpSsSerial());
        if (!serialCheckFlag) {
            throw new BusinessException("社保序号已存在");
        }
        {//首先添加社保档案表数据
            SsEmpArchive ssEmpArchive = getArchive(bo);
            ssEmpArchiveService.insert(ssEmpArchive);
            bo.setEmpArchiveId(ssEmpArchive.getEmpArchiveId());
        }
        //获得插入的 档案ID
        Long empArchiveId = bo.getEmpArchiveId();
        //更新任务单
        baseMapper.updateMyselfColumnById(bo);

        //获得进位方式
        int unit = Optional.ofNullable(bo.getWelfareUnit()).orElse(1);
        getRoundType(bo.getPolicyDetailId(), unit, bo.getStartMonth(), bo);

        //获得前端输入的缴纳费用段
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null || taskPeriods.size() == 0) {
            SsEmpTaskPeriod ssEmpTaskPeriod = getSsEmpTaskObjWhenHasNot(bo);
            taskPeriods.add(ssEmpTaskPeriod);
        }
        if (taskPeriods.size() > 1) {
            throw new BusinessException("暂不支持多段");
        }
        //任务单Id
        Long empTaskId = bo.getEmpTaskId();

        //缴纳费用段 startMonth 等于当前月
        List<SsEmpTaskPeriod> baseTaskPeriods = new ArrayList<>();

        //缴纳费用段 在当前月之前 （例如 在3月份 报 1月份入职  那么 1-2月的时间段在这里）
        List<SsEmpTaskPeriod> backPeriods = new ArrayList<>();
        // 删除 old 费用段和明细
        ssEmpBasePeriodService.deleteByEmpTaskId(empTaskId);
        // 更新任务单费用段
        String handleMonth = bo.getHandleMonth();

        //获取缴纳费用段
        SsEmpTaskPeriod ssEmpTaskPeriod = taskPeriods.get(0);
        //起缴月份
        String startMonth = ssEmpTaskPeriod.getStartMonth();
        Integer handleMonthInt = Integer.valueOf(handleMonth);
        Integer startMonthInt = Integer.valueOf(startMonth);

        //如果起缴月份在办理月份之前 如 3月份 报 1月份入职
        if (startMonthInt < handleMonthInt) {
            //获得办理月份上月的月份值
            String endMonth = TaskCommonUtils.getLastMonth(handleMonthInt);
            SsEmpTaskPeriod cloneObj = new SsEmpTaskPeriod();
            BeanUtils.copyProperties(ssEmpTaskPeriod, cloneObj);
            cloneObj.setEndMonth(endMonth);
            ssEmpTaskPeriod.setStartMonth(handleMonth);
            //新增做补缴的福利段
            backPeriods.add(cloneObj);
        }

        //获得费用段 用于插入数据库
        baseTaskPeriods.add(ssEmpTaskPeriod);
        //-1 代表新进 task表对应的费用段 转 福利段对应的费用段
        List<SsEmpBasePeriod> basePeriods = taskPeriodTranserEmpBase(baseTaskPeriods, bo, -1);
        ssEmpBasePeriodService.saveForEmpTaskId(basePeriods, empTaskId);

        // 险种
        // 更新雇员社保汇缴基数明细
        // 险种的数据段 （前道传递过来的）
        List<SsEmpTaskFront> empSocials = getEmpSocials(bo);
        if (empSocials.size() == 0) {
            throw new BusinessException("前道传递险种详细信息为空");
        }
        //添加明细 （养 医 失 工 生育 险种）
        addEmpBaseDetail(basePeriods, empSocials, empArchiveId, bo.getModifiedBy(), bo.getRoundTypeMap());
        bo.setListEmpBasePeriod(basePeriods);
        {//生成非标数据
            SsEmpBasePeriod ssEmpBasePeriod = basePeriods.get(0);
            createNonstandardData(bo, ssEmpBasePeriod, null, null, null);
        }
        if (backPeriods.size() != 0) {
            Integer taskCategory = bo.getTaskCategory();
            bo.setTaskCategory(Integer.valueOf(SocialSecurityConst.TASK_TYPE_4));
            //查询既存缴纳费用段
            List<SsEmpBasePeriod> ssEmpBasePeriodList = getPeriodsByEmployeeIdAndCompanyId(bo);
            backStartForTaskPeriods(backPeriods, ssEmpBasePeriodList, bo);

            bo.setTaskCategory(taskCategory);
        }

        // 手动填写的社保序号若大于当前种子，则更新为最新的种子
        if (bo.getComAccountId() != null && StringUtils.isNotEmpty(bo.getEmpSsSerial())) {
            Long existsSsSerial = ssComAccountService.getSerialByComAccountId(bo.getComAccountId());
            long ssSerial = Long.parseLong(bo.getEmpSsSerial());

            if (existsSsSerial == null || ssSerial > existsSsSerial) {
                SsComAccount ssComAccount = new SsComAccount();
                ssComAccount.setSsSerial(ssSerial);
                ssComAccount.setComAccountId(bo.getComAccountId());
                ssComAccount.setModifiedTime(LocalDateTime.now());
                ssComAccount.setModifiedBy(UserContext.getUserId());
                ssComAccountService.updateById(ssComAccount);
            }
        }
    }

//    private void setDetailCountNoChange(SsEmpTaskBO bo) {
//        if (StringUtils.isNotEmpty(bo.getOldAgreementId()) && StringUtils.isNotEmpty(bo.getBusinessInterfaceId())) {
//            Integer oldCount = ssEmpTaskFrontService.getEmpTaskDetailCount(bo.getOldAgreementId());
//            Integer newCount = ssEmpTaskFrontService.getEmpTaskDetailCount(bo.getBusinessInterfaceId());
//
//            if (oldCount != null && !oldCount.equals(newCount)) {
//                bo.setSocCountChange(true);
//            }
//        }
//    }

    private void checkStartMonth(SsEmpTaskBO bo) {
        YearMonth startMonthDate = YearMonth.parse(bo.getStartMonth(), formatter);
        YearMonth handleMonthDate = YearMonth.parse(bo.getHandleMonth(), formatter);

        if (startMonthDate.isAfter(handleMonthDate)) {
            throw new BusinessException("[" + bo.getEmployeeName() + "]该雇员起缴月份必须小于或者等于办理月份");
        }
    }

    private void checkEndMonth(SsEmpTaskBO bo) {
        YearMonth endMonthDate = YearMonth.parse(bo.getEndMonth(), formatter);
        YearMonth handleMonthDate = YearMonth.parse(bo.getHandleMonth(), formatter);

        if (!endMonthDate.plusMonths(1).equals(handleMonthDate)) {
            throw new BusinessException("[" + bo.getEmployeeName() + "]该雇员截止月份必须等于办理月份前一月");
        }
    }

    /**
     * 根据任务单ID逻辑删除报表记录及其明细记录
     *
     * @param empTaskId  任务单ID
     * @param modifiedBy
     */
    private void inactiveMonthChargeData(Long empTaskId, String modifiedBy) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("emp_task_id", empTaskId);
        condition.put("is_active", 1);
        List<SsMonthCharge> ssMonthChargeList = ssMonthChargeService.selectByMap(condition);

        if (CollectionUtils.isNotEmpty(ssMonthChargeList)) {
            for (SsMonthCharge ssMonthCharge : ssMonthChargeList) {
                SsMonthChargeItem ssMonthChargeItem = new SsMonthChargeItem();
                ssMonthChargeItem.setActive(false);
                ssMonthChargeItem.setModifiedTime(LocalDateTime.now());
                ssMonthChargeItem.setModifiedBy(modifiedBy);

                Wrapper<SsMonthChargeItem> wrapper = new EntityWrapper<>();
                wrapper.eq("month_charge_id", ssMonthCharge.getMonthChargeId());
                wrapper.eq("is_active", 1);
                ssMonthChargeItemService.update(ssMonthChargeItem, wrapper);

                SsMonthCharge delMonthCharge = new SsMonthCharge();
                delMonthCharge.setMonthChargeId(ssMonthCharge.getMonthChargeId());
                delMonthCharge.setActive(false);
                delMonthCharge.setModifiedTime(LocalDateTime.now());
                delMonthCharge.setModifiedBy(modifiedBy);
                ssMonthChargeService.updateById(delMonthCharge);
            }
        }
    }

    /**
     * 根据任务单ID逻辑删除差额补缴（逆调）费用段记录及其明细记录
     *
     * @param empTaskId  任务单ID
     * @param modifiedBy
     */
    private void inactiveBaseAdjustData(Long empTaskId, String modifiedBy) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("emp_task_id", empTaskId);
        condition.put("is_active", 1);
        List<SsEmpBaseAdjust> ssEmpBaseAdjustList = ssEmpBaseAdjustService.selectByMap(condition);

        if (CollectionUtils.isNotEmpty(ssEmpBaseAdjustList)) {
            for (SsEmpBaseAdjust ssEmpBaseAdjust : ssEmpBaseAdjustList) {
                SsEmpBaseAdjustDetail ssEmpBaseAdjustDetail = new SsEmpBaseAdjustDetail();
                ssEmpBaseAdjustDetail.setActive(false);
                ssEmpBaseAdjustDetail.setModifiedTime(LocalDateTime.now());
                ssEmpBaseAdjustDetail.setModifiedBy(modifiedBy);

                Wrapper<SsEmpBaseAdjustDetail> wrapper = new EntityWrapper<>();
                wrapper.eq("emp_base_adjust_id", ssEmpBaseAdjust.getEmpBaseAdjustId());
                wrapper.eq("is_active", 1);
                ssEmpBaseAdjustDetailService.update(ssEmpBaseAdjustDetail, wrapper);

                SsEmpBaseAdjust delEmpBaseAdjust = new SsEmpBaseAdjust();
                delEmpBaseAdjust.setEmpBaseAdjustId(ssEmpBaseAdjust.getEmpBaseAdjustId());
                delEmpBaseAdjust.setActive(false);
                delEmpBaseAdjust.setModifiedTime(LocalDateTime.now());
                delEmpBaseAdjust.setModifiedBy(modifiedBy);
                ssEmpBaseAdjustService.updateById(delEmpBaseAdjust);
            }
        }
    }

    /**
     * 根据任务单ID逻辑删除雇员费用段记录及其明细记录
     *
     * @param empTaskId  任务单ID
     * @param modifiedBy
     */
    private void inactiveBasePeriodData(Long empTaskId, String modifiedBy) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("emp_task_id", empTaskId);
        condition.put("is_active", 1);
        List<SsEmpBasePeriod> ssEmpBasePeriodList = ssEmpBasePeriodService.selectByMap(condition);

        if (CollectionUtils.isNotEmpty(ssEmpBasePeriodList)) {
            for (SsEmpBasePeriod ssEmpBasePeriod : ssEmpBasePeriodList) {
                SsEmpBaseDetail ssEmpBaseDetail = new SsEmpBaseDetail();
                ssEmpBaseDetail.setActive(false);
                ssEmpBaseDetail.setModifiedTime(LocalDateTime.now());
                ssEmpBaseDetail.setModifiedBy(modifiedBy);

                Wrapper<SsEmpBaseDetail> wrapper = new EntityWrapper<>();
                wrapper.eq("emp_base_period_id", ssEmpBasePeriod.getEmpBasePeriodId());
                wrapper.eq("is_active", 1);
                ssEmpBaseDetailService.update(ssEmpBaseDetail, wrapper);

                SsEmpBasePeriod delEmpBasePeriod = new SsEmpBasePeriod();
                delEmpBasePeriod.setEmpBasePeriodId(ssEmpBasePeriod.getEmpBasePeriodId());
                delEmpBasePeriod.setActive(false);
                delEmpBasePeriod.setModifiedTime(LocalDateTime.now());
                delEmpBasePeriod.setModifiedBy(modifiedBy);
                ssEmpBasePeriodService.updateById(delEmpBasePeriod);
            }
        }
    }

    /**
     * 逻辑删除雇员档案
     *
     * @param companyId
     * @param employeeId
     * @param ssEmpArchiveId
     * @param modifiedBy
     */
    private void inactiveEmpArchive(String companyId, String employeeId, Long ssEmpArchiveId, String modifiedBy) {
        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setActive(false);
        ssEmpArchive.setModifiedTime(LocalDateTime.now());
        ssEmpArchive.setModifiedBy(modifiedBy);

        Wrapper<SsEmpArchive> wrapper = new EntityWrapper<>();
        wrapper.eq("company_id", companyId);
        wrapper.eq("employee_id", employeeId);
        wrapper.eq("emp_archive_id", ssEmpArchiveId);
        wrapper.eq("archive_status", 1);
        wrapper.eq("is_active", 1);
        ssEmpArchiveService.update(ssEmpArchive, wrapper);
    }

    /**
     * 批量办理 查询用户对应的 企业是否开户
     *
     * @param bo
     */
    private void queryCompanyIsOpenAccount(SsEmpTaskBO bo) {
        SsComAccountBO ssComAccountBO = ssComAccountService.queryByEmpTaskId(String.valueOf(bo.getEmpTaskId()), String.valueOf(bo.getTaskCategory()));
        if (ssComAccountBO == null || null == ssComAccountBO.getComAccountId())
            throw new BusinessException("[" + bo.getEmployeeName() + "]该雇员对应的公司没有开户");
    }

    /**
     * 查询雇员是否 有新进和转入
     *
     * @param bo
     */
    private void queryEmployeeIsnewOrChangeInto(SsEmpTaskBO bo) {
        //新进需要判断雇员已经做过新进或转入
        if (bo.getTaskCategory() == 1 || bo.getTaskCategory() == 2 || bo.getTaskCategory() == 12 || bo.getTaskCategory() == 13) {
            //
            SsEmpArchiveBO ssEmpArchiveBO = ssEmpArchiveService.queryEmployeeIsnewOrChangeInto(String.valueOf(bo.getEmpTaskId()));
            if (null != ssEmpArchiveBO.getEmpArchiveId()) {
                throw new BusinessException("[" + bo.getEmployeeName() + "]该雇员已经做过新进转入");
            }
        } else {
            SsEmpArchiveBO ssEmpArchiveBO = ssEmpArchiveService.queryByEmpTaskId(String.valueOf(bo.getEmpTaskId()), String.valueOf(bo.getTaskCategory()));
            if (null == ssEmpArchiveBO.getEmpArchiveId()) {
                throw new BusinessException("[" + bo.getEmployeeName() + "]该雇员未做新进或者转入");
            }
            bo.setEmpArchiveId(ssEmpArchiveBO.getEmpArchiveId());
        }

    }

    private SsEmpTaskPeriod getSsEmpTaskObjWhenHasNot(SsEmpTaskBO bo) {
        SsEmpTaskPeriod ssEmpTaskPeriod = new SsEmpTaskPeriod();
        ssEmpTaskPeriod.setEmpTaskId(bo.getEmpTaskId());
        ssEmpTaskPeriod.setStartMonth(bo.getStartMonth());
        ssEmpTaskPeriod.setEndMonth(bo.getEndMonth());
        ssEmpTaskPeriod.setRemitWay(1);
        ssEmpTaskPeriod.setBaseAmount(bo.getEmpBase());
        return ssEmpTaskPeriod;
    }

    /**
     * 添加时间段明细表
     * ss_emp_base_detail
     *
     * @param basePeriods
     * @param empSocials
     * @param empArchiveId
     */
    private void addEmpBaseDetail(List<SsEmpBasePeriod> basePeriods, List<SsEmpTaskFront> empSocials, Long empArchiveId, String modifiedBy, Map<String, Map<String, Integer>> roundTypeMap) {
        //if (StringUtils.isBlank(roundType))throw new BusinessException("服务器异常");
        basePeriods.forEach(p -> {
            // 组合险种和费用段
            List<SsEmpBaseDetail> detailsList = new ArrayList<>();
            //费用段id
            Long empBasePeriodId = p.getEmpBasePeriodId();
            //前端提交设置的基数
            BigDecimal empBase = p.getBaseAmount();
            empSocials.forEach(empSocial -> {
                SsEmpBaseDetail detail = Adapter.ssEmpBaseDetail(empSocial);
                detail.setEmpBase(empBase);
                detail.setComBase(empBase);
                detail.setEmpArchiveId(empArchiveId);
                //个人金额 个人基数*个人比例
                BigDecimal empRatio = detail.getEmpRatio() != null ? detail.getEmpRatio() : BigDecimal.valueOf(0);

                BigDecimal empAmount = CalculateSocialUtils.calculateAmount(detail.getEmpBase(), empRatio, null, 2, null == roundTypeMap ? 1 : roundTypeMap.get(detail.getSsType()).get(PERSONROUNDTYPE));
                detail.setEmpAmount(empAmount);
                //公司金额 个人基数*个人比例
                BigDecimal comRatio = detail.getComRatio() != null ? detail.getComRatio() : BigDecimal.valueOf(0);
                //resetComRatio(BigDecimal comRatio);//工伤保险
                BigDecimal comAmount = CalculateSocialUtils.calculateAmount(
                    detail.getComBase(), comRatio, null, 2,
                    null == roundTypeMap ? 1 : roundTypeMap.get(detail.getSsType()).get(COMPANYROUNDTYPE));

                detail.setComAmount(comAmount);
                //个人+公司
                detail.setComempAmount(detail.getEmpAmount().add(detail.getComAmount()));
                detail.setEmpBasePeriodId(empBasePeriodId);
                by(detail);
                detailsList.add(detail);
            });
            p.setListEmpBaseDetail(detailsList);
            if(empArchiveId != null){
                SsEmpBaseDetail ssEmpBaseDetail = new SsEmpBaseDetail();
                ssEmpBaseDetail.setActive(false);
                ssEmpBaseDetail.setModifiedTime(LocalDateTime.now());
                ssEmpBaseDetail.setModifiedBy(modifiedBy);
                Wrapper<SsEmpBaseDetail> wrapper = new EntityWrapper<>();
                wrapper.eq("emp_archive_id", empArchiveId);
                wrapper.eq("emp_base_period_id", empBasePeriodId);
                ssEmpBaseDetailService.saveForSsEmpBaseDetail(detailsList, ssEmpBaseDetail, wrapper);
            }
        });
    }

    /*
     * 获得险种，根据业务接口 ID 查询险种或解析任务单扩展字段
     *
     * @param bo
     * @return
     */
    List<SsEmpTaskFront> getEmpSocials(SsEmpTaskBO bo) {
        //mp 的条件构造对象
        EntityWrapper<SsEmpTaskFront> entityWrapper = new EntityWrapper<SsEmpTaskFront>();
        //构建条件
        entityWrapper.where("emp_task_id={0}", bo.getEmpTaskId()).and("is_active=1");
        //查询
        List<SsEmpTaskFront> SsEmpTaskFrontList = ssEmpTaskFrontService.selectList(entityWrapper);

        return SsEmpTaskFrontList;
    }

    void by(Object entity) {
        BeanMap bm = new BeanMap(entity);
        bm.put("createdBy", UserContext.getUserId());
        bm.put("modifiedBy", UserContext.getUserId());
    }

    /**
     * 查询正常缴纳费用段 通过start 降序查询
     *
     * @param bo
     * @return
     */
    private List<SsEmpBasePeriod> getNormalPeriod(SsEmpTaskBO bo) {
        EntityWrapper<SsEmpBasePeriod> ew = new EntityWrapper<>();
        ew.where("emp_archive_id={0}", bo.getEmpArchiveId()).and("is_active=1").orderBy("start_month", false);
        return ssEmpBasePeriodService.selectList(ew);
    }

    /**
     * 查询正常缴纳费用段 通过start 降序查询
     *
     * @param bo
     * @return
     */
    private List<SsEmpBasePeriod> getPeriodsByEmployeeIdAndCompanyId(SsEmpTaskBO bo) {
        return ssEmpBasePeriodService.queryPeriodByEmployeeIdAndCompanyId(bo.getCompanyId(), bo.getEmployeeId());
    }

    /**
     * Stashed changes
     * 适配器
     */
    static class Adapter {
        /**
         * 适配《雇员社保汇缴基数明细》
         *
         * @param empSocial
         * @return
         */
        public static SsEmpBaseDetail ssEmpBaseDetail(SsEmpTaskFront empSocial) {
            SsEmpBaseDetail detail = new SsEmpBaseDetail();
            detail.setSsType(empSocial.getItemDicId());
            detail.setSsTypeName(empSocial.getPolicyName());
            detail.setEmpRatio(empSocial.getPersonalRatio());
            detail.setComRatio(empSocial.getCompanyRatio());
            return detail;
        }

        /**
         * 适配《雇员正常汇缴社保的基数分段》
         *
         * @param taskPeriod
         * @param bo
         * @param @return
         */
        public static SsEmpBasePeriod ssEmpBasePeriod(SsEmpTaskPeriod taskPeriod, SsEmpTaskBO bo) {
            SsEmpBasePeriod basePeriod = new SsEmpBasePeriod();
            basePeriod.setBaseAmount(taskPeriod.getBaseAmount());
            basePeriod.setEmpTaskId(taskPeriod.getEmpTaskId());
            basePeriod.setEndMonth(taskPeriod.getEndMonth());
            basePeriod.setStartMonth(taskPeriod.getStartMonth());
            basePeriod.setRemitWay(taskPeriod.getRemitWay());
            basePeriod.setEmpArchiveId(bo.getEmpArchiveId());
            basePeriod.setEmpTaskId(bo.getEmpTaskId());
            //办理月份
            basePeriod.setSsMonth(bo.getHandleMonth());
            return basePeriod;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // ------------------------------ 内部接口和内部类----------------------------------------------
    // ---------------------------------------------------------------------------------------------

    /**
     * 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6封存 7退账 8 提取 9特殊操作
     */
    interface taskCategoryConst {
        int INTO = 2;// 转入
    }

    /**
     * 办理状态：1、未处理 2 、处理中(已办)  3 已完成(已做) 4、批退 5、不需处理
     */
    interface TaskStatusConst {

        int NOTPROGRESS = 1;// 未处理
        int PROCESSING = 2;// 处理中
        int FINISH = 3;// 已完成
        int REJECTION = 4;// 批退
        int NOPROGRESS = 5;// 不需处理
    }

    interface ArchiveStatusConst {

        int PROCESSING = 1;// 已办
        int FINISH = 2;// 已做
        int OUT = 3;// 转出
    }

    // 社保档案任务状态 : 0-未办理 1-已办  2-已做 3-转出
    interface ArchiveTaskStatusConst {

        int NOTPROGRESS = 0;// 未处理
        int PROCESSING = 1;// 已办
        int FINISH = 2;// 已做
        int OUT = 3;// 转出
    }

    /**
     * 1 新进 2 转入 3 调整 4 补缴 5 转出 6封存 7退账 8 提取
     */
    interface TaskTypeConst {
        int NEW = 1;// 新进
        int INTO = 2;// 转入
        int ADJUSTMENT = 3;// 调整
        int BACK = 4;// 补缴
        int TURNOUT = 5;//转出
        int SEALED = 6;//封存
        int REFUNDACCOUNT = 7;//退账
        int FLOPNEW = 12;// 翻牌新进
        int FLOPINTO = 13;// 翻牌转入
        int FLOPTURNOUT = 14;// 翻牌转出
        int FLOPSEALED = 15;// 翻牌封存

    }

    interface TaskPeriodConst {

        String SIZEISONE = "sizeIsOne";//list 集合size是否是一个
        String SORT = "sort"; //集合排序
        String SSEMPTASKPERIOD = "ssEmpTaskPeriod";//集合对象
        Integer ADJUSTMENTTYPE = 1; //代表调整
        Integer SUPPLEMENTARYPAYTYPE = 2; //代表补缴
        String OVERLAPPING = "overlapping";//代表调整(逆调==差额补缴)的标志
        String OLDBASE = "oldBase";//代表前端传过来的时间段
        String ADJUSTADDLIST = "adjustAddList";//代表调整 从当前月到将来的 集合
        String SSEMPBASEADJUST = "ssEmpBaseAdjust";//调整差异表对应的对象
        String SSEMPBASEADJUSTDETAILLIST = "ssEmpBaseAdjustDetailList";//调整差异明细表对应的集合对象
        String SUPPLEMENTARYPAY = "supplementaryPayList"; //代表调整(全额补缴)的标志
    }

    private SsEmpArchive getArchive(SsEmpTaskBO bo) {
        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setComAccountId(bo.getComAccountId());
        ssEmpArchive.setCompanyId(bo.getCompanyId());
        ssEmpArchive.setEmployeeId(bo.getEmployeeId());
        ssEmpArchive.setSsSerial(bo.getEmpSsSerial());
        ssEmpArchive.setSalary(bo.getSalary());
        ssEmpArchive.setEmpClassify(bo.getEmpClassify());
        ssEmpArchive.setInDate(bo.getInDate());
        setEmpArchiveStatus(ssEmpArchive, bo.getTaskCategory());
        ssEmpArchive.setStartMonth(bo.getStartMonth());
        ssEmpArchive.setEndMonth(bo.getEndMonth());
        ssEmpArchive.setSsMonth(bo.getHandleMonth());
        ssEmpArchive.setActive(true);
        ssEmpArchive.setCreatedTime(LocalDateTime.now());
        ssEmpArchive.setCreatedBy(UserContext.getUserId());
        ssEmpArchive.setModifiedTime(LocalDateTime.now());
        ssEmpArchive.setModifiedBy(UserContext.getUserId());
        ssEmpArchive.setWelfareUnit(bo.getWelfareUnit());
        ssEmpArchive.setServiceCenterId(bo.getServiceCenterId());
        ssEmpArchive.setServiceCenter(bo.getServiceCenter());
        return ssEmpArchive;
    }

    /**
     * 获得雇员任务单
     *
     * @param bo
     * @return
     */
    SsEmpTask getSsEmpTask(SsEmpTaskBO bo) {
        return baseMapper.selectById(bo);
    }

    /**
     * 查询任务单信息
     *
     * @param ssEmpTaskBO
     */
    public List<SsEmpTaskBO> queryByTaskId(SsEmpTaskBO ssEmpTaskBO) {
        return baseMapper.queryByTaskId(ssEmpTaskBO);
    }

    /**
     * 查询任务单信息
     *
     * @param ssEmpTaskBO
     */
    public List<SsEmpTaskBO> queryByBusinessInterfaceId(SsEmpTaskBO ssEmpTaskBO) {
        return baseMapper.queryByBusinessInterfaceId(ssEmpTaskBO);
    }

    /**
     * 查询 证件号码
     *
     * @param employeeId
     */
    @Override
    public SsEmpTaskBO selectIdNumByEmployeeId(String employeeId) {
        return baseMapper.selectIdNumByEmployeeId(employeeId);
    }

    @Override
    public List<SsEmpTask> queryEmpTaskById(Long empTaskId, String userId) {
        return baseMapper.queryEmpTaskById(empTaskId, userId);
    }

    /**
     * 批量批退操作
     * @param ids
     * @param remark
     * @return
     */
    @Override
    public Boolean batchRejection(List<Long> ids, String remark) {
        int length = ids.size();
        List<String> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(ids.get(i).toString());
        }
        List<SsEmpTask> empTaskList = baseMapper.selectBatchIds(list);
        for(SsEmpTask ssEmpTask : empTaskList){
            SsEmpTaskBO bo =new SsEmpTaskBO();
            BeanUtils.copyProperties(ssEmpTask,bo);
            SsEmpTaskFront ssEmpTaskFront =new SsEmpTaskFront();
            ssEmpTaskFront.setEmpTaskId(ssEmpTask.getEmpTaskId());
            EntityWrapper wrapper = new EntityWrapper(ssEmpTaskFront);
            List<SsEmpTaskFront> taskFrontPeriods =  ssEmpTaskFrontService.selectList(wrapper);
            List<SsEmpBasePeriod> basePeriodsList =new ArrayList();
            taskFrontPeriods.stream().forEach(period->{
                SsEmpBasePeriod ssEmpBasePeriod=new SsEmpBasePeriod();
                ssEmpBasePeriod.setStartMonth(period.getStartMonth().toString());
                ssEmpBasePeriod.setEndMonth(null == period.getEndMonth()?"":period.getEndMonth().toString());
                ssEmpBasePeriod.setBaseAmount(period.getCompanyBase());
                basePeriodsList.add(ssEmpBasePeriod);
            });
            // 险种的数据段 （前道传递过来的）
            List<SsEmpTaskFront> empSocials = getEmpSocials(bo);
            if (empSocials.size() == 0) {
                throw new BusinessException("前道传递险种详细信息为空");
            }
            // 添加明细
            addEmpBaseDetail(basePeriodsList, empSocials, bo.getEmpArchiveId(), bo.getModifiedBy(), bo.getRoundTypeMap());
            bo.setListEmpBasePeriod(basePeriodsList);
            bo.setCompanyConfirmAmount(new BigDecimal(0));
            bo.setPersonalConfirmAmount(new BigDecimal(0));
            taskCompletCallBack(bo);
            ssEmpTask.setRejectionRemark(remark);
            ssEmpTask.setTaskStatus(TaskStatusConst.REJECTION);
            ssEmpTask.setRejectionRemarkDate(LocalDate.now());
            ssEmpTask.setRejectionRemarkMan(UserContext.getUser().getDisplayName());
            baseMapper.updateById(ssEmpTask);
        } //for
        return true;
    }

    /**
     * 非标
     *
     * @param ssEmpTaskBO
     * @param origSsEmpBasePeriod
     * @param ssEmpBaseAdjust
     * @param ssEmpBaseAdjustDetailList
     */
    public void createNonstandardData(
        SsEmpTaskBO ssEmpTaskBO,
        SsEmpBasePeriod origSsEmpBasePeriod,
        SsEmpBaseAdjust ssEmpBaseAdjust,
        List<SsEmpBaseAdjustDetail> ssEmpBaseAdjustDetailList,
        SsEmpRefund ssEmpRefund
    ) {
        SsEmpBasePeriod ssEmpBasePeriod = null;
        if (origSsEmpBasePeriod != null) {
            ssEmpBasePeriod = TaskCommonUtils.cloneObjet(origSsEmpBasePeriod, SsEmpBasePeriod.class);
        }

        //1新进  2  转入 3  调整 4 补缴 5 转出 6封存 7退账  9 特殊操作  10 集体转入   11 集体转出 12 翻牌新进 13翻牌转入 14翻牌转出 15翻牌封存
        switch (ssEmpTaskBO.getTaskCategory()) {
            case 1:
            case 2:
            case 12:
            case 13:
                ssEmpBasePeriod.setEndMonth(ssEmpBasePeriod.getSsMonth());
                //新进转入 只有办理月份 需要做非标数据
                createNewOrIntoNonstandard(ssEmpTaskBO, ssEmpBasePeriod);
                break;
            case 3://3  调整
                if (1 == ssEmpTaskBO.getAdustType()) {//顺调
                    ssEmpBasePeriod.setEndMonth(ssEmpBasePeriod.getSsMonth());
                    createNewOrIntoNonstandard(ssEmpTaskBO, ssEmpBasePeriod);
                } else {//逆调
                    createAdjustNonstandard(ssEmpTaskBO, ssEmpBaseAdjust, ssEmpBaseAdjustDetailList);
                }
                break;
            case 4:
                //补缴
                if (ssEmpTaskBO.getAdustType() != null && ssEmpTaskBO.getAdustType() == 0) {
                    createAdjustNonstandard(ssEmpTaskBO, ssEmpBaseAdjust, ssEmpBaseAdjustDetailList);
                } else {
                    createNewOrIntoNonstandard(ssEmpTaskBO, ssEmpBasePeriod);
                }
                break;
            case 5:
            case 6:
            case 14:
            case 15:
                ssEmpBasePeriod.setStartMonth(ssEmpTaskBO.getHandleMonth());
                ssEmpBasePeriod.setEndMonth(ssEmpTaskBO.getHandleMonth());
                //转出 封存 只有办理月份 需要做非标数据
                createNewOrIntoNonstandard(ssEmpTaskBO, ssEmpBasePeriod);
                break;
            case 7:
                //退账
                createRefundNonstandard(ssEmpTaskBO, ssEmpRefund);
                break;
        }
    }

    /**
     * 退账 非标数据
     *
     * @param ssEmpTaskBO
     * @param ssEmpRefund
     */
    private void createRefundNonstandard(SsEmpTaskBO ssEmpTaskBO, SsEmpRefund ssEmpRefund) {
        int startMonth = Integer.valueOf(ssEmpRefund.getStartMonth());
        int endMontrh = Integer.valueOf(ssEmpRefund.getEndMonth());
        int totalMonthCount = endMontrh - startMonth + 1;
        //每个月的缴纳金额
        BigDecimal eachMonthAmount = ssEmpRefund.getAmount().divide(new BigDecimal(totalMonthCount));
        SsMonthCharge ssMonthCharge = getSsMonthCharge(ssEmpTaskBO);
        ssMonthCharge.setCostCategory(8);
        ssMonthCharge.setTotalAmount(eachMonthAmount);
        for (int i = startMonth; i <= endMontrh; i = TaskCommonUtils.getNextMonthInt(i)) {
            ssMonthCharge.setSsMonthBelong(String.valueOf(i));
            ssMonthChargeService.insert(ssMonthCharge);
        }
    }

    /**
     * 逆调
     *
     * @param ssEmpTaskBO
     * @param ssEmpBaseAdjust
     * @param ssEmpBasedjustDetailList
     */
    private void createAdjustNonstandard(SsEmpTaskBO ssEmpTaskBO, SsEmpBaseAdjust ssEmpBaseAdjust, List<SsEmpBaseAdjustDetail> ssEmpBasedjustDetailList) {
        //初始化
        ssEmpTaskBO.setCompanyConfirmAmount(new BigDecimal(0));
        ssEmpTaskBO.setPersonalConfirmAmount(new BigDecimal(0));
        addSsMonthChargeAndDetails(ssEmpTaskBO, null, null, ssEmpBaseAdjust, ssEmpBasedjustDetailList);
    }

    /**
     * 除 逆调 和 退账 其他的非标数据生成 走这里
     *
     * @param ssEmpTaskBO
     * @param ssEmpBasePeriod
     */
    private void createNewOrIntoNonstandard(SsEmpTaskBO ssEmpTaskBO, SsEmpBasePeriod ssEmpBasePeriod) {
        Long empBasePeriodId = ssEmpBasePeriod.getEmpBasePeriodId();
        //通过empBasePeriodId 获得明细
        EntityWrapper<SsEmpBaseDetail> ew = new EntityWrapper();
        ew.where("emp_base_period_id={0}", empBasePeriodId).and("is_active=1");
        List<SsEmpBaseDetail> ssEmpBaseDetailList = ssEmpBaseDetailService.selectList(ew);
        if (ssEmpBaseDetailList.size() == 0) throw new BusinessException("费用段明细数据不正确");
        //获得月度变更 对象 和 详细数据
        addSsMonthChargeAndDetails(ssEmpTaskBO, ssEmpBasePeriod, ssEmpBaseDetailList, null, null);
    }


    /**
     * 添加 非标数据
     *
     * @param ssEmpTaskBO
     * @param ssEmpBasePeriod
     * @param ssEmpBaseDetailList
     */
    private void addSsMonthChargeAndDetails(
        SsEmpTaskBO ssEmpTaskBO, SsEmpBasePeriod ssEmpBasePeriod, List<SsEmpBaseDetail> ssEmpBaseDetailList,
        SsEmpBaseAdjust ssEmpBaseAdjust, List<SsEmpBaseAdjustDetail> ssEmpBaseeAdjustDetailList
    ) {
        //创建通用的 月度非标数据
        SsMonthCharge ssMonthCharge = getSsMonthCharge(ssEmpTaskBO);
        //1新进  2  转入 3  调整 4 补缴 5 转出 6封存 7退账  9 特殊操作  10 集体转入   11 集体转出 12 翻牌
        switch (ssEmpTaskBO.getTaskCategory()) {
            case 1:
            case 10:
            case 12:
                ssMonthCharge.setCostCategory(2);
                createSsMonthChargeObject(ssEmpTaskBO, ssEmpBasePeriod, ssMonthCharge, ssEmpBaseDetailList);
                break;
            case 2:
            case 11:
            case 13:
                ssMonthCharge.setCostCategory(3);
                createSsMonthChargeObject(ssEmpTaskBO, ssEmpBasePeriod, ssMonthCharge, ssEmpBaseDetailList);
                break;
            case 3:
                if (1 == ssEmpTaskBO.getAdustType()) {//顺调
                    ssMonthCharge.setCostCategory(5);
                    createSsMonthChargeObject(ssEmpTaskBO, ssEmpBasePeriod, ssMonthCharge, ssEmpBaseDetailList);
                } else {//逆调
                    ssMonthCharge.setCostCategory(9);
                    createSsMonthChargeObject(ssEmpTaskBO, ssEmpBaseAdjust, ssEmpBaseeAdjustDetailList, ssMonthCharge);
                }
                break;
            case 4://补缴
                if (ssEmpTaskBO.getAdustType() != null && ssEmpTaskBO.getAdustType() == 0) { // 差额补缴
                    ssMonthCharge.setCostCategory(9);
                    createSsMonthChargeObject(ssEmpTaskBO, ssEmpBaseAdjust, ssEmpBaseeAdjustDetailList, ssMonthCharge);
                } else {
                    ssMonthCharge.setCostCategory(4);
                    createSsMonthChargeObject(ssEmpTaskBO, ssEmpBasePeriod, ssMonthCharge, ssEmpBaseDetailList);
                }
                break;
            case 5:
            case 14:
                ssMonthCharge.setCostCategory(6);
                createSsMonthChargeObject(ssEmpTaskBO, ssEmpBasePeriod, ssMonthCharge, ssEmpBaseDetailList);
                break;
            case 6:
            case 15:
                ssMonthCharge.setCostCategory(7);
                createSsMonthChargeObject(ssEmpTaskBO, ssEmpBasePeriod, ssMonthCharge, ssEmpBaseDetailList);
                break;
        }
    }

    /**
     * 创建通用的 月度非标数据
     *
     * @param ssEmpTaskBO
     * @return
     */
    private SsMonthCharge getSsMonthCharge(SsEmpTaskBO ssEmpTaskBO) {
        SsMonthCharge ssMonthCharge = new SsMonthCharge();
        ssMonthCharge.setEmpTaskId(ssEmpTaskBO.getEmpTaskId());
        ssMonthCharge.setComAccountId(ssEmpTaskBO.getComAccountId());
        ssMonthCharge.setEmployeeId(ssEmpTaskBO.getEmployeeId());
        ssMonthCharge.setEmpArchiveId(String.valueOf(ssEmpTaskBO.getEmpArchiveId()));
        ssMonthCharge.setSsMonth(ssEmpTaskBO.getHandleMonth());
        by(ssMonthCharge);
        return ssMonthCharge;
    }

    /**
     * 创建非标(顺调和新进转入 补缴)
     *
     * @param ssEmpTaskBO
     * @param ssEmpBasePeriod
     * @param ssMonthCharge
     */
    private void createSsMonthChargeObject(SsEmpTaskBO ssEmpTaskBO, SsEmpBasePeriod ssEmpBasePeriod, SsMonthCharge ssMonthCharge, List<SsEmpBaseDetail> ssEmpBaseDetailList) {
//        int startMonth = Integer.valueOf(ssEmpBasePeriod.getStartMonth());
//        int endMonth = Integer.valueOf(ssEmpBasePeriod.getEndMonth());
        YearMonth startMonthDate = YearMonth.parse(ssEmpBasePeriod.getStartMonth(), formatter);
        YearMonth endMonthDate = YearMonth.parse(ssEmpBasePeriod.getEndMonth(), formatter);
        BigDecimal negative = new BigDecimal(-1);
        //用于回调
        BigDecimal ponsernalAmount = new BigDecimal(0);
        BigDecimal companyAmount = new BigDecimal(0);
        boolean isOut = 6 == ssMonthCharge.getCostCategory() || 7 == ssMonthCharge.getCostCategory();
        long months = startMonthDate.until(endMonthDate, ChronoUnit.MONTHS);

//        for (int i = startMonth; i <= endMonth; i = TaskCommonUtils.getNextMonthInt(i)) {
//            ssMonthCharge.setSsMonthBelong(String.valueOf(i));
        for (long i = 0; i <= months; i++) {
            if (isOut) {
//                // 如果是转出任务单，雇员月度汇缴明细库汇缴当月已生成的标准数据需删除
//                ssMonthChargeService.deleteOldDate(ssEmpTaskBO.getEmployeeId(), ssEmpTaskBO.getHandleMonth(), ssEmpTaskBO.getHandleMonth(), 1, ssEmpTaskBO.getModifiedBy());

                // 如果是转出任务单，雇员月度汇缴明细库转入数据可能被删除（当月转入当月转出），且不生成转出数据
                int rslt = ssMonthChargeService.deleteOldDate(ssEmpTaskBO.getEmployeeId(), ssEmpTaskBO.getHandleMonth(), ssEmpTaskBO.getHandleMonth(), 2, ssEmpTaskBO.getModifiedBy());
                if (rslt > 0) {
                    continue;
                }
                rslt = ssMonthChargeService.deleteOldDate(ssEmpTaskBO.getEmployeeId(), ssEmpTaskBO.getHandleMonth(), ssEmpTaskBO.getHandleMonth(), 3, ssEmpTaskBO.getModifiedBy());
                if (rslt > 0) {
                    continue;
                }
                rslt = ssMonthChargeService.deleteOldDate(ssEmpTaskBO.getEmployeeId(), ssEmpTaskBO.getHandleMonth(), ssEmpTaskBO.getHandleMonth(), 5, ssEmpTaskBO.getModifiedBy());
                if (rslt > 0) {
                    continue;
                }
                ssMonthCharge.setSsMonthBelong(startMonthDate.minusMonths(1).plusMonths(i).format(formatter));
            } else {
                if (2 == ssMonthCharge.getCostCategory() || 3 == ssMonthCharge.getCostCategory() || 5 == ssMonthCharge.getCostCategory()) { // 新开或转入或顺调当月数据清除
                    ssMonthChargeService.deleteOldDate(ssEmpTaskBO.getEmployeeId(), ssEmpTaskBO.getHandleMonth(), ssEmpTaskBO.getHandleMonth(), 2, ssEmpTaskBO.getModifiedBy());
                    ssMonthChargeService.deleteOldDate(ssEmpTaskBO.getEmployeeId(), ssEmpTaskBO.getHandleMonth(), ssEmpTaskBO.getHandleMonth(), 3, ssEmpTaskBO.getModifiedBy());
                    ssMonthChargeService.deleteOldDate(ssEmpTaskBO.getEmployeeId(), ssEmpTaskBO.getHandleMonth(), ssEmpTaskBO.getHandleMonth(), 5, ssEmpTaskBO.getModifiedBy());
                }
                ssMonthCharge.setSsMonthBelong(startMonthDate.plusMonths(i).format(formatter));
            }
            //转出和封存是负数
            ssMonthCharge.setBaseAmount(ssEmpBasePeriod.getBaseAmount());

            if (5 == ssMonthCharge.getCostCategory()) {  // 如果是顺调，那么则根据标准数据，计算调整后差额录入
                List<SsMonthChargeBO> ssMonthChargeBOList = ssMonthChargeService.selectTotalFromOld(ssEmpTaskBO.getEmployeeId(), ssEmpTaskBO.getHandleMonth(), 1);

                if (CollectionUtils.isNotEmpty(ssMonthChargeBOList)) {
                    //计算总额 并添加
                    transBaseDetailToMonthChargeItem(ssEmpTaskBO, ssEmpBaseDetailList, ssMonthCharge, ssMonthChargeBOList);
                    return;
                }
            }

            List<SsMonthChargeItem> ssMonthChargeItemList = new ArrayList<>();
            ssMonthChargeService.insert(ssMonthCharge);
            BigDecimal totalAmount = new BigDecimal(0);
            //明细表
            for (int j = 0; j < ssEmpBaseDetailList.size(); j++) {
                SsEmpBaseDetail ssEmpBaseDetail = ssEmpBaseDetailList.get(j);
                SsMonthChargeItem ssMonthChargeItem = new SsMonthChargeItem();
                ssMonthChargeItem.setMonthChargeId(ssMonthCharge.getMonthChargeId());
                //转出和封存是负数
                ssMonthChargeItem.setComAmount(isOut ? ssEmpBaseDetail.getComAmount().multiply(negative) : ssEmpBaseDetail.getComAmount());
                ssMonthChargeItem.setEmpAmount(isOut ? ssEmpBaseDetail.getEmpAmount().multiply(negative) : ssEmpBaseDetail.getEmpAmount());
                ssMonthChargeItem.setSubTotalAmount(isOut ? ssEmpBaseDetail.getComempAmount().multiply(negative) : ssEmpBaseDetail.getComempAmount());
                ssMonthChargeItem.setSsType(ssEmpBaseDetail.getSsType());
                ssMonthChargeItem.setSsTypeName(ssEmpBaseDetail.getSsTypeName());
                by(ssMonthChargeItem);
                ssMonthChargeItemList.add(ssMonthChargeItem);
                //主表没有记录 总额的数据 所以需要先计算
                totalAmount = totalAmount.add(ssEmpBaseDetail.getComempAmount());
                ponsernalAmount = ponsernalAmount.add(ssEmpBaseDetail.getEmpAmount());
                companyAmount = companyAmount.add(ssEmpBaseDetail.getComAmount());
            }
            ssMonthCharge.setTotalAmount(isOut ? totalAmount.multiply(negative) : totalAmount);
            ssMonthChargeService.updateById(ssMonthCharge);
            ssMonthChargeItemService.insertBatch(ssMonthChargeItemList);
        }
        //设置实缴金额
        ssEmpTaskBO.setPersonalConfirmAmount(ponsernalAmount);
        ssEmpTaskBO.setCompanyConfirmAmount(companyAmount);
    }

    /**
     * 创建非标(逆调)
     *
     * @param ssEmpTaskBO
     * @param ssEmpBaseAdjust
     * @param ssEmpBaseeAdjustDetailList
     * @param ssMonthCharge
     */
    private void createSsMonthChargeObject(SsEmpTaskBO ssEmpTaskBO, SsEmpBaseAdjust ssEmpBaseAdjust, List<SsEmpBaseAdjustDetail> ssEmpBaseeAdjustDetailList, SsMonthCharge ssMonthCharge) {
        int startMonth = Integer.parseInt(ssEmpBaseAdjust.getStartMonth());
        int endMonth = Integer.parseInt(ssEmpBaseAdjust.getEndMonth());
        //将每个月拆分出来
        for (int i = startMonth; i <= endMonth; i = TaskCommonUtils.getNextMonthInt(i)) {
            //删除 该员工 该所属月份 该办理月份的 非标数据(即 重新发调整任务单 并且 该任务是已办未做)
            ssMonthChargeService.deleteOldDate(ssEmpTaskBO.getEmployeeId(), String.valueOf(i), ssEmpTaskBO.getHandleMonth(), ssMonthCharge.getCostCategory(), ssEmpTaskBO.getModifiedBy());
            //减去 该所属月份的调整的历史差额总和
            List<SsMonthChargeBO> ssMonthChargeBOList = ssMonthChargeService.selectTotalFromOld(ssEmpTaskBO.getEmployeeId(), String.valueOf(i), ssMonthCharge.getCostCategory());
            ssMonthCharge.setSsMonthBelong(String.valueOf(i));
            ssMonthCharge.setBaseAmount(ssEmpBaseAdjust.getNewBaseAmount());
            ssMonthCharge.setTotalAmount(ssEmpBaseAdjust.getComempDiffAmount());
            //计算总额 并添加
            calculationTotalAmountAndAdd(ssEmpTaskBO, ssMonthChargeBOList, ssEmpBaseeAdjustDetailList, ssMonthCharge);
        }
    }

    /**
     * 计算 历史调整 总额
     *
     * @param ssEmpTaskBO
     * @param ssMonthChargeBOList
     * @param ssEmpBaseeAdjustDetailList
     */
    private void calculationTotalAmountAndAdd(SsEmpTaskBO ssEmpTaskBO, List<SsMonthChargeBO> ssMonthChargeBOList, List<SsEmpBaseAdjustDetail> ssEmpBaseeAdjustDetailList, SsMonthCharge ssMonthCharge) {

        if (ssMonthChargeBOList.size() == 0) {
            //将 调整明细的 五险 转 非标的明细(无历史已做)
            transAdjustToMonthChargeItem(ssEmpTaskBO, ssEmpBaseeAdjustDetailList, ssMonthCharge, null);
        } else {
            //将 调整明细的 五险 转 非标的明细
            transAdjustToMonthChargeItem(ssEmpTaskBO, ssEmpBaseeAdjustDetailList, ssMonthCharge, ssMonthChargeBOList);
        }
    }

    private void transAdjustToMonthChargeItem(SsEmpTaskBO ssEmpTaskBO, List<SsEmpBaseAdjustDetail> ssEmpBaseeAdjustDetailList, SsMonthCharge ssMonthCharge, List<SsMonthChargeBO> ssMonthChargeBOList) {
        boolean haveHistoryData = null == ssMonthChargeBOList ? false : true;
        //记录每个险种对应的历史总额
        Map<String, BigDecimal[]> totalDetailMap = new HashMap();
        //有历史 已做数据
        if (haveHistoryData) {
            BigDecimal total = new BigDecimal(0);
            for (int i = 0; i < ssMonthChargeBOList.size(); i++) {
                SsMonthChargeBO ssMonthChargeBO = ssMonthChargeBOList.get(i);
                //计算总差额
                total = total.add(ssMonthChargeBO.getTotalAmount());
                //计算单个险种历史差异总额
                setDetailTotal(totalDetailMap, ssMonthChargeBO.getSsMonthChargeItemList());
            }
            //总额减去 历史
            ssMonthCharge.setTotalAmount(ssMonthCharge.getTotalAmount().subtract(total));
        }
        //添加 月度变更主表
        ssMonthChargeService.insert(ssMonthCharge);

        List<SsMonthChargeItem> ssMonthChargeItemList = new ArrayList<>();
        //将 差异表转换成 非标
        ssEmpBaseeAdjustDetailList.forEach(p -> {
            SsMonthChargeItem ssMonthChargeItem = new SsMonthChargeItem();
            ssMonthChargeItem.setSsTypeName(p.getSsTypeName());
            ssMonthChargeItem.setSsType(p.getSsType());
            if (haveHistoryData) {
                BigDecimal[] amountDetailArr = totalDetailMap.get(p.getSsType());
                ssMonthChargeItem.setEmpAmount(p.getEmpDiffAmount().subtract(amountDetailArr[0]));
                ssMonthChargeItem.setComAmount(p.getComDiffAmount().subtract(amountDetailArr[1]));
                ssMonthChargeItem.setSubTotalAmount(p.getComempDiffAmount().subtract(amountDetailArr[2]));
            } else {
                ssMonthChargeItem.setEmpAmount(p.getEmpDiffAmount());
                ssMonthChargeItem.setComAmount(p.getComDiffAmount());
                ssMonthChargeItem.setSubTotalAmount(p.getComempDiffAmount());
            }
            ssMonthChargeItem.setMonthChargeId(ssMonthCharge.getMonthChargeId());
            by(ssMonthChargeItem);
            ssMonthChargeItemList.add(ssMonthChargeItem);
            //实缴金额计算
            ssEmpTaskBO.setCompanyConfirmAmount(ssEmpTaskBO.getCompanyConfirmAmount().add(ssMonthChargeItem.getComAmount()));
            ssEmpTaskBO.setPersonalConfirmAmount(ssEmpTaskBO.getPersonalConfirmAmount().add(ssMonthChargeItem.getEmpAmount()));
        });
        ssMonthChargeItemService.insertBatch(ssMonthChargeItemList);
    }

    private void transBaseDetailToMonthChargeItem(SsEmpTaskBO ssEmpTaskBO, List<SsEmpBaseDetail> ssEmpBaseDetailList, SsMonthCharge ssMonthCharge, List<SsMonthChargeBO> ssMonthChargeBOList) {
        boolean haveHistoryData = null == ssMonthChargeBOList ? false : true;
        //记录每个险种对应的历史总额
        Map<String, BigDecimal[]> totalDetailMap = new HashMap();
        BigDecimal total = new BigDecimal(0);
        //有历史 已做数据
        if (haveHistoryData) {
            for (int i = 0; i < ssMonthChargeBOList.size(); i++) {
                SsMonthChargeBO ssMonthChargeBO = ssMonthChargeBOList.get(i);
                //计算总差额
                total = total.add(ssMonthChargeBO.getTotalAmount());
                //计算单个险种历史差异总额
                setDetailTotal(totalDetailMap, ssMonthChargeBO.getSsMonthChargeItemList());
            }
            //总额减去 历史
//            ssMonthCharge.setTotalAmount(ssMonthCharge.getTotalAmount().subtract(total));
        }
        ssMonthChargeService.insert(ssMonthCharge);

        if (ssEmpTaskBO.getCompanyConfirmAmount() == null) {
            ssEmpTaskBO.setCompanyConfirmAmount(BigDecimal.ZERO);
        }

        if (ssEmpTaskBO.getPersonalConfirmAmount() == null) {
            ssEmpTaskBO.setPersonalConfirmAmount(BigDecimal.ZERO);
        }

        List<SsMonthChargeItem> ssMonthChargeItemList = new ArrayList<>();
        BigDecimal totalAmount = new BigDecimal(0);
        //将 差异表转换成 非标
        for (SsEmpBaseDetail p : ssEmpBaseDetailList) {
            SsMonthChargeItem ssMonthChargeItem = new SsMonthChargeItem();
            ssMonthChargeItem.setSsTypeName(p.getSsTypeName());
            ssMonthChargeItem.setSsType(p.getSsType());
            if (haveHistoryData) {
                BigDecimal[] amountDetailArr = totalDetailMap.get(p.getSsType());
                ssMonthChargeItem.setEmpAmount(p.getEmpAmount().subtract(amountDetailArr[0]));
                ssMonthChargeItem.setComAmount(p.getComAmount().subtract(amountDetailArr[1]));
                ssMonthChargeItem.setSubTotalAmount(p.getComempAmount().subtract(amountDetailArr[2]));
            } else {
                ssMonthChargeItem.setEmpAmount(p.getEmpAmount());
                ssMonthChargeItem.setComAmount(p.getComAmount());
                ssMonthChargeItem.setSubTotalAmount(p.getComempAmount());
            }
            totalAmount = totalAmount.add(p.getComempAmount());
            ssMonthChargeItem.setMonthChargeId(ssMonthCharge.getMonthChargeId());
            by(ssMonthChargeItem);
            ssMonthChargeItemList.add(ssMonthChargeItem);
            //实缴金额计算
            ssEmpTaskBO.setCompanyConfirmAmount(ssEmpTaskBO.getCompanyConfirmAmount().add(ssMonthChargeItem.getComAmount()));
            ssEmpTaskBO.setPersonalConfirmAmount(ssEmpTaskBO.getPersonalConfirmAmount().add(ssMonthChargeItem.getEmpAmount()));
        }
        ssMonthCharge.setTotalAmount(totalAmount.subtract(total));
        //添加 月度变更主表
        ssMonthChargeService.updateById(ssMonthCharge);
        ssMonthChargeItemService.insertBatch(ssMonthChargeItemList);
    }

    /**
     * 获得单个险种历史差异总额
     *
     * @param totalDetail
     * @param ssMonthChargeItemList
     */
    private void setDetailTotal(Map<String, BigDecimal[]> totalDetail, List<SsMonthChargeItem> ssMonthChargeItemList) {

        for (int i = 0; i < ssMonthChargeItemList.size(); i++) {
            SsMonthChargeItem ssMonthChargeItem = ssMonthChargeItemList.get(i);
            String ssType = ssMonthChargeItem.getSsType();
            if (null == totalDetail.get(ssType)) {
                BigDecimal[] bigDecimalArr = new BigDecimal[3];
                bigDecimalArr[0] = ssMonthChargeItem.getEmpAmount();
                bigDecimalArr[1] = ssMonthChargeItem.getComAmount();
                bigDecimalArr[2] = ssMonthChargeItem.getSubTotalAmount();
                totalDetail.put(ssType, bigDecimalArr);
            } else {
                BigDecimal[] data = totalDetail.get(ssType);
                data[0] = data[0].add(ssMonthChargeItem.getEmpAmount());
                data[1] = data[1].add(ssMonthChargeItem.getComAmount());
                data[2] = data[2].add(ssMonthChargeItem.getSubTotalAmount());
            }

        }
    }

    /**
     * 任务单 完成 回调
     *
     * @param bo
     */
    void taskCompletCallBack(SsEmpTaskBO bo) {
        // 1新进  2  转入 3  调整 4 补缴 5 转出 6封存 7退账  9 特殊操作  10 集体转入   11 集体转出 12翻牌新进13翻牌转入14翻牌转出15翻牌封存

        if (bo.getTaskCategory() != 9) {
            //回调 实缴金额 接口  批退为0
            TaskCommonUtils.updateConfirmDate(commonApiUtils, bo);
        }
        //任务单完成接口调用
        TaskCommonUtils.completeTask(bo.getTaskId(), commonApiUtils, UserContext.getUser().getDisplayName());
    }

    /**
     * 请求进位方式 接口 获得各险种的 进位方式
     *
     * @param ssPolicyId
     * @param payAccountType
     * @param effectiveMonth
     */
    public void getRoundType(String ssPolicyId, int payAccountType, String effectiveMonth, SsEmpTaskBO ssEmpTaskBO) {
        GetSSPItemsRequestDTO getSSPItemsRequestDTO = new GetSSPItemsRequestDTO();
        getSSPItemsRequestDTO.setSsPolicyId(ssPolicyId);
        getSSPItemsRequestDTO.setPayAccountType(payAccountType);
        getSSPItemsRequestDTO.setEffectiveMonth(effectiveMonth);
        List<SSPItemDTO> resultList = TaskCommonUtils.getRoundTypeFromApi(commonApiUtils, getSSPItemsRequestDTO);
        //将返回结果封装 成Map 对象 便于使用
        handleRoundType(resultList, ssEmpTaskBO);
    }

    /**
     * 处理进位方式返回的结果
     *
     * @param resultList
     */
    private void handleRoundType(List<SSPItemDTO> resultList, SsEmpTaskBO ssEmpTaskBO) {
        if (null == resultList || resultList.size() == 0) return;//throw new BusinessException("进位方式返回值为null")
        Map<String, Map<String, Integer>> roundTypeMap = new HashMap<>();
        resultList.forEach(p -> {
            //某个险种下对用的 个人进位和公司进位方式
            Map<String, Integer> map = new HashMap<>();
            int personRoundType = 1;
            int companyRoundType = 1;
            if (p.getPersonRoundType() != null) {
                personRoundType = p.getPersonRoundType();
            }
            if (p.getCompanyRoundType() != null) {
                companyRoundType = p.getCompanyRoundType();
            }
            map.put(PERSONROUNDTYPE, personRoundType);
            map.put(COMPANYROUNDTYPE, companyRoundType);
            roundTypeMap.put(p.getItemCode(), map);
        });
        ssEmpTaskBO.setRoundTypeMap(roundTypeMap);
    }

    //工伤保险
    private void resetComRatio(BigDecimal comRatio) {
     /*   if(empSocial.getItemDicId().equals("DIT00044")){//工伤保险
                    List<Map<String,BigDecimal>> ratioList =  baseMapper.fetchInjuryRatio(empArchiveId,p.getStartMonth());
                    if (ratioList.size()!=1){
                        throw new BusinessException("在企业社保账户中找不到或存在多个工伤比例,请维护基础数据");
                    }
                    BigDecimal ssComRatio=ratioList.get(0).get("com_ratio");
//                    if(ssComRatio.compareTo( comRatio) != 0) {  //和前道比例比较
//                        throw new BusinessException("工伤保险的比例和前道存在差异");
//                    }
                    if(ssComRatio.compareTo( new BigDecimal("0")) ==1 ) { //大于0
                        comRatio = ssComRatio;
                    }
                }*/
    }

    /**
     * 将雇员费用段时间段连续的组合到一个对象中
     *
     * @param existsSsEmpBasePeriodList 雇员费用段列表
     * @return 雇员费用段组合对象列表
     */
    public List<ComposedEmpBasePeriodBO> composeEmpBasePeriod(List<SsEmpBasePeriod> existsSsEmpBasePeriodList) {
        String startMonth;
        String endMonth = null;
        YearMonth startMonthDate = null;
        YearMonth endMonthDate;
        List<ComposedEmpBasePeriodBO> composedEmpBasePeriodBOList = new ArrayList<>();

        for (SsEmpBasePeriod ssEmpBasePeriod : existsSsEmpBasePeriodList) {
            startMonth = ssEmpBasePeriod.getStartMonth();
            if (org.apache.commons.lang.StringUtils.isNotEmpty(startMonth)) {
                startMonthDate = YearMonth.parse(startMonth, formatter);
            }

            ComposedEmpBasePeriodBO composedEmpBasePeriodBO;

            if (StringUtils.isNotEmpty(endMonth)) {
                endMonthDate = YearMonth.parse(endMonth, formatter);

                if (endMonthDate.plusMonths(1).equals(startMonthDate)) {
                    composedEmpBasePeriodBO = composedEmpBasePeriodBOList.get(composedEmpBasePeriodBOList.size() - 1);
                } else {
                    composedEmpBasePeriodBO = new ComposedEmpBasePeriodBO();
                    composedEmpBasePeriodBO.setStartMonth(startMonthDate);
                    composedEmpBasePeriodBOList.add(composedEmpBasePeriodBO);
                }
            } else {
                composedEmpBasePeriodBO = new ComposedEmpBasePeriodBO();
                composedEmpBasePeriodBO.setStartMonth(startMonthDate);

                composedEmpBasePeriodBOList.add(composedEmpBasePeriodBO);
            }

            if (StringUtils.isNotEmpty(ssEmpBasePeriod.getEndMonth())) {
                composedEmpBasePeriodBO.setEndMonth(YearMonth.parse(ssEmpBasePeriod.getEndMonth(), formatter));
            } else {
                composedEmpBasePeriodBO.setEndMonth(null);
            }

            composedEmpBasePeriodBO.getContainSsEmpBasePeriods().add(ssEmpBasePeriod);
            endMonth = ssEmpBasePeriod.getEndMonth();
        }

        return composedEmpBasePeriodBOList;
    }

    /**
     * 根据任务单类型及雇员档案当前原始状态来设置雇员档案中的任务单状态及原始状态
     *
     * @param ssEmpArchive 雇员档案当前原始状态
     * @param taskCategory 任务单类型
     */
    private void setEmpArchiveStatus(SsEmpArchive ssEmpArchive, Integer taskCategory) {
        Integer origStatus = ssEmpArchive.getArchiveStatus();

        switch (taskCategory) {
            case TaskTypeConst.NEW:
            case TaskTypeConst.INTO:
            case TaskTypeConst.FLOPNEW:
            case TaskTypeConst.FLOPINTO:
            case TaskTypeConst.ADJUSTMENT:
                ssEmpArchive.setArchiveTaskStatus(ArchiveTaskStatusConst.PROCESSING);
                ssEmpArchive.setArchiveStatus(ArchiveStatusConst.PROCESSING);
                break;
            case TaskTypeConst.BACK:
            case TaskTypeConst.REFUNDACCOUNT:
                if (origStatus != null && origStatus == ArchiveStatusConst.OUT) {
                    ssEmpArchive.setArchiveTaskStatus(ArchiveTaskStatusConst.OUT);
                    ssEmpArchive.setArchiveStatus(ArchiveStatusConst.OUT);
                } else if (origStatus == null || origStatus == ArchiveStatusConst.PROCESSING || origStatus == ArchiveStatusConst.FINISH) {
                    ssEmpArchive.setArchiveTaskStatus(ArchiveTaskStatusConst.PROCESSING);
                    ssEmpArchive.setArchiveStatus(ArchiveStatusConst.PROCESSING);
                }
                break;
            case TaskTypeConst.TURNOUT:
            case TaskTypeConst.SEALED:
            case TaskTypeConst.FLOPTURNOUT:
            case TaskTypeConst.FLOPSEALED:
                ssEmpArchive.setArchiveTaskStatus(ArchiveTaskStatusConst.OUT);
                ssEmpArchive.setArchiveStatus(ArchiveStatusConst.OUT);
                break;
            default:
                break;
        }
    }
}

