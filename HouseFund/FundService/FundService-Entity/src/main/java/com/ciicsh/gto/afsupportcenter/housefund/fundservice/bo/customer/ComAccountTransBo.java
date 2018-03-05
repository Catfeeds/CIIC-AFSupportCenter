package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class ComAccountTransBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long comAccountId;
    private String comAccountName;
    private Long comAccountClassId;
    private String hfComAccount;
    private Integer hfType;
}
