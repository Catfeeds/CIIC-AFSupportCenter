package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;

import java.util.List;

/**
 * 补充医疗--理单详情
 *
 * @author xiweizhen
 */
public class SupplyAcceptanceDetailDTO {
    private SupplyMedicalAcceptance supplyMedicalAcceptance;
    private List<SupplyMedicalInvoice> supplyMedicalInvoices;

    public SupplyMedicalAcceptance getSupplyMedicalAcceptance() {
        return supplyMedicalAcceptance;
    }

    public void setSupplyMedicalAcceptance(SupplyMedicalAcceptance supplyMedicalAcceptance) {
        this.supplyMedicalAcceptance = supplyMedicalAcceptance;
    }

    public List<SupplyMedicalInvoice> getSupplyMedicalInvoices() {
        return supplyMedicalInvoices;
    }

    public void setSupplyMedicalInvoices(List<SupplyMedicalInvoice> supplyMedicalInvoices) {
        this.supplyMedicalInvoices = supplyMedicalInvoices;
    }

    @Override
    public String toString() {
        return "SupplyAcceptanceDetailDTO{" +
            "supplyMedicalAcceptance=" + supplyMedicalAcceptance +
            ", supplyMedicalInvoices=" + supplyMedicalInvoices +
            '}';
    }
}
