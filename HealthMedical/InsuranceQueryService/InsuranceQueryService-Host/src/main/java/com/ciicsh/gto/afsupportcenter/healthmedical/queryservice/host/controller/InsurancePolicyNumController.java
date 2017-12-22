package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;


import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto.InsurancePolicyNumDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsurancePolicyNumQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyNumPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.bo.InsurancePolicyNumBO;
import com.ciicsh.gto.afsupportcenter.util.ConvertUtil;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 保单号表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/queryservice/insurancePolicyNum")
public class InsurancePolicyNumController {
    @Autowired
    private InsurancePolicyNumQueryService insurancePolicyNumQueryService;

    @RequestMapping(value = "/getInsurancePolicyNumListByID", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getInsurancePolicyNumListByID(String insurancepolicyid) {
        try {
            List<InsurancePolicyNumPO> bolist = insurancePolicyNumQueryService.getInsurancePolicyNumListByID(insurancepolicyid);
            List<InsurancePolicyNumDTO> dtoList = ConvertUtil.listConvert(bolist, InsurancePolicyNumDTO.class);
            return ResultGenerator.genSuccessResult(dtoList);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }


    @RequestMapping(value = "/getInsurancePolicyNumByID", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getInsurancePolicyNumByID(String insurancepolicynumid) {
        try {
            InsurancePolicyNumBO bo = insurancePolicyNumQueryService.getInsurancePolicyNumByID(insurancepolicynumid);

            return ResultGenerator.genSuccessResult(bo);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
