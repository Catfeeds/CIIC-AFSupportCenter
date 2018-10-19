package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;

@Data
public class SsEmpTaskUndoBO {
    private Integer taskStatus;
    private String startMonth;
    private String endMonth;
    private String ssMonth;
    private Long empArchiveId;
    private String modifiedBy;
    private Integer taskCategory;
    private Long empTaskId;
}
