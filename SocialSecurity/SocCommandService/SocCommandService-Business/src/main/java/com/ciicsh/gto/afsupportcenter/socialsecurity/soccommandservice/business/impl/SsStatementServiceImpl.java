package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatement;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsStatementMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsStatementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录） 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@Service
public class SsStatementServiceImpl extends ServiceImpl<SsStatementMapper, SsStatement> implements ISsStatementService {

}
