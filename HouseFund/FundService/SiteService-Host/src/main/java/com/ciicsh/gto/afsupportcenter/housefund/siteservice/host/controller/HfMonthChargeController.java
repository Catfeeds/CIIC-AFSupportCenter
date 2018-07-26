package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.util.PdfUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.ExportResponseUtil;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/fundcommandservice/hfMonthCharge")
public class HfMonthChargeController extends BasicController<HfMonthChargeService> {
    /**
     * 实时查询客户汇缴书
     * @param pageInfo
     * @return
     */
    @RequestMapping("/queryHfRimittedBookReport")
    public JsonResult<PageRows<HfRimittedBookReportBO>> queryHfRimittedBookReport(PageInfo pageInfo) {
        PageRows<HfRimittedBookReportBO> result = business.queryHfRimittedBookReport(pageInfo, UserContext.getUserId());
        return JsonResultKit.of(result);
    }

    /**
     * 导出实时查询客户汇缴书
     * @param response
     * @param pageInfo
     * @throws Exception
     */
    @RequestMapping("/queryHfRimittedBookReportExport")
    public void queryHfRimittedBookReportExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        pageInfo.setPageSize(10000);
        pageInfo.setPageNum(1);
        PageRows<HfRimittedBookReportBO> result = business.queryHfRimittedBookReport(pageInfo, UserContext.getUserId());
        long total = result.getTotal();
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        exportParams.setSheetName("企业账户汇缴书");
        Workbook workbook;
        if (total <= pageInfo.getPageSize()) {
            workbook = ExcelExportUtil.exportExcel(exportParams, HfRimittedBookReportBO.class, result.getRows());
        } else {
            workbook = ExcelExportUtil.exportBigExcel(exportParams, HfRimittedBookReportBO.class, result.getRows());
            int pageNum = (int) Math.ceil(total * 1.0 / pageInfo.getPageSize());
            for (int i = 2; i <= pageNum; i++) {
                pageInfo.setPageNum(i);
                result = business.queryHfRimittedBookReport(pageInfo, UserContext.getUserId());
                workbook = ExcelExportUtil.exportBigExcel(exportParams, HfRimittedBookReportBO.class, result.getRows());
            }
            ExcelExportUtil.closeExportBigExcel();
        }
        String fileName = "企业公积金账户汇缴书清单.xlsx";
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        ExportResponseUtil.encodeExportFileName(response, fileName);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 打印汇缴书
     */
    @PostMapping("/printRemittedBook")
    public JsonResult printRemittedBook(@RequestParam(value = "comAccountClassId", required = true) Long comAccountClassId,
                                        @RequestParam(value = "paymentMonth", required = true) String paymentMonth ){
        return business.printRemittedBook(comAccountClassId,paymentMonth);
    }

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
            for (int i = 2; i <= pageNum; i++) {
                pageInfo.setPageNum(i);
                result = business.queryHfMonthChargeReport(pageInfo, UserContext.getUserId());
                workbook = ExcelExportUtil.exportBigExcel(exportParams, HfEmpTaskExportBo.class, result.getRows());
            }
            ExcelExportUtil.closeExportBigExcel();
        }
        String fileName = "雇员公积金报表.xlsx";

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("Content-Disposition",
//                "attachment;filename=" + fileName);
        ExportResponseUtil.encodeExportFileName(response, fileName);
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
            String fileName = String.format("上海市%1$s公积金汇缴变更清册_%2$s.pdf", hfTypeName, hfMonthChargeQueryBO.getHfMonth());
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/pdf");
//            response.setHeader("Content-Disposition",
//                "attachment;filename=" + fileName);
            ExportResponseUtil.encodeExportFileName(response, fileName);

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
            response.setHeader("content-Type", "text/html");
            response.getWriter().write(e.getMessage() + "<a href=\"javascript:history.go(-1)\">返回</a>");
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
            String fileName = String.format("上海市%1$s住房公积金补缴清册_%2$s.pdf", hfTypeName, hfMonthChargeQueryBO.getHfMonth());
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/pdf");
//            response.setHeader("Content-Disposition",
//                "attachment;filename=" + fileName);
            ExportResponseUtil.encodeExportFileName(response, fileName);

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
            response.setHeader("content-Type", "text/html");
            response.getWriter().write(e.getMessage() + "<a href=\"javascript:history.go(-1)\">返回</a>");
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
        String fileName = "公积金汇缴支付明细-" + System.currentTimeMillis() + ".xlsx";
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition",
//            "attachment;filename=" + fileName);
        ExportResponseUtil.encodeExportFileName(response, fileName);
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
