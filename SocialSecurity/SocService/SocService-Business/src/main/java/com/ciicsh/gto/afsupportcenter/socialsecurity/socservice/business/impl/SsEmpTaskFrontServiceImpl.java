package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpAgreementDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpTaskFrontMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTaskFront;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.*;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia 服务实现类
 * </p>
 */
@Service
public class SsEmpTaskFrontServiceImpl extends ServiceImpl<SsEmpTaskFrontMapper, SsEmpTaskFront> implements
    SsEmpTaskFrontService {

    private final static Logger logger = LoggerFactory.getLogger(SsEmpTaskFrontServiceImpl.class);
    @Autowired
    private SsEmpTaskMapper ssEmpTaskMapper;
    @Autowired
    private SsEmpArchiveService ssEmpArchiveService;
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
    public boolean saveEmpTaskTc(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, Integer isChange,
                                 AfEmployeeInfoDTO dto) {
        boolean result = false;
        try {
            //插入数据到雇员任务单表
            saveSsEmpTask(taskMsgDTO, taskCategory, isChange, dto);
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
    public boolean saveSsEmpTask(TaskCreateMsgDTO taskMsgDTO, Integer socialType, Integer isChange,
                                 AfEmployeeInfoDTO dto) throws Exception {
        //基本信息
        AfEmployeeCompanyDTO afEmployeeCompanyDTO = dto.getEmployeeCompany();

        //险种明细
        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();

        //获得社保截止月份
        AfEmpAgreementDTO afEmpAgreementDTO = dto.getNowAgreement();
        logger.info("afEmployeeCompanyDTO:"+afEmployeeCompanyDTO);
        logger.info("socialList:"+socialList);


        SsEmpTask ssEmpTask = new SsEmpTask();
        ssEmpTask.setTaskId(taskMsgDTO.getTaskId());
        ssEmpTask.setCompanyId(afEmployeeCompanyDTO.getCompanyId());
        ssEmpTask.setEmployeeId(afEmployeeCompanyDTO.getEmployeeId());
        ssEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        ssEmpTask.setSubmitterId(afEmployeeCompanyDTO.getCreatedBy());
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
        if(null==ssEmpTask.getInDate() && (1!=socialType && 2!=socialType)){
            EntityWrapper<SsEmpArchive> ew = new EntityWrapper<SsEmpArchive>();
            ew.where("employee_id={0}",ssEmpTask.getEmployeeId())
                .where("archive_status!=3")
                .where("is_active=1");
            SsEmpArchive ssEmpArchive = ssEmpArchiveService.selectOne(ew);
            //设置入职日期
            if(null!=ssEmpArchive){
                if(null!=ssEmpArchive.getInDate())ssEmpTask.setInDate(ssEmpArchive.getInDate());
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
        ssEmpTask.setTaskCategory(socialType);
        ssEmpTask.setIsChange(isChange);
        ssEmpTask.setTaskFormContent(JSON.toJSONString(dto));
        //前道传递的政策明细ID,用它调用内控中心获取计算方式
        if (dto.getNowAgreement() != null && dto.getNowAgreement().getSocialRuleId() != null) {
            ssEmpTask.setPolicyDetailId(dto.getNowAgreement().getSocialPolicyId());
        }
        ssEmpTask.setProcessId(taskMsgDTO.getProcessId());
        ssEmpTask.setTaskStatus(Integer.parseInt(SocialSecurityConst.PROCESS_STATUS_1));
        ssEmpTask.setActive(true);
        //福利办理方
        ssEmpTask.setWelfareUnit(afEmployeeCompanyDTO.getSocialUnit());
        ssEmpTask.setModifiedBy(afEmployeeCompanyDTO.getCreatedBy());
        ssEmpTask.setModifiedTime(LocalDateTime.now());
        ssEmpTask.setCreatedBy(afEmployeeCompanyDTO.getCreatedBy());
        ssEmpTask.setCreatedTime(LocalDateTime.now());

        //社保缴纳段开始月份YYYYMM
        for (AfEmpSocialDTO socialDto : socialList) {
            if (socialDto.getPolicyType() != null && socialDto.getPolicyType() == SocialSecurityConst.POLICY_TYPE_SOCIAL_SECURITY) {
                ssEmpTask.setEmpBase(socialDto.getPersonalBase());
                if (socialDto.getStartDate() != null) {
                    ssEmpTask.setStartMonth(StringUtil.dateToString(socialDto.getStartDate(),"yyyyMM"));
                }
                if (socialDto.getEndDate() != null) {
                    ssEmpTask.setEndMonth(StringUtil.dateToString(socialDto.getEndDate(),"yyyyMM"));
                }
                break;
            }
        }
        //转出需要设置 终止时间
        if(socialType==5){
            if(null != afEmpAgreementDTO){
                Date endMonth = afEmpAgreementDTO.getEndDate();
                if(null!=endMonth){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    //社保截止月份 转出会有
                    ssEmpTask.setEndMonth(simpleDateFormat.format(endMonth));
                }
            }
        }
        //boolean insertRes = ssEmpTaskMapper.insertEmpTask(ssEmpTask);
        resetTaskSubmitTime(ssEmpTask);//
        Integer insertRes = ssEmpTaskMapper.insert(ssEmpTask);
        if (insertRes>0) {
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
                        if (socialDto.getStartDate() != null) {
                            ssEmpTaskFront.setStartMonth(Integer.parseInt(StringUtil.dateToString(socialDto.getStartDate(),"yyyyMM")));
                        }
                        if (socialDto.getEndDate() != null) {
                            ssEmpTaskFront.setEndMonth(Integer.parseInt(StringUtil.dateToString(socialDto.getEndDate(),"yyyyMM")));
                        }
                        ssEmpTaskFront.setActive(true);
                        ssEmpTaskFront.setModifiedBy(afEmployeeCompanyDTO.getCreatedBy());
                        ssEmpTaskFront.setModifiedTime(LocalDateTime.now());
                        ssEmpTaskFront.setCreatedBy(afEmployeeCompanyDTO.getCreatedBy());
                        ssEmpTaskFront.setCreatedTime(LocalDateTime.now());
                        ssEmpTaskFrontList.add(ssEmpTaskFront);
                    }
                }
                if (ssEmpTaskFrontList.size() > 0) {
                    this.insertBatch(ssEmpTaskFrontList);
                }
            }
        }
        return true;
    }
    /**
     * add by linhui
     * 根据不同任务单类型，把前道传递的社保起缴月份（业务老师们都习惯叫执行日期）
     * submitTime=startMonth+系统日
     * submitTime:任务发起日期
     *
     * */
    private void resetTaskSubmitTime(SsEmpTask ssEmpTask){
        String submitMonth="";
        LocalDateTime submitTime;
        String today=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"));
            if(ssEmpTask.getTaskCategory()==5){//转出任务单
                if(ssEmpTask.getEndMonth()==null || ssEmpTask.getEndMonth().equals(""))
                    return;
                submitMonth=ssEmpTask.getEndMonth()+today;
            }else {
                if(ssEmpTask.getStartMonth()==null || ssEmpTask.getStartMonth().equals(""))
                    return;
                submitMonth=ssEmpTask.getStartMonth()+today;
            }
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            submitMonth=sf2.format(sf1.parse(submitMonth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        submitTime=LocalDateTime.parse(submitMonth+" 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ssEmpTask.setSubmitTime(submitTime);
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
        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();

        SsEmpTask ssEmpTask = new SsEmpTask();
        ssEmpTask.setTaskId(paramMap.get("oldEmpAgreementId").toString());
        ssEmpTask.setCompanyId(companyDto.getCompanyId());
        ssEmpTask.setEmployeeId(companyDto.getEmployeeId());
        //更正时，不能更新businessInterfaceId
//        ssEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        ssEmpTask.setSubmitterName(companyDto.getCreatedBy());
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
        ssEmpTask.setModifiedBy(companyDto.getCreatedBy());
        ssEmpTask.setModifiedTime(LocalDateTime.now());
        ssEmpTaskMapper.updateById(ssEmpTask);

        List<SsEmpTaskFront> eleList = new ArrayList<>();
        SsEmpTaskFront ssEmpTaskFront = null;
        if (socialList != null) {
            for (AfEmpSocialDTO socialDto : socialList) {
                ssEmpTaskFront = new SsEmpTaskFront();
                ssEmpTaskFront.setEmpTaskId(Long.parseLong(paramMap.get("oldEmpAgreementId").toString()));
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

                ssEmpTaskFront.setModifiedBy(companyDto.getCreatedBy());
                ssEmpTaskFront.setModifiedTime(LocalDateTime.now());
                eleList.add(ssEmpTaskFront);
            }
            if (eleList.size() > 0) {
                this.updateBatchById(eleList);
            }
        }
        return true;
    }

}
