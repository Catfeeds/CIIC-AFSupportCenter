package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;

public class FundChangeInformationDTO {
    public String wageBase;
    public String fundType;
    public String executionDate;
    public String changeContent;

    public String getWageBase() {
        return wageBase;
    }

    public void setWageBase(String wageBase) {
        this.wageBase = wageBase;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
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
