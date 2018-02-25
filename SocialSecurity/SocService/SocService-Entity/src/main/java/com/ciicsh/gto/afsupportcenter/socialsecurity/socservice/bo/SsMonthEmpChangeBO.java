package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthEmpChange;

/**
 * <p>
 * 雇员月度变更主表
 * </p>
 *
 * @author wengxk
 * @since 2017-12-07
 */
public class SsMonthEmpChangeBO extends SsMonthEmpChange {

    private static final long serialVersionUID = 1L;

    /**
     * 外键, 企业社保账户名
     */
    private String comAccountName;

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }
}
