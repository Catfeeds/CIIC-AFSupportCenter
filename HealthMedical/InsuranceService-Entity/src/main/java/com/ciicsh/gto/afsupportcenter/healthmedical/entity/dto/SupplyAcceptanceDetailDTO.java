package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeeBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;

import java.util.List;

/**
 * 补充医疗--受理单详情
 *
 * @author xiweizhen
 */
public class SupplyAcceptanceDetailDTO {
    private EmployeeBO employee;
    private List<SupplyMedicalInvoice> supplyMedicalInvoices;

    public EmployeeBO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeBO employee) {
        this.employee = employee;
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
            "employee=" + employee +
            ", supplyMedicalInvoices=" + supplyMedicalInvoices +
            '}';
    }
}
