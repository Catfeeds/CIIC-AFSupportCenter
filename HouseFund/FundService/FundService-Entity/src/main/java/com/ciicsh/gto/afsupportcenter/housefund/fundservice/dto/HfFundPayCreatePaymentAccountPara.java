package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class HfFundPayCreatePaymentAccountPara {
    List listData;
    String payee; //收款方
    Integer paymentWay;//付款方式
    String paymentMonth;//支付年月
    Long paymentId;
    String remark;
}
