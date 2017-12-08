package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api;


import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto.JsonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("afsupport-center-query-service")
@RequestMapping("/api/FragmentaryReimbursement")
public interface FragmentaryReimbursementProxy {

    /**
     * 新增零星报销
     * @param param 零星报销信息
     * @return
     */
    @PostMapping("")
    void saveFragmentaryReimbursement(@RequestBody Map<String, Object> param);

}
