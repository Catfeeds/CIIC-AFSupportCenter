package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFMonthChargeQueryBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFMonthChargeReportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountReportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.util.PdfUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/fundcommandservice/hfMonthCharge")
public class HfMonthChargeController extends BasicController<HfMonthChargeService> {

    @RequestMapping("/hfMonthChargeQuery")
    public JsonResult<PageRows<HFMonthChargeReportBO>> hfMonthChargeQuery(PageInfo pageInfo) {
        PageRows<HFMonthChargeReportBO> result = business.queryHfMonthChargeReport(pageInfo, UserContext.getUserId());
        return JsonResultKit.of(result);
    }

    /**
     * 雇员公积金任务导出
     * @param
     * @return
     */
    @RequestMapping("/hfMonthChargeExport")
    public void hfMonthChargeExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        pageInfo.setPageSize(10000);
        pageInfo.setPageNum(1);
        PageRows<HFMonthChargeReportBO> result = business.queryHfMonthChargeReport(pageInfo, UserContext.getUserId());
        long total = result.getTotal();
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        exportParams.setSheetName("雇员公积金报表");
        Workbook workbook;

        if (total <= pageInfo.getPageSize()) {
            workbook = ExcelExportUtil.exportExcel(exportParams, HFMonthChargeReportBO.class, result.getRows());
        } else {
            workbook = ExcelExportUtil.exportBigExcel(exportParams, HFMonthChargeReportBO.class, result.getRows());
            int pageNum = (int) Math.ceil(total * 1.0 / pageInfo.getPageSize());
            for(int i = 2; i <= pageNum; i++) {
                pageInfo.setPageNum(i);
                result = business.queryHfMonthChargeReport(pageInfo, UserContext.getUserId());
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

    /**
     * 公积金变更清册导出
     * @param response
     * @param pageInfo
     * @throws Exception
     */
    @RequestMapping("/chgDetailListExport")
    public void chgDetailListExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        try {
            HFMonthChargeQueryBO hfMonthChargeQueryBO = pageInfo.toJavaObject(HFMonthChargeQueryBO.class);
            String hfTypeName;
            String templateFilePath;

            hfMonthChargeQueryBO.setUserId(UserContext.getUserId());
            List<Map<String, Object>> chgDetailsPageList = business.getChgDetailsPageList(hfMonthChargeQueryBO, true);

            if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                hfTypeName = "基本";
                templateFilePath = "/template/SH_BAS_HF_CHG_DTL_TMP.pdf";
            } else {
                hfTypeName = "补充";
                templateFilePath = "/template/SH_ADD_HF_CHG_DTL_TMP.pdf";
            }
            String fileName = URLEncoder.encode(String.format("上海市%1$s公积金汇缴变更清册_%2$s.pdf", hfTypeName, hfMonthChargeQueryBO.getHfMonth()), "UTF-8");
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/pdf");
            response.setHeader("Content-Disposition",
                "attachment;filename=" + fileName);

            PdfUtil.createPdfByTemplate(templateFilePath,
                PdfUtil.DEFAULT_FONT_NAME,
                PdfUtil.DEFAULT_FONT_ENCODING,
                false,
                false,
                chgDetailsPageList,
                response.getOutputStream());
        } catch (Exception e) {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "text/plain");
            response.getWriter().write(e.getMessage());
        }
    }

    /**
     * 公积金补缴清册导出
     *
     * @param response
     * @param pageInfo
     * @throws Exception
     */
    @RequestMapping("/repairDetailListExport")
    public void repairDetailListExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        HFMonthChargeQueryBO hfMonthChargeQueryBO = pageInfo.toJavaObject(HFMonthChargeQueryBO.class);
        String hfTypeName;
        String templateFilePath;

        try {
            hfMonthChargeQueryBO.setUserId(UserContext.getUserId());
            List<Map<String, Object>> repairDetailsPageList = business.getRepairDetailsPageList(hfMonthChargeQueryBO, true);

            if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                hfTypeName = "基本";
                templateFilePath = "/template/SH_BAS_HF_REP_DTL_TMP.pdf";
            } else {
                hfTypeName = "补充";
                templateFilePath = "/template/SH_ADD_HF_REP_DTL_TMP.pdf";
            }
            String fileName = URLEncoder.encode(String.format("上海市%1$s住房公积金补缴清册_%2$s.pdf", hfTypeName, hfMonthChargeQueryBO.getHfMonth()), "UTF-8");
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/pdf");
            response.setHeader("Content-Disposition",
                "attachment;filename=" + fileName);

            PdfUtil.createPdfByTemplate(templateFilePath,
                PdfUtil.DEFAULT_FONT_NAME,
                PdfUtil.DEFAULT_FONT_ENCODING,
                false,
                false,
                repairDetailsPageList,
                response.getOutputStream());
        } catch (Exception e) {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "text/plain");
            response.getWriter().write(e.getMessage());
        }
    }

    /**
     * 导出公积金汇缴支付详情Excel报表
     * @param pageInfo 导出条件
     */
    @RequestMapping("/operateDetailExcelExport")
    public void operateDetailExcelExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        PageRows<HfPaymentAccountReportBo> result = business.getOperateDetailReport(pageInfo, UserContext.getUserId());
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        exportParams.setSheetName("公积金汇缴支付明细");
        Workbook workbook = workbook = ExcelExportUtil.exportExcel(exportParams, HfPaymentAccountReportBo.class, result.getRows());
        String fileName = URLEncoder.encode("公积金汇缴支付明细-" + System.currentTimeMillis() + ".xlsx", "UTF-8");
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
            "attachment;filename=" + fileName);
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
