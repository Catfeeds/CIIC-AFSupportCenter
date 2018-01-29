package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsStatementBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatement;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.StatementExportArgs;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.StatementExportOpt;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录） 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
public interface SsStatementService extends IService<SsStatement> {

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


    /**
     * 对账导出数据集合
     * @param args 导出参数
     * @return 返回数据集合
     */
    List<StatementExportOpt> statementExportQuery(StatementExportArgs args);

}
