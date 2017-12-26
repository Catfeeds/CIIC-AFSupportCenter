package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthCharge;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成， Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-23
 */
public interface SsMonthChargeMapper extends BaseMapper<SsMonthCharge> {

}
