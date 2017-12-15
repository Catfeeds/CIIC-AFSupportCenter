package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.JsonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("afsupportcenter-center-query-service")
@RequestMapping("/api/gift")
public interface GiftCommandProxy {
    /**
     * 根据主键查询礼品信息
     *
     * @param
     * @return
     */
    @PostMapping("/findGiftList")
    JsonResult findGiftList(Byte giftType, Integer pageNum, Integer pageSize);

}
