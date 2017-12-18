package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementResultDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatementResult;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 对账差异结果表 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsStatementResultMapper extends BaseMapper<SsStatementResult> {

    /**
     * <p>Description: 查询对账单结果表</p>
     *
     * @author wengxk
     * @date 2017-12-12
     * @param SsStatementResultDTO 对账单查询结果实体
     * @return  List<SsStatementResultDTO>
     */
    List<SsStatementResultDTO> statementResultQuery(SsStatementResultDTO SsStatementResultDTO);

    /**
     * <p>Description: 清理对账单结果表</p>
     *
     * @author wengxk
     * @date 2017-12-15
     * @param SsStatementResultDTO 清理条件
     */
    void cleanResultByStatementId(SsStatementResultDTO SsStatementResultDTO);
}
