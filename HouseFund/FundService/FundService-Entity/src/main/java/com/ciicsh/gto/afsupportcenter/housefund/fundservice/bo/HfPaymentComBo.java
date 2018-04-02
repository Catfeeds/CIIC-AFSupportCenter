package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther shenjian
 * @since 2018/3/25
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class HfPaymentComBo extends HfPaymentCom {
    /**
     * 支付年月YYYYMM
     */
    private String paymentMonth;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公积金类型名称
     */
    private String hfTypeName;

}
