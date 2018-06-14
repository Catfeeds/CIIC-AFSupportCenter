package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpPreInput;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@ExcelTarget("hfEmpPreInput")
@Data
public class HfEmpPreInputBO implements Serializable, IExcelModel {
    private static final long serialVersionUID = 1L;

    @Excel(name = "雇员编号", width = 20)
    @NotBlank(message = "雇员编号不能为空")
    @Length(max=16, message = "雇员编号最大长度为16位")
    private String employeeId;
    @Excel(name = "姓名", orderNum = "1", width = 15)
    @Length(max=100, message = "姓名最大长度为100位")
    private String employeeName;
    @Excel(name = "基本公积金账号", orderNum = "2", width = 25)
    @Length(max=20, message = "基本公积金账号最大长度为20位")
    private String hfEmpBasAccount;
    @Excel(name = "补充公积金账号", orderNum = "3", width = 25)
    @Length(max=20, message = "补充公积金账号最大长度为20位")
    private String hfEmpAddAccount;
    private HfEmpPreInput addHfEmpPreInput;
    private HfEmpPreInput modifyHfEmpPreInput;
    private String modifiedBy;
    private String errorMsg;

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
