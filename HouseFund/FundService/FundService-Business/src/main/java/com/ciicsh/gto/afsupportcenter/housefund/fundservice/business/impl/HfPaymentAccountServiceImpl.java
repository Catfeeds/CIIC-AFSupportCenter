package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    HfPaymentAccountMapper hfPaymentAccountMapper;

    @Override
    public PageRows<HfPaymentAccountBo> getMakePayLists(PageInfo pageInfo){
        HfPaymentAccountBo hfPaymentAccountBo = pageInfo.toJavaObject(HfPaymentAccountBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfPaymentAccountMapper.getMakePayLists(hfPaymentAccountBo));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updatePaymentInfo(Long pkId, String remark, Integer payStatus) {
        HfPayment hfPayment = new HfPayment();
        hfPayment.setPaymentId(pkId);
        //根据ID获取到记录
        hfPayment = hfPaymentMapper.selectOne(hfPayment);

        if (hfPayment != null) {
            //更新批次状态 支付状态:付款状态(-1:审核未过;9.支付成功;-9:支付失败;)
            int paymentState = payStatus == 9 ? 8 :7;
            if(null != remark){
                hfPayment.setRejectionRemark(remark);
            }
            hfPayment.setPaymentState(paymentState);
            hfPayment.setModifiedBy("system");
            hfPayment.setModifiedTime(new Date());
            hfPaymentMapper.updateById(hfPayment);

            //将批次下的客户费用明细的状态也改为已申请到财务部
            //取出批次下所有的客户费用
            List<HfPaymentAccount> ssPaymentComList = baseMapper.getByPaymentId(hfPayment.getPaymentId());
            if (null != ssPaymentComList && ssPaymentComList.size() > 0) {
                ssPaymentComList
                    .stream()
                    .map(x->{
                        x.setPaymentStatus(paymentState);
                        x.setModifiedBy("system");
                        x.setModifiedTime(LocalDateTime.now());
                        return x;})
                    .collect(Collectors.toList());
                this.updateBatchById(ssPaymentComList);
            }
        }
        return true;
    }
}
