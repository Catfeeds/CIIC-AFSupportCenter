package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.enumeration;

/**
 * Created by houwanhua on 2018/3/5.
 */
public enum TaskCategory {
    NEW(1," 新增(新开)"),
    NEWCHANGEINTO(2,"新增(转入)"),
    NEWREOPEN(3,"新增(启封)"),
    ADJUSTSEALED(4,"调整"),
    ADJUSTREOPEN(5,"待定"),
    REPAY(6,"补缴"),
    LEAVETURNOUT(7,"离职(转出)"),
    LEAVESEALED(8,"离职(封存)"),
    TRANSFER(9,"转移"),
    SPECIALTASK(10,"特殊任务"),
    TEAMCHANGEINTO(11,"集体转入"),
    TEAMTURNOUT(12,"集体转出"),
    FLOP(13,"翻牌");

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
