package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskFrontMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskFront;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia 服务实现类
 * </p>
 */
@Service
public class SsEmpTaskFrontServiceImpl extends ServiceImpl<SsEmpTaskFrontMapper, SsEmpTaskFront> implements
    SsEmpTaskFrontService {
    @Autowired
    private SsEmpTaskMapper ssEmpTaskMapper;

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
            insertTaskTb(taskMsgDTO, taskCategory, isChange, dto);

            //更新旧的雇员任务单
            updateEmpTaskTb(taskMsgDTO, dto);

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
     * @param taskMsgDTO
     * @param taskCategory
     * @param isChange
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    @Override
    public boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, Integer isChange,
                                AfEmployeeInfoDTO dto) throws Exception {
        AfEmployeeCompanyDTO companyDto = dto.getEmployeeCompany();

        SsEmpTask ssEmpTask = new SsEmpTask();
        ssEmpTask.setTaskId(taskMsgDTO.getTaskId());
        ssEmpTask.setCompanyId(companyDto.getCompanyId());
        ssEmpTask.setEmployeeId(companyDto.getEmployeeId());
        ssEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
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

        ssEmpTask.setTaskCategory(taskCategory);
        ssEmpTask.setIsChange(isChange);
        ssEmpTask.setTaskFormContent(JSON.toJSONString(dto));

        ssEmpTask.setTaskStatus(1);
        ssEmpTask.setActive(true);
        ssEmpTask.setModifiedBy(companyDto.getCreatedBy());
        ssEmpTask.setModifiedTime(LocalDateTime.now());
        ssEmpTask.setCreatedBy(companyDto.getCreatedBy());
        ssEmpTask.setCreatedTime(LocalDateTime.now());
        boolean insertRes = ssEmpTaskMapper.insertEmpTask(ssEmpTask);

        if (insertRes) {
            List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();
            List<SsEmpTaskFront> eleList = new ArrayList<>();
            SsEmpTaskFront ssEmpTaskFront = null;
            if (socialList != null) {
                for (AfEmpSocialDTO socialDto : socialList) {
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

                    ssEmpTaskFront.setActive(true);
                    ssEmpTaskFront.setModifiedBy(companyDto.getCreatedBy());
                    ssEmpTaskFront.setModifiedTime(LocalDateTime.now());
                    ssEmpTaskFront.setCreatedBy(companyDto.getCreatedBy());
                    ssEmpTaskFront.setCreatedTime(LocalDateTime.now());
                    eleList.add(ssEmpTaskFront);
                }
                if (eleList.size() > 0) {
                    this.insertBatch(eleList);
                }
            }
        }
        return true;
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

        SsEmpTask ssEmpTask = new SsEmpTask();
        ssEmpTask.setTaskId(paramMap.get("oldTaskId").toString());
        ssEmpTask.setCompanyId(companyDto.getCompanyId());
        ssEmpTask.setEmployeeId(companyDto.getEmployeeId());
        ssEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
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

        ssEmpTask.setModifiedBy(companyDto.getCreatedBy());
        ssEmpTask.setModifiedTime(LocalDateTime.now());
        ssEmpTaskMapper.updateById(ssEmpTask);

        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();
        List<SsEmpTaskFront> eleList = new ArrayList<>();
        SsEmpTaskFront ssEmpTaskFront = null;
        if (socialList != null) {
            for (AfEmpSocialDTO socialDto : socialList) {
                ssEmpTaskFront = new SsEmpTaskFront();
                ssEmpTaskFront.setEmpTaskId(Long.parseLong(paramMap.get("oldTaskId").toString()));
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