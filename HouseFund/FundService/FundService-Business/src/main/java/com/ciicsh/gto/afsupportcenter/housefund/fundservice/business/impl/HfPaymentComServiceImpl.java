package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfArchiveBaseAdjustMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentComService;
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
