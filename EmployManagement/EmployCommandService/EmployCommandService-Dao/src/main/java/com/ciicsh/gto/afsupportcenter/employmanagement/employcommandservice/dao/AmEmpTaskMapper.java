package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用工退工任务单 Mapper 接口
 * </p>
 */
public interface AmEmpTaskMapper extends BaseMapper<AmEmpTask> {

    List<AmEmpTaskBO> queryAmEmpTask(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO>   taskCount(AmEmpTaskBO amEmpTaskBO);

    List<AmEmpTaskBO>  queryAmEmploymentById(String AmEmploymentId);

    List<AmEmpTaskBO>   queryEmployeeHository(String employeeId);

    List<AmEmpTaskBO>  queryCustom(String companyId);

}