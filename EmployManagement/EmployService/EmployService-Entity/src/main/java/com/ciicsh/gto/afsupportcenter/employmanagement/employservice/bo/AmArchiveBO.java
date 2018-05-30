package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;


/**
 * Created by zhangzhiwen on 2018/1/29.
 */
public class AmArchiveBO extends AmArchive {

    private  Boolean  end;

    private  String isFrist;

    private Long[] empTaskIds;


    public Long[] getEmpTaskIds() {
        return empTaskIds;
    }

    public void setEmpTaskIds(Long[] empTaskIds) {
        this.empTaskIds = empTaskIds;
    }

    public String getIsFrist() {
        return isFrist;
    }

    public void setIsFrist(String isFrist) {
        this.isFrist = isFrist;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }
}
