package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto;

import java.util.Date;

/**
 * Created by zhangzhiwen on 2018/4/10.
 */
public class MaterialDTO {
     /**
     * 材料名称
     */
    private String materialName;
    /**
     * 签收人
     */
    private String receiveName;
    /**
     * 签收日期
     */
    private Date receiveDate;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
}
