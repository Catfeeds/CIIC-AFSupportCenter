package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;

import java.time.LocalDate;
import java.util.List;

public class AmEmploymentBO extends AmEmployment {

    private String yuliuDocNum;
    /**
     * 档案编号
     */
    private String docNum;
    /**
     * 办理类型
     */
    private String handleType;
    /**
     * 退工日期
     */
    private LocalDate resignDate;
    /**
     * 出库日期
     */
    private LocalDate storageOutDate;
    /**
     * 存档地

     */
    private String archivePlace;

    private String resignFeedback1;

    private String resignFeedback2;

    private LocalDate returnDocDate;

    /**
     * 退工送办日期
     */
    private LocalDate resignHandleDate;

    private  String employFeedback;

    private Integer taskCategory;

    private Integer remarkType;

    private Long archiveId;

    private String params;

    private List<String> param;

    private Integer taskStatus;

    private  Integer count;

    private  String archiveDirection;

    private String employeeNature;

    private String idNum;

    private Integer idCardType;

    private  String employeeName;

    private String title;

    private LocalDate jobCentreFeedbackDate;

    private  String outReason;

    private LocalDate outDate;

    private  String employNotes;

    private String cici;

    /**
     * 服务中心
     */
    private  String serviceCenter;
    /**
     * 客服经理
     */
    private String leaderShipName;

    private  String archiveSpecial;

    public String getArchiveSpecial() {
        return archiveSpecial;
    }

    public void setArchiveSpecial(String archiveSpecial) {
        this.archiveSpecial = archiveSpecial;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public String getLeaderShipName() {
        return leaderShipName;
    }

    public void setLeaderShipName(String leaderShipName) {
        this.leaderShipName = leaderShipName;
    }

    public String getCici() {
        return cici;
    }

    public void setCici(String cici) {
        this.cici = cici;
    }

    /**
     * 用工属性编码
     */
    private Integer employCode;

    public Integer getEmployCode() {
        return employCode;
    }

    public void setEmployCode(Integer employCode) {
        this.employCode = employCode;
    }

    public String getEmployNotes() {
        return employNotes;
    }

    public void setEmployNotes(String employNotes) {
        this.employNotes = employNotes;
    }

    public String getOutReason() {
        return outReason;
    }

    public void setOutReason(String outReason) {
        this.outReason = outReason;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public LocalDate getJobCentreFeedbackDate() {
        return jobCentreFeedbackDate;
    }

    public void setJobCentreFeedbackDate(LocalDate jobCentreFeedbackDate) {
        this.jobCentreFeedbackDate = jobCentreFeedbackDate;
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

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Integer getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(Integer idCardType) {
        this.idCardType = idCardType;
    }

    public String getArchiveDirection() {
        return archiveDirection;
    }

    public void setArchiveDirection(String archiveDirection) {
        this.archiveDirection = archiveDirection;
    }

    public String getEmployeeNature() {
        return employeeNature;
    }

    public void setEmployeeNature(String employeeNature) {
        this.employeeNature = employeeNature;
    }

    public Integer getRemarkType() {
        return remarkType;
    }


    public Long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Long archiveId) {
        this.archiveId = archiveId;
    }

    public void setRemarkType(Integer remarkType) {
        this.remarkType = remarkType;
    }

    public Integer getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(Integer taskCategory) {
        this.taskCategory = taskCategory;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
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



    public String getEmployFeedback() {
        return employFeedback;
    }

    public void setEmployFeedback(String employFeedback) {
        this.employFeedback = employFeedback;
    }

    public LocalDate getReturnDocDate() {
        return returnDocDate;
    }

    public void setReturnDocDate(LocalDate returnDocDate) {
        this.returnDocDate = returnDocDate;
    }

    public LocalDate getResignHandleDate() {
        return resignHandleDate;
    }

    public void setResignHandleDate(LocalDate resignHandleDate) {
        this.resignHandleDate = resignHandleDate;
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

    @Override
    public String getHandleType() {
        return handleType;
    }

    @Override
    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public LocalDate getResignDate() {
        return resignDate;
    }

    public void setResignDate(LocalDate resignDate) {
        this.resignDate = resignDate;
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

    public String getResignFeedback1() {
        return resignFeedback1;
    }

    public void setResignFeedback1(String resignFeedback1) {
        this.resignFeedback1 = resignFeedback1;
    }

    public String getResignFeedback2() {
        return resignFeedback2;
    }

    public void setResignFeedback2(String resignFeedback2) {
        this.resignFeedback2 = resignFeedback2;
    }


}
