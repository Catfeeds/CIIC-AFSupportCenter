package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpEmployee;
import lombok.Data;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/4/9.
 */
@Data
public class AmEmpEmployeeBO extends AmEmpEmployee {

    private  String sex;

    /**
     * 是否无期限合同
     */
    private String isUnlimitedContract;
    /**
     * 派遣年限
     */
    private String sendCondemnationYears;

    /**
     * 人员性质
     */
    private String employeeNature;
    /**
     * 档案方向
     */
    private String archiveDirection;
    /**
     * 用工属性
     */
    private String employProperty;

    /**
     * 用工公司特殊情况
     */
    private String employSpecial;

    /**
     * Ukey类别
     */
    private String keyType;
    /**
     * Ukey编码
     */
    private String keyCode;
    /**
     * Ukey密码
     */
    private String keyPwd;

    /**
     * Ukey状态
     */
    private String keyStatus;

    private String laborStartDateStr;

    private String laborEndDateStr;

    private String firstInCompanyDateStr;

    private String firstInDateStr;

    /**
     * 是否邮寄退工单
     */
    private Boolean mailContinue;
    /**
     * 邮寄退工单地址
     */
    private String mailAdress;
    /**
     * 邮寄退工单收件人
     */
    private String recipient;
    /**
     * 邮寄退工单邮编
     */
    private String postCode;
    /**
     * 邮寄退工单电话
     */
    private String phone;

    private boolean show;

    private List<String> speacilStr;

}
