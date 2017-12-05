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
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/api/soccommandservice/ssComTask")
public class SsComTaskController extends BasicController<ISsComTaskService> {
    @Resource
    ISsComTaskService iSsComTaskService;

    @Log("查询企业任务单")
    @RequestMapping(value = "getTask")
    public String getCompanyTask(String pageSize, String currentPage, String stakStatus) {
        try {
            if (StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage) && StringUtils.isNotBlank(stakStatus)) {
                //mybatis 查询封装的对象
                EntityWrapper<SsComTask> entity = new EntityWrapper<SsComTask>();
                //查询 可用 并且 为未处理的 分页结果
                List list = iSsComTaskService.selectPage(new Page<SsComTask>(Integer.parseInt(currentPage), Integer.parseInt(pageSize)), entity.
                    eq("is_active", true).eq("task_status", Integer.parseInt(stakStatus))
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
            } else return JsonKit.toStr(TipKit.ofError(""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return JsonKit.toStr(TipKit.ofError(e.getMessage()));
        }
    }


}

