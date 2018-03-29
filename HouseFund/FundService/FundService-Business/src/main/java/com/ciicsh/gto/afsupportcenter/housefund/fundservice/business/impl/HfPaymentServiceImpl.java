package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentComBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentEmpBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentProcessParmBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfArchiveBasePeriodMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComAccountClassMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccountClass;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayapplyCompanyProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayapplyEmployeeProxyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 公积金汇缴支付批次表 服务实现类
 * </p>
 */
@Service
public class HfPaymentServiceImpl extends ServiceImpl<HfPaymentMapper, HfPayment> implements HfPaymentService {

    @Autowired
    private PayapplyServiceProxy payapplyServiceProxy;
    @Autowired
    private HfPaymentMapper hfPaymentMapper;
    @Autowired
    private HfArchiveBasePeriodMapper archiveBasePeriodMapper;
    @Autowired
    private HfComAccountClassMapper comAccountClassMapper;
    @Autowired
    private HfMonthChargeMapper monthChargeMapper;

    /**
     * 获得公积金汇缴支付列表
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfPaymentBo> getFundPays(PageInfo pageInfo) {
        HfPaymentBo hfPaymentBo = pageInfo.toJavaObject(HfPaymentBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfPaymentMapper.getFundPays(hfPaymentBo));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult processApproval(PaymentProcessParmBO processParmBO) {

        HfPayment payment = new HfPayment();
        payment.setPaymentId(Long.parseLong(processParmBO.getPaymentId()));
        payment = hfPaymentMapper.selectOne(payment);
        if(payment.getPaymentState().equals(1) || payment.getPaymentState().equals(4)){
            payment.setPaymentState(2);
            payment.setModifiedTime(new Date());
            payment.setModifiedBy(processParmBO.getOperator());
            Integer result = hfPaymentMapper.updateById(payment);

            if(result > 0){
                return JsonResultKit.of(0,"送审成功!");
            }
            else{
                return JsonResultKit.of(1,"送审失败!");
            }
        }
        else{
            return JsonResultKit.of(1,"该记录不能送审，请检查!");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult processPayment(PaymentProcessParmBO processParmBO) {
        HfPayment payment = new HfPayment();
        payment.setPaymentId(Long.parseLong(processParmBO.getPaymentId()));
        payment = hfPaymentMapper.selectOne(payment);
        JsonResult result = isCanPayment(payment);
        if(result.getCode() > 0){
            return  result;
        }
        else{
            PayApplyProxyDTO resDto = financePayApi(payment);
            com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult<PayApplyProxyDTO> jsRes =
                payapplyServiceProxy.addShHouseFundPayApply(resDto);

            if(jsRes.getCode().equals(0)){
                payment.setPaymentState(3);
                payment.setModifiedTime(new Date());
                payment.setModifiedBy(processParmBO.getOperator());
                Integer val = hfPaymentMapper.updateById(payment);
                if(val > 0){
                    return JsonResultKit.of(0,"汇缴成功！");
                }
                else{
                    return JsonResultKit.of(1,"更新汇缴状态失败,请检查！");
                }
            }
            else{
                return JsonResultKit.of(1,"汇缴失败，请检查!");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult processTicket(PaymentProcessParmBO processParmBO) {

        HfPayment payment = new HfPayment();
        payment.setPaymentId(Long.parseLong(processParmBO.getPaymentId()));
        payment = hfPaymentMapper.selectOne(payment);
        if(payment.getPaymentState().equals(5)){
            List<HfComAccountClass> comAccountClasses = comAccountClassMapper.getAccountClassByPaymentId(Long.parseLong(processParmBO.getPaymentId()));
            if(null != comAccountClasses && comAccountClasses.size() > 0){
                comAccountClasses.forEach(x->{
                    try {
                        String paymentMonth = setPaymentMonth(x.getComHfMonth());
                        String belongMonth = x.getComHfMonth();
                        x.setComHfMonth(paymentMonth);
                        x.setModifiedTime(new Date());
                        x.setModifiedBy(processParmBO.getOperator());
                        comAccountClassMapper.updateById(x);
                        createStandardMonthCharge(x,paymentMonth,belongMonth,processParmBO);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            }
            payment.setPaymentState(6);
            payment.setModifiedTime(new Date());
            payment.setModifiedBy(processParmBO.getOperator());
            hfPaymentMapper.updateById(payment);
            return JsonResultKit.of(0,"出票成功！");
        }
        else{
            return JsonResultKit.of(1,"该记录不能出票，请检查!");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult processReceipt(PaymentProcessParmBO processParmBO) {
        HfPayment payment = new HfPayment();
        payment.setPaymentId(Long.parseLong(processParmBO.getPaymentId()));
        payment = hfPaymentMapper.selectOne(payment);
        if(payment.getPaymentState().equals(6)){
            payment.setPaymentState(7);
            payment.setModifiedTime(new Date());
            payment.setModifiedBy(processParmBO.getOperator());
            Integer result = hfPaymentMapper.updateById(payment);
            if(result > 0){
                return JsonResultKit.of(0,"回单成功!");
            }
            else{
                return JsonResultKit.of(1,"回单失败!");
            }
        }
        else{
            return JsonResultKit.of(1,"该记录不能回单，请检查!");
        }
    }


    private JsonResult isCanPayment(HfPayment payment) {
        if(payment == null){
            return JsonResultKit.of(1,"支付信息为空！");
        }
        else if(!payment.getPaymentState().equals(2)){
            return JsonResultKit.of(1,"该记录不能汇缴，请检查!");
        }
        else{
            return JsonResultKit.of(0,"验证通过！");
        }
    }

    /**
     * 执行调用财务API
     * @param hfPayment
     */
    private PayApplyProxyDTO financePayApi(HfPayment hfPayment) {
        PayApplyProxyDTO dto = new PayApplyProxyDTO();
        dto.setDepartmentName("福利保障部公积金"); //待定
        dto.setIsFinancedept(0);
        dto.setBusinessType(2);//业务类型
        dto.setBusinessPkId(hfPayment.getPaymentId());//业务方主键ID(整型)
        dto.setPayWay(3);//转账
        dto.setPayAmount(hfPayment.getTotalApplicationAmonut());//申请支付金额 （待定）
        dto.setReceiver("公积金中心");//收款方名称（待定）
        dto.setApplyer(hfPayment.getRequestUser());  //申请人
        dto.setApplyDate(StringUtil.getNow());//申请日期

        //支付独立公积金费用+支付月份  1 大库、2 外包、3独立户
        if (hfPayment.getHfAccountType() == 1) {
            dto.setPayReason("支付大库公积金费用" + hfPayment.getPaymentMonth());
        } else if (hfPayment.getHfAccountType() == 2) {
            dto.setPayReason("支付外包公积金费用" + hfPayment.getPaymentMonth());
        } else if (hfPayment.getHfAccountType() == 3) {
            dto.setPayReason("支付独立户公积金费用" + hfPayment.getPaymentMonth());
        }
        dto.setPayPurpose(dto.getPayReason());
        dto.setReceiveAccountId(0); //待定(付款银行ID)
        dto.setReceiveAccount(""); //待定(付款银行)
        dto.setPresident("");//待定(总经理)
        dto.setLeader("");//待定(分管领导)
        dto.setDepartmentManager(""); //待定(部门经理)
        dto.setReviewer("");//待定(审核人)

        List<PayapplyCompanyProxyDTO> paymentComList = baseMapper.getPaymentComList(hfPayment.getPaymentId()).stream().map(x->toCompanyDto(x)).collect(Collectors.toList());
        List<PayapplyEmployeeProxyDTO> paymentEmpList = baseMapper.getPaymentEmpList(hfPayment.getPaymentId(),hfPayment.getPaymentMonth()).stream().map(x->toEmployeeDto(x)).collect(Collectors.toList());

        dto.setCompanyList(paymentComList);
        dto.setEmployeeList(paymentEmpList);

        return dto;
    }


