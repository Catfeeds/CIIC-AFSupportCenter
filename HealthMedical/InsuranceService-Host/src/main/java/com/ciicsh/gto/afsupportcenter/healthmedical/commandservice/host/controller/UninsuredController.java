package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 补充医疗理赔表 前端控制器
 * </p>
 *
 * @author xiweizhen
 */
@RestController
@RequestMapping("/CommandService/Acceptance")
public class UninsuredController {

    @Autowired
    private UninsuredMedicalService uninsuredMedicalService;


}
