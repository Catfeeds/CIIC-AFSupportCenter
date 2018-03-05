package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTaskTaskStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComTaskTaskStatusBo extends HfComTaskTaskStatus {
    private Integer taskStatusCode;
    private String taskStatusValue;
    private boolean disabled;
}
