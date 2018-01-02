package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthCharge;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成， 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-23
 */
public interface ISsMonthChargeService extends IService<SsMonthCharge>{
    void createMonthCharge( String ssMonth,String comAccuntId);
//    void createMonthNonStandard(String comAccuntId, String ssMonth, String computeType);
//    void createMonthStandard(String comAccuntId, String ssMonth, String computeType);
//    void createMonthEmpCharge(String comAccuntId, String ssMonth, String cmputeType)
//    void createMonthEmpChangeReport(String comAccuntId, String ssMonth, String computeType);
}
