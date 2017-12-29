package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentSrarchBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPayment;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsPaymentMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsPaymentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPaymentCom;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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


    @Autowired
    SsPaymentComMapper ssPaymentComMapper;

    @Override
    public PageRows<SsPaymentBO> paymentQuery(PageInfo pageInfo) {
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.paymentQuery(pageInfo.toJavaObject(SsPaymentBO.class)));
    }

    @Override
    public List<SsPaymentBO> showAddBatch(SsPaymentSrarchBO paymentSrarchDTO){
        return baseMapper.searchPaymentByAccountTypeAndState(paymentSrarchDTO);
    }

    @Override
    public JsonResult<String> doApplyPay(SsPayment ssPayment){
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);

        //验证
        //根据ID获取到记录
        ssPayment = baseMapper.selectOne(ssPayment);

        //验证客户费用ID是否能取到数据
        if(!Optional.ofNullable(ssPayment).isPresent()){
            json.setCode(2);
            json.setMessage("没有与选中列匹配的支付批次");
            return json;
        }

        //验证状态,只有3 ,可付 5,内部审批批退 状态的数据可申请支付
        if(3 != ssPayment.getPaymentState() && 5 != ssPayment.getPaymentState()){
            json.setCode(3);
            json.setMessage("只有可付和内部审批批退状态的记录可申请支付");
            return json;
        }
        //验证结束

        //执行业务
        //将批次状态改为申请中
        ssPayment.setPaymentState(4);
        ssPayment.setRequestUser("张三");
        ssPayment.setRequestDate(LocalDate.now());
        baseMapper.updateById(ssPayment);

        //将批次下的客户费用明细的状态也改为申请中
        //取出批次下所有的客户费用
        List<SsPaymentCom> ssPaymentComList= ssPaymentComMapper.getPaymentComByPaymentId(ssPayment.getPaymentId());
        if(Optional.ofNullable(ssPaymentComList).isPresent()) {
            for (int i = 0; i < ssPaymentComList.size(); i++) {
                //修改状态为申请中
                SsPaymentCom ssPaymentCom = ssPaymentComList.get(i);
                ssPaymentCom.setPaymentState(4);
                ssPaymentCom.setModifiedBy("张三");
                ssPaymentCom.setModifiedTime(LocalDateTime.now());
                ssPaymentComMapper.updateById(ssPaymentCom);
            }
        }
        return json;
    }

    @Override
    public JsonResult<String> doDelPayment(SsPayment ssPayment) {
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);

        //验证
        //根据ID获取到记录
        ssPayment = baseMapper.selectOne(ssPayment);

        //验证客户费用ID是否能取到数据
        if (!Optional.ofNullable(ssPayment).isPresent()) {
            json.setCode(2);
            json.setMessage("没有与选中列匹配的支付批次");
            return json;
        }

        //验证状态,只有3 ,可付 5,内部审批批退 状态的数据可申请支付
        if (3 != ssPayment.getPaymentState() && 5 != ssPayment.getPaymentState()) {
            json.setCode(3);
            json.setMessage("只有可付和内部审批批退状态的记录可删除");
            return json;
        }
        //验证结束
        //将批次逻辑删除
        ssPayment.setActive(false);
        ssPayment.setModifiedBy("张三");
        ssPayment.setModifiedTime(LocalDateTime.now());
        baseMapper.updateById(ssPayment);

        //将批次下的客户费用明细移出批次
        //取出批次下所有的客户费用
        List<SsPaymentCom> ssPaymentComList= ssPaymentComMapper.getPaymentComByPaymentId(ssPayment.getPaymentId());
        if(Optional.ofNullable(ssPaymentComList).isPresent()) {
            for (int i = 0; i < ssPaymentComList.size(); i++) {
                //修改状态为申请中
                SsPaymentCom ssPaymentCom = ssPaymentComList.get(i);
                ssPaymentCom.setPaymentId(null);
                ssPaymentCom.setModifiedBy("张三");
                ssPaymentCom.setModifiedTime(LocalDateTime.now());
                ssPaymentComMapper.updateAllColumnById(ssPaymentCom);
            }
        }
        return json;

    }

    @Override
    public JsonResult<String> addPayment(SsPayment ssPayment){
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);

        SsPayment newSsPayment = new  SsPayment();
        //传入值
        newSsPayment.setPaymentMonth(ssPayment.getPaymentMonth());
        newSsPayment.setPaymentBatchNum(ssPayment.getPaymentBatchNum());
        newSsPayment.setAccountType(ssPayment.getAccountType());
        //默认值
        newSsPayment.setPaymentState(3);
        newSsPayment.setTotalEmpCount(0);
        newSsPayment.setTotalCom(0);
        newSsPayment.setTotalAccount(0);
        newSsPayment.setTotalApplicationAmount(new BigDecimal(0));
        newSsPayment.setActive(true);
        newSsPayment.setCreatedBy("张三");
        newSsPayment.setModifiedBy("张三");
        newSsPayment.setCreatedTime(LocalDateTime.now());

        baseMapper.insert(newSsPayment);

        return json;
    }
}
