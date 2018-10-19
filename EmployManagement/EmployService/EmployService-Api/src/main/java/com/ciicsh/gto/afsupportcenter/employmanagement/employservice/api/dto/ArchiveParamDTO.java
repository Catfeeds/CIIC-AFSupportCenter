package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto;

/**
 * Created by zhangzhiwen on 2018/10/17.
 */
public class ArchiveParamDTO {
    private Long   empTaskId;
    private Boolean hire;

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public Boolean getHire() {
        return hire;
    }

    public void setHire(Boolean hire) {
        this.hire = hire;
    }
}
