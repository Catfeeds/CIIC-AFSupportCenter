package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HfPrintRemittedBookBO {
    private String comAccountName;
    private String hfComAccount;
    private String paymentYear;
    private String paymentMonth;
    private String curdate;
    private Integer curYear;
    private Integer curMonth;
    private Integer curDay;
    private String bankName;
    private BigDecimal remittedAmountAdd;//本月增加金额
    private BigDecimal remittedAmountReduce;//本月减少金额
    private BigDecimal remittedAmount;//本月汇缴金额
    private BigDecimal remittedCountEmpAdd;//本月增加人数
    private BigDecimal remittedCountEmpReduce;//本月减少人数
    private Integer remittedCountEmp;//本月汇缴人数
    private BigDecimal remittedAmountLast;//上月汇缴金额
    private BigDecimal remittedCountEmpLast;//上月汇缴人数
    private BigDecimal repairAmount;//补缴金额
    private Integer repairCountEmp;//补缴人数
    private Boolean isRemitted;//打钩汇缴
    private Boolean isRepair;//打钩补缴
    private Integer pageNum;
    private String moneyCN;
}
