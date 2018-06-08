package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by houwanhua on 2018/2/5.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountCompanyRelationOpt {
    private Long comAccountId;
    private String ssAccount;
    private String comAccountName;
    private String companyId;
    private String companyName;
    private String companyIds;
}
