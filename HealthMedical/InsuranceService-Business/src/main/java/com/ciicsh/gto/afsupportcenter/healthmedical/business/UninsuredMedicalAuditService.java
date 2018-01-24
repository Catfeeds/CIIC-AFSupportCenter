package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.UninsuredMedicalAuditBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalAuditDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedicalAudit;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiweizhen
 */
public interface UninsuredMedicalAuditService extends IService<UninsuredMedicalAudit> {

    /**
     * 查询未审核受理单列表
     *
     * @param page
     * @param uninsuredMedicalAuditDTO
     * @return
     */
    Page<UninsuredMedical> queryAcceptanceList(Page<UninsuredMedical> page, UninsuredMedicalAuditDTO uninsuredMedicalAuditDTO);

    /**
     * 查询未投保审核列表
     *
     * @param page
     * @param uninsuredMedicalAuditDTO
     * @return
     */
    Page<UninsuredMedicalAuditBO> queryAcceptanceAuditList(Page<UninsuredMedicalAuditBO> page, UninsuredMedicalAuditDTO uninsuredMedicalAuditDTO);
}
