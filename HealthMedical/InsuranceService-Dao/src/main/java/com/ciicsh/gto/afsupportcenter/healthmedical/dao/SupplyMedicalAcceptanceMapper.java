package com.ciicsh.gto.afsupportcenter.healthmedical.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.AcceptanceStatisticsBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpBankRefundBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeePaymentBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeePaymentStatusBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalAcceptanceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 补充医疗受理单表 Mapper 接口
 * </p>
 *
 * @author xiweizhen
 */
@Repository
public interface SupplyMedicalAcceptanceMapper extends BaseMapper<SupplyMedicalAcceptance> {

    /**
     * 补充医疗分页查询
     *
     * @param page
     * @param supplyMedicalAcceptanceDTO
     * @return
     */
    List<SupplyMedicalAcceptance> queryAcceptancePage(Page<SupplyMedicalAcceptance> page, SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO);

    /**
     * 查询汇总信息
     *
     * @param supplyMedicalAcceptanceDTO
     * @return
     */
    AcceptanceStatisticsBO queryAcceptanceTotal(SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO);

    /**
     * 更新状态
     * @param bo
     * @return
     */
    Integer updateStatus(EmployeePaymentStatusBO bo);

    /**
     * 更新受理单状态为已同步
     * @param bo
     * @return
     */
    Integer syncStatus(EmployeePaymentStatusBO bo);

    /**
     * 查询已审核未同步申请
     *
     * @param
     * @return
     */
    List<EmployeePaymentBO> selectAudited();

    /**
     * 查询退票申请
     *
     * @param
     * @return
     */
    List<EmployeePaymentBO> selectRefund();

    /**
     * 查询信息有误未同步数据
     *
     * @param
     * @return
     */
    List<EmpBankRefundBO> selectUnSync();

    /**
     * 查询银行退票数据
     * @param
     * @return
     */
    List<EmpBankRefundBO> selectBankRefund();

    /**
     * 批量更新受理单
     *
     * @param date
     * @return
     */
    Integer updateByEntity(Date date);

    /**
     * 条件查询所有补充医疗
     * @param params
     * @return
     */
    List<SupplyMedicalAcceptance> selectAll(SupplyMedicalAcceptance params);
}
