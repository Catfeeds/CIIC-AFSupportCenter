package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfRemittedBookParam;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.EmpTaskStatusBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentComBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentEmpBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentProcessParmBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HfCreatePaymentAccountBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HfPaymentComListBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HfPrintRemittedBookBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HfFundPayCreatePaymentAccountPara;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.MoneyToCN;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.EmployeeMonthlyDataProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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
    private HfPaymentComMapper hfPaymentComMapper;
    @Autowired
    private HfArchiveBasePeriodMapper archiveBasePeriodMapper;
    @Autowired
    private HfComAccountClassMapper comAccountClassMapper;
    @Autowired
    private HfMonthChargeMapper monthChargeMapper;
    @Autowired
    private HfEmpTaskMapper empTaskMapper;
    @Autowired
    private HfEmpArchiveMapper empArchiveMapper;
    @Autowired
    private EmployeeMonthlyDataProxy employeeMonthlyDataProxy;
    @Autowired
    HfPaymentAccountMapper hfPaymentAccountMapper;
    /**
     * 获得公积金汇缴支付列表
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfPaymentBo> getFundPays(PageInfo pageInfo) {
        HfPaymentBo hfPaymentBo = pageInfo.toJavaObject(HfPaymentBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfPaymentMapper.getFundPays(hfPaymentBo));
    }

    /**
     * 送审
     * @param processParmBO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult processApproval(PaymentProcessParmBO processParmBO) {
        HfPayment payment = new HfPayment();
        payment.setPaymentId(Long.parseLong(processParmBO.getPaymentId()));
        this.updateHfPaymentAmount(payment.getPaymentId()); //更新汇缴支付总金额
        payment = hfPaymentMapper.selectOne(payment);
        if (payment.getPaymentState().equals(0) || payment.getPaymentState().equals(1) || payment.getPaymentState().equals(4)) {
            payment.setPaymentState(2);
            payment.setModifiedTime(new Date());
            payment.setModifiedBy(processParmBO.getOperator());
            payment.setSendAuditDate(new Date());
            Integer result = hfPaymentMapper.updateById(payment);
            if (result > 0) {
                return JsonResultKit.of(0, "送审成功!");
            } else {
                return JsonResultKit.of(1, "送审失败!");
            }
        } else {
            return JsonResultKit.of(1, "该记录不能送审，请检查!");
        }
    }

    /**
     * 汇缴
     *
     * @param processParmBO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult processPayment(PaymentProcessParmBO processParmBO) {
        HfPayment payment = new HfPayment();
        payment.setPaymentId(Long.parseLong(processParmBO.getPaymentId()));
        payment = hfPaymentMapper.selectOne(payment);
        JsonResult result = isCanPayment(payment);
        if (result.getCode() > 0) {
            return result;
        } else {
            //询问财务是否可付
            try{
                result = enquireFinanceComAccount(payment);
                if(result.getCode() > 0){
                    return result;
                }
            }catch (Exception e){
                return JsonResultKit.of(1, "系统询问财务是否可付，调用接口发生异常");
            }

         //  if(true) return result;  //锁住 不支付申请

            PayApplyProxyDTO resDto = financePayApi(payment);
            com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult<PayApplyProxyDTO> jsRes =new com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult<>();
            try {
               jsRes = payapplyServiceProxy.addShHouseFundPayApply(resDto);
            }catch (Exception e){
                return JsonResultKit.of(1, "系统支付申请，调用接口发生异常");
            }


            if (jsRes.getCode().equals("0")) {
                payment.setPaymentState(3);//汇缴(已申请到财务部 )
                payment.setModifiedTime(new Date());
                payment.setModifiedBy(processParmBO.getOperator());
                payment.setPayApplyCode(jsRes.getData().getPayapplyCode());
                payment.setRequestUser(UserContext.getUser().getDisplayName());
                payment.setRequestDate(new Date());
                Integer val = hfPaymentMapper.updateById(payment);
                if (val > 0) {
                    return JsonResultKit.of(0, "汇缴成功！");
                } else {
                    return JsonResultKit.of(1, "更新汇缴状态失败,请检查！");
                }
            } else {
                return JsonResultKit.of(1, jsRes.getMsg());
            }
        }
    }
    // 询问结算中心是否可付
    private JsonResult enquireFinanceComAccount( HfPayment payment){
        CompanyMonthlyDataProxyDTO proxyDTO = new CompanyMonthlyDataProxyDTO();
        proxyDTO.setBusinessType("2");//上海公积金
        proxyDTO.setBatchMonth( payment.getPaymentMonth());
        proxyDTO.setPayMonth(payment.getPaymentMonth());
        List<HfPaymentComListBO> paymentComList = baseMapper.enquireFinanceComList(payment.getPaymentId());
        List<CompanyProxyDTO> proxyDTOList = CommonTransform.convertToDTOs(paymentComList, CompanyProxyDTO.class);
        proxyDTO.setCompanyList(proxyDTOList);
        com.ciicsh.common.entity.JsonResult<CompanyMonthlyDataProxyDTO> res = employeeMonthlyDataProxy.getCompanyAdvance(proxyDTO);
        List<CompanyProxyDTO> comList =null;
        if(res.getData()!=null){
            comList =res.getData().getCompanyList();
        }
        // 先更新所有客户的财务反馈状态
        for (CompanyProxyDTO companyProxyDTO : comList) {
            baseMapper.updatePaymentComStatus(payment.getPaymentId(),companyProxyDTO.getIsAdvance(),companyProxyDTO.getCompanyId());
        }
        // 其次循环查找是否可付
        for (CompanyProxyDTO companyProxyDTO : comList) {
            if(companyProxyDTO.getIsAdvance().equals("0")){
                return JsonResultKit.of(1, "结算中心告知：该批次中存在未到账的客户，拒绝申请！");
            }
        }
        return JsonResultKit.of(0, "");
    }

    // 出票
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult processTicket(PaymentProcessParmBO processParmBO) {
        HfPayment payment = new HfPayment();
        payment.setPaymentId(Long.parseLong(processParmBO.getPaymentId()));
        payment = hfPaymentMapper.selectOne(payment);
        if ( payment.getPaymentState().equals(5) || (payment.getPaymentWay().equals(0) &&  payment.getPaymentState().equals(2))) {
            List<HfComAccountClass> comAccountClasses = comAccountClassMapper.getAccountClassByPaymentId(Long.parseLong(processParmBO.getPaymentId()));
//            //校验月份是否匹配
//            if (null != comAccountClasses && comAccountClasses.size() > 0) {
//                for (HfComAccountClass x : comAccountClasses) {
//                    if (!payment.getPaymentMonth().equals(x.getComHfMonth())) { //支付月份和class表的hfmonth不相等
//                        return JsonResultKit.of(1, "企业公积金账户："+x.getHfComAccount()+"，请检查该账户的月份是否与支付年月匹配。");
//                    }
//                }
//            }


            if (null != comAccountClasses && comAccountClasses.size() > 0) {
                comAccountClasses.forEach(x -> {
                    try {
                        String paymentMonth = setPaymentMonth(x.getComHfMonth());
                        String belongMonth = x.getComHfMonth();
                        x.setComHfMonth(paymentMonth);
                        x.setModifiedTime(new Date());
                        x.setModifiedBy(processParmBO.getOperator());
                        comAccountClassMapper.updateById(x);
                        createStandardMonthCharge(x, paymentMonth, belongMonth, processParmBO);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            }
            payment.setPaymentState(6);
            payment.setModifiedTime(new Date());
            payment.setModifiedBy(processParmBO.getOperator());
            hfPaymentMapper.updateById(payment);
            //更新任务单相关状态
            updateEmpTaskStatus(payment.getPaymentId());
            return JsonResultKit.of(0, "出票成功！");
        } else {
            return JsonResultKit.of(1, "该记录不能出票，请检查!");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult processReceipt(PaymentProcessParmBO processParmBO) {
        HfPayment payment = new HfPayment();
        payment.setPaymentId(Long.parseLong(processParmBO.getPaymentId()));
        payment = hfPaymentMapper.selectOne(payment);
        if (payment.getPaymentState().equals(6)) {
            payment.setPaymentState(7);
            payment.setModifiedTime(new Date());
            payment.setModifiedBy(processParmBO.getOperator());
            Integer result = hfPaymentMapper.updateById(payment);
            if (result > 0) {
                return JsonResultKit.of(0, "回单成功!");
            } else {
                return JsonResultKit.of(1, "回单失败!");
            }
        } else {
            return JsonResultKit.of(1, "该记录不能回单，请检查!");
        }
    }

    @Override
    public JsonResult printRemittedBook(Long paymentId, Integer hfType) {
        List<HfPrintRemittedBookBO> listPrint = hfPaymentMapper.printRemittedBook(paymentId, hfType);
        if (listPrint.size() == 0) {
            return JsonResultKit.ofError("汇缴书数据为空！");
        }
        //转换打印汇缴书结果数据
        List<HfPrintRemittedBookBO> retListPrint = new ArrayList();
        for (HfPrintRemittedBookBO in : listPrint) {
            HfPrintRemittedBookBO out = new HfPrintRemittedBookBO();
            BeanUtils.copyProperties(in, out);
            out.setMoneyCN(MoneyToCN.number2CNMontrayUnit(in.getRemittedAmount()));
            out.setRemittedAmountArrange("¥"+in.getRemittedAmount().toString().replace(".",""));
            out.setCurYear(LocalDate.now().getYear());
            out.setCurMonth(LocalDate.now().getMonth().getValue());
            out.setCurDay(LocalDate.now().getDayOfMonth());
            String paymentYearMonth =  in.getPaymentMonth();
            if (paymentYearMonth != null) {
                out.setPaymentYear(paymentYearMonth.substring(0,4));
                out.setPaymentMonth(paymentYearMonth.substring(4,6));
            }
            out.setIsRemitted(true);//汇缴打钩
            out.setRepairCountEmp(null);
            out.setRepairAmount(null);
            retListPrint.add(out);

            if (in.getRepairAmount()!=null &&  in.getRepairAmount().compareTo(BigDecimal.ZERO) > 0) { //存在补缴
                out = new HfPrintRemittedBookBO();
                BeanUtils.copyProperties(in, out);
                out.setCurYear(LocalDate.now().getYear());
                out.setCurMonth(LocalDate.now().getMonth().getValue());
                out.setCurDay(LocalDate.now().getDayOfMonth());
                out.setIsRemitted(false);
                out.setPaymentYear(null);
                out.setPaymentMonth(null);
                out.setIsRepair(true);//补缴打钩
                out.setMoneyCN(MoneyToCN.number2CNMontrayUnit(in.getRepairAmount()));
                out.setRemittedAmountArrange("¥"+in.getRepairAmount().toString().replace(".",""));
                out.setRemittedAmountAdd(null);
                out.setRemittedAmountLast(null);
                out.setRemittedCountEmp(null);
                out.setRemittedAmountReduce(null);
                out.setRemittedCountEmpAdd(null);
                out.setRemittedCountEmpLast(null);
                out.setRemittedCountEmpReduce(null);
                out.setRemittedAmount(null);
                retListPrint.add(out);
            }

        }
        return JsonResultKit.of(retListPrint);
    }

    /**
     * 更新支付申请总金额
     * @param paymentId
     * @return
     */
    @Override
    public JsonResult updateHfPaymentAmount(Long paymentId) {
        try {
            HfFundPayCreatePaymentAccountPara params =new HfFundPayCreatePaymentAccountPara();
            params.setPaymentId(paymentId);
            List<HfCreatePaymentAccountBO> paymentAccountList= hfPaymentComMapper.selectPaymentAccount( params);
            BigDecimal totalApplicationAmount= BigDecimal.ZERO;
            HfCreatePaymentAccountBO accountMap =new HfCreatePaymentAccountBO();
            for(int i=0; i<paymentAccountList.size();i++){
                accountMap=paymentAccountList.get(i);
                totalApplicationAmount=totalApplicationAmount.add(
                    Optional.ofNullable(accountMap.getRemittedAmount()).orElse(BigDecimal.valueOf(0))
                        .add(Optional.ofNullable(accountMap.getRepairAmount()).orElse(BigDecimal.valueOf(0))));
            }
            //更新申请支付总金额
            HfPayment hfPayment=new HfPayment();
            hfPayment.setPaymentId(paymentId);
            hfPayment.setTotalApplicationAmonut(totalApplicationAmount);
            hfPaymentMapper.updateById(hfPayment);

        }catch (Exception e){
            return JsonResultKit.of(1, "申请支付总金额更新异常！");
        }
        return JsonResultKit.of(0, "申请支付总金额更新成功！");
    }

    @Override
    public Integer canEmpTaskHandleByPayment(List<String> paymentMonthList, Long comAccountId, Integer hfType) {
        return baseMapper.canEmpTaskHandleByPayment(paymentMonthList, comAccountId, hfType);
    }

    @Override
    public void updatePaymentStatusAfterHandle(List<String> paymentMonthList, Long comAccountId, Integer hfType) {
        baseMapper.updatePaymentStatusAfterHandle(paymentMonthList, comAccountId, hfType);
    }

    private JsonResult isCanPayment(HfPayment payment) {
        if (payment == null) {
            return JsonResultKit.of(1, "支付信息为空！");
        } else if (!payment.getPaymentState().equals(2)) {
            return JsonResultKit.of(1, "该记录不能汇缴，请检查!");
        } else {
            return JsonResultKit.of(0, "验证通过！");
        }
    }

    /**
     * 执行调用财务API
     *
     * @param hfPayment
     */
    private PayApplyProxyDTO financePayApi(HfPayment hfPayment) {
        PayApplyProxyDTO dto = new PayApplyProxyDTO();
        dto.setDepartmentName("福利保障部公积金");
        dto.setIsFinancedept(0);
        dto.setBusinessType(2);//业务类型
        dto.setBusinessPkId(hfPayment.getPaymentId());//业务方主键ID(整型)
        dto.setPayWay(hfPayment.getPaymentWay());// 3:转账   2:支票  如果是支票就不需要银行账户信息，这里需要判断
        dto.setPayAmount(hfPayment.getTotalApplicationAmonut());//申请支付金额
        dto.setReceiver(hfPayment.getReceiver());//页面传递
        dto.setApplyer(UserContext.getUser().getDisplayName());  //申请人
        dto.setApplyDate(StringUtil.now("yyyy-MM-dd"));//申请日期

        //支付独立公积金费用+支付月份  1 大库、2 外包、3独立户
        if (hfPayment.getHfAccountType() == 1) {
            dto.setPayReason("支付大库公积金费用" + hfPayment.getPaymentMonth());
        } else if (hfPayment.getHfAccountType() == 2) {
            dto.setPayReason("支付外包公积金费用" + hfPayment.getPaymentMonth());
        } else if (hfPayment.getHfAccountType() == 3) {
            dto.setPayReason("支付独立户公积金费用" + hfPayment.getPaymentMonth());
        }
        dto.setPayPurpose(dto.getPayReason());
        if (hfPayment.getPaymentWay() != 2) {//如果付款方式不是支票 即转账方式
            dto.setReceiveAccountId(hfPaymentMapper.getHfPaymentBankId(hfPayment.getPaymentId())); //付款银行ID
        }
        List<PayapplyCompanyProxyDTO> paymentComList = baseMapper.getHfPaymentComList(hfPayment.getPaymentId(), hfPayment.getPaymentMonth()).stream().map(x -> toCompanyDto(x)).collect(Collectors.toList());
        List<PayapplyEmployeeProxyDTO> paymentEmpList = baseMapper.getHfPaymentEmpList(hfPayment.getPaymentId(), hfPayment.getPaymentMonth()).stream().map(x -> toEmployeeDto(x)).collect(Collectors.toList());

        dto.setCompanyList(paymentComList);
        dto.setEmployeeList(paymentEmpList);

        return dto;
    }


    private PayapplyCompanyProxyDTO toCompanyDto(PaymentComBO comBO) {
        PayapplyCompanyProxyDTO companyProxyDTO = new PayapplyCompanyProxyDTO();
        BeanUtils.copyProperties(comBO, companyProxyDTO);
        return companyProxyDTO;
    }

    private PayapplyEmployeeProxyDTO toEmployeeDto(PaymentEmpBO empBO) {
        PayapplyEmployeeProxyDTO employeeProxyDTO = new PayapplyEmployeeProxyDTO();
        BeanUtils.copyProperties(empBO, employeeProxyDTO);
        return employeeProxyDTO;
    }

    private void createStandardMonthCharge(HfComAccountClass accountClass, String paymentMonth, String belongMonth, PaymentProcessParmBO processParmBO) {
        List<HfArchiveBasePeriod> basePeriods = archiveBasePeriodMapper.getArchiveBasePeriods(accountClass.getHfType(), accountClass.getComAccountId(), paymentMonth, belongMonth);
        if (null != basePeriods && basePeriods.size() > 0) {
            basePeriods.forEach(x -> monthChargeMapper.insert(setMonthCharge(x, paymentMonth, processParmBO)));
        }
    }

    private HfMonthCharge setMonthCharge(HfArchiveBasePeriod basePeriod, String paymentMonth, PaymentProcessParmBO processParmBO) {
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

    private String setPaymentMonth(String paymentMonth) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(paymentMonth));
        calendar.add(Calendar.MONTH, 1);
        return dateFormat.format(calendar.getTime());
    }


    /**
     * 更新任务单相关的办理状态
     *
     * @param paymentId
     */
    private void updateEmpTaskStatus(Long paymentId) {
        List<EmpTaskStatusBO> taskStatus = hfPaymentMapper.getEmpTaskStatusByPaymentId(paymentId);
        if (null != taskStatus && taskStatus.size() > 0) {
            taskStatus.forEach(x -> updateOperate(x));
        }
    }

    private void updateOperate(EmpTaskStatusBO taskStatus) {
        HfEmpTask task = empTaskMapper.selectById(taskStatus.getEmpTaskId());
        HfEmpArchive empArchive = empArchiveMapper.selectById(taskStatus.getEmpArchiveId());
        if (null != task) {
            task.setTaskStatus(3); //3=已完成(已做)
            task.setModifiedTime(LocalDateTime.now());
            task.setModifiedBy(UserContext.getUserId());
            task.setModifiedDisplayName(UserContext.getUser().getDisplayName());
            empTaskMapper.updateById(task);
            if (null != empArchive) {
                switch (task.getTaskCategory()) {
                    case 1://新开
                    case 2://转入
                    case 3://启封
                    case 9://翻牌新开
                    case 10://翻牌转入
                    case 11://翻牌启封
                        empArchive.setArchiveStatus(2); //已做
                        empArchive.setArchiveTaskStatus(2); //已做
                        break;
                    case 4://转出
                    case 5://封存
                    case 12://翻牌转出
                    case 13://翻牌封存
                        empArchive.setArchiveStatus(3); //封存
                        empArchive.setArchiveTaskStatus(3);//封存
                        break;
                    default:
                        empArchive.setArchiveTaskStatus(empArchive.getArchiveStatus());
                        break;
                }
                empArchive.setModifiedBy(UserContext.getUserId());
                empArchive.setModifiedTime(LocalDateTime.now());
                empArchiveMapper.updateById(empArchive);
            }
        }

    }
}