package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto.InsurancePolicyDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsurancePolicyQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyPO;
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
 * 保单表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/queryservice/insurancePolicy")
public class InsurancePolicyController {
    @Autowired
    private InsurancePolicyQueryService insurancePolicyQueryService;

    @RequestMapping(value = "/getInsurancePolicyListByID", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getInsurancePolicyListByID(String insurancecompanyid) {
        try {
            List<InsurancePolicyPO> bolist = insurancePolicyQueryService.getInsurancePolicyListByID(insurancecompanyid);
            List<InsurancePolicyDTO> agentBusinessIpDTOList = ConvertUtil.listConvert(bolist, InsurancePolicyDTO.class);
            return ResultGenerator.genSuccessResult(agentBusinessIpDTOList);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
