package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("SsYysReportBO")
public class SsYysReportBO {

    @Excel(name = "客户编号", width = 25, orderNum = "1")
    private String companyId;
    @Excel(name = "雇员编号", width = 20, orderNum = "2")
    private String employeeId;
    @Excel(name = "社保序号", width = 20, orderNum = "3")
    private Long ssSerial;
    @Excel(name = "姓名", width = 20, orderNum = "4")
    private String employeeName;
    @Excel(name = "身份证号", width = 20, orderNum = "5")
    private String idNum;
    @Excel(name = "结算区县", width = 20, orderNum = "6")
    private String settlementArea;
    @Excel(name = "人员属性", width = 20, orderNum = "7" ,replace = {"上海人_1", "外来城镇人员_2","外籍三险_3","外籍五险_4","非全日制仅工伤_5","柔性退休_6","工伤仅医疗_7","外籍互免_8"})
    private String empClassify;

    @Excel(name = "变更类型", orderNum = "8")
    private String changeTypeName;
    @Excel(name = "个人月缴纳基数",width = 20, orderNum = "9")
    private BigDecimal baseAmount;
    @Excel(name = "养老单位缴费额",width = 20, orderNum = "10")
    private BigDecimal comAmountPension;
    @Excel(name = "养老个人缴费额",width = 20, orderNum = "11")
    private BigDecimal empAmountPension;
    @Excel(name = "养老单位补缴", width = 20,orderNum = "12")
    private BigDecimal comCompensatedAmountPension;
    @Excel(name = "养老个人补缴", width = 20,orderNum = "13")
    private BigDecimal empCompensatedAmountPension;
    @Excel(name = "养老一次性支付", width = 20,orderNum = "14")
    private BigDecimal onePaymentPension;
    @Excel(name = "医疗单位缴费额", width = 20,orderNum = "15")
    private BigDecimal comAmountMedical;
    @Excel(name = "医疗个人缴费额", width = 20,orderNum = "16")
    private BigDecimal empAmountMedical;
    @Excel(name = "医疗单位补缴", width = 20,orderNum = "17")
    private BigDecimal comCompensatedAmountMedical;
    @Excel(name = "医疗个人补缴", width = 20,orderNum = "18")
    private BigDecimal empCompensatedAmountMedical;
    @Excel(name = "失业单位缴费额", width = 20,orderNum = "19")
    private BigDecimal comAmountUnemployment;
    @Excel(name = "失业个人缴费额", width = 20,orderNum = "20")
    private BigDecimal empAmountUnemployment;
    @Excel(name = "失业单位补缴", width = 20,orderNum = "21")
    private BigDecimal comCompensatedAmountUnemployment;
    @Excel(name = "失业个人补缴", width = 20,orderNum = "22")
    private BigDecimal empCompensatedAmountUnemployment;

    @Excel(name = "工伤单位缴费额", width = 20,orderNum = "23")
    private BigDecimal gsComAmount;
    @Excel(name = "工伤单位补缴", width = 20,orderNum = "24")
    private BigDecimal gsComRepayAmount;
    @Excel(name = "生育单位缴费额", width = 20,orderNum = "25")
    private BigDecimal syComAmount;
    @Excel(name = "生育单位补缴", width = 20,orderNum = "26")
    private BigDecimal syComRepayAmount;


    @Excel(name = "企业社保账户名称", width = 20, orderNum = "27")
    private String comAccountName;
    @Excel(name = "社保登记码", width = 20, orderNum = "28")
    private String ssAccount;
    @Excel(name = "服务中心", width = 30, orderNum = "29")
    private String serviceCenter;
    @Excel(name = "法人一证通密码", width = 25, orderNum = "30")
    private String ssPwd;

    private Integer category;





}
