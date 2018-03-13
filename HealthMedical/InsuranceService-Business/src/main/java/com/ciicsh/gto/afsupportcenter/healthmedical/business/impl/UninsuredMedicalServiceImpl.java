package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.UninsuredMedicalMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpMemberBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.UninsuredMedicalBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.CompanyConsultantRelation;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 未投保医疗 服务实现类
 * </p>
 *
 * @author xiweizhen
 */
@Service
public class UninsuredMedicalServiceImpl extends ServiceImpl<UninsuredMedicalMapper, UninsuredMedical> implements UninsuredMedicalService {

    @Override
    public Page<UninsuredMedical> queryAcceptanceList(Page<UninsuredMedical> page, UninsuredMedicalDTO uninsuredMedical) {
        page.setRecords(baseMapper.queryAcceptanceList(page, uninsuredMedical));
        return page;
    }

    @Override
    public Page<UninsuredMedicalBO> queryEmployeeList(Page<UninsuredMedicalBO> page, UninsuredMedicalBO uninsuredMedicalBO) {
        page.setRecords(baseMapper.queryEmployeeList(page, uninsuredMedicalBO));
        return page;
    }

    @Override
    public List<CompanyConsultantRelation> queryBusinessConsultant(String companyId) {
        return baseMapper.queryBusinessConsultant(companyId);
    }

    @Override
    public List<EmpMemberBO> queryEmpMember(String employeeId) {
        return baseMapper.queryEmpMember(employeeId);
    }

    @Override
    public List<UninsuredMedical> selectAll(UninsuredMedicalDTO uninsuredMedicalDTO) {
        return baseMapper.selectAll(uninsuredMedicalDTO);
    }
}
