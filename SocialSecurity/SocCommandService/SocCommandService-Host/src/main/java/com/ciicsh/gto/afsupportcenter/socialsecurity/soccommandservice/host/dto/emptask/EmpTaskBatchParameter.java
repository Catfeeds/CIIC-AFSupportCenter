package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.dto.emptask;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;

import java.util.List;

public class EmpTaskBatchParameter {
    //批量操作的参数
    private List<SsEmpTaskBO> ssEmpTaskBOList;

    public List<SsEmpTaskBO> getSsEmpTaskBOList() {
        return ssEmpTaskBOList;
    }

    public void setSsEmpTaskBOList(List<SsEmpTaskBO> ssEmpTaskBOList) {
        this.ssEmpTaskBOList = ssEmpTaskBOList;
    }
}
