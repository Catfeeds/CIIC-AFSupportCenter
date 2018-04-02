package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto;

import java.time.LocalDate;

/**
 * Created by zhangzhiwen on 2018/3/29.
 */
public class ArchiveDTO {
    /**
     * 档案编号
     */
    private String docNum;
    /**
     * 存档地:空、外来从业人员、居住证、属地管理、中智、徐汇职介、市人才、梅园路、商城路、漕虹分部、浦东大道、大柏树工作站、国际航运中心、区人才、就业指导中心、经营者人才、厂长经理人才、农村富裕劳动力、退休、协保、其他、公司自行保理、退工不调、用工不调、非全日制、翻牌转下一条任务单
     */
    private String archivePlace;
    /**
     * 存档卡状态
     */
    private String archiveCardState;
    /**
     * 档案来源：空、户口所在地调入、市区人才调入、单位转出（包括邮寄）、中智取、农业户口、其他
     */
    private String docFrom;
    /**
     * 入库日期
     */
    private LocalDate storageDate;
    /**
     * 存档地补充
     */
    private String archivePlaceAdditional;
    /**
     * 用工档案缴费至
     */
    private String employDocPaymentTo;
    /**
     * 档案号
     */
    private String docCode;
    /**
     * 户口号
     */
    private String hukouCode;
    /**
     * 调档反馈
     */
    private String diaodangFeedback;
    /**
     * 录用处理结束
     */
    private String luyongHandleEnd;

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

    public String getArchiveCardState() {
        return archiveCardState;
    }

    public void setArchiveCardState(String archiveCardState) {
        this.archiveCardState = archiveCardState;
    }

    public String getDocFrom() {
        return docFrom;
    }

    public void setDocFrom(String docFrom) {
        this.docFrom = docFrom;
    }

    public LocalDate getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(LocalDate storageDate) {
        this.storageDate = storageDate;
    }

    public String getArchivePlaceAdditional() {
        return archivePlaceAdditional;
    }

    public void setArchivePlaceAdditional(String archivePlaceAdditional) {
        this.archivePlaceAdditional = archivePlaceAdditional;
    }

    public String getEmployDocPaymentTo() {
        return employDocPaymentTo;
    }

    public void setEmployDocPaymentTo(String employDocPaymentTo) {
        this.employDocPaymentTo = employDocPaymentTo;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getHukouCode() {
        return hukouCode;
    }

    public void setHukouCode(String hukouCode) {
        this.hukouCode = hukouCode;
    }

    public String getDiaodangFeedback() {
        return diaodangFeedback;
    }

    public void setDiaodangFeedback(String diaodangFeedback) {
        this.diaodangFeedback = diaodangFeedback;
    }

    public String getLuyongHandleEnd() {
        return luyongHandleEnd;
    }

    public void setLuyongHandleEnd(String luyongHandleEnd) {
        this.luyongHandleEnd = luyongHandleEnd;
    }
}
