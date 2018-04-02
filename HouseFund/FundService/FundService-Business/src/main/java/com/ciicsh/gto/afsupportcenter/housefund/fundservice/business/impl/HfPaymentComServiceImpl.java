package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentComService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;

import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 公积金汇缴支付公司名单 服务实现类
 * </p>
 */
@Service
public class HfPaymentComServiceImpl extends ServiceImpl<HfPaymentComMapper, HfPaymentCom> implements
    HfPaymentComService {
    @Autowired
    HfPaymentComMapper hfPaymentComMapper;
    @Autowired
    HfPaymentMapper hfPaymentMapper;
    /**
     * 公积金汇缴支付-生成汇缴支付客户名单
     */
    @Override
    public JsonResult createPaymentCom(List paymentAccountIds) {
        //查询出所有前端选择的基本和补充公积金账户
        Map<String,Object> paymentAccountMap=new HashMap<>();
        List<Map<String,Object>> paymentAccountList= hfPaymentComMapper.selectPaymentAccount( paymentAccountIds);
        if (paymentAccountList.size()==0){
            return JsonResultKit.ofError("无数据可生成！");
        }else{
            paymentAccountMap=paymentAccountList.get(0);
        }

        Long paymentId= insertHfPayment(  paymentAccountMap);
        paymentAccountList.forEach((accountMap)->{
            insertHfPaymentCom(accountMap,paymentId);
        });
        return JsonResultKit.of("生成操作成功！");
    }

    /**
     * 插入HfPayment 支付批次表
     * @param
     * @return
     */
    private Long insertHfPayment(Map<String,Object> paymentAccountMap){
        HfPayment hfPayment=new HfPayment();
        Wrapper<HfPayment> wrapper = new EntityWrapper<>();
        wrapper.where(" is_active = 1 AND create_payment_date=CURDATE() ");
        Integer paymentCount = hfPaymentMapper.selectCount(wrapper);
        String serial = String.format("%04d", paymentCount);
        String today=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        hfPayment.setPaymentBatchNum(today+serial);
        hfPayment.setPaymentState(1);//可付
        hfPayment.setRequestUser(UserContext.getUserName());
        hfPayment.setRequestDate(new Date());
        hfPayment.setHfAccountType(Integer.parseInt(paymentAccountMap.get("hf_account_type").toString()));
        hfPaymentMapper.insert(hfPayment);
        return hfPayment.getPaymentId();
    }

    private void insertHfPaymentCom(Map<String,Object> accountMap,long paymentId){
        HfPaymentCom hfPaymentCom=new HfPaymentCom();
        hfPaymentCom.setPaymentId(paymentId);
        hfPaymentCom.setComAccountId(Long.valueOf(accountMap.get("com_account_id").toString()));
        hfPaymentCom.setComAccountClassId(Long.valueOf(accountMap.get("com_account_class_id").toString()));
        hfPaymentCom.setHfType(Integer.valueOf(accountMap.get("hf_type").toString()));
        hfPaymentCom.setCompanyId(accountMap.get("company_id").toString());
        hfPaymentCom.setPaymentBank(accountMap.get("payment_bank").toString());

        baseMapper.insert(hfPaymentCom);
    }
    private void updatePayment(){

    }
    private void updatePaymentCom(){

    }

    /**
     * 获得公积金汇缴支付详细操作数据
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfPaymentComBo> getFundPaysDetailOperationData(PageInfo pageInfo) {
        HfPaymentComBo hfPaymentComBo = pageInfo.toJavaObject(HfPaymentComBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfPaymentComMapper.getFundPaysDetailOperationData(hfPaymentComBo));
    }
}
