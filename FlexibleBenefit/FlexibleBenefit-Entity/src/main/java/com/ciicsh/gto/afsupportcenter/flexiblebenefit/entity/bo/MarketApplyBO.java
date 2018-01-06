package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.*;

import java.util.List;

/**
 * 礼品申请详情
 *
 * @author xwz
 */
public class MarketApplyBO {
    private MarketActivityPO marketActivity;
    private ApplyRecordPO applyRecord;
    private List<ApplyRecordDetailPO> recordDetailList;
    private List<ApplyMarketActivityRecordPO> applyMarketActivityRecordList;
    private List<ApprovalStepPO> approvalStepList;

    public MarketActivityPO getMarketActivity() {
        return marketActivity;
    }

    public void setMarketActivity(MarketActivityPO marketActivity) {
        this.marketActivity = marketActivity;
    }

    public ApplyRecordPO getApplyRecord() {
        return applyRecord;
    }

    public void setApplyRecord(ApplyRecordPO applyRecord) {
        this.applyRecord = applyRecord;
    }

    public List<ApplyRecordDetailPO> getRecordDetailList() {
        return recordDetailList;
    }

    public void setRecordDetailList(List<ApplyRecordDetailPO> recordDetailList) {
        this.recordDetailList = recordDetailList;
    }

    public List<ApplyMarketActivityRecordPO> getApplyMarketActivityRecordList() {
        return applyMarketActivityRecordList;
    }

    public void setApplyMarketActivityRecordList(List<ApplyMarketActivityRecordPO> applyMarketActivityRecordList) {
        this.applyMarketActivityRecordList = applyMarketActivityRecordList;
    }

    public List<ApprovalStepPO> getApprovalStepList() {
        return approvalStepList;
    }

    public void setApprovalStepList(List<ApprovalStepPO> approvalStepList) {
        this.approvalStepList = approvalStepList;
    }

    @Override
    public String toString() {
        return "MarketApplyBO{" +
            "marketActivity=" + marketActivity +
            ", applyRecord=" + applyRecord +
            ", recordDetailList=" + recordDetailList +
            ", applyMarketActivityRecordList=" + applyMarketActivityRecordList +
            ", approvalStepList=" + approvalStepList +
            '}';
    }
}
