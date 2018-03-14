package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFMonthChargeReportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


@RestController
@RequestMapping("/api/fundcommandservice/hfMonthCharge")
public class HfMonthChargeController extends BasicController<HfMonthChargeService> {

    @RequestMapping("/hfMonthChargeQuery")
    public JsonResult<PageRows<HFMonthChargeReportBO>> hfMonthChargeQuery(PageInfo pageInfo) {
        PageRows<HFMonthChargeReportBO> result = business.queryHfMonthChargeReport(pageInfo);
        return JsonResultKit.of(result);
    }

    /**
     * 雇员公积金任务导出
     *
     * @param
     * @return
     */
    @RequestMapping("/hfMonthChargeExport")
    @Log("雇员公积金报表导出")
    public void hfMonthChargeExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        pageInfo.setPageSize(10000);
        pageInfo.setPageNum(0);
        PageRows<HFMonthChargeReportBO> result = business.queryHfMonthChargeReport(pageInfo);
        long total = result.getTotal();
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        exportParams.setSheetName("雇员公积金报表");
        Workbook workbook;

        if (total <= pageInfo.getPageSize()) {
            workbook = ExcelExportUtil.exportExcel(exportParams, HFMonthChargeReportBO.class, result.getRows());
        } else {
            workbook = ExcelExportUtil.exportBigExcel(exportParams, HFMonthChargeReportBO.class, result.getRows());
            int pageNum = (int) Math.ceil(total / pageInfo.getPageSize());
            for(int i = 1; i < pageNum; i++) {
                pageInfo.setPageNum(i);
                result = business.queryHfMonthChargeReport(pageInfo);
                workbook = ExcelExportUtil.exportBigExcel(exportParams, HfEmpTaskExportBo.class, result.getRows());
            }
            ExcelExportUtil.closeExportBigExcel();
        }
        String fileName = URLEncoder.encode("雇员公积金报表.xlsx", "UTF-8");

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
            "attachment;filename=" + fileName);
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
