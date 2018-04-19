package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer;

/**
 * Created by houwanhua on 2018/4/19.
 */
public class EmpTaskStatusBO {
    private Long empTaskId;
    private Long empArchiveId;
    private Integer taskCategory;

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public Long getEmpArchiveId() {
        return empArchiveId;
    }

    public void setEmpArchiveId(Long empArchiveId) {
        this.empArchiveId = empArchiveId;
    }

    public Integer getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(Integer taskCategory) {
        this.taskCategory = taskCategory;
    }
}
