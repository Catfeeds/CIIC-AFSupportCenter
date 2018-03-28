package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpCustom;

/**
 * Created by zhangzhiwen on 2018/3/26.
 */
public class AmCustomBO extends AmEmpCustom {
    /**
     * 客户Id
     */
    private String companyId;

    private  String companyName;
    /**
     * 雇员服务专员
     */
    private String employeeCenterOperator;

    private  String cici;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCici() {
        return cici;
    }

    public void setCici(String cici) {
        this.cici = cici;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }


    public String getEmployeeCenterOperator() {
        return employeeCenterOperator;
    }

    public void setEmployeeCenterOperator(String employeeCenterOperator) {
        this.employeeCenterOperator = employeeCenterOperator;
    }

}
