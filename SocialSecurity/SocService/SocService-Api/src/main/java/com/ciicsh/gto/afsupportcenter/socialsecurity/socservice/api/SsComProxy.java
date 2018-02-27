package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountParamDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企业社保账户信息查询接口
 */
@FeignClient("support-center-soc-api-service")
@RequestMapping("/api/comaccount")
public interface SsComProxy {

    /**
     * 获取企业社保账户信息表
     *
     * @param paramDto
     * @return
     */
    @RequestMapping("/getAccountList")
    JsonResult getSsComAccountList(@RequestBody SsComAccountParamDTO paramDto);

}
