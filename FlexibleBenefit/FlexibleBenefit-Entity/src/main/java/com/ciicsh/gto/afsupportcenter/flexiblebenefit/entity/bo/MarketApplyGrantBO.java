package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.*;

import java.util.List;

/**
 * 礼品申请详情
 *
 * @author xwz
 */
public class MarketApplyGrantBO {
    private MarketActivityPO marketActivity;
    private ApplyRecordPO applyRecord;
    private List<MarketGrantApprovalBO> recordDetailList;
    private List<ApplyMarketActivityRecordPO> applyMarketActivityRecordList;
    private List<SelectEntity> giftFormList;
    private List<SelectEntity> sendWayList;

    public List<SelectEntity> getGiftFormList() {
        return giftFormList;
    }

    public void setGiftFormList(List<SelectEntity> giftFormList) {
        this.giftFormList = giftFormList;
    }

    public List<SelectEntity> getSendWayList() {
        return sendWayList;
    }

    public void setSendWayList(List<SelectEntity> sendWayList) {
        this.sendWayList = sendWayList;
    }

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

    public List<MarketGrantApprovalBO> getRecordDetailList() {
        return recordDetailList;
    }

    public void setRecordDetailList(List<MarketGrantApprovalBO> recordDetailList) {
        this.recordDetailList = recordDetailList;
    }

    public List<ApplyMarketActivityRecordPO> getApplyMarketActivityRecordList() {
        return applyMarketActivityRecordList;
    }

    public void setApplyMarketActivityRecordList(List<ApplyMarketActivityRecordPO> applyMarketActivityRecordList) {
        this.applyMarketActivityRecordList = applyMarketActivityRecordList;
    }


    @Override
    public String toString() {
        return "MarketApplyGrantBO{" +
            "marketActivity=" + marketActivity +
            ", applyRecord=" + applyRecord +
            ", recordDetailList=" + recordDetailList +
            ", applyMarketActivityRecordList=" + applyMarketActivityRecordList +
            ", giftFormList=" + giftFormList +
            ", sendWayList=" + sendWayList +
            '}';
    }
}
