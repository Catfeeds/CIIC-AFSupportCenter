package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComTaskDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 企业社保账户信息查询接口
 */
@FeignClient("support-center-soc-command-service")
@RequestMapping("/api/soccommandservice/ssComTask")
public interface SsComTaskProxy {
    /**
     * 保存企业任务单
     *
     * @param ssComTaskDTO
     * @return
     */
    @PostMapping("/saveSsComTask")
    com.ciicsh.common.entity.JsonResult saveSsComTask(@RequestBody SsComTaskDTO ssComTaskDTO);

}
