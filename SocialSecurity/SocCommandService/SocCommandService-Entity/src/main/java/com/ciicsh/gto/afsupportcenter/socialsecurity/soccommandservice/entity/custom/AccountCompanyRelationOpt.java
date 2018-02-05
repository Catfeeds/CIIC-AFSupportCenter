package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by houwanhua on 2018/2/5.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountCompanyRelationOpt {
    private Long comAccountId;
    private String comAccountName;
    private String companyId;
    private String companyName;
}
