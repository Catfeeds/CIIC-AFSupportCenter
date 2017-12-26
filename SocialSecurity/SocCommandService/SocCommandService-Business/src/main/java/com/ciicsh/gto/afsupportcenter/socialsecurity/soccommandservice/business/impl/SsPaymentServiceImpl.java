package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsPaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsPaymentSrarchDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPayment;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsPaymentMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsPaymentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 社保支付批次 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsPaymentServiceImpl extends ServiceImpl<SsPaymentMapper, SsPayment> implements ISsPaymentService {

    @Override
    public PageRows<SsPaymentDTO> paymentQuery(PageInfo pageInfo) {
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.paymentQuery(pageInfo.toJavaObject(SsPaymentDTO.class)));
    }

    @Override
    public List<SsPaymentDTO> showAddBatch(SsPaymentSrarchDTO paymentSrarchDTO){
        return baseMapper.searchPaymentByAccountTypeAndState(paymentSrarchDTO);
    }
}
