package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.translator.ApiTranslator;
import com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.validator.SocApiValidator;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SocApiProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountExtBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComTaskParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthCharge;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ComAccountExtPO;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.enumeration.LogInfo;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

//    @Autowired
//    private SsPaymentComService ssPaymentComService;

    @Autowired
    private SsMonthChargeService ssMonthChargeService;

    @Autowired
    private SsMonthChargeItemService ssMonthChargeItemService;

    @Autowired
    private SsEmpTaskService ssEmpTaskService;

    @Autowired
    private SsEmpBasePeriodService ssEmpBasePeriodService;

    @Autowired
    private CommonApiUtils commonApiUtils;

    private static Map<String, String> ssLocalTaskCategoryMap;

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

    @Override
    @ApiOperation(value = "微信端获取雇员社保信息接口", notes = "根据客户ID和雇员ID获取对象")
    @PostMapping("/getSocialSecurity")
    public JsonResult<SocialSecurityDTO> getSocialSecurity(String companyId, String employeeId) {
        SsEmpArchiveBO ssEmpArchiveBO = ssEmpArchiveService.getSsEmployee(companyId, employeeId);

        if (ssEmpArchiveBO == null) {
            return JsonResult.faultMessage("支持中心反馈：无数据");
        }

        SocialSecurityDTO socialSecurityDTO = new SocialSecurityDTO();
        socialSecurityDTO.setPensionAccounts(ssEmpArchiveBO.getSsSerial());
        socialSecurityDTO.setCityName("上海");

//        // 获取最后的支付年月
//        Wrapper<SsPaymentCom> ssPaymentComWrapper = new EntityWrapper<>();
//        ssPaymentComWrapper.where("is_active = 1");
//        ssPaymentComWrapper.and("company_id = {0}", companyId);
//        ssPaymentComWrapper.and("payment_state = 8");
//        ssPaymentComWrapper.orderBy("payment_month desc");
//        ssPaymentComWrapper.last("limit 1");
//        SsPaymentCom ssPaymentCom = ssPaymentComService.selectOne(ssPaymentComWrapper);
//
//        if (ssPaymentCom != null) {
//            String paymentMonth = ssPaymentCom.getPaymentMonth();

        Wrapper<SsMonthCharge> ssMonthChargeWrapper = new EntityWrapper<>();
        ssMonthChargeWrapper.where("is_active = 1");
        ssMonthChargeWrapper.and("com_account_id = {0}", ssEmpArchiveBO.getComAccountId());
        ssMonthChargeWrapper.and("employee_id = {0}", employeeId);
        ssMonthChargeWrapper.orderBy("ss_month desc");
        ssMonthChargeWrapper.last("limit 1");
        SsMonthCharge ssMonthCharge = ssMonthChargeService.selectOne(ssMonthChargeWrapper);

        if (ssMonthCharge != null) {
            String ssMonth = ssMonthCharge.getSsMonth();

            if (ssMonthCharge.getCostCategory() == 6 || ssMonthCharge.getCostCategory() == 7) {
                ssMonth = ssMonthCharge.getSsMonthBelong();
            }

//                if (DateUtil.compareMonth(ssMonth, paymentMonth) < 0) {
//                    paymentMonth = ssMonth;
//                }
//            }

            // 获取雇员末次支付年月社保各险种的支付金额合计，社保基数各险种比例等信息
            List<SsEmpLastPaymentBO> ssEmpLastPaymentBOList = ssMonthChargeItemService.searchDetailLastPaymentMonth(companyId, employeeId, ssMonth);

            if (CollectionUtils.isNotEmpty(ssEmpLastPaymentBOList)) {
                List<SocialSecurityDetailDTO> socialSecurityDetailDTOList = new ArrayList<>(ssEmpLastPaymentBOList.size());

                for (SsEmpLastPaymentBO ssEmpLastPaymentBO : ssEmpLastPaymentBOList) {
                    SocialSecurityDetailDTO socialSecurityDetailDTO = new SocialSecurityDetailDTO();
                    String base = CalculateSocialUtils.digitInSimpleFormat(ssEmpLastPaymentBO.getBaseAmount());
                    socialSecurityDetailDTO.setSocialSecurityType(ssEmpLastPaymentBO.getSsTypeName());
                    socialSecurityDetailDTO.setCompaniesBase(base);
                    socialSecurityDetailDTO.setPercentageOfCompanies(CalculateSocialUtils.digitInSimpleFormat(ssEmpLastPaymentBO.getComRatio()));
                    socialSecurityDetailDTO.setCompaniesPay(CalculateSocialUtils.digitInSimpleFormat(ssEmpLastPaymentBO.getComAmount()));
                    socialSecurityDetailDTO.setPersonalBase(base);
                    socialSecurityDetailDTO.setProportionOfIndividuals(CalculateSocialUtils.digitInSimpleFormat(ssEmpLastPaymentBO.getEmpRatio()));
                    socialSecurityDetailDTO.setIndividualContributions(CalculateSocialUtils.digitInSimpleFormat(ssEmpLastPaymentBO.getEmpAmount()));
                    socialSecurityDetailDTO.setTotal(CalculateSocialUtils.digitInSimpleFormat(ssEmpLastPaymentBO.getSubTotalAmount()));
                    socialSecurityDetailDTOList.add(socialSecurityDetailDTO);
                }
                socialSecurityDTO.setSocialSecurityDetails(socialSecurityDetailDTOList);

                return JsonResult.success(socialSecurityDTO,"数据获取成功");
            }
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @ApiOperation(value = "微信端获取雇员社保变更信息接口", notes = "根据客户ID，雇员ID，年份获取对象")
    @PostMapping("/getSocialSecurityChangeInformation")
    public JsonResult<List<SocialSecurityChangeInformationDTO>> getSocialSecurityChangeInformation(String companyId, String employeeId, String year) {
        // 获取最后的支付年月
//        Wrapper<SsPaymentCom> ssPaymentComWrapper = new EntityWrapper<>();
//        ssPaymentComWrapper.where("is_active = 1");
//        ssPaymentComWrapper.and("company_id = {0}", companyId);
//        ssPaymentComWrapper.and("payment_state = 8");
//        ssPaymentComWrapper.orderBy("payment_month desc");
//        ssPaymentComWrapper.last("limit 1");
//        SsPaymentCom ssPaymentCom = ssPaymentComService.selectOne(ssPaymentComWrapper);
//
//        if (ssPaymentCom != null) {
//            String paymentMonth = ssPaymentCom.getPaymentMonth();
        List<SsMonthCharge> ssMonthChargeList = ssMonthChargeService.getSocialSecurityChangeInformation(companyId, employeeId, null, year);

        if (CollectionUtils.isNotEmpty(ssMonthChargeList)) {
            List<SocialSecurityChangeInformationDTO> socialSecurityChangeInformationDTOList = new ArrayList<>(ssMonthChargeList.size());
            String[] costCategories = { "标准", "新进", "转入", "补缴", "调整（顺调）", "转出", "封存", "退账", "调整（倒调）" };
            for(SsMonthCharge ssMonthCharge : ssMonthChargeList) {
                SocialSecurityChangeInformationDTO socialSecurityChangeInformationDTO = new SocialSecurityChangeInformationDTO();
                socialSecurityChangeInformationDTO.setWageBase(CalculateSocialUtils.digitInSimpleFormat(ssMonthCharge.getBaseAmount()));
                socialSecurityChangeInformationDTO.setExecutionDate(ssMonthCharge.getSsMonth());
                socialSecurityChangeInformationDTO.setChangeContent(costCategories[ssMonthCharge.getCostCategory() - 1]);
                socialSecurityChangeInformationDTOList.add(socialSecurityChangeInformationDTO);
            }

            return JsonResult.success(socialSecurityChangeInformationDTOList,"数据获取成功");
        }
//        }

        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @ApiOperation(value = "获取雇员的社保信息接口", notes = "客户Id，雇员Id")
    @PostMapping("/getEmpSocialSecurityInfo")
    public JsonResult<EmpSocialSecurityInfoDTO> getEmpSocialSecurityInfo(String companyId, String employeeId) {
        // 从雇员档案获取信息
        Wrapper<SsEmpArchive> ssEmpArchiveWrapper = new EntityWrapper<>();
        ssEmpArchiveWrapper.where("is_active = 1");
        ssEmpArchiveWrapper.and("company_id = {0}", companyId);
        ssEmpArchiveWrapper.and("employee_id = {0}", employeeId);
        ssEmpArchiveWrapper.orderBy("created_time desc");
        ssEmpArchiveWrapper.last("limit 1");
        SsEmpArchive ssEmpArchive = ssEmpArchiveService.selectOne(ssEmpArchiveWrapper);

        if (ssEmpArchive != null) {
            if (ssEmpArchive.getArchiveStatus().equals(3)) {
                EmpSocialSecurityInfoDTO empSocialSecurityInfoDTO = new EmpSocialSecurityInfoDTO();
                empSocialSecurityInfoDTO.setEndMonth(DateUtil.yyyyMMCN(ssEmpArchive.getEndMonth()));
                return JsonResult.success(empSocialSecurityInfoDTO, "数据获取成功");
            } else {
                // 如果雇员档案非封存状态，查询雇员转出或封存任务单获取信息，获取相关信息
                SsEmpTask ssEmpTask = getEmpEndTask(companyId, employeeId);

                if (ssEmpTask != null) {
                    EmpSocialSecurityInfoDTO empSocialSecurityInfoDTO = new EmpSocialSecurityInfoDTO();
                    empSocialSecurityInfoDTO.setEndMonth(DateUtil.yyyyMMCN(ssEmpTask.getEndMonth()));
                    return JsonResult.success(empSocialSecurityInfoDTO, "数据获取成功");
                }
            }
        } else {
            // 如果雇员档案不存在，查询雇员任务单尚未办理，从雇员任务单获取信息
            SsEmpTask ssEmpTask = getEmpEndTask(companyId, employeeId);

            if (ssEmpTask != null) {
                EmpSocialSecurityInfoDTO empSocialSecurityInfoDTO = new EmpSocialSecurityInfoDTO();
                empSocialSecurityInfoDTO.setEndMonth(DateUtil.yyyyMMCN(ssEmpTask.getEndMonth()));
                return JsonResult.success(empSocialSecurityInfoDTO, "数据获取成功");
            }
        }

        return JsonResult.faultMessage("支持中心反馈：无数据");
    }


    @Override
    @ApiOperation(value = "获取雇员的社保福利段信息接口", notes = "客户Id，雇员Id")
    @PostMapping("/getEmpBasePeriodInfo")
    public JsonResult<List<SsEmpBasePeriodDTO>> getEmpBasePeriodInfo(String companyId, String employeeId) {
        List<SsEmpBasePeriodBO> ssEmpBasePeriodBOList = ssEmpBasePeriodService.getEmpBasePeriodByIntervalYear(companyId, employeeId, 4);

        if (ssLocalTaskCategoryMap == null) {
            try {
                List<DicItemDTO> dictItemList = commonApiUtils.listByDicId(DictUtil.DICT_ID_SOC_LOCAL_TASK_CATEGORY);
                ssLocalTaskCategoryMap = new LinkedHashMap<>();
                dictItemList.stream().forEach((d) -> ssLocalTaskCategoryMap.put(d.getDicItemValue(), d.getDicItemText()));
            } catch (Exception e) {
                return JsonResult.faultMessage("支持中心反馈：获取字典数据失败");
            }
        }

        if (CollectionUtils.isNotEmpty(ssEmpBasePeriodBOList)) {
            List<SsEmpBasePeriodDTO> ssEmpBasePeriodDTOList = new ArrayList<>(ssEmpBasePeriodBOList.size());

            for(SsEmpBasePeriodBO ssEmpBasePeriodBO : ssEmpBasePeriodBOList) {
                SsEmpBasePeriodDTO ssEmpBasePeriodDTO = new SsEmpBasePeriodDTO();
                ssEmpBasePeriodDTO.setBaseAmount(ssEmpBasePeriodBO.getBaseAmount());
                ssEmpBasePeriodDTO.setStartMonth(ssEmpBasePeriodBO.getStartMonth());
                ssEmpBasePeriodDTO.setEndMonth(ssEmpBasePeriodBO.getEndMonth());
                ssEmpBasePeriodDTO.setChgContent(ssLocalTaskCategoryMap.get(String.valueOf(ssEmpBasePeriodBO.getTaskCategory())));
                ssEmpBasePeriodDTOList.add(ssEmpBasePeriodDTO);
            }
            return JsonResult.success(ssEmpBasePeriodDTOList, "数据获取成功");
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @PostMapping("/apiGetSsEmpTaskByEmpCompanyId")
    public JsonResult<List<SsEmpTaskArchiveDTO>> apiGetSsEmpTaskByEmpCompanyId(String empCompanyId) {
        List<SsEmpTaskArchiveDTO> listResult=new ArrayList<>();
        SsEmpTaskArchiveDTO targetSsEmpTaskArchiveDTO=new SsEmpTaskArchiveDTO();
         List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsEmpTaskArchiveDTO> listEmp = ssEmpTaskService.apiGetSsEmpTaskByEmpCompanyId(empCompanyId);
         for ( com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsEmpTaskArchiveDTO ssEmpTaskArchiveDTO : listEmp) {
             targetSsEmpTaskArchiveDTO=new SsEmpTaskArchiveDTO();
             BeanUtils.copyProperties(ssEmpTaskArchiveDTO,targetSsEmpTaskArchiveDTO);
             listResult.add(targetSsEmpTaskArchiveDTO);
        }
        JsonResult<List<SsEmpTaskArchiveDTO>> result = new JsonResult<>();
        result.setData(listResult);
        return result;
    }

    @Override
    @PostMapping("/apiGetSsEmpArchiveByEmpCompanyId")
    public JsonResult<List<SsEmpTaskArchiveDTO>> apiGetSsEmpArchiveByEmpCompanyId(String empCompanyId) {
        List<SsEmpTaskArchiveDTO> listResult=new ArrayList<>();
        SsEmpTaskArchiveDTO targetSsEmpTaskArchiveDTO=new SsEmpTaskArchiveDTO();
        List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsEmpTaskArchiveDTO> listEmp = ssEmpArchiveService.apiGetSsEmpArchiveByEmpCompanyId(empCompanyId);
        for ( com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsEmpTaskArchiveDTO ssEmpTaskArchiveDTO : listEmp) {
            targetSsEmpTaskArchiveDTO=new SsEmpTaskArchiveDTO();
            BeanUtils.copyProperties(ssEmpTaskArchiveDTO,targetSsEmpTaskArchiveDTO);
            listResult.add(targetSsEmpTaskArchiveDTO);
        }
        JsonResult<List<SsEmpTaskArchiveDTO>> result = new JsonResult<>();
        result.setData(listResult);

        return result;

    }


    private SsEmpTask getEmpEndTask(String companyId, String employeeId) {
        Wrapper<SsEmpTask> ssEmpTaskWrapper = new EntityWrapper<>();
        ssEmpTaskWrapper.where("is_active = 1");
        ssEmpTaskWrapper.and("company_id = {0}", companyId);
        ssEmpTaskWrapper.and("employee_id = {0}", employeeId);
        ssEmpTaskWrapper.and("task_status in (1, 4, 5)");
        ssEmpTaskWrapper.and("task_category in (5, 6, 14, 15)");
        ssEmpTaskWrapper.orderBy("created_time desc");
        ssEmpTaskWrapper.last("limit 1");

        return ssEmpTaskService.selectOne(ssEmpTaskWrapper);
    }

    private boolean checkSsParam(List<SsEmpInfoParamDTO> paramDTOList) {
        return paramDTOList != null ? true : false;
    }
}
