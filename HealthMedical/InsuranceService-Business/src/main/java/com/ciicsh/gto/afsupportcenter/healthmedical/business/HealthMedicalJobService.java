package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.PaymentApplyBatchPO;
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
public interface HealthMedicalJobService extends IService<PaymentApplyBatchPO> {

    /**
     * @description 补充医疗受理任务
     * @author chenpb
     * @since 2018-02-06
     * @param
     * @return
     */
    void handleSupplyMedical();

    /**
     * @description 未投保医疗任务
     * @author chenpb
     * @since 2018-02-06
     * @param
     * @return
     */
    void handleUninsuredMedical();

    /**
     * @description 健康医疗退票处理
     * @author chenpb
     * @since 2018-02-06
     * @param dto: 结算中心退票信息
     */
    void handlePaymentRefund(PayApplyReturnTicketDTO dto);

    /**
     * @description 同步结算中心处理状态
     * @author chenpb
     * @since 2018-02-06
     * @param dto: 结算中心处理结果
     */
    void syncSettleCenterStatus(PayApplyPayStatusDTO dto);
}
