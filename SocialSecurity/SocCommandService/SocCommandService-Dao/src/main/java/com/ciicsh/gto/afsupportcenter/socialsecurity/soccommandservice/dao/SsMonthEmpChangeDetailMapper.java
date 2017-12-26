package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsMonthEmpChangeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsMonthEmpChangeDetailDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChangeDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
     * @param ssMonthEmpChangeDTO 社保汇总数据主表
     * @return   List<SsMonthEmpChangeDetailDTO>
     */
    List<SsMonthEmpChangeDetailDTO> serachMonthEmpChangeDetailByStatementId(SsMonthEmpChangeDTO ssMonthEmpChangeDTO);

    /**
     * <p>Description: 根据对账单ID查询社保汇总数据明细表</p>
     *
     * @author wengxk
     * @date 2017-12-14
     * @param statementId 社保汇总数据主表ID
     * @return   List<SsMonthEmpChangeDetail>
     */
    List<SsMonthEmpChangeDetail> serachMonthEmpChangeDetailPOByStatementId(Long statementId);

}
