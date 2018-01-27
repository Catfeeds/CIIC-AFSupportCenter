package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liuchuanhong on 2017/11/21.
 */
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = -4545751952113775050L;
    /**
     * 数据创建时间
     */
    private Date modifiedTime;

    /**
     * 数据最后修改时间
     */
    private Date createdTime;

    /**
     * 数据创建人
     */
    private String createdBy;

    /**
     * 数据最后修改人
     */
    private String modifiedBy;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
