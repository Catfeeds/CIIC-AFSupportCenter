package com.ciicsh.gto.afsupportcenter.socjob.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentCom;
import com.ciicsh.gto.afsupportcenter.socjob.entity.bo.SsMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.socjob.entity.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socjob.service.PaymentService;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.EmployeeMonthlyDataProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeMonthlyDataProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeProxyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Job 每日询问财务是否可付
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<SsPaymentComMapper, SsPaymentCom> implements PaymentService {
    @Autowired
    public SsPaymentComMapper ssPaymentMapper;

    @Autowired
    private EmployeeMonthlyDataProxy employeeMonthlyDataProxy;

    /**
     * 调用判断雇员是否垫付、是否可付接口，并更新雇员的垫付状态
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public void enquireFinanceComAccount(String ssMonth) {
        //1 查询未支付客户
        Map<String, Object> map = new HashMap<>();
        if("ss".equals(ssMonth)){
            ssMonth = LocalDate.now().plusMonths(-1).format(DateTimeFormatter.ofPattern("yyyyMM"));
        }
        map.put("payemntMonth", ssMonth);
        List<SsPaymentComBO> paymentComList = ssPaymentMapper.getPaymentComList(map);

        for (SsPaymentComBO ele : paymentComList) {
            if (ele.getComAccountId() != null) {
                enquireFinanceComAccount(ssMonth, ele.getPaymentComId(), ele.getComAccountId());
            }
        }
    }

    public void enquireFinanceComAccountTest(String ssMonth, Long paymentComId, Long comAccountId) {
        enquireFinanceComAccount(ssMonth, paymentComId , comAccountId);
    }
    /**
     * 更新雇员的垫付状态
     * @param ssMonth 支付年月
     */
    private void enquireFinanceComAccount(String ssMonth, Long paymentComId, Long comAccountId) {
        //查询雇员级信息
        Map<String, Object> qMap = new HashMap<>();
        qMap.put("paymentComId", paymentComId);
        qMap.put("ssMonth", ssMonth);
        List<SsMonthChargeBO> paymentEmpList = ssPaymentMapper.getSsPaymentEmpList(qMap);
        SsMonthChargeBO ssMonthChargeBO=ssPaymentMapper.getSsPaymentComIsCompanyEnjoyAdvance(qMap);
        String isComEnjoyAdvance= ssMonthChargeBO.getIsCompanyEnjoyAdvance();
        paymentEmpList.stream().forEach(SsMonthChargeBO->{
            SsMonthChargeBO.setIsCompanyEnjoyAdvance(isComEnjoyAdvance);
        });
        List<EmployeeProxyDTO> proxyDTOList = CommonTransform.convertToDTOs(paymentEmpList, EmployeeProxyDTO.class);

        //2 按照财务服务契约提供雇员级信息 并调用财务接口
        EmployeeMonthlyDataProxyDTO proxyDTO = new EmployeeMonthlyDataProxyDTO();
        //上海社保
        proxyDTO.setBusinessType("1");
        proxyDTO.setBatchMonth(ssMonth);
        proxyDTO.setEmployeeList(proxyDTOList);

        //判断雇员是否垫付、是否可付接口，返回雇员的垫付状态
        JsonResult<EmployeeMonthlyDataProxyDTO> res = employeeMonthlyDataProxy.employeeCanPay(proxyDTO);

        if ("0".equals(res.getCode())) {
            Map<String, Object> map = new HashMap<>();
            if (res.getData() != null) {
                List<Map<String, Object>> resDto = (List) res.getData();
                //4 财务接口返回的结果更新ss_month_charge
                for (Map<String, Object> ele : resDto) {
                    //map.put("monthChargeId", ele.get("objId"));
                    map.put("empPaymentStatus", ele.get("isAdvance"));//是否可付
                    map.put("comAccountId", comAccountId);
//                    map.put("companyId", ele.get("companyId"));
                    map.put("employeeId", ele.get("employeeId"));
//                    map.put("ssMonthBelong", ele.get("ssMonthBelong"));
                    map.put("ssMonthBelong", ele.get("payMonth"));
                    map.put("ssMonth", ssMonth);
                    ssPaymentMapper.updateSsMonthCharge(map);
                }
            }
            //5 查询 客户下有多少 不可付的记录
            map.clear();
            map.put("comAccountId", comAccountId);
            map.put("ssMonth", ssMonth);
            map.put("paymentComId", paymentComId);
            Integer cnt = ssPaymentMapper.countBySsEmpPaymentStatus(map);

            //更新客户的支付状态
            map.clear();
            map.put("paymentComId", paymentComId);
            if (cnt == 0) {
                map.put("paymentState", 3);//  3 =可付
                map.put("modifiedBy", "system");
                ssPaymentMapper.updateSsPaymentCom(map);
            } else {
                map.put("paymentState", 1);
                map.put("modifiedBy", "system");
                ssPaymentMapper.updateSsPaymentCom(map);
            }
        }else {
            System.out.println("结算中心反馈:"+res.getMsg());
        }
    }

}
