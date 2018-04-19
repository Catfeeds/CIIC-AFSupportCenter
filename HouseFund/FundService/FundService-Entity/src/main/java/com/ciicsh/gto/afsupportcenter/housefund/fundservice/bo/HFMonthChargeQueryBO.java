package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class HFMonthChargeQueryBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String employeeId;
    private String employeeName;
    private String basicHfEmpAccount;
    private String addedHfEmpAccount;
    private Integer hfAccountType;
    private String hfMonth;
    private String companyId;
    private String companyName;
    private String basicHfComAccount;
    private String addedHfComAccount;
    private Integer hfType;
    private Integer exceptRepairReason;
    private String paymentTypes;
    private String[] basicComAccountArray;
    private String[] addedComAccountArray;
    private Long paymentId;
    private String userId;
}
