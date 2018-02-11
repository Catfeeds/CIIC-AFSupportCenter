package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto;

import java.io.Serializable;

public class HfEmpTaskHandleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private Integer hfType;

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }
}
