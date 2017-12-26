package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthEmpChangeDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChangeDetail;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 雇员月度变更表明细
该表结果有可能需要调整 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
public interface ISsMonthEmpChangeDetailService extends IService<SsMonthEmpChangeDetail> {
    /**
     * <p>Description: 根据对账单ID展示社保汇总明细数据</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeBO 检索条件
     * @return   List<SsMonthEmpChangeDetailBO>
     */
    List<SsMonthEmpChangeDetailBO> showMonthEmpChangeDetailByStatementId(SsMonthEmpChangeBO ssMonthEmpChangeBO);
}
