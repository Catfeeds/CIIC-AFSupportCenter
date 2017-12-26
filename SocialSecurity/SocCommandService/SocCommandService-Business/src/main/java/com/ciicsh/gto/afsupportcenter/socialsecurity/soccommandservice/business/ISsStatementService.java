package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsStatementBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatement;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录） 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
public interface ISsStatementService extends IService<SsStatement> {

    /**
     * <p>Description: 查询对账单主表</p>
     *
     * @author wengxk
     * @date 2017-12-08
     * @param pageInfo 翻页检索条件
     * @return  PageRows<SsStatementBO>
     */
    PageRows<SsStatementBO> statementQuery(PageInfo pageInfo);


    /**
     * <p>Description: 查询对账单主表</p>
     *
     * @author wengxk
     * @date 2017-12-08
     * @param ssStatementBO 检索条件
     * @return  PageRows<ssStatementBO>
     */
    SsStatementBO serachStatementData(SsStatementBO ssStatementBO);

}
