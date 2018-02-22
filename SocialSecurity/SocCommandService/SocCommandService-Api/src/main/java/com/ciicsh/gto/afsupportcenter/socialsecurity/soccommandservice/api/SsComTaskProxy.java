package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsComTaskDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 企业社保账户信息查询接口
 */
@FeignClient("support-center-soc-command-service")
@RequestMapping("/api/soccommandservice/sscomtask")
public interface SsComTaskProxy {
    /**
     * 保存企业任务单
     *
     * @param ssComTaskDTO
     * @return
     */
    @PostMapping("/saveSsComTask")
    JsonResult saveSsComTask(@RequestBody SsComTaskDTO ssComTaskDTO);

}
