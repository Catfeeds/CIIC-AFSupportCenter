package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TimedTask;

public interface TimedTaskService extends IService<TimedTask> {
    TimedTask select(Long taskId);

    Integer updateTimedTaskByTaskId(String taskId);
}