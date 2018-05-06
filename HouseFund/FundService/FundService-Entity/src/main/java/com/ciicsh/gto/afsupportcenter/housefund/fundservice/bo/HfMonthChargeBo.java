package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HfMonthChargeBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean inactive;
    private Long empArchiveId;
    private String companyId;
    private String employeeId;
    private List<Long> empTaskIdList;
    private Integer hfType;
    private String hfMonth;
    private String ssMonthBelongStart;
    private String ssMonthBelongEnd;
    private String paymentTypes;
    private Integer chgPaymentType;
    private String modifiedBy;
    private Long exceptEmpTaskId;
}
