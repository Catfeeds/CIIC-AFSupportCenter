package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

@Data
public class HFNetBankComAccountBO {
    private Integer hfAccountType;
    private String comAccountId;
    private Long comAccountClassId;
    private String hfComAccount;
    private Integer hfType;
    private String paymentMonth;
}
