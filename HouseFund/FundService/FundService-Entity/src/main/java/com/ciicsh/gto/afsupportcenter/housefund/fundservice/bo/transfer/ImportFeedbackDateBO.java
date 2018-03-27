package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@ExcelTarget("importFeedbackDate")
@Data
public class ImportFeedbackDateBO implements Serializable, IExcelModel {
    private static final long serialVersionUID = 1L;

    @Excel(name = "雇员编号" )
    @NotBlank(message = "雇员编号不能为空")
    private String employeeId;
    @Excel(name = "姓名", orderNum = "1")
    @NotBlank(message = "姓名不能为空")
    private String employeeName;
    @Excel(name = "回单日期", orderNum = "2")
    @NotBlank(message = "回单日期不能为空")
    private String feedbackDate;
    private String modifiedBy;
    private List<Long> empTaskIds;
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
