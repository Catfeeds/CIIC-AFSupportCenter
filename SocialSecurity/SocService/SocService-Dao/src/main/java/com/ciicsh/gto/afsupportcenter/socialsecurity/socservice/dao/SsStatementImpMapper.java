package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsStatementImp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 对账导入雇员明细 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Mapper
@Component
public interface SsStatementImpMapper extends BaseMapper<SsStatementImp> {

    /**
     * <p>Description: 根据对账单ID查询导入的明细表</p>
     *
     * @author wengxk
     * @date 2017-12-14
     * @param statementId 社保汇总数据主表ID
     * @return   List<SsStatementImp>
     */
    List<SsStatementImp> getImpDetailByStatementId(Long statementId);


    /**
     * 根据对账单删除对账导入雇员明细
     * @param statementId 对账单ID
     * @return 返回影响的行数
     */
    Integer delByStatementId(@Param("statementId")Long statementId);
}
