package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.tips.PageTip;
import com.ciicsh.gto.afsupportcenter.util.tips.TipKit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 独立库客户任务单 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Controller
@RequestMapping("/soccommandservice/ssComTask")
public class SsComTaskController {
    @Resource
    ISsComTaskService iSsComTaskService;

    @Log("查询企业任务单")
    @ResponseBody
    @RequestMapping(value = "getTask")
    public String getCompanyTask(int pageSize, int currentPage, int stakStatus) {
        try {
            //mybatis 查询封装的对象
            EntityWrapper<SsComTask> entity = new EntityWrapper<SsComTask>();
            //查询 可用 并且 为未处理的 分页结果
            List list = iSsComTaskService.selectPage(new Page<SsComTask>(currentPage, pageSize), entity.eq("is_active", true).eq("task_status", stakStatus)
            ).getRecords();
            //查询总是
            int totalSize = iSsComTaskService.selectCount(entity);
            //封装返回的结果
            PageRows<SsComTask> pageRows = new PageRows<SsComTask>();
            pageRows.setRows(list);
            pageRows.setTotal(totalSize);
            PageTip pageTip = TipKit.ofPage(pageRows);
            String result = JsonKit.toStr(pageTip);
            return result;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return  JsonKit.toStr(TipKit.ofError(e.getMessage()));
        }
    }


}

