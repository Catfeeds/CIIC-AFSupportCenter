package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用工退工任务单 服务类
 * </p>
 */
public interface IAmEmpTaskService extends IService<AmEmpTask> {

    PageRows<AmEmpTaskBO> queryAmEmpTask(PageInfo pageInfo);

    List<AmEmpTaskBO> taskCount(PageInfo pageInfo);

    List<AmEmpTaskBO> queryAmEmpTaskById(@Param("AmEmploymentId") String AmEmploymentId);

    List<AmEmpTaskBO>  queryEmployeeHository(String employeeId);

    List<AmEmpTaskBO>  queryCustom(String companyId);

}
