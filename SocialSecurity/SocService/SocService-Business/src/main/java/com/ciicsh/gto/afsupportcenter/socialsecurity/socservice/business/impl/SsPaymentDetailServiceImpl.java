package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPaymentDetail;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsPaymentDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 本地社保应付金额交易记录明细表 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsPaymentDetailServiceImpl extends ServiceImpl<SsPaymentDetailMapper, SsPaymentDetail> implements SsPaymentDetailService {

    @Override
    public List<SsPaymentDetail> paymentDetailQuery(SsPaymentDetail ssPaymentDetail){
        List<SsPaymentDetail> detailList = baseMapper.paymentDetailQuery(ssPaymentDetail);
        return detailList;
    }
}
