package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.EmployeePaymentApplyPO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyPayStatusDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyReturnTicketDTO;

/**
 * <p>
 * 雇员付款任务 服务类
 * </p>
 *
 * @author chenpb
 * @since 2018-01-29
 */
public interface EmployeePaymentJobService extends IService<EmployeePaymentApplyPO> {

    /**
     * @description 处理雇员付款任务
     * @author chenpb
     * @since 2018-01-29
     * @param
     * @return
     */
    void handleEmpPayment();

    /**
     * @description 雇员报销退票处理
     * @author chenpb
     * @since 2018-02-02
     * @param dto: 结算中心退票信息
     */
    void handlePaymentRefund (PayApplyReturnTicketDTO dto);

    /**
     * @description 同步结算中心处理状态
     * @author chenpb
     * @since 2018-02-02
     * @param dto: 结算中心处理结果
     */
    void syncSettleCenterStatus (PayApplyPayStatusDTO dto);
}
