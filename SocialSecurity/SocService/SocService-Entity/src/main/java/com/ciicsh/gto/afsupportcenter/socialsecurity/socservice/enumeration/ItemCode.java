package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.enumeration;

public enum  ItemCode {

    SOCIAL_ITEM_CODE("DIT00136","社保"),
    FOUND_ITEM_CODE("DIT00137","公积金");

    private String itemCode;
    private String itemName;
    ItemCode(String itemCode,String itemName){
        this.itemCode = itemCode;
        this.itemName = itemName;
    }
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
