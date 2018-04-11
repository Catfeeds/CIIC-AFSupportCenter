package com.ciicsh.gto.afsupportcenter.credentialscommandservice.enums;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:02 2018/4/3
 */
public interface Constants {

    enum credentialsTypes {
        INTEGRALDEAL(1, "积分办理"),
        RESIDENCEPERMIT(2, "居住证B证"),
        STUDENTABROADSETTLE(3, "留学生落户"),
        RESIDENCETRANSFER(4, "已完成"),
        SPOUSELIVEAPART(5, "已退票"),
        TALENTINTRODUCTION(6, "已退票");

        private Integer code;
        private String name;
        credentialsTypes(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
        public Integer getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
    }
}
