package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.MedicalRelationTransformPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.dao.MedicalRelationTransformMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.MedicalRelationTransformCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteInvocationFailureException;
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
public class MedicalRelationTransformCommandServiceImpl implements MedicalRelationTransformCommandService {

    @Autowired
    private MedicalRelationTransformMapper medicalRelationTransformMapper;

    @Override
    public int save(MedicalRelationTransformPO medicalRelationTransform){
       Integer returnNum= medicalRelationTransformMapper.insert(medicalRelationTransform);
       return returnNum;
    }

    @Override
    public int edit(MedicalRelationTransformPO medicalRelationTransform){
        return  medicalRelationTransformMapper.updateById(medicalRelationTransform);
    }
}
