package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatementImp;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 对账导入雇员明细 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsStatementImpMapper extends BaseMapper<SsStatementImp> {

    /**
     * <p>Description: 根据对账单ID查询导入的明细表</p>
     *
     * @author wengxk
     * @date 2017-12-14
     * @param statementId 社保汇总数据主表ID
     * @return   List<SsStatementImp>
     */
    List<SsStatementImp> getImpDetailByStatementId(String statementId);
}
