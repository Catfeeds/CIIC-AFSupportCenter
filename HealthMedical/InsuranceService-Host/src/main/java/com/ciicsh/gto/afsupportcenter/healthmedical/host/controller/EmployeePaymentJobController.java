package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.api.EmployeePaymentJobProxy;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.EmployeePaymentJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 雇员付款定时任务 前端控制器
 * </p>
 *
 * @author chenpb
 * @since 2018-01-16
 */
@RestController
@RequestMapping("/api/support/employeePayment")
public class EmployeePaymentJobController implements EmployeePaymentJobProxy {
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(EmployeePaymentJobController.class);

    @Autowired
    private EmployeePaymentJobService employeePaymentService;

    /**
     * @description 雇员付款任务
     * @author chenpb
     * @since 2018-01-29
     * @param
     * @return
     */
    @Scheduled(cron = "0 0/10 * * * *")
//    @GetMapping(value = "/handle")
    public void handle() {
        try {
            logger.info("+++++++++++++++++++++++++++ 雇员付款任务: 开始 +++++++++++++++++++++++++++ ");
            employeePaymentService.handle();
            logger.info("+++++++++++++++++++++++++++ 雇员付款任务: 结束 +++++++++++++++++++++++++++ ");
        } catch (Exception e) {
            logger.error("雇员付款任务 异常 ：--------------------------------------->  " + e.getMessage());
        }
    }
}
