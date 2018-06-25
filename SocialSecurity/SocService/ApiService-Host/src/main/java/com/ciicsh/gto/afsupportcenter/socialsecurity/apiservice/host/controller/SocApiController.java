package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.translator.ApiTranslator;
import com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.validator.SocApiValidator;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SocApiProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpInfoBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpInfoDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpInfoParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountExtBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComTaskParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ComAccountExtPO;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.enumeration.LogInfo;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by houwanhua on 2018/3/8.
 */
@RestController
@RequestMapping("/api/soc")
@Api(value = "soc-api-service", description = "support interface for other center")
public class SocApiController implements SocApiProxy {
    @Autowired
    private SsComAccountService accountService;

    @Autowired
    private SsComTaskService taskService;

    @Autowired
    private SocApiValidator socApiValidator;

    @Autowired
    LogApiUtil logApiUtil;
    @Autowired
    private SsEmpArchiveService ssEmpArchiveService;


    @Override
    @ApiOperation(value = "企业社保账户开户、变更、转移、转出的 创建任务单接口", notes = "根据ComTask对象创建")
    @ApiImplicitParam(name = "ssComTaskDTO", value = "企业任务单对象 ssComTaskDTO", required = true, dataType = "SsComTaskDTO")
    @PostMapping("/saveComTask")
    public JsonResult saveComTask(@RequestBody SsComTaskDTO ssComTaskDTO) {
//        logService.info(LogContext.of().setSource(LogInfo.SOURCE_API.getKey()).setTitle("saveComTask").setTextContent("Request: "+ JSON.toJSONString(ssComTaskDTO)));
        try {
            //参数校验
            String result = socApiValidator.saveComTaskValidator(ssComTaskDTO);
            if (StringUtils.isNotBlank(result)) {
                logApiUtil.info(LogMessage.create().setTitle(LogInfo.SOURCE_API.getKey() + "#" + "SocApiController.saveComTask").setContent("result: " + result));
                return JsonResult.faultMessage(result);
            }
            //结算中心转变成字符串
            if (StringUtils.isNotBlank(ssComTaskDTO.getSettlementArea())) {
                String settlementArea = SocialSecurityConst.DISTRICT_MAP.get(ssComTaskDTO.getSettlementArea());
                if (StringUtils.isNotBlank(settlementArea)) {
                    ssComTaskDTO.setSettlementArea(settlementArea);
                }
            }
            Long newComTaskId = addComTask(ssComTaskDTO);
            return JsonResult.success(newComTaskId);
        } catch (Exception e) {
            return JsonResult.faultMessage(e.getMessage());
        }
    }

    //保存企业任务单
    private Long addComTask(SsComTaskDTO ssComTaskDTO) {
        LocalDateTime now = LocalDateTime.now();
        //对象转换
        SsComTask ssComTask = CommonTransform.convertToEntity(ssComTaskDTO, SsComTask.class);
        ssComTask.setTaskStatus(SocialSecurityConst.COM_TASK_STATUS_0);
        ssComTask.setActive(true);
        ssComTask.setSubmitTime(now);
        ssComTask.setSubmitterId(ssComTaskDTO.getSubmitterId());
        ssComTask.setSubmitterName(ssComTaskDTO.getSubmitterName());
        ssComTask.setCreatedTime(now);
        ssComTask.setCreatedBy(ssComTaskDTO.getSubmitterId());
        ssComTask.setCreatedDisplayName(ssComTaskDTO.getSubmitterName());
        ssComTask.setModifiedTime(now);
        ssComTask.setModifiedBy(ssComTaskDTO.getSubmitterId());
        ssComTask.setModifiedDisplayName(ssComTaskDTO.getSubmitterName());
        ssComTask.setLeaderShipId(ssComTaskDTO.getLeaderShipId());
        ssComTask.setLeaderShipName(ssComTaskDTO.getLeaderShipName());
        //任务单上前道系统传递过来的内容，Json格式
        ssComTask.setTaskFormContent(JSONObject.toJSONString(ssComTaskDTO));

        taskService.insertComTask(ssComTask);
        return ssComTask.getComTaskId();
    }


    @Override
    @ApiOperation(value = "获取企业社保账户信息列表", notes = "根据SsComAccountParamDTO对象获取")
    @ApiImplicitParam(name = "paramDto", value = "企业任务单对象 paramDto", required = true, dataType = "SsComAccountParamDTO")
    @PostMapping("/getAccountList")
    public JsonResult<List<SsComAccountDTO>> getComAccountList(@RequestBody SsComAccountParamDTO paramDto) {
        ComAccountParamBO paramBO = new ComAccountParamBO();
        BeanUtils.copyProperties(paramDto, paramBO);
        // 根据 客户ID和账户类型查询
        List<ComAccountExtPO> ssComAccountList = accountService.getSsComAccountList(paramBO);
        List<SsComAccountDTO> accountDTOS = new ArrayList<>();
        if (null != ssComAccountList && ssComAccountList.size() > 0) {
            accountDTOS = ssComAccountList.stream().map(ApiTranslator::toComAccountDTO).collect(Collectors.toList());
        }
        return JsonResult.success(accountDTOS);
    }


