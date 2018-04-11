package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HfCreatePaymentAccountBO {
    Long paymentAccountId;
    Long comAccountId;
    String companyId;
    String paymentMonth;
    Long comAccountClassId;
    Integer paymentBank;
    Integer hfType;
    Integer hfAccountType;
    BigDecimal sumAdd;
    BigDecimal sumSub;
    BigDecimal sumAmount;
    BigDecimal payInBackAmount;
}
