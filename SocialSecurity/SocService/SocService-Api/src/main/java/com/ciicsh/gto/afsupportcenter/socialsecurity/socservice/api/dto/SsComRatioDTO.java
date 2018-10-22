package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.customer.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.customer.SsComTask;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SsComRatioDTO {
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 行业类别
     */
    private String industryCategory;
    /**
     * 工伤比例
     */
    private BigDecimal comRatio;
    /**
     * 开始年月
     */
    private String startMonth;
    /**
     * 结束年月
     */
    private String endMonth;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIndustryCategory() {
        return industryCategory;
    }

    public void setIndustryCategory(String industryCategory) {
        this.industryCategory = industryCategory;
    }

    public BigDecimal getComRatio() {
        return comRatio;
    }

    public void setComRatio(BigDecimal comRatio) {
        this.comRatio = comRatio;
    }

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
}