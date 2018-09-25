package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmCompanySet;
import lombok.Data;

/**
 * Created by zhangzhiwen on 2018/3/21.
 */
@Data
public class AmCompanySetBO extends AmCompanySet {

    private  String companyName;

    private  String organizationCode;

    /**
     * Ukey类别
     */
    private String keyType;
    /**
     * uKey编号
     */
    private String keyCode;
    /**
     * 档案部uKey密码
     */
    private String keyPwd;
    /**
     * Ukey序列号
     */
    private String keySeq;

    private String ssPwd;


}
