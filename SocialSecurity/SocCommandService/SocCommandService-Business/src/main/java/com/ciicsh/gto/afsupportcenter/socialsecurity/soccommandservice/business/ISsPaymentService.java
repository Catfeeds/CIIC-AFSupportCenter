package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentSrarchBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPayment;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;

/**
 * <p>
 * 社保支付批次 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface ISsPaymentService extends IService<SsPayment> {

    /**
     * <p>Description: 查询社保支付-企业账户</p>
     *
     * @author wengxk
     * @date 2017-12-22
     * @param pageInfo 翻页检索条件
     * @return  PageRows<SsPaymentBO>
     */
    PageRows<SsPaymentBO> paymentQuery(PageInfo pageInfo);

    /**
     * <p>Description: 按照条件显示可加入的批次</p>
     *
     * @author wengxk
     * @date 2017-12-26
     * @param paymentSrarchDTO 检索条件
     * @return  JsonResult<>
     */
    List<SsPaymentBO> showAddBatch(SsPaymentSrarchBO paymentSrarchDTO);

    /**
     * <p>Description: 申请支付</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param ssPayment 检索条件
     * @return  JsonResult<>
     */
    JsonResult<String> doApplyPay(SsPayment ssPayment);

    /**
     * <p>Description: 删除批次</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param ssPayment 检索条件
     * @return  JsonResult<>
     */
    JsonResult<String> doDelPayment(SsPayment ssPayment);

    /**
     * <p>Description: 添加批次</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param ssPayment 保存批次参数
     * @return  JsonResult<>
     */
    JsonResult<String> addPayment(SsPayment ssPayment);


    /**
     * <p>Description: 批次审批通过</p>
     *
     * @author wengxk
     * @date 2018-01-05
     * @param ssPayment 检索条件
     * @return  JsonResult<>
     */
    JsonResult<String> doReviewdePass(SsPayment ssPayment);


    /**
     * <p>Description: 批次批退</p>
     *
     * @author wengxk
     * @date 2018-01-05
     * @param ssPayment 检索条件
     * @return  JsonResult<>
     */
    JsonResult<String> doRejection(SsPayment ssPayment);


}
