package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by houwanhua on 2018/1/29.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StatementExportArgs {
    private String ssMonth;
    private Long comAccountId;
    private String impFileType;
    private Integer minDiffSumByEmp;
    private Integer maxDiffSumByEmp;
    private String companyId;
}
