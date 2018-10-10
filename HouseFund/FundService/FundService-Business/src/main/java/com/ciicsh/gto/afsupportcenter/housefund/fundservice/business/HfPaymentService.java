package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentProcessParmBO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;

public interface HfPaymentService {
    /**
     * 获得公积金汇缴支付列表
     * @param pageInfo
     * @return
     */
    PageRows<HfPaymentBo> getFundPays(PageInfo pageInfo);

    /**
     * 公积金汇缴支付流程操作-送审
     */
    JsonResult processApproval(PaymentProcessParmBO processParmBO);


    /**
     * 公积金汇缴支付流程操作-汇缴
     */
    JsonResult processPayment(PaymentProcessParmBO processParmBO);


    /**
     * 公积金汇缴支付流程操作-出票
     */
    JsonResult processTicket(PaymentProcessParmBO processParmBO);


    /**
     * 公积金汇缴支付流程操作-回单
     */
    JsonResult processReceipt(PaymentProcessParmBO processParmBO);

    /**
     * 打印汇缴书
     */
    JsonResult printRemittedBook(Long paymentId,Integer hfType);

    /**
     * 重新汇总支付申请总金额，总雇员数
     */
    JsonResult updateHfPaymentAmount(Long paymentId);

    Integer canEmpTaskHandleByPayment(List<String> paymentMonthList, Long comAccountId, Integer hfType);
    void updatePaymentStatusAfterHandle(List<String> paymentMonthList, Long comAccountId, Integer hfType);

}
