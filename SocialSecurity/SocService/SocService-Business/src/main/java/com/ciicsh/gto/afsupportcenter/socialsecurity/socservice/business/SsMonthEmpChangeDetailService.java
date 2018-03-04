package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthEmpChangeDetail;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.GsyExportOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.YysExportOpt;

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
public interface SsMonthEmpChangeDetailService extends IService<SsMonthEmpChangeDetail> {
    /**
     * <p>Description: 根据对账单ID展示社保汇总明细数据</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeBO 检索条件
     * @return   List<SsMonthEmpChangeDetailBO>
     */
    List<SsMonthEmpChangeDetailBO> showMonthEmpChangeDetailByStatementId(SsMonthEmpChangeBO ssMonthEmpChangeBO);


    /**
     * 养老、医疗和失业变更明细导出
     * @param monthEmpChangeId 对账ID
     * @return 返回变更明细数据
     */
    List<YysExportOpt> yysExportQuery(Long monthEmpChangeId);

    /**
     * 工伤和生育变更明细导出
     * @param monthEmpChangeId 对账ID
     * @return 返回变更明细数据
     */
    List<GsyExportOpt> gsyExportQuery(Long monthEmpChangeId);
}
