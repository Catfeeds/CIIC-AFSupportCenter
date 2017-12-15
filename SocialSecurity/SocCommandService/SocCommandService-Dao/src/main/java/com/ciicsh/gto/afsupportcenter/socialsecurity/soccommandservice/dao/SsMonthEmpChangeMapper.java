package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsMonthEmpChangeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChangePO;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 雇员月度变更主表 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
public interface SsMonthEmpChangeMapper extends BaseMapper<SsMonthEmpChangePO> {

    /**
     * <p>Description: 根据对账单ID查询社保汇总数据主表</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeDTO 社保汇总数据主表
     * @return  SsMonthEmpChangeDTO
     */
    SsMonthEmpChangeDTO serachMonthEmpChangeByStatementId(SsMonthEmpChangeDTO ssMonthEmpChangeDTO);
}
