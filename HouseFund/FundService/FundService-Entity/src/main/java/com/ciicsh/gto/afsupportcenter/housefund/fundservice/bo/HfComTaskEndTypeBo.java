package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTaskEndType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComTaskEndTypeBo extends HfComTaskEndType {
    private Integer endTypeCode;
    private String endTypeValue;
}
