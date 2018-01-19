package com.ciicsh.gto.afsupportcenter.socjob.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentCom;
import com.ciicsh.gto.afsupportcenter.socjob.service.PaymentService;
import com.ciicsh.gto.afsupportcenter.socjob.entity.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.EmployeeMonthlyDataProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeMonthlyDataProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeProxyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
     * Job 每日询问财务是否可付
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public void enquireFinanceComAccount(String ssMonth) {
        //1 查询未支付客户
        HashMap map = new HashMap();
        map.put("payemntMonth", StringUtil.getYear_Month(new Date()));
        List<SsPaymentComBO> paymentComList = ssPaymentMapper.getPaymentComList(map);

        for (SsPaymentComBO ele : paymentComList) {
            if (ele.getComAccountId() != null) {
                enquireFinanceComAccount(ssMonth, ele.getPaymentComId(), ele.getComAccountId());
            }
        }
    }

    private void enquireFinanceComAccount(String ssMonth, Long paymentComId, Long comAccountId) {
        //查询雇员级信息
        HashMap qMap = new HashMap();
        qMap.put("paymentComId", paymentComId);
        qMap.put("ssMonth", ssMonth);
        List<EmployeeProxyDTO> paymentEmpList = ssPaymentMapper.getPaymentEmpList(qMap);

        //2 按照财务服务契约提供雇员级信息 并调用财务接口
        EmployeeMonthlyDataProxyDTO proxyDTO = new EmployeeMonthlyDataProxyDTO();
        //上海社保
        proxyDTO.setBusinessType("1");
        proxyDTO.setBatchMonth(ssMonth);
        proxyDTO.setEmployeeList(paymentEmpList);
        JsonResult<EmployeeMonthlyDataProxyDTO> res = employeeMonthlyDataProxy.employeeCanPay(proxyDTO);

        EmployeeMonthlyDataProxyDTO resDto = (EmployeeMonthlyDataProxyDTO) res.getData();
        List<EmployeeProxyDTO> employeeProxyDTOS = resDto.getEmployeeList();
        HashMap map = new HashMap();
        //4 财务接口返回的结果更新ss_month_charge
        for (EmployeeProxyDTO ele : employeeProxyDTOS) {
            map.put("monthChargeId", ele.getObjId());
            map.put("empPaymentStatus", ele.getIsCompanyEnjoyAdvance());
            ssPaymentMapper.updateMonthCharge(map);
        }

        //5 查询 客户下有多少 不可付的记录
        map.clear();
        map.put("comAccountId", comAccountId);
        map.put("ssMonth", ssMonth);
        Integer cnt = ssPaymentMapper.countByEmpPaymentStatus(map);

        //更新客户的支付状态
        map.clear();
        map.put("paymentComId", paymentComId);
        if (cnt == 0) {
            map.put("paymentState", 3);
            ssPaymentMapper.updatePaymentCom(map);
        } else {
            map.put("paymentState", 1);
            ssPaymentMapper.updatePaymentCom(map);
        }
    }

}
