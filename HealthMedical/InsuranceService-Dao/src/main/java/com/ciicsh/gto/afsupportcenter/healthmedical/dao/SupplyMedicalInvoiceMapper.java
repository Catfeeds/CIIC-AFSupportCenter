package com.ciicsh.gto.afsupportcenter.healthmedical.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeeBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalAcceptanceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;

/**
 * <p>
 * 补充医疗受理单相关发票表 Mapper 接口
 * </p>
 *
 * @author xiweizhen
 */
public interface SupplyMedicalInvoiceMapper extends BaseMapper<SupplyMedicalInvoice> {

    /**
     * 查询雇员详情
     *
     * @param employeeId
     * @return
     */
    EmployeeBO queryEmployeeInfo(String employeeId);

    /**
     * 查询发票总条数
     *
     * @param supplyMedicalAcceptanceDTO
     * @return
     */
    Integer queryInvoiceCount(SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO);
}
