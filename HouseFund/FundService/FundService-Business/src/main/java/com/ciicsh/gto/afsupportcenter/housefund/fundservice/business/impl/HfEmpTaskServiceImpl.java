package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpAgreementDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountTransBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.SsHfAutoOffsetFailMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTaskChgRelation;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.SsHfAutoOffsetFail;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.enumeration.ProcessCategory;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.sheetservice.api.dto.Result;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 雇员任务单总表 服务实现类
 * </p>
 */
@Service
public class HfEmpTaskServiceImpl extends ServiceImpl<HfEmpTaskMapper, HfEmpTask> implements HfEmpTaskService {
    @Autowired
    private LogApiUtil logApiUtil;
    @Autowired
    private HfComAccountService hfComAccountService;
    @Autowired
    private HfEmpTaskHandleService hfEmpTaskHandleService;
    @Autowired
    private HfEmpArchiveService hfEmpArchiveService;
    @Autowired
    private HfEmpTaskChgRelationService hfEmpTaskChgRelationService;
    @Autowired
    private SsHfAutoOffsetFailMapper ssHfAutoOffsetFailMapper;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    private static Integer[] SUSPEND_TASK_CATEGORIES = new Integer[] {
        HfEmpTaskConstant.TASK_CATEGORY_IN_ADD,
        HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN,
        HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN,
        HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD,
        HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN,
        HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN,
        HfEmpTaskConstant.TASK_CATEGORY_TRANSFER_TASK,
        SocialSecurityConst.TASK_CATEGORY_NO_HANDLE
    };

    @Override
    public PageRows<HfEmpTaskExportBo> queryHfEmpTaskInPage(PageInfo pageInfo, String userId) {
        return queryHfEmpTaskInPage(pageInfo, userId, null);
    }

