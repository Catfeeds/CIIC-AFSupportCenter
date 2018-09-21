package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpTaskCollection;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.EmployeeBatchBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.archiveSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工主表 服务类
 * </p>
 */
public interface IAmEmploymentService extends IService<AmEmployment> {

    List<AmEmploymentBO> queryAmEmployment(Map<String, Object> param);

    PageRows<AmEmploymentBO> queryAmArchive(PageInfo pageInfo);

    List<AmArchiveReturnPrintDTO> queryAmArchiveForeignerPritDate(PageInfo pageInfo);

    List<AmEmploymentBO> taskCountEmployee(PageInfo pageInfo);

    List<AmEmploymentBO> taskCountResign(PageInfo pageInfo);

    List<archiveSearchExportOpt>  queryAmArchiveList(AmEmploymentBO amEmploymentBO);

    List<AmEmploymentBO> queryAmEmploymentResign(Map<String, Object> param);

    List<AmEmploymentBO> queryAmEmploymentBatch(List<Long> empTaskIds);

    AmEmpTaskCollection queryArchiveTaskCount(AmEmploymentBO amEmploymentBO);

    JsonResult xlsImportAmEmpAdvance(List<AmEmpArchiveAdvanceXsl> opts, String fileName);

    List<AmEmploymentBO>  queryAmEmploymentCount(EmployeeBatchBO employeeBatchBO);

    List<AmEmpDispatchExportPageDTO> queryExportOptDispatch(AmEmploymentBO bo,Integer employCode,Integer pageSize);

    List<AmEmpDispatchExportPageDTO> queryExportOptDispatch(AmEmploymentBO bo,Integer pageSize);

    List<AmEmpCollectExportPageDTO> queryExportOptCollect(AmEmploymentBO bo, Integer employCode);

    List<AmEmpCollectExportPageDTO> queryExportOptCollect(AmEmploymentBO bo);

    List<AmEmpExplainExportPageDTO> queryExportOptExplain(AmEmploymentBO bo, Integer employCode,Integer isEntry);

    List<AmEmpExplainExportPageDTO> queryExportOptExplain(AmEmploymentBO bo,Integer isEntry);

}
