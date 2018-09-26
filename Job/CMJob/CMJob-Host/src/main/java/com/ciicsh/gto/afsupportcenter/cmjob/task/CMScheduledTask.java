package com.ciicsh.gto.afsupportcenter.cmjob.task;

import com.ciicsh.gto.afsupportcenter.cmjob.service.TimedTaskService;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by guwei on 2018/9/19.
 */
@Component
@RestController
@RequestMapping("/api")
public class CMScheduledTask {

    @Autowired
    private TimedTaskService timedTaskService;

//    @Autowired
//    private LogApiUtil logApiUtil;

    /**
     * 台账批量生成一次性账单
     *
     * 每天凌晨启动一次，"0 0 0 * * ?"
     */
    @Scheduled(cron = "0 0 0 * * ?")
//    @GetMapping("/test")
    public void doEnterBillTask() {
//        logApiUtil.info(LogMessage.create().setTitle("证件管理周期性生成一次性账单").setContent("开始"));
        timedTaskService.doTimedTask();
//        logApiUtil.info(LogMessage.create().setTitle("证件管理周期性生成一次性账单").setContent("结束"));
    }

}
