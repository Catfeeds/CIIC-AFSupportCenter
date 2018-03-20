package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HFMonthChargeBasChgDetailBO {
    private Integer rowNo;
    private String inChangeType;
    private String inEmployeeName;
    private String idNum;
    private String inHfEmpAccount;
    private BigDecimal inAmount;
    private BigDecimal base;
    private String ratio;
    private String outHfEmpAccount;
    private String outEmployeeName;
    private String outChangeType;
    private BigDecimal outAmount;
}
