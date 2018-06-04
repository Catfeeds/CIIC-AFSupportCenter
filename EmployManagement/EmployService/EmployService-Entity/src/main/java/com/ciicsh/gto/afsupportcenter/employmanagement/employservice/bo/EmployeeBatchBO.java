package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by zhangzhiwen on 2018/5/24.
 */
public class EmployeeBatchBO {
    private List<Long> empTaskIds;

    private String employWay;
    private LocalDate receiveDate;

    private LocalDate employDate;

    private LocalDate openAfDate;

    private  String handleType;

    private String employStyle;

    private String employProperty;

    private String remarkContent;

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public LocalDate getEmployDate() {
        return employDate;
    }

    public void setEmployDate(LocalDate employDate) {
        this.employDate = employDate;
    }

    public LocalDate getOpenAfDate() {
        return openAfDate;
    }

    public void setOpenAfDate(LocalDate openAfDate) {
        this.openAfDate = openAfDate;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getEmployStyle() {
        return employStyle;
    }

    public void setEmployStyle(String employStyle) {
        this.employStyle = employStyle;
    }

    public String getEmployProperty() {
        return employProperty;
    }

    public void setEmployProperty(String employProperty) {
        this.employProperty = employProperty;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public List<Long> getEmpTaskIds() {
        return empTaskIds;
    }

    public void setEmpTaskIds(List<Long> empTaskIds) {
        this.empTaskIds = empTaskIds;
    }

    public String getEmployWay() {
        return employWay;
    }

    public void setEmployWay(String employWay) {
        this.employWay = employWay;
    }
}
