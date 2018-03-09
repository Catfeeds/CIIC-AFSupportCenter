package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.ComAccountExtDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.ComTaskParamDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountParamDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComTaskDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by houwanhua on 2018/3/8.
 */
@FeignClient("gt1-af-support-center-soc-api-service")
@RequestMapping("/api/soc")
public interface SocApiProxy {
    /**
     * 保存企业任务单
     * @param ssComTaskDTO
     * @return
     */
    @PostMapping("/saveComTask")
    JsonResult saveComTask(@RequestBody SsComTaskDTO ssComTaskDTO);
    /**
     * 获取企业社保账户信息表
     * @param paramDto
     * @return
     */
    @PostMapping("/getAccountList")
    JsonResult<List<SsComAccountDTO>> getComAccountList(@RequestBody SsComAccountParamDTO paramDto);


    @PostMapping("/getAccountByCompany")
    JsonResult<ComAccountExtDTO> getAccountByCompany(@RequestBody ComTaskParamDTO paramDTO);
}
