package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.EmployeePaymentApplyPO;

/**
 * <p>
 * 雇员付款任务 服务类
 * </p>
 *
 * @author chenpb
 * @since 2018-01-29
 */
public interface EmployeePaymentService extends IService<EmployeePaymentApplyPO> {

    /**
     * @description 处理雇员付款任务
     * @author chenpb
     * @since 2018-01-29
     * @param
     * @return
     */
    void handleEmpPayment();
}
