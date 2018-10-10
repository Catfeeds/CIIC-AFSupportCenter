package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskTypeService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TaskTypeMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:59 2018/4/3
 */
@Service
public class TaskTypeServiceImpl extends ServiceImpl<TaskTypeMapper, TaskType> implements TaskTypeService {

    @Autowired
    private TaskTypeMapper taskTypeMapper;

    @Override
    public List<TaskType> findTaskType(String pid) {
        Wrapper wr = new EntityWrapper<TaskType>().eq("pid",pid).eq("is_active", true);
        return taskTypeMapper.selectList(wr);
    }
}
