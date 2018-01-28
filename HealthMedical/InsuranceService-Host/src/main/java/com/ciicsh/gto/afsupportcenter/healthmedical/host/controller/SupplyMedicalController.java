package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalAcceptanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


}
