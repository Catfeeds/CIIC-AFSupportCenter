package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsAddPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPaymentCom;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsPaymentComService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsPaymentComServiceImpl extends ServiceImpl<SsPaymentComMapper, SsPaymentCom> implements ISsPaymentComService {

    @Override
    public PageRows<SsPaymentComBO> paymentComQuery(PageInfo pageInfo) {
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.paymentComQuery(pageInfo.toJavaObject(SsPaymentComBO.class)));
    }



    @Override
    public JsonResult<String> saveAdjustment(SsPaymentComBO ssPaymentComBO){
        JsonResult<String> json = new JsonResult<String>();
        //验证状态

        //根据ID取出原数据
        SsPaymentCom ssPaymentCom = baseMapper.selectById(ssPaymentComBO.getPaymentComId());

        //新数据
        //抵扣费用是否纳入支付申请
        int ifDeductedIntoPay = ssPaymentComBO.getIfDeductedIntoPay();
        //额外金
        BigDecimal extraAmount = ssPaymentComBO.getExtraAmount();
        //原数据
        //应缴纳金额
        BigDecimal oughtAmount = ssPaymentCom.getOughtAmount();
        //退账抵扣费用
        BigDecimal refundDeducted = ssPaymentCom.getRefundDeducted();
        //调整抵扣费用
        BigDecimal adjustDeducted = ssPaymentCom.getAdjustDeducted();

        //计算
        BigDecimal totalPayAmount = new BigDecimal(0);
        if(ifDeductedIntoPay == 0){
            totalPayAmount = oughtAmount.add(extraAmount);
        }else{
            totalPayAmount = oughtAmount.add(extraAmount).add(refundDeducted).add(adjustDeducted);
        }

        //为原数据放入新的值
        ssPaymentCom.setExtraAmount(extraAmount);
        ssPaymentCom.setIfDeductedIntoPay(ifDeductedIntoPay);
        ssPaymentCom.setTotalPayAmount(totalPayAmount);
        ssPaymentCom.setRemark(ssPaymentComBO.getRemark());
        ssPaymentCom.setModifiedBy("张三");
        ssPaymentCom.setModifiedTime(LocalDateTime.now());
        //保存
        baseMapper.updateById(ssPaymentCom);


        //如果有批次则重算批次的值

        return json;
    }

    @Override
    public JsonResult<String> doAddBatch(SsAddPaymentBO ssAddPaymentBO){
        JsonResult<String> json = new JsonResult<String>();
        json.setCode(0);
        json.setMessage("成功");

        return json;
    }
}
