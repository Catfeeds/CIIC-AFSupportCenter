package com.ciicsh.gto.afsupportcenter.cmjob.service;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.cmjob.po.TimedTask;

public interface TimedTaskService extends IService<TimedTask> {
    void doTimedTask();
}