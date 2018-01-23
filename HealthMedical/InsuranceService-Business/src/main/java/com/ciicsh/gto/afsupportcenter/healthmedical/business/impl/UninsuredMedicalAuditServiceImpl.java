package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalAuditService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.UninsuredMedicalAuditMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.UninsuredMedicalAuditBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalAuditDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedicalAudit;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiweizhen
 */
@Service
public class UninsuredMedicalAuditServiceImpl extends ServiceImpl<UninsuredMedicalAuditMapper, UninsuredMedicalAudit> implements UninsuredMedicalAuditService {

    @Override
    public Page<UninsuredMedical> queryAcceptanceList(Page<UninsuredMedical> page, UninsuredMedicalAuditDTO uninsuredMedicalAuditDTO) {
        page.setRecords(baseMapper.queryAcceptanceList(page, uninsuredMedicalAuditDTO));
        return page;
    }

    @Override
    public Page<UninsuredMedicalAuditBO> queryAcceptanceAuditList(Page<UninsuredMedicalAuditBO> page, UninsuredMedicalAuditDTO uninsuredMedicalAuditDTO) {
        page.setRecords(baseMapper.queryAcceptanceAuditList(page, uninsuredMedicalAuditDTO));
        return page;
    }
}
