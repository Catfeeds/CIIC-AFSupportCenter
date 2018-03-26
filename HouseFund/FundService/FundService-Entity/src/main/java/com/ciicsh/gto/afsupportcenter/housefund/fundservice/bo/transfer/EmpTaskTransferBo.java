package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ExcelTarget("EmpTaskTransfer")
public class EmpTaskTransferBo {

    private Long empTaskId;
    private Integer hfType;
    private String companyId;
    private String title;
    private String employeeId;
    @Excel(name = "雇员姓名", width = 20)
    private String employeeName;
    private String idNum;
    @Excel(name = "公积金账号", orderNum = "1", width = 20)
    private String hfEmpAccount;
    private Integer status;
    private Integer taskStatus;
    private Integer archiveStatus;
    private Date inDate;
    private String submitTime;
    private String submitterId;
    private String comAccountId;
    private Integer ssAccountType;
    private Long empArchiveId;
    private Integer processCategory;
    private Integer taskCategory;
    @Excel(name = "转出单位名称", orderNum = "4", width = 20)
    private String transferOutUnit;
    @Excel(name = "转出单位账号", orderNum = "5", width = 20)
    private String transferOutUnitAccount;
    @Excel(name = "转入单位名称", orderNum = "2", width = 20)
    private String transferInUnit;
    @Excel(name = "转入单位名称", orderNum = "3", width = 20)
    private String transferInUnitAccount;
    private Date transferDate;
    @Excel(name = "转移日期", orderNum = "6", width = 20)
    private String transferDateFormat;
    private Date feedbackDate;
    private Date operateDate;
    private String handleRemark;
    private Integer paymentBank;

    public String getTransferDateFormat() {
        if (this.transferDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(this.transferDate);
        }
        return transferDateFormat;
    }
}
