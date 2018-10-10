package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TaskMaterialMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.bo.TaskPrintBO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskDetialDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 证件任务单材料 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Service
public class TaskMaterialServiceImpl extends ServiceImpl<TaskMaterialMapper, TaskMaterial> implements TaskMaterialService {

    @Autowired
    private TaskMaterialMapper taskMaterialMapper;

    @Override
    public TaskMaterial selectByTaskId(String taskId) {
        TaskMaterial taskMaterial = new TaskMaterial();
        taskMaterial.setTaskId(taskId);
        return taskMaterialMapper.selectOne(taskMaterial);
    }

    @Override
    public int updateTaskMaterials(TaskMaterial taskMaterial) {
        Wrapper<TaskMaterial> wr = new EntityWrapper<TaskMaterial>().eq("task_id",taskMaterial.getTaskId());
        return taskMaterialMapper.update(taskMaterial, wr);
    }

    @Override
    public boolean insertOrUpdateTaskMaterial(TaskDetialDTO taskDetialDTO, long taskId) {
        TaskMaterial taskMaterial = CommonTransform.convertToEntity(taskDetialDTO, TaskMaterial.class);
        taskMaterial.setTaskId(String.valueOf(taskDetialDTO.getTaskId()));
        return this.insertMaterials(taskDetialDTO,taskMaterial);
    }

    @Override
    public TaskMaterial selectMetarials(String taskId) {
        TaskMaterial taskMaterial = new TaskMaterial();
        taskMaterial.setTaskId(taskId);
        return taskMaterialMapper.selectOne(taskMaterial);
    }

    private Boolean insertMaterials(TaskDetialDTO taskDetialDTO, TaskMaterial taskMaterial) {
        delete(new EntityWrapper<TaskMaterial>().eq("task_id", taskDetialDTO.getTaskId()));
        taskMaterial.setCreatedBy(UserContext.getUser().getDisplayName());
        taskMaterial.setCreatedTime(new Date());
        taskMaterial.setModifiedBy(UserContext.getUser().getDisplayName());
        taskMaterial.setModifiedTime(new Date());
        taskMaterial.setTaskId(String.valueOf(taskDetialDTO.getTaskId()));
        return this.insert(taskMaterial);
    }
}
