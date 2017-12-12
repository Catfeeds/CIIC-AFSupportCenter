package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementResultDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatementResultPO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsStatementResultMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsStatementResultService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 对账差异结果表 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsStatementResultServiceImpl extends ServiceImpl<SsStatementResultMapper, SsStatementResultPO> implements ISsStatementResultService {

    @Override
    public List<SsStatementResultDTO> statementResultQuery(SsStatementResultDTO ssStatementResultDTO) {
        return baseMapper.statementResultQuery(ssStatementResultDTO);
    }
}
