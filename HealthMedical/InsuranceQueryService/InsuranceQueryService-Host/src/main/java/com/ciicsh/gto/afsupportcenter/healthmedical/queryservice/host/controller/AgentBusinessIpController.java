package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;


import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.AgentBusinessIpQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessIpPO;

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
 * 代收代付保单表（不提供维护界面，跑脚本录入） 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
@RestController
@RequestMapping("/queryservice/agentBusinessIp")
public class AgentBusinessIpController extends BasicController<AgentBusinessIpQueryService> {
    @Autowired
    private AgentBusinessIpQueryService agentBusinessIpQueryService;


    @Log("代收代付查询")
    @PostMapping("/getEntityList")
    public JsonResult<List<AgentBusinessIpPO>> getEntityList(AgentBusinessIpPO po) {
        List<AgentBusinessIpPO> list = agentBusinessIpQueryService.getList(po);

        if (list.size() == 0) {
            return JsonResultKit.of(400, "未查找到数据", (List) null);
        } else {
            return JsonResultKit.of(list);
        }

    }

}

