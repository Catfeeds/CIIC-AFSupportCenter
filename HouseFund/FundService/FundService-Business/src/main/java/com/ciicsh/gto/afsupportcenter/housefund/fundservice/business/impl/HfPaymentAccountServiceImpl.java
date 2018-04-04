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
    public JsonResult delHfPayment(String paymentId){
        //验证是否符合删除条件，代码暂略

        //删除payment_com
        Map map=new HashMap();
        map.put("paymentId",paymentId);
        hfPaymentComMapper.deleteByMap(map);
        //更新payment_account
        HfPaymentAccount hfPaymentAccount=new HfPaymentAccount();
        hfPaymentAccount.setPaymentId(Long.valueOf(0));
        Wrapper<HfPaymentAccount> wrapperAccount = new EntityWrapper();
        wrapperAccount.where(" is_active = 1 AND payment_id={0}", paymentId);
        hfPaymentAccountMapper.update(hfPaymentAccount,wrapperAccount);
        //更新payment
        HfPayment hfPayment =new HfPayment();
        hfPayment.setActive(false);
        Wrapper<HfPayment> wrapperPayment = new EntityWrapper();
        wrapperPayment.where(" is_active = 1 AND payment_id={0}", paymentId);
        hfPaymentMapper.update(hfPayment,wrapperPayment);

        return JsonResultKit.of("生成操作成功！");
    }
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updatePaymentInfo(Long pkId, String remark, Integer payStatus) {
        HfPayment hfPayment = new HfPayment();
        hfPayment.setPaymentId(pkId);
        hfPayment = hfPaymentMapper.selectOne(hfPayment);
        if(null != hfPayment){
            switch (payStatus){
                case -1:
                    hfPayment.setPaymentState(4);
                    hfPayment.setRejectionRemark(remark);
                    break;
                case 8:
                    hfPayment.setPaymentState(5);
                    break;
                case 9:
                    hfPayment.setFinancePaymentDate(new Date());
                    break;
            }
            hfPayment.setModifiedBy("system");
            hfPayment.setModifiedTime(new Date());
            Integer val = hfPaymentMapper.updateById(hfPayment);
            if(val > 0){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }
}
