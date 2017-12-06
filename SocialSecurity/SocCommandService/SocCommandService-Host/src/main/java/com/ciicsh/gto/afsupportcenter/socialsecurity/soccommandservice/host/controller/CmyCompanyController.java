package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ICmyCompanyService;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 客户基础表 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2017-12-06
 */
@Controller
@RequestMapping("/api/soccommandservice/cmyCompany")
public class CmyCompanyController extends BasicController<ICmyCompanyService> {

    @Log("获得客户名称")
    @RequestMapping(value = "getCompanyName")
    public void getCompanyName(PageInfo pageInfo){
       // business.selectPage()

    }
}

