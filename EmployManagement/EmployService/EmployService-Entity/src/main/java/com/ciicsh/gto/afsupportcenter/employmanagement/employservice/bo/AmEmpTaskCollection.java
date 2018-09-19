package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/1/24.
 */
@Data
public class AmEmpTaskCollection {

    private List<AmEmpTaskCountBO> row;

    private AmTaskStatusBO amTaskStatusBO;

    private AmArchiveStatusBO amArchiveStatusBO;

    private  AmTaskStatusResignBO amTaskStatusResignBO;

    private  AmEmpTaskCountBO  amEmpTaskCountBO;

    private  AmResTaskCountBO amResTaskCountBO;
}
