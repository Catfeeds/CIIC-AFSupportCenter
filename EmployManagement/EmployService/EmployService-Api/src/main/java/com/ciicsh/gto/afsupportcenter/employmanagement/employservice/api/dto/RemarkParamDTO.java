package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto;

/**
 * Created by zhangzhiwen on 2018/9/15.
 */
public class RemarkParamDTO {

    private Long   empTaskId;
    /**
     * 1 用工备注 2 档案备注 3 退工备注 0 退工taskId查询档案备注
     */
    private Integer remarkType;

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public Integer getRemarkType() {
        return remarkType;
    }

    public void setRemarkType(Integer remarkType) {
        this.remarkType = remarkType;
    }
}
