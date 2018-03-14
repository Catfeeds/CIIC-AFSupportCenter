package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HFMonthChargeRepairDetailBO {

    private String employeeId;
    private String hfEmpAccount;
    private String employeeName;
    private String idNum;
    private Integer repairReason;
    private String startMonth;
    private String endMonth;
    private BigDecimal ratio;
    private BigDecimal amount;
    private int months;
    private BigDecimal subTotalAmount;
    private BigDecimal totalAmount;

    public void plusOneMonth() {
        this.months++;
    }
}
