package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
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
 * 企业社保账户信息表
 * 企业社保账户分类 : 大库（中智大库、外包库）、独立户 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssComAccount")
@Log("企业社保账户信息")
public class SsComAccountController extends BasicController<ISsComAccountService> {

    /**
     * 根据雇员任务 ID 查询 企业社保账户信息
     *
     * @param empTaskId
     * @return
     */
    @RequestMapping("/queryByEmpTaskId")
    @Log("根据雇员任务 ID 查询")
    public JsonResult<SsComAccountDTO> queryByEmpTaskId(String empTaskId) {
        SsComAccountDTO dto = business.queryByEmpTaskId(empTaskId);
        return JsonResultKit.of(dto);
    }

    /**
     * 查询企业社保账户信息
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/accountQuery")
    @Log("查询")
    public JsonResult<List<SsComAccount>> accountQuery(PageInfo pageInfo) {
        PageRows<SsComAccount> pageRows = business.accountQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }
}

