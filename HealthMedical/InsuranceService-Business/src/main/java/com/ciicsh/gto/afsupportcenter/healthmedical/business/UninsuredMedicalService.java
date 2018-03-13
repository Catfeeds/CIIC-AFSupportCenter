package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpMemberBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.UninsuredMedicalBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.CompanyConsultantRelation;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;

import java.util.List;

/**
 * <p>
 * 未投保医疗 服务类
 * </p>
 *
 * @author xiweizhen
 */
public interface UninsuredMedicalService extends IService<UninsuredMedical> {

    /**
     * 受理单分页查询
     *
     * @param page
     * @param uninsuredMedical
     * @return
     */
    Page<UninsuredMedical> queryAcceptanceList(Page<UninsuredMedical> page, UninsuredMedicalDTO uninsuredMedical);

    /**
     * 查询联系人分页数据
     *
     * @param page
     * @param uninsuredMedicalBO
     * @return
     */
    Page<UninsuredMedicalBO> queryEmployeeList(Page<UninsuredMedicalBO> page, UninsuredMedicalBO uninsuredMedicalBO);

    /**
     * 查询业务顾问数据
     *
     * @param companyId
     * @return
     */
    List<CompanyConsultantRelation> queryBusinessConsultant(String companyId);

    /**
     * 查询连带人列表
     *
     * @param employeeId
     * @return
     */
    List<EmpMemberBO> queryEmpMember(String employeeId);

    /**
     * 条件查询所有受理单
     * @param uninsuredMedicalDTO
     * @return
     */
    List<UninsuredMedical> selectAll(UninsuredMedicalDTO uninsuredMedicalDTO);
}