    private PayapplyCompanyProxyDTO toCompanyDto(PaymentComBO comBO){
        PayapplyCompanyProxyDTO companyProxyDTO = new PayapplyCompanyProxyDTO();
        BeanUtils.copyProperties(comBO,companyProxyDTO);
        return companyProxyDTO;
    }

    private PayapplyEmployeeProxyDTO toEmployeeDto(PaymentEmpBO empBO){
        PayapplyEmployeeProxyDTO employeeProxyDTO = new PayapplyEmployeeProxyDTO();
        BeanUtils.copyProperties(empBO,employeeProxyDTO);
        return employeeProxyDTO;
    }

    private void createStandardMonthCharge(HfComAccountClass accountClass, String paymentMonth, String belongMonth,PaymentProcessParmBO processParmBO){
        List<HfArchiveBasePeriod> basePeriods = archiveBasePeriodMapper.getArchiveBasePeriods(accountClass.getComAccountId(),paymentMonth,belongMonth);
        if(null != basePeriods && basePeriods.size() > 0){
            basePeriods.forEach(x->monthChargeMapper.insert(setMonthCharge(x,paymentMonth,processParmBO)));
        }
    }

    private HfMonthCharge setMonthCharge(HfArchiveBasePeriod basePeriod,String paymentMonth,PaymentProcessParmBO processParmBO){
        HfMonthCharge monthCharge = new HfMonthCharge();
        monthCharge.setEmpTaskId(basePeriod.getEmpTaskId());
        monthCharge.setEmpArchiveId(basePeriod.getEmpArchiveId());
        monthCharge.setCompanyId(basePeriod.getCompanyId());
        monthCharge.setEmployeeId(basePeriod.getEmployeeId());
        monthCharge.setHfType(basePeriod.getHfType());
        monthCharge.setHfMonth(paymentMonth);
        monthCharge.setSsMonthBelong(paymentMonth);
        monthCharge.setPaymentType(1);
        monthCharge.setBase(basePeriod.getBaseAmount());
        monthCharge.setRatio(basePeriod.getRatio());
        monthCharge.setRatioEmp(basePeriod.getRatioEmp());
        monthCharge.setRatioCom(basePeriod.getRatioCom());
        monthCharge.setAmount(basePeriod.getAmount());
        monthCharge.setEmpAmount(basePeriod.getAmountEmp());
        monthCharge.setComAmount(basePeriod.getComAmount());
        monthCharge.setActive(true);
        monthCharge.setCreatedTime(LocalDateTime.now());
        monthCharge.setCreatedBy(processParmBO.getOperator());
        monthCharge.setModifiedTime(LocalDateTime.now());
        monthCharge.setModifiedBy(processParmBO.getOperator());
        return monthCharge;
    }


    public String setPaymentMonth(String paymentMonth) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(paymentMonth));
        calendar.add(Calendar.MONTH,1);
        return dateFormat.format(calendar.getTime());
    }
}