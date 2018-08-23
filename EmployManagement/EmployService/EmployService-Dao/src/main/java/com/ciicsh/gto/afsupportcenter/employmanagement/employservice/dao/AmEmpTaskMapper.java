package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.EmployeeBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.EmployeeBatchBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.employSearchExportOpt;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工退工任务单 Mapper 接口
 * </p>
 */
public interface AmEmpTaskMapper extends BaseMapper<AmEmpTask> {

    List<AmEmpTaskBO> queryAmEmpTask(AmEmpTaskBO amEmpTaskBO);

    List<String> queryAmEmpTaskCompanys(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO> queryAmEmpTaskOther(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO>   taskCount(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO>  queryAmEmploymentById(Map<String, Object> param);

    AmEmpTaskBO  queryAccout(String companyId);

    AmEmpTaskBO  selectEmployId(Map<String, Object> param);

    List<AmEmpTaskBO>  queryEmpTask(AmEmpTaskBO amEmpTaskBO);

    Integer updateTaskStatus(Map<String, Object> param);

    List<employSearchExportOpt>  queryAmEmpTaskList(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO>  querySocial(Map<String, Object> param);

    List<AmEmpTaskBO> querySocialCi();

    List<EmploymentDTO>  getEmploymentByTaskId(TaskParamDTO taskParamDTO);

    List<ArchiveDTO>  getArchiveByEmployeeId(TaskParamDTO taskParamDTO);

    List<ResignFeedbackDTO>  queryResignLinkByTaskId(TaskParamDTO taskParamDTO);

    List<ResignDTO>  queryResignByTaskId(TaskParamDTO taskParamDTO);

    EmployeeBO queryNature(String id);

    EmployeeBO queryArchiveDriection(String id);

    List<AmEmpTaskBO> queryIsFinish(EmployeeBatchBO employeeBatchBO);

    List<AmEmpTaskBO> queryChange(EmployeeBatchBO employeeBatchBO);

    List<AmEmpTaskBO> queryIsMaterial(EmployeeBatchBO employeeBatchBO);

    List<AmEmpTaskBO> queryByTaskId(String taskId);

}
