package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;


import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsuranceCompanyQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsuranceCompanyPO;

import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * <p>
 * 保险公司表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
@RestController
@RequestMapping("/queryservice/InsuranceCompany")
public class InsuranceCompanyController extends BasicController<InsuranceCompanyQueryService> {
    @Autowired
    private InsuranceCompanyQueryService insuranceCompanyQueryService;

    @Log("保险公司单条记录查询")
    @GetMapping("/getEntityById")
    public JsonResult getEntityById(String id) {
        JsonResult jr = new JsonResult();
        InsuranceCompanyPO po = insuranceCompanyQueryService.getEntityByID(id);
        if (po == null) {
            JsonResultKit.ofNum(400, "未查找到数据", 0);
        } else {
            jr.setData(po);
        }
        return jr;
    }

    @Log("保险公司列表查询")
    @GetMapping("/getALL")
    public JsonResult getALL() {
        JsonResult jr = new JsonResult();
        List<InsuranceCompanyPO> list = insuranceCompanyQueryService.getAll();
        if (list.size() == 0) {
            JsonResultKit.ofNum(400, "未查找到数据", 0);
        } else {
            jr.setData(list);
        }
        return jr;
    }

}
