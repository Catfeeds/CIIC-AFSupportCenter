package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.employSearchExportOpt;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工退工任务单 Mapper 接口
 * </p>
 */
public interface AmEmpTaskMapper extends BaseMapper<AmEmpTask> {

    List<AmEmpTaskBO> queryAmEmpTask(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO>   taskCount(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO>  queryAmEmploymentById(Map<String,Object> param);

    AmEmpTaskBO  queryAccout(String companyId);

    AmEmpTaskBO  selectEmployId(Map<String,Object> param);

    AmEmpTaskBO  queryEmpTask(AmEmpTaskBO amEmpTaskBO);

    Integer updateTaskStatus(Map<String,Object> param);

    List<employSearchExportOpt>  queryAmEmpTaskList(AmEmpTaskBO amEmpTaskBO);

}
