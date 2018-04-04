package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 17:56 2018/4/3
 */
public class TaskTypeDTO {

    private String taskTypeId;
    private String taskTypeCode;
    private String taskTypeName;
    private String basicProductId;
    private String level;
    private String pid;

    public String getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(String taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getBasicProductId() {
        return basicProductId;
    }

    public void setBasicProductId(String basicProductId) {
        this.basicProductId = basicProductId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "TaskTypeDTO{" +
            "taskTypeId='" + taskTypeId + '\'' +
            ", taskTypeCode='" + taskTypeCode + '\'' +
            ", taskTypeName='" + taskTypeName + '\'' +
            ", basicProductId='" + basicProductId + '\'' +
            ", level='" + level + '\'' +
            ", pid='" + pid + '\'' +
            '}';
    }
}
