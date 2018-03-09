package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmTaskParamBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.employSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工退工任务单 服务类
 * </p>
 */
public interface IAmEmpTaskService extends IService<AmEmpTask> {

    PageRows<AmEmpTaskBO> queryAmEmpTask(PageInfo pageInfo);

    List<AmEmpTaskBO> taskCount(PageInfo pageInfo);

    List<AmEmpTaskBO> queryAmEmpTaskById(Map<String,Object> param);

    boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception;

    AmEmpTaskBO queryAccout(String companyId);

    AmEmpTaskBO queryEmpTask(AmEmpTaskBO amEmpTaskBO);

    boolean updateTaskStatus(Map<String,Object> param);

    AmEmpTask getAmEmpTaskById(Long amEmpTaskId);

    Map<String, Object>  getInformation(AmTaskParamBO param);

    boolean insertTaskFire(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception;

    List<employSearchExportOpt>  queryAmEmpTaskList(AmEmpTaskBO amEmpTaskBO);

}
