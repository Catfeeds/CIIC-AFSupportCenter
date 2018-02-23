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
                return "单添随员";
            case 5:
                return "新办";
            case 6:
                return "继办";
            case 7:
                return "单位信息变更";
            case 8:
                return "随员";
            case 9:
                return "个人信息修改";
            case 10:
                return "挂失和补办";
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

    public static String afStatus(Integer i) {
        switch(i){
            case 0:
                return "预录用";
            case 1:
                return "雇员信息确认中";
            case 2:
                return "在职";
            case 3:
                return "离职";
            case 4:
                return "取消入职";
            default:
                return "其他";
        }
    }

    public static String bpoStatus(Integer i) {
        switch(i){
            case 0:
                return "预增";
            case 1:
                return "报入职";
            case 2:
                return "在职";
            case 3:
                return "报离职";
            case 4:
                return "离职";
            default:
                return "其他";
        }
    }

    public static String fcStatus(Integer i) {
        switch(i){
            case 0:
                return "离职";
            case 1:
                return "在职";
            default:
                return "其他";
        }
    }

}
