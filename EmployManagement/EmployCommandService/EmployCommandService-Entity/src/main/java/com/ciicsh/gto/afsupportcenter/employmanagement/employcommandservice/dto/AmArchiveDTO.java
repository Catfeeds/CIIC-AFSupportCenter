package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Created by zhangzhiwen on 2018/2/7.
 */
public class AmArchiveDTO implements Serializable {

    /**
     * 主键
     */
    private Long archiveId;
    /**
     * 外键
     */
    private Long employmentId;
    /**
     * 客户Id
     */
    private Long companyId;
    /**
     * 雇员id
     */
    private String employeeId;
    /**
     * 外键 预增雇员信息
     */
    private Long empPriorId;
    /**
     * 预留档案类别：空、A、Aa、B、Bb…Z、Zz
     */
    private String yuliuDocType;
    /**
     * 预留档案编号
     */
    private String yuliuDocNum;
    /**
     * 档案类别：空、A、Aa、B、Bb...Z、Zz...AB、AC、AD...AZ、BA、BC...BZ
     */
    private String docType;
    /**
     * 档案编号
     */
    private String docNum;
    /**
     * 存档地:空、外来从业人员、居住证、属地管理、中智、徐汇职介、市人才、梅园路、商城路、漕虹分部、浦东大道、大柏树工作站、国际航运中心、区人才、就业指导中心、经营者人才、厂长经理人才、农村富裕劳动力、退休、协保、其他、公司自行保理、退工不调、用工不调、非全日制、翻牌转下一条任务单
     */
    private String archivePlace;
    /**
     * 存档地补充
     */
    private String archivePlaceAdditional;
    /**
     * 存档卡状态：空、无、卡为复印件
     */
    private String archiveCardState;
    /**
     * 档案号
     */
    private String docCode;
    /**
     * 档案来源：空、户口所在地调入、市区人才调入、单位转出（包括邮寄）、中智取、农业户口、其他
     */
    private String docFrom;
    /**
     * 用工反馈：空、用工成功、用工已办查无档、用工失败、Ukey外借、前道要求撤销用工、用工成功,重复任务单、用工已办,前道已中止
     */
    private String employFeedback;
    /**
     * 用工反馈操作日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate employFeedbackOptDate;
    /**
     * 调档反馈：空、已告知本人转档、无档自查、浦东职介代管、黄浦职介代管、长宁职介代管、静安职介代管、普陀职介代管、虹口职介代管、金桥职介代管、徐汇职介代管、档在高招办
     */
    private String diaodangFeedback;
    /**
     * 调档反馈操作日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate diaodangFeedbackOptDate;
    /**
     * Ukey外借日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate ukeyBorrowDate;
    /**
     * Ukey返回日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate ukeyReturnDate;
    /**
     * 户口号
     */
    private String hukouCode;
    /**
     * 用工档案缴费至
     */
    private String employDocPaymentTo;
    /**
     * 入库日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate storageDate;
    /**
     * 录用处理结束
     */
    private String luyongHandleEnd;
    /**
     * 档案中途转出
     */
    private String docHalfwayOut;
    /**
     * 档案中途转出时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate docHalfwayOutDate;
    /**
     * 档案中途转出方向
     */
    private String docHalfwayOutDirect;
    /**
     * 手册入库日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate manualStorageDate;
    /**
     * 用工后手册入库人
     */
    private String afterEmployManualStorageMan;
    /**
     * 用工后收到手册日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate afterEmployManualReceiveDate;
    /**
     * 恢复用工手册入库人
     */
    private String recoverEmployManualStorageMan;
    /**
     * 恢复用工手册入库日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate recoverEmployManualStorageDate;
    /**
     * 寄档案回执人
     */
    private String mailDocReturnMan;
    /**
     * 寄档案回执日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate mailDocReturnDate;
    /**
     * 开存档证明人
     */
    private String openDocProofMan;
    /**
     * 开存档证明日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate openDocProofDate;
    /**
     * 补调档案日期1
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate budiaoDocDate1;
    /**
     * 补调档人1
     */
    private String budiaoDocMan1;
    /**
     * 补调档案日期2
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate budiaoDocDate2;
    /**
     * 补调档人2
     */
    private String budiaoDocMan2;
    /**
     * 用工资料备注
     */
    private String employMaterialRemark;
    /**
     * 出库日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate storageOutDate;
    /**
     * 出库人
     */
    private String storageOutMan;
    /**
     * 实际寄信日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate postLetterDate;
    /**
     * 寄信人
     */
    private String postLetterMan;
    /**
     * 实际结费日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate closeFeeDate;
    /**
     * 退工档案费缴至
     */
    private String resignDocPaymentTo;
    /**
     * 支付档案费金额
     */
    private String payDocAmount;
    /**
     * 档案结费起始日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate closeFeeStartDate;
    /**
     * 退工单并档日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate resignDocDate;
    /**
     * 退工单返回日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDate resignReturnDate;
    /**
     * 无存根联
     */
    private String noStub;
    /**
     * 是否可用
     */
    private Integer isActive;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private LocalDateTime modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;

