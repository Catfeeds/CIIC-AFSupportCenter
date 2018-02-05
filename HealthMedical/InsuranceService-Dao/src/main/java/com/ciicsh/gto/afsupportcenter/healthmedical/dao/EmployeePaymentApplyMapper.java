package com.ciicsh.gto.afsupportcenter.healthmedical.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpBankRefundBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeePaymentBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.EmployeePaymentApplyPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员付款申请 Mapper 接口
 * </p>
 *
 * @author chenpb
 * @since 2017-01-29
 */
public interface EmployeePaymentApplyMapper extends BaseMapper<EmployeePaymentApplyPO> {

    /**
     * 查询同步数据
     * @param
     * @return
     */
    List<EmployeePaymentBO> selectSyncApply();

    /**
     * 查询已审核未同步申请
     * @param
     * @return
     */
    List<EmployeePaymentBO> selectAudited();

    /**
     * 查询退票申请
     * @param
     * @return
     */
    List<EmployeePaymentBO> selectRefund();

    /**
     * 更新申请状态为已同步
     * @param batchId
     * @param businessId
     * @param modifiedBy
     * @return
     */
    Integer updateSyncStatus(@Param("batchId") Integer batchId, @Param("businessId") Integer businessId, @Param("modifiedBy") String modifiedBy);

    /**
     * 查询信息有误未同步数据
     * @param
     * @return
     */
    List<EmpBankRefundBO> selectUnSyncApply();

    /**
     * 更新付款申请状态
     * @param paymentApplyId
     * @param status
     * @param modifiedBy
     * @return
     */
    Integer updateApplyStatus(@Param("paymentApplyId") Integer paymentApplyId, @Param("status") Integer status, @Param("modifiedBy") String modifiedBy);
}
