package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.HealthMedicalJobService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.enums.SysConstants;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.PaymentApplyBatchMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.PaymentApplyDetailMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.SupplyMedicalAcceptanceMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.UninsuredMedicalMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpBankRefundBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeePaymentBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeePaymentStatusBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.PaymentApplyDetailBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.PaymentApplyBatchPO;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.BankCardRefundDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.BankCardInfoProxy;
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
import java.util.stream.Collectors;

/**
 * <p>
 * 健康医疗服务实现类
 * </p>
 *
 * @author chenpb
 * @since 2018-02-06
 */
@Service
public class HealthMedicalJobServiceImpl extends ServiceImpl<PaymentApplyBatchMapper, PaymentApplyBatchPO> implements HealthMedicalJobService {
    /**
     *  结算中心数据同步接口
     */
    @Autowired
    private PayapplyServiceProxy payapplyServiceProxy;
    /**
     *  雇员中心补全银行卡信息处理
     */
    @Autowired
    private BankCardInfoProxy bankCardInfoProxy;
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
        List<EmployeePaymentBO> audited = supplyMedicalAcceptanceMapper.selectAudited();
        /** 退票已处理 */
        audited.addAll(supplyMedicalAcceptanceMapper.selectRefund());
        if (!audited.isEmpty()) {
            PaymentApplyBatchPO batchPO = this.addPaymentApply(audited);
            JsonResult jsonResult = this.syncPaymentData(batchPO);
            System.out.println(JSON.toJSONString(jsonResult));
            if(JsonResult.MsgCode.SUCCESS.getCode().equals(jsonResult.getCode())) {
                supplyMedicalAcceptanceMapper.syncStatus(new EmployeePaymentStatusBO(
                    batchPO.getApplyBatchId(), SysConstants.BusinessId.SUPPLY_MEDICAL.getId(),
                    SysConstants.SupplyMedicalStatus.SYNC.getCode(), StringUtils.EMPTY,
                    SysConstants.JobConstants.SYSTEM_ZH.getName()
                ));
            } else {
                this.delBathData(batchPO.getApplyBatchId());
            }
        }
        List<EmpBankRefundBO> unSync = this.selectUnSyncSupplyMedical();
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
        List<EmployeePaymentBO> audited = uninsuredMedicalMapper.selectAudited();
        /** 退票已处理 */
        audited.addAll(uninsuredMedicalMapper.selectRefund());
        if (!audited.isEmpty()) {
            PaymentApplyBatchPO batchPO = this.addPaymentApply(audited);
            JsonResult jsonResult = this.syncPaymentData(batchPO);
            System.out.println(JSON.toJSONString(jsonResult));
            if(JsonResult.MsgCode.SUCCESS.getCode().equals(jsonResult.getCode())) {
                uninsuredMedicalMapper.syncStatus(new EmployeePaymentStatusBO(
                    batchPO.getApplyBatchId(), SysConstants.BusinessId.UNINSURED_MEDICAL.getId(),
                    SysConstants.UninsuredMedicalStatus.SYNC.getCode(), StringUtils.EMPTY,
                    SysConstants.JobConstants.SYSTEM_ZH.getName()
                ));
            } else {
                this.delBathData(batchPO.getApplyBatchId());
            }
        }
        List<EmpBankRefundBO> unSync = this.selectUnSyncUninsuredMedical();
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
            List<EmpBankRefundBO> refund = null;
            if (SysConstants.BusinessId.SUPPLY_MEDICAL.equals(dto.getBusinessType())) {
                detail.forEach(pay->this.updateSupplyMedicalRefundStatus(dto.getBusinessPkId().intValue(), pay));
                refund = this.selectSupplyMedicalBankRefund();
            } else {
                detail.forEach(pay->this.updateUninsuredMedicalRefundStatus(dto.getBusinessPkId().intValue(), pay));
                refund = this.selectUninsuredMedicalBankRefund();
            }
            if (!refund.isEmpty()) {
                this.syncIncompleteBankCardInfoApply (refund);
            }
        }
    }
