package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto;

import java.io.Serializable;
import java.util.Map;

public class TaskSheetRequestDTO implements Serializable {

    private static final long serialVersionUID = -4304052210316428011L;

    private String taskId;
    private String assignee;
    private Map<String,Object> variable;

    public TaskSheetRequestDTO() {
    }

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Map<String, Object> getVariable() {
		return variable;
	}

	public void setVariable(Map<String, Object> variable) {
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "TaskSheetRequestDTO{" +"taskId='" + taskId + ", assignee='" + assignee + ", variable=" + variable +'}';
	}
}
