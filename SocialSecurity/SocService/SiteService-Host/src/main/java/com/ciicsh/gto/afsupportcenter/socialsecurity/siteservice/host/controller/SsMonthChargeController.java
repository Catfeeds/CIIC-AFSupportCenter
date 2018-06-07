package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsYysReportBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsYysReportParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthChargeService;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/soccommandservice/ssMonthCharge")
public class SsMonthChargeController extends BasicController<SsMonthChargeService> {

    @RequestMapping("/queryYysReport")
    public JsonResult<List<SsYysReportBO>> queryYysReport(SsYysReportParamBO ssYysReportParamBO) {
        List<SsYysReportBO> ssYysReportBOList = business.queryYysReport(ssYysReportParamBO);
        return JsonResultKit.of(ssYysReportBOList);
    }

    /**
     * 变更总汇明细(养保、医保、失保)导出
     * @param response
     * @param pageInfo
     */
    @RequestMapping("/yysExport")
    public void yysExport(HttpServletResponse response, PageInfo pageInfo){
        Date date = new Date();
        String fileNme = "YYS_" + StringUtil.getDateString(date) + ".xls";
        SsYysReportParamBO ssYysReportParamBO = pageInfo.toJavaObject(SsYysReportParamBO.class);
        List<SsYysReportBO> ssYysReportBOList = business.queryYysReport(ssYysReportParamBO);
        ExcelUtil.exportExcel(ssYysReportBOList, SsYysReportBO.class, fileNme, response);
    }
}
