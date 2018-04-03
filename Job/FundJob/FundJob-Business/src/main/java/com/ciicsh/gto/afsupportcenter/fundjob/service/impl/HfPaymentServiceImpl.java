package com.ciicsh.gto.afsupportcenter.fundjob.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfPaymentAccountBO;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfPaymentComBO;
import com.ciicsh.gto.afsupportcenter.fundjob.dao.HfEmpMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.fundjob.service.HfPaymentService;
import com.ciicsh.gto.employeecenter.util.CommonTransform;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.EmployeeMonthlyDataProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeMonthlyDataProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeProxyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公积金汇缴支付批次表 服务实现类
 * </p>
 */
@Service
public class HfPaymentServiceImpl extends ServiceImpl<HfPaymentMapper, HfPayment> implements HfPaymentService {

    @Autowired
    private HfPaymentComMapper hfPaymenthfPaymentComMapper;
    @Autowired
    private HfEmpMonthChargeMapper hfEmpMonthChargeMapper;
    @Autowired
    private HfPaymentAccountMapper hfPaymentAccountMapper;
    @Autowired
    private EmployeeMonthlyDataProxy employeeMonthlyDataProxy;

    /**
     * 调用判断雇员是否垫付、是否可付接口，并更新雇员的垫付状态
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public void enquireFinanceComAccount() {
        //1 查询未支付客户
        Map<String, Object> map = new HashMap<>();
        //map.put("payemntMonth", ssMonth);
        List<HfPaymentAccountBO> paymentAccountList = hfPaymenthfPaymentComMapper.getPaymentAccountList(map);
        for (HfPaymentAccountBO ele : paymentAccountList) {
            if (ele.getComAccountId() != null) {
                enquireFinanceComAccount(ele.getPaymentMonth(), ele.getComAccountClassId(), ele.getComAccountId());
            }
        }
    }
    public void enquireFinanceComAccountTest(String ssMonth, Long comAccountId){
        enquireFinanceComAccount(ssMonth , Long.valueOf(0),comAccountId);
    }
    /**
     * 更新雇员的垫付状态
     *
     * @param paymentMonth 支付年月
     */
    private void enquireFinanceComAccount(String paymentMonth, Long comAccountClassId, Long comAccountId) {
        //查询雇员级信息
        Map<String, Object> qMap = new HashMap<>();
        //qMap.put("comAccountClassId", comAccountClassId);
        qMap.put("paymentAccountId", comAccountId);
        qMap.put("paymentMonth", paymentMonth);
        List<HfMonthChargeBO> paymentEmpList = hfEmpMonthChargeMapper.getPaymentEmpListEnquireFinance(qMap);
        List<EmployeeProxyDTO> proxyDTOList = CommonTransform.convertToDTOs(paymentEmpList, EmployeeProxyDTO.class);
        //2 按照财务服务契约提供雇员级信息 并调用财务接口
        EmployeeMonthlyDataProxyDTO proxyDTO = new EmployeeMonthlyDataProxyDTO();
        //上海公积金
        proxyDTO.setBusinessType("2");
        proxyDTO.setBatchMonth(paymentMonth);
        proxyDTO.setEmployeeList(proxyDTOList);

        //判断雇员是否垫付、是否可付接口，返回雇员的垫付状态
        com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult<EmployeeMonthlyDataProxyDTO> res =
            employeeMonthlyDataProxy.employeeCanPay(proxyDTO);

        if ("0".equals(res.getCode())) {
            Map<String, Object> map = new HashMap<>();

            if (res.getData() != null) {
                List<Map<String, Object>> resDto = (List) res.getData();
                //4 财务接口返回的结果更新ss_month_charge
                for (Map<String, Object> ele : resDto) {
                    map.put("monthChargeId", ele.get("objId"));
                    map.put("empPaymentStatus", ele.get("isAdvance"));
                    hfEmpMonthChargeMapper.updateMonthCharge(map);
                }
            }
            //5 查询 客户下有多少 不可付的记录
            map.clear();
            map.put("comAccountId", comAccountId);
            map.put("paymentMonth", paymentMonth);

            Integer cnt = hfEmpMonthChargeMapper.countByEmpPaymentStatus(map);

            //更新客户的支付状态
            map.clear();
            map.put("comAccountId", comAccountId);
            map.put("paymentMonth", paymentMonth);
            if (cnt == 0) {
                map.put("paymentStatus", 3);
                map.put("modifiedBy", "system");
                hfPaymentAccountMapper.updatePaymentAccount(map);
            } else {
                map.put("paymentStatus", 1);
                map.put("modifiedBy", "system");
                hfPaymentAccountMapper.updatePaymentAccount(map);
            }
        }else {
            System.out.println("结算中心返回接口："+res.getMsg());
        }
    }

    @Override
    public void createPaymentAccount() {
        hfPaymentAccountMapper.insertPaymentAccountJob();
    }
}