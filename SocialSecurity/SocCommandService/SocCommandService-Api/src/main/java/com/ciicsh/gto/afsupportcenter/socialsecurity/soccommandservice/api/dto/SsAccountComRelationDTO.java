package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.entity.SsAccountComRelation;

public class SsAccountComRelationDTO extends SsAccountComRelation {
    //公司名称
    private String title;

    //操作标志,1:保存,2:更新
    private String saveflag;

    public String getSaveflag() {
        return saveflag;
    }

    public void setSaveflag(String saveflag) {
        this.saveflag = saveflag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
