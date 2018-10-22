package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@ExcelTarget("hfPaymentAccountReportBo")
@Data
public class HfPaymentAccountReportBo implements Serializable {
    private Integer hfType;
    private int fCount;
    private Long comAccountId;

    @Excel(name = "公积金类型", orderNum = "1", width = 12)
    private String hfTypeName;

    @Excel(name = "汇缴月份", orderNum = "2",  width = 12)
    private String paymentMonth;

    @Excel(name = "公司编号", orderNum = "3",  width = 40)
    private String companyId;

    @Excel(name = "公司名称", orderNum = "4",  width = 70)
    private String title;

    @Excel(name = "财务反馈状态", orderNum = "5",  width = 12 )
    private String comPaymentStatus;

    @Excel(name = "汇缴银行", orderNum = "6",  width = 40)
    private String paymentBankValue;

    @Excel(name = "汇缴金额", orderNum = "7",  width = 12 )
    private BigDecimal remittedAmount;

    @Excel(name = "补缴金额", orderNum = "8",  width = 12 )
    private BigDecimal repairAmount;

    @Excel(name = "汇缴人数", orderNum = "9",  width = 12 )
    private int remittedCountEmp;

}
