package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HFMonthChargeAddChgDetailBO {
    private Integer rowNo;
    private String inChangeType;
    private String inEmployeeName;
    private String basicHfEmpAccount;
    private String inAddedHfEmpAccount;
    private BigDecimal inAmount;
    private String ratio;
    private String outAddedHfEmpAccount;
    private String outEmployeeName;
    private String outChangeType;
    private BigDecimal outAmount;
}
