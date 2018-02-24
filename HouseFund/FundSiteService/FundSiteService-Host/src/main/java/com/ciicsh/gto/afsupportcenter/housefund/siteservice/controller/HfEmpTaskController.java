package com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskBatchRejectBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/fundcommandservice/hfEmpTask")
public class HfEmpTaskController extends BasicController<HfEmpTaskService> {

    /**
     * 雇员公积金任务查询
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/hfEmpTaskQuery")
    @Log("雇员公积金任务查询")
    public JsonResult<PageRows> hfEmpTaskQuery(@RequestBody PageInfo pageInfo) {
        return JsonResultKit.of(business.queryHfEmpTaskInPage(pageInfo, StringUtils.join(
            new Integer[] {
                HfEmpTaskConstant.TASK_CATEGORY_TRANS_TASK,
                HfEmpTaskConstant.TASK_CATEGORY_SPEC_TASK
            }, ',')));
    }

    /**
     * 雇员公积金任务导出
     *
     * @param
     * @return
     */
    @RequestMapping("/hfEmpTaskExport")
    @Log("雇员公积金任务导出")
    public void hfEmpTaskExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        pageInfo.setPageSize(10000);
        pageInfo.setPageNum(0);
        PageRows<HfEmpTaskBo> result = business.queryHfEmpTaskInPage(pageInfo);
        long total = result.getTotal();
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        exportParams.setSheetName("雇员公积金任务信息");
        Workbook workbook;

        if (total <= pageInfo.getPageSize()) {
            workbook = ExcelExportUtil.exportExcel(exportParams, HfEmpTaskBo.class, result.getRows());
        } else {
            workbook = ExcelExportUtil.exportBigExcel(exportParams, HfEmpTaskBo.class, result.getRows());
            int pageNum = (int) Math.ceil(total / pageInfo.getPageSize());
            for(int i = 1; i < pageNum; i++) {
                pageInfo.setPageNum(i);
                result = business.queryHfEmpTaskInPage(pageInfo);
                workbook = ExcelExportUtil.exportBigExcel(exportParams, HfEmpTaskBo.class, result.getRows());
            }
            ExcelExportUtil.closeExportBigExcel();
        }
        String fileName = URLEncoder.encode("雇员公积金任务信息.xlsx", "UTF-8");

        response.reset();
        response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
            "attachment;filename=" + fileName);
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    @RequestMapping("/hfEmpTaskBatchReject")
    @Log("雇员公积金任务批退")
    public JsonResult hfEmpTaskBatchReject(@RequestBody HfEmpTaskBatchRejectBo hfEmpTaskBatchRejectBo) {
        Long[] selectedData = hfEmpTaskBatchRejectBo.getSelectedData();
        if (!ArrayUtils.isEmpty(selectedData)) {
            List<HfEmpTask> list = new ArrayList<>();
            for (Long empTaskId : selectedData) {
                HfEmpTask hfEmpTask = new HfEmpTask();
                hfEmpTask.setEmpTaskId(empTaskId);
                hfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_REJECTED);
                hfEmpTask.setRejectionRemark(hfEmpTaskBatchRejectBo.getRejectionRemark());
                hfEmpTask.setModifiedTime(LocalDateTime.now());
                hfEmpTask.setModifiedBy("test"); // TODO current user
                list.add(hfEmpTask);
            }

            if (!business.updateBatchById(list)) {
                return JsonResultKit.ofError("数据库批量更新失败");
            }
        }

        return JsonResultKit.of();
    }
}
