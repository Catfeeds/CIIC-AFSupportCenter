package com.ciicsh.gto.afsupportcenter.healthmedical.api;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("afsupport-center-command-service")
@RequestMapping("/api/FragmentaryReimbursement")
public interface FragmentaryReimbursementProxy {

}
