package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;/**
 * Created by zhengj on 2018/5/14
 */

import java.math.BigDecimal;

/**
 * @author: zhengj
 * @date: 2018/5/14 19:38
 **/
public class SsEmpInfoDetailDTO {
    /**
     * 险种类型
     */
    private String ssType;
    /**
     * 险种名称
     */
    private String ssTypeName;
    /**
     * 个人部分金额
     */
    private BigDecimal empAmount;

    public String getSsType() {
        return ssType;
    }

    public void setSsType(String ssType) {
        this.ssType = ssType;
    }

    public String getSsTypeName() {
        return ssTypeName;
    }

    public void setSsTypeName(String ssTypeName) {
        this.ssTypeName = ssTypeName;
    }

    public BigDecimal getEmpAmount() {
        return empAmount;
    }

    public void setEmpAmount(BigDecimal empAmount) {
        this.empAmount = empAmount;
    }
}
