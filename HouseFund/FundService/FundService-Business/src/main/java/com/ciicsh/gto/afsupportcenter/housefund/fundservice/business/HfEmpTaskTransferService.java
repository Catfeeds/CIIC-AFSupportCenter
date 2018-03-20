package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskRejectExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;

import java.util.List;

public interface HfEmpTaskTransferService extends IService<HfEmpTask> {

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<EmpTaskTransferBo> queryEmpTaskTransferPage(PageInfo pageInfo);

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @param pageInfo
     * @return
     */
    PageRows<EmpTaskTransferBo> queryEmpTaskTransferNewTaskPage(PageInfo pageInfo);

    HfEmpTaskHandleBo queryComEmpTransferForm(String employeeId,String companyId,Long empTaskId);

    JsonResult<Object> submitTransferTask(EmpTaskTransferBo empTaskTransferBo);
}
