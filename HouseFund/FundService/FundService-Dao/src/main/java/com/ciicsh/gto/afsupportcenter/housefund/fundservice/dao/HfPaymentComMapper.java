package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公积金汇缴支付公司名单 Mapper 接口
 * </p>
 */
public interface HfPaymentComMapper extends BaseMapper<HfPaymentCom> {
    /**
     * 更新前端传递的list参数查询企业账户
     * @param paymentAccountIds
     * @return
     */
    List<Map<String,Object>> selectPaymentAccount(@Param("paymentAccountIds") List paymentAccountIds);
}
