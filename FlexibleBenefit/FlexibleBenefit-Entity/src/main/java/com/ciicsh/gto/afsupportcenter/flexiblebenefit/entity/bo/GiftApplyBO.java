package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyGiftRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;

/**
 * 礼品申请详情
 *
 * @author xwz
 */
public class GiftApplyBO {
    private GiftPO giftPO;
    private ApplyRecordPO applyRecordPO;
    private ApplyRecordDetailPO applyRecordDetailPO;
    private ApplyGiftRecordPO applyGiftRecordPO;

    public GiftPO getGiftPO() {
        return giftPO;
    }

    public void setGiftPO(GiftPO giftPO) {
        this.giftPO = giftPO;
    }

    public ApplyRecordPO getApplyRecordPO() {
        return applyRecordPO;
    }

    public void setApplyRecordPO(ApplyRecordPO applyRecordPO) {
        this.applyRecordPO = applyRecordPO;
    }

    public ApplyRecordDetailPO getApplyRecordDetailPO() {
        return applyRecordDetailPO;
    }

    public void setApplyRecordDetailPO(ApplyRecordDetailPO applyRecordDetailPO) {
        this.applyRecordDetailPO = applyRecordDetailPO;
    }

    public ApplyGiftRecordPO getApplyGiftRecordPO() {
        return applyGiftRecordPO;
    }

    public void setApplyGiftRecordPO(ApplyGiftRecordPO applyGiftRecordPO) {
        this.applyGiftRecordPO = applyGiftRecordPO;
    }

    @Override
    public String toString() {
        return "GiftApplyBO{" +
            "giftPO=" + giftPO +
            ", applyRecordPO=" + applyRecordPO +
            ", applyRecordDetailPO=" + applyRecordDetailPO +
            ", applyGiftRecordPO=" + applyGiftRecordPO +
            '}';
    }
}
