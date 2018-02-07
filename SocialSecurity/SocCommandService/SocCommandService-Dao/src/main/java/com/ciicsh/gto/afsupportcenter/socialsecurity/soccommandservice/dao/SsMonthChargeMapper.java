package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthCharge;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 月度缴费明细
 * 系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成， Mapper 接口
 * </p>
 *
 * @author LiuHui
 * @since 2017-12-23
 */
public interface SsMonthChargeMapper extends BaseMapper<SsMonthCharge> {
    HashMap countIfMonthFirstReport(String ssMonth, String comAccountId);

    List<HashMap> selectIfNewEmpTask(String ssMonth, String comAccountId);

    List<HashMap> selectStandardEmpPeriod(String ssMonth, String comAccountId);

    void updateMonthChargeItem(String ssMonth, String comAccountId, String empArchiveId);

    void updateMonthCharge(String ssMonth, String comAccountId, String empArchiveId);

    List<HashMap> selectNonStandardTaskCategory124(String ssMonth, String comAccountId);

    void deleteOldDate(@Param("employeeId") String employeeId, @Param("paymentMonth") String paymentMonth, @Param("handleMonth") String handleMonth);

    List<SsMonthCharge> selectOldDate(@Param("employeeId") String employeeId, @Param("paymentMonth") String paymentMonth, @Param("handleMonth") String handleMonth);

    List<SsMonthChargeBO> selectTotalFromOld(@Param("employeeId")String employeeId, @Param("paymentMonth")String paymentMonth);
}
