package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class SsDataauthWelfareUnitBO implements Serializable {

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
     * 福利办理方ID
     */
    private Integer welfareUnit;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
