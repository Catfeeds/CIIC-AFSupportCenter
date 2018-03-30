package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by zhangzhiwen on 2018/3/29.
 */
public class EmploymentDTO {
    /**
     * 实际录用日期
     */
    private LocalDate employDate;
    /**
     * 开AF单日期
     */
    private LocalDate openAfDate;
    /**
     * 用工形式
     */
    private String employStyle;
    /**
     * 办理类型
     */
    private String handleType;
    /**
     * 用工反馈
     */
    private String employFeedback;
    /**
     * 用工反馈操作日期
     */
    private LocalDate employFeedbackOptDate;
    /**
     * 用工操作员
     */
    private String employOperateMan;

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

    public String getEmployStyle() {
        return employStyle;
    }

    public void setEmployStyle(String employStyle) {
        this.employStyle = employStyle;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getEmployFeedback() {
        return employFeedback;
    }

    public void setEmployFeedback(String employFeedback) {
        this.employFeedback = employFeedback;
    }

    public LocalDate getEmployFeedbackOptDate() {
        return employFeedbackOptDate;
    }

    public void setEmployFeedbackOptDate(LocalDate employFeedbackOptDate) {
        this.employFeedbackOptDate = employFeedbackOptDate;
    }

    public String getEmployOperateMan() {
        return employOperateMan;
    }

    public void setEmployOperateMan(String employOperateMan) {
        this.employOperateMan = employOperateMan;
    }
}
