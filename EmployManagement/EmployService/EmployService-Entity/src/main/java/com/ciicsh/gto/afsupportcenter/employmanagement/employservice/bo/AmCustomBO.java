package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;


import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpCustom;
import lombok.Data;

/**
 * Created by zhangzhiwen on 2018/3/26.
 */
@Data
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

    private  String ciCi;

    private Integer employCode;

}
