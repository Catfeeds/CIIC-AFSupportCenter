package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class SsAccountRatioBO {

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

}