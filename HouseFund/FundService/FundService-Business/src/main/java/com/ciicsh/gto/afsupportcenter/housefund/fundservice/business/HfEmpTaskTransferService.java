package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.HfEmpTaskHandleVo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;
import java.util.Map;

public interface HfEmpTaskTransferService extends IService<HfEmpTask> {

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<EmpTaskTransferBo> queryEmpTaskTransferPage(PageInfo pageInfo);

    List<EmpTaskTransferBo> queryEmpTaskTransfer(EmpTaskTransferBo empTaskTransferBo);

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @param pageInfo
     * @return
     */
    PageRows<EmpTaskTransferBo> queryEmpTaskTransferNewTaskPage(PageInfo pageInfo);

    HfEmpTaskHandleVo queryComEmpTransferForm(String employeeId, String companyId, Long empTaskId);

    JsonResult submitTransferTask(EmpTaskTransferBo empTaskTransferBo);

    JsonResult notHandleTransfer(EmpTaskTransferBo empTaskTransferBo);

    List<Map<String, Object>> printTransferTask (EmpTaskTransferBo empTaskTransferBo);

}
