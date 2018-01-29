package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthEmpChangeDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChangeDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.GsyExportOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.YysExportOpt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员月度变更表明细
该表结果有可能需要调整 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
public interface SsMonthEmpChangeDetailMapper extends BaseMapper<SsMonthEmpChangeDetail> {

    /**
     * <p>Description: 根据对账单ID查询社保汇总数据明细表</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeBO 社保汇总数据主表
     * @return   List<SsMonthEmpChangeDetailBO>
     */
    List<SsMonthEmpChangeDetailBO> serachMonthEmpChangeDetailByStatementId(SsMonthEmpChangeBO ssMonthEmpChangeBO);

    /**
     * <p>Description: 根据对账单ID查询社保汇总数据明细表</p>
     *
     * @author wengxk
     * @date 2017-12-14
     * @param statementId 社保汇总数据主表ID
     * @return   List<SsMonthEmpChangeDetail>
     */
    List<SsMonthEmpChangeDetail> serachMonthEmpChangeDetailPOByStatementId(Long statementId);

    /**
     * 养老、医疗和失业变更明细导出
     * @param statementId 对账ID
     * @return 返回变更明细数据
     */
    List<YysExportOpt> yysExportQuery(@Param("statementId")Long statementId);

    /**
     * 工伤和生育变更明细导出
     * @param statementId 对账ID
     * @return 返回变更明细数据
     */
    List<GsyExportOpt> gsyExportQuery(@Param("statementId")Long statementId);

}
