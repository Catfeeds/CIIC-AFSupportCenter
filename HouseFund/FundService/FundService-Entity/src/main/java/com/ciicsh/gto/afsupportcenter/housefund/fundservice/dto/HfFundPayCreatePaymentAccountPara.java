package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class HfFundPayCreatePaymentAccountPara {
    List listData;
    String payee;
    String paymentMonth;
    Long paymentId;
}
