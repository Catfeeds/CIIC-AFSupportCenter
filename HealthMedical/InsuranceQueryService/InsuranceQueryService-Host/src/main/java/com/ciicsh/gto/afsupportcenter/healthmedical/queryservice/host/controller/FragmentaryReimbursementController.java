package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.FragmentaryReimbursementQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.FragmentaryReimbursementPO;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
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
 * 零星报销表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */

@RestController
@RequestMapping("/api/afsupportcenter/healthmedical/queryservice/FragmentaryReimbursement")

    public class FragmentaryReimbursementController extends BasicController<FragmentaryReimbursementQueryService> {

    @Autowired
    private FragmentaryReimbursementQueryService fragmentaryReimbursementQueryService;

    @Log("零星报销单条记录查询")
    @GetMapping("/getEntityById")
    public JsonResult getEntityById(String id) {
        JsonResult jr = new JsonResult();
        FragmentaryReimbursementPO po = fragmentaryReimbursementQueryService.getById(id);
        if (po == null) {
            return JsonResultKit.of(400, "未查找到数据", (List) null);
        } else {
            jr.setData(po);
        }
        return jr;
    }

    @Log("零星报销查询")
    @PostMapping("/getEntityList")
    public JsonResult<List<FragmentaryReimbursementPO>> getEntityList(PageInfo pageInfo) {
        PageRows<FragmentaryReimbursementPO> pageRows = business.getEntityList(pageInfo);
        long count = pageRows.getTotal();
        if (count == 0) {
            return JsonResultKit.of(400, "未查找到数据", (List) null);
        } else {
            return JsonResultKit.ofPage(pageRows);
        }
    }
}


