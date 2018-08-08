package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.SalCompany;
import lombok.Data;

/**
 * Created by zhangzhiwen on 2018/3/20.
 */
@Data
public class SalCompanyBO extends SalCompany {

    private  String  special;

    // 服务中心
    private String serviceCenter;

    // 客服经理
    private String salManagerName;

}
