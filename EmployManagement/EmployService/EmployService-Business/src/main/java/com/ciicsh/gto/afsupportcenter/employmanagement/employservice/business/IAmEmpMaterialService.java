package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialUpdateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpMaterialBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/1/26.
 */
public interface IAmEmpMaterialService extends IService<AmEmpMaterial> {

    PageRows<AmEmpMaterialBO> queryAmEmpMaterial(PageInfo pageInfo);

    List<AmEmpMaterialBO> queryAmEmpMaterialList(AmEmpMaterialBO amEmpMaterialBO);

    PageRows<AmEmpMaterialBO> queryMaterialDic(PageInfo pageInfo);

    List<AmEmpMaterialBO> queryMaterialDicList();

    List<MaterialDTO> queryMaterialByTaskId(Long empTaskId);

    boolean updateMaterialByTaskId(MaterialUpdateDTO materialUpdateDTO);
}