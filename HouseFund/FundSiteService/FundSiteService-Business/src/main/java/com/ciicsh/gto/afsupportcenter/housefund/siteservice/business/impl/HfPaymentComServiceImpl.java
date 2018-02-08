package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfArchiveBaseAdjustMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfPaymentCom;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfPaymentComService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公积金汇缴支付公司名单 服务实现类
 * </p>
 */
@Service
public class HfPaymentComServiceImpl extends ServiceImpl<HfPaymentComMapper, HfPaymentCom> implements
    HfPaymentComService {

}
