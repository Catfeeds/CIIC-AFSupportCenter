package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfEmpTaskPeriodBo extends com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTaskPeriod {
    private String taskCategory;
    private Integer isChange;
    private String taskEndMonth;

}
