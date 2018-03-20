package com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.enumeration;

/**
 *
 */
public enum ProcessCategory {

    AF_EMP_IN(1,"雇员新增"),
    AF_EMP_OUT(2,"雇员终止"),
    AF_EMP_MAKE_UP(3,"雇员补缴"),
    AF_EMP_COMPANY_CHANGE(4,"雇员翻牌"),
    AF_EMP_AGREEMENT_ADJUST(5,"雇员服务协议调整"),
    AF_EMP_AGREEMENT_UPDATE(6,"雇员服务协议更正");

    ProcessCategory(Integer category, String categoryName){
        this.category = category;
        this.categoryName = categoryName;
    }

    private Integer category;
    private String categoryName;
    public Integer getCategory() {
        return category;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
