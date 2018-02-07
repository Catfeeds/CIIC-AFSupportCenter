package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import java.io.Serializable;

public class HfEmpTaskHandleBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private String companyId;
    private String companyName;
    private Integer paymentBank;
    private Integer state;
    private Integer paymentWay;
    private Integer ukeyStore;
    private String comAccountName;
    private Integer basicComTaskStatus;
    private Integer addedComTaskStatus;
    private Integer hfAccountType;
    private String basicHfComAccount;
    private Integer basicComHfMonth;
}
