package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccountPaymentWay;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComAccountPaymentWayBo extends HfComAccountPaymentWay {
    private Integer paymentwayCode;
    private String paymentwayValue;
}
