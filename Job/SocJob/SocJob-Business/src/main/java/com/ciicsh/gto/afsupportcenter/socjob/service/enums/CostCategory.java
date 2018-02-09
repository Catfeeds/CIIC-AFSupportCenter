package com.ciicsh.gto.afsupportcenter.socjob.service.enums;

/**
 * Created by houwanhua on 2018/2/6.
 */
public enum CostCategory {
    STANDARD(1,"标准"),
    NEW(2,"新进"),
    CHANGEINTO(3,"转入"),
    REPPAYMENT(4,"补缴"),
    SEQADJUST(5,"调整(顺调)"),
    OUT(6,"转出"),
    SEALED(7,"封存"),
    REFUND(8,"退账"),
    INVERSEADJUST(9,"调整(逆调)");


    private Integer category;
    private String categoryName;


    CostCategory(Integer category, String categoryName) {
        this.category = category;
        this.categoryName = categoryName;
    }

    public Integer getCategory() {
        return category;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