    @Override
    public PageRows<HfEmpTaskExportBo> queryHfEmpTaskInPage(PageInfo pageInfo, String userId, String exceptTaskCategories) {
        HfEmpTaskBo hfEmpTaskBo = pageInfo.toJavaObject(HfEmpTaskBo.class);
        hfEmpTaskBo.setUserId(userId);
        if (StringUtils.isNotBlank(exceptTaskCategories)) {
            hfEmpTaskBo.setExceptTaskCategories(exceptTaskCategories);
        }
        if( !StringUtil.isEmpty(hfEmpTaskBo.getEmpTaskIds()) ){
            String arr[] = hfEmpTaskBo.getEmpTaskIds().split(",");
            List<String> empTaskIdsList=new ArrayList<>() ;
            for (String dd : arr) {
                empTaskIdsList.add(dd);
            }
            hfEmpTaskBo.setEmpTaskIdsList(empTaskIdsList);
        }

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();

        if (!StringUtil.isEmpty(hfEmpTaskBo.getParams())) {
            String arr[] = hfEmpTaskBo.getParams().split(";");
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].indexOf("processStatus")!=-1){
                    String str[] = arr[i].split(" ");
                    String regexp = "\'";
                    String status = str[2].replaceAll(regexp, "");
                    hfEmpTaskBo.setProcessStatus(Integer.parseInt(status));
                }else if(arr[i].indexOf("preInput")!=-1) {
                    String str[] = arr[i].split(" ");
                    String regexp = "\'";
                    String preInput = str[2].replaceAll(regexp, "");
                    hfEmpTaskBo.setPreInput(Integer.parseInt(preInput));
                }else{

                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        if(arr[i].indexOf("hf_account_type")!=-1)
                        {
                            String str[] = arr[i].split(" ");
                            String regexp = "\'";
                            String status = str[2].replaceAll(regexp, "");
                            if ("null".equals(status)) {
                                status = "0";
                            }
                            hfEmpTaskBo.setHfAccountType(0);
                        }
                        if(arr[i].indexOf("payment_bank")!=-1)
                        {
                            String str[] = arr[i].split(" ");
                            String regexp = "\'";
                            String status = str[2].replaceAll(regexp, "");
                            if ("null".equals(status)) {
                                status = "0";
                            }
                            hfEmpTaskBo.setPaymentBank(0);
                        }
                        if(arr[i].indexOf("hf_com_account")!=-1)
                        {
                            String str[] = arr[i].split(" ");
                            String regexp = "\'";
                            String status = str[2].replaceAll(regexp, "");
                            if ("null".equals(status)) {
                                status = "0";
                            }
                            hfEmpTaskBo.setHfComAccount(status);
                        }
                        if (arr[i].indexOf("af_ec.status")!=-1) {
                            String str[] = arr[i].split(" ");
                            String regexp = "\'";
                            String status = str[2].replaceAll(regexp, "");
                            if ("0".equals(status)) {
                                status = "!=3";
                            } else if ("1".equals(status)) {
                                status = "=3";
                            }
                            arr[i] = "IFNULL(" + str[0] + ", 0)" + status;
                        }

                        param.add(arr[i]);
                    }

                }

            }
        }

        hfEmpTaskBo.setParam(param);
        hfEmpTaskBo.setOrderParam(orderParam);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryHfEmpTask(hfEmpTaskBo));
    }

    @Override
    public PageRows<HfEmpTaskRejectExportBo> queryHfEmpTaskRejectInPage(PageInfo pageInfo, String userId, String exceptTaskCategories) {
        HfEmpTaskBo hfEmpTaskBo = pageInfo.toJavaObject(HfEmpTaskBo.class);
        hfEmpTaskBo.setUserId(userId);
        if (StringUtils.isNotBlank(exceptTaskCategories)) {
            hfEmpTaskBo.setExceptTaskCategories(exceptTaskCategories);
        }

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();

        if (!StringUtil.isEmpty(hfEmpTaskBo.getParams())) {
            String arr[] = hfEmpTaskBo.getParams().split(";");
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].indexOf("processStatus")!=-1){
                    String str[] = arr[i].split(" ");
                    String regexp = "\'";
                    String status = str[2].replaceAll(regexp, "");
                    hfEmpTaskBo.setProcessStatus(Integer.parseInt(status));
                }else if(arr[i].indexOf("preInput")!=-1){
                    String str[] = arr[i].split(" ");
                    String regexp = "\'";
                    String preInput = str[2].replaceAll(regexp, "");
                    hfEmpTaskBo.setPreInput(Integer.parseInt(preInput));
                }else{

                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        if (arr[i].indexOf("desc") > 0 || arr[i].indexOf("asc") > 0) {
                            orderParam.add(arr[i]);
                        } else {
                            if (arr[i].indexOf("hf_account_type") != -1) {
                                String str[] = arr[i].split(" ");
                                String regexp = "\'";
                                String status = str[2].replaceAll(regexp, "");
                                if ("null".equals(status)) {
                                    status = "0";
                                }
                                hfEmpTaskBo.setHfAccountType(0);
                            }
                            if (arr[i].indexOf("payment_bank") != -1) {
                                String str[] = arr[i].split(" ");
                                String regexp = "\'";
                                String status = str[2].replaceAll(regexp, "");
                                if ("null".equals(status)) {
                                    status = "0";
                                }
                                hfEmpTaskBo.setPaymentBank(0);
                            }
                            if (arr[i].indexOf("hf_com_account") != -1) {
                                String str[] = arr[i].split(" ");
                                String regexp = "\'";
                                String status = str[2].replaceAll(regexp, "");
                                if ("null".equals(status)) {
                                    status = "0";
                                }
                                hfEmpTaskBo.setHfComAccount(status);
                            }
                            if (arr[i].indexOf("af_ec.status")!=-1) {
                                String str[] = arr[i].split(" ");
                                String regexp = "\'";
                                String status = str[2].replaceAll(regexp, "");
                                if ("0".equals(status)) {
                                    status = "!=3";
                                } else if ("1".equals(status)) {
                                    status = "=3";
                                }
                                arr[i] = "IFNULL(" + str[0] + ", 0)" + status;
                            }

                            param.add(arr[i]);
                        }
                    }
                }
            }
        }

        hfEmpTaskBo.setParam(param);
        hfEmpTaskBo.setOrderParam(orderParam);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryHfEmpTaskReject(hfEmpTaskBo));
    }

    /**
     * 查询任务单信息
     * @param hfEmpTask
     */
    @Override
    public List<HfEmpTask> queryByTaskId(HfEmpTask hfEmpTask) {
        return baseMapper.queryByTaskId(hfEmpTask);
    }

    /**
     * 添加数据到雇员任务单表
     * @param taskMsgDTO
     * @param fundCategory
     * @param taskCategory
     * @param isChange
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean addEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, Integer processCategory,Integer taskCategory, String oldAgreementId, Integer isChange,
                              Map<String, Object> cityCodeMap, AfEmployeeInfoDTO dto, AfEmpSocialDTO socialDTO, AfCompanyDetailResponseDTO afCompanyDetailResponseDTO) throws Exception {
        AfEmployeeCompanyDTO companyDto = dto.getEmployeeCompany();
        AfEmpAgreementDTO afEmpAgreementDTO = dto.getNowAgreement();
        HfEmpTask hfEmpTask = new HfEmpTask();
        hfEmpTask.setTaskId(taskMsgDTO.getTaskId());

        if (cityCodeMap.get("oldAgreementId") != null) {
            hfEmpTask.setBusinessInterfaceId(cityCodeMap.get("oldAgreementId").toString());
        } else {
            hfEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        }

        // 调整通道或更正通道过来的任务单，都需要加上oldAgreementId，回调前道接口时需使用
        if (oldAgreementId != null) {
            hfEmpTask.setOldAgreementId(oldAgreementId);
        }

        if (cityCodeMap != null) {
            if (cityCodeMap.get("oldFundCityCode") != null) {
                hfEmpTask.setOldCityCode(cityCodeMap.get("oldFundCityCode").toString());
            }

            if (cityCodeMap.get("newFundCityCode") != null) {
                hfEmpTask.setNewCityCode(cityCodeMap.get("newFundCityCode").toString());
            }
        }

        if(null != companyDto){
            hfEmpTask.setCompanyId(companyDto.getCompanyId());
            hfEmpTask.setEmployeeId(companyDto.getEmployeeId());
            hfEmpTask.setEmpCompanyId(companyDto.getEmpCompanyId());
            hfEmpTask.setSubmitterId(afEmpAgreementDTO.getCreatedBy());
            hfEmpTask.setSubmitterRemark(companyDto.getRemark());
            //福利办理方
            hfEmpTask.setWelfareUnit(companyDto.getFundUnit());
            if (companyDto.getInDate() != null) {
                hfEmpTask.setInDate(LocalDateTime.ofInstant(companyDto.getInDate().toInstant(), ZoneId.systemDefault()));
            }
            if(null != companyDto.getOutDate()){
                hfEmpTask.setOutDate(LocalDateTime.ofInstant(companyDto.getOutDate().toInstant(), ZoneId.systemDefault()));
            }
            hfEmpTask.setCreatedBy(afEmpAgreementDTO.getCreatedBy() != null ? afEmpAgreementDTO.getCreatedBy() : "system");
            hfEmpTask.setCreatedDisplayName(afEmpAgreementDTO.getCreatedDisplayName());
            hfEmpTask.setModifiedBy(afEmpAgreementDTO.getCreatedBy() != null ? afEmpAgreementDTO.getCreatedBy() : "system");
            hfEmpTask.setModifiedDisplayName(afEmpAgreementDTO.getCreatedDisplayName());
            hfEmpTask.setLeaderShipId(companyDto.getLeadershipId() != null ? companyDto.getLeadershipId() : "system");
            hfEmpTask.setLeaderShipName(companyDto.getLeadershipName() != null ? companyDto.getLeadershipName() : "system");

            if (afCompanyDetailResponseDTO != null) {
                hfEmpTask.setServiceCenterId(afCompanyDetailResponseDTO.getServiceCenterId());
                hfEmpTask.setServiceCenter(afCompanyDetailResponseDTO.getServiceCenter());
            }
        }
        hfEmpTask.setSubmitTime(LocalDate.now());
        Map<String, Object> paramMap = taskMsgDTO.getVariables();
        //转出单位(来源地)
        hfEmpTask.setTransferOutUnit(this.getTransUnit(paramMap));
        hfEmpTask.setProcessCategory(processCategory);
        //任务类型
        hfEmpTask.setTaskCategory(taskCategory);

        hfEmpTask.setOperationType((String)paramMap.get("operation_type"));

        //是否更正 1 是 0 否
        hfEmpTask.setIsChange(isChange);
        hfEmpTask.setTaskFormContent(JSON.toJSONString(dto));

        //前道传递的政策明细ID,用它调用系统中心获取进位方式
        if (dto.getNowAgreement() != null && dto.getNowAgreement().getFundPolicyId() != null) {
            hfEmpTask.setPolicyDetailId(dto.getNowAgreement().getFundPolicyId());
        }

        //办理状态：1、未处理 2 、处理中(已办)  3 已完成(已做) 4、批退 5、不需处理
        hfEmpTask.setTaskStatus(1);
        //入职日期

        hfEmpTask.setActive(true);


        hfEmpTask.setModifiedTime(LocalDateTime.now());
        hfEmpTask.setCreatedTime(LocalDateTime.now());
        hfEmpTask.setAmount(new BigDecimal(0));
        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();
        if(null != socialList && socialList.size() > 0){
            this.setEmpTask(hfEmpTask,socialDTO,fundCategory);

            // 调整或更正的转出或封存时
            if (oldAgreementId != null && (
                    ProcessCategory.AF_EMP_AGREEMENT_ADJUST.getCategory().equals(processCategory)
                        || ProcessCategory.AF_EMP_AGREEMENT_UPDATE.getCategory().equals(processCategory)
                ) && (
                    HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT == taskCategory
                        || HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE == taskCategory
            )) {
                // 非0转0不是常规意义的停办，是一种调整任务，所以没有截止年月，此处需特别处理
                if (StringUtils.isEmpty(hfEmpTask.getEndMonth()) && StringUtils.isNotEmpty(hfEmpTask.getStartMonth())) {
                    YearMonth startMonthDate = YearMonth.parse(hfEmpTask.getStartMonth(), formatter);
                    hfEmpTask.setEndMonth(startMonthDate.minusMonths(1).format(formatter));
                    hfEmpTask.setStartMonth(null);
                }
            }
        }

        //公积金类型:1 基本 2 补充
        Integer hfType = fundCategory.equals(DictUtil.DICT_ITEM_ID_FUND_BASIC) ? 1 : 2;
        hfEmpTask.setHfType(hfType);

        // 重复任务单校验
        Wrapper<HfEmpTask> hfEmpTaskWrapper = new EntityWrapper<>();
        hfEmpTaskWrapper.where("(is_active = 1 OR (is_active = 0 AND is_suspended = 1))");
        hfEmpTaskWrapper.and("business_interface_id = {0}", hfEmpTask.getBusinessInterfaceId());
        hfEmpTaskWrapper.and("task_id = {0}", hfEmpTask.getTaskId());
        hfEmpTaskWrapper.and("is_change = {0}", hfEmpTask.getIsChange());
        List<HfEmpTask> hfEmpTaskList = baseMapper.selectList(hfEmpTaskWrapper);
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("business_interface_id", hfEmpTask.getBusinessInterfaceId());
//        condition.put("task_id", hfEmpTask.getTaskId());
//        condition.put("is_change", hfEmpTask.getIsChange());
//        condition.put("is_active", 1);
//        List<HfEmpTask> hfEmpTaskList = baseMapper.selectByMap(condition);

        if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
            logApiUtil.warn(LogMessage.create().setTitle("HfEmpTaskServiceImpl#addEmpTask")
                .setContent("任务单幂等校验未通过。business_interface_id=" + String.valueOf(hfEmpTask.getBusinessInterfaceId())
                    + ", task_id=" + String.valueOf(hfEmpTask.getTaskId())
                    + ", is_change=" + String.valueOf(hfEmpTask.getIsChange())));
            hfEmpTask.setActive(false);
        } else if (!ArrayUtils.contains(SUSPEND_TASK_CATEGORIES, hfEmpTask.getTaskCategory())) {
            // 判断是否需要暂存
            Wrapper<HfEmpArchive> hfEmpArchiveWrapper = new EntityWrapper<>();
            hfEmpArchiveWrapper.where("is_active = 1");
            hfEmpArchiveWrapper.and("archive_status < 3");
            hfEmpArchiveWrapper.and("hf_type = {0}", hfEmpTask.getHfType());
            hfEmpArchiveWrapper.and("company_id = {0}", hfEmpTask.getCompanyId());
            hfEmpArchiveWrapper.and("employee_id = {0}", hfEmpTask.getEmployeeId());
            List<HfEmpArchive> hfEmpArchiveList = hfEmpArchiveService.selectList(hfEmpArchiveWrapper);

            // 未转出的雇员档案不存在
            if (CollectionUtils.isEmpty(hfEmpArchiveList)) {
                hfEmpTaskWrapper = new EntityWrapper<>();
                hfEmpTaskWrapper.where("is_active = 1");
                hfEmpTaskWrapper.and("hf_type = {0}", hfEmpTask.getHfType());
                hfEmpTaskWrapper.and("company_id = {0}", hfEmpTask.getCompanyId());
                hfEmpTaskWrapper.and("employee_id = {0}", hfEmpTask.getEmployeeId());
                hfEmpTaskWrapper.and("task_category in (1,2,3,9,10,11,99)");
                hfEmpTaskList = baseMapper.selectList(hfEmpTaskWrapper);

                // 且新增类任务单(含不做)未收到
                if (CollectionUtils.isEmpty(hfEmpTaskList)) {
                    // 此时非新增类任务单需暂存
                    hfEmpTask.setActive(false);
                    hfEmpTask.setSuspended(true);
                    logApiUtil.info(LogMessage.create().setTitle("HfEmpTaskServiceImpl#addEmpTask")
                        .setContent("任务单暂存。hf_type=" + String.valueOf(hfEmpTask.getHfType())
                            + ", company_id=" + String.valueOf(hfEmpTask.getCompanyId())
                            + ", employee_id=" + String.valueOf(hfEmpTask.getEmployeeId())
                        ));
                }
            }
        }

        baseMapper.insert(hfEmpTask);

        // 如果是取消入职，则不自动发转移任务单
        if (hfEmpTask.getOperationType() == null) {
            if (hfEmpTask.getActive() || hfEmpTask.getSuspended()) {
                // 转出或封存任务单，同时生成转移任务单
                createTransferTask(hfEmpTask, null);
            }
        }

        if (hfEmpTask.getActive()) {
            if (!hfEmpTask.getTaskCategory().equals(HfEmpTaskConstant.TASK_CATEGORY_TRANSFER_TASK) &&
                ArrayUtils.contains(SUSPEND_TASK_CATEGORIES, hfEmpTask.getTaskCategory())
                ) {
                // 恢复暂存任务单
                hfEmpTaskWrapper = new EntityWrapper<>();
                hfEmpTaskWrapper.where("is_active = 0");
                hfEmpTaskWrapper.and("is_suspended = 1");
                hfEmpTaskWrapper.and("hf_type = {0}", hfEmpTask.getHfType());
                hfEmpTaskWrapper.and("company_id = {0}", hfEmpTask.getCompanyId());
                hfEmpTaskWrapper.and("employee_id = {0}", hfEmpTask.getEmployeeId());
                hfEmpTaskWrapper.and("task_category in (4,5,6,7,8,12,13)");
                HfEmpTask updateHfEmpTask = new HfEmpTask();
                updateHfEmpTask.setSuspended(false);
                updateHfEmpTask.setActive(true);
                int rtn = baseMapper.update(updateHfEmpTask, hfEmpTaskWrapper);
                if (rtn > 0) {
                    logApiUtil.info(LogMessage.create().setTitle("HfEmpTaskServiceImpl#addEmpTask")
                        .setContent("任务单恢复暂存。hf_type=" + String.valueOf(hfEmpTask.getHfType())
                            + ", company_id=" + String.valueOf(hfEmpTask.getCompanyId())
                            + ", employee_id=" + String.valueOf(hfEmpTask.getEmployeeId())
                        ));
                }
            }
        }

        Long preEmpTaskId = (Long)cityCodeMap.get("preEmpTaskId");
        if (preEmpTaskId != null && (hfEmpTask.getActive() || hfEmpTask.getSuspended())) {
            HfEmpTaskChgRelation hfEmpTaskChgRelation = new HfEmpTaskChgRelation();
            hfEmpTaskChgRelation.setCompanyId(hfEmpTask.getCompanyId());
            hfEmpTaskChgRelation.setEmployeeId(hfEmpTask.getEmployeeId());
            hfEmpTaskChgRelation.setEmpTaskId(hfEmpTask.getEmpTaskId());
            hfEmpTaskChgRelation.setPreEmpTaskId(preEmpTaskId);
            hfEmpTaskChgRelation.setCreatedBy(afEmpAgreementDTO.getCreatedBy());
            hfEmpTaskChgRelationService.insert(hfEmpTaskChgRelation);
        }
        return true;
    }

    /**
     * 修改数据到雇员任务单表
     * @param taskMsgDTO 消息队列接受的对象
     * @param fundCategory 公积金类别（基本或者补充公积金）
     * @param dto        取得的雇员信息
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, AfEmployeeInfoDTO dto, AfEmpSocialDTO socialDTO) {

        Map<String, Object> paramMap = taskMsgDTO.getVariables();
        AfEmployeeCompanyDTO companyDto = dto.getEmployeeCompany();
        AfEmpAgreementDTO afEmpAgreementDTO = dto.getNowAgreement();
        HfEmpTask hfEmpTask = new HfEmpTask();
        hfEmpTask.setTaskId(paramMap.get("oldTaskId").toString());

        // 调整通道或更正通道过来的任务单（目前只有更正通道调用该方法），都需要加上oldAgreementId，回调前道接口时需使用
        if (paramMap.get("oldAgreementId") != null) {
            hfEmpTask.setOldAgreementId(paramMap.get("oldAgreementId").toString());
        }

        //查询旧的任务类型保存到新的任务单
        hfEmpTask = baseMapper.selectOne(hfEmpTask);

        if(null != companyDto){
            hfEmpTask.setCompanyId(companyDto.getCompanyId());
            hfEmpTask.setEmployeeId(companyDto.getEmployeeId());
            hfEmpTask.setEmpCompanyId(companyDto.getEmpCompanyId());
            hfEmpTask.setSubmitterId(afEmpAgreementDTO.getCreatedBy());
            hfEmpTask.setSubmitterRemark(companyDto.getRemark());
            //福利办理方
            hfEmpTask.setWelfareUnit(companyDto.getFundUnit());
            if (companyDto.getInDate() != null) {
                hfEmpTask.setInDate(LocalDateTime.ofInstant(companyDto.getInDate().toInstant(), ZoneId.systemDefault()));
            }
            if(null != companyDto.getOutDate()){
                hfEmpTask.setOutDate(LocalDateTime.ofInstant(companyDto.getOutDate().toInstant(), ZoneId.systemDefault()));
            }
            hfEmpTask.setModifiedBy(afEmpAgreementDTO.getModifiedBy() != null ? afEmpAgreementDTO.getModifiedBy() : "system");
            hfEmpTask.setModifiedDisplayName(afEmpAgreementDTO.getModifiedDisplayName());
            hfEmpTask.setLeaderShipId(companyDto.getLeadershipId() != null ? companyDto.getLeadershipId() : "system");
            hfEmpTask.setLeaderShipName(companyDto.getLeadershipName() != null ? companyDto.getLeadershipName() : "system");
        }

        hfEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        hfEmpTask.setTaskFormContent(JSON.toJSONString(dto));
        //转出单位(来源地)
        hfEmpTask.setTransferOutUnit(this.getTransUnit(paramMap));
        //前道传递的政策明细ID,用它调用系统中心获取进位方式
        if (dto.getNowAgreement() != null && dto.getNowAgreement().getFundPolicyId() != null) {
            hfEmpTask.setPolicyDetailId(dto.getNowAgreement().getFundPolicyId());
        }

        hfEmpTask.setModifiedTime(LocalDateTime.now());
        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();
        if(null != socialList && socialList.size() > 0){
            this.setEmpTask(hfEmpTask,socialDTO,fundCategory);

            // 调整或更正的转出或封存时
            if (paramMap.get("oldAgreementId") != null && (
                ProcessCategory.AF_EMP_AGREEMENT_ADJUST.getCategory().equals(hfEmpTask.getProcessCategory())
                    || ProcessCategory.AF_EMP_AGREEMENT_UPDATE.getCategory().equals(hfEmpTask.getProcessCategory())
            ) && (
                HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT == hfEmpTask.getTaskCategory()
                    || HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE == hfEmpTask.getTaskCategory()
            )) {
                // 非0转0不是常规意义的停办，是一种调整任务，所以没有截止年月，此处需特别处理
                if (StringUtils.isEmpty(hfEmpTask.getEndMonth()) && StringUtils.isNotEmpty(hfEmpTask.getStartMonth())) {
                    YearMonth startMonthDate = YearMonth.parse(hfEmpTask.getStartMonth(), formatter);
                    hfEmpTask.setEndMonth(startMonthDate.minusMonths(1).format(formatter));
                    hfEmpTask.setStartMonth(null);
                }
            }
        }
        baseMapper.updateById(hfEmpTask);

        return true;
    }


    /**
     * 设置EmpTask的值
     * @param empTask
     * @param socialDTO
     * @param fundCategory
     */
    private void setEmpTask(HfEmpTask empTask,AfEmpSocialDTO socialDTO,String fundCategory){
//        AfEmpSocialDTO socialDTO = this.getAfEmpSocialByType(socialDTOS,fundCategory);
        if(null != socialDTO){
            empTask.setEmpBase(socialDTO.getPersonalBase());
            if(null != socialDTO.getStartDate()){
                empTask.setStartMonth(StringUtil.dateToString(socialDTO.getStartDate(),"yyyyMM"));
            }
            if(null != socialDTO.getEndDate()){
                empTask.setEndMonth(StringUtil.dateToString(socialDTO.getEndDate(),"yyyyMM"));
            }
            if(null != socialDTO.getTotal()){
                empTask.setAmount(socialDTO.getTotal());
            }
            if(null != socialDTO.getPersonalRatio()){
                empTask.setRatioEmp(socialDTO.getPersonalRatio());
            }
            if(null != socialDTO.getPersonalRatio()){
                empTask.setRatioCom(socialDTO.getCompanyRatio());
            }
            if(null != socialDTO.getAccount()){
                empTask.setHfEmpAccount(socialDTO.getAccount());
            }
        }
    }

    /**
     * 根据公积金类型获取AfEmpSocialDTO
     * @param socialDTOS
     * @param fundCategory
     * @return
     */
    public AfEmpSocialDTO getAfEmpSocialByType(List<AfEmpSocialDTO> socialDTOS,String fundCategory){
        AfEmpSocialDTO socialDTO = null;
        List<AfEmpSocialDTO> fundInfos = socialDTOS
            .stream()
            .filter(x->null != x.getPolicyType() && x.getPolicyType().equals(2) && x.getItemCode().equals(fundCategory))
            .collect(Collectors.toList());
        if(null != fundInfos && fundInfos.size() > 0){
            socialDTO = fundInfos.get(0);
        }
        return socialDTO;
    }

    @Override
    public Integer getExistHandleRemarkCount(HfEmpTaskBo hfEmpTaskBo) {
        return baseMapper.getExistHandleRemarkCount(hfEmpTaskBo);
    }

    /**
     * 转出或封存（翻牌转出或翻牌封存）类型的任务单办理完成自动生成转移任务单
     *
     * @param inputHfEmpTask 任务单表数据
     * @param comAccountId   企业账户ID
     */
    @Override
    public void createTransferTask(HfEmpTask inputHfEmpTask, Long comAccountId) {
        HfEmpTaskCreateTransBo hfEmpTaskCreateTransBo;
        ComAccountTransBo comAccountTransBo;

        switch (inputHfEmpTask.getTaskCategory()) {
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_OUT:
                hfEmpTaskCreateTransBo = new HfEmpTaskCreateTransBo();
                hfEmpTaskCreateTransBo.setEmpTaskId(inputHfEmpTask.getEmpTaskId());
                comAccountTransBo = new ComAccountTransBo();
                List<ComAccountTransBo> comAccountTransBoList;

                // 如果转出或封存时，转入单位统一为市公积金中心
                if (inputHfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE
                    || inputHfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT) {
                    String transferInUnit = SocialSecurityConst.FUND_OUT_UNIT_LIST.get(1); //市公积金中心单位名称
                    hfEmpTaskCreateTransBo.setTransferInUnit(transferInUnit);
                    String transferInUnitAccount =
                        (inputHfEmpTask.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) ? SocialSecurityConst.CENTER_BASIC_COM_ACCOUNT
                            : SocialSecurityConst.CENTER_ADDED_COM_ACCOUNT;
                    hfEmpTaskCreateTransBo.setTransferInUnitAccount(transferInUnitAccount);
                }
                comAccountTransBo.setHfType(inputHfEmpTask.getHfType());
                if (comAccountId != null) {
                    comAccountTransBo.setComAccountId(comAccountId);
                } else {
                    comAccountTransBo.setWelfareUnit(inputHfEmpTask.getWelfareUnit());
                    comAccountTransBo.setCompanyId(inputHfEmpTask.getCompanyId());
                }
                comAccountTransBoList = hfComAccountService.queryComAccountTransBoList(comAccountTransBo);
                if (CollectionUtils.isNotEmpty(comAccountTransBoList)) {
                    if (comAccountTransBoList.size() > 1) {
                        String hfTypeName = (comAccountTransBo.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) ? "基本公积金" : "补充公积金";
                        throw new BusinessException("转出单位的" + hfTypeName + "账户存在重复数据");
                    }
                    hfEmpTaskCreateTransBo.setTransferOutUnit(comAccountTransBoList.get(0).getComAccountName());
                    hfEmpTaskCreateTransBo.setTransferOutUnitAccount(comAccountTransBoList.get(0).getHfComAccount());
                }
                hfEmpTaskCreateTransBo.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_UNHANDLED);
                hfEmpTaskCreateTransBo.setModifiedBy(inputHfEmpTask.getModifiedBy());
                hfEmpTaskCreateTransBo.setModifiedDisplayName(inputHfEmpTask.getModifiedDisplayName());
                baseMapper.createTransEmpTask(hfEmpTaskCreateTransBo);
                break;
//            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN:
//            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN:
//                // 翻牌转入或翻牌启封时
//                Map<String, Object> condition = new HashMap<>();
//                hfEmpTaskCreateTransBo = new HfEmpTaskCreateTransBo();
//                condition.put("employee_id", hfEmpTaskCreateTransBo.getEmployeeId());
//                condition.put("task_category", HfEmpTaskConstant.TASK_CATEGORY_TRANSFER_TASK);
//                condition.put("hf_type", hfEmpTaskCreateTransBo.getHfType());
//                condition.put("task_status", HfEmpTaskConstant.TASK_STATUS_UNHANDLED);
//                condition.put("business_interface_id", inputHfEmpTask.getBusinessInterfaceId());
//                condition.put("is_active", 1);
//                List<HfEmpTask> hfEmpTaskList = selectByMap(condition);
//
//                // 判断相应的翻牌转出或翻牌封存办理时，生成的转移任务单是否存在
//                if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
//                    int size = hfEmpTaskList.size();
//                    if (size > 0) {
//                        HfEmpTask transferTask = hfEmpTaskList.get(size - 1);
//                        HfEmpTask updateTransferTask = new HfEmpTask();
//                        comAccountTransBo = new ComAccountTransBo();
//                        comAccountTransBo.setHfType(inputHfEmpTask.getHfType());
//                        comAccountTransBo.setComAccountId(comAccountId);
//                        comAccountTransBoList = hfComAccountService.queryComAccountTransBoList(comAccountTransBo);
//                        if (CollectionUtils.isNotEmpty(comAccountTransBoList)) {
//                            if (comAccountTransBoList.size() > 1) {
//                                String hfTypeName = (comAccountTransBo.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) ? "基本公积金" : "补充公积金";
//                                throw new BusinessException("转入单位的" + hfTypeName + "账户存在重复数据");
//                            }
//                            updateTransferTask.setTransferInUnit(comAccountTransBoList.get(0).getComAccountName());
//                            updateTransferTask.setTransferInUnitAccount(comAccountTransBoList.get(0).getHfComAccount());
//                        }
//                        updateTransferTask.setEmpTaskId(transferTask.getEmpTaskId());
//                        updateTransferTask.setModifiedBy(UserContext.getUserId());
//                        updateTransferTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
//                        updateById(updateTransferTask);
//                    }
//                }
//                break;
            default:
                break;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean autoOffset(String companyId, String employeeId, Integer hfType, Integer offsetType) {
        Wrapper<HfEmpTask> hfEmpTaskWrapper = new EntityWrapper<>();
        hfEmpTaskWrapper.where("is_active = 1");
        hfEmpTaskWrapper.and("company_id = {0}", companyId);
        hfEmpTaskWrapper.and("employee_id = {0}", employeeId);
        hfEmpTaskWrapper.and("hf_type = {0}", hfType);
        hfEmpTaskWrapper.and("task_status = 1");
        hfEmpTaskWrapper.and("modified_time = created_time");

        if (offsetType == 1) {
            hfEmpTaskWrapper.and("task_category = {0}", SocialSecurityConst.TASK_CATEGORY_NO_HANDLE);
        } else if (offsetType == 2) {
            hfEmpTaskWrapper.and("task_category in (1, 2, 3, 9, 10, 11)");
        } else {
            hfEmpTaskWrapper.and("task_category in (4, 5, 12, 13)");
        }
        List<HfEmpTask> hfEmpTaskList = this.selectList(hfEmpTaskWrapper);

        // 收到不做类型任务单(或入离职抵消任务单)
        if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
            hfEmpTaskWrapper = new EntityWrapper<>();
            hfEmpTaskWrapper.where("(is_active = 1 OR (is_active = 0 AND is_suspended = 1))");
            hfEmpTaskWrapper.and("company_id = {0}", companyId);
            hfEmpTaskWrapper.and("employee_id = {0}", employeeId);
            hfEmpTaskWrapper.and("hf_type = {0}", hfType);
            hfEmpTaskWrapper.and("task_status = 1");
            hfEmpTaskWrapper.and("modified_time = created_time");

            if (offsetType == 1) {
                hfEmpTaskWrapper.and("task_category in (4, 5, 12, 13)");
            } else if (offsetType == 2) {
                hfEmpTaskWrapper.and("task_category in (4, 5, 12, 13)");
                hfEmpTaskWrapper.and("operation_type = 'emp_in_cancel'");
            } else {
                hfEmpTaskWrapper.and("task_category in (1, 2, 3, 9, 10, 11)");
                hfEmpTaskWrapper.and("operation_type = 'emp_out_cancel'");
            }

            List<HfEmpTask> outHfEmpTaskList = this.selectList(hfEmpTaskWrapper);

            // 且收到停办类任务单
            if (CollectionUtils.isNotEmpty(outHfEmpTaskList)
                && hfEmpTaskList.size() == 1 && outHfEmpTaskList.size() == 1
                ) {
                HfEmpTask inHfEmpTask;
                HfEmpTask outHfEmpTask;

                if (offsetType == 3) {
                    inHfEmpTask = outHfEmpTaskList.get(0);
                    outHfEmpTask = hfEmpTaskList.get(0);
                } else {
                    inHfEmpTask = hfEmpTaskList.get(0);
                    outHfEmpTask = outHfEmpTaskList.get(0);
                }

                // 停办年月小于新增年月
                if (offsetType == 3 || DateUtil.compareMonth(inHfEmpTask.getStartMonth(), outHfEmpTask.getEndMonth()) > 0) {
                    hfEmpTaskWrapper = new EntityWrapper<>();
                    hfEmpTaskWrapper.where("(is_active = 1 OR (is_active = 0 AND is_suspended = 1))");
                    hfEmpTaskWrapper.and("company_id = {0}", companyId);
                    hfEmpTaskWrapper.and("employee_id = {0}", employeeId);
                    hfEmpTaskWrapper.and("hf_type = {0}", hfType);
                    hfEmpTaskWrapper.and("task_status = 1");
                    hfEmpTaskWrapper.and("task_category in (6, 7)");
                    int otherCnt = this.selectCount(hfEmpTaskWrapper);

                    // 且不存在其他类型任务单（批退，或不需处理除外）
                    if (otherCnt == 0) {
                        // 新增类任务单批退，回调任务单完成接口和实际金额回调接口
                        HfEmpTaskBatchRejectBo hfEmpTaskBatchRejectBo = new HfEmpTaskBatchRejectBo();
                        hfEmpTaskBatchRejectBo.setSelectedData(new Long[]{inHfEmpTask.getEmpTaskId()});

                        if (offsetType == 1) {
                            hfEmpTaskBatchRejectBo.setRejectionRemark("不做，自动抵消");
                        } else {
                            hfEmpTaskBatchRejectBo.setRejectionRemark("入离职，自动抵消");
                        }
                        hfEmpTaskBatchRejectBo.setModifiedBy(SocialSecurityConst.SYSTEM_USER);
                        hfEmpTaskBatchRejectBo.setModifiedDisplayName(SocialSecurityConst.SYSTEM_USER);
                        hfEmpTaskHandleService.handleReject(hfEmpTaskBatchRejectBo);

                        // 停办类任务单批退，仅回调任务单完成接口
                        HfEmpTask updateHfEmpTask = new HfEmpTask();
                        updateHfEmpTask.setEmpTaskId(outHfEmpTask.getEmpTaskId());
                        updateHfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_REJECTED);
                        updateHfEmpTask.setRejectionRemark(hfEmpTaskBatchRejectBo.getRejectionRemark());
                        updateHfEmpTask.setModifiedTime(LocalDateTime.now());
                        updateHfEmpTask.setModifiedBy(SocialSecurityConst.SYSTEM_USER);
                        updateHfEmpTask.setModifiedDisplayName(SocialSecurityConst.SYSTEM_USER);
                        this.updateById(updateHfEmpTask);

                        try {
                            Result result = hfEmpTaskHandleService.apiCompleteTask(outHfEmpTask.getTaskId(),
                                hfEmpTaskBatchRejectBo.getModifiedDisplayName());
                            return true;
                        } catch (Exception e) {
                            StringWriter sw = new StringWriter();
                            PrintWriter pw = new PrintWriter(sw);
                            e.printStackTrace(pw);
                            LogMessage logMessage = LogMessage.create().setTitle("访问接口").
                                setContent("访问客服中心的完成任务接口失败,ExceptionMessage:" + sw.toString());
                            logApiUtil.error(logMessage);
                            pw.close();
                            SsHfAutoOffsetFail ssHfAutoOffsetFail = new SsHfAutoOffsetFail();
                            ssHfAutoOffsetFail.setCompanyId(companyId);
                            ssHfAutoOffsetFail.setEmployeeId(employeeId);
                            ssHfAutoOffsetFail.setSsHfType(hfType);
                            ssHfAutoOffsetFail.setOffsetType(offsetType);
                            if (inHfEmpTask != null) {
                                ssHfAutoOffsetFail.setInEmpTaskId(inHfEmpTask.getEmpTaskId());
                            }
                            if (outHfEmpTask != null) {
                                ssHfAutoOffsetFail.setOutEmpTaskId(outHfEmpTask.getEmpTaskId());
                            }
                            ssHfAutoOffsetFailMapper.insert(ssHfAutoOffsetFail);
                            throw new BusinessException("访问客服中心的完成任务接口失败");
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public EmpTaskDetailBO getEmpTaskDetailByTaskId(String taskId) {
        return baseMapper.getEmpTaskDetailByTaskId(taskId);
    }

    /**
     * 转出单位(来源地)
     * @param paramMap
     * @return
     */
    private String getTransUnit(Map<String, Object> paramMap){
        String transUnit = "";
        if (null != paramMap.get("source")) {
            String source = paramMap.get("source").toString();
            if ("1".equals(source)) {
                transUnit = "中心";
            }
            else if ("2".equals(source)) {
                transUnit ="原单位";
            }
        }
        return transUnit;
    }
}
