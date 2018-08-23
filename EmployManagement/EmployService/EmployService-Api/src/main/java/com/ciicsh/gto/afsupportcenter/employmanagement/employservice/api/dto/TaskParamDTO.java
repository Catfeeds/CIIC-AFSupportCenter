package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto;

/**
 * Created by zhangzhiwen on 2018/3/30.
 */
public class TaskParamDTO {

    private  String taskId;

    private String employeeId;

    private String empCompanyId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmpCompanyId() {
        return empCompanyId;
    }

    public void setEmpCompanyId(String empCompanyId) {
        this.empCompanyId = empCompanyId;
    }
}
