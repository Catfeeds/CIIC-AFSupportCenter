package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAccountComRelationBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountComRelation;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.AccountCompanyRelationOpt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
public interface SsAccountComRelationMapper extends BaseMapper<SsAccountComRelation> {
    /**
     * 通过账户ID查询
     * @param comAccountId
     * @return
     */
    List<SsAccountComRelationBO> queryByAccountId(@Param("comAccountId") String comAccountId);

    /**
     * 通过指定条件查询
     * @param cond
     * @return
     */
    List<SsAccountComRelation> queryByCond(Map cond);


    /**
     * 根据企业社保账户获取公司信息
     * @param comAccountId 企业社保账户
     * @return 返回企业社保账户获取公司信息
     */
    AccountCompanyRelationOpt getAccountCompanyRelationByAccountId(@Param("comAccountId") Long comAccountId);

    Integer isExistCompany(@Param("companyId") String companyId);

}
