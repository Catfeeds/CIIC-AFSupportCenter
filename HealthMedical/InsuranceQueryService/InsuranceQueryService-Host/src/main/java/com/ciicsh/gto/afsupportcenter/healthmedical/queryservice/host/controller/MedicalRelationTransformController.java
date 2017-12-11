package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;


import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.MedicalRelationTransformQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.MedicalRelationTransformPO;

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
 * 医疗关系转移表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */
@RestController
@RequestMapping("/api/afsupportcenter/healthmedical/queryservice/MedicalRelationTransform")
public class MedicalRelationTransformController  extends BasicController<MedicalRelationTransformQueryService>{

    @Autowired
    private MedicalRelationTransformQueryService medicalRelationTransformQueryServiceQueryService;


    @Log("医疗关系转移单条记录查询")
    @GetMapping("/getEntityById")
    public JsonResult getEntityById(String id) {
        JsonResult jr = new JsonResult();
        jr.setData(medicalRelationTransformQueryServiceQueryService.getById(id));
        return jr;
    }

    @Log("医疗关系转移查询")
    @PostMapping("/getMedicalRelationTransformList")
    public JsonResult<List<MedicalRelationTransformPO>> getEntityList(PageInfo pageInfo) {
        PageRows<MedicalRelationTransformPO> pageRows = business.medicalRelationTransformMapperQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }
}
