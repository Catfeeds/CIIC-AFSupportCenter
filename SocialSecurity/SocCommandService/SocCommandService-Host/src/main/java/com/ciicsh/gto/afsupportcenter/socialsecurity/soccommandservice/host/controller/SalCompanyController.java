package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISalCompanyService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SalCompany;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户基础表 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@RestController
@RequestMapping("/api/soccommandservice/salCompany")
@Log("客户基础信息")
public class SalCompanyController extends BasicController<ISalCompanyService> {

    /**
     * 查询客户基础信息
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/companyQuery")
    @Log("查询")
    public JsonResult<List<SalCompany>> companyQuery(PageInfo pageInfo) {
        PageRows<SalCompany> pageRows = business.companyQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }
}

