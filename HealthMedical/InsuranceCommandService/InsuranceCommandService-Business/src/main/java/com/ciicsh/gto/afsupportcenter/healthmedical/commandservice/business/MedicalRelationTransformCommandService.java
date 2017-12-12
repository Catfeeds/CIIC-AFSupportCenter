package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.MedicalRelationTransformPO;


/**
 * <p>
 * 医疗关系转移表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface MedicalRelationTransformCommandService {

    int save(MedicalRelationTransformPO medicalRelationTransform);
    int edit(MedicalRelationTransformPO medicalRelationTransform);

}
