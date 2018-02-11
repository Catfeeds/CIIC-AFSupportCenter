package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskHandleDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
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
    PageRows<HfEmpTaskBo> queryHfEmpTaskInPage(PageInfo pageInfo);

    /**
     * 分页查询雇员任务单信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<HfEmpTaskBo> queryHfEmpTaskInPage(PageInfo pageInfo, String exceptTaskCategories);

    /**
     * 查询雇员任务单办理信息
     * @param hfEmpTaskHandleDTO
     * @return
     */
    List<HfEmpTaskHandleBo> getEmpTaskHandleData(HfEmpTaskHandleDTO hfEmpTaskHandleDTO);
    /**
     * 保存数据到雇员任务单表
     *
     * @param taskMsgDTO
     * @param taskCategory
     * @param isChange
     * @param dto
     * @return
     */
    boolean saveEmpTaskTc(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, Integer isChange, AfEmployeeInfoDTO dto);

    /**
     * 更新旧的雇员任务单
     *
     * @param taskMsgDTO 消息队列接受的对象
     * @param dto        取得的雇员信息
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    boolean updateEmpTaskTc(TaskCreateMsgDTO taskMsgDTO,
                            AfEmployeeInfoDTO dto);

    /**
     * 保存数据到雇员任务单表
     *
     * @param taskMsgDTO
     * @param taskCategory
     * @param isChange
     * @param dto
     * @return
     * @throws Exception
     */
    boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, Integer isChange,
                         AfEmployeeInfoDTO dto) throws Exception;


    /**
     * 查询任务单信息
     *
     * @param ssEmpTask
     */
    List<HfEmpTask> queryByTaskId(HfEmpTask ssEmpTask);

}
