package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountComRelation;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
public interface SsAccountComRelationMapper extends BaseMapper<SsAccountComRelation> {

}
