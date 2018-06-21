package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

@Data
public class HfRemittedBookParam {
    private String paymentMonth;
    private Integer hfType;
    private Long comAccountClassId;
    private Long comAccountId;
    private Integer hfAccountType;
    private Integer paymentBank;

}
