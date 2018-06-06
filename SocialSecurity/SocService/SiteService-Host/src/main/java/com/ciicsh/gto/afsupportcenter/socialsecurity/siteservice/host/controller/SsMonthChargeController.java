package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsYysReportBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsYysReportParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthChargeService;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/soccommandservice/ssMonthCharge")
public class SsMonthChargeController extends BasicController<SsMonthChargeService> {

    @RequestMapping("/queryYysReport")
    public JsonResult<List<SsYysReportBO>> queryYysReport(SsYysReportParamBO ssYysReportParamBO) {
        List<SsYysReportBO> ssYysReportBOList = business.queryYysReport(ssYysReportParamBO);
        return JsonResultKit.of(ssYysReportBOList);
    }
}
