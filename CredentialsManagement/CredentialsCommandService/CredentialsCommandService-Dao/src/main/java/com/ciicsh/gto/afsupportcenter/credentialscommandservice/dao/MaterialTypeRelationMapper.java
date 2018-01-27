package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.MaterialTypeRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 证件类型与材料关系表 Mapper 接口
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Repository
public interface MaterialTypeRelationMapper extends BaseMapper<MaterialTypeRelation> {

    /**
     * 查询材料列表
     * @param ids
     * @return
     */
    List<MaterialTypeRelation> selectByIds(@Param("ids")List<String> ids);
}