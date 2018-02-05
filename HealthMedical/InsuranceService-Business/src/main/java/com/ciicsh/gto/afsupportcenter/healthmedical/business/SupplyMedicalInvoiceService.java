package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeeBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalAcceptanceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;

import java.util.List;

/**
 * <p>
 * 补充医疗受理单相关发票表 服务类
 * </p>
 *
 * @author xiweizhen
 */
public interface SupplyMedicalInvoiceService extends IService<SupplyMedicalInvoice> {

    /**
     * 根据条件删除
     *
     * @param acceptanceId
     */
    void deleteByAcceptanceId(String acceptanceId);

    /**
     * 查询发票列表
     *
     * @param id
     * @return
     */
    List<SupplyMedicalInvoice> queryMedicalInvoiceList(String id);

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
