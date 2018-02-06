package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.HealthMedicalJobService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.enums.SysConstants;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.*;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpBankRefundBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeePaymentBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.PaymentApplyDetailBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.PaymentApplyBatchPO;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 雇员付款服务实现类
 * </p>
 *
 * @author chenpb
 * @since 2018-01-29
 */
@Service
public class HealthMedicalJobServiceImpl extends ServiceImpl<PaymentApplyBatchMapper, PaymentApplyBatchPO> implements HealthMedicalJobService {
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
     *  补充医疗受理
     */
    @Autowired
    private SupplyMedicalAcceptanceMapper supplyMedicalAcceptanceMapper;
    /**
     *  未投保医疗
     */
    @Autowired
    private UninsuredMedicalMapper uninsuredMedicalMapper;

    /**
     * @description 补充医疗受理任务
     * @author chenpb
     * @since 2018-02-06
     * @param
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void handleSupplyMedical () {
        /** 审核未同步 */
        List<EmployeePaymentBO> audited = employeePaymentApplyMapper.selectAudited();
        /** 退票已处理 */
        audited.addAll(employeePaymentApplyMapper.selectRefund());
        if (!audited.isEmpty()) {
            PaymentApplyBatchPO batchPO = this.addPaymentApply(audited);
            JsonResult jsonResult = this.syncPaymentData(batchPO);
            System.out.println(JSON.toJSONString(jsonResult));
            if(JsonResult.MsgCode.SUCCESS.getCode().equals(jsonResult.getCode())) {
                this.updateSyncStatus(batchPO.getApplyBatchId(), SysConstants.SupplyMedicalStatus.SYNC.getCode());
            } else {
                this.delBathData(batchPO.getApplyBatchId());
            }
        }
        List<EmpBankRefundBO> unSync = this.selectUnSyncApply();
        if (!unSync.isEmpty()) {
            this.syncIncompleteBankCardInfoApply(unSync);
        }
    }

    /**
     * @description 未投保医疗任务
     * @author chenpb
     * @since 2018-02-06
     * @param
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void handleUninsuredMedical () {
        /** 审核未同步 */
        List<EmployeePaymentBO> audited = employeePaymentApplyMapper.selectAudited();
        /** 退票已处理 */
        audited.addAll(employeePaymentApplyMapper.selectRefund());
        if (!audited.isEmpty()) {
            PaymentApplyBatchPO batchPO = this.addPaymentApply(audited);
            JsonResult jsonResult = this.syncPaymentData(batchPO);
            System.out.println(JSON.toJSONString(jsonResult));
            if(JsonResult.MsgCode.SUCCESS.getCode().equals(jsonResult.getCode())) {
                this.updateSyncStatus(batchPO.getApplyBatchId(), SysConstants.UninsuredMedicalStatus.SYNC.getCode());
            } else {
                this.delBathData(batchPO.getApplyBatchId());
            }
        }
        List<EmpBankRefundBO> unSync = this.selectUnSyncApply();
        if (!unSync.isEmpty()) {
            this.syncIncompleteBankCardInfoApply(unSync);
        }
    }

    /**
     * @description 处理退票
     * @author chenpb
     * @since 2018-02-06
     * @param dto: 结算中心退票
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void handlePaymentRefund (PayApplyReturnTicketDTO dto) {
        List<EmployeeReturnTicketDTO> detail = dto.getEmployeeReturnTicketDTOList();
        if (!detail.isEmpty()) {
            detail.forEach(pay->this.updateRefundStatus(dto.getBusinessPkId().intValue(), pay));
        }
    }

    /**
     * @description 同步结算中心驳回，支付成功状态
     * @author chenpb
     * @since 2018-02-06
     * @param dto: 结算中心处理结果
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void syncSettleCenterStatus (PayApplyPayStatusDTO dto) {
        employeePaymentApplyMapper.syncStatus(dto.getBusinessPkId().intValue(),
            dto.getBusinessType(),
            dto.getPayStatus(),
            dto.getRemark(),
            SysConstants.JobConstants.SYSTEM_ZH.getName());
    }

    /**
     * @description 添加补全银行卡信息申请到雇员中心
     * @author chenpb
     * @since 2018-02-02
     * @param emp: 雇员信息
     * @return
     */
    private void syncIncompleteBankCardInfoApply (List<EmpBankRefundBO> emp) {
        System.out.println(emp.size());
    }

    /**
     * @description 更新付款申请退票状态
     * @author chenpb
     * @since 2018-02-06
     * @param batchId
     * @param dto
     */
    private void updateRefundStatus (Integer batchId, EmployeeReturnTicketDTO dto) {
        PaymentApplyDetailBO bo = new PaymentApplyDetailBO();
        BeanUtils.copyProperties(dto, bo);
        bo.setApplyBatchId(batchId);
        List<PaymentApplyDetailBO> list = paymentApplyDetailMapper.selectRefundDetail(bo);
        if(!list.isEmpty()){
            Integer businessId = list.get(0).getBusinessId();
            if (SysConstants.BusinessId.SUPPLY_MEDICAL.equals(businessId)) {
                supplyMedicalAcceptanceMapper.updateStatus(list.get(0).getPaymentApplyId().toString(), SysConstants.SupplyMedicalStatus.REFUND.getCode(), SysConstants.JobConstants.SYSTEM_ZH.getName());
            } else {
                uninsuredMedicalMapper.updateStatus(list.get(0).getPaymentApplyId(), SysConstants.UninsuredMedicalStatus.REFUND.getCode(), SysConstants.JobConstants.SYSTEM_ZH.getName());
            }
        }
    }
    /**
     * @description 同步支付申请信息到结算中心
     * @author chenpb
     * @since 2018-02-06
     * @param batchPO: 批处理信息
     * @return JsonResult: 结算中心同步数据结果
     */
    private JsonResult  syncPaymentData (PaymentApplyBatchPO batchPO) {
        PayApplyProxyDTO payApplyProxyDTO= new PayApplyProxyDTO();
        BeanUtils.copyProperties(batchPO, payApplyProxyDTO);
        this.assignBatchDefaultValue(batchPO, payApplyProxyDTO);
        List<PaymentApplyDetailBO> detail = paymentApplyDetailMapper.selectPending(batchPO.getApplyBatchId());
        payApplyProxyDTO.setEmployeeList(CommonTransform.convertToDTOs(detail, PayapplyEmployeeProxyDTO.class));
        return payapplyServiceProxy.medicalReimburse(payApplyProxyDTO);
    }

    /**
     * @description 保存支付申请信息
     * @author chenpb
     * @since 2018-02-06
     * @param list: 付款申请数据
     * @return PaymentApplyBatchPO: 申请支付批次记录
     */
    private PaymentApplyBatchPO addPaymentApply (List<EmployeePaymentBO> list) {
        Date now = new Date();
        String title = SysConstants.JobConstants.MEDICAL_CLAIMS.getName();
        BigDecimal payAmount = list.stream().map(p->p.getPayAmount()).reduce(BigDecimal.ZERO, (x,y)->x.add(y));
        PaymentApplyBatchPO batchPO = new PaymentApplyBatchPO (
            SysConstants.JobConstants.HEALTH_MEDICAL_DEPT.getName(),
            SysConstants.JobConstants.FINANCE_NOT.getCode(),
            SysConstants.JobConstants.HEALTH_MEDICAL_DEPT.getCode(),
            SysConstants.JobConstants.PAY_WAY.getCode(),
            payAmount,
            SysConstants.JobConstants.INDIVIDUAL.getName(),
            SysConstants.JobConstants.SYSTEM_ZH.getName(), now, title, title,
            SysConstants.BatchStatus.ADD.getCode(),
            SysConstants.JobConstants.ACTIVE.getCode(), now, now,
            SysConstants.JobConstants.SYSTEM_ZH.getName(),
            SysConstants.JobConstants.SYSTEM_ZH.getName()
        );
        paymentApplyBatchMapper.insert(batchPO);
        paymentApplyDetailMapper.insertDetails(list, batchPO.getApplyBatchId(), batchPO.getModifiedBy());
        return batchPO;
    }

    /**
     * @description 查询已审核未同步数据
     * @author chenpb
     * @since 2018-02-06
     * @param
     * @return
     */
    private List<EmpBankRefundBO> selectUnSyncApply () {
        return employeePaymentApplyMapper.selectUnSyncApply();
    }

    /**
     * @description 更新付款申请状态
     * @author chenpb
     * @since 2018-02-01
     * @param batchId
     * @return
     */
    private Integer updateSyncStatus (Integer batchId, Integer status) {
        return employeePaymentApplyMapper.syncStatus(batchId,
            SysConstants.BusinessId.EMPLOYEE_PAYMENT.getId(),
            status,
            StringUtils.EMPTY,
            SysConstants.JobConstants.SYSTEM_ZH.getName());
    }

    /**
     * @description 删除数据有误批次申请
     * @author chenpb
     * @since 2018-02-06
     * @param batchId
     * @return
     */
    private void delBathData (Integer batchId) {
        paymentApplyBatchMapper.updateActiveByBachId(batchId, SysConstants.JobConstants.SYSTEM_ZH.getName());
        paymentApplyDetailMapper.updateActiveByBachId(batchId, SysConstants.JobConstants.SYSTEM_ZH.getName());
    }

    /**
     * @description 申请支付批次默认值处理
     * @author chenpb
     * @since 2018-02-06
     * @param dto
     * @return PayApplyProxyDTO
     */
    private PayApplyProxyDTO assignBatchDefaultValue (PaymentApplyBatchPO batchPo, PayApplyProxyDTO dto) {
        SimpleDateFormat df =new SimpleDateFormat(SysConstants.JobConstants.DATE_FORMAT.getName());
        dto.setBusinessPkId(batchPo.getApplyBatchId().longValue());
        dto.setApplyDate(df.format(new Date()));
        dto.setIsFinancedept(SysConstants.JobConstants.FINANCE_NOT.getCode());
        dto.setPresident(SysConstants.JobConstants.PRESIDENT.getName());
        dto.setLeader(SysConstants.JobConstants.LEADER.getName());
        dto.setDepartmentManager(SysConstants.JobConstants.DEPARTMENT_MANAGER.getName());
        dto.setReviewer(SysConstants.JobConstants.REVIEWER.getName());
        return dto;
    }

}
