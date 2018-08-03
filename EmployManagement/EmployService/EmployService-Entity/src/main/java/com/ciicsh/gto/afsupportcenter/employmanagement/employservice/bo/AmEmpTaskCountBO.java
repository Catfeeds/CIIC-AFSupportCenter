package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import lombok.Data;

/**
 * Created by zhangzhiwen on 2018/1/24.
 */
@Data
public class AmEmpTaskCountBO {
    private Integer noSign;
    private Integer finished;
    private Integer employSuccess;
    private Integer employFailed;
    private Integer employCancel;
    private Integer other;
    private Integer amount;

}
