package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.bo.TaskPrintBO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskDetialDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 证件任务单材料 服务类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
public interface TaskMaterialService extends IService<TaskMaterial> {

    /**
     * 根据任务单id查询材料收缴信息
     * @param taskId
     * @return
     */
    TaskMaterial selectByTaskId(String taskId);

    /**
     * 更新材料收缴信息
     * @param taskMaterial
     * @return
     */
    int updateTaskMaterials(TaskMaterial taskMaterial);

    boolean insertOrUpdateTaskMaterial(TaskDetialDTO taskDetialDTO,long taskId);

    TaskMaterial selectMetarials(String taskId);
}
