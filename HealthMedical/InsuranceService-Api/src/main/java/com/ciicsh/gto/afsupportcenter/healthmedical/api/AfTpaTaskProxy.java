package com.ciicsh.gto.afsupportcenter.healthmedical.api;

import com.ciicsh.gto.afsupportcenter.healthmedical.api.dto.AfTpaTaskDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("insurance-service")
@RequestMapping("/api/AfTpaTaskProxy")
public interface AfTpaTaskProxy {

    /**
     * 更新受理单数据
     *
     * @param afTpaTaskDTO
     * @return
     */
    @PostMapping(value = {"/getEmployeeCompany"}, produces = {"application/json"})
    Result updateAfTpaTask(AfTpaTaskDTO afTpaTaskDTO);

}
