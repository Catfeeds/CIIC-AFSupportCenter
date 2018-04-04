package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

@Data
public class HfCreatePaymentAccountBO {
    Long comAccountId;
    String companyId;
    Long comAccountClassId;
    Integer paymentBank;
    Integer hfType;
    Integer hfAccountType;
}