//    public void handlePaymentRefund (PayApplyReturnTicketDTO dto) {
//        List<EmployeeReturnTicketDTO> detail = dto.getEmployeeReturnTicketDTOList();
//        if (!detail.isEmpty()) {
//            detail.forEach(pay->this.updateSupplyMedicalRefundStatus(dto.getBusinessPkId().intValue(), pay));
//        }
//    }


    /**
     * @description 同步结算中心驳回，支付成功状态
     * @author chenpb
     * @since 2018-02-07
     * @param dto: 结算中心处理结果
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void syncSettleCenterStatus (PayApplyPayStatusDTO dto) {
        Integer status = SysConstants.SupplyMedicalStatus.COMPLETE.getCode();
        if (SysConstants.SettlementCenterStatus.BACK.getCode().equals(dto.getPayStatus())) {
            status = SysConstants.SupplyMedicalStatus.BACK.getCode();
        }
        Integer businessId = paymentApplyDetailMapper.selectBusinessId(dto.getBusinessPkId().intValue());
        EmployeePaymentStatusBO statusBO = new EmployeePaymentStatusBO(
            dto.getBusinessPkId().intValue(), businessId, status, dto.getRemark(), SysConstants.JobConstants.SYSTEM_ZH.getName()
        );
        if(SysConstants.BusinessId.SUPPLY_MEDICAL.equals(businessId)) {
            supplyMedicalAcceptanceMapper.syncStatus(statusBO);
        } else if(SysConstants.BusinessId.UNINSURED_MEDICAL.equals(businessId)) {
            if (SysConstants.SettlementCenterStatus.BACK.getCode().equals(dto.getPayStatus())) {
                statusBO.setStatus(SysConstants.UninsuredMedicalStatus.BACK.getCode());
            } else {
                statusBO.setStatus(SysConstants.UninsuredMedicalStatus.COMPLETE.getCode());
            }
            uninsuredMedicalMapper.syncStatus(statusBO);
        }
    }

    /**
     * @description 添加补全银行卡信息申请到雇员中心
     * @author chenpb
     * @since 2018-02-06
     * @param refund: 雇员信息
     * @return
     */
    private void syncIncompleteBankCardInfoApply (List<EmpBankRefundBO> refund) {
        List<BankCardRefundDTO> list = CommonTransform.convertToDTOs(refund, BankCardRefundDTO.class);
        com.ciicsh.gto.employeecenter.util.JsonResult jsonResult = bankCardInfoProxy.createBankRefund(list);
        System.out.println(JSON.toJSONString(jsonResult));
    }

    /**
     * @description 更新退票状态
     * @author chenpb
     * @since 2018-02-06
     * @param batchId
     * @param dto
     */
    private void updateSupplyMedicalRefundStatus (Integer batchId, EmployeeReturnTicketDTO dto) {
        PaymentApplyDetailBO bo = new PaymentApplyDetailBO();
        BeanUtils.copyProperties(dto, bo);
        bo.setApplyBatchId(batchId);
        List<PaymentApplyDetailBO> list = paymentApplyDetailMapper.selectRefundDetail(bo);
        if(!list.isEmpty()){
            supplyMedicalAcceptanceMapper.updateStatus(new EmployeePaymentStatusBO (
                list.get(0).getPaymentApplyId().toString(), SysConstants.SupplyMedicalStatus.REFUND.getCode(), dto.getRemark(), SysConstants.JobConstants.SYSTEM_ZH.getName()
            ));
        }
    }

    /**
     * @description 更新退票状态
     * @author chenpb
     * @since 2018-02-06
     * @param batchId
     * @param dto
     */
    private void updateUninsuredMedicalRefundStatus (Integer batchId, EmployeeReturnTicketDTO dto) {
        PaymentApplyDetailBO bo = new PaymentApplyDetailBO();
        BeanUtils.copyProperties(dto, bo);
        bo.setApplyBatchId(batchId);
        List<PaymentApplyDetailBO> list = paymentApplyDetailMapper.selectRefundDetail(bo);
        if(!list.isEmpty()){
            uninsuredMedicalMapper.updateStatus(new EmployeePaymentStatusBO(
                Integer.getInteger(list.get(0).getPaymentApplyId()), SysConstants.UninsuredMedicalStatus.REFUND.getCode(), dto.getRemark(), SysConstants.JobConstants.SYSTEM_ZH.getName()
            ));
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
        String title;
        Date now = new Date();
        if(SysConstants.JobConstants.SUPPLY_MEDICAL.getCode().equals(list.get(0).getBusinessId())){
            title = SysConstants.JobConstants.SUPPLY_MEDICAL.getName();
        } else {
            title = SysConstants.JobConstants.UNINSURED_MEDICAL.getName();
        }
        BigDecimal payAmount = list.stream().map(p->p.getPayAmount()).reduce(BigDecimal.ZERO, (x,y)->x.add(y));
        list.stream().map((x) -> setAreaInfo(x)).collect(Collectors.toList());
        PaymentApplyBatchPO batchPO = new PaymentApplyBatchPO (
            SysConstants.JobConstants.HEALTH_MEDICAL_DEPT.getName(),
            SysConstants.JobConstants.FINANCE_NOT.getCode(),
            SysConstants.JobConstants.MEDICAL_CLAIMS.getCode(),
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
     * @description 查询补充医疗已审核未同步数据
     * @author chenpb
     * @since 2018-02-07
     * @param
     * @return
     */
    private List<EmpBankRefundBO> selectUnSyncSupplyMedical() {
        return supplyMedicalAcceptanceMapper.selectUnSync();
    }

    /**
     * @description 查询银行退票数据
     * @author chenpb
     * @since 2018-03-01
     * @param
     * @return
     */
    private List<EmpBankRefundBO> selectSupplyMedicalBankRefund () {
        return supplyMedicalAcceptanceMapper.selectBankRefund();
    }

    /**
     * @description 查询未投保医疗已审核未同步数据
     * @author chenpb
     * @since 2018-02-07
     * @param
     * @return
     */
    private List<EmpBankRefundBO> selectUnSyncUninsuredMedical () {
        return uninsuredMedicalMapper.selectUnSync();
    }

    /**
     * @description 查询银行退票数据
     * @author chenpb
     * @since 2018-03-01
     * @param
     * @return
     */
    private List<EmpBankRefundBO> selectUninsuredMedicalBankRefund () {
        return uninsuredMedicalMapper.selectBankRefund();
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
        dto.setDepartmentManager(SysConstants.JobConstants.DEPARTMENT_MASTER.getName());
        dto.setReviewer(SysConstants.JobConstants.REVIEWER.getName());
        return dto;
    }

    /**
     * 根据城市code查询省份名和城市名
     * @param bo
     * @return
     */
    private EmployeePaymentBO setAreaInfo (EmployeePaymentBO bo) {
        bo.setProvinceCode("上海");
        bo.setCityCode("上海市");
        return bo;
    }

}
