package com.ciicsh.gto.afsupportcenter.healthmedical.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.*;
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

    /**
     * 更新状态
     * @param bo
     * @return
     */
    Integer updateStatus(EmployeePaymentStatusBO bo);

    /**
     * 更新受理单状态为已同步
     * @param bo
     * @return
     */
    Integer syncStatus(EmployeePaymentStatusBO bo);

    /**
     * 查询已审核未同步申请
     * @param
     * @return
     */
    List<EmployeePaymentBO> selectAudited();

    /**
     * 查询退票申请
     * @param
     * @return
     */
    List<EmployeePaymentBO> selectRefund();

    /**
     * 查询信息有误未同步数据
     * @param
     * @return
     */
    List<EmpBankRefundBO> selectUnSync();

    /**
     * 查询银行退票数据
     * @param
     * @return
     */
    List<EmpBankRefundBO> selectBankRefund();

    /**
     * 查询受理单列表数据
     *
     * @param entity
     * @return
     */
    List<UninsuredMedical> selectAll(UninsuredMedicalDTO entity);
}
