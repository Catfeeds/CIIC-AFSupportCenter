package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalAcceptanceService;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 补充医疗理赔
 *
 * @author xiweizhen
 */
@RestController
@RequestMapping("/supplyMedicalService")
public class SupplyMedicalController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(SupplyMedicalController.class);

    @Autowired
    private SupplyMedicalAcceptanceService supplyMedicalAcceptanceService;

    @GetMapping("syncAcceptanceSummaryDetail")
    public Result syncAcceptanceSummaryDetail() {
        try {
            boolean flag = supplyMedicalAcceptanceService.syncAcceptanceSummaryDetail();
            logger.info("查询受理单分页列表");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
