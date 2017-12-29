package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsStatementResultBO;
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
     * @param ssStatementResultBO 对账单结果检索条件
     * @return  PageRows<SsStatementBO>
     */
    List<SsStatementResultBO> statementResultQuery(SsStatementResultBO ssStatementResultBO);

    /**
     * <p>Description: 计算对账</p>
     *
     * @author wengxk
     * @date 2017-12-15
     * @param statementId 对账单主表ID
     */
    void calculateSstatementResult(Long statementId);

    /**
     * <p>Description: 创建一个对账结果对象,并初始化数值变量</p>
     *
     * @author wengxk
     * @date 2017-12-15
     * @return  SsStatementResult
     */
    SsStatementResult newSsStatementResult();
}