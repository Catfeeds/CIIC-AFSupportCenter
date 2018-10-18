package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HfArchiveBasePeriodUpdateBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Long> empTaskIdList;
    private Long empArchiveId;
    private String endMonth;
    private String modifiedBy;
}
