package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsStatementBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatement;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsStatementMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsStatementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.StatementExportArgs;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.StatementExportOpt;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录） 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@Service
public class SsStatementServiceImpl extends ServiceImpl<SsStatementMapper, SsStatement> implements SsStatementService {

    @Override
    public PageRows<SsStatementBO> statementQuery(PageInfo pageInfo) {
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.statementQuery(pageInfo.toJavaObject(SsStatementBO.class)));
    }

    @Override
    public SsStatementBO serachStatementData(SsStatementBO ssStatementBO) {
        return baseMapper.serachStatementByStatementId(ssStatementBO);
    }

    @Override
    public List<StatementExportOpt> statementExportQuery(StatementExportArgs args) {
        return baseMapper.statementExportQuery(args);
    }

}
