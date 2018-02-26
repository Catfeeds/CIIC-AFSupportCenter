package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by houwanhua on 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComAccountParamBO {

    private String companyId;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    private Integer ssAccountType;
}
