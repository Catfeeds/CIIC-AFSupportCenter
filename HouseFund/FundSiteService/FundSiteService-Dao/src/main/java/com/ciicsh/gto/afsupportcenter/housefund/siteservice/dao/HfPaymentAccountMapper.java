package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfPaymentAccount;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 公积金汇缴支付公司账户名单 Mapper 接口
 * </p>
 */
public interface HfPaymentAccountMapper extends BaseMapper<HfPaymentAccount> {

    /**
     * <p>Description: 根据批次检索</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param paymentId 批次ID
     * @return   List<SsPaymentCom>
     */
    List<HfPaymentAccount> getByPaymentId(Integer paymentId);

}
