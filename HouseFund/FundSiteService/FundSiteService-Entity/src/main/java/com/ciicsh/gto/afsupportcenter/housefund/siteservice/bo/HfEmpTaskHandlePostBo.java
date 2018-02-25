package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class HfEmpTaskHandlePostBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private Integer hfType;
    private Integer taskStatus;
}
