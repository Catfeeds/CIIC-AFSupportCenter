package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.MedicalRelationTransformQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.MedicalRelationTransformMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.MedicalRelationTransformPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 医疗关系转移表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Service
@Transactional
public class MedicalRelationTransformQueryServiceImpl extends ServiceImpl<MedicalRelationTransformMapper, MedicalRelationTransformPO> implements MedicalRelationTransformQueryService {

    @Autowired
    private MedicalRelationTransformMapper medicalRelationTransformMapper;

    @Override
    public int save(MedicalRelationTransformPO medicalRelationTransform){
        Integer returnNum= medicalRelationTransformMapper.insert(medicalRelationTransform);
        return returnNum;
    }

    @Override
    public int edit(MedicalRelationTransformPO medicalRelationTransform){
        return  medicalRelationTransformMapper.updateAllColumnById(medicalRelationTransform);
    }
    @Override
    public MedicalRelationTransformPO getById(String id) {
        return medicalRelationTransformMapper.selectById(id);
    }

    @Override
    public Page<MedicalRelationTransformPO> medicalRelationTransformMapperQuery(Page<MedicalRelationTransformPO> page, MedicalRelationTransformPO medicalRelationTransformDTO) {
        page.setRecords(baseMapper.medicalRelationTransformMapperQuery(page,medicalRelationTransformDTO));
        return page;
    }

}