package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsCreateEmpChangeReportAdjustBaseDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsCreateEmpChangeReportBaseDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsCreateEmpChangeReportRefundBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChange;

import java.util.List;

/**
 * <p>
 *     生成社保变更汇总表
 * </p>
 *
 * @author
 * @since 2
 */
public interface SsCreateEmpChangeReportMapper extends BaseMapper<SsMonthEmpChange> {

    /**
     * <p>Description: 生成社保变更汇总表：  </p>
     *
     * @author linhui
     * @date 2017-12-16
     */

    //新进 转入 补缴 转出 封存
    List<SsCreateEmpChangeReportBaseDetailBO> searchEmpBaseDetailByYYS(String comAccuntId, String ssMonth);
    //调整
    List<SsCreateEmpChangeReportAdjustBaseDetailBO> searchEmpBaseAdjustDetailByYYS(String comAccuntId, String ssMonth);
    //转出
    List<SsCreateEmpChangeReportBaseDetailBO> searchEmpStopByYYS(String comAccuntId, String ssMonth);

    //退账
    List<SsCreateEmpChangeReportRefundBO> searchEmpRefundByYYS(String comAccuntId, String ssMonth);

}
