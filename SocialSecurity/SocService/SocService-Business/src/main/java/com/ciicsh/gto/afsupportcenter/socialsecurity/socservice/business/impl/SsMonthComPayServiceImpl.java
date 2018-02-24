package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthComPay;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsMonthComPayMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthComPayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 月度缴费明细报表，系统在每月26日晚上自动生成每月的明细数据，用户可重新生成 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@Service
public class SsMonthComPayServiceImpl extends ServiceImpl<SsMonthComPayMapper, SsMonthComPay> implements SsMonthComPayService {

}
