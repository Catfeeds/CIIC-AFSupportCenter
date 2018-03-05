package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.insuretask;

/**
 * <p>
 * 零星报销表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */

public class DaLuGaoDuanDTO extends CommonEntity {
    private String gender;
    private String age;
    private String brithday;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }
}
