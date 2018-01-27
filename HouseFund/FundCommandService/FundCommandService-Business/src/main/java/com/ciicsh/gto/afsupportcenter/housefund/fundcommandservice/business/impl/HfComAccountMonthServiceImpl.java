package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfComAccountMonth;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfComAccountMonthMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfComAccountMonthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业账户级每月支付信息快照
公积金专员每月记录所有客户的支付情况

 服务实现类
 * </p>
 */
@Service
public class HfComAccountMonthServiceImpl extends ServiceImpl<HfComAccountMonthMapper, HfComAccountMonth> implements IHfComAccountMonthService {

}
