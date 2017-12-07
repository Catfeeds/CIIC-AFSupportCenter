package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiweizhen
 * @date 2017/12/6 10:47
 */
public class GiftDTO {
    private Integer id;

    private String giftName;

    private BigDecimal price;

    private Byte rightPerson;

    private Byte giftType;

    private String color;

    private Integer number;

    private Integer applyMaxnum;

    private String pictureUrl;

    private String remarks;

    private Byte status;

    private Boolean isNew;

    private Boolean isActive;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;

    private String createdBy;

    private String modifiedBy;


    @Override
    public String toString() {
        return "GiftPO{" +
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
                ", isNew=" + isNew +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }


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
        this.giftName = giftName == null ? null : giftName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Byte getRightPerson() {
        return rightPerson;
    }

    public void setRightPerson(Byte rightPerson) {
        this.rightPerson = rightPerson;
    }

    public Byte getGiftType() {
        return giftType;
    }

    public void setGiftType(Byte giftType) {
        this.giftType = giftType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
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
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
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
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}