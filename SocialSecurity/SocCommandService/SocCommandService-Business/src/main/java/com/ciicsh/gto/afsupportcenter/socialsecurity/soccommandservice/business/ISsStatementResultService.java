package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementResultDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatementResult;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 对账差异结果表 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface ISsStatementResultService extends IService<SsStatementResult> {

    /**
     * <p>Description: 查询对账单结果表</p>
     *
     * @author wengxk
     * @date 2017-12-12
     * @param ssStatementResultDTO 对账单结果检索条件
     * @return  PageRows<SsStatementDTO>
     */
    List<SsStatementResultDTO> statementResultQuery(SsStatementResultDTO ssStatementResultDTO);
}
