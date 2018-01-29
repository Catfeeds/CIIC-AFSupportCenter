package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComAccountBo extends HfComAccount {

    private String hfType;
    private String hfComAccount;
    private String comStartMonth;
    private String endMonth;
    private String comHfMonth;
    private String addComAccount;
    private String companyId;
    private String title;
}
