package com.ciicsh.gto.afsupportcenter.fundjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfPaymentComListBO;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfPayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 公积金汇缴支付批次表 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface HfPaymentMapper extends BaseMapper<HfPayment> {
    List<HfPaymentComListBO> enquireFinanceComList(@Param("paymentId") Long paymentId);
    void updatePaymentComStatus(@Param("paymentId") Long paymentId,@Param("comPaymentStatus") String comPaymentStatus,@Param("companyId") String companyId);
}
