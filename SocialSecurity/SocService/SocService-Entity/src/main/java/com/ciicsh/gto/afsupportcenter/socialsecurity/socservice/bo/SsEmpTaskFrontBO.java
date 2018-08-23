package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SsEmpTaskFrontBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private Integer taskCategory;
    private Integer taskStatus;
    private BigDecimal empCompanyBase;
    private String startMonth;
    private String endMonth;
    private String createdDisplayName;
    private LocalDateTime createdTime;
}
