package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfStatementComparePO;

/**
 * 公积金对账记录业务实体
 */
public class HFStatementCompareBO extends HfStatementComparePO
{
    /**
     * 企业公积金账户名称
     */
    private String comAccountName;


    /**
     * 企业公积金账户编号
     */
    private String hfComAccount;


    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public String getHfComAccount() {
        return hfComAccount;
    }

    public void setHfComAccount(String hfComAccount) {
        this.hfComAccount = hfComAccount;
    }
}
