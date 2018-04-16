package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HfCreatePaymentAccountBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HfFundPayCreatePaymentAccountPara;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
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
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    HfPaymentAccountMapper hfPaymentAccountMapper;
    /**
     * 公积金汇缴支付-生成汇缴支付客户名单
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult createPaymentCom(HfFundPayCreatePaymentAccountPara params) {
        //查询出所有前端选择的基本和补充公积金账户
        HfCreatePaymentAccountBO hfCreatePaymentAccountBO=new HfCreatePaymentAccountBO();

        List<HfCreatePaymentAccountBO> paymentAccountList= hfPaymentComMapper.selectPaymentAccount( params.getListData());
        if (paymentAccountList.size()==0){
            return JsonResultKit.ofError("无数据可生成！");
        }else{
            hfCreatePaymentAccountBO=paymentAccountList.get(0);
        }
        HfPaymentAccount hfPaymentAccount=new HfPaymentAccount();
        Long paymentId= insertHfPayment(hfCreatePaymentAccountBO,params);
        BigDecimal totalApplicationAmount= BigDecimal.ZERO;
        int empCount=0;
        HfCreatePaymentAccountBO accountMap =new HfCreatePaymentAccountBO();
        for(int i=0; i<paymentAccountList.size();i++){
            accountMap=paymentAccountList.get(i);
            totalApplicationAmount=totalApplicationAmount.add(
                Optional.ofNullable(accountMap.getSumAmount()).orElse(BigDecimal.valueOf(0))
                    .add(Optional.ofNullable(accountMap.getPayInBackAmount()).orElse(BigDecimal.valueOf(0))));
        //   empCount=empCount+accountMap.getEmpCount();
            insertHfPaymentCom(accountMap,paymentId);
            hfPaymentAccount.setPaymentId(paymentId);
            hfPaymentAccount.setPaymentAccountId(accountMap.getPaymentAccountId());
            hfPaymentAccount.setModifiedBy(UserContext.getUser().getDisplayName());
            hfPaymentAccount.setModifiedTime(LocalDateTime.now());
            hfPaymentAccountMapper.updateById(hfPaymentAccount);
        }
       /* paymentAccountList.forEach((accountMap)->{
            totalApplicationAmount.add(accountMap.getSumAmount()).add(accountMap.getPayInBackAmount());
            empCount=empCount+accountMap.getEmpCount();
            insertHfPaymentCom(accountMap,paymentId);
            hfPaymentAccount.setPaymentId(paymentId);
            hfPaymentAccount.setPaymentAccountId(accountMap.getPaymentAccountId());
            hfPaymentAccount.setModifiedBy(UserContext.getUser().getDisplayName());
            hfPaymentAccount.setModifiedTime(LocalDateTime.now());
            hfPaymentAccountMapper.updateById(hfPaymentAccount);
        });*/
        //更新申请支付总金额
        HfPayment hfPayment=new HfPayment();
        hfPayment.setPaymentId(paymentId);
        hfPayment.setTotalApplicationAmonut(totalApplicationAmount);
       // hfPayment.setTotalEmpCount(empCount);
        hfPaymentMapper.updateById(hfPayment);
        return JsonResultKit.of();

    }

    /**
     * 插入HfPayment 支付批次表
     * @param
     * @return
     */
    private Long insertHfPayment(HfCreatePaymentAccountBO paymentAccountMap,HfFundPayCreatePaymentAccountPara params){
        HfPayment hfPayment=new HfPayment();
        Wrapper<HfPayment> wrapper = new EntityWrapper<>();
        wrapper.where(" is_active = 1 AND create_payment_date=CURDATE() ");
        Integer paymentCount = hfPaymentMapper.selectCount(wrapper)+1;
        String serial = String.format("%04d", paymentCount);
        String today=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        hfPayment.setPaymentBatchNum(today+serial);
        hfPayment.setPaymentState(1);//可付
        hfPayment.setPaymentMonth(params.getPaymentMonth());
        hfPayment.setCreatePaymentUser(UserContext.getUser().getDisplayName());
        hfPayment.setCreatePaymentDate(new Date());
        hfPayment.setHfAccountType( paymentAccountMap.getHfAccountType() );
        hfPayment.setReceiver(params.getPayee());
        hfPayment.setPaymentWay(params.getPaymentWay());
        hfPayment.setCreatedBy(UserContext.getUser().getDisplayName());
        hfPayment.setModifiedBy(UserContext.getUser().getDisplayName());
        hfPaymentMapper.insert(hfPayment);
        return hfPayment.getPaymentId();
    }

    private void insertHfPaymentCom(HfCreatePaymentAccountBO hfCreatePaymentAccountBO, long paymentId){
        HfPaymentCom hfPaymentCom=new HfPaymentCom();
        hfPaymentCom.setPaymentId(paymentId);
        hfPaymentCom.setComAccountId(hfCreatePaymentAccountBO.getComAccountId());
        hfPaymentCom.setComAccountClassId(hfCreatePaymentAccountBO.getComAccountClassId());
        hfPaymentCom.setHfType(hfCreatePaymentAccountBO.getHfType());
        hfPaymentCom.setCompanyId(hfCreatePaymentAccountBO.getCompanyId());
        hfPaymentCom.setPaymentBank(String.valueOf(hfCreatePaymentAccountBO.getPaymentBank()));
        hfPaymentCom.setRemittedAmount(hfCreatePaymentAccountBO.getSumAmount());//汇缴金额
        hfPaymentCom.setRepairAmount(hfCreatePaymentAccountBO.getPayInBackAmount());//补缴金额
        hfPaymentCom.setRemittedCountEmp(hfCreatePaymentAccountBO.getEmpCount());
        hfPaymentCom.setCreatedBy(UserContext.getUser().getDisplayName());
        hfPaymentCom.setModifiedBy(UserContext.getUser().getDisplayName());
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
