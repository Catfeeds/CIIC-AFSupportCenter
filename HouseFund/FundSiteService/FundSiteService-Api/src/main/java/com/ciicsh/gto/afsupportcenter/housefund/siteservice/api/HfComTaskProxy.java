package com.ciicsh.gto.afsupportcenter.housefund.siteservice.api;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.ResultDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企业公积金账户信息查询接口
 */
@FeignClient("support-center-housefund-site-service")
@RequestMapping("/ai/soccommandservice/hfApi")
public interface HfComTaskProxy {
    /**
     * 保存企业任务单
     *
     * @param hfComTaskDTO
     * @return
     */
    @PostMapping("/saveHfComTask")
    ResultDTO saveHfComTask(@RequestBody HfComTaskDTO hfComTaskDTO);

}
