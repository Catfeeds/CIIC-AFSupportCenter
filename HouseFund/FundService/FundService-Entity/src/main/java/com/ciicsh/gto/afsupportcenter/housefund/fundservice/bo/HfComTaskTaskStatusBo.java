package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTaskTaskStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComTaskTaskStatusBo extends HfComTaskTaskStatus {
    private Integer taskStatusCode;
    private String taskStatusValue;
    private boolean disabled;
}
