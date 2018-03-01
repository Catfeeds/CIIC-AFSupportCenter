package com.ciicsh.gto.afsupportcenter.socjob.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsAccountComRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@Mapper
@Component
public interface SsAccountComRelationMapper extends BaseMapper<SsAccountComRelation> {

}
