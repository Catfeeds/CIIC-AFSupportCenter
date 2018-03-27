package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HFMonthChargeRepairDetailBO {

    private Integer rowNo;
    private String hfEmpAccount;
    private String employeeName;
    private String idNum;
    private String repairReason;
    private String repairPeriodFir;
    private String startMonth;
    private String endMonth;

    private String ratioFir;
    private BigDecimal amountFir;
    private BigDecimal subAmountFir;
    private int months;
    private BigDecimal totalAmount;

    public void plusOneMonth() {
        this.months++;
    }
}
