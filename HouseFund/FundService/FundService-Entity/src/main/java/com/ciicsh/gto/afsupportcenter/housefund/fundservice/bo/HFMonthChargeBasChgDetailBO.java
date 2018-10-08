package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HFMonthChargeBasChgDetailBO {
    private Integer rowNo;
    private String inChangeType;
    private String inEmployeeName;
    private String certificateType;
    private String idNum;
    private String birthday;
    private String gender;
    private String inHfEmpAccount;
    private BigDecimal inAmountValue;
    private String inAmount;
    private BigDecimal base;
    private String ratio;
    private String outHfEmpAccount;
    private String outEmployeeName;
    private String outChangeType;
    private BigDecimal outAmount;
}