    public Long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Long archiveId) {
        this.archiveId = archiveId;
    }

    public Long getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(Long employmentId) {
        this.employmentId = employmentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmpPriorId() {
        return empPriorId;
    }

    public void setEmpPriorId(Long empPriorId) {
        this.empPriorId = empPriorId;
    }

    public String getYuliuDocType() {
        return yuliuDocType;
    }

    public void setYuliuDocType(String yuliuDocType) {
        this.yuliuDocType = yuliuDocType;
    }

    public String getYuliuDocNum() {
        return yuliuDocNum;
    }

    public void setYuliuDocNum(String yuliuDocNum) {
        this.yuliuDocNum = yuliuDocNum;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getArchivePlace() {
        return archivePlace;
    }

    public void setArchivePlace(String archivePlace) {
        this.archivePlace = archivePlace;
    }

    public String getArchivePlaceAdditional() {
        return archivePlaceAdditional;
    }

    public void setArchivePlaceAdditional(String archivePlaceAdditional) {
        this.archivePlaceAdditional = archivePlaceAdditional;
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

    public String getDocFrom() {
        return docFrom;
    }

    public void setDocFrom(String docFrom) {
        this.docFrom = docFrom;
    }

    public String getEmployFeedback() {
        return employFeedback;
    }

    public void setEmployFeedback(String employFeedback) {
        this.employFeedback = employFeedback;
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

    public LocalDate getUkeyBorrowDate() {
        return ukeyBorrowDate;
    }

    public void setUkeyBorrowDate(LocalDate ukeyBorrowDate) {
        this.ukeyBorrowDate = ukeyBorrowDate;
    }

    public LocalDate getUkeyReturnDate() {
        return ukeyReturnDate;
    }

    public void setUkeyReturnDate(LocalDate ukeyReturnDate) {
        this.ukeyReturnDate = ukeyReturnDate;
    }

    public String getHukouCode() {
        return hukouCode;
    }

    public void setHukouCode(String hukouCode) {
        this.hukouCode = hukouCode;
    }

    public String getEmployDocPaymentTo() {
        return employDocPaymentTo;
    }

    public void setEmployDocPaymentTo(String employDocPaymentTo) {
        this.employDocPaymentTo = employDocPaymentTo;
    }

    public LocalDate getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(LocalDate storageDate) {
        this.storageDate = storageDate;
    }

    public String getLuyongHandleEnd() {
        return luyongHandleEnd;
    }

    public void setLuyongHandleEnd(String luyongHandleEnd) {
        this.luyongHandleEnd = luyongHandleEnd;
    }

    public String getDocHalfwayOut() {
        return docHalfwayOut;
    }

    public void setDocHalfwayOut(String docHalfwayOut) {
        this.docHalfwayOut = docHalfwayOut;
    }

    public LocalDate getDocHalfwayOutDate() {
        return docHalfwayOutDate;
    }

    public void setDocHalfwayOutDate(LocalDate docHalfwayOutDate) {
        this.docHalfwayOutDate = docHalfwayOutDate;
    }

    public String getDocHalfwayOutDirect() {
        return docHalfwayOutDirect;
    }

    public void setDocHalfwayOutDirect(String docHalfwayOutDirect) {
        this.docHalfwayOutDirect = docHalfwayOutDirect;
    }

    public LocalDate getManualStorageDate() {
        return manualStorageDate;
    }

    public void setManualStorageDate(LocalDate manualStorageDate) {
        this.manualStorageDate = manualStorageDate;
    }

    public String getAfterEmployManualStorageMan() {
        return afterEmployManualStorageMan;
    }

    public void setAfterEmployManualStorageMan(String afterEmployManualStorageMan) {
        this.afterEmployManualStorageMan = afterEmployManualStorageMan;
    }

    public LocalDate getAfterEmployManualReceiveDate() {
        return afterEmployManualReceiveDate;
    }

    public void setAfterEmployManualReceiveDate(LocalDate afterEmployManualReceiveDate) {
        this.afterEmployManualReceiveDate = afterEmployManualReceiveDate;
    }

    public String getRecoverEmployManualStorageMan() {
        return recoverEmployManualStorageMan;
    }

    public void setRecoverEmployManualStorageMan(String recoverEmployManualStorageMan) {
        this.recoverEmployManualStorageMan = recoverEmployManualStorageMan;
    }

    public LocalDate getRecoverEmployManualStorageDate() {
        return recoverEmployManualStorageDate;
    }

    public void setRecoverEmployManualStorageDate(LocalDate recoverEmployManualStorageDate) {
        this.recoverEmployManualStorageDate = recoverEmployManualStorageDate;
    }

    public String getMailDocReturnMan() {
        return mailDocReturnMan;
    }

    public void setMailDocReturnMan(String mailDocReturnMan) {
        this.mailDocReturnMan = mailDocReturnMan;
    }

    public LocalDate getMailDocReturnDate() {
        return mailDocReturnDate;
    }

    public void setMailDocReturnDate(LocalDate mailDocReturnDate) {
        this.mailDocReturnDate = mailDocReturnDate;
    }

    public String getOpenDocProofMan() {
        return openDocProofMan;
    }

    public void setOpenDocProofMan(String openDocProofMan) {
        this.openDocProofMan = openDocProofMan;
    }

    public LocalDate getOpenDocProofDate() {
        return openDocProofDate;
    }

    public void setOpenDocProofDate(LocalDate openDocProofDate) {
        this.openDocProofDate = openDocProofDate;
    }

    public LocalDate getBudiaoDocDate1() {
        return budiaoDocDate1;
    }

    public void setBudiaoDocDate1(LocalDate budiaoDocDate1) {
        this.budiaoDocDate1 = budiaoDocDate1;
    }

    public String getBudiaoDocMan1() {
        return budiaoDocMan1;
    }

    public void setBudiaoDocMan1(String budiaoDocMan1) {
        this.budiaoDocMan1 = budiaoDocMan1;
    }

    public LocalDate getBudiaoDocDate2() {
        return budiaoDocDate2;
    }

    public void setBudiaoDocDate2(LocalDate budiaoDocDate2) {
        this.budiaoDocDate2 = budiaoDocDate2;
    }

    public String getBudiaoDocMan2() {
        return budiaoDocMan2;
    }

    public void setBudiaoDocMan2(String budiaoDocMan2) {
        this.budiaoDocMan2 = budiaoDocMan2;
    }

    public String getEmployMaterialRemark() {
        return employMaterialRemark;
    }

    public void setEmployMaterialRemark(String employMaterialRemark) {
        this.employMaterialRemark = employMaterialRemark;
    }

    public LocalDate getStorageOutDate() {
        return storageOutDate;
    }

    public void setStorageOutDate(LocalDate storageOutDate) {
        this.storageOutDate = storageOutDate;
    }

    public String getStorageOutMan() {
        return storageOutMan;
    }

    public void setStorageOutMan(String storageOutMan) {
        this.storageOutMan = storageOutMan;
    }

    public LocalDate getPostLetterDate() {
        return postLetterDate;
    }

    public void setPostLetterDate(LocalDate postLetterDate) {
        this.postLetterDate = postLetterDate;
    }

    public String getPostLetterMan() {
        return postLetterMan;
    }

    public void setPostLetterMan(String postLetterMan) {
        this.postLetterMan = postLetterMan;
    }

    public LocalDate getCloseFeeDate() {
        return closeFeeDate;
    }

    public void setCloseFeeDate(LocalDate closeFeeDate) {
        this.closeFeeDate = closeFeeDate;
    }

    public String getResignDocPaymentTo() {
        return resignDocPaymentTo;
    }

    public void setResignDocPaymentTo(String resignDocPaymentTo) {
        this.resignDocPaymentTo = resignDocPaymentTo;
    }

    public String getPayDocAmount() {
        return payDocAmount;
    }

    public void setPayDocAmount(String payDocAmount) {
        this.payDocAmount = payDocAmount;
    }

    public LocalDate getCloseFeeStartDate() {
        return closeFeeStartDate;
    }

    public void setCloseFeeStartDate(LocalDate closeFeeStartDate) {
        this.closeFeeStartDate = closeFeeStartDate;
    }

    public LocalDate getResignDocDate() {
        return resignDocDate;
    }

    public void setResignDocDate(LocalDate resignDocDate) {
        this.resignDocDate = resignDocDate;
    }

    public LocalDate getResignReturnDate() {
        return resignReturnDate;
    }

    public void setResignReturnDate(LocalDate resignReturnDate) {
        this.resignReturnDate = resignReturnDate;
    }

    public String getNoStub() {
        return noStub;
    }

    public void setNoStub(String noStub) {
        this.noStub = noStub;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
