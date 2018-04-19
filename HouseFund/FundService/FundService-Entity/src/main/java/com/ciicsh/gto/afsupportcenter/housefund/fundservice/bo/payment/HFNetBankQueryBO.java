package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

@Data
public class HFNetBankQueryBO {
    private Long comAccountClassId;
    private String hfMonth;
    private Integer hfType;
    private String paymentTypes;
    private Integer exceptRepairReason;
}
