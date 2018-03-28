package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/27.
 */
public class AmMaterialBO {
    private  String  submitName;
    private  String  phone;
    private  List<AmEmpMaterialBO> materialsData;

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AmEmpMaterialBO> getMaterialsData() {
        return materialsData;
    }

    public void setMaterialsData(List<AmEmpMaterialBO> materialsData) {
        this.materialsData = materialsData;
    }
}
