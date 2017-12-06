package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.tips.PageTip;
import com.ciicsh.gto.afsupportcenter.util.tips.TipKit;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 独立库客户任务单 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssComTask")
public class SsComTaskController extends BasicController<ISsComTaskService> {
    @Resource
    ISsComTaskService iSsComTaskService;

    @Log("查询企业任务单")
    @RequestMapping(value = "getTask")
    public PageTip<SsComTaskDTO> getCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = iSsComTaskService.queryCompanyTask(pageInfo);
        return TipKit.ofPage(pageRows);

    }


}

