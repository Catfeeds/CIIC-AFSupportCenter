package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsYysReportBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsYysReportParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthCharge;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成， 服务类
 * </p>
 *
 * @author xsj
 * @since 2018-02-05
 */
public interface SsMonthChargeService extends IService<SsMonthCharge> {

    int deleteOldDate(String employeeId, String paymentMonth, String handleMonth,Integer costCategory, String modifiedBy);

    List<SsMonthChargeBO>  selectTotalFromOld(String employeeId, String paymentMonth, Integer costCategory);

    List<SsYysReportBO> queryYysReport(SsYysReportParamBO ssYysReportParamBO);
}
