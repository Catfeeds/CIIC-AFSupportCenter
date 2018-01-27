package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfEmpArchiveDto  {
    private String companyId;
    private String title;
    private String hfAccountType;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private String hfEmpAccountBc;
    private String operationRemind;
    private String operationRemindDate;
}
