package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by houwanhua on 2018/2/27.
 */
@FeignClient("support-center-housefund-api-service")
@RequestMapping("/api/fund")
public interface FundApiProxy {
    /**
     * 获取企业公积金账户信息表
     * @param paramDto
     * @return
     */
    @PostMapping("/getAccountList")
    JsonResult<List<HfComAccountDTO>> getComAccountList(@RequestBody HfComAccountParamDTO paramDto);

    /**
     * 保存企业任务单
     * @param comTaskDTO
     * @return
     */
    @PostMapping("/saveComTask")
    JsonResult saveComTask(@RequestBody HfComTaskDTO comTaskDTO);


    /**
     * 根据公司ID和公积金类别获取公积金账户信息
     * @param companyId 公司ID
     * @return
     */
    @GetMapping("/getAccountByCompany")
    JsonResult<HfComAccountExtDTO> getAccountByCompany(@RequestParam("companyId") String companyId);

    @PostMapping("/getHfEmpInfo")
    JsonResult<List<HfEmpInfoDTO>> getHfEmpInfo(@RequestBody List<HfEmpInfoParamDTO> paramDTOList);
}
