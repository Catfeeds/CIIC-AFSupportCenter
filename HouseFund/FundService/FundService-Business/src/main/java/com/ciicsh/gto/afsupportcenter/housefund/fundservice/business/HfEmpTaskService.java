package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskRejectExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;

import java.util.List;

public interface HfEmpTaskService extends IService<HfEmpTask> {

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<HfEmpTaskExportBo> queryHfEmpTaskInPage(PageInfo pageInfo);

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @param exceptTaskCategories
     * @return
     */
    PageRows<HfEmpTaskExportBo> queryHfEmpTaskInPage(PageInfo pageInfo, String exceptTaskCategories);

    /**
     * 分页查询批退雇员任务单信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<HfEmpTaskRejectExportBo> queryHfEmpTaskRejectInPage(PageInfo pageInfo, String exceptTaskCategories);


    /**
     * 更新数据到雇员任务单表
     * @param taskMsgDTO
     * @param fundCategory
     * @param dto
     * @return
     */
    boolean updateEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, AfEmployeeInfoDTO dto);

    /**
     * 添加数据到雇员任务单表
     * @param taskMsgDTO
     * @param fundCategory
     * @param taskCategory
     * @param isChange
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory,Integer processCategory,Integer taskCategory, String oldAgreementId, Integer isChange,
                         AfEmployeeInfoDTO dto) throws Exception;


    /**
     * 查询任务单信息
     *
     * @param ssEmpTask
     */
    List<HfEmpTask> queryByTaskId(HfEmpTask ssEmpTask);
}
