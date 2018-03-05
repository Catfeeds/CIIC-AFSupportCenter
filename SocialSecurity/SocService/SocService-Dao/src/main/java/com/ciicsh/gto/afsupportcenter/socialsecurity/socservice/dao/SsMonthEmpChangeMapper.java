package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthEmpChange;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 雇员月度变更主表 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@Mapper
@Component
public interface SsMonthEmpChangeMapper extends BaseMapper<SsMonthEmpChange> {

    /**
     * <p>Description: 根据对账单ID查询社保汇总数据主表</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeBO 社保汇总数据主表
     * @return  SsMonthEmpChangeBO
     */
    SsMonthEmpChangeBO serachMonthEmpChangeByStatementId(SsMonthEmpChangeBO ssMonthEmpChangeBO);
}
