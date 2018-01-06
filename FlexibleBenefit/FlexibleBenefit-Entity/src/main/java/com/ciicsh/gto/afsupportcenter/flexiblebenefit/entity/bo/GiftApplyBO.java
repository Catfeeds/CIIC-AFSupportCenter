package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.*;

import java.util.List;

/**
 * 礼品申请详情
 *
 * @author xwz
 */
public class GiftApplyBO {
    private GiftPO gift;
    private ApplyRecordPO applyRecord;
    private ApplyRecordDetailPO applyRecordDetail;
    private ApplyGiftRecordPO applyGiftRecord;
    private List<ApprovalStepPO> approvalStepList;

    public GiftPO getGift() {
        return gift;
    }

    public void setGift(GiftPO gift) {
        this.gift = gift;
    }

    public ApplyRecordPO getApplyRecord() {
        return applyRecord;
    }

    public void setApplyRecord(ApplyRecordPO applyRecord) {
        this.applyRecord = applyRecord;
    }

    public ApplyRecordDetailPO getApplyRecordDetail() {
        return applyRecordDetail;
    }

    public void setApplyRecordDetail(ApplyRecordDetailPO applyRecordDetail) {
        this.applyRecordDetail = applyRecordDetail;
    }

    public ApplyGiftRecordPO getApplyGiftRecord() {
        return applyGiftRecord;
    }

    public void setApplyGiftRecord(ApplyGiftRecordPO applyGiftRecord) {
        this.applyGiftRecord = applyGiftRecord;
    }

    public List<ApprovalStepPO> getApprovalStepList() {
        return approvalStepList;
    }

    public void setApprovalStepList(List<ApprovalStepPO> approvalStepList) {
        this.approvalStepList = approvalStepList;
    }

    @Override
    public String toString() {
        return "GiftApplyBO{" +
            "gift=" + gift +
            ", applyRecord=" + applyRecord +
            ", applyRecordDetail=" + applyRecordDetail +
            ", applyGiftRecord=" + applyGiftRecord +
            ", approvalStepList=" + approvalStepList +
            '}';
    }
}
