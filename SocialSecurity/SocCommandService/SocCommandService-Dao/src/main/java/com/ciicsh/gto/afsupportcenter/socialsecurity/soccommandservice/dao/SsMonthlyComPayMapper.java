package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthlyComPay;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 月度缴费明细报表，系统在每月26日晚上自动生成每月的明细数据，用户可重新生成 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsMonthlyComPayMapper extends BaseMapper<SsMonthlyComPay> {

}
