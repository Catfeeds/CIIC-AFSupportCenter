package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpMaterialBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpMaterial;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 材料签收表 Mapper 接口
 * </p>
 */
public interface AmEmpMaterialMapper extends BaseMapper<AmEmpMaterial> {

    List<AmEmpMaterialBO> queryAmEmpMaterial(AmEmpMaterialBO amEmpMaterialBO);

}
