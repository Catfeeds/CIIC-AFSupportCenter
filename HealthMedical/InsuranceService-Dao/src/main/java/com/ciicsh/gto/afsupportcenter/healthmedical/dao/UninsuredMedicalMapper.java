package com.ciicsh.gto.afsupportcenter.healthmedical.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpMemberBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.UninsuredMedicalBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.CompanyConsultantRelation;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;

import java.util.List;

/**
 * <p>
 * 未投保医疗 Mapper 接口
 * </p>
 *
 * @author xiweizhen
 */
public interface UninsuredMedicalMapper extends BaseMapper<UninsuredMedical> {

    /**
     * 查询联系人分页数据
     *
     * @param page
     * @param entity
     * @return
     */
    List<UninsuredMedicalBO> queryEmployeeList(Page<UninsuredMedicalBO> page, UninsuredMedicalBO entity);

    /**
     * 根据公司编号查询业务顾问
     *
     * @param companyId
     * @return
     */
    List<CompanyConsultantRelation> queryBusinessConsultant(String companyId);


    /**
     * 查询连带人下拉数据
     *
     * @param companyId
     * @return
     */
    List<EmpMemberBO> queryEmpMember(String companyId);

    /**
     * 查询受理单列表数据
     *
     * @param page
     * @param entity
     * @return
     */
    List<UninsuredMedical> queryAcceptanceList(Page<UninsuredMedical> page, UninsuredMedicalDTO entity);

}
