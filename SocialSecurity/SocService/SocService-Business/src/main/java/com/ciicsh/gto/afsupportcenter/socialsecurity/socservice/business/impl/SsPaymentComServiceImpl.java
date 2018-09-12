package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAddPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsDelPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsPaymentMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPayment;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPaymentCom;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyPayStatusDTO;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsPaymentComServiceImpl extends ServiceImpl<SsPaymentComMapper, SsPaymentCom> implements
    SsPaymentComService {
    @Autowired
    SsPaymentMapper ssPaymentMapper;


    @Override
    public PageRows<SsPaymentComBO> paymentComQuery(PageInfo pageInfo) {
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.paymentComQuery(pageInfo.toJavaObject(SsPaymentComBO
            .class)));
    }

    @Override
    public List<SsPaymentComBO> paymentComQueryExport(SsPaymentComBO ssPaymentComBO) {
        return baseMapper.paymentComQuery(ssPaymentComBO);
    }

    @Override
    public JsonResult<String> saveAdjustment(SsPaymentComBO ssPaymentComBO) {
        JsonResult<String> json = new JsonResult<String>();
        //验证状态

        //根据ID取出原数据
        SsPaymentCom ssPaymentCom = baseMapper.selectById(ssPaymentComBO.getPaymentComId());

        //新数据
        //抵扣费用是否纳入支付申请
        int ifDeductedIntoPay = Optional.ofNullable(ssPaymentComBO.getIfDeductedIntoPay()).orElse(0);
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
        BigDecimal totalPayAmount;
        if (ifDeductedIntoPay == 0) {
            totalPayAmount = oughtAmount.add(extraAmount);
        } else {
            totalPayAmount = oughtAmount.add(extraAmount).add(refundDeducted).add(adjustDeducted);
        }

        //为原数据放入新的值
        ssPaymentCom.setExtraAmount(extraAmount);
        ssPaymentCom.setPaymentBalance(totalPayAmount.subtract(ssPaymentCom.getTotalPayAmount()));
        ssPaymentCom.setIfDeductedIntoPay(ifDeductedIntoPay);
//        ssPaymentCom.setTotalPayAmount(totalPayAmount);
        ssPaymentCom.setRemark(ssPaymentComBO.getRemark());
        ssPaymentCom.setModifiedBy(UserContext.getUser().getDisplayName());
        ssPaymentCom.setModifiedTime(LocalDateTime.now());
        //保存
        baseMapper.updateById(ssPaymentCom);


        //如果有批次则重算批次的值

        return json;
    }

    @Override
    public JsonResult<String> doCheck(Long paymentComId) {
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);
        json.setMessage("成功");
        SsPaymentCom ssPaymentCom =new SsPaymentCom();
        ssPaymentCom.setPaymentComId(paymentComId);
        ssPaymentCom = baseMapper.selectOne(ssPaymentCom);
        ssPaymentCom.setIfCheck("1".equals(Optional.ofNullable(ssPaymentCom.getIfCheck()).orElse(0).toString()) ? 0:1);
        ssPaymentCom.setPaymentComId(paymentComId);
        ssPaymentCom.setModifiedBy(UserContext.getUser().getUserId());
        ssPaymentCom.setModifiedDisplayName(UserContext.getUser().getDisplayName());
        ssPaymentCom.setModifiedTime(LocalDateTime.now());
        baseMapper.updateById(ssPaymentCom);
        return json;
    }

    @Override
    public JsonResult<String> doAddBatch(SsAddPaymentBO ssAddPaymentBO) {
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);
        json.setMessage("成功");
        //校验数据的可操作性
        //验证用数据
        List<Long> paymentComIdList = ssAddPaymentBO.getPaymentComIdList();
        //验证是否有选中的客户费用
        if (!Optional.ofNullable(paymentComIdList).isPresent()) {
            json.setCode(1);
            json.setMessage("没有选中的客户费用");
            return json;
        }
        //循环取出客户费用
        List<SsPaymentCom> paymentComList = new ArrayList<>();
        for (int i = 0; i < paymentComIdList.size(); i++) {
            SsPaymentCom ssPaymentCom = baseMapper.selectById(paymentComIdList.get(i));
            //验证客户费用ID是否能取到数据
            if (!Optional.ofNullable(ssPaymentCom).isPresent()) {
                json.setCode(2);
                json.setMessage("没有与选中列匹配的客户费用记录");
                return json;
            }

            //验证状态,只有3 ,可付 5,内部审批批退 状态的数据可执行添加操作
            if (3 != ssPaymentCom.getPaymentState() && 5 != ssPaymentCom.getPaymentState() && 7 != ssPaymentCom.getPaymentState()) {
                json.setCode(3);
                json.setMessage("只有可付和内部审批批退状态的记录可以进行添加批次操作");
                return json;
            }

            //验证原本是否已有关联批次
            if (Optional.ofNullable(ssPaymentCom.getPaymentId()).isPresent()) {
                json.setCode(4);
                json.setMessage("已加入批次的数据不可再次加入别的批次");
                return json;
            }
            paymentComList.add(ssPaymentCom);
        }
        //取出批次
        SsPayment ssPayment = ssPaymentMapper.selectById(ssAddPaymentBO.getPaymentId());
        //验证批次是否存在
        if (!Optional.ofNullable(ssPayment).isPresent()) {
            json.setCode(2);
            json.setMessage("批次信息不存在");
            return json;
        }
        //验证状态,只有3 ,可付 5,内部审批批退 状态的数据可执行添加操作
        if (3 != ssPayment.getPaymentState() && 5 != ssPayment.getPaymentState() && 7 != ssPayment.getPaymentState()) {
            json.setCode(3);
            json.setMessage("只有可付和内部审批批退状态的批次可被添加");
            return json;
        }
        //验证结束

        //处理业务
        //将批次ID加入客户费用中
        for (int i = 0; i < paymentComList.size(); i++) {
            SsPaymentCom ssPaymentCom = paymentComList.get(i);
            ssPaymentCom.setPaymentId(ssAddPaymentBO.getPaymentId());
            baseMapper.updateById(ssPaymentCom);
        }
        //重算批次信息
        json = calculatePayment(ssAddPaymentBO.getPaymentId());

        return json;
    }
