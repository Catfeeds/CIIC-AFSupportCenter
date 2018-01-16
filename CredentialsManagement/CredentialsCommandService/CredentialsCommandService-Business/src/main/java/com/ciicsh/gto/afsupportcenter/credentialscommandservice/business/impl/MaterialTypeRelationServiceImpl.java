package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.MaterialTypeRelationService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.MaterialTypeRelationMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.MaterialTypeRelationPO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 证件类型与材料关系表 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Service
public class MaterialTypeRelationServiceImpl extends ServiceImpl<MaterialTypeRelationMapper, MaterialTypeRelationPO> implements MaterialTypeRelationService {
	
}
