package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.commonservice.util.dto.Result;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 本地社保的雇员任务单 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpTaskServiceImpl extends ServiceImpl<SsEmpTaskMapper, SsEmpTask>  implements ISsEmpTaskService{
    @Autowired
    ISsEmpTaskPeriodService ssEmpTaskPeriodService;

    @Autowired
    ISsEmpBasePeriodService ssEmpBasePeriodService;

    @Autowired
    ISsEmpBaseDetailService ssEmpBaseDetailService;

    @Autowired
    ISsEmpArchiveService ssEmpArchiveService;

    @Autowired
    ISsEmpTaskFrontService ssEmpTaskFrontService;
    @Autowired
    ISsEmpBaseAdjustService ssEmpBaseAdjustService;
    @Autowired
    ISsEmpBaseAdjustDetailService ssEmpBaseAdjustDetailService;
    @Autowired
    ISsEmpRefundService ssEmpRefundService;
    @Autowired
    CommonApiUtils commonApiUtils;

    @Override
    public PageRows<SsEmpTaskBO> employeeOperatorQuery(PageInfo pageInfo) {
        SsEmpTaskBO dto = pageInfo.toJavaObject(SsEmpTaskBO.class);
        handleTaskCategory(dto);
        if (2 == dto.getOperatorType()) {
            return PageKit.doSelectPage(pageInfo, () -> baseMapper.employeeSpecialOperatorQuery(dto));
        } else {
            return PageKit.doSelectPage(pageInfo, () -> baseMapper.employeeDailyOperatorQuery(dto));
        }
    }

    @Override
    public List<SsEmpTask> queryTaskByEmpArchiveId(String empArchiveId) {
        return baseMapper.queryTaskByEmpArchiveId(empArchiveId);
    }

    /**
     * 雇员日常操作 做事物控制
     *
     * @param bo
     * @return
     */
    @Transactional(rollbackFor =Exception.class )
    @Override
    public boolean saveHandleData(SsEmpTaskBO bo) {
        int taskStatus = bo.getTaskStatus();
        int taskCategory = bo.getTaskCategory();
        //批退调用工作流
        if(taskStatus==4){
            Result result = completeTask(bo);
            handleWorkflowResult(result);
        }
        // 更新任务单费用段
        List<SsEmpTaskPeriod> periods = bo.getEmpTaskPeriods();
        if (periods != null) {
            //表示有时间段
            ssEmpTaskPeriodService.saveForEmpTaskId(periods, bo.getEmpTaskId());
            periods = ssEmpTaskPeriodService.queryByEmpTaskId(bo.getEmpTaskId());
            bo.setEmpTaskPeriods(periods);
        }else{
            //无时间段
            //更新雇员任务信息
            baseMapper.updateMyselfColumnById(bo);
        }
        // 更新雇员任务信息
        // 备注时间
        LocalDate now = LocalDate.now();
        bo.setHandleRemarkDate(now);
        bo.setRejectionRemarkDate(now);
        bo.setModifiedTime(LocalDateTime.now());

        // 处理中，正式把数据写入到 ss_emp_base_period and ss_emp_base_detail(雇员社)
        if (TaskStatusConst.PROCESSING == taskStatus || TaskStatusConst.FINISH==taskStatus) {
            if (TaskTypeConst.NEW == taskCategory || TaskTypeConst.INTO == taskCategory) {
                //新进和转入
                newOrChangeInto(bo);
            } else if (TaskTypeConst.ADJUSTMENT == taskCategory) {
                //调整
                handleAdjustmentTask(bo);
            } else if (TaskTypeConst.BACK == taskCategory) {
                //补缴
                handleBackTask(bo);
            } else if (TaskTypeConst.TURNOUT == taskCategory) {
                //转出
                handleTurnOutTask(bo);
            } else if (TaskTypeConst.SEALED == taskCategory) {
                //封存
                handleSealedTask(bo);
            } else if (TaskTypeConst.REFUNDACCOUNT == taskCategory) {
                //退账
                handleRefundAccountTask(bo);
            } else if (TaskTypeConst.EXTRACT == taskCategory) {
                //提取
                handleExtractTask(bo);
            }
        } else {
            //更新雇员任务信息
            baseMapper.updateMyselfColumnById(bo);
        }

        return true;
    }

    @Override
    public String selectMaxSsSerialByTaskId(Long empTaskId) {
        return baseMapper.selectMaxSsSerialByTaskId(empTaskId);
    }
    /**
     * 调整
     *
     * @param bo
     */
    private void handleAdjustmentTask(SsEmpTaskBO bo) {
        //修改任务单详细
        baseMapper.updateMyselfColumnById(bo);
        //获得前端输入的缴纳费用段
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null || taskPeriods.size() == 0) throw new BusinessException("费用段为空");
        /**
         * 现在需求 调整 时间段与之前没有交叉，则直接接上
         * 如果有交叉则交叉部分体现在差异表中
         */
        //删除任务单对应的时间段 因为如果之前办理时有数据，再次办理时候会有原来的数据存在
        //删除 SsEmpBasePeriod和字表 中数据
        deleteForTask(bo,TaskPeriodConst.ADJUSTMENTTYPE);
        //查询正常缴纳费用段
        List<SsEmpBasePeriod> ssEmpBasePeriodList = getNormalPeriod(bo);

        //判断时间费否有交叉 再进行修改添加  （通过startDate）
        adjustmentStartForTaskPeriods(taskPeriods, ssEmpBasePeriodList, bo);
    }

    /**
     * 任务单未完成 再次办理时 需要把之前SsEmpBasePeriod 表中任务单对应的时间段删除
     * 同时SsEmpBasePeriod 对应的detail 时间段也要删除
     *
     * @param bo
     */
    private void deleteForTask(SsEmpTaskBO bo,Integer type) {
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
        if (periods.size() > 0 && type==TaskPeriodConst.ADJUSTMENTTYPE) {
            EntityWrapper<SsEmpBasePeriod> ew = new EntityWrapper();
            ew.where("emp_task_id!={0}", bo.getEmpTaskId()).and("emp_archive_id={0}", bo.getEmpArchiveId()).
                orderBy("start_month", false).and("is_active=1");
            List<SsEmpBasePeriod> oldPeriod = getOldSsEmpBasePeriod(ew);
            SsEmpBasePeriod ssEmpBasePeriod = oldPeriod.get(0);
            ssEmpBasePeriodService.updateEndMonthById(ssEmpBasePeriod);
        }
        //删除调整差异表
        EntityWrapper<SsEmpBaseAdjust> ew2 = new EntityWrapper();
        ew1.where("emp_task_id={0}", bo.getEmpTaskId());
        List<SsEmpBaseAdjust> ssEmpBaseAdjustList =ssEmpBaseAdjustService.selectList(ew2);
        ssEmpBaseAdjustList.forEach(p->{
            //删除差异详细表
            SsEmpBaseAdjustDetail ssEmpBaseAdjustDetail  = new SsEmpBaseAdjustDetail();
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
        //在前段有多段并且和原数据有交叉时使用
        // boolean sizeIsOne = (boolean) map.get(TaskPeriodConst.SIZEISONE);
        //获得前端传递 startMonth 最小的那条数据
        SsEmpTaskPeriod ssEmpTaskPeriod = (SsEmpTaskPeriod) map.get(TaskPeriodConst.SSEMPTASKPERIOD);
        //String sort = (String) map.get(TaskPeriodConst.SORT);
        //前端传过来的最小startDate
        Integer minStartDateTask = Integer.valueOf(ssEmpTaskPeriod.getStartMonth());
        //原来数据库历史数据 最大的时间段
        SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(0);
        //判断是否大于当前月
        LocalDate nowDate = LocalDate.now();
        StringBuffer nowDateStr = getMonthStr(nowDate);
        Integer currentYearMonth = Integer.valueOf(nowDateStr.toString());
        //通过各自的开始时间进行比较 判断是否有交叉
        if (minStartDateTask > currentYearMonth) {
            //无交叉
            //添加 新添加的时间段
            //任务时间段转empBase
            List<SsEmpBasePeriod> newEmpBasePeriodList = taskPeriodTranserEmpBase(taskPeriods, bo,TaskPeriodConst.ADJUSTMENTTYPE);
            //截上 之前的endMonth
            String endMonth = getLastMonth(minStartDateTask);
            ssEmpBasePeriod.setEndMonth(endMonth);
            ssEmpBasePeriodService.saveAdjustmentPeriod(ssEmpBasePeriod, newEmpBasePeriodList);
            // 险种的数据段 （前道传递过来的）
            List<SsEmpTaskFront> empSocials = getEmpSocials(bo);
            if(empSocials.size()==0) throw new BusinessException("前道传递险种详细信息为空");
            //添加明细
            addEmpBaseDetail(newEmpBasePeriodList, empSocials, bo.getEmpArchiveId());
        } else {
            //有交叉 逻辑
            /**
             * 暂时只考虑到一段 如果后期需要考虑多段
             * 逻辑在这里添加
             */
            //判断正常缴纳时间段是否连续 返回不连续的两个下标   1代表 调整走调整
            judgeDateIsContinuity(ssEmpBasePeriodList, ssEmpTaskPeriod, bo, 1);
        }
    }

    /**
     * 获得当前月
     *
     * @return
     */
    StringBuffer getMonthStr(LocalDate now) {
        StringBuffer sb = new StringBuffer();
        if (now.getMonth().getValue() <= 9) {
            return sb.append(now.getYear()).append(0).append(now.getMonth().getValue());
        } else {
            return sb.append(now.getYear()).append(now.getMonth().getValue());
        }
    }

    /**
     * 调整逻辑
     *   1代表 调整走调整   2 代表补缴走调整(暂未实现)
     * @param ssEmpBasePeriodList
     * @param ssEmpTaskPeriod
     * @param type
     * @return
     */
    private void judgeDateIsContinuity(List<SsEmpBasePeriod> ssEmpBasePeriodList, SsEmpTaskPeriod ssEmpTaskPeriod, SsEmpTaskBO bo, int type) {
        List<int[]> haveNoContinuityList = new ArrayList<int[]>();
        //调整走过来的
        if (TaskPeriodConst.ADJUSTMENTTYPE == type) { //  SUPPLEMENTARYPAYTYPE
            for (int i = 0; i < ssEmpBasePeriodList.size(); i++) {
                if (i != ssEmpBasePeriodList.size() - 1) {
                    String startMonth = ssEmpBasePeriodList.get(i).getStartMonth();
                    //获得上月的String
                    String beforeStartMonth = getLastMonth(Integer.valueOf(startMonth));
                    //获得上段的endMonth
                    String endMonth = ssEmpBasePeriodList.get(i + 1).getEndMonth();
                    //如果这段的startMonth的上月 与上段的endMonth相同表示连续
                    if (!beforeStartMonth.equals(endMonth)) {
                        //将不连续的两个 下标 放到一个数组里
                        int[] index = new int[2];
                        index[0] = i;
                        index[1] = i + 1;
                        //用来判断有多少个不连续的区间
                        haveNoContinuityList.add(index);
                    }
                }
            }
        }
        handleAdjustment(haveNoContinuityList, ssEmpBasePeriodList, ssEmpTaskPeriod, bo, type);
    }

    /**
     * 有交叉调整
     *
     * @param haveNoContinuityList
     * @param ssEmpBasePeriodList
     * @param ssEmpTaskPeriod
     * @param type
     */
    private void handleAdjustment(List<int[]> haveNoContinuityList, List<SsEmpBasePeriod> ssEmpBasePeriodList, SsEmpTaskPeriod ssEmpTaskPeriod, SsEmpTaskBO bo, int type) {
        /**
         * 1,判断需要调整的时间段是否有endMonth
         * 2,根据有无endMonth 分两种情况做考虑
         * 3,在上两种情况下，再分原缴纳时间是否连续两种情况
         * 4,在连续情况下，直接找出交叉的时间段
         * 5,在不连续的情况下找出交叉的时间段和为缴纳的时间段
         */
        //分调整的时间段是否有endMonth

        //添加调整交叉的时间段
        List<SsEmpBasePeriod> overlappingPeriodList = new ArrayList<SsEmpBasePeriod>();
        //最后一段 当前月到永远
        List<SsEmpBasePeriod> addPeriodList = new ArrayList<SsEmpBasePeriod>();
        //调整 走补缴
        List<SsEmpBasePeriod> supplementPayList = new ArrayList<SsEmpBasePeriod>();
        //连续
        //
        List notPaidMonthList = new ArrayList();
        getNotPaidMonth(notPaidMonthList,ssEmpTaskPeriod,haveNoContinuityList,ssEmpBasePeriodList);
        StringBuffer sb = new StringBuffer();
        notPaidMonthList.forEach(p->{
            sb.append(p).append(",");
        });
        //表示原时间段没有连续
        if(notPaidMonthList.size()!=0)throw new BusinessException("["+sb.toString()+"]月份没有缴纳过社保，不能调整");

            SsEmpBasePeriod lastPeriod = ssEmpBasePeriodList.get(ssEmpBasePeriodList.size() - 1);
            //数据库中保存时间最早的时间段 起始时间
            int lastStartMonth = Integer.parseInt(lastPeriod.getStartMonth());
            //前端调整的起始时间
            int normalStartMonth = Integer.parseInt(ssEmpTaskPeriod.getStartMonth());
            //在正常缴纳范围之内 即前端的startMonth时间在正常缴纳范围的最小startMonth之前
            overlappingPeriodList.clear();
            addPeriodList.clear();
            //当前月时间+1
            LocalDate now = LocalDate.now();
            LocalDate nextMonth = now.plusMonths(1);
            StringBuffer nextMonthObj = getMonthStr(nextMonth);
            //当月时间
            StringBuffer currentMonthObj = getMonthStr(now);
            //表示前端 输入的endMonth为空,即调整 输入月份到将来
            if (StringUtils.isBlank(ssEmpTaskPeriod.getEndMonth())) {
                //找到需要调整的时间段
                for (int i = 0; i < ssEmpBasePeriodList.size(); i++) {
                    SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(i);
                    int startMonth = Integer.parseInt(ssEmpBasePeriod.getStartMonth());
                    if (i == 0) {
                        //这里预留  默认当前月份在 最大startMonth 之后
                        //将最大startMonth切成两段
                        SsEmpBasePeriod needAdustObj1 = cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                        SsEmpBasePeriod needAdustObj2 = cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                        needAdustObj1 = setValueForEmpBasePeriod(needAdustObj1, ssEmpTaskPeriod);
                        needAdustObj1.setEndMonth(null);
                        needAdustObj1.setStartMonth(nextMonthObj.toString());
                        //当前月到永远的那段 （即如果调整是到永远的话，则从下月设置到永远）
                        addPeriodList.add(needAdustObj1);
                        //如果缴纳初始小于 入职后缴纳最小日期 则为补缴
                        if (normalStartMonth < lastStartMonth) {
                            //调整中需要补缴的
                            SsEmpBasePeriod needAdust = getNeedBackInAdust(lastPeriod, ssEmpTaskPeriod);
                            supplementPayList.add(needAdust);
                            //不在第一段之中
                            needAdustObj2.setStartMonth(ssEmpBasePeriod.getStartMonth());
                            needAdustObj2.setEndMonth(currentMonthObj.toString());
                            overlappingPeriodList.add(needAdustObj2);
                        } else {
                            if (normalStartMonth >= startMonth && normalStartMonth <= Integer.parseInt(currentMonthObj.toString())) {
                                //如果调整段在第一段的起始月到当前月
                                needAdustObj2.setStartMonth(ssEmpBasePeriod.getStartMonth());
                                needAdustObj2.setEndMonth(currentMonthObj.toString());
                                overlappingPeriodList.add(needAdustObj2);
                                break;
                            } else {
                                //不在第一段之中
                                needAdustObj2.setStartMonth(ssEmpBasePeriod.getStartMonth());
                                needAdustObj2.setEndMonth(currentMonthObj.toString());
                                overlappingPeriodList.add(needAdustObj2);
                            }
                        }
                    } else {
                        //如果前端传递 其实时间在入职之前
                        if (normalStartMonth < lastStartMonth) {
                            overlappingPeriodList.add(ssEmpBasePeriod);
                        } else {
                            //克隆前端任务单对象
                            //获得调整的时间段
                            int endMonth = Integer.parseInt(ssEmpBasePeriod.getEndMonth());
                            if (normalStartMonth >= startMonth && normalStartMonth <= endMonth) {
                                //如果调整段在第i 段
                                SsEmpBasePeriod needAdustObj = cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                                needAdustObj.setStartMonth(ssEmpTaskPeriod.getStartMonth());
                                needAdustObj.setEndMonth(ssEmpBasePeriod.getEndMonth());
                                overlappingPeriodList.add(needAdustObj);
                                break;
                            } else {
                                //用于获得起始和终止日期
                                overlappingPeriodList.add(ssEmpBasePeriod);
                            }
                        }
                    }
                }
            } else {
                //此时表示前端 传递调整 有 终止 时间
                int taskEndMonth = Integer.parseInt(ssEmpTaskPeriod.getEndMonth());
                //此时endMonth 大于 当前月
                if (taskEndMonth > Integer.parseInt(currentMonthObj.toString()))
                    throw new BusinessException("截止时间不能大于当前月份。");
                //前端时间截止时间所在的下标
                int endOfIndex = 0;
                //前端时间段 起始时间所在数据区间的下标
                int startOfIndex = 0;
                for (int i = 0; i < ssEmpBasePeriodList.size(); i++) {
                    SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(i);
                    int startMonth = Integer.parseInt(ssEmpBasePeriod.getStartMonth());
                    if (i == 0) {
                        //获得start 所在的时间段的数组 下标  在获取endMonth所在的下标
                        if (taskEndMonth >= startMonth) endOfIndex = i;
                        if (normalStartMonth >= startMonth) startOfIndex = i;
                    } else {
                        int endMonth = Integer.parseInt(ssEmpBasePeriod.getEndMonth());
                        if (taskEndMonth >= startMonth && taskEndMonth <= endMonth) endOfIndex = i;
                        if (normalStartMonth >= startMonth && normalStartMonth <= endMonth) startOfIndex = i;
                    }
                }
                //如果缴纳初始小于 入职后缴纳最小日期 则最小下标应该在最后一个
                if (normalStartMonth < lastStartMonth) {
                    startOfIndex = ssEmpBasePeriodList.size() - 1;
                }
                //表示起始和截止都在第一段
                if (endOfIndex == startOfIndex && endOfIndex == 0) {
                    SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(0);
                    SsEmpBasePeriod needAdustObj = cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                    //任务单 始末月 在第一天startMonth 到当前月之前
                    needAdustObj.setStartMonth(ssEmpTaskPeriod.getStartMonth());
                    needAdustObj.setEndMonth(ssEmpTaskPeriod.getEndMonth());
                    overlappingPeriodList.add(needAdustObj);
                    //表示起始和截止都在一个区间 且不是在第一段之前
                } else if (endOfIndex == startOfIndex && endOfIndex != 0) {
                    SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(endOfIndex);
                    SsEmpBasePeriod needAdustObj = cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                    //如果缴纳初始小于 入职后缴纳最小日期 则调整时间就是原克隆对象的时间
                    if (!(normalStartMonth < lastStartMonth)) {
                        needAdustObj.setStartMonth(ssEmpTaskPeriod.getStartMonth());
                        needAdustObj.setEndMonth(ssEmpTaskPeriod.getEndMonth());
                    }
                    overlappingPeriodList.add(needAdustObj);
                } else if (endOfIndex != startOfIndex) {
                    //找到startMonth所在的区间
                    for (int i = endOfIndex; i <= startOfIndex; i++) {
                        //表示任务单endMonth在当前月之前
                        SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(i);
                        if (i == endOfIndex) {
                            SsEmpBasePeriod needAdustObj = cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                            //如果缴纳初始小于 入职后缴纳最小日期 则为补缴
                            if (normalStartMonth < lastStartMonth) {
                                SsEmpBasePeriod needAdust = getNeedBackInAdust(lastPeriod, ssEmpTaskPeriod);
                                supplementPayList.add(needAdust);
                            }
                            //任务单 始末月 在第一天startMonth 到当前月之内
                            needAdustObj.setStartMonth(ssEmpBasePeriod.getStartMonth());
                            needAdustObj.setEndMonth(currentMonthObj.toString());
                            overlappingPeriodList.add(needAdustObj);
                        } else if (i == startOfIndex) {
                            SsEmpBasePeriod needAdustObj = cloneObjet(ssEmpBasePeriod, SsEmpBasePeriod.class);
                            //任务单 始末月 在第一天startMonth 到当前月之内

                            //如果缴纳初始小于 入职后缴纳最小日期 则为补缴
                            //当达到最大后 起始时间应为数据库时间段的起始时间
                            if (normalStartMonth < lastStartMonth) {
                                needAdustObj.setStartMonth(lastPeriod.getStartMonth());
                            } else {
                                //否则为任务单的起始时间
                                needAdustObj.setStartMonth(ssEmpTaskPeriod.getStartMonth());
                            }
                            needAdustObj.setEndMonth(ssEmpBasePeriod.getEndMonth());
                            overlappingPeriodList.add(needAdustObj);
                        } else {
                            overlappingPeriodList.add(ssEmpBasePeriod);
                        }
                    }
                }
            }
            //将有交叉的调整段
            Map newData = new HashMap();
            newData.put(TaskPeriodConst.OVERLAPPING, overlappingPeriodList);
            //保存前端传过来的时间段 保存原始的起始 截止日期 调整基数
            newData.put(TaskPeriodConst.OLDBASE, ssEmpTaskPeriod);
            //用于添加，表示当前月到将来
            newData.put(TaskPeriodConst.ADJUSTADDLIST, addPeriodList);

            /**
             * 预留调整走补缴
             *
             */
//            { //
//                //找出 调整时间 走补缴的任务
//                if (supplementPayList.size() > 0) {
//                    //前端只有一条 连续情况下调整走补缴也只有一条
//                    SsEmpBasePeriod ssEmpBasePeriod = supplementPayList.get(0);
//                    //判断当前时间是否在 半年之内
//                }
//            }
            //获得需要处理的集合 进行处理
            handleAdjustmentResult(newData, bo);

    }

    /**
     * 获得没有缴纳社保的月份
     * @param notPaidMonthList
     * @param ssEmpTaskPeriod
     * @param haveNoContinuityList
     * @param ssEmpBasePeriodList
     */
    private void getNotPaidMonth(List notPaidMonthList,SsEmpTaskPeriod ssEmpTaskPeriod, List<int[]> haveNoContinuityList, List<SsEmpBasePeriod> ssEmpBasePeriodList) {
        Integer startMonth = Integer.valueOf(ssEmpTaskPeriod.getStartMonth());
        Integer endMonth = null;
        //ssEmpTaskPeriod.getEndMonth();
        //表示调整无
        if(StringUtils.isBlank(ssEmpTaskPeriod.getEndMonth())){
            //当前月时间
            LocalDate now = LocalDate.now();
            StringBuffer currentMonth = getMonthStr(now);
            endMonth= Integer.valueOf(currentMonth.toString());
        }else{
            endMonth = Integer.valueOf(ssEmpTaskPeriod.getEndMonth());
        }
            for (int i=startMonth;i<=endMonth;i=getNextMonthInt(i)){
                //在不连续情况下 是否有没有缴纳过的
                for (int j = 0; j <haveNoContinuityList.size() ; j++) {
                    //判断前端时间 是否有没有缴纳过社保的月份
                    noContinuityIsPaid(i, haveNoContinuityList.get(j), notPaidMonthList, ssEmpBasePeriodList);
                }
            }
    }

    /**
     * 将前端传递没有缴纳过的 年月找出来
     * @param i
     * @param p
     * @param notPaidMonthList
     * @param ssEmpBasePeriodList
     */
    private void noContinuityIsPaid(int i, int[] p, List notPaidMonthList, List<SsEmpBasePeriod> ssEmpBasePeriodList) {
        // 两个不连续区间 的 未缴纳月份起始
       int startMonth = Integer.valueOf(getNextMonth(Integer.valueOf(ssEmpBasePeriodList.get(p[1]).getEndMonth())));
        int endMonth = Integer.valueOf(getLastMonth(Integer.valueOf(ssEmpBasePeriodList.get(p[0]).getStartMonth())));
        //i 月份 未缴纳
       if(i>=startMonth && i<=endMonth){
           notPaidMonthList.add(i);
       }

    }


    /**
     * 调整中获得需要补缴的对象
     *
     * @param lastPeriod
     * @param ssEmpTaskPeriod
     * @return
     */
    private SsEmpBasePeriod getNeedBackInAdust(SsEmpBasePeriod lastPeriod, SsEmpTaskPeriod ssEmpTaskPeriod) {
        SsEmpBasePeriod needAdust = cloneObjet(lastPeriod, SsEmpBasePeriod.class);
        needAdust = setValueForEmpBasePeriod(needAdust, ssEmpTaskPeriod);
        needAdust.setStartMonth(ssEmpTaskPeriod.getStartMonth());
        needAdust.setEndMonth(getLastMonth(Integer.valueOf(lastPeriod.getStartMonth())));
        return needAdust;
    }

    private SsEmpBasePeriod setValueForEmpBasePeriod(SsEmpBasePeriod needAdustObj1, SsEmpTaskPeriod ssEmpTaskPeriod) {
        needAdustObj1.setRemitWay(ssEmpTaskPeriod.getRemitWay());
        needAdustObj1.setEmpTaskId(ssEmpTaskPeriod.getEmpTaskId());
        needAdustObj1.setActive(true);
        needAdustObj1.setBaseAmount(ssEmpTaskPeriod.getBaseAmount());
        needAdustObj1.setSsMonthStop(null);
        needAdustObj1.setSsMonth(ssEmpTaskPeriod.getStartMonth());
        LocalDateTime nowTime = LocalDateTime.now();
        needAdustObj1.setCreatedTime(nowTime);
        needAdustObj1.setModifiedTime(nowTime);
        by(needAdustObj1);
        return needAdustObj1;
    }

    private void handleAdjustmentResult(Map newData, SsEmpTaskBO bo) {
        //获得需要调整的时间段 与之前有交叉的
        List<SsEmpBasePeriod> overlappingPeriodList = (List<SsEmpBasePeriod>) newData.get(TaskPeriodConst.OVERLAPPING);
        //原任务单
        SsEmpTaskPeriod ssEmpTaskPeriod = (SsEmpTaskPeriod) newData.get(TaskPeriodConst.OLDBASE);
        //需要添加的时间段 表示前端 调整无 endMonth
        List<SsEmpBasePeriod> addPeriodList = (List<SsEmpBasePeriod>) newData.get(TaskPeriodConst.ADJUSTADDLIST);

        //如果没有调整本月之后的 就没有数据 否则 进行添加修改
        if (addPeriodList.size() > 0) {
            addPeriodAndUpdateEndMoth(addPeriodList);
        }
        //将有交叉的调整转 差异 对象  详细 转差异详细 ss_emp_base_adjust ss_emp_base_adjust_detail
        //并保存
        transforAndSave(overlappingPeriodList, ssEmpTaskPeriod, bo);
    }

    /**
     * 调整 添加新的时间段
     *
     * @param addPeriodList
     */
    private void addPeriodAndUpdateEndMoth(List<SsEmpBasePeriod> addPeriodList) {
        //前端只有一段
        SsEmpBasePeriod ssEmpBasePeriod = addPeriodList.get(0);
        //先修改 再添加
        SsEmpBasePeriod ssEmpBasePeriodForUpdate = new SsEmpBasePeriod();
        ssEmpBasePeriodForUpdate.setEmpBasePeriodId(ssEmpBasePeriod.getEmpBasePeriodId());
        StringBuffer sb = getMonthStr(LocalDate.now());
        ssEmpBasePeriodForUpdate.setEndMonth(sb.toString());
        ssEmpBasePeriodService.updateById(ssEmpBasePeriodForUpdate);
        ssEmpBasePeriod.setEmpBasePeriodId(null);
        ssEmpBasePeriodService.insert(ssEmpBasePeriod);
    }

    private void transforAndSave(List<SsEmpBasePeriod> overlappingPeriodList, SsEmpTaskPeriod ssEmpTaskPeriod, SsEmpTaskBO bo) {
        overlappingPeriodList.forEach(p -> {
            Map<String, Object> resultMap = getSsEmpBaseAdjust(p, ssEmpTaskPeriod, bo);
            SsEmpBaseAdjust ssEmpBaseAdjust = (SsEmpBaseAdjust) resultMap.get(TaskPeriodConst.SSEMPBASEADJUST);
            List<SsEmpBaseAdjustDetail> ssEmpBaseDetailList = (List<SsEmpBaseAdjustDetail>) resultMap.get(TaskPeriodConst.SSEMPBASEADJUSTDETAILLIST);
            if (null == ssEmpBaseAdjust || null == ssEmpBaseDetailList || ssEmpBaseDetailList.size() == 0)
                throw new BusinessException("转换差异详情异常");
            //保存
            saveAdjustAndDetail(ssEmpBaseAdjust, ssEmpBaseDetailList);
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
        Map<String, Object> map = new HashMap();
        SsEmpBaseAdjust ssEmpBaseAdjust = new SsEmpBaseAdjust();
        ssEmpBaseAdjust.setEmpTaskId(ssEmpTaskPeriod.getEmpTaskId());
        ssEmpBaseAdjust.setEmpArchiveId(p.getEmpArchiveId());
        ssEmpBaseAdjust.setProcessWay(bo.getHandleWay());
        ssEmpBaseAdjust.setNewBaseAmount(ssEmpTaskPeriod.getBaseAmount());
        ssEmpBaseAdjust.setSsMonth(bo.getHandleMonth());
        ssEmpBaseAdjust.setStartMonth(p.getStartMonth());
        ssEmpBaseAdjust.setEndMonth(p.getEndMonth());
        //通过时间段ID 查询详细表的信息
        EntityWrapper ew = new EntityWrapper<SsEmpBasePeriod>();
        ew.where("emp_base_period_id={0}", p.getEmpBasePeriodId()).and("is_active=1");
        List<SsEmpBaseDetail> ssEmpBaseDetailList = ssEmpBaseDetailService.selectList(ew);
        List<SsEmpBaseAdjustDetail> ssEmpBaseAdjustDetailList = new ArrayList();
        LocalDateTime now = LocalDateTime.now();
        //企业差额合计
        BigDecimal comDiffSumAmount = new BigDecimal(0);
        //雇员差额合计
        BigDecimal empDiffSumAmount = new BigDecimal(0);
        //总差额合计
        BigDecimal comempDiffSumAmount = new BigDecimal(0);
        for (int i = 0; i < ssEmpBaseDetailList.size(); i++) {
            SsEmpBaseAdjustDetail ssEmpBaseAdjustDetail = new SsEmpBaseAdjustDetail();
            SsEmpBaseDetail ssEmpBaseDetail = ssEmpBaseDetailList.get(i);
            ssEmpBaseAdjustDetail.setEmpArchiveId(p.getEmpArchiveId());
            ssEmpBaseAdjustDetail.setSsType(ssEmpBaseDetail.getSsType());
            ssEmpBaseAdjustDetail.setSsTypeName(ssEmpBaseDetail.getSsTypeName());
            ssEmpBaseAdjustDetail.setComPolicyItemId(ssEmpBaseDetail.getComPolicyItemId());
            ssEmpBaseAdjustDetail.setEmpCssPolicyItemId(ssEmpBaseDetail.getEmpPolicyItemId());
            ssEmpBaseAdjustDetail.setComBase(ssEmpTaskPeriod.getBaseAmount());
            ssEmpBaseAdjustDetail.setEmpBase(ssEmpTaskPeriod.getBaseAmount());
            ssEmpBaseAdjustDetail.setComRatio(ssEmpBaseDetail.getComRatio());
            ssEmpBaseAdjustDetail.setEmpRatio(ssEmpBaseDetail.getEmpRatio());
            //企业总额
            //BigDecimal base, BigDecimal ratio, BigDecimal fixedAmount, Integer calculateMethod, String roundType
            //通过进位方式进行 计算
            BigDecimal comAmount = CalculateSocialUtils.calculateAmount(ssEmpBaseAdjustDetail.getComBase(),ssEmpBaseAdjustDetail.getComRatio(),null,2,"DIT00018");
            ssEmpBaseAdjustDetail.setComAmount(comAmount);
            //雇员总额
            BigDecimal empAmount  = CalculateSocialUtils.calculateAmount(ssEmpBaseAdjustDetail.getEmpBase(),ssEmpBaseAdjustDetail.getEmpRatio(),null,2,"DIT00018");
            ssEmpBaseAdjustDetail.setEmpAmount(empAmount);
            //企业+雇员
            ssEmpBaseAdjustDetail.setComempAmount(ssEmpBaseAdjustDetail.getComAmount().add(ssEmpBaseAdjustDetail.getEmpAmount()));
            //调整后减去原来 企业部分差额
            ssEmpBaseAdjustDetail.setComDiffAmount(ssEmpBaseAdjustDetail.getComAmount().subtract(ssEmpBaseDetail.getComAmount()));
            //调整减原来    雇员部分差额
            ssEmpBaseAdjustDetail.setEmpDiffAmount(ssEmpBaseAdjustDetail.getEmpAmount().subtract(ssEmpBaseDetail.getEmpAmount()));
            //总差额
            ssEmpBaseAdjustDetail.setComempDiffAmount(ssEmpBaseAdjustDetail.getComempAmount().subtract(ssEmpBaseDetail.getComempAmount()));
            //企业附加金额
            ssEmpBaseAdjustDetail.setComAdditionAmount(ssEmpBaseDetail.getComAdditionAmount());
            //雇员附加金额
            ssEmpBaseAdjustDetail.setEmpAdditionAmount(ssEmpBaseDetail.getEmpAdditionAmount());
            ssEmpBaseAdjustDetail.setCreatedTime(now);
            ssEmpBaseAdjustDetail.setModifiedTime(now);
            by(ssEmpBaseAdjustDetail);
            comDiffSumAmount = comDiffSumAmount.add(ssEmpBaseAdjustDetail.getComDiffAmount());
            empDiffSumAmount = empDiffSumAmount.add(ssEmpBaseAdjustDetail.getEmpDiffAmount());
            comempDiffSumAmount = comempDiffSumAmount.add(ssEmpBaseAdjustDetail.getComempDiffAmount());
            ssEmpBaseAdjustDetailList.add(ssEmpBaseAdjustDetail);
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
        //boolean sizeIsOne = (boolean)map.get(TaskPeriodConst.SIZEISONE);
        //时间段List size为1 逻辑先放一边 现在只做 size 为1的
        //if(sizeIsOne){}
        SsEmpTaskPeriod ssEmpTaskPeriod = (SsEmpTaskPeriod) map.get(TaskPeriodConst.SSEMPTASKPERIOD);
        if(StringUtils.isBlank(ssEmpTaskPeriod.getEndMonth()))throw new BusinessException("截止时间不能为空");
        {//当前月 (判断补缴只能补半年之内的)
//            LocalDate now = LocalDate.now();
//            String currentMonth = getMonthStr(now).toString();
//            //半年内
//            LocalDate sixMonthAgoDate = now.minusMonths(6);
//            String sixMonthAgo = getMonthStr(sixMonthAgoDate).toString();
//            int startMonth = Integer.parseInt(ssEmpTaskPeriod.getStartMonth());
//            int endMonth = Integer.parseInt(ssEmpTaskPeriod.getEndMonth());
//            //判断是否在半年之内(补缴只能缴半年之内的)
//            if (!(startMonth >= Integer.parseInt(sixMonthAgo) && endMonth < Integer.parseInt(currentMonth)))
//                throw new BusinessException("补缴时间只能在半年之内。");
        }
        LocalDate now = LocalDate.now();
        String currentMonth = getMonthStr(now).toString();
        int endMonth = Integer.parseInt(ssEmpTaskPeriod.getEndMonth());
         if (Integer.parseInt(currentMonth)<endMonth)
              throw new BusinessException("补缴的时间段的截止月份需在当前月之前。");
         int startMonth = Integer.parseInt(ssEmpTaskPeriod.getStartMonth());
        //判断是否有交叉
        List<Integer> overLappingList = new ArrayList();
        for (int i = 0; i < ssEmpBasePeriodList.size(); i++) {
            judgeIsOverlapping(ssEmpBasePeriodList.get(i),startMonth ,endMonth,overLappingList);
        }
        if(overLappingList.size()==0){
            //表示没有交叉月份
            //补缴
            //throw new BusinessException("没有交叉");
            supplementaryPayment(taskPeriods,bo);

        }else{
            /**
             * 现在暂时传递到前端为有过缴纳不能补缴，之后 走调整逻辑在这里开始
             */
            StringBuffer sb = new StringBuffer();
            if(overLappingList.size()==1){
                sb.append("[").append(overLappingList.get(0)).append("]");
            }else{
                for (int i = 0; i < overLappingList.size(); i++) {
                    if(i==0){
                        sb.append("[").append(overLappingList.get(i)).append(",");
                    }else if(i==overLappingList.size()-1){
                        sb.append(overLappingList.get(i)).append("]");
                    }else{
                        sb.append(overLappingList.get(i)).append(",");
                    }
                }
            }
            sb.append("月份有过缴纳，不需补缴");
            throw new BusinessException(sb.toString());
        }
    }

    /**
     * 表示之前步骤都符合补缴条件
     *正式做补缴逻辑
     * @param taskPeriods 任务单时间段
     * @param bo task bo对象
     */
    private void supplementaryPayment(List<SsEmpTaskPeriod> taskPeriods,SsEmpTaskBO bo) {
        List<SsEmpBasePeriod> newEmpBasePeriodList = taskPeriodTranserEmpBase(taskPeriods, bo,TaskPeriodConst.SUPPLEMENTARYPAYTYPE);
        ssEmpBasePeriodService.saveBackPeriod(newEmpBasePeriodList);
        // 险种
        // 更新雇员社保汇缴基数明细
        // 险种的数据段 （前道传递过来的）
        List<SsEmpTaskFront> empSocials = getEmpSocials(bo);
        if(empSocials.size()==0) throw new BusinessException("前道传递险种详细信息为空");
        //添加明细 （养 医 失 工 生育 险种）
        addEmpBaseDetail(newEmpBasePeriodList, empSocials, bo.getEmpArchiveId());
    }

    /**
     * 补缴判断是否有交叉时间
     * @param ssEmpBasePeriod
     * @param startMonth
     *@param endMonth
     * @return
     */
    private void judgeIsOverlapping(SsEmpBasePeriod ssEmpBasePeriod, int startMonth, int endMonth,List<Integer> overLappingList) {
        Integer originalEndMonth = null;
        //源数据 起始时间
        int originalStartMonth = Integer.valueOf(ssEmpBasePeriod.getStartMonth());
        //表示第一段 截止时间为空
        if (StringUtils.isBlank(ssEmpBasePeriod.getEndMonth())) {
            //当前月
            LocalDate now = LocalDate.now();
            originalEndMonth = Integer.parseInt(getMonthStr(now).toString());
        }else{
            //源数据终止月份
            originalEndMonth = Integer.parseInt(ssEmpBasePeriod.getEndMonth());
        }
        //先判断是否有交叉
        boolean isOverLapping1 = startMonth>=originalStartMonth && startMonth<=originalEndMonth;
        boolean isOverLapping2 =endMonth>=originalStartMonth && endMonth<=originalEndMonth;
        boolean isOverLapping3 = startMonth<originalStartMonth && endMonth>originalEndMonth;

        if(isOverLapping1 || isOverLapping2 || isOverLapping3){
            //有交叉月份
            for (int i = originalStartMonth; i <=originalEndMonth ; i=getNextMonthInt(i)) {
                if(i>=startMonth && i<=endMonth){
                    overLappingList.add(i);
                }
            }
        }
    }
    //获得下个月的int型
    private int getNextMonthInt(int i) {
        return Integer.parseInt(getNextMonth(i));
    }

    /**
     * 前端任务时间段转enmBasePeriods
     *
     * @param taskPeriods
     * @param
     */
    private List<SsEmpBasePeriod> taskPeriodTranserEmpBase(List<SsEmpTaskPeriod> taskPeriods, SsEmpTaskBO bo,int type) {
        List<SsEmpBasePeriod> newEmpBasePeriodList = new ArrayList();
        //如果为降序则最大下标为最小startDate
        //int size = "DESC".equals(sort) ? taskPeriods.size() - 1 : 0;
        for (int i = 0; i < taskPeriods.size(); i++) {
            SsEmpTaskPeriod ssEmpTaskPeriod = taskPeriods.get(i);
            if(type==TaskPeriodConst.ADJUSTMENTTYPE){
                ssEmpTaskPeriod.setRemitWay(3);
            }else if(type==TaskPeriodConst.SUPPLEMENTARYPAYTYPE){
                ssEmpTaskPeriod.setRemitWay(2);
            }
            //将前端emptask 转empBase
            SsEmpBasePeriod basePeriod = Adapter.ssEmpBasePeriod(ssEmpTaskPeriod);
            basePeriod.setEmpArchiveId(bo.getEmpArchiveId());
            basePeriod.setEmpTaskId(bo.getEmpTaskId());
            //办理月份
            basePeriod.setSsMonth(bo.getHandleMonth());
            //设置创建人和修改人
            by(basePeriod);
            //更新原来时间段和添加新时间段
            newEmpBasePeriodList.add(basePeriod);
        }
        return newEmpBasePeriodList;
    }

    /**
     * 获得截断月份
     * 参数月的上月
     *
     * @param date
     */
    private String getLastMonth(Integer date) {
        StringBuffer sb = new StringBuffer();
        String dateStr = date.toString();
        int length = dateStr.length();
        String year = dateStr.substring(0, 4);
        String day = dateStr.substring(length - 2, length);
        if (1 == Integer.parseInt(day)) {
            return sb.append(Integer.valueOf(year) - 1).append(12).toString();
        } else {
            return sb.append(date - 1).toString();
        }
    }
    /**
     * 参数月的下月
     *
     * @param date
     */
    private String getNextMonth(Integer date) {
        StringBuffer sb = new StringBuffer();
        String dateStr = date.toString();
        int length = dateStr.length();
        String year = dateStr.substring(0, 4);
        String day = dateStr.substring(length - 2, length);
        if (12 == Integer.parseInt(day)) {
            return sb.append(Integer.valueOf(year) + 1).append("01").toString();
        }  else {
            return sb.append(date + 1).toString();
        }
    }

    private Map getSsEmpTaskPeriod(List<SsEmpTaskPeriod> taskPeriods) {
        SsEmpTaskPeriod ssEmpTaskPeriod = new SsEmpTaskPeriod();
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
    private void handleBackTask(SsEmpTaskBO bo) {
        //修改任务单详细
        baseMapper.updateMyselfColumnById(bo);
        //获得任务单信息
        SsEmpTask ssEmpTask = getSsEmpTask(bo);
        //获得前端输入的补缴费用段
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null || taskPeriods.size() == 0) throw new BusinessException("费用段为空");
        /**
         * 获取原来的数据检测是否有交叉
         */
        //删除任务单对应的时间段 因为如果之前办理时有数据，再次办理时候会有原来的数据存在
        deleteForTask(bo,TaskPeriodConst.SUPPLEMENTARYPAYTYPE);
        //查询正常缴纳费用段
        List<SsEmpBasePeriod> ssEmpBasePeriodList = getNormalPeriod(bo);
        //补缴的前提条件
        backStartForTaskPeriods(taskPeriods, ssEmpBasePeriodList, bo);
    }


    /**
     * 转出
     *
     * @param bo
     */
    private void handleTurnOutTask(SsEmpTaskBO bo) {
        List<SsEmpBasePeriod> ssEmpBasePeriodList = getNormalPeriod(bo);
        if(ssEmpBasePeriodList.size()>0){
            //有可能是再次办理 先将endMonth 和 ss_month_stop
            SsEmpBasePeriod ssEmpBasePeriod= ssEmpBasePeriodList.get(0);
            //还原之前修改
            Integer result = ssEmpBasePeriodService.updateReductionById(ssEmpBasePeriod);
            if(result==0)throw new BusinessException("数据库修改不成功.");
            ssEmpBasePeriod.setSsMonthStop(bo.getHandleMonth());
            ssEmpBasePeriod.setEndMonth(bo.getEndMonth());
            ssEmpBasePeriod.setModifiedBy("xsj");
            ssEmpBasePeriod.setModifiedTime(LocalDateTime.now());
            //修改 没有截止时间时间段的截止时间和停缴月份
            ssEmpBasePeriodService.updateEndMonAndHandleMon(ssEmpBasePeriod);

            //修改档案表的离职时间和缴纳截止时间
            SsEmpArchive ssEmpArchive = new SsEmpArchive();
            ssEmpArchive.setEmpArchiveId(bo.getEmpArchiveId());
            ssEmpArchive.setEndMonth(bo.getEndMonth());
            ssEmpArchive.setOutDate(bo.getOutDate());
            ssEmpArchive.setModifiedBy("xsj");
            ssEmpArchive.setModifiedTime(LocalDateTime.now());
            ssEmpArchiveService.updateById(ssEmpArchive);
        }else throw new BusinessException("数据库没有缴纳时间段");
    }

    /**
     * 封存
     *
     * @param bo
     */
    private void handleSealedTask(SsEmpTaskBO bo) {
        //封存也是转出
        handleTurnOutTask(bo);
    }

    /**
     * 退账
     * @param bo
     */
    private void handleRefundAccountTask(SsEmpTaskBO bo) {
        SsEmpRefund ssEmpRefund = provideSsEmpRefund(bo);
        LocalDateTime now = LocalDateTime.now();
        by(ssEmpRefund);
        ssEmpRefund.setCreatedTime(now);
        ssEmpRefund.setModifiedTime(now);

        ssEmpRefundService.insert(ssEmpRefund);

    }

    /**
     * 返回退账对象
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
     * 提取
     *
     * @param bo
     */
    private void handleExtractTask(SsEmpTaskBO bo) {
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
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 6, 7};
                        break;
                    // 特殊操作
                    case 2:
                        taskCategories = null;//现在特殊任务只有状态为9的 后面sql已经写死为9
                        break;
                    default:// 日常操作
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 6, 7};
                }
            } else {
                taskCategories = new Integer[]{taskCategory};
            }

            dto.setTaskCategories(taskCategories);
            //dto.setTaskCategory(null);
        }

    }


    /**
     * 新进或者转入
     *
     * @param bo
     */
    private void newOrChangeInto(SsEmpTaskBO bo) {
        {//首先添加社保档案表数据
            //先查询是否存在档案ID
            SsEmpTask ssEmpTask = getSsEmpTask(bo);
            //表示雇员档案还没有添加 则添加 否则更新
            if (null == ssEmpTask.getEmpArchiveId()) {
                SsEmpArchive ssEmpArchive = getArchive(bo);
                ssEmpArchiveService.insert(ssEmpArchive);
                bo.setEmpArchiveId(ssEmpArchive.getEmpArchiveId());
            } else {
                SsEmpArchive ssEmpArchive = getArchive(bo);
                ssEmpArchive.setEmpArchiveId(bo.getEmpArchiveId());
                ssEmpArchiveService.updateById(ssEmpArchive);
            }
        }
        //获得插入的 档案ID
        Long empArchiveId = bo.getEmpArchiveId();
        //更新任务单
        baseMapper.updateMyselfColumnById(bo);

        //获得前端输入的缴纳费用段
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null) {
            return;
        }
        //任务单Id
        Long empTaskId = bo.getEmpTaskId();
        //缴纳费用段
        List<SsEmpBasePeriod> basePeriods = new ArrayList<>(taskPeriods.size());
        // 删除 old 费用段和明细
        ssEmpBasePeriodService.deleteByEmpTaskId(empTaskId);
        // 更新任务单费用段
        String handleMonth = bo.getHandleMonth();

        //task表对应的费用段 转 档案表对应的费用段
        taskPeriods.forEach(p -> {
            //获得费用段 用于插入数据库
            SsEmpBasePeriod basePeriod = Adapter.ssEmpBasePeriod(p);
            basePeriod.setEmpArchiveId(empArchiveId);
            basePeriod.setEmpTaskId(empTaskId);
            //办理月份
            basePeriod.setSsMonth(handleMonth);
            //设置创建人和修改人
            by(basePeriod);
            basePeriods.add(basePeriod);
        });
        ssEmpBasePeriodService.saveForEmpTaskId(basePeriods, empTaskId);
        // 险种
        // 更新雇员社保汇缴基数明细
        // 险种的数据段 （前道传递过来的）
        List<SsEmpTaskFront> empSocials = getEmpSocials(bo);
        if(empSocials.size()==0) throw new BusinessException("前道传递险种详细信息为空");
        //添加明细 （养 医 失 工 生育 险种）
        addEmpBaseDetail(basePeriods, empSocials, empArchiveId);
    }

    /**
     * 添加时间段明细表
     *
     * @param basePeriods
     * @param empSocials
     * @param empArchiveId
     */
    private void addEmpBaseDetail(List<SsEmpBasePeriod> basePeriods, List<SsEmpTaskFront> empSocials, Long empArchiveId) {
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
                BigDecimal empAmount  = CalculateSocialUtils.calculateAmount(detail.getEmpBase(),detail.getEmpRatio(),null,2,"DIT00018");
                detail.setEmpAmount(empAmount);
                //公司金额 个人基数*个人比例
                BigDecimal comAmount  = CalculateSocialUtils.calculateAmount(detail.getComBase(),detail.getComRatio(),null,2,"DIT00018");
                detail.setComAmount(comAmount);
                //个人+公司
                detail.setComempAmount(detail.getEmpAmount().add(detail.getComAmount()));
                detail.setEmpBasePeriodId(empBasePeriodId);
                by(detail);
                detailsList.add(detail);
            });
            SsEmpBaseDetail detail = new SsEmpBaseDetail();
            detail.setEmpArchiveId(empArchiveId);
            detail.setEmpBasePeriodId(empBasePeriodId);
            ssEmpBaseDetailService.saveForSsEmpBaseDetail(detailsList, detail);
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
        bm.put("createdBy", "xsj");
        bm.put("modifiedBy", "xsj");
    }

    /**
     * 查询正常缴纳费用段 通过start 降序查询
     *
     * @param bo
     * @return
     */
    private List<SsEmpBasePeriod> getNormalPeriod(SsEmpTaskBO bo) {
        EntityWrapper<SsEmpBasePeriod> ew = new EntityWrapper<SsEmpBasePeriod>();
        ew.where("emp_archive_id={0}", bo.getEmpArchiveId()).and("is_active=1").orderBy("start_month", false);
        return ssEmpBasePeriodService.selectList(ew);
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
         * @return
         */
        public static SsEmpBasePeriod ssEmpBasePeriod(SsEmpTaskPeriod taskPeriod) {
            SsEmpBasePeriod basePeriod = new SsEmpBasePeriod();
            basePeriod.setBaseAmount(taskPeriod.getBaseAmount());
            basePeriod.setEmpTaskId(taskPeriod.getEmpTaskId());
            basePeriod.setEndMonth(taskPeriod.getEndMonth());
            basePeriod.setStartMonth(taskPeriod.getStartMonth());
            basePeriod.setRemitWay(taskPeriod.getRemitWay());
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
        int NOPROGRESS = 1;// 不需处理
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
        int EXTRACT = 8;//提取
    }

    interface TaskPeriodConst {

        String SIZEISONE = "sizeIsOne";//list 集合size是否是一个
        String SORT = "sort"; //集合排序
        String SSEMPTASKPERIOD = "ssEmpTaskPeriod";//集合对象
        Integer ADJUSTMENTTYPE = 1; //代表调整
        Integer SUPPLEMENTARYPAYTYPE = 2; //代表补缴
        String OVERLAPPING = "overlapping";//代表调整有交叉List 的标志
        String OLDBASE = "oldBase";//代表前端传过来的时间段
        String ADJUSTADDLIST = "adjustAddList";//代表调整 从当前月到将来的 集合
        String SSEMPBASEADJUST = "ssEmpBaseAdjust";//调整差异表对应的对象
        String SSEMPBASEADJUSTDETAILLIST = "ssEmpBaseAdjustDetailList";//调整差异明细表对应的集合对象
    }

    SsEmpArchive getArchive(SsEmpTaskBO bo) {
        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setComAccountId(bo.getComAccountId());
        ssEmpArchive.setCompanyId(bo.getCompanyId());
        ssEmpArchive.setEmployeeId(bo.getEmployeeId());
        ssEmpArchive.setSsSerial(bo.getEmpSsSerial());
        ssEmpArchive.setSalary(bo.getSalary());
        ssEmpArchive.setEmpClassify(bo.getEmpClassify());
        ssEmpArchive.setInDate(bo.getInDate());
        ssEmpArchive.setArchiveStatus(1);
        ssEmpArchive.setArchiveTaskStatus(1);
        ssEmpArchive.setStartMonth(bo.getStartMonth());
        ssEmpArchive.setEndMonth(bo.getEndMonth());
        ssEmpArchive.setSsMonth(bo.getHandleMonth());
        ssEmpArchive.setActive(true);
        ssEmpArchive.setCreatedTime(LocalDateTime.now());
        ssEmpArchive.setCreatedBy("xsj");
        ssEmpArchive.setModifiedTime(LocalDateTime.now());
        ssEmpArchive.setModifiedBy("xsj");
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
     * 克隆对象
     *
     * @param object
     * @param z
     * @return
     */
    private <T> T cloneObjet(T object, Class z) {
        return (T) JSONObject.parseObject(JSONObject.toJSON(object).toString(), z);
    }

    /**
     * 任务单处理调用工作流
     * @param bo
     * @return
     */
    private Result completeTask(SsEmpTaskBO bo){
        TaskSheetRequestDTO taskSheetRequestDTO = new TaskSheetRequestDTO();
        taskSheetRequestDTO.setTaskId(bo.getTaskId());
        taskSheetRequestDTO.setAssignee("xsj");
        try {
            Result result =commonApiUtils.completeTask(taskSheetRequestDTO);

            return result;
        } catch (Exception e) {
            throw new BusinessException("调用工作流异常");
        }
    }

    /**
     * 处理工作流结果
     * @param result
     */
   private void handleWorkflowResult(Result result){
        //int code，接口调用成功=0，错误码=其他值
        //T object，具体返回值  TRUE,FALSE
        //String error，字符串错误码，可选
        //String message，错误消息
       Assert.isNull(result,"任务单操作调用工作流结果为空");

       if(0!=result.getCode())throw new BusinessException(result.getMessage());
   }
}

