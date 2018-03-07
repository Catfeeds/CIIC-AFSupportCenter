package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComAccountPaymentWayBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskEndTypeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskTaskStatusBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;

import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Log("查询企业任务单列表")
    @PostMapping("/postComTasks")
    public JsonResult<List<HfComTaskBo>> postComTasks(PageInfo pageInfo) {

        PageRows<HfComTaskBo> pageRows = hfComTaskService.queryCompanyTasks(pageInfo);

        return JsonResultKit.ofPage(pageRows);
    }

    /**
     * 导出企业任务单
     */
    @Log("导出企业任务单")
    @RequestMapping("/exportCompanyTasks")
    public void exportCompanyTasks(HttpServletResponse response, PageInfo pageInfo) {
        Date date = new Date();
        String fileNme = "企业任务单_" + StringUtil.getDateString(date) + ".xls";
        HfComTaskBo hfComTaskBo = pageInfo.toJavaObject(HfComTaskBo.class);
        List<HfComTaskBo> hfComTaskBos = hfComTaskService.getCompanyTasks(hfComTaskBo);
        ExcelUtil.exportExcel(hfComTaskBos, HfComTaskBo.class, fileNme, response);
    }

    @Log("更新未处理企业任务单")
    @PostMapping("/updateCompanyTask")
    public JsonResult<Boolean> updateCompanyTask(@RequestParam Map<String, String> map) {
        boolean result = hfComTaskService.upsertCompanyTaskRelated(map);
        return JsonResultKit.of(result);
    }

    @Log("更新未处理企业任务单（变更）")
    @PostMapping("/updateCompanyTaskChangeInfo")
    public JsonResult<Boolean> updateCompanyTaskChangeInfo(@RequestParam Map<String, String> map) {
        boolean result = hfComTaskService.upsertCompanyTask(map);
        return JsonResultKit.of(result);
    }

    @Log("更新未处理企业任务单（终止）")
    @PostMapping("/updateCompanyTaskEndInfo")
    public JsonResult<Boolean> updateCompanyTaskEndInfo(@RequestParam Map<String, String> map) {
        boolean result = hfComTaskService.upsertCompanyTask(map);
        return JsonResultKit.of(result);
    }

    @Log("查询企业任务单支付方式数据")
    @GetMapping("/getCompanyTaskPaymentWayData")
    public JsonResult<List<HfComAccountPaymentWayBo>> queryComTaskPaymentWayData() {
        List<HfComAccountPaymentWayBo> result = hfComTaskService.queryComTaskPaymentWayData();
        return JsonResultKit.of(result);
    }

    @Log("查询企业任务单任务状态数据")
    @GetMapping("/getCompanyTaskTaskStatusData")
    public JsonResult<List<HfComTaskTaskStatusBo>> queryComTaskTaskStatusData() {
        List<HfComTaskTaskStatusBo> result = hfComTaskService.queryComTaskTaskStatusData();
        return JsonResultKit.of(result);
    }

    @Log("查询企业任务单终止类型数据")
    @GetMapping("/getCompanyTaskEndTypeData")
    public JsonResult<List<HfComTaskEndTypeBo>> queryComTaskEndTypeData() {
        List<HfComTaskEndTypeBo> result = hfComTaskService.queryComTaskEndTypeData();
        return JsonResultKit.of(result);
    }

}
