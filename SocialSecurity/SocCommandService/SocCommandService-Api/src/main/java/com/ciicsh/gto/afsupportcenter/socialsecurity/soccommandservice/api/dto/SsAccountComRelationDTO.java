package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.entity.SsAccountComRelation;

public class SsAccountComRelationDTO extends SsAccountComRelation {
    //公司名称
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
