package com.ciicsh.gto.afsupportcenter.healthmedical.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.PaymentApplyBatchPO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 支付批次记录表 Mapper 接口
 * </p>
 *
 * @author chenpb
 * @since 2018-01-30
 */
public interface PaymentApplyBatchMapper extends BaseMapper<PaymentApplyBatchPO> {
    Integer updateActiveByBachId (@Param("batchId") Integer batchId, @Param("modifiedBy") String modifiedBy);
}
