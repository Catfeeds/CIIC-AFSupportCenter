package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

import java.math.BigDecimal;

public class HfEmpTaskDetailInfoDTO {
    private String idNum;
    private String remark;
    private BigDecimal amount;
    private String account;
    private String status;
    private String startDate;
    private String handleDate;
    private String stopDate;
    private String stopHandleDate;

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getStopHandleDate() {
        return stopHandleDate;
    }

    public void setStopHandleDate(String stopHandleDate) {
        this.stopHandleDate = stopHandleDate;
    }
}
