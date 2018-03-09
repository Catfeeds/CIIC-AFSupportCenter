package com.ciicsh.gto.afsupportcenter.healthmedical.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.MedicalRelationTransformPO;
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
@Repository
public interface MedicalRelationTransformMapper  extends BaseMapper<MedicalRelationTransformPO> {


    MedicalRelationTransformPO getById(String id);


    List<MedicalRelationTransformPO> medicalRelationTransformMapperQuery(Page<MedicalRelationTransformPO> page, MedicalRelationTransformPO fragmentaryReimbursementDTO);
}