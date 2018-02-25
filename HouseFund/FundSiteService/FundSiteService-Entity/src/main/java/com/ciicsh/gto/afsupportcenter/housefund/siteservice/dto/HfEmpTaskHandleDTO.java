package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class HfEmpTaskHandleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private Integer hfType;
    private Integer taskStatus;
}
