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

    @Log("查询未处理企业任务单")
    @RequestMapping(value = "getNoProgressTask")
    public PageTip<SsComTaskDTO> getNoProgressCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = iSsComTaskService.queryNoProgressCompanyTask(pageInfo);
        return TipKit.ofPage(pageRows);

    }
    @Log("查询处理中企业任务单")
    @RequestMapping(value = "getProgressingTask")
    public PageTip<SsComTaskDTO> getNoProgressingCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = iSsComTaskService.queryProgressingCompanyTask(pageInfo);
        return TipKit.ofPage(pageRows);
    }

    @Log("查询已完成企业任务单")
    @RequestMapping(value = "getFinshedTask")
    public PageTip<SsComTaskDTO> getFinshedCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = iSsComTaskService.queryNoProgressCompanyTask(pageInfo);
        return TipKit.ofPage(pageRows);
    }

    @Log("查询批退企业任务单")
    @RequestMapping(value = "getRefusedTask")
    public PageTip<SsComTaskDTO> getRefusedCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = iSsComTaskService.queryNoProgressCompanyTask(pageInfo);
        return TipKit.ofPage(pageRows);
    }


}

