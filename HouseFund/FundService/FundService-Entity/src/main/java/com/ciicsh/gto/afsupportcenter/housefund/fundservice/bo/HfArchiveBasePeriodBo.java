package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfArchiveBasePeriodBo extends com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod {
    private String hfEmpAccount;
    private String archiveTaskStatus;
}
