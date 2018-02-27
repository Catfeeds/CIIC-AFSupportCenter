package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by houwanhua on 2018/2/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComAccountParamExtBo {
    // 客户ID
    private String companyId;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    private Integer hfAccountType;
}
