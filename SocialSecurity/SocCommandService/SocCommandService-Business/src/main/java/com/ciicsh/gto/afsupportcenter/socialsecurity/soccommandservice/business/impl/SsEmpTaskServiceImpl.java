package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskPeriodMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Override
    public PageRows<SsEmpTaskBO> employeeOperatorQuery(PageInfo pageInfo) {
        SsEmpTaskBO dto = pageInfo.toJavaObject(SsEmpTaskBO.class);
        handleTaskCategory(dto);
        if(2==dto.getOperatorType()){
            return PageKit.doSelectPage(pageInfo, () -> baseMapper.employeeSpecialOperatorQuery(dto));
        }else{
            return PageKit.doSelectPage(pageInfo, () -> baseMapper.employeeDailyOperatorQuery(dto));
        }


    }

    @Override
    public List<SsEmpTask> queryTaskByEmpArchiveId(String empArchiveId) {
        return baseMapper.queryTaskByEmpArchiveId(empArchiveId);
    }

    /**
     * 雇员日常操作 做事物控制
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
            // 处理中，正式把数据写入到 ss_emp_base_period and ss_emp_base_detail(雇员社)
            if (TaskStatusConst.PROCESSING == taskStatus) {
                progressing(bo);
            }else{
                //更新雇员信息
                baseMapper.updateMyselfColumnById(bo);
            }

          return true;
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
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 6,7};
                        break;
                    // 特殊操作
                    case 2:
                        taskCategories = null;//现在特殊任务只有状态为9的 后面sql已经写死为9
                        break;
                    default:// 日常操作
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 6,7};
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
        //首先添加社保档案表数据

        //先查询是否存在档案ID
        SsEmpTask ssEmpTask =baseMapper.selectById(bo);

        //表示雇员档案还没有添加
        if(null==ssEmpTask.getEmpArchiveId()){
            SsEmpArchive ssEmpArchive =getArchive(bo);
            ssEmpArchiveService.insert(ssEmpArchive);
            bo.setEmpArchiveId(ssEmpArchive.getEmpArchiveId());
        }else{
            SsEmpArchive ssEmpArchive =getArchive(bo);
            ssEmpArchive.setEmpArchiveId(bo.getEmpArchiveId());
            ssEmpArchiveService.updateById(ssEmpArchive);
        }
        //获得插入的 档案ID
        Long  empArchiveId = bo.getEmpArchiveId();
        //更新任务单
        baseMapper.updateMyselfColumnById(bo);
        //获得前端的缴纳费用段
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null) {
            return;
        }

        //任务单Id
        Long empTaskId = bo.getEmpTaskId();
        //缴纳费用段
        List<SsEmpBasePeriod> basePeriods = new ArrayList<>(taskPeriods.size());
        // 险种的JSON 模拟数据
        List<JSONObject> empSocials = getEmpSocials(bo);
        // 删除 old 费用段和明细
        ssEmpBasePeriodService.deleteByEmpTaskId(empTaskId);
        // 更新任务单费用段
            int taskCategory = bo.getTaskCategory();
            String handleMonth = bo.getHandleMonth();

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
            basePeriods.forEach(p -> {
                // 组合险种和费用段
                List<SsEmpBaseDetail> details = new ArrayList<>();
                Long empBasePeriodId = p.getEmpBasePeriodId();
                empSocials.forEach(empSocial -> {
                    SsEmpBaseDetail detail = Adapter.ssEmpBaseDetail(empSocial);
                    detail.setEmpArchiveId(empArchiveId);
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
    List<JSONObject> getEmpSocials(SsEmpTaskBO bo) {
        List<JSONObject> list = new ArrayList<>();
        {
            JSONObject obj = new JSONObject();
            obj.put("itemDicId", "1");
            obj.put("policyName", "1");
            obj.put("empBase", "1");
            obj.put("comBase", "1");
            obj.put("personalRatio", "1");
            obj.put("companyRatio", "1");
            list.add(obj);
        }
        {
            JSONObject obj = new JSONObject();
            obj.put("itemDicId", "2");
            obj.put("policyName", "2");
            obj.put("empBase", "2");
            obj.put("comBase", "2");
            obj.put("personalRatio", "2");
            obj.put("companyRatio", "2");
            list.add(obj);
        }
        {
            JSONObject obj = new JSONObject();
            obj.put("itemDicId", "3");
            obj.put("policyName", "3");
            obj.put("empBase", "3");
            obj.put("comBase", "3");
            obj.put("personalRatio", "3");
            obj.put("companyRatio", "3");
            list.add(obj);
        }

//        bo.setTaskFormContent("");
        return list;
    }

    void by(Object entity){
        BeanMap bm = new BeanMap(entity);
        bm.put("createdBy","xsj");
        bm.put("modifiedBy","xsj");
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
        public static SsEmpBaseDetail ssEmpBaseDetail(JSONObject empSocial) {
            SsEmpBaseDetail detail = new SsEmpBaseDetail();
            detail.setSsType(empSocial.getString("itemDicId"));
            detail.setSsTypeName(empSocial.getString("policyName"));
            detail.setEmpBase(empSocial.getBigDecimal("empBase"));
            detail.setComBase(empSocial.getBigDecimal("comBase"));
            detail.setEmpRatio(empSocial.getBigDecimal("personalRatio"));
            detail.setComRatio(empSocial.getBigDecimal("companyRatio"));
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

    SsEmpArchive getArchive(SsEmpTaskBO bo){
        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setComAccountId(bo.getComAccountId());
        ssEmpArchive.setCompanyId(bo.getCompanyId());
        ssEmpArchive.setEmployeeId(bo.getEmployeeId());
        ssEmpArchive.setSsSerial(bo.getEmpSsSerial());
        ssEmpArchive.setSalary(bo.getSalary());
        ssEmpArchive.setEmpClassify(bo.getEmpClassify());
        ssEmpArchive.setInDate(bo.getInDate());
        ssEmpArchive.setArchiveStatus(0);
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

}

