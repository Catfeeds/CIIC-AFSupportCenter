package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/27.
 */
public class AmMaterialBO {
    private  String  submitName;
    private  String  extension;
    private  List<AmEmpMaterialBO> materialsData;

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public List<AmEmpMaterialBO> getMaterialsData() {
        return materialsData;
    }

    public void setMaterialsData(List<AmEmpMaterialBO> materialsData) {
        this.materialsData = materialsData;
    }
}
