package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 13:20 2018/1/20
 */
public class SelectionUtils {

    public static String credentials(Integer i) {
        switch(i){
            case 1:
                return "积分办理";
            case 2:
                return "居住证B证";
            case 3:
                return "留学生落户";
            case 4:
                return "居转户";
            case 5:
                return "夫妻分居";
            case 6:
                return "人才引进";
            default:
                return "其他";
        }
    }

    public static String operateType(Integer i) {
        switch(i){
            case 1:
                return "待审代交";
            case 2:
                return "待审不代交";
            case 3:
                return "不待审代交";
            default:
                return "其他";
        }
    }

    public static String chargeType(Integer i) {
        switch(i){
            case 1:
                return "免费";
            case 2:
                return "常规收费";
            case 3:
                return "特殊收费";
            default:
                return "其他";
        }
    }

    public static String payType(Integer i) {
        switch(i){
            case 1:
                return "台账";
            case 2:
                return "员工自付";
            default:
                return "其他";
        }
    }
}
