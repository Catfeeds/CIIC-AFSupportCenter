package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.dataauth;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SsDataauthWelfareUnitDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 配置的福利方ID
     */
    private List<Integer> welfareUnits = new ArrayList<>();


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Integer> getWelfareUnits() {
        return welfareUnits;
    }

    public void setWelfareUnits(List<Integer> welfareUnits) {
        this.welfareUnits = welfareUnits;
    }
}
