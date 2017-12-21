package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.InsurancePolicyCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.InsurancePolicyPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.api.dto.InsurancePolicyDTO;
import com.ciicsh.gto.afsupportcenter.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 保单表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/commandservice/insurancePolicy")
public class InsurancePolicyController {
    @Autowired
    private InsurancePolicyCommandService insurancePolicyCommandService;

}
