package com.ciicsh.gto.afsupportcenter.healthmedical.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeePaymentBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.PaymentApplyDetailBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.PaymentApplyDetailPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 支付申请详情表 Mapper 接口
 * </p>
 *
 * @author chenpb
 * @since 2018-01-30
 */
public interface PaymentApplyDetailMapper extends BaseMapper<PaymentApplyDetailPO> {
    Integer insertDetails (@Param("detail")List<EmployeePaymentBO> detail, @Param("applyBatchId") Integer applyBatchId, @Param("modifiedBy") String modifiedBy);
    List<PaymentApplyDetailBO> selectPending(@Param("applyBatchId") Integer applyBatchId);
}
