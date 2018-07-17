package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto;

import java.util.Date;

/**
 * Created by zhangzhiwen on 2018/4/10.
 */
public class MaterialUpdateDTO {

    /**
     * 退工单主键
     */
    private Long empTaskId;
    /**
     * 签收人
     */
    private String receiveName;

    /**
     * 签收人Id
     */
    private String receiveId;
    /**
     * 签收日期
     */
    private Date receiveDate;

    /**
     * 批退人ID
     */
    private String rejectId;

    /**
     * 批退人姓名
     */
    private String rejectName;

    /**
     * 批退日期
     */
    private Date rejectDate;



    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
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

    public String getRejectId() {
        return rejectId;
    }

    public void setRejectId(String rejectId) {
        this.rejectId = rejectId;
    }

    public String getRejectName() {
        return rejectName;
    }

    public void setRejectName(String rejectName) {
        this.rejectName = rejectName;
    }

    public Date getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Date rejectDate) {
        this.rejectDate = rejectDate;
    }
}
