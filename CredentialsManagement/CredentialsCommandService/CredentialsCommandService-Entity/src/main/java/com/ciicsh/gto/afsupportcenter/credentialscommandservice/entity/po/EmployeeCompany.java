package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:35 2018/2/12
 */
@TableName("vw_employee_company")
public class EmployeeCompany extends Model<EmployeeCompany> {

    /**
     * 雇员编号
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 雇员姓名
     */
    @TableField("employee_name")
    private String employeeName;
    /**
     * 身份证号码
     */
    @TableField("id_num")
    private String idNum;
    /**
     * 客户编号
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 客户名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 入离职状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 雇员类型 1：af 2：bpo 3：fc
     */
    @TableField("type")
    private String type;

    @Override
    protected Serializable pkVal() {
        return employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EmployeeCompany{" +
            "employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", idNum='" + idNum + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", type='" + type + '\'' +
            ", status=" + status +
            '}';
    }
}
