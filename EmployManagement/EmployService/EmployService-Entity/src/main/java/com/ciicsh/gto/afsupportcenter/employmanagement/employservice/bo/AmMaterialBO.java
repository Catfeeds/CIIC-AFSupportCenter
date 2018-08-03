package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/27.
 */
@Data
public class AmMaterialBO {
    private  String  submitName;
    private  String  extension;
    private String reasonValue;
    private  List<AmEmpMaterialBO> materialsData;

    // 用工材料操作记录
    private List<AmEmpMaterialOperationLogBO> logBOList;

}
