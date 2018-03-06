package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.enumeration;

/**
 * Created by houwanhua on 2018/3/5.
 */
public enum FundCategory {

    BASICFUND("DIT00057","基本公积金"),
    ADDFUND("DIT00058","补充公积金");

    FundCategory(String category, String categoryName){
        this.category = category;
        this.categoryName = categoryName;
    }

    private String category;
    private String categoryName;
    public String getCategory() {
        return category;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
