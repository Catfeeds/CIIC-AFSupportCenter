package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.controller;

import com.alibaba.fastjson.JSON;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.enumeration.Const;
import com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.translator.ApiTranslator;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.FundApiProxy;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by houwanhua on 2018/2/27.
 */
@RestController
@RequestMapping("/api/fund")
@Api(value = "fund-api-service",description = "support interface for other center")
public class FundApiController implements FundApiProxy{
    @Autowired
    HfComAccountService hfComAccountService;

    @Autowired
    HfComTaskService hfComTaskService;

    @Autowired
    private LogApiUtil log;

    /**
     * 企业公积金账户开户、变更、转移、转出的 创建任务单接口
     * @param comTaskDTO
     * @return
     */
    @Override
    @ApiOperation(value = "企业公积金账户开户、变更、转移、转出的 创建任务单接口",notes = "根据ComTask对象创建")
    @ApiImplicitParam(name = "comTaskDTO",value = "企业任务单对象 comTaskDTO",required = true,dataType = "HfComTaskDTO")
    @PostMapping("/saveComTask")
    public JsonResult saveComTask(@RequestBody HfComTaskDTO comTaskDTO) {
        try {
            log.info(LogMessage.create().setTitle(Const.SAVECOMTASK.getKey()).setContent("Request: "+JSON.toJSONString(comTaskDTO)));
            if (StringUtils.isBlank(comTaskDTO.getCompanyId())) {
                return JsonResult.faultMessage("客户Id不能为空！");
            }
            if (comTaskDTO.getTaskCategory() == null || comTaskDTO.getTaskCategory() == 0) {
                return JsonResult.faultMessage("任务类型不能为空！");
            }
            boolean flag = checkIsExistAccount(comTaskDTO);
            if(flag){
                return JsonResult.faultMessage("该企业已存在相同类型的处理中任务单，不能重复添加！");
            }
            else{
                Long comTaskId = addComTask(comTaskDTO);
                return JsonResult.success(comTaskId);
            }
        } catch (Exception e) {
            return JsonResult.faultMessage("exception: "+e.getMessage());
        }

    }

    private boolean checkIsExistAccount(HfComTaskDTO comTask){
        Integer isExist = hfComAccountService.isExistAccount(comTask.getCompanyId(),comTask.getHfType());
        if(isExist <= 0){
            isExist = hfComTaskService.isExistComTask(comTask.getCompanyId(),comTask.getHfType(),comTask.getTaskCategory());
        }
        return isExist > 0 ? true : false;
    }

    //保存企业任务单
    private Long addComTask(HfComTaskDTO hfComTaskDTO) {
        HfComTask hfComTask = new HfComTask();
        BeanUtils.copyProperties(hfComTaskDTO,hfComTask);
        if(hfComTaskDTO.getTaskCategory().equals(1)){
            if(!StringUtils.isBlank(hfComTask.getHfComAccount())){
                hfComTask.setHfComAccount("");
            }
        }
        hfComTask.setSubmitTime(new Date());
        hfComTask.setTaskStatus(0);
        hfComTask.setActive(true);
        hfComTask.setCreatedTime(new Date());
        hfComTask.setModifiedTime(new Date());
        hfComTask.setCreatedBy(hfComTaskDTO.getSubmitterId());
        hfComTask.setCreatedDisplayName(hfComTaskDTO.getSubmitterName());
        hfComTask.setModifiedBy(hfComTaskDTO.getSubmitterId());
        hfComTask.setModifiedDisplayName(hfComTaskDTO.getSubmitterName());
        hfComTaskService.insert(hfComTask);
        return hfComTask.getComTaskId();
    }

    @Override
    @ApiOperation(value = "获取企业公积金账户信息",notes = "根据HfComAccountParamDTO对象获取")
    @ApiImplicitParam(name = "paramDto",value = "企业任务单对象 paramDto",required = true,dataType = "HfComAccountParamDTO")
    @PostMapping("/getAccountList")
    public JsonResult<List<HfComAccountDTO>> getComAccountList(@RequestBody HfComAccountParamDTO paramDto) {

        log.info(LogMessage.create().setTitle(Const.GETACCOUNTLIST.getKey()).setContent("Request: "+JSON.toJSONString(paramDto)));
        ComAccountParamExtBo paramBO = new ComAccountParamExtBo();
        BeanUtils.copyProperties(paramDto,paramBO);

        // 根据 客户ID和账户类型查询
        List<ComAccountExtBo> ssComAccountList = hfComAccountService.getHfComAccountList(paramBO);
        List<HfComAccountDTO> accountDTOS = new ArrayList<>();
        if(null != ssComAccountList && ssComAccountList.size() > 0){
            accountDTOS = ssComAccountList.stream().map(ApiTranslator::toComAccountDTO).collect(Collectors.toList());
        }
        log.info(LogMessage.create().setTitle(Const.GETACCOUNTLIST.getKey()).setContent("Response: "+JSON.toJSONString(accountDTOS)));
        return JsonResult.success(accountDTOS);
    }

    /**
     * 根据公司ID获取公积金账户信息
     * @param companyId 公司ID
     * @return
     */
    @Override
    @ApiOperation(value = "获取公积金账户信息",notes = "根据公司ID获取公积金账户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "companyId", value = "公司ID", required = true, dataType = "String")
    })
    @GetMapping("/getAccountByCompany")
    public JsonResult<HfComAccountExtDTO> getAccountByCompany(@RequestParam("companyId") String companyId) {

        String request =  "Request: { companyId :" + companyId +"}";
        log.info(LogMessage.create().setTitle(Const.GETACCOUNTBYCOMPANY.getKey()).setContent(request));
        List<AccountInfoBO> infos = hfComAccountService.getAccountByCompany(companyId);
        HfComAccountExtDTO extDTO = null;
        if(null != infos && infos.size() > 0){
            AccountInfoBO info = infos.get(0);
            extDTO = new HfComAccountExtDTO();
            extDTO.setComAccountName(info.getComAccountName());
            extDTO.setPaymentWay(info.getPaymentWay());
            extDTO.setCloseDay(info.getCloseDay());
            extDTO.setCompanyId(info.getCompanyId());
            extDTO.setFundInfos(infos.stream().map(ApiTranslator::toFundInfoDTO).collect(Collectors.toList()));
        }
        log.info(LogMessage.create().setTitle(Const.GETACCOUNTBYCOMPANY.getKey()).setContent("Response: "+JSON.toJSONString(extDTO)));
        return JsonResult.success(extDTO);
    }

    @Override
    public JsonResult<List<HfEmpInfoDTO>> getHfEmpInfo(@RequestBody List<HfEmpInfoParamDTO> paramDTOList) {
        return null;
    }
}
