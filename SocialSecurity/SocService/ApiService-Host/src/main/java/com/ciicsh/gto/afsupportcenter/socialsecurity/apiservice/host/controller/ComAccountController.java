package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SsComProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountParamDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by houwanhua on 2018/2/26.
 */
@RestController
public class ComAccountController implements SsComProxy {

    @Autowired
    private SsComAccountService accountService;

    @Override
    @RequestMapping("/getSsComAccountList")
    @Log("获取企业社保账户信息表")
    public JsonResult getSsComAccountList(@RequestBody SsComAccountParamDTO paramDto) {
        // 根据 客户ID和账户类型查询
        List<SsComAccountDTO> ssComAccountList =
            accountService.getSsComAccountList(paramDto);
        return JsonResult.success(ssComAccountList);
    }
}