//生成支付批次
    @Override
    public JsonResult<String> addPaymentBatch(SsAddPaymentBO ssAddPaymentBO) {
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);
        json.setMessage("成功");
        //校验数据的可操作性
        //验证用数据
        List<Long> paymentComIdList = ssAddPaymentBO.getPaymentComIdList();
        //验证是否有选中的客户费用
        if (!Optional.ofNullable(paymentComIdList).isPresent()) {
            json.setCode(1);
            json.setMessage("没有选中的客户费用");
            return json;
        }
        //循环取出客户费用
        List<SsPaymentCom> paymentComList = new ArrayList<>();
        for (int i = 0; i < paymentComIdList.size(); i++) {
            SsPaymentCom ssPaymentCom = baseMapper.selectById(paymentComIdList.get(i));
            //验证客户费用ID是否能取到数据
            if (!Optional.ofNullable(ssPaymentCom).isPresent()) {
                json.setCode(2);
                json.setMessage("没有与选中列匹配的客户费用记录");
                return json;
            }

            //验证状态,只有3 ,可付 5,内部审批批退 状态的数据可执行添加操作
            if (3 != ssPaymentCom.getPaymentState() && 5 != ssPaymentCom.getPaymentState() && 7 != ssPaymentCom.getPaymentState()) {
                json.setCode(3);
                json.setMessage("只有可付和内部审批批退状态的记录可以进行添加批次操作");
                return json;
            }

            //验证原本是否已有关联批次
            if (Optional.ofNullable(ssPaymentCom.getPaymentId()).isPresent()) {
                json.setCode(4);
                json.setMessage("已加入批次的数据不可再次加入别的批次");
                return json;
            }
            paymentComList.add(ssPaymentCom);
        }

        //验证结束

        //处理业务
        SsPayment newSsPayment = new SsPayment();
        //传入值

        //newSsPayment.setPaymentMonth(LocalDate.now().plusMonths(-1).format( DateTimeFormatter.ofPattern("yyyyMM")));
        newSsPayment.setPaymentMonth(ssAddPaymentBO.getPaymentMonth());
        newSsPayment.setAccountType(ssAddPaymentBO.getSsAccountType());
        //默认值
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        newSsPayment.setPaymentBatchNum( LocalDateTime.now().format(formatter).toString());
        newSsPayment.setPaymentState(3);
        newSsPayment.setTotalEmpCount(0);
        newSsPayment.setTotalCom(0);
        newSsPayment.setTotalAccount(0);
        newSsPayment.setTotalApplicationAmount(new BigDecimal(0));
        newSsPayment.setActive(true);
        newSsPayment.setCreatedBy(UserContext.getUser().getDisplayName());
        newSsPayment.setModifiedBy(UserContext.getUser().getDisplayName());
        newSsPayment.setCreatedTime(LocalDateTime.now());
        ssPaymentMapper.insert(newSsPayment);

        //将批次ID加入客户费用中
        for (int i = 0; i < paymentComList.size(); i++) {
            SsPaymentCom ssPaymentCom = paymentComList.get(i);
            ssPaymentCom.setPaymentId(newSsPayment.getPaymentId());
            baseMapper.updateById(ssPaymentCom);
        }
        //重算批次信息
        json = calculatePayment(newSsPayment.getPaymentId());

        return json;
    }

    @Override
    public JsonResult<String> calculatePayment(Long paymentId) {
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);
        //取出批次
        SsPayment ssPayment = ssPaymentMapper.selectById(paymentId);
        //验证批次是否存在
        if (!Optional.ofNullable(ssPayment).isPresent()) {
            json.setCode(2);
            json.setMessage("批次信息不存在");
            return json;
        }

        //取出批次下所有的客户费用
        List<SsPaymentCom> ssPaymentComList = baseMapper.getPaymentComByPaymentId(paymentId);

        //计算阶段
        //申请总金额
        BigDecimal totalApplicationAmount = new BigDecimal(0);
        //总雇员数
        int totalEmpCount = 0;
        //账户总数
        int totalAccount = 0;
        //客户总数
        int totalCom = 0;

        //账户map
        Map<Long, Object> comAccountMap = new HashedMap();
        //客户map
        Map<String, Object> companyMap = new HashedMap();

        //循环累加
        if (Optional.ofNullable(ssPaymentComList).isPresent()) {
            for (int i = 0; i < ssPaymentComList.size(); i++) {
                SsPaymentCom ssPaymentCom = ssPaymentComList.get(i);
                //申请总金额
                if(ssPaymentCom.getTotalPayAmount()!=null)
                    totalApplicationAmount = totalApplicationAmount.add(ssPaymentCom.getTotalPayAmount());
                //总雇员数
                if(ssPaymentCom.getEmpCount()!=null)
                    totalEmpCount = totalEmpCount + ssPaymentCom.getEmpCount();
                //账户总数
                if (!comAccountMap.containsKey(ssPaymentCom.getComAccountId())) {
                    totalAccount++;
                    comAccountMap.put(ssPaymentCom.getComAccountId(), null);
                }
                //客户总数
                if (!companyMap.containsKey(ssPaymentCom.getCompanyId())) {
                    totalCom++;
                    companyMap.put(ssPaymentCom.getCompanyId(), null);
                }
            }
        }
        //放入值中
        ssPayment.setTotalApplicationAmount(totalApplicationAmount);
        ssPayment.setTotalEmpCount(totalEmpCount);
        ssPayment.setTotalAccount(totalAccount);
        ssPayment.setTotalCom(totalCom);
        ssPayment.setModifiedBy(UserContext.getUserId());
        ssPayment.setModifiedTime(LocalDateTime.now());
        ssPaymentMapper.updateById(ssPayment);

        return json;
    }

    @Override
    public JsonResult<String> doDelBatch(SsDelPaymentBO ssDelPaymentBO) {
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);
        json.setMessage("成功");
        //校验数据的可操作性
        //验证用数据
        List<Long> paymentComIdList = ssDelPaymentBO.getPaymentComIdList();
        //验证是否有选中的客户费用
        if (!Optional.ofNullable(paymentComIdList).isPresent()) {
            json.setCode(1);
            json.setMessage("没有选中的客户费用");
            return json;
        }
        //循环取出客户费用
        List<SsPaymentCom> paymentComList = new ArrayList<>();
        for (int i = 0; i < paymentComIdList.size(); i++) {
            SsPaymentCom ssPaymentCom = baseMapper.selectById(paymentComIdList.get(i));
            //验证客户费用ID是否能取到数据
            if (!Optional.ofNullable(ssPaymentCom).isPresent()) {
                json.setCode(2);
                json.setMessage("没有与选中列匹配的客户费用记录");
                return json;
            }

            //验证状态,只有3 ,可付 5,内部审批批退 状态的数据可执行添加操作
            if (3 != ssPaymentCom.getPaymentState() && 5 != ssPaymentCom.getPaymentState() && 7 != ssPaymentCom
                .getPaymentState()) {
                json.setCode(3);
                json.setMessage("只有可付和内部审批批退状态的记录可以进行添加批次操作");
                return json;
            }

            //验证原本是否已有关联批次
            if (!Optional.ofNullable(ssPaymentCom.getPaymentId()).isPresent()) {
                json.setCode(5);
                json.setMessage("原本没有加入批次的客户费用,无法移除批次");
                return json;
            }
            paymentComList.add(ssPaymentCom);
        }
        //验证结束
        //处理业务
        //需要重算的批次
        List<Long> paymentIdList = new ArrayList<>();

        //将批次ID加入客户费用中
        for (int i = 0; i < paymentComList.size(); i++) {
            SsPaymentCom ssPaymentCom = paymentComList.get(i);
            //放入要重算的批次中
            if (!paymentIdList.contains(ssPaymentCom.getPaymentId())) {
                paymentIdList.add(ssPaymentCom.getPaymentId());
            }
            //将批次移除
            ssPaymentCom.setPaymentId(null);
            baseMapper.updateAllColumnById(ssPaymentCom);
        }
        //重算批次
        if (Optional.ofNullable(paymentIdList).isPresent()) {
            for (int i = 0; i < paymentIdList.size(); i++) {
                calculatePayment(paymentIdList.get(i));
            }
        }

        return json;
    }

    @Override
    public SsPaymentComBO getPaymentComBoByPaymentId(Long paymentComId) {
        return baseMapper.getPaymentComBoByPaymentId(paymentComId);
    }

    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public void saveReviewdePassResult(SsPayment ssPayment,String payApplyCode) {
        //根据ID获取到记录
        ssPayment = ssPaymentMapper.selectOne(ssPayment);

        if (ssPayment != null) {
            //执行业务
            //将批次状态改为已申请到财务部
            ssPayment.setPaymentState(6);
            ssPayment.setPayApplyCode(payApplyCode);
            ssPayment.setModifiedBy("system");
            ssPayment.setModifiedTime(LocalDateTime.now());

            ssPaymentMapper.updateById(ssPayment);

            //将批次下的客户费用明细的状态也改为已申请到财务部
            //取出批次下所有的客户费用
            List<SsPaymentCom> ssPaymentComList = baseMapper.getPaymentComByPaymentId(ssPayment.getPaymentId());
            if (ssPaymentComList != null) {
                SsPaymentCom ssPaymentCom = null;
                for (int i = 0; i < ssPaymentComList.size(); i++) {
                    //修改状态为已申请到财务部
                    ssPaymentCom = ssPaymentComList.get(i);
                    ssPaymentCom.setPaymentState(6);
                    ssPaymentCom.setModifiedBy("system");
                    ssPaymentCom.setModifiedTime(LocalDateTime.now());
                }
                this.updateBatchById(ssPaymentComList);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean savePaymentInfo(PayApplyPayStatusDTO taskMsgDTO ) {
        SsPayment ssPayment = new SsPayment();
        ssPayment.setPaymentId(taskMsgDTO.getBusinessPkId());
        Integer payStatus = taskMsgDTO.getPayStatus();
        String remark=taskMsgDTO.getRemark();
        ssPayment = ssPaymentMapper.selectOne(ssPayment);
        if (null != ssPayment) {
            //更新批次状态 支付状态:付款状态(-1:审核未过;9.支付成功;-9:支付失败;)
            Integer paymentState = (payStatus == 9 ? 8 : 7);
            if(remark != null && remark != ""){
                ssPayment.setRejectionRemark(remark);
            }
            ssPayment.setPaymentState(paymentState);
            ssPayment.setModifiedBy("system");
            ssPayment.setModifiedTime(LocalDateTime.now());
            if(8 == paymentState.intValue()){
                ssPayment.setFinancePaymentDate(DateUtil.dateToLocaleDate(taskMsgDTO.getOptionDateTime()));
            }

            Integer res = ssPaymentMapper.updateById(ssPayment);
            if(res > 0){
                //将批次下的客户费用明细的状态也改为已申请到财务部
                //取出批次下所有的客户费用
                List<SsPaymentCom> paymentComs = baseMapper.getPaymentComByPaymentId(ssPayment.getPaymentId());
                if(null != paymentComs && paymentComs.size() > 0){
                    paymentComs.stream().map(x->{
                        x.setPaymentState(paymentState);
                        x.setModifiedBy("system");
                        x.setModifiedTime(LocalDateTime.now());
                        return x;
                    }).collect(Collectors.toList());
                    this.updateBatchById(paymentComs);
                }
            }
        }
        return true;
    }
}
