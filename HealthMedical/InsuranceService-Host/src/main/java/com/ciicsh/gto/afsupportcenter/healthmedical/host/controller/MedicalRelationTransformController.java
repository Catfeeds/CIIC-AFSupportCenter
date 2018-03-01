package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;


import com.ciicsh.gto.afsupportcenter.healthmedical.business.MedicalRelationTransformQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.MedicalRelationTransformPO;

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
@RequestMapping("/api/afsupportcenter/healthmedical/MedicalRelationTransform")
public class MedicalRelationTransformController  extends BasicController<MedicalRelationTransformQueryService> {

    @Autowired
    private MedicalRelationTransformQueryService medicalRelationTransformQueryService;

    @Log("新增")
    @RequestMapping(value = "/save", method = { RequestMethod.POST})
    public JsonResult<Integer> save(MedicalRelationTransformPO po) {
        int code = business.save(po);
        if(code == 0){
            return JsonResultKit.of(400, "无数据更新",  (Integer) null);
        }else{
            return JsonResultKit.of(400, "操作成功",  (Integer) null);
        }
    }

    @Log("更新")
    @RequestMapping(value = "/edit", method = { RequestMethod.POST})
    public  JsonResult<Integer>  edit(MedicalRelationTransformPO po) {
        int code = business.edit(po);
        if(code == 0){
            return JsonResultKit.of(400, "无数据更新",  (Integer) null);
        }else{
            return JsonResultKit.of(400, "操作成功",  (Integer) null);
        }
    }

    @Log("医疗关系转移单条记录查询")
    @GetMapping("/getEntityById")
    public JsonResult getEntityById(String id) {
        JsonResult jr = new JsonResult();
        MedicalRelationTransformPO po = medicalRelationTransformQueryService.getById(id);
        if (po == null) {
            return JsonResultKit.of(400, "未查找到数据", (List) null);
        } else {
            jr.setData(po);
        }
        return jr;
    }

    @Log("医疗关系转移查询")
    @PostMapping("/getEntityList")
    public JsonResult<List<MedicalRelationTransformPO>> getEntityList(PageInfo pageInfo) {
        PageRows<MedicalRelationTransformPO> pageRows = business.medicalRelationTransformMapperQuery(pageInfo);
        long count = pageRows.getTotal();
        if (count == 0) {
            return JsonResultKit.of(400, "未查找到数据", (List) null);
        } else {
            return JsonResultKit.ofPage(pageRows);
        }

    }
}
