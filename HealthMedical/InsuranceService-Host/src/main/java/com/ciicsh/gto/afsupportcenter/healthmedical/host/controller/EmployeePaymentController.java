package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.api.EmployeePaymentProxy;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.EmployeePaymentService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.PaymentBatchDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import com.ciicsh.gto.commonservice.util.dto.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class EmployeePaymentController implements EmployeePaymentProxy {
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(EmployeePaymentController.class);

    @Autowired
    private EmployeePaymentService employeePaymentService;

    /**
     * @description 更新结算中心付款结果
     * @author chenpb
     * @since 2018-01-31
     * @param
     * @return
     */
    @Override
    @PostMapping(value = "/updatePayment")
    public Result updatePayment(@RequestBody PaymentBatchDTO dto) {
        return ResultGenerator.genSuccessResult();
    }

    /**
     * @description 雇员付款任务
     * @author chenpb
     * @since 2018-01-29
     * @param
     * @return
     */
//    @Scheduled(cron = "0/6 * * * * *")
    @GetMapping(value = "/handleEmpPayment")
//    @PostMapping(value = "/handleEmpPayment")
    public void handleEmpPayment() {
        try {
            logger.info("+++++++++++++++++++++++++++ 雇员付款任务: 开始 +++++++++++++++++++++++++++ ");
            employeePaymentService.handleEmpPayment();
            logger.info("+++++++++++++++++++++++++++ 雇员付款任务: 结束 +++++++++++++++++++++++++++ ");
        } catch (Exception e) {
            logger.error("雇员付款任务 异常 ：--------------------------------------->  " + e.getMessage());
        }
    }
}
