package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import lombok.Data;

/**
 * Created by zhangzhiwen on 2018/3/1.
 */
@Data
public class AmTaskParamBO {

    private Long empTaskId;

    private Long empTaskResignId;

    private Long employmentId;

    private String employeeId;

    private String companyId;

    private String idNum;

    private Integer idCardType;

    private  String remarkType;

    private  boolean isResign;

}
