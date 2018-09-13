package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccount;
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
    private String accountTempStoreBc;
    private String accountTempStore;
    private Integer closeDay;
    private String serviceSpecialist;
    private String leaderShipName;
    private String serviceCenter;
}
