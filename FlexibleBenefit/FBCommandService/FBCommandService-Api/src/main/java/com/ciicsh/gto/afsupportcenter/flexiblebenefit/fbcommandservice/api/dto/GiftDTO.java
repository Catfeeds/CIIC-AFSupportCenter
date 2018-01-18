package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiweizhen
 * @date 2017/12/6 10:47
 */
public class GiftDTO {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 礼品名称
     */
    private String giftName;
    /**
     * 礼品价格
     */
    private BigDecimal price;
    /**
     * 适用人群：男士适用0，女士适用1，男女通用2
     */
    private Integer rightPerson;
    /**
     * 礼品类型：票券0,办公用品1,生活用品,2，食品3，饰品4，数码周边5，儿童用品6
     */
    private Integer giftType;
    /**
     * 礼品颜色
     */
    private String color;
    /**
     * 礼品数量
     */
    private Integer number;
    /**
     * 限制申请的最大数量
     */
    private Integer applyMaxnum;
    /**
     * 图片地址
     */
    private String pictureUrl;
    /**
     * 礼品介绍
     */
    private String remarks;
    /**
     * 0，正常；1，已下架
     */
    private Integer status;
    /**
     * 是否new标识-0
     */
    private Boolean newTag;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRightPerson() {
        return rightPerson;
    }

    public void setRightPerson(Integer rightPerson) {
        this.rightPerson = rightPerson;
    }

    public Integer getGiftType() {
        return giftType;
    }

    public void setGiftType(Integer giftType) {
        this.giftType = giftType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getApplyMaxnum() {
        return applyMaxnum;
    }

    public void setApplyMaxnum(Integer applyMaxnum) {
        this.applyMaxnum = applyMaxnum;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getNewTag() {
        return newTag;
    }

    public void setNewTag(Boolean newTag) {
        this.newTag = newTag;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
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

    @Override
    public String toString() {
        return "GiftDTO{" +
            "id=" + id +
            ", giftName='" + giftName + '\'' +
            ", price=" + price +
            ", rightPerson=" + rightPerson +
            ", giftType=" + giftType +
            ", color='" + color + '\'' +
            ", number=" + number +
            ", applyMaxnum=" + applyMaxnum +
            ", pictureUrl='" + pictureUrl + '\'' +
            ", remarks='" + remarks + '\'' +
            ", status=" + status +
            ", newTag=" + newTag +
            ", isActive=" + isActive +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}