package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAnnualAdjustEmployeeService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustEmployee;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  社保雇员年调表信息展示
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/soccommandservice/ssAnnualAdjustEmployee")
public class SsAnnualAdjustEmployeeController extends BasicController<ISsAnnualAdjustEmployeeService> {
    /**
     * 社保雇员年调表分页查询
     *
     * @param
     * @return
     */
    @RequestMapping("/annualAdjustEmployeeQuery")
    @Log("查询社保雇员年调表信息")
    public JsonResult<PageRows> annualAdjustEmployeeQuery(PageInfo pageInfo) {
        PageRows<SsAnnualAdjustEmployee> result = business.queryAnnualAdjustEmployeeInPage(pageInfo);
        return JsonResultKit.of(result);
    }

    /**
     * 社保雇员年调表导出
     *
     * @param
     * @return
     */
    @RequestMapping("/annualAdjustEmployeeExport")
    @Log("导出社保雇员年调表信息")
    public void annualAdjustEmployeeExport(HttpServletResponse response, PageInfo pageInfo) {
        try {
            pageInfo.setPageSize(10000);
            pageInfo.setPageNum(0);
            PageRows<SsAnnualAdjustEmployee> result = business.queryAnnualAdjustEmployeeInPage(pageInfo);
            long total = result.getTotal();
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);
            exportParams.setSheetName("雇员工资数据采集");
            Workbook workbook = null;

            if (total <= pageInfo.getPageSize()) {
                workbook = ExcelExportUtil.exportExcel(exportParams, SsAnnualAdjustEmployee.class, result.getRows());
            } else {
                workbook = ExcelExportUtil.exportBigExcel(exportParams, SsAnnualAdjustEmployee.class, result.getRows());
                int pageNum = (int) Math.ceil(total / pageInfo.getPageSize());
                for(int i = 1; i < pageNum; i++) {
                    pageInfo.setPageNum(i);
                    result = business.queryAnnualAdjustEmployeeInPage(pageInfo);
                    workbook = ExcelExportUtil.exportBigExcel(exportParams, SsAnnualAdjustEmployee.class, result.getRows());
                }
                ExcelExportUtil.closeExportBigExcel();
            }

            response.reset();
            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("系统申报表.xlsx", "UTF-8"));
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<SsAnnualAdjustEmployee> getLimitList(PageInfo pageInfo) {
        PageRows<SsAnnualAdjustEmployee> result = business.queryAnnualAdjustEmployeeInPage(pageInfo);
        return result.getRows();
    }
}

