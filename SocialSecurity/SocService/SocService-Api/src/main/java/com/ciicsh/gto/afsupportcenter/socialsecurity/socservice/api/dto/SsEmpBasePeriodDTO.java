package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;/**
 * Created by zhengj on 2018/3/25
 */

import java.math.BigDecimal;

/**
 * @author: zhengj
 * @date: 2018/3/25 14:41
 **/
public class SsEmpBasePeriodDTO {
    /**
     * 起缴月份
     */
    private String startMonth;
    /**
     * 截止月份
     */
    private String endMonth;
    /**
     * 基数
     */
    private BigDecimal baseAmount;
    /**
     * 变更内容
     */
    private String chgContent;

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getChgContent() {
        return chgContent;
    }

    public void setChgContent(String chgContent) {
        this.chgContent = chgContent;
    }
}
