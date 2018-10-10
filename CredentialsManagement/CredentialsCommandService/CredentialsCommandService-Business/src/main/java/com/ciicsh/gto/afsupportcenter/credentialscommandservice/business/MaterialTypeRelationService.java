package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.MaterialTypeRelation;

import java.util.List;

/**
 * <p>
 * 证件类型与材料关系表 服务类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
public interface MaterialTypeRelationService extends IService<MaterialTypeRelation> {

    /**
     * 根据材料id查询材料
     * @param materialIds
     * @return
     */
    List<MaterialTypeRelation> selectList(String materialIds);

    /**
     * 材料收缴菜单
     * @param credentialsType
     * @param credentialsDealType
     * @return
     */
    List<MaterialTypeRelation> selectMaterials(String credentialsType, String credentialsDealType);

    List<MaterialTypeRelation> selectMetarialList(String materialIds);
}
