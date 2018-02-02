package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.EmployeePaymentService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.enums.SysConstants;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.EmployeePaymentApplyMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.PaymentApplyBatchMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.PaymentApplyDetailMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpBankRefundBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeePaymentBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.PaymentApplyDetailBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.EmployeePaymentApplyPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.PaymentApplyBatchPO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayapplyEmployeeProxyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 雇员付款服务实现类
 * </p>
 *
 * @author chenpb
 * @since 2018-01-29
 */
@Service
public class EmployeePaymentServiceImpl extends ServiceImpl<EmployeePaymentApplyMapper, EmployeePaymentApplyPO> implements EmployeePaymentService {
    /**
     *  结算中心数据同步接口
     */
    @Autowired
    private PayapplyServiceProxy payapplyServiceProxy;
    /**
     *  雇员付款申请
     */
    @Autowired
    private EmployeePaymentApplyMapper employeePaymentApplyMapper;
    /**
     *  支付批次记录表
     */
    @Autowired
    private PaymentApplyBatchMapper paymentApplyBatchMapper;
    /**
     *  支付申请详情表
     */
    @Autowired
    private PaymentApplyDetailMapper paymentApplyDetailMapper;

    /**
     * @description 处理雇员付款业务
     * @author chenpb
     * @since 2018-01-29
     * @param
     * @return
     */
    @Override
    public void handleEmpPayment () {
        /** 已审核未同步 */
        List<EmployeePaymentBO> audited = employeePaymentApplyMapper.selectAudited();
        /** 退票已处理 */
        audited.addAll(employeePaymentApplyMapper.selectRefund());
        if (!audited.isEmpty()) {
            PaymentApplyBatchPO batchPO = this.addPaymentApply(audited);
            JsonResult jsonResult = this.syncPaymentData(batchPO);
            if(SysConstants.MsgCode.SUCCESS.getCode().equals(jsonResult.getCode())){
                this.updateSyncStatus(batchPO.getApplyBatchId());
            }
            System.out.println("-----> " + JSON.toJSONString(jsonResult));
        }
        List<EmpBankRefundBO> unSync = this.selectUnSyncApply();
        System.out.println("-----> " + unSync.size());
    }

    /**
     * @description 同步支付申请信息到结算中心
     * @author chenpb
     * @since 2018-01-31
     * @param batchPO: 批处理信息
     * @return JsonResult: 结算中心同步数据结果
     */
    private JsonResult  syncPaymentData (PaymentApplyBatchPO batchPO) {
        PayApplyProxyDTO payApplyProxyDTO= new PayApplyProxyDTO();
        BeanUtils.copyProperties(batchPO, payApplyProxyDTO);
        this.assignBatchDefaultValue(batchPO, payApplyProxyDTO);
        List<PaymentApplyDetailBO> detail = paymentApplyDetailMapper.selectPending(batchPO.getApplyBatchId());
        payApplyProxyDTO.setEmployeeList(this.convertToDTOs(detail, PayapplyEmployeeProxyDTO.class));
        return payapplyServiceProxy.employeeReimburse(payApplyProxyDTO);
    }

    /**
     * @description 保存支付申请信息
     * @author chenpb
     * @since 2018-01-30
     * @param list: 雇员付款申请数据
     * @return PaymentApplyBatchPO: 申请支付批次记录
     */
    private PaymentApplyBatchPO addPaymentApply (List<EmployeePaymentBO> list) {
        SimpleDateFormat df =new SimpleDateFormat(SysConstants.PaymentJob.DATE_FORMAT.getName());
        Date now = new Date();
        String title = df.format(now).concat(SysConstants.PaymentJob.BUSINESS.getName());
        PaymentApplyBatchPO batchPO = new PaymentApplyBatchPO (
            SysConstants.PaymentJob.AF_SYS_MANAGEMENT.getName(),
            SysConstants.PaymentJob.FINANCE.getCode(),
            SysConstants.PaymentJob.BUSINESS.getCode(),
            SysConstants.PaymentJob.PAY_WAY.getCode(),
            BigDecimal.ZERO,
            SysConstants.PaymentJob.INDIVIDUAL.getName(),
            SysConstants.PaymentJob.SYSTEM_EN.getName(), now, title, title,
            SysConstants.BatchStatus.ADD.getCode(),
            SysConstants.PaymentJob.ACTIVE.getCode(), now, now,
            SysConstants.PaymentJob.SYSTEM_ZH.getName(),
            SysConstants.PaymentJob.SYSTEM_ZH.getName()
        );
        paymentApplyBatchMapper.insert(batchPO);
        paymentApplyDetailMapper.insertDetails(list, batchPO.getApplyBatchId(), batchPO.getModifiedBy());
        return batchPO;
    }

    /**
     * @description 更新雇员付款同步状态
     * @author chenpb
     * @since 2018-02-01
     * @param batchId
     * @return
     */
    private Integer updateSyncStatus (Integer batchId) {
        return employeePaymentApplyMapper.updateSyncStatus(batchId, SysConstants.BusinessId.EMPLOYEE_PAYMENT.getId(), SysConstants.PaymentJob.SYSTEM_EN.getName());
    }

    /**
     * @description 查询已审核未同步数据
     * @author chenpb
     * @since 2018-02-01
     * @param
     * @return
     */
    private List<EmpBankRefundBO> selectUnSyncApply () {
        return employeePaymentApplyMapper.selectUnSyncApply();
    }

    /**
     * @description 申请支付批次默认值处理
     * @author chenpb
     * @since 2018-01-31
     * @param dto
     * @return PayApplyProxyDTO
     */
    private PayApplyProxyDTO assignBatchDefaultValue (PaymentApplyBatchPO batchPo, PayApplyProxyDTO dto) {
        SimpleDateFormat df =new SimpleDateFormat(SysConstants.PaymentJob.DATE_FORMAT.getName());
        Date now = new Date();
        dto.setBusinessPkId(batchPo.getApplyBatchId().longValue());
        dto.setApplyDate(df.format(now));
//        dto.setApplyDate(batchPo.getApplyDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dto.setIsFinancedept(SysConstants.PaymentJob.FINANCE.getCode());
        dto.setPresident(SysConstants.PaymentJob.PRESIDENT.getName());
        dto.setLeader(SysConstants.PaymentJob.LEADER.getName());
        dto.setDepartmentManager(SysConstants.PaymentJob.DEPARTMENT_MANAGER.getName());
        dto.setReviewer(SysConstants.PaymentJob.REVIEWER.getName());
        return dto;
    }

    private <T, E> List<T> convertToDTOs(Collection<E> list, Class<T> t) {
        if (list == null || list.size() <= 0) {
            return Collections.emptyList();
        }
        return list.stream().map(e -> convertToDTO(e, t)).collect(Collectors.toList());
    }

    private <T, E> T convertToDTO(E e, Class<T> t) {
        T dto = BeanUtils.instantiate(t);
        if (e == null) {
            return null;
        }
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

}
