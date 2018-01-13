package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiweizhen
 */
@FeignClient("afsupportcenter-center-query-service")
@RequestMapping("/api/market")
public interface MarketQueryProxy {
    /**
     * 根据主键查询礼品信息
     *
     * @param activityName
     * @param publish
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/findMarketList")
    Result findMarketList(String activityName, String publish, Byte status, Integer pageNum, Integer pageSize);
}
