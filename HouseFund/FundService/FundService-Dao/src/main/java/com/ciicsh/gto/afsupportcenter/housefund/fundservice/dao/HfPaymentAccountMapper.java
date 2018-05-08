package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankComAccountBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 公积金汇缴支付公司账户名单 Mapper 接口
 * </p>
 */
@Repository
public interface HfPaymentAccountMapper extends BaseMapper<HfPaymentAccount> {

    /**
     * <p>Description: 根据批次检索</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param paymentId 批次ID
     * @return   List<SsPaymentCom>
     */
    List<HfPaymentAccount> getByPaymentId(Long paymentId);

    /**
     * <p>Description: 获得公积金汇缴支付列表</p>
     *
     * @author sj
     * @date 2018-03-20
     * @param hfPaymentAccountBo UI制作汇缴名单
     * @return   List<HfPaymentAccountBo>
     */
    List<HfPaymentAccountBo> getMakePayLists(HfPaymentAccountBo hfPaymentAccountBo);

    List<HFNetBankComAccountBO> getComAccountByPaymentId(Long paymentId);

    List<HfPaymentAccountBo> getFundPaysEditOperationData(HfPaymentAccountBo hfPaymentComBo);

    /**
     * 编辑页面添加汇缴支付详细数据
     * @param hfPaymentAccountBo 条件
     * @return 添加结果
     */
    List<HfPaymentAccountBo> getMakePayListsById(HfPaymentAccountBo hfPaymentAccountBo);

    /**
     * 删除汇缴编辑操作数据时同步更新hf_payment_account表中数据
     * @param hfPaymentAccountBo 删除条件
     */
    void updateDelOperateEditDataFromHpa(HfPaymentAccountBo hfPaymentAccountBo);
}
