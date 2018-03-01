package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.translator.ApiTranslator;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SsComProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountParamDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ComAccountExtPO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by houwanhua on 2018/2/26.
 */
@RestController
@RequestMapping("/api/soc/comaccount")
public class ComAccountController implements SsComProxy {

    @Autowired
    private SsComAccountService accountService;

    @Override
    @RequestMapping("/getAccountList")
    @Log("获取企业社保账户信息表")
    public JsonResult<List<SsComAccountDTO>> getSsComAccountList(@RequestBody SsComAccountParamDTO paramDto) {

        ComAccountParamBO paramBO = new ComAccountParamBO();
        BeanUtils.copyProperties(paramDto,paramBO);
        // 根据 客户ID和账户类型查询
        List<ComAccountExtPO> ssComAccountList = accountService.getSsComAccountList(paramBO);
        List<SsComAccountDTO> accountDTOS = new ArrayList<>();
        if(null != ssComAccountList && ssComAccountList.size() > 0){
            accountDTOS = ssComAccountList.stream().map(ApiTranslator::toComAccountDTO).collect(Collectors.toList());
        }
        return JsonResult.success(accountDTOS);
    }
}
