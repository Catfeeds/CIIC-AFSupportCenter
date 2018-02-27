package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.AmEmpTaskOfSsService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 用工退工任务单 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2018-02-25
 */
//@Controller
//@RequestMapping("/api/soccommandservice/amEmpTask")
//public class AmEmpTaskOfSsController {
//    @Autowired
//    AmEmpTaskOfSsService amEmpTaskOfSsService;
//    @RequestMapping("/queryReworkInfo")
//    public JsonResult<AmEmpTaskDTO> queryReworkInfo(@RequestParam("empTaskId") String empTaskId){
//        AmEmpTaskDTO amEmpTaskDTO = amEmpTaskOfSsService.queryReworkInfo(empTaskId);
//        return JsonResultKit.of(null==amEmpTaskDTO?new AmEmpTaskDTO():amEmpTaskDTO);
//    }
//}

