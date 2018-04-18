package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class AmDataauthCompanyBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主建，自增ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 服务中心ID
     */
    private Long serviceCenterId;

    /**
     * 客户ID
     */
    private String companyId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String modifiedBy;
}
