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

    // 客户名称
    private String companyName;
    // 企业基本公积金账号
    private String basicHfComAccount;
    // 企业补充公积金账号
    private String addedHfComAccount;
    //账户类型
    private Integer hfType;

    private String userId;
}
