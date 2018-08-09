package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.ArchiveDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.EmploymentDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.ResignDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.TaskParamDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.EmployeeBatchBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.employSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpCollectExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpDispatchExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工退工任务单 服务类
 * </p>
 */
public interface IAmEmpTaskService extends IService<AmEmpTask> {

    PageRows<AmEmpTaskBO> queryAmEmpTask(PageInfo pageInfo);

    List<AmEmpTaskBO> queryAmEmpTaskCompanyNames(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO> taskCount(PageInfo pageInfo);

    List<AmEmpTaskBO> queryAmEmpTaskById(Map<String, Object> param);

    boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception;

    AmEmpTaskBO queryAccout(String companyId);

    AmEmpTaskBO queryEmpTask(AmEmpTaskBO amEmpTaskBO);

    boolean updateTaskStatus(Map<String, Object> param);

    AmEmpTask getAmEmpTaskById(Long amEmpTaskId);

    boolean insertTaskFire(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception;

    List<employSearchExportOpt>  queryAmEmpTaskList(AmEmpTaskBO amEmpTaskBO);

    AmEmpTaskBO queryAmEmpTaskBO(Object empCompanyId);

    AmEmpTaskBO getDefualtEmployBO(AmEmpTaskBO amEmpTaskBO);

    boolean insertTaskFireChange(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception;

    EmploymentDTO getEmploymentByTaskId(TaskParamDTO taskParamDTO);

    ArchiveDTO getArchiveByEmployeeId(TaskParamDTO taskParamDTO);

    ResignDTO getResignByTaskId(TaskParamDTO taskParamDTO);

    Map<String,Object> batchSaveEmployee(AmArchiveBO amArchiveBO);

    Map<String,Object> batchSaveEmployment(EmployeeBatchBO employeeBatchBO);

    Map<String,Object> batchCheck(EmployeeBatchBO employeeBatchBO);

    List<AmEmpDispatchExportPageDTO> queryExportOptDispatch(AmEmpTaskBO amEmpTaskBO,Integer employCode,Integer pageSize);

    List<AmEmpDispatchExportPageDTO> queryExportOptDispatch(AmEmpTaskBO amEmpTaskBO,Integer pageSize);

    List<AmEmpCollectExportPageDTO> queryExportOptCollect(AmEmpTaskBO amEmpTaskBO, Integer employCode);

    List<AmEmpCollectExportPageDTO> queryExportOptCollect(AmEmpTaskBO amEmpTaskBO);

}
