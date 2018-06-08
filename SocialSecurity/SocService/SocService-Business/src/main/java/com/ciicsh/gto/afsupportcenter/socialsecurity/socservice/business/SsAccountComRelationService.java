package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAccountComRelationBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountComRelation;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.AccountCompanyRelationOpt;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
public interface SsAccountComRelationService extends IService<SsAccountComRelation> {
    /**
     * 通过账户ID查询
     *
     * @param comAccountId
     * @return
     */
   public List<SsAccountComRelationBO> queryByAccountId(String comAccountId);

    /**
     * 通过指定条件查询
     *
     * @param cond
     * @return
     */
    public List<SsAccountComRelation> queryByCond(Map cond);


    /**
     * 根据企业社保账户获取公司信息
     * @param comAccountId 企业社保账户
     * @return 返回企业社保账户获取公司信息
     */
    List<AccountCompanyRelationOpt> getAccountCompanyRelationByAccountId(Long comAccountId);

}
