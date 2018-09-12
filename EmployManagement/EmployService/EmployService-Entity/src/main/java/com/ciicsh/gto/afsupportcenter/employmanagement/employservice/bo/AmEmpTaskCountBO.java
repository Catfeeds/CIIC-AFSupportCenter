package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import lombok.Data;

/**
 * Created by zhangzhiwen on 2018/1/24.
 */
@Data
public class AmEmpTaskCountBO {
    /**
     * 未反馈
     */
    private Integer noSign;
    /**
     * 用工已办查无档
     */
    private Integer noRecord;
    /**
     * 用工成功
     */
    private Integer employSuccess;
    /**
     * 用工失败
     */
    private Integer employFailed;
    /**
     * 前道撤销用工
     */
    private Integer employCancel;
    /**
     * ukey外借
     */
    private Integer borrowKey;
    /**
     * 其他
     */
    private Integer other;
    /**
     * 总数
     */
    private Integer amount;

}
