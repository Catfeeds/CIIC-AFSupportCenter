package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.enumeration.Const;
import com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.translator.ApiTranslator;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.FundApiProxy;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpArchiveConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfMonthChargeConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

    @Autowired
    private HfEmpArchiveService hfEmpArchiveService;
//    @Autowired
//    private HfPaymentComService hfPaymentComService;
    @Autowired
    private HfMonthChargeService hfMonthChargeService;
    @Autowired
    private HfEmpTaskService hfEmpTaskService;
    @Autowired
    private HfEmpTaskTransferService hfEmpTaskTransferService;

    @Autowired
    private CommonApiUtils commonApiUtils;

    private static Map<String, String> taskCategoryMap;

    private static Map<String, String> taskStatusMap;

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
    @ApiOperation(value = "获取雇员公积金账户详细信息接口", notes = "根据雇员编号、客户编号、所属月份获取公积金详细信息")
    @ApiImplicitParam(name = "paramDTOList", value = "雇员信息集合 paramDTOList", required = true, dataType = "List<HfEmpInfoParamDTO>")
    @PostMapping("/getHfEmpInfo")
    public JsonResult<List<HfEmpInfoDTO>> getHfEmpInfo(@RequestBody List<HfEmpInfoParamDTO> paramDTOList) {
        // 对参数集合做null值判断，如果paramDTOList非null但对象为空，则根据输出约定返回对应空对象
        boolean checkFlag = checkHfParam(paramDTOList);
        if (!checkFlag) return JsonResult.message(false, "传入的参数集合为null");
        List<HfEmpInfoParamBO> paramBOList = new ArrayList<>();
        for (HfEmpInfoParamDTO paramDTO : paramDTOList) {
            HfEmpInfoParamBO paramBO = new HfEmpInfoParamBO();
            BeanUtils.copyProperties(paramDTO, paramBO);
            paramBOList.add(paramBO);
        }
        List<HfEmpInfoBO> resultBoList = hfEmpArchiveService.getHfEmpArchiveInfo(paramBOList);
        List<HfEmpInfoDTO> resultDTOList = new ArrayList<>();
        // resultBoList不会为null
        for (HfEmpInfoBO resultBO : resultBoList) {
            HfEmpInfoDTO resultDTO = new HfEmpInfoDTO();
            BeanUtils.copyProperties(resultBO, resultDTO);
            List<HfEmpInfoDetailDTO> targetDTOList = new ArrayList<>();
            for (HfEmpInfoDetailBO sourceBO : resultBO.getHfEmpInfoDetailBOList()) {
                HfEmpInfoDetailDTO targetDTO = new HfEmpInfoDetailDTO();
                BeanUtils.copyProperties(sourceBO, targetDTO);
                targetDTOList.add(targetDTO);
            }
            resultDTO.setHfEmpInfoDetailDTOList(targetDTOList);
            resultDTOList.add(resultDTO);
        }
        return JsonResult.success(resultDTOList);
    }

    @Override
    @ApiOperation(value = "获取企业社保账户信息接口", notes = "根据客户ID获取对象")
    @ApiImplicitParam(name = "companyId", value = "客户Id", required = true, dataType = "String")
    @PostMapping("/getHfComAccountByComId")
    public JsonResult<HfComAccountDTO> getHfComAccountByComId(@RequestParam("companyId")String companyId) {
        ComAccountExtBo comAccountExtBo = hfComAccountService.getHfComAccountByComId(companyId);
        HfComAccountDTO hfComAccountDTO = new HfComAccountDTO();
        if(comAccountExtBo!=null){
            BeanUtils.copyProperties(comAccountExtBo,hfComAccountDTO);
            return JsonResult.success(hfComAccountDTO,"数据获取成功");
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @ApiOperation(value = "获取社保雇员信息接口", notes = "根据客户ID和雇员ID获取对象")
    @PostMapping("/getHfEmpInfoById")
    public JsonResult<HfEmpInfoDTO> getHfEmpInfoById(@RequestParam("companyId")String companyId, @RequestParam("employeeId")String employeeId) {
        HfEmpInfoBO hfEmpInfoBO = hfEmpArchiveService.getHfEmpInfoById(companyId,employeeId);
        HfEmpInfoDTO hfEmpInfoDTO=new HfEmpInfoDTO();
        if(hfEmpInfoBO!=null){
            BeanUtils.copyProperties(hfEmpInfoBO,hfEmpInfoDTO);
            return JsonResult.success(hfEmpInfoDTO,"数据获取成功");
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @ApiOperation(value = "微信端获取雇员公积金信息接口", notes = "根据客户ID和雇员ID获取对象")
    @PostMapping("/getFund")
    public JsonResult<FundDTO> getFund(String companyId, String employeeId) {
        Wrapper<HfEmpArchive> hfEmpArchiveWrapper = new EntityWrapper<>();
        hfEmpArchiveWrapper.where("is_active = 1");
        hfEmpArchiveWrapper.and("company_id = {0}", companyId);
        hfEmpArchiveWrapper.and("employee_id = {0}", employeeId);
        hfEmpArchiveWrapper.and("hf_type = 1");
        hfEmpArchiveWrapper.orderBy("created_time desc");
        hfEmpArchiveWrapper.last("limit 1");
        HfEmpArchive hfEmpArchive = hfEmpArchiveService.selectOne(hfEmpArchiveWrapper);

        if (hfEmpArchive == null) {
            return JsonResult.faultMessage("支持中心反馈：无数据");
        }

        FundDTO fundDTO = new FundDTO();
        fundDTO.setCityName("上海");
        fundDTO.setFundAccount(hfEmpArchive.getHfEmpAccount());
        String basStatus = "正常";

        if (hfEmpArchive.getArchiveStatus() == HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED) {
            basStatus = "封存";
        }
        Wrapper<HfEmpArchive> hfEmpArchiveAddWrapper = new EntityWrapper<>();
        hfEmpArchiveAddWrapper.where("is_active = 1");
        hfEmpArchiveAddWrapper.and("company_id = {0}", companyId);
        hfEmpArchiveAddWrapper.and("employee_id = {0}", employeeId);
        hfEmpArchiveAddWrapper.and("hf_type = 2");
        hfEmpArchiveAddWrapper.orderBy("created_time desc");
        hfEmpArchiveAddWrapper.last("limit 1");
        HfEmpArchive hfEmpArchiveAdd = hfEmpArchiveService.selectOne(hfEmpArchiveAddWrapper);

        String addStatus = "正常";
        if (hfEmpArchiveAdd != null) {
            fundDTO.setSupplementaryFundAccount(hfEmpArchiveAdd.getHfEmpAccount());

            if (hfEmpArchiveAdd.getArchiveStatus() == HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED) {
                addStatus = "封存";
            }
        }
//        String paymentMonth = hfPaymentComService.getLastPaymentMonth(companyId, 1);
//
//        if (paymentMonth != null) {
        Wrapper<HfMonthCharge> hfMonthChargeWrapper = new EntityWrapper<>();
        hfMonthChargeWrapper.where("is_active = 1");
        hfMonthChargeWrapper.and("company_id = {0}", hfEmpArchive.getCompanyId());
        hfMonthChargeWrapper.and("employee_id = {0}", hfEmpArchive.getEmployeeId());
        hfMonthChargeWrapper.and("hf_type = 1");
        hfMonthChargeWrapper.orderBy("hf_month desc");
        hfMonthChargeWrapper.last("limit 1");
        HfMonthCharge hfMonthCharge = hfMonthChargeService.selectOne(hfMonthChargeWrapper);

        if (hfMonthCharge != null) {
            String hfMonth = hfMonthCharge.getHfMonth();

            if (hfMonthCharge.getPaymentType() == HfMonthChargeConstant.PAYMENT_TYPE_TRANS_OUT
                || hfMonthCharge.getPaymentType() == HfMonthChargeConstant.PAYMENT_TYPE_CLOSE
                ) {
                hfMonth = hfMonthCharge.getSsMonthBelong();
            }

//                if (DateUtil.compareMonth(hfMonth, paymentMonth) < 0) {
//                    paymentMonth = hfMonth;
//                }
//        }

            List<HfEmpLastPaymentBO> hfEmpLastPaymentBOList = hfMonthChargeService.searchByLastPaymentMonth(companyId, employeeId, hfMonth);

            if (CollectionUtils.isNotEmpty(hfEmpLastPaymentBOList)) {
                List<FundDetailDTO> fundDetailDTOList = new ArrayList<>(hfEmpLastPaymentBOList.size());

                for (HfEmpLastPaymentBO hfEmpLastPaymentBO : hfEmpLastPaymentBOList) {
                    FundDetailDTO fundDetailDTO = new FundDetailDTO();
                    String fundType = "基本";
                    String status = basStatus;

                    if (hfEmpLastPaymentBO.getHfType() == HfEmpTaskConstant.HF_TYPE_ADDED) {
                        fundType = "补充";
                        status = addStatus;
                    }
                    fundDetailDTO.setFundType(fundType);
                    fundDetailDTO.setBasePay(CalculateSocialUtils.digitInSimpleFormat(hfEmpLastPaymentBO.getBase()));
                    fundDetailDTO.setPercentageOfCompanies(CalculateSocialUtils.digitInSimpleFormat(hfEmpLastPaymentBO.getRatioCom()));
                    fundDetailDTO.setCompaniesPay(CalculateSocialUtils.digitInSimpleFormat(hfEmpLastPaymentBO.getComAmount()));
                    fundDetailDTO.setProportionOfIndividuals(CalculateSocialUtils.digitInSimpleFormat(hfEmpLastPaymentBO.getRatioEmp()));
                    fundDetailDTO.setIndividualContributions(CalculateSocialUtils.digitInSimpleFormat(hfEmpLastPaymentBO.getEmpAmount()));
                    fundDetailDTO.setTotal(CalculateSocialUtils.digitInSimpleFormat(hfEmpLastPaymentBO.getAmount()));
                    fundDetailDTO.setStatus(status);
                    fundDetailDTOList.add(fundDetailDTO);
                }
                fundDTO.setFundDetails(fundDetailDTOList);

                return JsonResult.success(fundDTO,"数据获取成功");
            }
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @ApiOperation(value = "微信端获取雇员公积金变更信息接口", notes = "根据客户ID，雇员ID，年份获取对象")
    @PostMapping("/getFundChangeInformation")
    public JsonResult<List<FundChangeInformationDTO>> getFundChangeInformation(String companyId, String employeeId, String year) {
//        String paymentMonth = hfPaymentComService.getLastPaymentMonth(companyId, 1);
//
//        if (paymentMonth != null) {
        Wrapper<HfMonthCharge> hfMonthChargeWrapper = new EntityWrapper<>();
        hfMonthChargeWrapper.where("is_active = 1");
        hfMonthChargeWrapper.and("company_id = {0}", companyId);
        hfMonthChargeWrapper.and("employee_id = {0}", employeeId);
        hfMonthChargeWrapper.and("LEFT(hf_month, 4) = {0}", year);
//                hfMonthChargeWrapper.and("hf_month <= {0}", paymentMonth);
        hfMonthChargeWrapper.and("payment_type != 1");
        hfMonthChargeWrapper.orderBy("company_id,employee_id,hf_month,ss_month_belong,hf_type");
        List<HfMonthCharge> hfMonthChargeList = hfMonthChargeService.selectList(hfMonthChargeWrapper);

        if (CollectionUtils.isNotEmpty(hfMonthChargeList)) {
            List<FundChangeInformationDTO> fundChangeInformationDTOList = new ArrayList<>(hfMonthChargeList.size());
            String[] paymentTypes = {"标准", "开户", "转入", "启封", "调整启封", "补缴", "转出", "封存", "调整封存", "销户", "差额补缴"};

            for (HfMonthCharge hfMonthCharge : hfMonthChargeList) {
                FundChangeInformationDTO fundChangeInformationDTO = new FundChangeInformationDTO();
                fundChangeInformationDTO.setWageBase(CalculateSocialUtils.digitInSimpleFormat(hfMonthCharge.getBase()));
                String fundType = "基本";

                if (hfMonthCharge.getHfType() == HfEmpTaskConstant.HF_TYPE_ADDED) {
                    fundType = "补充";
                }
                fundChangeInformationDTO.setFundType(fundType);
                fundChangeInformationDTO.setExecutionDate(hfMonthCharge.getHfMonth());
                fundChangeInformationDTO.setChangeContent(paymentTypes[hfMonthCharge.getPaymentType() - 1]);
                fundChangeInformationDTOList.add(fundChangeInformationDTO);
            }

            return JsonResult.success(fundChangeInformationDTOList, "数据获取成功");

        }
//        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @ApiOperation(value = "获取雇员的公积金信息接口", notes = "客户Id，雇员Id，公积金类型")
    @PostMapping("/getEmpFundInfo")
    public JsonResult<EmpFundInfoDTO> getEmpFundInfo(String companyId, String employeeId, Integer hfType) {
        // 从雇员档案获取信息
        Wrapper<HfEmpArchive> hfEmpArchiveWrapper = new EntityWrapper<>();
        hfEmpArchiveWrapper.where("is_active = 1");
        hfEmpArchiveWrapper.and("company_id = {0}", companyId);
        hfEmpArchiveWrapper.and("employee_id = {0}", employeeId);
        hfEmpArchiveWrapper.and("hf_type = {0}", hfType);
        hfEmpArchiveWrapper.orderBy("created_time desc");
        hfEmpArchiveWrapper.last("limit 1");
        HfEmpArchive hfEmpArchive = hfEmpArchiveService.selectOne(hfEmpArchiveWrapper);

        if (hfEmpArchive != null) {
            if (hfEmpArchive.getArchiveStatus().equals(HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED)) {
                EmpFundInfoDTO empFundInfoDTO = new EmpFundInfoDTO();
                empFundInfoDTO.setEndMonth(DateUtil.yyyyMMCN(hfEmpArchive.getEndMonth()));
                return JsonResult.success(empFundInfoDTO, "数据获取成功");
            } else {
                // 如果雇员档案非封存状态，查询雇员转出或封存任务单获取信息，获取相关信息
                HfEmpTask hfEmpTask = getEmpEndTask(companyId, employeeId, hfType);

                if (hfEmpTask != null) {
                    EmpFundInfoDTO empFundInfoDTO = new EmpFundInfoDTO();
                    empFundInfoDTO.setEndMonth(DateUtil.yyyyMMCN(hfEmpTask.getEndMonth()));
                    return JsonResult.success(empFundInfoDTO, "数据获取成功");
                }
            }
        } else {
            // 如果雇员档案不存在，查询雇员任务单尚未办理，从雇员任务单获取信息
            HfEmpTask hfEmpTask = getEmpEndTask(companyId, employeeId, hfType);

            if (hfEmpTask != null) {
                EmpFundInfoDTO empFundInfoDTO = new EmpFundInfoDTO();
                empFundInfoDTO.setEndMonth(DateUtil.yyyyMMCN(hfEmpTask.getEndMonth()));
                return JsonResult.success(empFundInfoDTO, "数据获取成功");
            }
        }

        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @ApiOperation(value = "获取雇员公积金档案信息接口", notes = "雇员入离职Id，公积金类型")
    @PostMapping("/getEmpArchiveByEmpCompanyId")
    public JsonResult<HfEmpArchiveInfoDTO> getEmpArchiveByEmpCompanyId(Long empCompanyId, Integer hfType) {
        Wrapper<HfEmpArchive> hfEmpArchiveWrapper = new EntityWrapper<>();
        hfEmpArchiveWrapper.where("is_active = 1");
        hfEmpArchiveWrapper.and("emp_company_id = {0}", empCompanyId);
        hfEmpArchiveWrapper.and("hf_type = {0}", hfType);
        hfEmpArchiveWrapper.last("limit 1");
        HfEmpArchive hfEmpArchive = hfEmpArchiveService.selectOne(hfEmpArchiveWrapper);

        if (hfEmpArchive != null) {
            HfComAccount hfComAccount = hfComAccountService.selectById(hfEmpArchive.getComAccountId());

            HfEmpArchiveInfoDTO hfEmpArchiveInfoDTO = new HfEmpArchiveInfoDTO();
            hfEmpArchiveInfoDTO.setPaymentBank(hfComAccountService.getHfComAccountPaymentBankValue(hfComAccount.getPaymentBank()));
            BeanUtils.copyProperties(hfEmpArchive, hfEmpArchiveInfoDTO, new String[] {"operationRemindDate", "inDate", "outDate"});
            hfEmpArchiveInfoDTO.setOperationRemindDate(DateUtil.localDateToDate(hfEmpArchive.getOperationRemindDate()));
            hfEmpArchiveInfoDTO.setInDate(DateUtil.localDateToDate(hfEmpArchive.getInDate()));
            hfEmpArchiveInfoDTO.setOutDate(DateUtil.localDateToDate(hfEmpArchive.getOutDate()));
            return JsonResult.success(hfEmpArchiveInfoDTO, "数据获取成功");
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }

    @Override
    @ApiOperation(value = "获取雇员公积金任务单相关信息接口", notes = "雇员任务Id")
    @PostMapping("/getEmpTaskDetailByTaskId")
    public JsonResult<HfEmpTaskDetailInfoDTO> getEmpTaskDetailByTaskId(String taskId) {
        EmpTaskDetailBO empTaskDetailBO = hfEmpTaskService.getEmpTaskDetailByTaskId(taskId);

        if (empTaskDetailBO != null) {
            HfEmpTaskDetailInfoDTO hfEmpTaskDetailInfoDTO = new HfEmpTaskDetailInfoDTO();
            BeanUtils.copyProperties(empTaskDetailBO, hfEmpTaskDetailInfoDTO, new String[] {"operationRemindDate", "submitTime", "createdTime", "modifiedTime"});
            hfEmpTaskDetailInfoDTO.setOperationRemindDate(DateUtil.localDateToDate(empTaskDetailBO.getOperationRemindDate()));
            hfEmpTaskDetailInfoDTO.setSubmitTime(DateUtil.localDateToDate(empTaskDetailBO.getSubmitTime()));
            hfEmpTaskDetailInfoDTO.setCreatedTime(DateUtil.localDateTimeToDate(empTaskDetailBO.getCreatedTime()));
            hfEmpTaskDetailInfoDTO.setModifiedTime(DateUtil.localDateTimeToDate(empTaskDetailBO.getModifiedTime()));
            return JsonResult.success(hfEmpTaskDetailInfoDTO, "数据获取成功");
        }

        return JsonResult.faultMessage("支持中心反馈：无数据");
    }


    @Override
    @ApiOperation(value = "获取雇员公积金转移信息接口", notes = "雇员入离职Id，公积金类型")
    @PostMapping("/getEmpTaskTransferByEmpCompanyId")
    public JsonResult<List<HfEmpTaskTransferInfoDTO>> getEmpTaskTransferByEmpCompanyId(Long empCompanyId, Integer hfType) {
        List<EmpTaskTransferBo> empTaskTransferBoList = hfEmpTaskTransferService.getEmpTaskTransferByEmpCompanyId(empCompanyId, hfType);

        if (CollectionUtils.isNotEmpty(empTaskTransferBoList)) {
            List<HfEmpTaskTransferInfoDTO> hfEmpTaskTransferInfoDTOList = new ArrayList<>(empTaskTransferBoList.size());

            for (EmpTaskTransferBo empTaskTransferBo : empTaskTransferBoList) {
                HfEmpTaskTransferInfoDTO hfEmpTaskTransferInfoDTO = new HfEmpTaskTransferInfoDTO();
                hfEmpTaskTransferInfoDTO.setCompanyId(empTaskTransferBo.getCompanyId());
                hfEmpTaskTransferInfoDTO.setEmployeeId(empTaskTransferBo.getEmployeeId());
                hfEmpTaskTransferInfoDTO.setEmpTaskId(empTaskTransferBo.getEmpTaskId());
                hfEmpTaskTransferInfoDTO.setEmpArchiveId(empTaskTransferBo.getEmpArchiveId());
                hfEmpTaskTransferInfoDTO.setHfEmpAccount(empTaskTransferBo.getHfEmpAccount());
                hfEmpTaskTransferInfoDTO.setHfType(empTaskTransferBo.getHfType());
                hfEmpTaskTransferInfoDTO.setSubmitterId(empTaskTransferBo.getSubmitterId());
                hfEmpTaskTransferInfoDTO.setSubmitTime(DateUtil.localDateToDate(empTaskTransferBo.getSubmitTime()));
                hfEmpTaskTransferInfoDTO.setTransferInUnit(empTaskTransferBo.getTransferInUnit());
                hfEmpTaskTransferInfoDTO.setTransferInUnitAccount(empTaskTransferBo.getTransferInUnitAccount());
                hfEmpTaskTransferInfoDTO.setTransferOutUnit(empTaskTransferBo.getTransferOutUnit());
                hfEmpTaskTransferInfoDTO.setTransferOutUnitAccount(empTaskTransferBo.getTransferOutUnitAccount());
                hfEmpTaskTransferInfoDTO.setTransferDate(DateUtil.localDateToDate(empTaskTransferBo.getTransferDate()));
                hfEmpTaskTransferInfoDTO.setFeedbackDate(DateUtil.localDateToDate(empTaskTransferBo.getFeedbackDate()));
                hfEmpTaskTransferInfoDTO.setCreatedDisplayName(empTaskTransferBo.getCreatedDisplayName());
                hfEmpTaskTransferInfoDTO.setHandleUserName(empTaskTransferBo.getHandleUserName());
                hfEmpTaskTransferInfoDTOList.add(hfEmpTaskTransferInfoDTO);
            }
            return JsonResult.success(hfEmpTaskTransferInfoDTOList, "数据获取成功");
        }
        return JsonResult.faultMessage("支持中心反馈：无数据");
    }


//    @Override
//    @ApiOperation(value = "获取雇员公积金任务单信息接口", notes = "雇员入离职Id，公积金类型")
//    @PostMapping("/getEmpTaskByEmpCompanyId")
//    public JsonResult<List<HfEmpTaskInfoDTO>> getEmpTaskByEmpCompanyId(Long empCompanyId, Integer hfType) {
//        this.dictInit();
//        List<EmpCompanyIdTaskBO> empCompanyIdTaskBOList = hfEmpTaskService.getEmpTaskByEmpCompanyId(empCompanyId, hfType);
//        if (CollectionUtils.isNotEmpty(empCompanyIdTaskBOList)) {
//            List<HfEmpTaskInfoDTO> hfEmpTaskInfoDTOList = new ArrayList<>(empCompanyIdTaskBOList.size());
//            for (EmpCompanyIdTaskBO empCompanyIdTaskBO : empCompanyIdTaskBOList) {
//                HfEmpTaskInfoDTO hfEmpTaskInfoDTO = new HfEmpTaskInfoDTO();
//                hfEmpTaskInfoDTO.setEmpTaskId(empCompanyIdTaskBO.getEmpTaskId());
//                hfEmpTaskInfoDTO.setCompanyId(empCompanyIdTaskBO.getCompanyId());
//                hfEmpTaskInfoDTO.setEmployeeId(empCompanyIdTaskBO.getEmployeeId());
//                hfEmpTaskInfoDTO.setInCity(commonApiUtils.getCityName(empCompanyIdTaskBO.getOldCityCode()));
//                hfEmpTaskInfoDTO.setAmount(empCompanyIdTaskBO.getAmount());
//                hfEmpTaskInfoDTO.setBase(empCompanyIdTaskBO.getEmpBase());
//                if (empCompanyIdTaskBO.getTaskCategory() != null) {
//                    hfEmpTaskInfoDTO.setChangeType(taskCategoryMap.get(empCompanyIdTaskBO.getTaskCategory().toString()));
//                }
//                if (empCompanyIdTaskBO.getTaskStatus() != null) {
//                    hfEmpTaskInfoDTO.setTaskStatus(taskStatusMap.get(empCompanyIdTaskBO.getTaskStatus().toString()));
//                }
//                hfEmpTaskInfoDTO.setRejectReason(empCompanyIdTaskBO.getRejectionRemark());
//                hfEmpTaskInfoDTO.setExecutionDate(DateUtil.yyyyMMCN(empCompanyIdTaskBO.getStartMonth()));
//                hfEmpTaskInfoDTO.setModifiedDate(DateUtil.yyyyMMCN(empCompanyIdTaskBO.getHfMonth()));
//                hfEmpTaskInfoDTO.setModifiedTime(DateUtil.yyyyMMddCN(empCompanyIdTaskBO.getModifiedTime()));
//                hfEmpTaskInfoDTOList.add(hfEmpTaskInfoDTO);
//            }
//            return JsonResult.success(hfEmpTaskInfoDTOList, "数据获取成功");
//        }
//        return JsonResult.faultMessage("支持中心反馈：无数据");
//    }
//
//    @Override
//    @ApiOperation(value = "获取雇员公积金任务单相关信息接口", notes = "雇员任务单Id")
//    @PostMapping("/getEmpDetailByEmpTaskId")
//    public JsonResult<HfEmpTaskDetailInfoDTO> getEmpDetailByEmpTaskId(Long empTaskId) {
//        EmpTaskDetailBO empTaskDetailBO = hfEmpTaskService.getEmpDetailByEmpTaskId(empTaskId, 0);
//        if (empTaskDetailBO == null) {
//            empTaskDetailBO = hfEmpTaskService.getEmpDetailByEmpTaskId(empTaskId, 1);
//        }
//        if (empTaskDetailBO != null) {
//            HfEmpTaskDetailInfoDTO hfEmpTaskDetailInfoDTO = new HfEmpTaskDetailInfoDTO();
//            hfEmpTaskDetailInfoDTO.setAccount(empTaskDetailBO.getHfEmpAccount());
//            hfEmpTaskDetailInfoDTO.setIdNum(empTaskDetailBO.getIdNum());
//            hfEmpTaskDetailInfoDTO.setAmount(empTaskDetailBO.getAmount());
//            if (empTaskDetailBO.getArchiveStatus() != null) {
//                hfEmpTaskDetailInfoDTO.setStatus(SocialSecurityConst.EMP_ARCHIVE_STATUS_MAP.get(empTaskDetailBO.getArchiveStatus().toString()));
//            }
//            hfEmpTaskDetailInfoDTO.setStartDate(DateUtil.yyyyMMCN(empTaskDetailBO.getStartMonth()));
//            hfEmpTaskDetailInfoDTO.setStopDate(DateUtil.yyyyMMCN(empTaskDetailBO.getEndMonth()));
//            hfEmpTaskDetailInfoDTO.setHandleDate(DateUtil.yyyyMMCN(empTaskDetailBO.getHfMonthHandle()));
//            hfEmpTaskDetailInfoDTO.setStopHandleDate(DateUtil.yyyyMMCN(empTaskDetailBO.getHfMonthStop()));
//            hfEmpTaskDetailInfoDTO.setRemark(empTaskDetailBO.getHandleRemark());
//            return JsonResult.success(hfEmpTaskDetailInfoDTO, "数据获取成功");
//        }
//
//        return JsonResult.faultMessage("支持中心反馈：无数据");
//    }

//    public JsonResult<HfEmpTaskInfoDTO> getEmpTaskInfo(Long empCompanyId, Integer hfType) {
//
//    }

    private HfEmpTask getEmpEndTask(String companyId, String employeeId, Integer hfType) {
        Wrapper<HfEmpTask> hfEmpTaskWrapper = new EntityWrapper<>();
        hfEmpTaskWrapper.where("is_active = 1");
        hfEmpTaskWrapper.and("company_id = {0}", companyId);
        hfEmpTaskWrapper.and("employee_id = {0}", employeeId);
        hfEmpTaskWrapper.and("hf_type = {0}", hfType);
        hfEmpTaskWrapper.and("task_status in (1, 4, 5)");
        hfEmpTaskWrapper.and("task_category in (4, 5, 12, 13)");
        hfEmpTaskWrapper.orderBy("created_time desc");
        hfEmpTaskWrapper.last("limit 1");

        return hfEmpTaskService.selectOne(hfEmpTaskWrapper);
    }

    private boolean checkHfParam(List<HfEmpInfoParamDTO> paramDTOList) {
        return paramDTOList != null ? true : false;
    }

    private synchronized void dictInit() throws BusinessException {
        if (taskCategoryMap == null) {
            try {
                List<DicItemDTO> dictItemList = commonApiUtils.listByDicId(DictUtil.DICT_ID_HF_LOCAL_TASK_CATEGORY);
                taskCategoryMap = new LinkedHashMap<>();
                dictItemList.stream().forEach((d) -> taskCategoryMap.put(d.getDicItemValue(), d.getDicItemText()));
            } catch (Exception e) {
                throw new BusinessException("支持中心反馈：获取字典数据失败");
            }
        }

        if (taskStatusMap == null) {
            try {
                List<DicItemDTO> dictItemList = commonApiUtils.listByDicId(DictUtil.DICT_ID_TASK_PROCESS_STATUS);
                taskStatusMap = new LinkedHashMap<>();
                dictItemList.stream().forEach((d) -> taskStatusMap.put(d.getDicItemValue(), d.getDicItemText()));
            } catch (Exception e) {
                throw new BusinessException("支持中心反馈：获取字典数据失败");
            }
        }
    }
}
