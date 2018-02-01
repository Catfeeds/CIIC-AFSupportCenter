package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.MaterialTypeRelationService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.MaterialTypeRelationMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.MaterialTypeRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 证件类型与材料关系表 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Service
public class MaterialTypeRelationServiceImpl extends ServiceImpl<MaterialTypeRelationMapper, MaterialTypeRelation> implements MaterialTypeRelationService {

    @Autowired
    private MaterialTypeRelationMapper materialTypeRelationMapper;

    @Override
    public List<MaterialTypeRelation> selectList(String materialIds) {
        List<String> ids = Arrays.asList(materialIds.split(","));
        return materialTypeRelationMapper.selectByIds(ids);
    }

    @Override
    public List<MaterialTypeRelation> selectMaterials(String credentialsType, String credentialsDealType) {
        return materialTypeRelationMapper.selectMaterials(credentialsType,credentialsDealType);
    }
}
