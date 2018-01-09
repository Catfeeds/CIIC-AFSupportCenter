package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskPeriodMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.AttributeList;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
public class SsEmpTaskServiceImpl extends ServiceImpl<SsEmpTaskMapper, SsEmpTask> implements ISsEmpTaskService {

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
    @Transactional
    @Override
    public boolean saveHandleData(SsEmpTaskBO bo) {
        // 更新任务单费用段
        List<SsEmpTaskPeriod> periods = bo.getEmpTaskPeriods();
        if (periods != null) {
            ssEmpTaskPeriodService.saveForEmpTaskId(periods, bo.getEmpTaskId());
            periods = ssEmpTaskPeriodService.queryByEmpTaskId(bo.getEmpTaskId());
            bo.setEmpTaskPeriods(periods);
        }
        // 更新雇员任务信息
        // 备注时间
        LocalDate now = LocalDate.now();
        bo.setHandleRemarkDate(now);
        bo.setRejectionRemarkDate(now);
        bo.setModifiedTime(LocalDateTime.now());

        int taskStatus = bo.getTaskStatus();
        int taskCategory = bo.getTaskCategory();
        // 处理中，正式把数据写入到 ss_emp_base_period and ss_emp_base_detail(雇员社)
        if (TaskStatusConst.PROCESSING == taskStatus) {

            if (TaskTypeConst.NEW == taskCategory || TaskTypeConst.INTO == taskCategory) {
                //新进和转入
                progressing(bo);
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
            //更新雇员信息
            baseMapper.updateMyselfColumnById(bo);
        }

        return true;
    }

    /**
     * 调整
     *
     * @param bo
     */
    private void handleAdjustmentTask(SsEmpTaskBO bo) {

        SsEmpTask ssEmpTask = getSsEmpTask(bo);
        //获得插入的 档案ID
        Long empArchiveId = bo.getEmpArchiveId();

        baseMapper.updateMyselfColumnById(bo);
        //获得前端输入的缴纳费用段
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null || taskPeriods.size() == 0) return;

        //任务单Id
        Long empTaskId = bo.getEmpTaskId();
        //缴纳费用段
        List<SsEmpBasePeriod> basePeriods = new ArrayList<>(taskPeriods.size());
        /**
         * 现在需求 调整 时间段与之前没有交叉，则直接接上
         * 如果有交叉则交叉部分体现在差异表中
         */
        //先查询旧费用段
        EntityWrapper<SsEmpBasePeriod> ew = new EntityWrapper<SsEmpBasePeriod>();
        ew.where("empArchiveId={0}", bo.getEmpArchiveId()).and("is_active=1").orderBy("start_month", false);
        List<SsEmpBasePeriod> ssEmpBasePeriodList = ssEmpBasePeriodService.selectList(ew);
        //判断时间费否有交叉 再进行修改添加  （通过startDate）
        judgeStartForTaskPeriods(taskPeriods, ssEmpBasePeriodList, bo);

    }

    /**
     * @param taskPeriods
     * @param ssEmpBasePeriodList
     * @param bo
     */
    private void judgeStartForTaskPeriods(List<SsEmpTaskPeriod> taskPeriods, List<SsEmpBasePeriod> ssEmpBasePeriodList, SsEmpTaskBO bo) {
        //获得组装map
        Map map = getSsEmpTaskPeriod(taskPeriods);
        //在前段有多段并且和原数据有交叉时使用
        boolean sizeIsOne = (boolean) map.get("sizeIsOne");
        SsEmpTaskPeriod ssEmpTaskPeriod = (SsEmpTaskPeriod) map.get("ssEmpTaskPeriod");
        //String sort = (String) map.get("sort");
        //前端传过来的最小startDate
        Integer minStartDateTask = Integer.valueOf(ssEmpTaskPeriod.getStartMonth());

        //原来数据库历史数据 最大的时间段
        SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodList.get(0);
        Integer maxStartDateEmpBase = Integer.valueOf(ssEmpBasePeriod.getStartMonth());
        //更新有交叉的时间段
        List oldEmpBasePeriodList = new ArrayList();
        //添加新添加的时间段
        List<SsEmpBasePeriod> newEmpBasePeriodList = new ArrayList();
        //通过各自的开始时间进行比较 判断是否有交叉
        if (minStartDateTask > maxStartDateEmpBase) {
            //前端传过来只有一段
            //任务时间段转empBase
            newEmpBasePeriodList = taskPeriodTranserEmpBase(taskPeriods, bo);
            ssEmpBasePeriodService.saveAdjustmentPeriod(ssEmpBasePeriod, newEmpBasePeriodList);
            // 险种的数据段 （前道传递过来的）
            List<SsEmpTaskFront> empSocials = getEmpSocials(bo);
            //添加明细
            addEmpBaseDetail(newEmpBasePeriodList,empSocials,bo.getEmpArchiveId());

        } else //之后有交叉 走else 逻辑
            throw new BusinessException("时间段设置有问题.");
    }