    @Override
    @ApiOperation(value = "获取企业社保账户信息", notes = "根据ComTaskParamDTO对象获取")
    @ApiImplicitParam(name = "paramDTO", value = "企业任务单对象 paramDTO", required = true, dataType = "ComTaskParamDTO")
    @PostMapping("/getAccountByCompany")
    public JsonResult<ComAccountExtDTO> getAccountByCompany(@RequestBody ComTaskParamDTO paramDTO) {
        logApiUtil.info(LogMessage.create().setTitle(LogInfo.SOURCE_API.getKey() + "#" + "SocApiController.getAccountByCompany").setContent("Request: " + JSON.toJSONString(paramDTO)));
        ComTaskParamBO paramBO = new ComTaskParamBO();
        BeanUtils.copyProperties(paramDTO, paramBO);
        ComAccountExtBO extBO = taskService.getComAccountInfo(paramBO);
        ComAccountExtDTO extDTO = null;
        if (null != extBO) {
            extDTO = new ComAccountExtDTO();
            BeanUtils.copyProperties(extBO, extDTO);
        }
        return JsonResult.success(extDTO);
    }

    @Override
    @ApiOperation(value = "获取企业社保雇员信息接口", notes = "根据List<SsEmpInfoParamDTO>集合获取")
    @ApiImplicitParam(name = "List<SsEmpInfoParamDTO>", value = "企业社保雇员集合 List<SsEmpInfoParamDTO>", required = true, dataType = "ComTaskParamDTO")
    @PostMapping("/getSsEmpInfo")
    public JsonResult<List<SsEmpInfoDTO>> getSsEmpInfo(@RequestBody List<SsEmpInfoParamDTO> paramDTOList) {
        // 对参数集合做null值判断，如果paramDTOList非null但对象为空，则根据输出约定返回对应空对象
        boolean checkFlag = checkSsParam(paramDTOList);
        if (!checkFlag) return JsonResult.message(false, "传入的参数集合为null");
        List<SsEmpInfoParamBO> paramBOList = new ArrayList<>();
        for (SsEmpInfoParamDTO paramDTO : paramDTOList) {
            SsEmpInfoParamBO paramBO = new SsEmpInfoParamBO();
            BeanUtils.copyProperties(paramDTO, paramBO);
            paramBOList.add(paramBO);
        }
        List<SsEmpInfoBO> resultBoList = ssEmpArchiveService.getSsEmpArchiveInfo(paramBOList);
        List<SsEmpInfoDTO> resultDTOList = new ArrayList<>();
        // resultBoList不会为null
        for (SsEmpInfoBO resultBO : resultBoList) {
            SsEmpInfoDTO resultDTO = new SsEmpInfoDTO();
            BeanUtils.copyProperties(resultBO, resultDTO);
            List<SsEmpInfoDetailDTO> targetDTOList = new ArrayList<>();
            for (SsEmpInfoDetailBO sourceBO : resultBO.getSsEmpInfoDetailBOList()) {
                SsEmpInfoDetailDTO targetDTO = new SsEmpInfoDetailDTO();
                BeanUtils.copyProperties(sourceBO, targetDTO);
                targetDTOList.add(targetDTO);
            }
            resultDTO.setSsEmpInfoDetailDTOList(targetDTOList);
            resultDTOList.add(resultDTO);
        }
        return JsonResult.success(resultDTOList);
    }

    @Override
    @ApiOperation(value = "获取企业社保账户信息接口", notes = "根据客户ID获取对象")
    @ApiImplicitParam(name = "companyId", value = "客户Id", required = true, dataType = "String")
    @PostMapping("/getSsComAccountByComId")
    public JsonResult<SsComAccountDTO> getSsComAccountByComId(@RequestParam("companyId")String companyId) {
        ComAccountExtPO comAccountExtPO = accountService.getSsComAccountByComId(companyId);
        SsComAccountDTO ssComAccountDTO =new SsComAccountDTO();
        if(comAccountExtPO !=null){
            BeanUtils.copyProperties(comAccountExtPO, ssComAccountDTO);
            return JsonResult.success(ssComAccountDTO,"数据获取成功");
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");


    }

    @Override
    @ApiOperation(value = "获取社保雇员信息接口", notes = "根据客户ID和雇员ID获取对象")
    @PostMapping("/getSsEmpInfoById")
    public JsonResult<SsEmpInfoDTO> getSsEmpInfoById(@RequestParam("companyId")String companyId, @RequestParam("employeeId")String employeeId) {
        SsEmpInfoBO ssEmpInfoBO = accountService.getSsEmpInfoById(companyId,employeeId);
        SsEmpInfoDTO ssEmpInfoDTO=new SsEmpInfoDTO();
        if(ssEmpInfoBO!=null){
            BeanUtils.copyProperties(ssEmpInfoBO,ssEmpInfoDTO);
            return JsonResult.success(ssEmpInfoDTO,"数据获取成功");
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    private boolean checkSsParam(List<SsEmpInfoParamDTO> paramDTOList) {
        return paramDTOList != null ? true : false;
    }
}
