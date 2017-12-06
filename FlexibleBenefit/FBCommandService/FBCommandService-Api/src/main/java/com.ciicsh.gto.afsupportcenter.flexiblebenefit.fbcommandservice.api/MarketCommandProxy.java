package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.JsonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("afsupportcenter-center-command-service")
@RequestMapping("/api/market")
public interface MarketCommandProxy {
    /**
     * 根据主键查询礼品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public JsonResult findById(@PathVariable Integer id);
}
