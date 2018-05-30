package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustEmployeeService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustEmployee;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.ExportResponseUtil;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * <p>
 *  社保雇员年调表信息展示
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/soccommandservice/ssAnnualAdjustEmployee")
public class SsAnnualAdjustEmployeeController extends BasicController<SsAnnualAdjustEmployeeService> {

    /**
     * 社保雇员年调表分页查询
     * @param
     * @return
     */
    @RequestMapping("/annualAdjustEmployeeQuery")
    public JsonResult<PageRows> annualAdjustEmployeeQuery(PageInfo pageInfo) {
        PageRows<SsAnnualAdjustEmployee> result = business.queryAnnualAdjustEmployeeInPage(pageInfo);
        return JsonResultKit.of(result);
    }

    /**
     * 社保雇员年调表导出
     * @param
     * @return
     */
    @RequestMapping("/annualAdjustEmployeeExport")
    public void annualAdjustEmployeeExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        pageInfo.setPageSize(10000);
        pageInfo.setPageNum(1);
        PageRows<SsAnnualAdjustEmployee> result = business.queryAnnualAdjustEmployeeInPage(pageInfo);
        long total = result.getTotal();
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        exportParams.setSheetName("雇员工资数据采集");
        Workbook workbook;

        if (total <= pageInfo.getPageSize()) {
            workbook = ExcelExportUtil.exportExcel(exportParams, SsAnnualAdjustEmployee.class, result.getRows());
        } else {
            workbook = ExcelExportUtil.exportBigExcel(exportParams, SsAnnualAdjustEmployee.class, result.getRows());
            int pageNum = (int) Math.ceil(total * 1.0 / pageInfo.getPageSize());
            for(int i = 2; i <= pageNum; i++) {
                pageInfo.setPageNum(i);
                result = business.queryAnnualAdjustEmployeeInPage(pageInfo);
                workbook = ExcelExportUtil.exportBigExcel(exportParams, SsAnnualAdjustEmployee.class, result.getRows());
            }
            ExcelExportUtil.closeExportBigExcel();
        }
        SsAnnualAdjustEmployeeDTO ssAnnualAdjustEmployeeDTO = pageInfo.toJavaObject(SsAnnualAdjustEmployeeDTO.class);
        Calendar calendar = Calendar.getInstance();
        int adjustYear = calendar.get(Calendar.YEAR);
        String fileName = "系统申报表_companyId_adjustYear.xlsx"
            .replace("companyId", ssAnnualAdjustEmployeeDTO.getCompanyId())
            .replace("adjustYear", String.valueOf(adjustYear));

        response.reset();
        response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition",
//                "attachment;filename=" + fileName);
        ExportResponseUtil.encodeExportFileName(response, fileName);
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}

