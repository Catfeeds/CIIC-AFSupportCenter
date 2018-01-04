package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsurancePolicyBatchQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyBatchPO;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 保单号批次表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/queryservice/insurancePolicyBatch")
public class InsurancePolicyBatchController {
    @Autowired
    private InsurancePolicyBatchQueryService insurancePolicyBatchQueryService;

    @RequestMapping(value = "/getPolicyBatchListByID", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getPolicyBatchListByID(String policynumid) {
        List<InsurancePolicyBatchPO> batchlist = insurancePolicyBatchQueryService.getPolicyBatchByid(policynumid);
        return ResultGenerator.genSuccessResult(batchlist);
    }

    @RequestMapping(value = "/getPolicyBatchDetailByID", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getPolicyBatchDetailByID(String policybatchdetailid) {
        InsurancePolicyBatchPO batch = insurancePolicyBatchQueryService.selectById(policybatchdetailid);
        return ResultGenerator.genSuccessResult(batch);
    }

}

