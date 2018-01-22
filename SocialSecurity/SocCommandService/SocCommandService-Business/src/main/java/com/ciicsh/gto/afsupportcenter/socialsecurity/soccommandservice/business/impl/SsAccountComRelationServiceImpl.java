package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsAccountComRelationBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAccountComRelationService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsAccountComRelationMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountComRelation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@Service
public class SsAccountComRelationServiceImpl extends ServiceImpl<SsAccountComRelationMapper, SsAccountComRelation>
    implements ISsAccountComRelationService {
    /**
     * 通过账户ID查询
     *
     * @param comAccountId
     * @return
     */
    public List<SsAccountComRelationBO> queryByAccountId(String comAccountId) {
        return baseMapper.queryByAccountId(comAccountId);
    }

    /**
     * 通过指定条件查询
     *
     * @param cond
     * @return
     */
    public List<SsAccountComRelation> queryByCond(Map cond) {
        return baseMapper.queryByCond(cond);
    }
}
