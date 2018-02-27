package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfArchiveBaseAdjustMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 公积金汇缴支付公司账户名单 服务实现类
 * </p>
 */
@Service
public class HfPaymentAccountServiceImpl extends ServiceImpl<HfPaymentAccountMapper, HfPaymentAccount> implements
    HfPaymentAccountService {

    @Autowired
    HfPaymentMapper hfPaymentMapper;

    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveRejectResult(Long pkId, String remark, Integer payStatus) {
        boolean bol = false;
        HfPayment hfPayment = new HfPayment();
        hfPayment.setPaymentId(pkId.intValue());
        //根据ID获取到记录
        hfPayment = hfPaymentMapper.selectOne(hfPayment);

        if (hfPayment != null) {
            int paymentState = 0;

            //更新批次状态 支付状态:付款状态(-1:审核未过;9.支付成功;-9:支付失败;)
            if (payStatus == 9) {
                paymentState = 8;
            } else {
                //审核未过
                paymentState = 7;
                hfPayment.setRejectionRemark(remark);
            }
            hfPayment.setPaymentState(paymentState);
            hfPayment.setModifiedBy("system");
            hfPayment.setModifiedTime(LocalDateTime.now());
            hfPaymentMapper.updateById(hfPayment);

            //将批次下的客户费用明细的状态也改为已申请到财务部
            //取出批次下所有的客户费用
            List<HfPaymentAccount> ssPaymentComList = baseMapper.getByPaymentId(hfPayment.getPaymentId());
            if (ssPaymentComList != null) {
                HfPaymentAccount hfPaymentAcc = null;
                for (int i = 0; i < ssPaymentComList.size(); i++) {
                    //修改状态为已申请到财务部
                    hfPaymentAcc = ssPaymentComList.get(i);
                    hfPaymentAcc.setPaymentStatus(paymentState);
                    hfPaymentAcc.setModifiedBy("system");
                    hfPaymentAcc.setModifiedTime(LocalDateTime.now());
                }
                this.updateBatchById(ssPaymentComList);
            }
        }
        bol = true;
        return bol;
    }
}
