package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.MedicalRelationTransformPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <p>
  * 医疗关系转移表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface MedicalRelationTransformMapper  extends BaseMapper<MedicalRelationTransformPO> {

    MedicalRelationTransformPO getById(String id);
    List<MedicalRelationTransformPO> medicalRelationTransformMapperQuery(MedicalRelationTransformPO po);

}