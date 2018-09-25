package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.SalCompany;
import lombok.Data;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/20.
 */
@Data
public class SalCompanyBO extends SalCompany {

    private  String  special;

    // 服务中心
    private String serviceCenter;

    // 客服经理
    private String leaderName;

    private String employeeName;

    private  String employeeId;

    private String idNum;

    private String hireUnit;

    private Integer count;

    private String params;

    private Integer status;

    private List<String> param;

    private Integer job;

    private Boolean archiveAble;

    private Boolean checked=false;

}
