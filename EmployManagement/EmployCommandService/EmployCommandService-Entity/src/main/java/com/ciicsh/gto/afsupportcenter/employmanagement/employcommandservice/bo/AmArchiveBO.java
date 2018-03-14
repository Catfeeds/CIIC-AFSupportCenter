package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchive;

/**
 * Created by zhangzhiwen on 2018/1/29.
 */
public class AmArchiveBO extends AmArchive {

    private  Integer  isEnd;

    private  String isFrist;

    public String getIsFrist() {
        return isFrist;
    }

    public void setIsFrist(String isFrist) {
        this.isFrist = isFrist;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }
}
