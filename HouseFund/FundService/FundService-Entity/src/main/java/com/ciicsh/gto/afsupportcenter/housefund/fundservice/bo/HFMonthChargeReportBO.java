package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HFMonthChargeReportBO implements Serializable {
    private Integer hfType;
    private String hfTypeName;
    private String employeeId;
    private String employeeName;
    private String hfEmpAccount;
    private String idNum;
    private LocalDate hfMonth;
    private LocalDate ssMonthBelong;
    private Integer paymentType;
    private String paymentTypeName;
    private BigDecimal base;
    private BigDecimal ratio;
    private BigDecimal amount;
    private String companyId;
    private String companyName;
    private String hfComAccount;

    public String getHfTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.hfType), SocialSecurityConst.FUND_TYPE_KEY, true);
    }

    public String getPaymentTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.paymentType), SocialSecurityConst.PAYMENT_TYPE_KEY, true);
    }
}
