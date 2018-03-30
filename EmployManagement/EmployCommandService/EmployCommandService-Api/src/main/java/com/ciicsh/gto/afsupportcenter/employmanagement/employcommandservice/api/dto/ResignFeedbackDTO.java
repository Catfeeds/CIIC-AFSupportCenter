package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto;

import java.time.LocalDateTime;

/**
 * Created by zhangzhiwen on 2018/3/30.
 */
public class ResignFeedbackDTO {
    /**
     * 退工反馈
     */
    private String resignFeedback;

    /**
     * 退工操作员
     */
    private String resignOperateMan;
    /**
     * 操作日期
     */
    private LocalDateTime createdTime;

    public String getResignFeedback() {
        return resignFeedback;
    }

    public void setResignFeedback(String resignFeedback) {
        this.resignFeedback = resignFeedback;
    }

    public String getResignOperateMan() {
        return resignOperateMan;
    }

    public void setResignOperateMan(String resignOperateMan) {
        this.resignOperateMan = resignOperateMan;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
