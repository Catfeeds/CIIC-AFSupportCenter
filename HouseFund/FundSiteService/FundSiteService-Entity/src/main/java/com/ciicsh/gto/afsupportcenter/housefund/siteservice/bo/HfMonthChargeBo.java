package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class HfMonthChargeBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empArchiveId;
    private String companyId;
    private String employeeId;
    private Integer hfType;
    private String ssMonthBelongStart;
    private String ssMonthBelongEnd;
    private String paymentTypes;
}
