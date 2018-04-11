package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.MaterialDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.MaterialUpdateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpMaterialBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhiwen on 2018/1/26.
 */
public interface IAmEmpMaterialService extends IService<AmEmpMaterial> {

    PageRows<AmEmpMaterialBO> queryAmEmpMaterial(PageInfo pageInfo);

    List<AmEmpMaterialBO> queryAmEmpMaterialList(AmEmpMaterialBO amEmpMaterialBO);

    PageRows<AmEmpMaterialBO> queryMaterialDic(PageInfo pageInfo);

    List<MaterialDTO> queryMaterialByTaskId(Long empTaskId);

    boolean updateMaterialByTaskId(MaterialUpdateDTO materialUpdateDTO);
}
