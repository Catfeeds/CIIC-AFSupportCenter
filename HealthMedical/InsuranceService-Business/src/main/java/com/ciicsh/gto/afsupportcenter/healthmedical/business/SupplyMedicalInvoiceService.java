package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;

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
     * @param datilEntity
     */
    void deleteByEntity(SupplyMedicalInvoice datilEntity);
}
