package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;

import java.time.LocalDate;
import java.util.List;

public class AmResignBO extends AmResign {

    private String employStyle;
    /**
     * 办理类型
     */
    private String handleType;
    /**
     * 用工属性
     */
    private String employProperty;
    /**
     * 预留档案编号
     */
    private String yuliuDocNum;
    /**
     * 存档地
     */
    private String archivePlace;

    /**
     * 身份证号码
     */
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

    /**
     * 调档反馈操作日期
     */
    private LocalDate diaodangFeedbackOptDate;
    /**
     * 出库日期
     */
    private  LocalDate storageOutDate;
    /**
     * 退工状态数量
     */
    private  Integer count;
    /**
     * 公司编码
     */
    private  String licenseCode;
    /**
     * 服务中心
     */
    private  String serviceCenter;
    /**
     * 客服
     */
    private String custom;
    /**
     * 实际录用日期
     */
    private LocalDate employDate;
    /**
     * 入库日期
     */
    private LocalDate storageDate;

    private  String diaodangFeedback;

    private String docCode;

    private  String  archivePlaceAdditional;

    private String archiveCardState;

    private Integer taskStatus;

    private String remarkType;
    /**
     * 用工反馈
     */
    private  String employFeedback;

    private String matchEmployIndex;

    public String getMatchEmployIndex() {
        return matchEmployIndex;
    }

    public void setMatchEmployIndex(String matchEmployIndex) {
        this.matchEmployIndex = matchEmployIndex;
    }

    public String getEmployFeedback() {
        return employFeedback;
    }

    public void setEmployFeedback(String employFeedback) {
        this.employFeedback = employFeedback;
    }

    private String params;

    private List<String> param;

    public String getRemarkType() {
        return remarkType;
    }

    public void setRemarkType(String remarkType) {
        this.remarkType = remarkType;
    }

    public String getArchiveCardState() {
        return archiveCardState;
    }

    public void setArchiveCardState(String archiveCardState) {
        this.archiveCardState = archiveCardState;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getArchivePlaceAdditional() {
        return archivePlaceAdditional;
    }

    public void setArchivePlaceAdditional(String archivePlaceAdditional) {
        this.archivePlaceAdditional = archivePlaceAdditional;
    }

    public LocalDate getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(LocalDate storageDate) {
        this.storageDate = storageDate;
    }

    public LocalDate getStorageOutDate() {
        return storageOutDate;
    }

    public void setStorageOutDate(LocalDate storageOutDate) {
        this.storageOutDate = storageOutDate;
    }

    public String getArchivePlace() {
        return archivePlace;
    }

    public void setArchivePlace(String archivePlace) {
        this.archivePlace = archivePlace;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> param) {
        this.param = param;
    }

    public String getEmployStyle() {
        return employStyle;
    }

    public void setEmployStyle(String employStyle) {
        this.employStyle = employStyle;
    }

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

    public String getYuliuDocNum() {
        return yuliuDocNum;
    }

    public void setYuliuDocNum(String yuliuDocNum) {
        this.yuliuDocNum = yuliuDocNum;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
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

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

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

    public LocalDate getEmployDate() {
        return employDate;
    }

    public void setEmployDate(LocalDate employDate) {
        this.employDate = employDate;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }



}
