package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.JsonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("afsupportcenter-center-query-service")
@RequestMapping("/api/market")
public interface MarketCommandProxy {
    /**
     * 根据主键查询礼品信息
     *
     * @param
     * @return
     */
    @PostMapping("/findMarketList")
    JsonResult findMarketList(String activityName, String publish, Byte status, Integer pageNum, Integer pageSize);
}
