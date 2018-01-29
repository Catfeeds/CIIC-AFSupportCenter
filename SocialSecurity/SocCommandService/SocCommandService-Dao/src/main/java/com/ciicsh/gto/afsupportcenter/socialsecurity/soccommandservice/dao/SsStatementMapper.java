package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsStatementBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.StatementExportArgs;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.StatementExportOpt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录） Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Mapper
@Component
public interface SsStatementMapper extends BaseMapper<SsStatement> {

    /**
     * <p>Description: 查询对账单主表</p>
     *
     * @author wengxk
     * @date 2017-12-08
     * @param ssStatementBO 对账单查询实体
     * @return  PageRows<SsStatement>
     */
    List<SsStatementBO> statementQuery(SsStatementBO ssStatementBO);

    /**
     * <p>Description: 根据ID查询对账单主表</p>
     *
     * @author wengxk
     * @date 2017-12-11
     * @param ssStatementBO 对账单查询实体
     * @return  SsStatement
     */
    SsStatementBO serachStatementByStatementId(SsStatementBO ssStatementBO);


    /**
     * 对账导出数据集合
     * @param args 导出参数
     * @return 返回数据集合
     */
    List<StatementExportOpt> statementExportQuery(StatementExportArgs args);


}
