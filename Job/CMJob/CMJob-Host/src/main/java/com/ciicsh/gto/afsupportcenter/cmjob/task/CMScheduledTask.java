package com.ciicsh.gto.afsupportcenter.cmjob.task;

import com.ciicsh.gto.afsupportcenter.cmjob.service.TimedTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by guwei on 2018/9/19.
 */
@Component
public class CMScheduledTask {

    private final static Logger logger = LoggerFactory.getLogger(CMScheduledTask.class);

    @Autowired
    private TimedTaskService timedTaskService;

    /**
     * 台账批量生成一次性账单
     *
     * 每天凌晨启动一次，"0 0 0 * * ?"
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void doEnterBillTask() {
        timedTaskService.doTimedTask();
    }

}
