package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.AgentBusinessIpCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.AgentBusinessIpPO;

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
public class AgentBusinessIpController{


}

