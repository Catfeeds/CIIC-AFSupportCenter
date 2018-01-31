package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfArchiveBasePeriodBo extends com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfArchiveBasePeriod {
    private String hfEmpAccount;
    private String archiveTaskStatus;
}
