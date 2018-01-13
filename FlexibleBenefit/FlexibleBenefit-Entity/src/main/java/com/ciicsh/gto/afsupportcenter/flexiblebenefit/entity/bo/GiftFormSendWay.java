package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo;

import java.util.List;

/**
 * @author xiweizhen
 */
public class GiftFormSendWay {
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
}