    /**
     * 前端任务时间段转enmBasePeriods
     *
     * @param taskPeriods
     * @param
     */
    private List<SsEmpBasePeriod> taskPeriodTranserEmpBase(List<SsEmpTaskPeriod> taskPeriods, SsEmpTaskBO bo) {
        List<SsEmpBasePeriod> newEmpBasePeriodList = new ArrayList();
        //如果为降序则最大下标为最小startDate
        //int size = "DESC".equals(sort) ? taskPeriods.size() - 1 : 0;

        for (int i = 0; i < taskPeriods.size(); i++) {
            //将前端emptask 转empBase
            SsEmpBasePeriod basePeriod = Adapter.ssEmpBasePeriod(taskPeriods.get(i));
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
     *
     * @param date
     */
    private String getChangeEndMonth(Integer date) {
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
        //前端时候是一条数据
        map.put("sizeIsOne", sizeIsOne);
        map.put("ssEmpTaskPeriod", ssEmpTaskPeriod);
        //升序还是降序
        map.put("sort", SORT);
        return map;
    }


    /**
     * 补缴
     *
     * @param bo
     */
    private void handleBackTask(SsEmpTaskBO bo) {

    }

    /**
     * 转出
     *
     * @param bo
     */
    private void handleTurnOutTask(SsEmpTaskBO bo) {

    }

    /**
     * 封存
     *
     * @param bo
     */
    private void handleSealedTask(SsEmpTaskBO bo) {
    }

    /**
     * 退账
     *
     * @param bo
     */
    private void handleRefundAccountTask(SsEmpTaskBO bo) {
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

        // 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
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
            dto.setTaskCategory(null);
        }

    }


    /**
     * 处理中
     *
     * @param bo
     */
    private void progressing(SsEmpTaskBO bo) {
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
        //添加明细 （养 医 失 工 生育）
        addEmpBaseDetail(basePeriods,empSocials,empArchiveId);
    }

    /**
     * 添加时间段明细表
     * @param basePeriods
     * @param empSocials
     * @param empArchiveId
     */
    private void addEmpBaseDetail(List<SsEmpBasePeriod> basePeriods, List<SsEmpTaskFront> empSocials, Long empArchiveId) {
        basePeriods.forEach(p -> {
            // 组合险种和费用段
            List<SsEmpBaseDetail> details = new ArrayList<>();
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
                detail.setEmpAmount(detail.getEmpBase().multiply(detail.getEmpRatio()));
                //公司金额 个人基数*个人比例
                detail.setComAmount(detail.getComBase().multiply(detail.getComRatio()));
                detail.setEmpBasePeriodId(empBasePeriodId);
                by(detail);
                details.add(detail);
            });
            SsEmpBaseDetail detail = new SsEmpBaseDetail();
            detail.setEmpArchiveId(empArchiveId);
            detail.setEmpBasePeriodId(empBasePeriodId);
            ssEmpBaseDetailService.saveForSsEmpBaseDetail(details, detail);
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
}

