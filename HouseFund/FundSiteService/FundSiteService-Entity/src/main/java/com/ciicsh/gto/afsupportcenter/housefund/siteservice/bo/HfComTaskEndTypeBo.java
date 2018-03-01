package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTaskEndType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComTaskEndTypeBo extends HfComTaskEndType {
    private Integer endTypeCode;
    private String endTypeValue;
}
