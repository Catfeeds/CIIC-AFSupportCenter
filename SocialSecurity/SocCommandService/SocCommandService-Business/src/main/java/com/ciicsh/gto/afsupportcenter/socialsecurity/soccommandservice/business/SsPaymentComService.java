package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsAddPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsDelPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPayment;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPaymentCom;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

/**
 * <p>
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsPaymentComService extends IService<SsPaymentCom> {
    /**
     * <p>Description: 查询社保支付-企业账户</p>
     *
     * @author wengxk
     * @date 2017-12-21
     * @param pageInfo 翻页检索条件
     * @return  PageRows<SsPaymentComBO>
     */
    PageRows<SsPaymentComBO> paymentComQuery(PageInfo pageInfo);

    /**
     * <p>Description: 保存调整结果</p>
     *
     * @author wengxk
     * @date 2017-12-23
     * @param ssPaymentComBO 翻页检索条件
     * @return  JsonResult<>
     */
    JsonResult<String> saveAdjustment(SsPaymentComBO ssPaymentComBO);

    /**
     * <p>Description: 添加至支付批次</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param ssAddPaymentBO 添加至支付批次参数
     * @return  JsonResult<>
     */
    JsonResult<String> doAddBatch(SsAddPaymentBO ssAddPaymentBO);

    /**
     * <p>Description: 从支付批次移除</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param ssDelPaymentBO 添加至支付批次参数
     * @return  JsonResult<>
     */
    JsonResult<String> doDelBatch(SsDelPaymentBO ssDelPaymentBO);

    /**
     * <p>Description: 重新计算批次</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param paymentId 批次ID
     * @return  int
     */
    JsonResult<String> calculatePayment(Long paymentId);


    /**
     * <p>Description: 根据ID获取信息及扩展信息</p>
     *
     * @author wengxk
     * @date 2018-01-02
     * @param paymentComId 客户费用明细ID
     * @return  SsPaymentComBO
     */
    SsPaymentComBO getPaymentComBoByPaymentId(Long paymentComId);

    /**
     * <p>Description:更新社保申请支付结果</p>
     *
     * @author wengxk
     * @date 2018-01-02
     * @param ssPayment 社保支付批次
     * @return  SsPaymentComBO
     */
    void saveReviewdePassResult(SsPayment ssPayment);

    /**
     * <p>Description:付款申请拒绝返回更新支付状态</p>
     *
     * @author zhangxj
     * @date 2018-01-02
     * @param pkId 业务方主键id
     * @param pkId 批退备注
     */
    boolean saveRejectResult(Long pkId, String remark);
}
