package com.ciicsh.gto.afsupportcenter.healthmedical.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalAuditDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedicalAudit;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiweizhen
 */
public interface UninsuredMedicalAuditMapper extends BaseMapper<UninsuredMedicalAudit> {

    /**
     * 查询受理单列表数据
     *
     * @param page
     * @param uninsuredMedicalAuditDTO
     * @return
     */
    List<UninsuredMedical> queryAcceptanceList(Page<UninsuredMedical> page, UninsuredMedicalAuditDTO uninsuredMedicalAuditDTO);
}
