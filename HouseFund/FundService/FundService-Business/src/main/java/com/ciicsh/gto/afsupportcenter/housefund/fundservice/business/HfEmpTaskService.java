package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;

import java.util.List;
import java.util.Map;

public interface HfEmpTaskService extends IService<HfEmpTask> {

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<HfEmpTaskExportBo> queryHfEmpTaskInPage(PageInfo pageInfo, String userId);

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @param exceptTaskCategories
     * @return
     */
    PageRows<HfEmpTaskExportBo> queryHfEmpTaskInPage(PageInfo pageInfo, String userId, String exceptTaskCategories);

    /**
     * 分页查询批退雇员任务单信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<HfEmpTaskRejectExportBo> queryHfEmpTaskRejectInPage(PageInfo pageInfo, String userId, String exceptTaskCategories);


    /**
     * 更新数据到雇员任务单表
     * @param taskMsgDTO
     * @param fundCategory
     * @param dto
     * @return
     */
    boolean updateEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, AfEmployeeInfoDTO dto, AfEmpSocialDTO socialDTO);

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
    boolean addEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, Integer processCategory, Integer taskCategory, String oldAgreementId, Integer isChange,
                       Map<String, Object> cityCodeMap, AfEmployeeInfoDTO dto, AfEmpSocialDTO socialDTO, AfCompanyDetailResponseDTO afCompanyDetailResponseDTO) throws Exception;


    /**
     * 查询任务单信息
     *
     * @param ssEmpTask
     */
    List<HfEmpTask> queryByTaskId(HfEmpTask ssEmpTask);

    AfEmpSocialDTO getAfEmpSocialByType(List<AfEmpSocialDTO> socialDTOS, String fundCategory);

    Integer getExistHandleRemarkCount(HfEmpTaskBo hfEmpTaskBo);

    void createTransferTask(HfEmpTask inputHfEmpTask, Long comAccountId);

    boolean autoOffset(String companyId, String employeeId, Integer hfType, Integer offsetType);

    EmpTaskDetailBO getEmpTaskDetailByTaskId(String taskId);
}
