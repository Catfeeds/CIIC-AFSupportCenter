package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

@Data
public class HfEmpTaskUndoBO {
    private Integer taskStatus;
    private String startMonth;
    private String endMonth;
    private String hfMonth;
    private Long empArchiveId;
    private String modifiedBy;
    private Integer taskCategory;
    private Long empTaskId;
    private Integer hfType;
}
