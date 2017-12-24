package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.bo.InsurancePolicyNumBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.InsurancePolicyNumCommandService;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 保单号表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/commandservice/insurancePolicyNum")
public class InsurancePolicyNumController {
    @Autowired
    InsurancePolicyNumCommandService policyNumCommandService;

    @Log("新增")
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Result add(InsurancePolicyNumBO bo) {
        try {
            boolean success = policyNumCommandService.add(bo);
            return ResultGenerator.genSuccessResult(success);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("编辑")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(InsurancePolicyNumBO bo) {
        try {
            boolean success = policyNumCommandService.update(bo);
            return ResultGenerator.genSuccessResult(success);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
