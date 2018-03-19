package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.enumeration;

/**
 * Created by houwanhua on 2018/3/19.
 */
public enum ProcessCategory {

    EMPLOYEENEW(1,"雇员新增"),
    EMPLOYEESTOP(2,"雇员终止"),
    EMPLOYEEREPAY(3,"雇员补缴"),
    EMPLOYEEFLOP(4,"雇员翻牌"),
    EMPLOYEEAGREEMENTADJUST(5,"雇员服务协议调整"),
    EMPLOYEEAGREEMENTCORRECT(6,"雇员服务协议更正"),
    COLLECTIVEOPERATION(7,"集体操作"),
    SPECIALOPERATION(8,"特殊操作"),
    OTHERPERATION(9,"特殊操作");

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
