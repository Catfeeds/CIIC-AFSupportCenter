package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAnnualAdjustAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAnnualAdjustAccountEmpBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustAccountEmpService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustAccountEmpTempService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustAccountEmpTemp;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/soccommandservice/ssAnnualAdjustAccountEmp")
public class SsAnnualAdjustAccountEmpController extends BasicController<SsAnnualAdjustAccountEmpService> {

    @Autowired
    SsAnnualAdjustAccountService ssAnnualAdjustAccountService;
    @Autowired
    SsAnnualAdjustAccountEmpTempService ssAnnualAdjustAccountEmpTempService;

    @RequestMapping("/accountUnitAvgMonthSalaryQuery")
    public JsonResult<List> accountUnitAvgMonthSalaryQuery(PageInfo pageInfo) {
        SsAnnualAdjustAccountDTO ssAnnualAdjustAccountDTO = pageInfo.toJavaObject(SsAnnualAdjustAccountDTO.class);
        Long annualAdjustAccountId = ssAnnualAdjustAccountDTO.getAnnualAdjustAccountId();
        List<SsAnnualAdjustAccountBO> list = ssAnnualAdjustAccountService.getUnitAvgMonthSalaryByAnnualAdjustAccountId(annualAdjustAccountId);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 1) {
                return JsonResultKit.ofError("存在不同的单位平均工资信息，数据不正确");
            } else {
                return JsonResultKit.of(list);
            }
        }
        return JsonResultKit.of();
    }

    @RequestMapping("/annualAdjustAccountEmpTempQuery")
    public JsonResult<PageRows> annualAdjustAccountEmpTempQuery(PageInfo pageInfo) {
        SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO = pageInfo.toJavaObject(SsAnnualAdjustAccountEmpTempDTO.class);
        EntityWrapper<SsAnnualAdjustAccountEmpTemp> condition = new EntityWrapper<>();
        condition.where("annual_adjust_account_id={0}", ssAnnualAdjustAccountEmpTempDTO.getAnnualAdjustAccountId());
        condition.orderBy("order_num", true);
        PageRows<SsAnnualAdjustAccountEmpTemp> result = PageKit.doSelectPage(pageInfo, () -> ssAnnualAdjustAccountEmpTempService.selectList(condition));
        return JsonResultKit.of(result);
    }

    @RequestMapping("/annualAdjustAccountEmpInsert")
    public JsonResult annualAdjustAccountEmpInsert(PageInfo pageInfo) {
        SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO = pageInfo.toJavaObject(SsAnnualAdjustAccountEmpTempDTO.class);
        ssAnnualAdjustAccountEmpTempDTO.setCreatedBy(UserContext.getUserId());
        business.insertDataWithoutErrorMsg(ssAnnualAdjustAccountEmpTempDTO);
        return JsonResultKit.of();
    }

    /**
     * 分页查询年调社保账户雇员信息
     * @param pageInfo
     * @return
     */
    @RequestMapping("/annualAdjustAccountEmpQuery")
    public JsonResult<PageRows> annualAdjustAccountEmpQuery(PageInfo pageInfo) {
        PageRows<SsAnnualAdjustAccountEmpBO> result = business.queryAnnualAdjustAccountEmpInPage(pageInfo);
        return JsonResultKit.of(result);
    }

    /**
     * 导出年调社保账户雇员信息
     * @param
     * @return
     */
    @RequestMapping("/annualAdjustAccountEmpExport")
    public void annualAdjustAccountEmpExport(HttpServletResponse response, PageInfo pageInfo) {
        try {
            pageInfo.setPageSize(10000);
            pageInfo.setPageNum(0);
            PageRows<SsAnnualAdjustAccountEmpBO> result = business.queryAnnualAdjustAccountEmpInPage(pageInfo);
            long total = result.getTotal();
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);
            exportParams.setSheetName("社保月平均工资申报表匹配结果");
            Workbook workbook;

            if (total <= pageInfo.getPageSize()) {
                workbook = ExcelExportUtil.exportExcel(exportParams, SsAnnualAdjustAccountEmpBO.class, result.getRows());
            } else {
                workbook = ExcelExportUtil.exportBigExcel(exportParams, SsAnnualAdjustAccountEmpBO.class, result.getRows());
                int pageNum = (int) Math.ceil(total / pageInfo.getPageSize());
                for(int i = 1; i < pageNum; i++) {
                    pageInfo.setPageNum(i);
                    result = business.queryAnnualAdjustAccountEmpInPage(pageInfo);
                    workbook = ExcelExportUtil.exportBigExcel(exportParams, SsAnnualAdjustAccountEmpBO.class, result.getRows());
                }
                ExcelExportUtil.closeExportBigExcel();
            }

            response.reset();
            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("社保月平均工资申报表匹配结果.xlsx", "UTF-8"));
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

