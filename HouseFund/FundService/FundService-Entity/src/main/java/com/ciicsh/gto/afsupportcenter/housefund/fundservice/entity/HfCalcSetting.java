package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("hf_calc_setting")
public class HfCalcSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="calc_setting_id", type= IdType.AUTO)
    private Long calcSettingId;
    /**
     * 开始生效年月
     */
    @TableField("start_month")
    private String startMonth;
    /**
     * 所属城市编码
     */
    @TableField("city_code")
    private String cityCode;
    /**
     * 缴纳部分 1-企业 2-个人
     */
    @TableField("payment_part")
    private Integer paymentPart;
    /**
     * 公积金类型
     */
    @TableField("hf_type")
    private String hfType;
    /**
     * 小数进位方式
     */
    @TableField("round_type")
    private Integer roundType;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private LocalDateTime modifiedTime;
    /**
     * 创建者登录名
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 修改者登录名
     */
    @TableField("modified_by")
    private String modifiedBy;

    public Long getCalcSettingId() {
        return calcSettingId;
    }

    public void setCalcSettingId(Long calcSettingId) {
        this.calcSettingId = calcSettingId;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getPaymentPart() {
        return paymentPart;
    }

    public void setPaymentPart(Integer paymentPart) {
        this.paymentPart = paymentPart;
    }

    public String getHfType() {
        return hfType;
    }

    public void setHfType(String hfType) {
        this.hfType = hfType;
    }

    public Integer getRoundType() {
        return roundType;
    }

    public void setRoundType(Integer roundType) {
        this.roundType = roundType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
