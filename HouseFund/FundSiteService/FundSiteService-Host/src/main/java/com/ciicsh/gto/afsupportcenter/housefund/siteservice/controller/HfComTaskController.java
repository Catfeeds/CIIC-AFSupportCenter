package com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller;


import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 企业任务单控制器：公积金企业任务单所有相关的操作都集中于此控制器
Com：公司简写 前端控制器
 * </p>
 *
 * @author 沈健
 * @since 2018-02-07
 */
@RestController
@RequestMapping("/api/fundcommandservice/hfComTask")
public class HfComTaskController {

    @Autowired
    private HfComTaskService hfComTaskService;

    @Log("查询未处理企业任务单")
    @PostMapping("/postNoProcessTask")
    public JsonResult<List<HfComTaskBo>> postNoProcessTask(PageInfo pageInfo) {

        PageRows<HfComTaskBo> pageRows = hfComTaskService.queryNoProgressCompanyTask(pageInfo);

        return JsonResultKit.ofPage(pageRows);
    }
}
