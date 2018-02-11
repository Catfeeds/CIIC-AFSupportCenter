package com.ciicsh.gto.afsupportcenter.fundjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfPaymentAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公积金汇缴支付公司账户名单 Mapper 接口
 * </p>
 */
@Mapper
@Component
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

    int updatePaymentAcc(Map map);
}
