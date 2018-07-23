package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialOperationLogDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialUpdateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpMaterialBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpMaterialOperationLogBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/1/26.
 */
public interface IAmEmpMaterialService extends IService<AmEmpMaterial> {

    PageRows<AmEmpMaterialBO> queryAmEmpMaterial(PageInfo pageInfo);

    List<AmEmpMaterialOperationLogBO> queryAmEmpMaterialOperationLogList(PageInfo pageInfo);

    List<AmEmpMaterialBO> queryAmEmpMaterialList(AmEmpMaterialBO amEmpMaterialBO);

    List<AmEmpMaterialOperationLogBO> queryAmEmpMaterialLogList(AmEmpMaterialBO amEmpMaterialBO);

    PageRows<AmEmpMaterialBO> queryMaterialDic(PageInfo pageInfo);

    List<AmEmpMaterialBO> queryMaterialDicList();

    List<MaterialDTO> queryMaterialByTaskId(Long empTaskId);

    boolean updateMaterialByTaskId(MaterialUpdateDTO materialUpdateDTO);

    MaterialOperationLogDTO queryMaterialLastOperationLog(String empTaskId);

    List<MaterialOperationLogDTO> queryMaterialOperationLogList(String empTaskId);

    boolean updateMaterialBatch(AmEmpMaterialBO amEmpMaterialBO);

    boolean insertAmEmpMaterialOperationLog(AmEmpMaterialOperationLogBO logBO);

    String receiveMaterial(String taskId, Integer operation, String remark);
}
