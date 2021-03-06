package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.enumeration;

/**
 * Created by houwanhua on 2018/3/5.
 */
public enum TaskCategory {
    NEW(1," 新开"),
    CHANGEINTO(2,"转入"),
    REOPEN(3,"启封"),
    TURNOUT(4,"转出"),
    SEALED(5,"封存"),
    REPAY(6,"补缴"),
    ADJUST(7,"调整"),
    TRANSFER(8,"转移"),
    FLOPNEW(9, "翻牌新开"),
    FLOPINTO(10, "翻牌转入"),
    FLOPREOPEN(11, "翻牌启封"),
    FLOPOUT(12, "翻牌转出"),
    FLOPSEALED(13, "翻牌封存"),
    NOHANDLE(99, "不做");

    TaskCategory(Integer category, String categoryName){
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
