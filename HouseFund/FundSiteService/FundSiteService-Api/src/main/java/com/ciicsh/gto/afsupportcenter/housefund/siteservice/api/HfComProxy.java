package com.ciicsh.gto.afsupportcenter.housefund.siteservice.api;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountParamDto;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.ResultDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    ResultDTO<List<HfComAccountDTO>> getHfComAccountList(@RequestBody HfComAccountParamDto paramDto);

}
