package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import java.util.Date;
import java.util.List;

public class AmArchiveReturnPrintDTO {


    // 档案类别
    private String docType;

    // 档案编号
    private String docNum;

    /**
     * 雇员id
     */
    private String employeeId;

    /**
     * 雇员姓名
     */
    private String employeeName;

    /**
     * 基本公积金帐号
     */
    private String hfAccount;

    /**
     * 补充公积金帐号
     */
    private String hfAccountBC;

    /**
     * 雇员姓别
     */
    private String employeeSex;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 用工形式
     */
    private String employStyle;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 终止类型:空、合同终止、合同解除
     */
    private String endType;

    /**
     * 身份证号
     */
    private String idNum;

    /**
     * 转出时间
     */
    private Date outDate;

    /**
     * 转移方式
     */
    private String transferWay;

    /**
     * 劳动手册是否交被退人员
     */
    private Integer ifLaborManualReturnStr;

    // 组织机构代码
    private String organizationCode;

    // 经办人
    private String operationName;

    // 联系电话
    private String mobile;

    // 操作日期
    private Date operationDate;

    //

}
