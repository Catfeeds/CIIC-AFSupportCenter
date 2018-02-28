package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto;

import java.io.Serializable;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:25 2018/2/28
 */
public class ExpCompanyDTO implements Serializable {

    private String companyId;

    private String title;

    private String productId;

    private String basicProductId;

    private String productName;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBasicProductId() {
        return basicProductId;
    }

    public void setBasicProductId(String basicProductId) {
        this.basicProductId = basicProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ExpCompanyDTO{" +
            "companyId='" + companyId + '\'' +
            ", title='" + title + '\'' +
            ", productId='" + productId + '\'' +
            ", basicProductId='" + basicProductId + '\'' +
            ", productName='" + productName + '\'' +
            '}';
    }
}
