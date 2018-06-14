package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialUpdateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpMaterialBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpMaterial;

import java.util.List;

/**
 * <p>
 * 材料签收表 Mapper 接口
 * </p>
 */
public interface AmEmpMaterialMapper extends BaseMapper<AmEmpMaterial> {

    List<AmEmpMaterialBO> queryAmEmpMaterial(AmEmpMaterialBO amEmpMaterialBO);

    List<AmEmpMaterialBO>  queryMaterialDic();

    List<AmEmpMaterialBO>  queryMaterialByTaskId(Long empTaskId);

    Integer updateMaterialByTaskId(MaterialUpdateDTO materialUpdateDTO);

    Integer updateMaterialBatch(AmEmpMaterialBO amEmpMaterialBO);

}
