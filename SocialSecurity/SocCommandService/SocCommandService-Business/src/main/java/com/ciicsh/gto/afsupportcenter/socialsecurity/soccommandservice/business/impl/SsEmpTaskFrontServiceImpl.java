package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskFrontMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.AFEmpSocialDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.AFEmployeeCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.EmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskFront;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia 服务实现类
 * </p>
 */
@Service
public class SsEmpTaskFrontServiceImpl extends ServiceImpl<SsEmpTaskFrontMapper, SsEmpTaskFront> implements
    ISsEmpTaskFrontService {
    @Autowired
    private SsEmpTaskMapper ssEmpTaskMapper;

    /**
     * <p>Description: 保存数据到雇员任务单表</p>
     *
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, EmployeeInfoDTO dto) {
        boolean result = true;
        try {
            AFEmployeeCompanyDTO companyDto = dto.getEmployeeCompany();

            SsEmpTask ssEmpTask = new SsEmpTask();
            ssEmpTask.setTaskId(taskMsgDTO.getTaskId());
            ssEmpTask.setCompanyId(companyDto.getCompanyId());
            ssEmpTask.setEmployeeId(companyDto.getEmpId());
            ssEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
            ssEmpTask.setSubmitterName(companyDto.getCreatedBy());
            ssEmpTask.setSalary(companyDto.getSalary());
            ssEmpTask.setSubmitterRemark(companyDto.getRemark());

            ssEmpTask.setInDate(LocalDateTime.ofInstant(companyDto.getInDate().toInstant(), ZoneId.systemDefault())
                .toLocalDate());
            ssEmpTask.setOutDate(LocalDateTime.ofInstant(companyDto.getOutDate().toInstant(), ZoneId.systemDefault())
                .toLocalDate());

            ssEmpTask.setTaskCategory(taskCategory);

            ssEmpTask.setActive(true);
            ssEmpTask.setModifiedBy("system");
            ssEmpTask.setModifiedTime(LocalDateTime.now());
            ssEmpTask.setCreatedBy("system");
            ssEmpTask.setCreatedTime(LocalDateTime.now());
            ssEmpTaskMapper.insert(ssEmpTask);

            List<AFEmpSocialDTO> socialList = dto.getEmpSocial();
            List<SsEmpTaskFront> eleList = new ArrayList<>();
            SsEmpTaskFront ssEmpTaskFront = null;
            for (AFEmpSocialDTO socialDto : socialList) {
                ssEmpTaskFront = new SsEmpTaskFront();
                ssEmpTaskFront.setEmpTaskId(Long.parseLong(taskMsgDTO.getTaskId()));
                ssEmpTaskFront.setItemDicId(socialDto.getItemDicId());
                ssEmpTaskFront.setEmpCompanyBase(socialDto.getEmpBase());
                ssEmpTaskFront.setPolicyId(socialDto.getPolicyId());

                ssEmpTaskFront.setPolicyName(socialDto.getPolicyName());
                ssEmpTaskFront.setCompanyRatio(socialDto.getCompanyRatio());
                ssEmpTaskFront.setCompanyBase(socialDto.getCompanyBase());
                ssEmpTaskFront.setCompanyAmount(socialDto.getCompanyAmount());

                ssEmpTaskFront.setPersonalRatio(socialDto.getPersonalRatio());
                ssEmpTaskFront.setPersonalBase(socialDto.getPersonalBase());
                ssEmpTaskFront.setPersonalAmount(socialDto.getPersonalAmount());
                ssEmpTaskFront.setStartMonth(socialDto.getStartMonth());
                ssEmpTaskFront.setEndMonth(socialDto.getEndMonth());

                ssEmpTaskFront.setActive(true);
                ssEmpTaskFront.setModifiedBy(null);
                ssEmpTaskFront.setModifiedTime(null);
                ssEmpTaskFront.setCreatedBy("system");
                ssEmpTaskFront.setCreatedTime(LocalDateTime.now());
                eleList.add(ssEmpTaskFront);
            }
            if (eleList.size() > 0) {
                this.insertBatch(eleList);
            }
        } catch (Exception e) {
            result = false;
            throw new RuntimeException("保存到雇员任务单表处理异常");
        }
        return result;
    }
}
