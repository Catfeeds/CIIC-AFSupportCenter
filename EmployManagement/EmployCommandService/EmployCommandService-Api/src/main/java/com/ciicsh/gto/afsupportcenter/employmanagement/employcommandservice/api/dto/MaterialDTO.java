package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
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
     * 签收人Id
     */
    private String receiveId;


    private  String  receiveDateStr;

    public String getReceiveDateStr() {
        return receiveDateStr;
    }

    public void setReceiveDateStr(String receiveDateStr) {
        this.receiveDateStr = receiveDateStr;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

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


}
