package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/27.
 */
public class AmMaterialBO {
    private  String  submitName;
    private  String  extension;
    private String reasonValue;
    private  List<AmEmpMaterialBO> materialsData;

    // 用工材料操作记录
    private List<AmEmpMaterialOperationLogBO> logBOList;

    public String getReasonValue() {
        return reasonValue;
    }

    public void setReasonValue(String reasonValue) {
        this.reasonValue = reasonValue;
    }

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

    public List<AmEmpMaterialOperationLogBO> getLogBOList() {
        return logBOList;
    }

    public void setLogBOList(List<AmEmpMaterialOperationLogBO> logBOList) {
        this.logBOList = logBOList;
    }
}
