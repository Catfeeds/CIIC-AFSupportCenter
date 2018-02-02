package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TaskMaterialMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return taskMaterialMapper.updateTaskMaterials(taskMaterial);
    }
}
