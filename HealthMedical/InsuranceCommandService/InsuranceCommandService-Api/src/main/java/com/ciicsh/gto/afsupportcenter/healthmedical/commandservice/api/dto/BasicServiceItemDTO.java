package com.ciicsh.gto.productcenter.commandservice.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangh
 * 服务项目表
 */
public class BasicServiceItemDTO implements Serializable {
    String basicServiceItemId;
    String basicProductId;
    String serviceItemName;
    String remark;
    String desc;
    String options;
    Boolean isActive;
    Date dataChangeCreateTime;
    Date dataChangeLastTime;
    String createdBy;
    String modifiedBy;

    public String getBasicServiceItemId() {
        return basicServiceItemId;
    }

    public void setBasicServiceItemId(String basicServiceItemId) {
        this.basicServiceItemId = basicServiceItemId;
    }

    public String getBasicProductId() {
        return basicProductId;
    }

    public void setBasicProductId(String basicProductId) {
        this.basicProductId = basicProductId;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    public void setDataChangeCreateTime(Date dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
    }

    public Date getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    public void setDataChangeLastTime(Date dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "BasicServiceItemDTO{" +
                "basicServiceItemId='" + basicServiceItemId + '\'' +
                ", basicProductId='" + basicProductId + '\'' +
                ", serviceItemName='" + serviceItemName + '\'' +
                ", remark='" + remark + '\'' +
                ", desc='" + desc + '\'' +
                ", options='" + options + '\'' +
                ", isActive=" + isActive +
                ", dataChangeCreateTime=" + dataChangeCreateTime +
                ", dataChangeLastTime=" + dataChangeLastTime +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
