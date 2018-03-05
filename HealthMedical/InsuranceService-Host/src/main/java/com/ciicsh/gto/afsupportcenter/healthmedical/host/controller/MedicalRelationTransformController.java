package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.MedicalRelationTransformQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.MedicalRelationTransformPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.MedicalRelationTransformDTO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.ResultGenerator;
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
    @PostMapping("/save")
    public Result save(@RequestBody MedicalRelationTransformPO po) {
        try {
            Boolean code = medicalRelationTransformQueryService.insert(po);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("更新")
    @PostMapping("/edit")
    public  Result edit(@RequestBody MedicalRelationTransformPO po) {
        try {
            Boolean code = medicalRelationTransformQueryService.updateAllColumnById(po);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("医疗关系转移单条记录查询")
    @GetMapping("/getEntityById")
    public Result getEntityById(@RequestBody String id) {
        try {
            MedicalRelationTransformPO code = medicalRelationTransformQueryService.getById(id);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("医疗关系转移查询")
    @PostMapping("/getEntityList")

    public Result getEntityList(@RequestBody MedicalRelationTransformDTO  medicalRelationTransformDTO) {
        try {
            Page<MedicalRelationTransformPO> page = new Page<>(medicalRelationTransformDTO.getCurrent(), medicalRelationTransformDTO.getSize());
            page = business.medicalRelationTransformMapperQuery(page, medicalRelationTransformDTO);
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }

    }
}
