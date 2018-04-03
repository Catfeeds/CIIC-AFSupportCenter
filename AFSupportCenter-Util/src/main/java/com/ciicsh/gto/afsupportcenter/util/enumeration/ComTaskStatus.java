package com.ciicsh.gto.afsupportcenter.util.enumeration;

/**
 *
 */
public enum ComTaskStatus {

    STATUS_0(0,"未受理"),
    STATUS_1(1,"受理中"),
    STATUS_2(2,"送审中"),
    STATUS_3(3,"已完成"),
    STATUS_4(4,"批退");

    ComTaskStatus(Integer key, String value){
        this.key = key;
        this.value = value;
    };

    public static String getValue(int status) {
        for (ComTaskStatus c : ComTaskStatus.values()) {
            if (c.getKey() == status) {
                return c.value;
            }
        }
        return null;
    }

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
