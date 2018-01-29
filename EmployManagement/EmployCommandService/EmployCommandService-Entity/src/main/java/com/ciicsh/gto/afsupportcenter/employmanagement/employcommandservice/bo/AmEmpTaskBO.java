package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;

import java.time.LocalDate;
import java.util.List;

public class AmEmpTaskBO extends AmEmpTask {

    private String employStyle;
    /**
     * 空、外来从业人员、居住证、调档、属地管理、市人才、梅园路、商城路、漕虹路、区人才、高校、经营者、厂长经理人才、农民工、退休、协保、退工不调档、用工不调档、其他、非全日制、中智、徐职、公司自行保管
     */
    private String handleType;

    /**
     * 空、中智、外包、独立
     */

    private String employProperty;

    private String yuliuDocNum;

   private String idNum;

    /**
     * 档案编号

     */
    private String docNum;
    /**
     * 公司名称
     */
    private String title;
    /**
     * 雇员姓名
     */

    private String employeeName;

    /**
     * 用工反馈操作日期
     */
    private LocalDate employFeedbackOptDate;

    private String diaodangFeedback;
    /**
     * 调档反馈操作日期
     */
    private LocalDate diaodangFeedbackOptDate;
    /**
     * 退工状态数量
     */
    private  Integer count;

    private  String licenseCode;

    private  String serviceCenter;
    private String custom;

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }




    public LocalDate getEmployFeedbackOptDate() {
        return employFeedbackOptDate;
    }

    public void setEmployFeedbackOptDate(LocalDate employFeedbackOptDate) {
        this.employFeedbackOptDate = employFeedbackOptDate;
    }

    public String getDiaodangFeedback() {
        return diaodangFeedback;
    }

    public void setDiaodangFeedback(String diaodangFeedback) {
        this.diaodangFeedback = diaodangFeedback;
    }

    public LocalDate getDiaodangFeedbackOptDate() {
        return diaodangFeedbackOptDate;
    }

    public void setDiaodangFeedbackOptDate(LocalDate diaodangFeedbackOptDate) {
        this.diaodangFeedbackOptDate = diaodangFeedbackOptDate;
    }

    private String params;

    private List<String> param;

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> param) {
        this.param = param;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getYuliuDocNum() {
        return yuliuDocNum;
    }

    public void setYuliuDocNum(String yuliuDocNum) {
        this.yuliuDocNum = yuliuDocNum;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getEmployWay() {
        return employWay;
    }

    public void setEmployWay(String employWay) {
        this.employWay = employWay;
    }

    /**
     * 空、Ukey、集体转入,用工自办、翻牌、无材料用工、网办无材料、转人员性质、新进转人员性质、送外区办、修改信息、外来新进、外来转入
     */
    private String employWay;

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getEmployProperty() {
        return employProperty;
    }

    public void setEmployProperty(String employProperty) {
        this.employProperty = employProperty;
    }

    public String getEmployStyle() {
        return employStyle;
    }

    public void setEmployStyle(String employStyle) {
        this.employStyle = employStyle;
    }
}
