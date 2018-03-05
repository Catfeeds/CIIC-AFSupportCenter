package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComAccountPaymentWay;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComAccountPaymentWayBo extends HfComAccountPaymentWay {
    private Integer paymentwayCode;
    private String paymentwayValue;
}
