package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.business.HealthMedicalJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 健康医疗定时任务 前端控制器
 * </p>
 *
 * @author chenpb
 * @since 2018-02-06
 */
@RestController
@RequestMapping("/api/support/healthMedical")
public class HealthMedicalJobController {
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(HealthMedicalJobController.class);

    @Autowired
    private HealthMedicalJobService healthMedicalJobService;

    /**
     * @description 补充医疗受理任务
     * @author chenpb
     * @since 2018-02-06
     * @param
     * @return
     */
    @Scheduled(cron = "20 0/30 * * * *")
//    @GetMapping(value = "/handleSupplyMedical")
    public void handleSupplyMedical() {
        try {
            logger.info("+++++++++++++++++++++++++++ 补充医疗受理任务: 开始 +++++++++++++++++++++++++++ ");
            healthMedicalJobService.handleSupplyMedical();
            logger.info("+++++++++++++++++++++++++++ 补充医疗受理任务: 结束 +++++++++++++++++++++++++++ ");
        } catch (Exception e) {
            logger.error("健补充医疗受理任务 异常 ：--------------------------------------->  " + e.getMessage());
        }
    }

    /**
     * @description 未投保医疗任务
     * @author chenpb
     * @since 2018-02-06
     * @param
     * @return
     */
    @Scheduled(cron = "45 0/30 * * * *")
//    @GetMapping(value = "/handleUninsuredMedical")
    public void handleUninsuredMedical() {
        try {
            logger.info("+++++++++++++++++++++++++++ 未投保医疗任务: 开始 +++++++++++++++++++++++++++ ");
            healthMedicalJobService.handleUninsuredMedical();
            logger.info("+++++++++++++++++++++++++++ 未投保医疗任务: 结束 +++++++++++++++++++++++++++ ");
        } catch (Exception e) {
            logger.error("未投保医疗任务 异常 ：--------------------------------------->  " + e.getMessage());
        }
    }
}
