package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 本地社保的雇员任务单 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpTask")
@Log("本地社保的雇员任务单")
public class SsEmpTaskController extends BasicController<ISsEmpTaskService> {

    /**
     * 雇员日常操作查询
     */
    @Log("雇员日常操作查询")
    @PostMapping("/employeeOperatorQuery")
    public JsonResult<List<SsEmpTaskDTO>> employeeOperatorQuery(PageInfo pageInfo) {
        PageRows<SsEmpTaskDTO> pageRows = business.employeeOperatorQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }
}

