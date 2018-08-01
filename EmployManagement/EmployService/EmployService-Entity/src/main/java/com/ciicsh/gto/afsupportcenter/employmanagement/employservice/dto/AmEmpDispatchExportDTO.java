package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import java.util.Date;

/**
 * Created by liyuelong on 2018/7/24 派遣录用名册打印DTO.
 */
public class AmEmpDispatchExportDTO{

    /**
     * 雇员名称
     */
    private String employeeName;

    /**
     * 身份证号码
     */
    private String idNum;

    /**
     * 劳动合同开始日期
     */
    private Date laborStartDate;

    /**
     * 劳动合结束日期
     */
    private Date laborEndDate;

    /**
     * 用工起始日期
     */
    private Date employmentStartDate;

    /**
     * 用工形式
     */
    private String employStyle;

    /**
     * 岗位 工种
     */
    private String position;

    /**
     * 劳务派遣
     */
    private String laborDispatch;

    /**
     * 派遣去向
     */
    private String sendOut;

    /**
     * 派遣期限
     */
    private String timeLimitForDispatch;

    /**
     * 备注, 办理类型
     */
    private String handleType;


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getLaborStartDate() {
        return laborStartDate;
    }

    public void setLaborStartDate(Date laborStartDate) {
        this.laborStartDate = laborStartDate;
    }

    public Date getLaborEndDate() {
        return laborEndDate;
    }

    public void setLaborEndDate(Date laborEndDate) {
        this.laborEndDate = laborEndDate;
    }

    public Date getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(Date employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public String getEmployStyle() {
        return employStyle;
    }

    public void setEmployStyle(String employStyle) {
        this.employStyle = employStyle;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLaborDispatch() {
        return laborDispatch;
    }

    public void setLaborDispatch(String laborDispatch) {
        this.laborDispatch = laborDispatch;
    }

    public String getSendOut() {
        return sendOut;
    }

    public void setSendOut(String sendOut) {
        this.sendOut = sendOut;
    }

    public String getTimeLimitForDispatch() {
        return timeLimitForDispatch;
    }

    public void setTimeLimitForDispatch(String timeLimitForDispatch) {
        this.timeLimitForDispatch = timeLimitForDispatch;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }
}
