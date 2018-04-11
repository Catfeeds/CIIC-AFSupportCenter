package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TaskMaterialMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskDetialDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        TaskMaterial taskMaterial = new TaskMaterial();
        taskMaterial.setMaterialIds(taskDetialDTO.getMaterialIds());
        taskMaterial.setTaskId(String.valueOf(taskDetialDTO.getTaskId()));
        taskMaterial.setCompanyId(taskDetialDTO.getCompanyId());
        taskMaterial.setEmployeeId(taskDetialDTO.getEmployeeId());
        taskMaterial.setComp(taskDetialDTO.getComp());
        taskMaterial.setMarryStatus(taskDetialDTO.getMarryStatus());
        taskMaterial.setHasFollower(taskDetialDTO.getHasFollower());
        taskMaterial.setFamilerMaterials(taskDetialDTO.getFamilerMaterials());
        taskMaterial.setApplyAddrChange(taskDetialDTO.getApplyAddrChange());
        taskMaterial.setAddr(taskDetialDTO.getAddr());
        taskMaterial.setFollowerType(taskDetialDTO.getFollowerType());
        taskMaterial.setFollower(taskDetialDTO.getFollower());
        taskMaterial.setHasShPerson(taskDetialDTO.getHasShPerson());
        taskMaterial.setHasChildFollow(taskDetialDTO.getHasChildFollow());
        taskMaterial.setHasSpouseFollow(taskDetialDTO.getHasSpouseFollow());
        taskMaterial.setMarried(taskDetialDTO.getMarried());
        taskMaterial.setJobMaterials(taskDetialDTO.getJobMaterials());
        taskMaterial.setEducate(taskDetialDTO.getEducate());
        taskMaterial.setFollowMaterials(taskDetialDTO.getFollowMaterials());
        taskMaterial.setNotFollowMaterials(taskDetialDTO.getNotFollowMaterials());
        //TODO
        if (taskDetialDTO.getTaskId() == null) {
            taskMaterial.setCreatedBy("gu");
            taskMaterial.setCreatedTime(new Date());
            taskMaterial.setModifiedBy("gu");
            taskMaterial.setModifiedTime(new Date());
            taskMaterial.setTaskId(String.valueOf(taskId));
            return this.insert(taskMaterial);
        } else {
            taskMaterial.setModifiedBy("gu");
            taskMaterial.setModifiedTime(new Date());
            int rows = this.updateTaskMaterials(taskMaterial);
            return rows==1 ? true : false;
        }
    }
}
