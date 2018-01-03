package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.*;

import java.util.List;

/**
 * 礼品申请详情
 *
 * @author xwz
 */
public class MarketApplyBO {
    private MarketActivityPO marketActivityPO;
    private ApplyRecordPO applyRecordPO;
    private List<ApplyRecordDetailPO> recordDetailList;
    private List<ApplyMarketActivityRecordPO> applyMarketActivityRecordList;

    public MarketActivityPO getMarketActivityPO() {
        return marketActivityPO;
    }

    public void setMarketActivityPO(MarketActivityPO marketActivityPO) {
        this.marketActivityPO = marketActivityPO;
    }

    public ApplyRecordPO getApplyRecordPO() {
        return applyRecordPO;
    }

    public void setApplyRecordPO(ApplyRecordPO applyRecordPO) {
        this.applyRecordPO = applyRecordPO;
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

    @Override
    public String toString() {
        return "MarketApplyBO{" +
            "marketActivityPO=" + marketActivityPO +
            ", applyRecordPO=" + applyRecordPO +
            ", recordDetailList=" + recordDetailList +
            ", applyMarketActivityRecordList=" + applyMarketActivityRecordList +
            '}';
    }
}
