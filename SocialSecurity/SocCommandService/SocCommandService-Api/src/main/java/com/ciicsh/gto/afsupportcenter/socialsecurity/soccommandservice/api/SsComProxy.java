package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsComAccountParamDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企业社保账户信息查询接口
 */
@FeignClient("support-center-soc-command-service")
@RequestMapping("/api/soccommandservice/ssComAccount")
public interface SsComProxy {

    /**
     * 获取企业社保账户信息表
     *
     * @param paramDto
     * @return
     */
    @RequestMapping("/getSsComAccountList")
    com.ciicsh.common.entity.JsonResult getSsComAccountList(@RequestBody SsComAccountParamDTO paramDto);

}
