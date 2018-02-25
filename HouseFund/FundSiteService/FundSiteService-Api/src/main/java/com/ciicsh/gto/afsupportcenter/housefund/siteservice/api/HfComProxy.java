package com.ciicsh.gto.afsupportcenter.housefund.siteservice.api;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountParamDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企业公积金账户信息查询接口
 */
@FeignClient("support-center-housefund-site-service")
@RequestMapping("/ai/soccommandservice/hfApi")
public interface HfComProxy {

    /**
     * 获取企业公积金账户信息表
     *
     * @param paramDto
     * @return
     */
    @RequestMapping("/getHfComAccountList")
    com.ciicsh.common.entity.JsonResult getHfComAccountList(@RequestBody HfComAccountParamDto paramDto);

}
