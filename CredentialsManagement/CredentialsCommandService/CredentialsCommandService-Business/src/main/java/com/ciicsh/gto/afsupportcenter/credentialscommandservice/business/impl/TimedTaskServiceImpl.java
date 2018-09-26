package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TimedTaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TimedTaskMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TimedTask;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 定时任务表 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-09-18
 */
@Service
public class TimedTaskServiceImpl extends ServiceImpl<TimedTaskMapper, TimedTask> implements TimedTaskService {

    @Override
    public TimedTask select(Long taskId) {
        List<TimedTask> list = baseMapper.selectList(new EntityWrapper<TimedTask>().eq("task_id", taskId));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Integer updateTimedTaskByTaskId(String taskId) {
        TimedTask timedTask = new TimedTask();
        timedTask.setActive(false);
        return baseMapper.update(timedTask, new EntityWrapper<TimedTask>().eq("task_id", taskId));
    }
}
