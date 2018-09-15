package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto;

import java.util.Date;

/**
 * Created by zhangzhiwen on 2018/9/15.
 */
public class RemarkDTO {
    /**
     * 备注内容
     */
    private String remarkContent;
    /**
     * 操作员
     */
    private String remarkMan;
    /**
     * 备注时间
     */
    private Date remarkDate;

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public String getRemarkMan() {
        return remarkMan;
    }

    public void setRemarkMan(String remarkMan) {
        this.remarkMan = remarkMan;
    }

    public Date getRemarkDate() {
        return remarkDate;
    }

    public void setRemarkDate(Date remarkDate) {
        this.remarkDate = remarkDate;
    }
}
