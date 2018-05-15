package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;/**
 * Created by zhengj on 2018/5/14
 */

import java.math.BigDecimal;

/**
 * 公积金明细
 * @author: zhengj
 * @date: 2018/5/14 19:38
 **/
public class HfEmpInfoDetailDTO {
    /**
     * 类型1：基本公积金2：补充公积金
     */
    private String hfType;
    /**
     * 名称
     */
    private String hfTypeName;
    /**
     * 个人部分金额
     */
    private BigDecimal empAmount;

    public String getHfType() {
        return hfType;
    }

    public void setHfType(String hfType) {
        this.hfType = hfType;
    }

    public String getHfTypeName() {
        return hfTypeName;
    }

    public void setHfTypeName(String hfTypeName) {
        this.hfTypeName = hfTypeName;
    }

    public BigDecimal getEmpAmount() {
        return empAmount;
    }

    public void setEmpAmount(BigDecimal empAmount) {
        this.empAmount = empAmount;
    }
}
