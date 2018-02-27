package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComAccountParamDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComTaskDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by houwanhua on 2018/2/27.
 */
@FeignClient("support-center-fund-api-service")
@RequestMapping("/api/fund")
public interface FundApiProxy {
    /**
     * 获取企业公积金账户信息表
     *
     * @param paramDto
     * @return
     */
    @RequestMapping("/getAccountList")
    JsonResult getHfComAccountList(@RequestBody HfComAccountParamDTO paramDto);

    /**
     * 保存企业任务单
     * @param hfComTaskDTO
     * @return
     */
    @PostMapping("/saveComTask")
    JsonResult saveHfComTask(@RequestBody HfComTaskDTO hfComTaskDTO);
}
