package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 企业社保账户信息表
企业社保账户分类 : 大库（中智大库、外包库）、独立户 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssComAccount")
public class SsComAccountController  extends BasicController<ISsComAccountService> {
    @Resource
    ISsComAccountService ISsComAccountService;
    @RequestMapping(value = "getList")
    public JsonResult<List<SsComAccountDTO>>   queryComAccountList(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComAccountDTO> pageRows = ISsComAccountService.queryComAccount(pageInfo);
        return JsonResultKit.ofPage(pageRows);

    }
}

