package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpMaterial;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/1/26.
 */
public class AmEmpMaterialBO extends AmEmpMaterial {

    private List<Long> empTaskIdList;
    private  String  type;

    public List<Long> getEmpTaskIdList() {
        return empTaskIdList;
    }

    public void setEmpTaskIdList(List<Long> empTaskIdList) {
        this.empTaskIdList = empTaskIdList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
