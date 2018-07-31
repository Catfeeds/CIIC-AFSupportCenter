package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@ExcelTarget("HfRimittedBookReportBO")
@Data
public class HfRimittedBookReportBO implements Serializable {

    private Long comAccountClassId;


    private String paymentYear;

    @Excel(name = "公积金类型", orderNum = "1", replace = {"基本公积金_1","补充公积金_2"},width = 12)
    private Integer hfType;
    @Excel(name = "企业账户名称", orderNum = "2", width = 30)
    private String comAccountName;
    @Excel(name = "客户编号", orderNum = "3", width = 12)
    private String companyId;
    @Excel(name = "缴费月份", orderNum = "4", width = 12)
    private String paymentMonth;

    @Excel(name = "上月汇缴金额", orderNum = "5", width = 12)
    private BigDecimal remittedAmountLast;//上月汇缴金额
    @Excel(name = "本月增加汇缴金额", orderNum = "6", width = 12)
    private BigDecimal remittedAmountAdd;//本月增加金额
    @Excel(name = "本月减少汇缴金额", orderNum = "7", width = 12)
    private BigDecimal remittedAmountReduce;//本月减少金额
    private String paymentBank;
    @Excel(name = "本月汇缴金额", orderNum = "8", width = 12)
    private BigDecimal remittedAmount;//本月汇缴金额
    @Excel(name = "补缴金额", orderNum = "9", width = 12)
    private BigDecimal repairAmount;//补缴金额
    private String curdate;
    private Integer curYear;
    private Integer curMonth;
    private Integer curDay;
    private String bankName;

    @Excel(name = "上月汇缴人数", orderNum = "10", width = 12)
    private BigDecimal remittedCountEmpLast;//上月汇缴人数

    private String remittedAmountArrange;//本月汇缴金额矩阵
    @Excel(name = "本月增加人数", orderNum = "11", width = 12)
    private BigDecimal remittedCountEmpAdd;//本月增加人数
    @Excel(name = "本月减少人数", orderNum = "12", width = 12)
    private BigDecimal remittedCountEmpReduce;//本月减少人数
    @Excel(name = "本月汇缴人数", orderNum = "13", width = 12)
    private Integer remittedCountEmp;//本月汇缴人数

    @Excel(name = "补缴人数", orderNum = "14", width = 12)
    private Integer repairCountEmp;//补缴人数

    @Excel(name = "企业公积金账号", orderNum = "15", width = 12)
    private String hfComAccount;
    @Excel(name = "企业账户类型", orderNum = "16",replace = {"中智大库_1","中智外包_2","独立户_3"}, width = 12)
    private Integer hfAccountType;
    private Boolean isRemitted;//打钩汇缴
    private Boolean isRepair;//打钩补缴
    private Integer pageNum;
    private String moneyCN;
}
