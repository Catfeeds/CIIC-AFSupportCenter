package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("afsupportcenter-center-command-service")
@RequestMapping("/api/gift")
public interface GiftCommandProxy {
    /**
     * 根据主键查询礼品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    Result findById(@PathVariable Integer id);

    /**
     * 根据主键查询礼品信息
     *
     * @param
     * @return
     */
    @PostMapping("/updateById")
    Result updateById(Integer id, Integer num);

}
