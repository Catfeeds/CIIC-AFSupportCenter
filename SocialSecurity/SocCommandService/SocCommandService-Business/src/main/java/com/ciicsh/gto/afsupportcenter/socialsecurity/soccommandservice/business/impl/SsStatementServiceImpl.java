package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatementPO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsStatementMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsStatementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录） 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsStatementServiceImpl extends ServiceImpl<SsStatementMapper, SsStatementPO> implements ISsStatementService {

    @Override
    public PageRows<SsStatementDTO> statementQuery(PageInfo pageInfo) {
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.statementQuery(pageInfo.toJavaObject(SsStatementDTO.class)));
    }

    @Override
    public SsStatementDTO serachStatementData(SsStatementDTO ssStatementDTO) {
        return baseMapper.serachStatementBystatementId(ssStatementDTO);
    }

}
