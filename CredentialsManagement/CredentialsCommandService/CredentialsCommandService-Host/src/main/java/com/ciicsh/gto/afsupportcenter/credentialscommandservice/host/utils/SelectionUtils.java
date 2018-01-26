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

    public static String credentialsDeal(Integer i) {
        switch(i){
            case 1:
                return "积分申请";
            case 2:
                return "积分确认";
            case 3:
                return "信息变更";
            case 4:
                return "随员";
            case 5:
                return "重置密码";
            case 6:
                return "新办";
            case 7:
                return "继办";
            case 8:
                return "随员";
            case 9:
                return "个人信息修改";
            case 10:
                return "挂失";
            case 11:
                return "补办";
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
