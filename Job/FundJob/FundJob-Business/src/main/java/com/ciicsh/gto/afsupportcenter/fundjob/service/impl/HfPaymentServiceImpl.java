package com.ciicsh.gto.afsupportcenter.fundjob.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfPaymentAccountBO;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfPaymentComListBO;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfPaymentComProxyBO;
import com.ciicsh.gto.afsupportcenter.fundjob.dao.HfEmpMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.fundjob.service.HfPaymentService;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;

import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.EmployeeMonthlyDataProxy;
//import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeMonthlyDataProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.CompanyMonthlyDataProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.CompanyProxyDTO;
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
//    @Autowired
//    private HfPaymentMapper hfPaymentMapper;
    /**
     * 调用判断雇员是否垫付、是否可付接口，并更新雇员的垫付状态
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public void enquireFinanceComAccount() {
        //1 查询未支付客户
        Map<String, Object> map = new HashMap<>();
        //map.put("paymentMonth", ssMonth);
        List<HfPaymentAccountBO> paymentAccountList = hfPaymenthfPaymentComMapper.getPaymentAccountList(map);

       for (HfPaymentAccountBO ele : paymentAccountList) {
            if (ele.getComAccountId() != null) {
                HfPayment payment = new HfPayment();
                payment.setPaymentId(ele.getPaymentId());
                payment = baseMapper.selectOne(payment);
                enquireFinanceComAccount(payment);
                //enquireFinanceComAccount(ele.getPaymentMonth(), ele.getComAccountId(), ele.getPaymentAccountId());
            }
        }

    }
    public void enquireFinanceComAccountTest(String ssMonth, Long paymentAccountId,Long comAccountId){
        //enquireFinanceComAccount(ssMonth , comAccountId,paymentAccountId);
    }
    /**
     * 更新雇员的垫付状态
     *
     * //@param paymentMonth 支付年月
     */
    //雇员级询问结算中心是否可付，早期结算中心的调用方案，暂时不用
  /*  @Transactional(rollbackFor = RuntimeException.class)
    public void enquireFinanceComAccount(String paymentMonth, Long comAccountId, Long paymentAccountId) {
        //查询雇员级信息
        Map<String, Object> qMap = new HashMap<>();
        qMap.put("paymentAccountId", paymentAccountId);
        qMap.put("paymentMonth", paymentMonth);
        List<HfMonthChargeBO> paymentEmpList = hfEmpMonthChargeMapper.getPaymentEmpListEnquireFinance(qMap);
        String isComEnjoyAdvance= String.valueOf(hfPaymentAccountMapper.getHfPaymentIsCompanyEnjoyAdvance(qMap));
        paymentEmpList.forEach(HfMonthChargeBO->{
            HfMonthChargeBO.setIsCompanyEnjoyAdvance(isComEnjoyAdvance);
        });
        List<EmployeeProxyDTO> proxyDTOList = CommonTransform.convertToDTOs(paymentEmpList, EmployeeProxyDTO.class);
        //2 按照财务服务契约提供雇员级信息 并调用财务接口
        EmployeeMonthlyDataProxyDTO proxyDTO = new EmployeeMonthlyDataProxyDTO();
        //上海公积金
        proxyDTO.setBusinessType("2");
        proxyDTO.setBatchMonth(paymentMonth);
        proxyDTO.setEmployeeList(proxyDTOList);
        Map<String, Object> map = new HashMap<>();
        map.put("paymentAccountId", paymentAccountId);
        map.put("paymentMonth", paymentMonth);
        //判断雇员是否垫付、是否可付接口，返回雇员的垫付状态
        try {
            com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult<EmployeeMonthlyDataProxyDTO> res =
                employeeMonthlyDataProxy.employeeCanPay(proxyDTO);
            if ("0".equals(res.getCode())) {
                if (res.getData() != null) {
                    List<Map<String, Object>> resDto = (List) res.getData();
                    //4 财务接口返回的结果更新ss_month_charge
                    //isAdvance: 0:不可付;1:来款可付;2:垫付可付
                    for (Map<String, Object> ele : resDto) {
                        map.put("empPaymentStatus", ele.get("isAdvance"));
                        map.put("companyId", ele.get("companyId"));
                        map.put("employeeId", ele.get("employeeId"));
                        map.put("hfMonthBelong", ele.get("payMonth"));
                        map.put("hfMonth", paymentMonth);
                        hfEmpMonthChargeMapper.updateMonthCharge(map);
                    }
                }
                //5 查询 客户下有多少 不可付的记录
                map.clear();
                map.put("paymentAccountId", paymentAccountId);
                map.put("paymentMonth", paymentMonth);
                Integer cnt = hfEmpMonthChargeMapper.countByEmpPaymentStatus(map);
                //更新客户的支付状态
                if (cnt == 0) {
                    map.put("paymentStatus", 3);
                    map.put("financeRetMsg", "");
                    map.put("modifiedBy", "system");
                    hfPaymentAccountMapper.updateHfPaymentAcc(map);
                } else {
                    map.put("paymentStatus", 1);
                    map.put("financeRetMsg", "");
                    map.put("modifiedBy", "system");
                    hfPaymentAccountMapper.updateHfPaymentAcc(map);
                }
            }else {
                map.put("financeRetMsg", res.getMsg());
                map.put("modifiedBy", "system");
                hfPaymentAccountMapper.updateHfPaymentAcc(map);
                System.out.println("结算中心返回接口："+res.getMsg());
            }
        }catch (Exception e){
            map.put("financeRetMsg",e.getMessage());
            map.put("modifiedBy", "system");
            hfPaymentAccountMapper.updateHfPaymentAcc(map);
        }
    }*/
// 询问结算中心是否可付
    private JsonResult enquireFinanceComAccount(HfPayment payment){
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
        for (CompanyProxyDTO companyProxyDTO : comList) {
            if(companyProxyDTO.getIsAdvance().equals("0")){
                return JsonResultKit.of(1, "结算中心告知：该批次中存在未到账的客户，拒绝申请！");
            }
            baseMapper.updatePaymentComStatus(payment.getPaymentId(),companyProxyDTO.getIsAdvance(),companyProxyDTO.getCompanyId());
        }
        return JsonResultKit.of(0, "");
    }
    @Override
    public void createPaymentAccount() {
        hfPaymentAccountMapper.insertPaymentAccountJob();
    }
}