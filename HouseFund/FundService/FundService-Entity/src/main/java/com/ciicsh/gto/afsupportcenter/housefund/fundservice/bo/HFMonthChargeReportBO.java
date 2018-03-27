package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@ExcelTarget("hfMonthChargeReport")
@Data
public class HFMonthChargeReportBO implements Serializable {
    private Integer hfType;
    @Excel(name = "公积金类型",  width = 12)
    private String hfTypeName;
    @Excel(name = "雇员编号", orderNum = "1",  width = 15)
    private String employeeId;
    @Excel(name = "雇员姓名", orderNum = "2",  width = 15)
    private String employeeName;
    @Excel(name = "公积金账号", orderNum = "3",  width = 18)
    private String hfEmpAccount;
    @Excel(name = "证件号", orderNum = "4",  width = 25)
    private String idNum;
    @Excel(name = "缴费月份", orderNum = "5",  width = 10)
    private String hfMonth;
    @Excel(name = "所属公积金月份", orderNum = "6",  width = 15)
    private String ssMonthBelong;
    private Integer paymentType;
    @Excel(name = "缴费类型", orderNum = "7",  width = 10)
    private String paymentTypeName;
    @Excel(name = "公积金基数", orderNum = "8",  width = 12)
    private BigDecimal base;
    @Excel(name = "公积金比例", orderNum = "9",  width = 12)
    private BigDecimal ratio;
    @Excel(name = "公积金金额", orderNum = "10",  width = 12)
    private BigDecimal amount;
    @Excel(name = "客户编号", orderNum = "11",  width = 15)
    private String companyId;
    @Excel(name = "客户姓名", orderNum = "12",  width = 25)
    private String companyName;
    @Excel(name = "公司公积金账号", orderNum = "13",  width = 18)
    private String hfComAccount;
    private Integer repairReason;
    private String basicHfEmpAccount;

    public String getHfTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.hfType), SocialSecurityConst.FUND_TYPE_KEY, true);
    }

    public String getPaymentTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.paymentType), SocialSecurityConst.PAYMENT_TYPE_KEY, true);
    }
}
