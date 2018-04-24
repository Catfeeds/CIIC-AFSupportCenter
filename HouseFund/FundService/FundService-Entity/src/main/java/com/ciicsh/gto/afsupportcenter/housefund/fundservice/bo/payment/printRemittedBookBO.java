package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

@Data
public class printRemittedBookBO {
    private String comAccountName;
    private String hfComAccount;
    private String paymentMonth;
    private String curdate;
    private String bankName;
    private String remittedAmountAdd;
    private String remittedAmountReduce;
    private String remittedAmount;
    private String remittedCountEmpAdd;
    private String remittedCountEmpReduce;
    private String remittedCountEmp;
    private String remittedAmountLast;
    private String remittedCountEmpLast;
}
