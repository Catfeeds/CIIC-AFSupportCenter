package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountComTie;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsAccountComTieMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAccountComTieService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsAccountComTieServiceImpl extends ServiceImpl<SsAccountComTieMapper, SsAccountComTie> implements ISsAccountComTieService {

}
