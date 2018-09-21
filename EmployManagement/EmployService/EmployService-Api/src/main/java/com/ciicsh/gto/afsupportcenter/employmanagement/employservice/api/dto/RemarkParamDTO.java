package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto;

/**
 * Created by zhangzhiwen on 2018/9/15.
 */
public class RemarkParamDTO {
    
    private String   taskId;
    /**
     * 1 用工备注 2 档案备注 3 退工备注 0 退工taskId查询档案备注
     */
    private Integer remarkType;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getRemarkType() {
        return remarkType;
    }

    public void setRemarkType(Integer remarkType) {
        this.remarkType = remarkType;
    }
}
