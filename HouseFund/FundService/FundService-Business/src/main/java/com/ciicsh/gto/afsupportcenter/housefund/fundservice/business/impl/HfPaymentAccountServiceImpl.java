package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    HfPaymentComMapper hfPaymentComMapper;

    @Override
    public PageRows<HfPaymentAccountBo> getMakePayLists(PageInfo pageInfo){
        HfPaymentAccountBo hfPaymentAccountBo = pageInfo.toJavaObject(HfPaymentAccountBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfPaymentAccountMapper.getMakePayLists(hfPaymentAccountBo));
    }
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult delHfPayment(String paymentId){
        //判断是否符合删除条件
        Integer paymentState=hfPaymentMapper.selectById(paymentId).getPaymentState();
        if(paymentState!=1 &&  paymentState!=4){
            return JsonResultKit.ofError("删除操作失败！原因是支付状态不符合删除条件。");
        }
        try{
            //删除payment_com
            Map map=new HashMap();
            map.put("payment_id",paymentId);
            hfPaymentComMapper.deleteByMap(map);
            //更新payment_account  暂用Wrapper后续修改掉
            HfPaymentAccount hfPaymentAccount=new HfPaymentAccount();
            hfPaymentAccount.setPaymentId(Long.valueOf(0));
            Wrapper<HfPaymentAccount> wrapperAccount = new EntityWrapper();
            wrapperAccount.where(" is_active = 1 AND payment_id={0}", paymentId);
            hfPaymentAccountMapper.update(hfPaymentAccount,wrapperAccount);
            //更新payment   暂用Wrapper后续修改掉
            HfPayment hfPayment =new HfPayment();
            hfPayment.setActive(false);
            Wrapper<HfPayment> wrapperPayment = new EntityWrapper();
            wrapperPayment.where(" is_active = 1 AND payment_id={0}", paymentId);
            hfPaymentMapper.update(hfPayment,wrapperPayment);
        }catch (Exception e){
            return JsonResultKit.ofError("删除操作失败！");
        }
        return JsonResultKit.of("删除操作成功！");
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
