package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpAgreementDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskFrontBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpTaskFrontMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTaskFront;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.enumeration.ProcessCategory;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia 服务实现类
 * </p>
 */
@Service
public class SsEmpTaskFrontServiceImpl extends ServiceImpl<SsEmpTaskFrontMapper, SsEmpTaskFront> implements
    SsEmpTaskFrontService {
    @Autowired
    LogApiUtil logApiUtil;
    @Autowired
    private SsEmpTaskMapper ssEmpTaskMapper;
    @Autowired
    private SsEmpArchiveService ssEmpArchiveService;
//    @Autowired
//    private LogApiUtil logApiUtil;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    private static Integer[] SUSPEND_TASK_CATEGORIES = new Integer[] {
        Integer.valueOf(SocialSecurityConst.TASK_TYPE_1),
        Integer.valueOf(SocialSecurityConst.TASK_TYPE_2),
        Integer.valueOf(SocialSecurityConst.TASK_TYPE_12),
        Integer.valueOf(SocialSecurityConst.TASK_TYPE_13),
        SocialSecurityConst.TASK_CATEGORY_NO_HANDLE
    };

    /**
     * <p>Description: 保存数据到雇员任务单表</p>
     *
     * @param taskMsgDTO   消息队列接受的对象
     * @param taskCategory 任务类型
     * @param isChange     是否更正任务单1 是 0 否
     * @param dto          取得的雇员信息
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    @Override
    public boolean saveEmpTaskTc(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, Integer processCategory, Integer isChange,
                                 String oldAgreementId, AfEmployeeInfoDTO dto,
                                 AfCompanyDetailResponseDTO afCompanyDetailResponseDTO, Map<String, Object> cityCodeMap) {
        boolean result = false;
        try {
            //插入数据到雇员任务单表
            saveSsEmpTask(taskMsgDTO, taskCategory, processCategory, isChange, oldAgreementId, dto, afCompanyDetailResponseDTO, cityCodeMap);
            result = true;
        } catch (Exception e) {
            result = false;
            throw new RuntimeException("保存到雇员任务单表处理异常");
        }
        return result;
    }

    /**
     * 更新旧的雇员任务单
     *
     * @param taskMsgDTO 消息队列接受的对象
     * @param dto        取得的雇员信息
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    @Override
    public boolean updateEmpTaskTc(TaskCreateMsgDTO taskMsgDTO,
                                   AfEmployeeInfoDTO dto) {
        //更新旧的雇员任务单
        return updateEmpTaskTb(taskMsgDTO, dto);
    }

    /**
     * 保存数据到雇员任务单表
     *
     * @param taskMsgDTO 雇员任务单对象
     * @param socialType
     * @param isChange
     * @param dto        雇员任务单表单对象
     * @return
     * @throws Exception
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    @Override
    public boolean saveSsEmpTask(TaskCreateMsgDTO taskMsgDTO, Integer socialType, Integer processCategory, Integer isChange,
                                 String oldAgreementId, AfEmployeeInfoDTO dto,
                                 AfCompanyDetailResponseDTO afCompanyDetailResponseDTO, Map<String, Object> cityCodeMap) throws Exception {
        //基本信息
        AfEmployeeCompanyDTO afEmployeeCompanyDTO = dto.getEmployeeCompany();
        AfEmpAgreementDTO afEmpAgreementDTO = dto.getNowAgreement();


        //险种明细
        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();

        SsEmpTask ssEmpTask = new SsEmpTask();
        ssEmpTask.setTaskId(taskMsgDTO.getTaskId());
        ssEmpTask.setCompanyId(afEmployeeCompanyDTO.getCompanyId());
        ssEmpTask.setEmployeeId(afEmployeeCompanyDTO.getEmployeeId());
        ssEmpTask.setEmpCompanyId(afEmployeeCompanyDTO.getEmpCompanyId());

        if (cityCodeMap.get("oldAgreementId") != null) {
            ssEmpTask.setBusinessInterfaceId(cityCodeMap.get("oldAgreementId").toString());
        } else {
            ssEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        }
        // 调整通道或更正通道过来的任务单，都需要加上oldAgreementId，回调前道接口时需使用
        if (oldAgreementId != null) {
            ssEmpTask.setOldAgreementId(oldAgreementId);
        }
        if (cityCodeMap != null) {
            if (cityCodeMap.get("oldSocialCityCode") != null) {
                ssEmpTask.setOldCityCode(cityCodeMap.get("oldSocialCityCode").toString());
            }

            if (cityCodeMap.get("newSocialCityCode") != null) {
                ssEmpTask.setNewCityCode(cityCodeMap.get("newSocialCityCode").toString());
            }

            if (cityCodeMap.get("socialStartAndStop") != null) {
                ssEmpTask.setSocialStartAndStop(Boolean.valueOf(cityCodeMap.get("socialStartAndStop").toString()));
            }
        }

        ssEmpTask.setSubmitterId(afEmpAgreementDTO.getCreatedBy());
        ssEmpTask.setSalary(afEmployeeCompanyDTO.getSalary());
        ssEmpTask.setSubmitterRemark(afEmployeeCompanyDTO.getRemark());
        ssEmpTask.setSubmitTime(LocalDateTime.now());
        ssEmpTask.setTaskDefKey(taskMsgDTO.getTaskType());
        ssEmpTask.setProDefKey(taskMsgDTO.getProcessDefinitionKey());
        //将Date类型转换为LocalDate类型
        if (afEmployeeCompanyDTO.getInDate() != null) {
            ssEmpTask.setInDate(LocalDateTime.ofInstant(afEmployeeCompanyDTO.getInDate().toInstant(), ZoneId.systemDefault()).toLocalDate());
        }
        if (afEmployeeCompanyDTO.getOutDate() != null) {
            ssEmpTask.setOutDate(LocalDateTime.ofInstant(afEmployeeCompanyDTO.getOutDate().toInstant(), ZoneId.systemDefault()).toLocalDate());
        }
        //如果inDate 为空 则判断任务单状态 不是新进和转入的 任务单 则查询档案表中的入职日期
        if (null == ssEmpTask.getInDate() && (Integer.parseInt(SocialSecurityConst.TASK_TYPE_1) != socialType && Integer.parseInt(SocialSecurityConst.TASK_TYPE_2) != socialType)) {
            EntityWrapper<SsEmpArchive> ew = new EntityWrapper<SsEmpArchive>();
            ew.where("employee_id={0}", ssEmpTask.getEmployeeId())
                .where("archive_status!=3")
                .where("is_active=1");
            SsEmpArchive ssEmpArchive = ssEmpArchiveService.selectOne(ew);
            //设置入职日期
            if (null != ssEmpArchive && null != ssEmpArchive.getInDate()) {
                ssEmpTask.setInDate(ssEmpArchive.getInDate());
            }
        }
        //任务单类型不是 新进 和 转入 就要补充雇员社保档案主表ID
//        if (socialType != 1 && socialType != 2) {
//            if (Optional.ofNullable(afEmployeeCompanyDTO.getCompanyId()).isPresent() && Optional.ofNullable(afEmployeeCompanyDTO.getEmployeeId()).isPresent()) {
//                String ssEmpArchiveId;
//                ssEmpArchiveId = ssEmpTaskMapper.fetchEmpArchiveId(afEmployeeCompanyDTO.getCompanyId(), afEmployeeCompanyDTO.getEmployeeId());
//                ssEmpTask.setEmpArchiveId(Long.valueOf(Optional.ofNullable(ssEmpArchiveId).orElse("0")));
//            }
//        }
        ssEmpTask.setProcessCategory(processCategory);
        ssEmpTask.setTaskCategory(socialType);
        ssEmpTask.setIsChange(isChange);
        ssEmpTask.setTaskFormContent(JSON.toJSONString(dto));

        ssEmpTask.setProcessId(taskMsgDTO.getProcessId());
        ssEmpTask.setTaskStatus(Integer.parseInt(SocialSecurityConst.PROCESS_STATUS_1));
        ssEmpTask.setActive(true);
        //福利办理方
        ssEmpTask.setWelfareUnit(afEmployeeCompanyDTO.getSocialUnit());
        ssEmpTask.setModifiedBy(afEmpAgreementDTO.getCreatedBy());
        ssEmpTask.setModifiedDisplayName(afEmpAgreementDTO.getCreatedDisplayName());
        ssEmpTask.setModifiedTime(LocalDateTime.now());
        ssEmpTask.setCreatedBy(afEmpAgreementDTO.getCreatedBy());
        ssEmpTask.setCreatedDisplayName(afEmpAgreementDTO.getCreatedDisplayName());
        ssEmpTask.setCreatedTime(LocalDateTime.now());
        ssEmpTask.setLeaderShipId(afEmployeeCompanyDTO.getLeadershipId());
        ssEmpTask.setLeaderShipName(afEmployeeCompanyDTO.getLeadershipName());

        if (afCompanyDetailResponseDTO != null) {
            ssEmpTask.setServiceCenterId(afCompanyDetailResponseDTO.getServiceCenterId());
            ssEmpTask.setServiceCenter(afCompanyDetailResponseDTO.getServiceCenter());
        }

        //社保缴纳段开始月份YYYYMM
        for (AfEmpSocialDTO socialDto : socialList) {
            if (socialDto.getPolicyType() != null && socialDto.getPolicyType() == SocialSecurityConst.POLICY_TYPE_SOCIAL_SECURITY) {
                ssEmpTask.setEmpBase(socialDto.getPersonalBase());
                ssEmpTask.setPolicyDetailId(socialDto.getPolicyId());
                if (socialDto.getStartDate() != null) {
                    ssEmpTask.setStartMonth(StringUtil.dateToString(socialDto.getStartDate(), "yyyyMM"));
                }
                if (socialDto.getEndDate() != null) {
                    ssEmpTask.setEndMonth(StringUtil.dateToString(socialDto.getEndDate(), "yyyyMM"));
                }
                break;
            }
        }

        // 调整或更正的转出或封存时
        if (oldAgreementId != null && (
            ProcessCategory.AF_EMP_AGREEMENT_ADJUST.getCategory().equals(ssEmpTask.getProcessCategory())
                || ProcessCategory.AF_EMP_AGREEMENT_UPDATE.getCategory().equals(ssEmpTask.getProcessCategory())
        ) && (
            SocialSecurityConst.TASK_TYPE_5.equals(String.valueOf(ssEmpTask.getTaskCategory()))
                || SocialSecurityConst.TASK_TYPE_6.equals(String.valueOf(ssEmpTask.getTaskCategory()))
        )) {
            // 非0转0不是常规意义的停办，是一种调整任务，所以没有截止年月，此处需特别处理
            if (StringUtils.isEmpty(ssEmpTask.getEndMonth()) && StringUtils.isNotEmpty(ssEmpTask.getStartMonth())) {
                YearMonth startMonthDate = YearMonth.parse(ssEmpTask.getStartMonth(), formatter);
                ssEmpTask.setEndMonth(startMonthDate.minusMonths(1).format(formatter));
                ssEmpTask.setStartMonth(null);
            }
        }

        //boolean insertRes = ssEmpTaskMapper.insertEmpTask(ssEmpTask);
        //resetTaskSubmitTime(ssEmpTask);
        // 重复任务单校验
        Wrapper<SsEmpTask> ssEmpTaskWrapper = new EntityWrapper<>();
        ssEmpTaskWrapper.where("(is_active = 1 OR (is_active = 0 AND is_suspended = 1))");
        ssEmpTaskWrapper.and("business_interface_id = {0}", ssEmpTask.getBusinessInterfaceId());
        ssEmpTaskWrapper.and("task_id = {0}", ssEmpTask.getTaskId());
        ssEmpTaskWrapper.and("is_change = {0}", ssEmpTask.getIsChange());
        List<SsEmpTask> ssEmpTaskList = ssEmpTaskMapper.selectList(ssEmpTaskWrapper);
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("business_interface_id", ssEmpTask.getBusinessInterfaceId());
//        condition.put("task_id", ssEmpTask.getTaskId());
//        condition.put("is_change", ssEmpTask.getIsChange());
//        condition.put("is_active", 1);
//        List<SsEmpTask> ssEmpTaskList = ssEmpTaskMapper.selectByMap(condition);

        if (CollectionUtils.isNotEmpty(ssEmpTaskList)) {
            logApiUtil.warn(LogMessage.create().setTitle("SsEmpTaskFrontServiceImpl#saveSsEmpTask")
                .setContent("任务单幂等校验未通过。business_interface_id=" + String.valueOf(ssEmpTask.getBusinessInterfaceId())
                    + ", task_id=" + String.valueOf(ssEmpTask.getTaskId())
                    + ", is_change=" + String.valueOf(ssEmpTask.getIsChange())));
            ssEmpTask.setActive(false);
        } else if (!ArrayUtils.contains(SUSPEND_TASK_CATEGORIES, ssEmpTask.getTaskCategory())) {
            // 判断是否需要暂存
            Wrapper<SsEmpArchive> ssEmpArchiveWrapper = new EntityWrapper<>();
            ssEmpArchiveWrapper.where("is_active = 1");
            ssEmpArchiveWrapper.and("archive_status < 3");
            ssEmpArchiveWrapper.and("company_id = {0}", ssEmpTask.getCompanyId());
            ssEmpArchiveWrapper.and("employee_id = {0}", ssEmpTask.getEmployeeId());
            List<SsEmpArchive> ssEmpArchiveList = ssEmpArchiveService.selectList(ssEmpArchiveWrapper);

            // 未转出的雇员档案不存在
            if (CollectionUtils.isEmpty(ssEmpArchiveList)) {
                ssEmpTaskWrapper = new EntityWrapper<>();
                ssEmpTaskWrapper.where("is_active = 1");
                ssEmpTaskWrapper.and("company_id = {0}", ssEmpTask.getCompanyId());
                ssEmpTaskWrapper.and("employee_id = {0}", ssEmpTask.getEmployeeId());
                ssEmpTaskWrapper.and("task_category in (1,2,12,13,99)");
                ssEmpTaskList = ssEmpTaskMapper.selectList(ssEmpTaskWrapper);

                // 且新增类任务单(含不做)未收到
                if (CollectionUtils.isEmpty(ssEmpTaskList)) {
                    // 此时非新增类任务单需暂存
                    ssEmpTask.setActive(false);
                    ssEmpTask.setSuspended(true);
                    logApiUtil.info(LogMessage.create().setTitle("SsEmpTaskFrontServiceImpl#saveSsEmpTask")
                        .setContent("任务单暂存。company_id=" + String.valueOf(ssEmpTask.getCompanyId())
                            + ", employee_id=" + String.valueOf(ssEmpTask.getEmployeeId())
                        ));
                }
            }
        }

        Integer insertRes = ssEmpTaskMapper.insert(ssEmpTask);
        if (insertRes > 0) {
            List<SsEmpTaskFront> ssEmpTaskFrontList = new ArrayList<>();
            SsEmpTaskFront ssEmpTaskFront;
            if (socialList != null) {
                for (AfEmpSocialDTO socialDto : socialList) {
                    if (socialDto.getPolicyType() != null && socialDto.getPolicyType() == SocialSecurityConst.POLICY_TYPE_SOCIAL_SECURITY) {
                        ssEmpTaskFront = new SsEmpTaskFront();
                        ssEmpTaskFront.setEmpTaskId(ssEmpTask.getEmpTaskId());
                        ssEmpTaskFront.setItemDicId(socialDto.getItemCode());
                        ssEmpTaskFront.setEmpCompanyBase(socialDto.getEmpCompanyBase());
                        ssEmpTaskFront.setPolicyId(socialDto.getPolicyId());
                        ssEmpTaskFront.setPolicyName(socialDto.getPolicyName());
                        ssEmpTaskFront.setCompanyRatio(socialDto.getCompanyRatio());
                        ssEmpTaskFront.setCompanyBase(socialDto.getCompanyBase());
                        ssEmpTaskFront.setCompanyAmount(socialDto.getCompanyAmount());
                        ssEmpTaskFront.setPersonalRatio(socialDto.getPersonalRatio());
                        ssEmpTaskFront.setPersonalBase(socialDto.getPersonalBase());
                        ssEmpTaskFront.setPersonalAmount(socialDto.getPersonalAmount());
                        if (ssEmpTask.getStartMonth() != null) {
                            ssEmpTaskFront.setStartMonth(Integer.parseInt(ssEmpTask.getStartMonth()));
                        }
                        if (ssEmpTask.getEndMonth() != null) {
                            ssEmpTaskFront.setEndMonth(Integer.parseInt(ssEmpTask.getEndMonth()));
                        }
                        ssEmpTaskFront.setActive(ssEmpTask.getActive());
                        ssEmpTaskFront.setModifiedBy(afEmpAgreementDTO.getCreatedBy());
                        ssEmpTaskFront.setModifiedDisplayName(afEmpAgreementDTO.getCreatedDisplayName());
                        ssEmpTaskFront.setModifiedTime(LocalDateTime.now());
                        ssEmpTaskFront.setCreatedBy(afEmpAgreementDTO.getCreatedBy());
                        ssEmpTaskFront.setCreatedDisplayName(afEmpAgreementDTO.getCreatedDisplayName());
                        ssEmpTaskFront.setCreatedTime(LocalDateTime.now());
                        ssEmpTaskFront.setLeaderShipId(afEmployeeCompanyDTO.getLeadershipId());
                        ssEmpTaskFront.setLeaderShipName(afEmployeeCompanyDTO.getLeadershipName());
                        ssEmpTaskFrontList.add(ssEmpTaskFront);
                    }
                }
                if (ssEmpTaskFrontList.size() > 0) {
                    this.insertBatch(ssEmpTaskFrontList);
                }
            }

            if (ArrayUtils.contains(SUSPEND_TASK_CATEGORIES, ssEmpTask.getTaskCategory())) {
                // 恢复暂存任务单
                ssEmpTaskWrapper = new EntityWrapper<>();
                ssEmpTaskWrapper.where("is_active = 0");
                ssEmpTaskWrapper.and("is_suspended = 1");
                ssEmpTaskWrapper.and("company_id = {0}", ssEmpTask.getCompanyId());
                ssEmpTaskWrapper.and("employee_id = {0}", ssEmpTask.getEmployeeId());
                ssEmpTaskWrapper.and("task_category in (3,4,5,6,7,9,14,15)");
                SsEmpTask updateSfEmpTask = new SsEmpTask();
                updateSfEmpTask.setSuspended(false);
                updateSfEmpTask.setActive(true);
                int rtn = ssEmpTaskMapper.update(updateSfEmpTask, ssEmpTaskWrapper);
                if (rtn > 0) {
                    logApiUtil.info(LogMessage.create().setTitle("SsEmpTaskFrontServiceImpl#saveSsEmpTask")
                        .setContent("任务单恢复暂存。company_id=" + String.valueOf(ssEmpTask.getCompanyId())
                            + ", employee_id=" + String.valueOf(ssEmpTask.getEmployeeId())
                        ));
                }
            }
        }
        return true;
    }

    @Override
    public Integer getEmpTaskDetailCount(String businessInterfaceId) {
        return baseMapper.getEmpTaskDetailCount(businessInterfaceId);
    }

    @Override
    public List<SsEmpTaskFrontBO> getOriginEmpTaskList(Long empArchiveId) {
        return baseMapper.getOriginEmpTask(empArchiveId);
    }

    /**
     * add by linhui
     * 根据不同任务单类型，把前道传递的社保起缴月份（业务老师们都习惯叫执行日期）
     * submitTime=startMonth+系统日
     * submitTime:任务发起日期
     */
    private void resetTaskSubmitTime(SsEmpTask ssEmpTask) {
        String submitMonth = "";
        LocalDateTime submitTime;
        LocalDateTime now = LocalDateTime.now();
        String today = now.format(DateTimeFormatter.ofPattern("dd"));
        String thisMonth=now.format(DateTimeFormatter.ofPattern("yyyyMM"));
        if (ssEmpTask.getTaskCategory() == Integer.parseInt(SocialSecurityConst.TASK_TYPE_5) ||
            ssEmpTask.getTaskCategory() == Integer.parseInt(SocialSecurityConst.TASK_TYPE_6) ||
            ssEmpTask.getTaskCategory() == Integer.parseInt(SocialSecurityConst.TASK_TYPE_14) ||
            ssEmpTask.getTaskCategory() == Integer.parseInt(SocialSecurityConst.TASK_TYPE_15)) {//转出任务单
            if (Optional.ofNullable(ssEmpTask.getEndMonth()).isPresent() == false) {
                return;
            }
            //如果当前系统月份 > 社保缴纳截止月份 就会在本月处理，否则就是截止月份的下一个月份处理
            if (Integer.parseInt(thisMonth) > Integer.parseInt(ssEmpTask.getEndMonth())) {
                submitMonth = ssEmpTask.getEndMonth() + today;
            } else {
                String em = LocalDate.parse(ssEmpTask.getEndMonth() + today, DateTimeFormatter.ofPattern("yyyyMMdd")).plusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                submitMonth = em;
            }
            submitTime = LocalDateTime.parse(submitMonth + " 00:00:00", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
            ssEmpTask.setSubmitTime(submitTime);
        }
//        else {
//            if (Optional.ofNullable(ssEmpTask.getStartMonth()).isPresent() == false) {
//                return;
//            }
//            submitMonth = ssEmpTask.getStartMonth() + today;
//        }

    }

    /**
     * 更新旧的雇员任务单
     *
     * @param taskMsgDTO
     * @param dto
     * @return
     */
    private boolean updateEmpTaskTb(TaskCreateMsgDTO taskMsgDTO,
                                    AfEmployeeInfoDTO dto) {
        Map<String, Object> paramMap = taskMsgDTO.getVariables();

        AfEmployeeCompanyDTO companyDto = dto.getEmployeeCompany();
        AfEmpAgreementDTO afEmpAgreementDTO = dto.getNowAgreement();
        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();
        //根据oldTaskId获取SsEmpTask对象
        String oldTaskId = paramMap.get("oldTaskId").toString();
        SsEmpTask ssEmpTask = new SsEmpTask();
        ssEmpTask.setTaskId(oldTaskId);
        ssEmpTask = ssEmpTaskMapper.selectOne(ssEmpTask);
        //由于是更正任务单，因此不需要设置taskId
//        ssEmpTask.setTaskId(taskMsgDTO.getTaskId());
        ssEmpTask.setCompanyId(companyDto.getCompanyId());
        ssEmpTask.setEmployeeId(companyDto.getEmployeeId());
        ssEmpTask.setEmpCompanyId(companyDto.getEmpCompanyId());
        //未处理任务单更正时，由于前道生成的新的雇员服务协议记录，因此需要更新businessInterfaceId
        ssEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        ssEmpTask.setSubmitterName(afEmpAgreementDTO.getCreatedBy());
        ssEmpTask.setSalary(companyDto.getSalary());
        ssEmpTask.setSubmitterRemark(companyDto.getRemark());

        if (companyDto.getInDate() != null) {
            ssEmpTask.setInDate(LocalDateTime.ofInstant(companyDto.getInDate().toInstant(), ZoneId.systemDefault())
                .toLocalDate());
        }
        if (companyDto.getOutDate() != null) {
            ssEmpTask.setOutDate(LocalDateTime.ofInstant(companyDto.getOutDate().toInstant(), ZoneId.systemDefault())
                .toLocalDate());
        }
        ssEmpTask.setTaskFormContent(JSON.toJSONString(dto));

        if (dto.getNowAgreement() != null && dto.getNowAgreement().getSocialRuleId() != null) {
            ssEmpTask.setPolicyDetailId(dto.getNowAgreement().getSocialPolicyId());
        }
        //福利办理方
        ssEmpTask.setWelfareUnit(companyDto.getSocialUnit());

        //缴费段开始月份YYYYMM
        for (AfEmpSocialDTO socialDto : socialList) {
            if (socialDto.getPolicyType() != null && socialDto.getPolicyType() == SocialSecurityConst.POLICY_TYPE_SOCIAL_SECURITY) {
                ssEmpTask.setEmpBase(socialDto.getPersonalBase());
                if (socialDto.getStartDate() != null) {
                    ssEmpTask.setStartMonth(StringUtil.dateToString(socialDto
                            .getStartDate(),
                        "yyyyMM"));
                }
                if (socialDto.getEndDate() != null) {
                    ssEmpTask.setEndMonth(StringUtil.dateToString(socialDto.getEndDate(),
                        "yyyyMM"));
                }
                break;
            }
        }

        // 调整或更正的转出或封存时
        if (paramMap.get("oldAgreementId") != null && (
            ProcessCategory.AF_EMP_AGREEMENT_ADJUST.getCategory().equals(ssEmpTask.getProcessCategory())
                || ProcessCategory.AF_EMP_AGREEMENT_UPDATE.getCategory().equals(ssEmpTask.getProcessCategory())
        ) && (
            SocialSecurityConst.TASK_TYPE_5.equals(String.valueOf(ssEmpTask.getTaskCategory()))
                || SocialSecurityConst.TASK_TYPE_6.equals(String.valueOf(ssEmpTask.getTaskCategory()))
        )) {
            // 非0转0不是常规意义的停办，是一种调整任务，所以没有截止年月，此处需特别处理
            if (StringUtils.isEmpty(ssEmpTask.getEndMonth()) && StringUtils.isNotEmpty(ssEmpTask.getStartMonth())) {
                YearMonth startMonthDate = YearMonth.parse(ssEmpTask.getStartMonth(), formatter);
                ssEmpTask.setEndMonth(startMonthDate.minusMonths(1).format(formatter));
                ssEmpTask.setStartMonth(null);
            }
        }

        ssEmpTask.setModifiedBy(afEmpAgreementDTO.getCreatedBy());
        ssEmpTask.setModifiedDisplayName(afEmpAgreementDTO.getModifiedDisplayName());
        ssEmpTask.setModifiedTime(LocalDateTime.now());
        ssEmpTaskMapper.updateById(ssEmpTask);

        //先删除字表的明细数据
        EntityWrapper<SsEmpTaskFront> ew = new EntityWrapper<SsEmpTaskFront>();
        ew.where("emp_task_id={0}", ssEmpTask.getEmpTaskId());
        delete(ew);

        List<SsEmpTaskFront> eleList = new ArrayList<>();
        SsEmpTaskFront ssEmpTaskFront = null;
        if (socialList != null) {
            for (AfEmpSocialDTO socialDto : socialList) {
                if (socialDto.getPolicyType() != null && socialDto.getPolicyType() == SocialSecurityConst.POLICY_TYPE_SOCIAL_SECURITY) {
                    ssEmpTaskFront = new SsEmpTaskFront();
                    ssEmpTaskFront.setEmpTaskId(ssEmpTask.getEmpTaskId());
                    ssEmpTaskFront.setItemDicId(socialDto.getItemCode());
                    ssEmpTaskFront.setEmpCompanyBase(socialDto.getEmpCompanyBase());
                    ssEmpTaskFront.setPolicyId(socialDto.getPolicyId());

                    ssEmpTaskFront.setPolicyName(socialDto.getPolicyName());
                    ssEmpTaskFront.setCompanyRatio(socialDto.getCompanyRatio());
                    ssEmpTaskFront.setCompanyBase(socialDto.getCompanyBase());
                    ssEmpTaskFront.setCompanyAmount(socialDto.getCompanyAmount());

                    ssEmpTaskFront.setPersonalRatio(socialDto.getPersonalRatio());
                    ssEmpTaskFront.setPersonalBase(socialDto.getPersonalBase());
                    ssEmpTaskFront.setPersonalAmount(socialDto.getPersonalAmount());

                    if (socialDto.getStartDate() != null) {
                        ssEmpTaskFront.setStartMonth(Integer.parseInt(StringUtil.dateToString(socialDto.getStartDate(),
                            "yyyyMM")));
                    }
                    if (socialDto.getEndDate() != null) {
                        ssEmpTaskFront.setEndMonth(Integer.parseInt(StringUtil.dateToString(socialDto.getEndDate(),
                            "yyyyMM")));
                    }

                    ssEmpTaskFront.setModifiedBy(afEmpAgreementDTO.getCreatedBy());
                    ssEmpTaskFront.setModifiedTime(LocalDateTime.now());
                    eleList.add(ssEmpTaskFront);
                }
            }
            if (eleList.size() > 0) {
                this.insertOrUpdateBatch(eleList);
            }
        }
        return true;
    }

}
