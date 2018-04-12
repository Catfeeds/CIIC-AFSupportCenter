package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

/**
 * Created by zhangzhiwen on 2018/3/1.
 */
public class AmTaskParamBO {

    private Long empTaskId;

    private Long empTaskResignId;

    private Long employmentId;

    private String employeeId;

    private String companyId;

    private String idNum;

    private Integer idCardType;

    private  String remarkType;

    private  boolean isResign;

    public Long getEmpTaskResignId() {
        return empTaskResignId;
    }

    public void setEmpTaskResignId(Long empTaskResignId) {
        this.empTaskResignId = empTaskResignId;
    }

    public boolean isResign() {
        return isResign;
    }

    public void setResign(boolean resign) {
        isResign = resign;
    }

    public Long getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(Long employmentId) {
        this.employmentId = employmentId;
    }

    public String getRemarkType() {
        return remarkType;
    }

    public void setRemarkType(String remarkType) {
        this.remarkType = remarkType;
    }

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Integer getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(Integer idCardType) {
        this.idCardType = idCardType;
    }
}
