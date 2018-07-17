package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;

public class SocialSecurityChangeInformationDTO {
    public String wageBase;
    public String executionDate;
    public String changeContent;

    public String getWageBase() {
        return wageBase;
    }

    public void setWageBase(String wageBase) {
        this.wageBase = wageBase;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }
}
