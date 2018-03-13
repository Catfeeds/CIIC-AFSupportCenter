package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SsStatementResultCompareBO {
    private String employeeId;
    private String employeeName;
    private Integer diffHeadcount;
}
