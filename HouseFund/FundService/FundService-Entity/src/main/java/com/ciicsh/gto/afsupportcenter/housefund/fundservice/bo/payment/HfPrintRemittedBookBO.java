package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HfPrintRemittedBookBO {
    private String comAccountName;
    private String hfComAccount;
    private String paymentMonth;
    private String curdate;
    private String bankName;
    private BigDecimal remittedAmountAdd;
    private BigDecimal remittedAmountReduce;
    private BigDecimal remittedAmount;
    private BigDecimal remittedCountEmpAdd;
    private BigDecimal remittedCountEmpReduce;
    private Integer remittedCountEmp;
    private BigDecimal remittedAmountLast;
    private BigDecimal remittedCountEmpLast;
    private BigDecimal repairAmount;
    private Integer repairCountEmp;
    private Boolean isRemitted;//打钩汇缴
    private Boolean isRepair;//打钩补缴

}
