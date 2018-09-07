package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpProductDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeProductProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.employSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpCollectExportDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpCollectExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpDispatchExportDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpDispatchExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SocApiProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMUserInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.*;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.SheetInfoProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.CompanyTypeDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 用工退工任务单 服务实现类
 * </p>
 */
@Service
public class AmEmpTaskServiceImpl extends ServiceImpl<AmEmpTaskMapper, AmEmpTask> implements IAmEmpTaskService {

    private final static Logger logger = LoggerFactory.getLogger(AmEmpTaskServiceImpl.class);

    @Autowired
    private IAmEmpMaterialService amEmpMaterialService;

    @Autowired
    private CommonApiUtils employeeInfoProxy;

    @Autowired
    AfEmployeeCompanyProxy afEmployeeCompanyProxy;

    @Autowired
    private IAmCompanySetService iAmCompanySetService;

    @Autowired
    private  IAmCompanySetService amCompanySetService;

    @Autowired
    private IAmEmpCustomService amEmpCustomService;

    @Autowired
    private AmEmpEmployeeService amEmpEmployeeService;

    @Autowired
    private AfEmployeeProductProxy afEmployeeProductProxy;

    @Autowired
    private IAmEmploymentService amEmploymentService;

    @Autowired
    private  IAmArchiveService  amArchiveService;

    @Autowired
    private IAmRemarkService amRemarkService;

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private IAmArchiveAdvanceService amArchiveAdvanceService;

    @Autowired
    private LogApiUtil logApiUtil;

    @Autowired
    private SheetInfoProxy sheetInfoProxy;

    @Autowired
    private SocApiProxy socApiProxy;


    @Override
    public PageRows<AmEmpTaskBO> queryAmEmpTask(PageInfo pageInfo) {

        AmEmpTaskBO amEmpTaskBO = pageInfo.toJavaObject(AmEmpTaskBO.class);

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if (!StringUtil.isEmpty(amEmpTaskBO.getParams())) {
            String arr[] = amEmpTaskBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
            if(amEmpTaskBO.getParams().indexOf("material_name")!=-1){
                amEmpTaskBO.setMaterial("1");
            }
        }

        amEmpTaskBO.setParam(param);
        amEmpTaskBO.setOrderParam(orderParam);

        if (null != amEmpTaskBO.getTaskStatus() && amEmpTaskBO.getTaskStatus() == 0) {
            amEmpTaskBO.setTaskStatus(null);
        }

        if(amEmpTaskBO.getTaskStatus()!=null&&amEmpTaskBO.getTaskStatus()==6){
            amEmpTaskBO.setTaskStatusOther(0);
        }
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmEmpTask(amEmpTaskBO));

    }

    @Override
    public List<AmEmpTaskBO> taskCount(PageInfo pageInfo) {
        AmEmpTaskBO amEmpTaskBO = pageInfo.toJavaObject(AmEmpTaskBO.class);
        List<String> param = new ArrayList<String>();

        if (!StringUtil.isEmpty(amEmpTaskBO.getParams())) {
            String arr[] = amEmpTaskBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                param.add(arr[i]);
            }
        }
        amEmpTaskBO.setParam(param);

        if(amEmpTaskBO.getTaskStatus()!=null&&amEmpTaskBO.getTaskStatus()==6){
            amEmpTaskBO.setTaskStatusOther(0);
        }

        if (null != amEmpTaskBO.getTaskStatus() && amEmpTaskBO.getTaskStatus() == 0) {
            amEmpTaskBO.setTaskStatus(null);
        }

        return baseMapper.taskCount(amEmpTaskBO);
    }

    @Override
    public List<AmEmpTaskBO> queryAmEmpTaskById(Map<String, Object> param) {
        return baseMapper.queryAmEmploymentById(param);
    }
    /**
     * 保存数据到雇员任务单表
     *
     * @param taskMsgDTO
     * @param taskCategory
     * @return
     * @throws Exception
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    @Override
    public boolean  insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception {
        if(!StringUtil.isEmpty(taskMsgDTO.getTaskId()))
        {
            List<AmEmpTaskBO> taskBOList = baseMapper.queryByTaskId(taskMsgDTO.getTaskId());
            if(null!=taskBOList&&taskBOList.size()>0)
            {
                LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(taskMsgDTO.getTaskId()+"is repetition");
                logApiUtil.info(logMessage);
                return false;
            }
        }
        AmEmpTask amEmpTask = new AmEmpTask();
        amEmpTask.setTaskId(taskMsgDTO.getTaskId());
        amEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        amEmpTask.setTaskCategory(taskCategory);
        amEmpTask.setTaskStatus(1);
        if(null!=taskMsgDTO.getVariables())
        {
            /**
             * 翻盘根本不传hireTaskId值 写的时候要判断
             */
            if(taskMsgDTO.getVariables().containsKey("hireTaskId"))
            {
                String hireTaskId = taskMsgDTO.getVariables().get("hireTaskId")==null?"":taskMsgDTO.getVariables().get("hireTaskId").toString();
                amEmpTask.setHireTaskId(hireTaskId);
            }
        }
        String taskId = taskMsgDTO.getTaskId()==null?"":taskMsgDTO.getTaskId();


        if(StringUtil.isEmpty(taskMsgDTO.getVariables().get("empCompanyId")))
        {
            LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(taskId+"empCompanyId is null ...");
            logApiUtil.info(logMessage);
            logger.info("empCompanyId is null ...");
            return false;
        }
        AmEmpTaskBO bo = this.queryAmEmpTaskBO(taskMsgDTO.getVariables().get("empCompanyId"));
        amEmpTask.setEmpCompanyId(taskMsgDTO.getVariables().get("empCompanyId").toString());

        if(null!=bo){
            amEmpTask.setCompanyId(bo.getCompanyId());
            amEmpTask.setEmployeeId(bo.getEmployeeId());
            amEmpTask.setTaskFormContent(JSON.toJSONString(taskMsgDTO.getVariables()));
        }

        String archiveDirection = null;
        String employeeNature = null;
        String submitterId = null;//材料提交人
        Map<String, Object> map = null;
        try {
            map = taskMsgDTO.getVariables();
            if(map!=null)
            {
                if(map.containsKey("archiveDirection"))
                {
                    archiveDirection = (String)map.get("archiveDirection");
                    EmployeeBO employeeBO  = baseMapper.queryArchiveDriection(archiveDirection);
                    if(null!=employeeBO){
                        amEmpTask.setArchiveDirection(employeeBO.getArchiveDirection());
                    }
                }
                if(map.containsKey("employeeNature"))
                {
                    employeeNature = (String)map.get("employeeNature");
                    EmployeeBO personNature = baseMapper.queryNature(employeeNature);
                    if(null!=personNature){
                        amEmpTask.setEmployeeNature(personNature.getPersonNature());
                    }
                }
                if(map.containsKey("submitterId"))
                {
                    submitterId = taskMsgDTO.getVariables().get("submitterId")==null?"":map.get("submitterId").toString();
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        //TODO 调用吴敬磊接口传入taskMsgDTO.getMissionId()返回数据
        AfEmployeeInfoDTO dto = null;
        AfEmployeeCompanyDTO employeeCompany = null;
        try {
            dto = employeeInfoProxy.callInf(taskMsgDTO);
            employeeCompany = dto.getEmployeeCompany();
            if(null!=employeeCompany)
            {
                amEmpTask.setCreatedBy(employeeCompany.getCreatedBy());
                amEmpTask.setModifiedBy(employeeCompany.getModifiedBy());
                amEmpTask.setSubmitterId(submitterId);
                if(employeeCompany.getHireUnit()!=null)
                {
                    amEmpTask.setEmployCode(employeeCompany.getHireUnit());
                    amEmpTask.setEmployProperty(ReasonUtil.getYgsx(employeeCompany.getHireUnit().toString()));
                }
            }

            //如果是翻盘
            if("emp_company_change".equals(taskMsgDTO.getProcessDefinitionKey()))
            {
                amEmpTask.setChangeCompany("是");
            }else{
                amEmpTask.setChangeCompany("否");
            }


        } catch (Exception e) {
            try {
                LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(taskId+e.getMessage());
                logApiUtil.info(logMessage);
            } catch (Exception e1) {

            }
            logger.error(e.getMessage(), e);
        }

        amEmpTask.setActive(true);
        amEmpTask.setModifiedTime(LocalDateTime.now());
        amEmpTask.setCreatedTime(LocalDateTime.now());

        baseMapper.insert(amEmpTask);

        try {
            this.saveEmpCustom(employeeCompany,amEmpTask.getEmpTaskId(),bo.getCompanyId());

            this.saveEmpEmployee(taskMsgDTO,bo,amEmpTask.getEmpTaskId());

        } catch (Exception e) {

        }

        if(map.containsKey("materialList"))
        {
            try {
                List<String> list = (List<String>) map.get("materialList");
                SMUserInfoDTO smUserInfoDTO = null;
                if(!StringUtil.isEmpty(submitterId))
                {
                    try {
                        smUserInfoDTO = employeeInfoProxy.getUserInfo(submitterId);
                    } catch (Exception e) {
                        LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(e.getMessage());
                        logApiUtil.error(logMessage);
                    }
                }
                List<AmEmpMaterial> amEmpMaterialsList = new ArrayList<>();
                if(null!=list)
                {
                    for(String str:list)
                    {
                        AmEmpMaterial amEmpMaterial = new AmEmpMaterial();
                        amEmpMaterial.setMaterialName(str);
                        amEmpMaterial.setEmployeeId(bo.getEmployeeId());
                        amEmpMaterial.setOperateType(1);
                        amEmpMaterial.setActive(true);
                        String createdBy = "System";
                        try {
                            createdBy = smUserInfoDTO.getUserId();
                        } catch (Exception e) {

                        }
                        amEmpMaterial.setCreatedBy(createdBy);
                        amEmpMaterial.setCreatedTime(LocalDateTime.now());
                        amEmpMaterial.setModifiedTime(LocalDateTime.now());
                        amEmpMaterial.setModifiedBy(createdBy);
                        amEmpMaterial.setSubmitterDate(LocalDateTime.now());
                        amEmpMaterial.setSubmitterId(submitterId);
                        amEmpMaterial.setSubmitterName(smUserInfoDTO==null?"":smUserInfoDTO.getDisplayName());
                        amEmpMaterial.setExtension(smUserInfoDTO==null?"":smUserInfoDTO.getExtension());
                        amEmpMaterial.setEmpTaskId(amEmpTask.getEmpTaskId());
                        amEmpMaterialsList.add(amEmpMaterial);
                    }
                    amEmpMaterialService.insertBatch(amEmpMaterialsList);
                }else{
                    logger.info("materialList",map.get("materialList"));
                }

            } catch (Exception e) {
                LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(e.getMessage());
                logApiUtil.error(logMessage);
            }
        }

        return true;
    }

    @Override
    public boolean insertTaskFire(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception {
        if(!StringUtil.isEmpty(taskMsgDTO.getTaskId()))
        {
            List<AmEmpTaskBO> taskBOList = baseMapper.queryByTaskId(taskMsgDTO.getTaskId());
            if(null!=taskBOList&&taskBOList.size()>0)
            {
                LogMessage logMessage = LogMessage.create().setTitle("退工任务单").setContent(taskMsgDTO.getTaskId()+"is repetition");
                logApiUtil.info(logMessage);
                return false;
            }
        }
        AmEmpTask amEmpTask = new AmEmpTask();
        amEmpTask.setTaskId(taskMsgDTO.getTaskId());
        amEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        amEmpTask.setTaskCategory(taskCategory);
        amEmpTask.setTaskStatus(99);

        //TODO 调用吴敬磊接口传入taskMsgDTO.getMissionId()返回数据
        AfEmployeeInfoDTO dto = null;
        try {
            dto = employeeInfoProxy.callInf(taskMsgDTO);
            AfEmployeeCompanyDTO employeeCompany = dto.getEmployeeCompany();
            amEmpTask.setEmployeeId(employeeCompany.getEmployeeId());
            amEmpTask.setCompanyId(employeeCompany.getCompanyId());
            amEmpTask.setTaskFormContent(JSON.toJSONString(taskMsgDTO.getVariables()));

            amEmpTask.setOutDate(employeeCompany==null?null:employeeCompany.getOutDate());

            if(null!=employeeCompany&&null!=employeeCompany.getOutReason()){
                amEmpTask.setOutReasonCode(employeeCompany.getOutReason().toString());
                amEmpTask.setOutReason(ReasonUtil.getReasonOut(employeeCompany.getOutReason().toString()));
            }else{
                if(employeeCompany!=null){
                    LogMessage logMessage = LogMessage.create().setTitle("退工任务单").setContent(JSON.toJSONString(employeeCompany));
                    logApiUtil.info(logMessage);
                    logger.info(JSON.toJSONString(employeeCompany));
                }
                logger.info("outReason is null "+"  MissionId is "+taskMsgDTO.getMissionId());
            }
            //如果是调整或者更正
            if("emp_agreement_adjust".equals(taskMsgDTO.getProcessDefinitionKey())||"emp_agreement_update".equals(taskMsgDTO.getProcessDefinitionKey()))
            {
                amEmpTask.setOutReason("转其他城市缴纳");
                amEmpTask.setOutReasonCode("changeOther");
                List<AfEmpSocialDTO> empSocialList = dto.getEmpSocialList();
                if(null!=empSocialList)
                {
                    boolean isContinue = false;
                    for(AfEmpSocialDTO afEmpSocialDTO:empSocialList)
                    {
                        if("DIT00042".equals(afEmpSocialDTO.getItemCode())&&null!=afEmpSocialDTO.getEndDate())
                        {
                            amEmpTask.setOutDate(afEmpSocialDTO.getEndDate());
                            isContinue = true;
                            break;
                        }
                    }
                    if(!isContinue)
                    {
                        for(AfEmpSocialDTO afEmpSocialDTO:empSocialList)
                        {
                            if(null!=afEmpSocialDTO.getEndDate())
                            {
                                amEmpTask.setOutDate(afEmpSocialDTO.getEndDate());
                                break;
                            }

                        }
                    }
                }
            }
            //如果是翻盘
            if("emp_company_change".equals(taskMsgDTO.getProcessDefinitionKey()))
            {
                amEmpTask.setChangeCompany("是");
            }else{
                amEmpTask.setChangeCompany("否");
            }
            if(employeeCompany!=null&&employeeCompany.getHireUnit()!=null)
            {
                amEmpTask.setEmployCode(employeeCompany.getHireUnit());
                amEmpTask.setEmployProperty(ReasonUtil.getYgsx(employeeCompany.getHireUnit().toString()));
            }
            if(employeeCompany!=null)
            {
                amEmpTask.setCreatedBy(employeeCompany.getCreatedBy());
                amEmpTask.setModifiedBy(employeeCompany.getModifiedBy());
                amEmpTask.setSubmitterId(employeeCompany.getCreatedBy());
            }

            //是否在职
            if("emp_agreement_adjust".equals(taskMsgDTO.getProcessDefinitionKey())||"emp_agreement_update".equals(taskMsgDTO.getProcessDefinitionKey()))
            {

            }else if("emp_company_change".equals(taskMsgDTO.getProcessDefinitionKey())){
                amEmpTask.setJob("N");
            }else{
                amEmpTask.setJob("N");
            }

            try {
                if(taskMsgDTO.getVariables().containsKey("empCompanyId"))
                {
                    if(taskMsgDTO.getVariables().get("empCompanyId")!=null)
                    {
                        amEmpTask.setEmpCompanyId(taskMsgDTO.getVariables().get("empCompanyId").toString());
                    }
                }
            } catch (Exception e) {
                LogMessage logMessage = LogMessage.create().setTitle("退工任务单").setContent(e.getMessage());
                logApiUtil.error(logMessage);
            }

            try {
                //更新离职原因
                AmEmpTaskBO amEmpTaskBO = new AmEmpTaskBO();
                amEmpTaskBO.setEmployeeId(amEmpTask.getEmployeeId());
                amEmpTaskBO.setCompanyId(amEmpTask.getCompanyId());
                List<AmEmpTaskBO> amEmpTaskBOList = baseMapper.queryEmpTask(amEmpTaskBO);
                if(null!=amEmpTaskBOList&&amEmpTaskBOList.size()>0){
                    AmEmpTaskBO  amEmpTaskBO1 =  amEmpTaskBOList.get(0);
                    AmEmpTask amEmpTask1 = this.selectById(amEmpTaskBO1.getEmpTaskId());
                    amEmpTask1.setOutDate(amEmpTask.getOutDate());
                    amEmpTask1.setOutReason(amEmpTask.getOutReason());
                    amEmpTask1.setOutReasonCode(amEmpTask.getOutReasonCode());
                    if("N".equals(amEmpTask.getJob())){
                        amEmpTask1.setJob("N");
                    }

                    this.insertOrUpdate(amEmpTask1);
                }
            } catch (Exception e) {

            }

        } catch (Exception e) {
            logger.error("callOut interface error ......");
            logger.error(e.getMessage(), e);
        }

        amEmpTask.setActive(true);
        amEmpTask.setModifiedTime(LocalDateTime.now());
        amEmpTask.setCreatedTime(LocalDateTime.now());

        baseMapper.insert(amEmpTask);

        return true;
    }

    @Override
    public AmEmpTaskBO queryAccout(String companyId) {

        AmEmpTaskBO amEmpTaskBO = null;

        try {
            amEmpTaskBO = baseMapper.queryAccout(companyId);
        } catch (Exception e) {

        }

        return amEmpTaskBO;
    }

    @Override
    public AmEmpTaskBO queryEmpTask(AmEmpTaskBO amEmpTaskBO) {
        AmEmpTaskBO empTaskBO = null;
        try {
            List<AmEmpTaskBO>  list = baseMapper.queryEmpTask(amEmpTaskBO);
            if(null!=list&&list.size()>0){
                return  list.get(0);
            }
        } catch (Exception e) {

        }
        return empTaskBO;
    }

    @Override
    public boolean updateTaskStatus(Map<String,Object> param) {
        Integer i =0;
        i = baseMapper.updateTaskStatus(param);
        if(i>0){
            return  true;
        }
        return false;
    }

    @Override
    public AmEmpTask getAmEmpTaskById(Long amEmpTaskId) {
        return super.selectById(amEmpTaskId);
    }

    @Override
    public List<employSearchExportOpt> queryAmEmpTaskList(AmEmpTaskBO amEmpTaskBO) {
        return  baseMapper.queryAmEmpTaskList(amEmpTaskBO);
    }

    @Override
    public AmEmpTaskBO queryAmEmpTaskBO(Object empCompanyId) {
        Map<String,Object> param = new HashMap<>();
        param.put("empCompanyId",empCompanyId);
        AmEmpTaskBO bo = baseMapper.selectEmployId(param);
        return  bo;
    }

    @Override
    public AmEmpTaskBO getDefualtEmployBO(AmEmpTaskBO amEmpTaskBO) {
        AmTaskParamBO amTaskParamBO = new AmTaskParamBO();
        amTaskParamBO.setEmployeeId(amEmpTaskBO.getEmployeeId());
        amTaskParamBO.setCompanyId(amEmpTaskBO.getCompanyId());

        AmEmpEmployeeBO amEmpEmployeeBO = amEmpEmployeeService.queryDefaultAmEmployee(amTaskParamBO);

        AmEmpTask amEmpTask = super.selectById(amEmpTaskBO.getEmpTaskId());
        AmEmpTaskBO amEmpTaskBO1 = new AmEmpTaskBO();
        BeanUtils.copyProperties(amEmpTask,amEmpTaskBO1);

        amEmpTaskBO1 = defaultRule(amEmpTaskBO1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if(amEmpEmployeeBO!=null)
        {
            /**
             * 用工属性根据雇佣类型  派遣默认中智
             */
            if(null!=amEmpEmployeeBO.getHireUnit()&&amEmpEmployeeBO.getHireUnit()==1)
            {
                amEmpTaskBO1.setEmployProperty("独立");
            }

            if(null!=amEmpEmployeeBO.getLaborStartDate()){
                amEmpTaskBO1.setFirstInDate(sdf.format(amEmpEmployeeBO.getLaborStartDate()));//实际录用日期,合同开始日期
            }
        }

        amEmpTaskBO1.setOpenAfDate(sdf.format(new Date()));
        amEmpTaskBO1.setEmployStyle("1");//默认全日制
        return amEmpTaskBO1;
    }

    @Override
    public boolean insertTaskFireChange(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception {
        return false;
    }

    @Override
    public EmploymentDTO getEmploymentByTaskId(TaskParamDTO taskParamDTO) {
        List<EmploymentDTO> list = baseMapper.getEmploymentByTaskId(taskParamDTO);
        if(list!=null&&list.size()>0){
            return  list.get(0);
        }
        return null;
    }

    @Override
    public ArchiveDTO getArchiveByEmployeeId(TaskParamDTO taskParamDTO) {
        List<ArchiveDTO> list = baseMapper.getArchiveByEmployeeId(taskParamDTO);
        if(null!=list&&list.size()>0)
        {
            return  list.get(0);
        }
        return null;
    }

    @Override
    public ResignDTO getResignByTaskId(TaskParamDTO taskParamDTO) {
        List<ResignFeedbackDTO> resignFeedbackDTOList = baseMapper.queryResignLinkByTaskId(taskParamDTO);
        List<ResignDTO> resignDTOList = baseMapper.queryResignByTaskId(taskParamDTO);
        if(null!=resignDTOList&&resignDTOList.size()>0){
            ResignDTO resignDTO = resignDTOList.get(0);
            resignDTO.setFeedbackDTOList(resignFeedbackDTOList);
            return  resignDTO;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String,Object> batchSaveEmployee(AmArchiveBO amArchiveBO) {
        Map<String,Object> map = new HashMap<>();
        List<String> taskIdList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        boolean result = false;
        Map<String,Object> param = new HashMap<>();

        List<AmEmploymentBO> amEmploymentBOList = amEmploymentService.queryAmEmploymentBatch(Arrays.asList(amArchiveBO.getEmpTaskIds()));

        if(amEmploymentBOList==null||amEmploymentBOList.size()==0){
            map.put("size",false);
            return  map;
        }

        List<AmArchive> amArchiveList = new ArrayList<>();
        List<AmEmpTask> amEmpTaskList = new ArrayList<>();
        String ylDocNum = amArchiveBO.getYuliuDocNum();
        String docNum = amArchiveBO.getDocNum();
        List<Long> archiveAdvanceIdList = new ArrayList<>();

        int i = 0;
        int cCnum = 0;
        for(AmEmploymentBO temp:amEmploymentBOList)
        {
            param.put("employmentId",temp.getEmploymentId());
            List<AmArchiveBO> amArchiveBOList = amArchiveService.queryAmArchiveList(param);
            AmArchive entity = new AmArchive();
            BeanUtils.copyProperties(amArchiveBO,entity);
            if(null!=amArchiveBOList&&amArchiveBOList.size()>0)
            {
                AmArchiveBO amArchiveBO1 = amArchiveBOList.get(0);
                entity.setArchiveId(amArchiveBO1.getArchiveId());
                entity.setCreatedTime(amArchiveBO1.getCreatedTime());
                entity.setCreatedBy(amArchiveBO1.getCreatedBy());
            }else{
                entity.setCreatedTime(now);
                entity.setCreatedBy(ReasonUtil.getUserId());
            }
            if(!StringUtil.isEmpty(ylDocNum)){
                Integer  ylDocNumInt = Integer.parseInt(ylDocNum) + i;
                entity.setYuliuDocNum(ylDocNumInt.toString());
            }
            if(!StringUtil.isEmpty(docNum)){
                Integer docNumInt = Integer.parseInt(docNum) + i;
                entity.setDocNum(docNumInt.toString());
            }

            entity.setEmploymentId(temp.getEmploymentId());
            entity.setEmployeeId(temp.getEmployeeId());
            entity.setCompanyId(temp.getCompanyId());
            //档案预增匹配
            if(!StringUtil.isEmpty(temp.getDocNum())){
                entity.setDocNum(temp.getDocNum());
                entity.setDocType(temp.getDocType());
                entity.setArchivePlace(temp.getArchivePlace());
                entity.setDocFrom(temp.getDocFrom());
                archiveAdvanceIdList.add(temp.getArchiveAdvanceId());
                if(cCnum==0){
                    cCnum = Integer.parseInt(temp.getDocNum());
                }else if(cCnum<Integer.parseInt(temp.getDocNum())){
                    cCnum = Integer.parseInt(temp.getDocNum());
                }
            }else{
                ++i;
            }

            entity.setModifiedTime(now);
            entity.setModifiedBy(ReasonUtil.getUserId());
            entity.setIsActive(1);

            AmEmpTask amEmpTask = null;
            if(!StringUtil.isEmpty(entity.getEmployFeedback()))
            {
                amEmpTask = baseMapper.selectById(temp.getEmpTaskId());
                amEmpTask.setTaskStatus(Integer.parseInt(entity.getEmployFeedback()));
                taskIdList.add(amEmpTask.getTaskId());
                if("0".equals(amArchiveBO.getIsFrist()))
                {
                    if("11".equals(entity.getEmployFeedback()))
                    {

                    }else{
                        amEmpTask.setFinish(true);
                    }
                }
            }
            amArchiveList.add(entity);
            amEmpTaskList.add(amEmpTask);
        }
        if(archiveAdvanceIdList.size()>0)
        {
            List<AmArchiveAdvance> amArchiveAdvancesList = new ArrayList<>();
            for(Long archiveAdvanceId:archiveAdvanceIdList)
            {
                AmArchiveAdvance amArchiveAdvance = new AmArchiveAdvance();
                amArchiveAdvance.setArchiveAdvanceId(archiveAdvanceId);
                amArchiveAdvance.setStatus(2);
                amArchiveAdvancesList.add(amArchiveAdvance);
            }
            amArchiveAdvanceService.updateBatchById(amArchiveAdvancesList);
        }
        this.insertOrUpdateBatch(amEmpTaskList);
        result =  amArchiveService.insertOrUpdateBatch(amArchiveList);
        if(result)
        {
            // 修改预留档案编号 seq
            if(amArchiveBO.getYuliuDocNum() != null && amArchiveBO.getYuliuDocType() != null){
                AmArchiveDocSeq seq = new AmArchiveDocSeq();
                seq.setType(1);
                seq.setDocType(amArchiveBO.getYuliuDocType());
                Integer yuliuDocNumInt = Integer.parseInt(amArchiveBO.getYuliuDocNum())+i-1;
                seq.setDocSeq(yuliuDocNumInt);
                List<AmArchiveDocSeqBO> list1 = amArchiveService.queryCountHaveAbove(seq);
                // 比原有的seq要大
                if(list1.size() == 0){
                    amArchiveService.updateByTypeAndDocType(seq);
                }
            }
            // 修改档案编号 seq
            if(amArchiveBO.getDocNum() != null && amArchiveBO.getDocType() != null){
                AmArchiveDocSeq seq2 = new AmArchiveDocSeq();
                seq2.setType(2);
                seq2.setDocType(amArchiveBO.getDocType());
                Integer docNumInt = Integer.parseInt(amArchiveBO.getDocNum())+i-1;
                seq2.setDocSeq(docNumInt);
                List<AmArchiveDocSeqBO> list2 = amArchiveService.queryCountHaveAbove(seq2);
                // 比原有的seq要大
                if(list2.size() == 0){
                    amArchiveService.updateByTypeAndDocType(seq2);
                }
            }
            if(archiveAdvanceIdList.size()>0)
            {
                AmArchiveDocSeq seq3 = new AmArchiveDocSeq();
                seq3.setType(2);
                seq3.setDocType("Cc");
                seq3.setDocSeq(cCnum);
                List<AmArchiveDocSeqBO> list2 = amArchiveService.queryCountHaveAbove(seq3);
                // 比原有的seq要大
                if(list2.size() == 0){
                    amArchiveService.updateByTypeAndDocType(seq3);
                }
            }
        }
        try {
            if(!StringUtil.isEmpty(amArchiveBO.getRemarkContent())){
                List<AmRemark> amRemarkList = new ArrayList<>();
                Long[] longs = amArchiveBO.getEmpTaskIds();
                for(int m=0;m<longs.length;m++)
                {
                    AmRemark amRemark = new AmRemark();
                    amRemark.setEmpTaskId(longs[m]);
                    amRemark.setRemarkContent(amArchiveBO.getRemarkContent());
                    amRemark.setRemarkType(2);
                    amRemark.setCreatedTime(now);
                    amRemark.setModifiedTime(now);
                    amRemark.setCreatedBy(ReasonUtil.getUserId());
                    amRemark.setModifiedBy(ReasonUtil.getUserId());
                    amRemark.setRemarkDate(LocalDate.now());
                    amRemark.setRemarkMan(ReasonUtil.getUserName());
                    amRemarkList.add(amRemark);
                }
                amRemarkService.insertBatch(amRemarkList);
            }
        } catch (Exception e) {

        }
        map.put("result",result);
        map.put("taskIdList",taskIdList);
        return map;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String,Object> batchSaveEmployment(EmployeeBatchBO employeeBatchBO) {
        Map<String,Object> map = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        String userId = ReasonUtil.getUserId();
        String userName = ReasonUtil.getUserName();
        List<AmEmpTaskBO> amEmpTaskMaterialList = baseMapper.queryIsMaterial(employeeBatchBO);
        List<HireMaterialTransferRecordDTO> hireMaterialTransferRecordDTOList = new ArrayList<>();
        for(AmEmpTaskBO taskBO:amEmpTaskMaterialList)
        {
            if(!StringUtil.isEmpty(taskBO.getHireTaskId()))
            {
                HireMaterialTransferRecordDTO dto = new HireMaterialTransferRecordDTO();
                dto.setTaskId(taskBO.getHireTaskId());
                dto.setOperation(1);
                dto.setRemark("");
                dto.setOperator(UserContext.getUser().getDisplayName());
                hireMaterialTransferRecordDTOList.add(dto);
            }
        }
        if(hireMaterialTransferRecordDTOList.size()>0)
        {
            HireMaterialTransferRecordBatchDTO hireMaterialTransferRecordBatchDTO = new HireMaterialTransferRecordBatchDTO();
            hireMaterialTransferRecordBatchDTO.setHireMaterialTransferRecordDTOList(hireMaterialTransferRecordDTOList);

            JsonResult jsonResult = null;
            try {
                jsonResult = sheetInfoProxy.feedbackHireMaterialOperationInfoList(hireMaterialTransferRecordBatchDTO);
            } catch (Exception e) {
                LogMessage logMessage = LogMessage.create().setTitle("employee_sheetInfoProxy").setContent(e.getMessage());
                logApiUtil.error(logMessage);
            }

            if(jsonResult==null){
                map.put("message","雇员中心接口异常");
                return map;
            }else{
                if(jsonResult.getCode()==1){
                    map.put("message",jsonResult.getMessage());
                    return map;
                }else{
                    List<AmEmpTaskBO> changeList = baseMapper.queryChange(employeeBatchBO);
                    List<Long> tempChangeList = new ArrayList<>();
                    for(AmEmpTaskBO tempBO:changeList)
                    {
                        tempChangeList.add(tempBO.getEmpTaskId());
                    }
                    //翻盘不需要材料签收
                    List<Long> taskIds  = employeeBatchBO.getEmpTaskIds();
                    if(tempChangeList.size()>0){
                        taskIds.removeAll(tempChangeList);
                    }
                    AmEmpMaterialBO amEmpMaterialBO = new AmEmpMaterialBO();
                    amEmpMaterialBO.setEmpTaskIdList(taskIds);
                    amEmpMaterialBO.setReceiveDate(LocalDate.now());
                    amEmpMaterialBO.setReceiveId(userId);
                    amEmpMaterialBO.setReceiveName(userName);
                    amEmpMaterialBO.setModifiedBy(userId);
                    amEmpMaterialBO.setModifiedTime(now);
                    try {
                        amEmpMaterialService.updateMaterialBatch(amEmpMaterialBO);
                    } catch (Exception e) {
                        LogMessage logMessage = LogMessage.create().setTitle("用工材料批量签收").setContent(e.getMessage());
                        logApiUtil.error(logMessage);
                    }
                }
            }
        }


        Map<String,Object> param = new HashMap<>();
        List<AmRemark> amRemarkList = new ArrayList<>();
        List<AmEmployment> list = new ArrayList<>();


        for(Long emTaskId:employeeBatchBO.getEmpTaskIds())
        {
            AmEmpTask amEmpTask = this.getAmEmpTaskById(emTaskId);
            AmEmployment entity = null;
            param.put("empTaskId",emTaskId);
            List<AmEmploymentBO>  amEmploymentBOList = amEmploymentService.queryAmEmployment(param);

            entity = new AmEmployment();

            if(amEmploymentBOList!=null&&amEmploymentBOList.size()>0)
            {
                AmEmploymentBO amEmploymentBO = amEmploymentBOList.get(0);
                entity.setEmploymentId(amEmploymentBO.getEmploymentId());
                entity.setCreatedBy(amEmploymentBO.getCreatedBy());
                entity.setCreatedTime(amEmploymentBO.getCreatedTime());
            }else{

                entity.setCreatedTime(now);
                entity.setCreatedBy(userId);
            }

            entity.setEmpTaskId(emTaskId);
            entity.setEmployProperty(employeeBatchBO.getEmployProperty());
            entity.setEmployDate(employeeBatchBO.getEmployDate());
            entity.setOpenAfDate(employeeBatchBO.getOpenAfDate());
            entity.setEmployStyle(employeeBatchBO.getEmployStyle());//默认全日制
            entity.setEmployeeId(amEmpTask.getEmployeeId());
            entity.setCompanyId(amEmpTask.getCompanyId());
            entity.setEmployOperateMan(userName);
            entity.setHandleType(employeeBatchBO.getHandleType());
            entity.setEmployWay(employeeBatchBO.getEmployWay());

            entity.setModifiedTime(now);
            entity.setModifiedBy(userId);
            entity.setIsActive(1);

            if(!StringUtil.isEmpty(employeeBatchBO.getRemarkContent())){
                AmRemark amRemark = new AmRemark();
                amRemark.setEmpTaskId(emTaskId);
                amRemark.setRemarkContent(employeeBatchBO.getRemarkContent());
                amRemark.setRemarkType(1);
                amRemark.setRemarkDate(LocalDate.now());
                amRemark.setRemarkMan(userName);
                amRemark.setCreatedBy(userId);
                amRemark.setCreatedTime(now);
                amRemark.setModifiedBy(userId);
                amRemark.setModifiedTime(now);

                amRemarkList.add(amRemark);
            }

            list.add(entity);
        }
        if(amRemarkList.size()>0)
        {
            amRemarkService.insertBatch(amRemarkList);
        }
        boolean b = amEmploymentService.insertOrUpdateAllColumnBatch(list);
        map.put("result",b);
        return map;
    }

    @Override
    public Map<String, Object>  batchCheck(EmployeeBatchBO employeeBatchBO) {
        Map<String,Object> resultMap = new HashMap<>();
        List<AmEmpTaskBO> amEmpTaskMaterialList = baseMapper.queryIsMaterial(employeeBatchBO);
        List<HireMaterialTransferRecordDTO> hireMaterialTransferRecordDTOList = new ArrayList<>();
        Map<String,String> tempMap = new HashMap<>();
        for(AmEmpTaskBO taskBO:amEmpTaskMaterialList)
        {
            if(!StringUtil.isEmpty(taskBO.getHireTaskId()))
            {
                tempMap.put(taskBO.getHireTaskId(),taskBO.getEmployeeId());
                HireMaterialTransferRecordDTO dto = new HireMaterialTransferRecordDTO();
                dto.setTaskId(taskBO.getHireTaskId());
                dto.setOperation(1);
                dto.setRemark("");
                dto.setOperator(UserContext.getUser().getDisplayName());
                hireMaterialTransferRecordDTOList.add(dto);
            }
        }
        if(hireMaterialTransferRecordDTOList.size()>0)
        {
            HireMaterialTransferRecordBatchDTO hireMaterialTransferRecordBatchDTO = new HireMaterialTransferRecordBatchDTO();
            hireMaterialTransferRecordBatchDTO.setHireMaterialTransferRecordDTOList(hireMaterialTransferRecordDTOList);
            JsonResult<List<FeedbackMessageDTO>> jsonResult = null;
            try {
                jsonResult = sheetInfoProxy.validateHireMaterialOperation(hireMaterialTransferRecordBatchDTO);
            } catch (Exception e) {
                LogMessage logMessage = LogMessage.create().setTitle("employee_sheetInfoProxy").setContent(e.getMessage());
                logApiUtil.error(logMessage);
            }
            if(null==jsonResult)
            {
                resultMap.put("isMaterial","雇员中心接口调用异常...");
                return  resultMap;
            }else{
                List<FeedbackMessageDTO> data = jsonResult.getData();
                if(null!=data&&data.size()>0)
                {
                    String empId = tempMap.get(data.get(0).getTaskId())==null?"":tempMap.get(data.get(0).getTaskId());
                    StringBuffer buf = new StringBuffer();
                    buf.append("雇员编号是");
                    buf.append(empId);
                    buf.append(",原因是");
                    buf.append(data.get(0).getMessage());
                    resultMap.put("isMaterial",buf.toString());
                    return  resultMap;
                }
            }
        }
        List<AmEmpTaskBO> amEmpTaskBOList = baseMapper.queryIsFinish(employeeBatchBO);
        if(null!=amEmpTaskBOList&&amEmpTaskBOList.size()>0)
        {
            resultMap.put("empTask",amEmpTaskBOList.size());
            return  resultMap;
        }
        List<AmEmploymentBO> amEmploymentBOList = amEmploymentService.queryAmEmploymentBatch(employeeBatchBO.getEmpTaskIds());

        if(amEmploymentBOList!=null&&amEmploymentBOList.size()>0)
        {
            AmArchiveBO amArchiveBO = new AmArchiveBO();
            List<Long> employmentIds = new ArrayList<>();
            for(AmEmploymentBO amEmploymentBO:amEmploymentBOList)
            {
                employmentIds.add(amEmploymentBO.getEmploymentId());
            }
            amArchiveBO.setEmploymentIds(employmentIds);
            List<AmArchiveBO> amArchiveBOList = amArchiveService.queryAmArchiveBatch(amArchiveBO);
            resultMap.put("employmentCount",amEmploymentBOList.size());
            if(amArchiveBOList!=null&&amArchiveBOList.size()>0)
            {
                resultMap.put("ArchiveCount",amArchiveBOList.size());
            }

        }else{
            resultMap.put("employmentCount",0);
        }
        return resultMap;
    }


    AmEmpTaskBO  defaultRule(AmEmpTaskBO amEmpTaskBO){
        if("外来三险".equals(amEmpTaskBO.getEmployeeNature())||"外地人员".equals(amEmpTaskBO.getEmployeeNature()))
        {
            amEmpTaskBO.setHandleType("外来从业人员");
            amEmpTaskBO.setArchivePlace("外来从业人员");
        }

        if("居住证".equals(amEmpTaskBO.getEmployeeNature()))
        {
            amEmpTaskBO.setHandleType(amEmpTaskBO.getEmployeeNature());
            amEmpTaskBO.setArchivePlace(amEmpTaskBO.getEmployeeNature());
        }
        if("上海失业人员".equals(amEmpTaskBO.getEmployeeNature())&&"户口所在地".equals(amEmpTaskBO.getArchiveDirection()))
        {
            AmCompanySetBO amCompanySetBO = new AmCompanySetBO();
            amCompanySetBO.setCompanyId(amEmpTaskBO.getCompanyId());
            AmCompanySetBO amCompanySetBO1 = iAmCompanySetService.queryAmCompanySet(amCompanySetBO);

            if(amCompanySetBO1!=null&&amCompanySetBO1.getCompanySpecial8()!=null&&amCompanySetBO1.getCompanySpecial8()==1){
                amEmpTaskBO.setHandleType("调档");
            }else {
                amEmpTaskBO.setHandleType("属地管理");
                amEmpTaskBO.setArchivePlace("属地管理");
            }

        }
        if("人才引进".equals(amEmpTaskBO.getEmployeeNature())&&"中智".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType(amEmpTaskBO.getArchiveDirection());
            amEmpTaskBO.setArchivePlace(amEmpTaskBO.getArchiveDirection());
        }
        if("上海失业人员".equals(amEmpTaskBO.getEmployeeNature())&&"区人才".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType(amEmpTaskBO.getArchiveDirection());
            amEmpTaskBO.setArchivePlace(amEmpTaskBO.getArchiveDirection());
        }
        if("农村富裕劳动力".equals(amEmpTaskBO.getEmployeeNature())&&"农村富裕劳动力".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType("农民工");
            amEmpTaskBO.setArchivePlace("农村富裕劳动力");
        }
        if("上海失业人员".equals(amEmpTaskBO.getEmployeeNature())&&"市人才".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType("市人才");
            amEmpTaskBO.setArchivePlace("市人才");
        }
        if("人才引进".equals(amEmpTaskBO.getEmployeeNature())&&"高教中心".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType("高校");
            amEmpTaskBO.setArchivePlace("就业指导中心");
        }
        if("退休人员".equals(amEmpTaskBO.getEmployeeNature())&&"退休".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType("退休");
            amEmpTaskBO.setArchivePlace("退休");
        }
        if("协保人员".equals(amEmpTaskBO.getEmployeeNature())&&"协保".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType("协保");
            amEmpTaskBO.setArchivePlace("协保");
        }
        if("非全日制用工".equals(amEmpTaskBO.getEmployeeNature())&&"非全日制".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType("非全日制");
            amEmpTaskBO.setArchivePlace("非全日制");
        }

        return  amEmpTaskBO;
    }

    /**
     * 获取公司信息通过销售中心接口
     * 保存客户信息对象
     */
    void saveEmpCustom(AfEmployeeCompanyDTO employeeCompany,Long empTaskId,String companyId){

        try {
            AfCompanyDetailResponseDTO afCompanyDetailResponseDTO = employeeInfoProxy.getCompanyDetail(companyId);
            AmEmpCustom amEmpCustom = new AmEmpCustom();
            amEmpCustom.setEmpTaskId(empTaskId);
            amEmpCustom.setCreatedDisplayName(employeeCompany==null?"":employeeCompany.getCreatedDisplayName());//客服专员
            amEmpCustom.setModifiedDisplayName(employeeCompany==null?"":employeeCompany.getCreatedDisplayName());
            amEmpCustom.setLeaderShipId(employeeCompany==null?"":employeeCompany.getLeadershipId());
            amEmpCustom.setLeaderShipName(employeeCompany==null?"":employeeCompany.getLeadershipName());//客服经理
            amEmpCustom.setCreatedBy(employeeCompany==null?"":employeeCompany.getCreatedBy());
            amEmpCustom.setServiceCenter(afCompanyDetailResponseDTO.getServiceCenter());
            amEmpCustom.setTitle(employeeCompany==null?"":employeeCompany.getTitle());
            amEmpCustom.setActive(true);
            amEmpCustom.setCreatedTime(LocalDateTime.now());
            amEmpCustom.setModifiedTime(LocalDateTime.now());

            amEmpCustomService.insert(amEmpCustom);
        } catch (Exception e) {
            LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(e.getMessage());
            logApiUtil.info(logMessage);
            logger.error(e.getMessage(),e);
        }

    }

    void saveEmpEmployee(TaskCreateMsgDTO taskMsgDTO,AmEmpTaskBO bo,Long empTaskId){

        EmployeeHireInfoQueryDTO employeeHireInfoQueryDTO = new EmployeeHireInfoQueryDTO();
        employeeHireInfoQueryDTO.setCompanyId(bo.getCompanyId());
        employeeHireInfoQueryDTO.setEmployeeId(bo.getEmployeeId());
        JsonResult<EmployeeHireInfoDTO> employeeHireInfo = null;//雇佣雇佣信息接口
        com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<CompanyTypeDTO> comDto = null;
        AmEmpEmployee  amEmpEmployee = new AmEmpEmployee();
        try {
            employeeHireInfo = employeeInfoProxy.getEmployeeHireInfo(employeeHireInfoQueryDTO);

            EmployeeHireInfoDTO employeeHireInfoDTO = employeeHireInfo.getData();

            amEmpEmployee.setEmployeeId(bo.getEmployeeId());
            amEmpEmployee.setCompanyId(bo.getCompanyId());
            amEmpEmployee.setEmpTaskId(empTaskId);
            amEmpEmployee.setLaborStartDate(employeeHireInfoDTO.getLaborStartDate());
            amEmpEmployee.setLaborEndDate(employeeHireInfoDTO.getLaborEndDate());
            amEmpEmployee.setGender(employeeHireInfoDTO.getGender());
            amEmpEmployee.setIdNum(employeeHireInfoDTO.getIdNum());
            amEmpEmployee.setEmployeeName(employeeHireInfoDTO.getEmployeeName());
            amEmpEmployee.setPosition(employeeHireInfoDTO.getPosition());
            //amEmpEmployee.setOrganizationCode(employeeHireInfoDTO.getOrganizationCode());
            comDto = companyProxy.getCompanyCoreInfo(bo.getCompanyId());
            if(comDto.getObject() != null){
                amEmpEmployee.setOrganizationCode(comDto.getObject().getOrganizationCode());
            }
            amEmpEmployee.setFirstInDate(employeeHireInfoDTO.getFirstInDate());
            amEmpEmployee.setFirstInCompanyDate(employeeHireInfoDTO.getFirstInCompanyDate());
            amEmpEmployee.setResidenceAddress(employeeHireInfoDTO.getResidenceAddress());
            amEmpEmployee.setMobile(employeeHireInfoDTO.getMobile());

            try {
                SMUserInfoDTO smUserInfoDTO = null;
                if(!StringUtil.isEmpty(employeeHireInfoDTO.getEmployeeCenterOperator()))
                {
                    smUserInfoDTO = employeeInfoProxy.getUserInfo(employeeHireInfoDTO.getEmployeeCenterOperator());
                    amEmpEmployee.setEmployeeCenterOperator(smUserInfoDTO==null?"":smUserInfoDTO.getDisplayName());
                }
            } catch (Exception e) {

            }

            //调用客服中心接口
            AfEmployeeCompanyDTO afEmployeeCompanyDTO = null;
            try {
                AfEmployeeInfoDTO afEmployeeInfoDTO = employeeInfoProxy.callInfByMissId(taskMsgDTO);
                afEmployeeCompanyDTO = afEmployeeInfoDTO.getEmployeeCompany();

                if(afEmployeeCompanyDTO!=null)
                {
                    amEmpEmployee.setHireUnit(afEmployeeCompanyDTO.getHireUnit());
                    amEmpEmployee.setPosition(afEmployeeCompanyDTO.getPosition());

                    Map<String,Object> param0 = new HashMap<>();
                    List<AmEmpTaskBO> list = null;
                    if(afEmployeeCompanyDTO.getHireUnit()==1){ //独立户
                        param0.put("companyId",afEmployeeCompanyDTO.getCompanyId());
                        list = baseMapper.querySocial(param0);
                    }else {//大库
                        list = baseMapper.querySocialCi();
                    }

                    if(list!=null&&list.size()>0)
                    {
                        amEmpEmployee.setSsAccount(list.get(0).getSsAccount());
                        amEmpEmployee.setSettlementArea(list.get(0).getSettlementArea());
                        amEmpEmployee.setAccountRepairDate(list.get(0).getAccountRepairDate());
                        amEmpEmployee.setSsPwd(list.get(0).getSsPwd());
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }

            //单位性质
            CompanyTypeDTO companyTypeDTO = employeeInfoProxy.getCompanyType(bo.getCompanyId());
            amEmpEmployee.setCompanyType(companyTypeDTO==null?"":companyTypeDTO.getTypeName());

            //档案费
            try {
                Long ll = Long.parseLong(taskMsgDTO.getMissionId());
                List<AfEmpProductDTO> afEmpProductDTOList = afEmployeeProductProxy.getByEmpAgreement(ll,1);
                for(AfEmpProductDTO afEmpProductDTO:afEmpProductDTOList)
                {
                    if("CPJSW1800005".equals(afEmpProductDTO.getBasicProductId())){
                        amEmpEmployee.setFileFee("有");
                    }
                }
            } catch (NumberFormatException e) {

            }

            amEmpEmployeeService.insert(amEmpEmployee);
        } catch (Exception e) {
            LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(e.getMessage());
            logApiUtil.info(logMessage);
            logger.error(e.getMessage(),e);
        }

        try {
            AmArchiveAdvanceBO advanceBO = amArchiveAdvanceService.queryAmArchiveAdvanceByNameIdcard(amEmpEmployee.getEmployeeName(),amEmpEmployee.getIdNum(),1);
            if(null!=advanceBO)
            {
                AmEmployment amEmployment = new AmEmployment();
                amEmployment.setEmpTaskId(bo.getEmpTaskId());
                amEmployment.setEmployeeId(bo.getEmployeeId());
                amEmployment.setCompanyId(bo.getCompanyId());
                LocalDateTime now = LocalDateTime.now();
                amEmployment.setCreatedTime(now);
                amEmployment.setModifiedTime(now);
                amEmployment.setIsActive(1);
                amEmployment.setCreatedBy(bo.getCreatedBy());
                amEmployment.setModifiedBy(bo.getCreatedBy());
                amEmploymentService.insert(amEmployment);

                AmArchive amArchive = new AmArchive();
                amArchive.setEmploymentId(amEmployment.getEmploymentId());
                amArchive.setEmployeeId(bo.getEmployeeId());
                amArchive.setCompanyId(bo.getCompanyId());
                amArchive.setYuliuDocType(advanceBO.getReservedArchiveType());
                amArchive.setYuliuDocNum(advanceBO.getReservedArchiveNo() == null ? "" : advanceBO.getReservedArchiveNo().toString());
                amArchive.setDocFrom(advanceBO.getArchiveSource());// 档案来源
                amArchive.setArchivePlace(advanceBO.getArchivePlace());// 存档地

                amArchive.setCreatedTime(now);
                amArchive.setModifiedTime(now);
                amArchive.setIsActive(1);
                amArchive.setCreatedBy(bo.getCreatedBy());
                amArchive.setModifiedBy(bo.getCreatedBy());

                amArchiveService.insert(amArchive);

                amArchiveAdvanceService.updateAmArchiveAdvance(advanceBO);
            }
        } catch (Exception e) {
            logApiUtil.error(LogMessage.create().setTitle("预增档案").setContent(e.getMessage()));
        }

    }

    AmEmpTask setEmployeeId(AmEmpTask amEmpTask,TaskCreateMsgDTO taskMsgDTO){
        Object empCompanyId = taskMsgDTO.getVariables().get("empCompanyId");
        amEmpTask.setEmpCompanyId(empCompanyId.toString());
        AmEmpTaskBO bo = this.queryAmEmpTaskBO(empCompanyId);
        if(null!=bo){
            amEmpTask.setCompanyId(bo.getCompanyId());
            amEmpTask.setEmployeeId(bo.getEmployeeId());
            amEmpTask.setTaskFormContent(JSON.toJSONString(taskMsgDTO.getVariables()));
        }
        return  amEmpTask;
    }

    AmCustomBO  setCustomBO( AmEmpTask amEmpTask,AmCustomBO customBO,String companyName){
        if(amEmpTask!=null&&amEmpTask.getEmployCode()!=null)
        {
            if(amEmpTask.getEmployCode()==1){//是独立
                customBO.setCompanyName(companyName);
            }else if(amEmpTask.getEmployCode()==2){
                customBO.setCompanyName("中智上海经济技术合作公司");
            }else if(amEmpTask.getEmployCode()==3){
                customBO.setCompanyName(companyName);
                customBO.setCici("上海中智项目外包咨询服务有限公司");
            }
//            customBO.setTaskId(amEmpTask.getTaskId());
        }else{
            customBO.setCompanyName(companyName);
        }

        return customBO;
    }


    @Override
    public List<AmEmpDispatchExportPageDTO> queryExportOptDispatch(AmEmpTaskBO amEmpTaskBO, Integer employCode, Integer pageCount) {

        List<AmEmpDispatchExportPageDTO> result = new ArrayList<>();

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if (!StringUtil.isEmpty(amEmpTaskBO.getParams())) {
            String arr[] = amEmpTaskBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
            if(amEmpTaskBO.getParams().indexOf("material_name")!=-1){
                amEmpTaskBO.setMaterial("1");
            }
        }

        // 中智大库 还是外包
        param.add("a.employ_code=" + employCode);
        amEmpTaskBO.setParam(param);
        amEmpTaskBO.setOrderParam(orderParam);

        if (null != amEmpTaskBO.getTaskStatus() && amEmpTaskBO.getTaskStatus() == 0) {
            amEmpTaskBO.setTaskStatus(null);
        }

        PageInfo pageInfo  = new PageInfo();
        pageInfo.setPageSize(pageCount);
        List<AmEmpTaskBO> list = baseMapper.taskCount(amEmpTaskBO);
        Integer count = 0;
        for (AmEmpTaskBO bo:list) {
            count += bo.getCount();
        }
        Integer pageSize = (count-1)/pageCount +1;
        for (int i = 1;i<=pageSize;i++){
            pageInfo.setPageNum(i);
            AmEmpDispatchExportPageDTO dtoList = new AmEmpDispatchExportPageDTO();
            List<AmEmpDispatchExportDTO> exportList = new ArrayList<>();
            PageRows<AmEmpTaskBO> pageRows = null;
            if(amEmpTaskBO.getTaskStatus()!=null&&amEmpTaskBO.getTaskStatus()==6){
                amEmpTaskBO.setTaskStatusOther(0);
            }

            pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmEmpTask(amEmpTaskBO));

            List<AmEmpTaskBO> amList = pageRows.getRows();
            for (AmEmpTaskBO b:amList ) {
                AmEmpDispatchExportDTO dto =  getAmEmpDispatchExportDTO(b);
                if(dtoList.getCreatedBy() == null){// 用工操作员
                    dtoList.setCreatedBy(b.getEmployOperateMan());
                }
                if(dtoList.getCreatedTime() == null){// 开F单日期
                    dtoList.setCreatedTime(b.getOpenAfDate());
                }
                if(dto.getLaborStartDate()==null||dto.getLaborEndDate()==null){
                    dto.setTimeLimitForDispatch("5年以上");
                }else{
                    dto.setTimeLimitForDispatch(ReasonUtil.getCondemnationYears(dto.getLaborStartDate(),dto.getLaborEndDate()));
                }
                dto.setSendOut(b.getCompanyType());
                exportList.add(dto);
            }
            if(exportList.size()!=0){
                // 中智大库和外包公司title 不一样
                dtoList.setSuperiorDepartment("无");
                dtoList.setCompanyName(employCode==2?"中智上海经济技术合作有限公司":employCode==3?"上海中智项目外包咨询服务有限公司":"");
                dtoList.setCompanyType("国有");
                dtoList.setOrganizationCode(employCode==2?"132224030":employCode==3?"669359349":"");
                dtoList.setCompanyAddress(employCode==2?"上海市徐汇区衡山路922号18楼":employCode==3?"上海市徐汇区衡山路922号8楼":"");
                dtoList.setPostalCode("200030");
                dtoList.setIndustryCategory("职业中介");
                dtoList.setMembership(employCode==2?"中央属":employCode==3?"无":"");
                dtoList.setLinkman("");
                dtoList.setLinkPhone("54594545");
                dtoList.setSsAccount(employCode==2?"00048926":employCode==3?"00309096":"");//社保登记码
                dtoList.setSettlementArea("徐汇");
                dtoList.setList(exportList);
                result.add(dtoList);
            }
        }
        return result;
    }

    public AmEmpDispatchExportDTO getAmEmpDispatchExportDTO(AmEmpTaskBO b){
        AmEmpDispatchExportDTO dto = new AmEmpDispatchExportDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BeanUtils.copyProperties(b,dto);
        try {
            if(b.getLaborStartDate()!=null){
                dto.setLaborStartDate(sdf.parse(b.getLaborStartDate()));
            }
            if(b.getLaborEndDate()!=null){
                dto.setLaborEndDate(sdf.parse(b.getLaborEndDate()));
            }
            dto.setEmploymentStartDate(DateUtil.localDateToDate(b.getEmployDate()));
            // 派遣期限
            if(dto.getLaborEndDate() == null || dto.getLaborStartDate() == null){
                dto.setTimeLimitForDispatch("4");
            }else{
                String s1 = sdf.format(dto.getLaborStartDate());
                String s2 = sdf.format(dto.getLaborEndDate());
                Integer i1 = Integer.parseInt(s1.substring(0,4));
                Integer i2 = Integer.parseInt(s2.substring(0,4));
                switch ((i2-i1)){
                    case 0:
                    case 1:
                        dto.setTimeLimitForDispatch("1");
                        break;
                    case 2:
                        dto.setTimeLimitForDispatch("2");
                        break;
                    case 3:
                    case 4:
                    case 5:
                        dto.setTimeLimitForDispatch("3");
                        break;
                    default:
                        dto.setTimeLimitForDispatch("4");
                        break;
                }
            }
        }catch (Exception e){
        }
        return dto;
    }


    @Override
    public List<AmEmpDispatchExportPageDTO> queryExportOptDispatch(AmEmpTaskBO amEmpTaskBO, Integer pageCount) {

        List<AmEmpDispatchExportPageDTO> result = new ArrayList<>();

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if (!StringUtil.isEmpty(amEmpTaskBO.getParams())) {
            String arr[] = amEmpTaskBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
            if(amEmpTaskBO.getParams().indexOf("material_name")!=-1){
                amEmpTaskBO.setMaterial("1");
            }
        }

        // 固定为独立户
        param.add("a.employ_code=" + 1);
        amEmpTaskBO.setParam(param);
        amEmpTaskBO.setOrderParam(orderParam);

        if (null != amEmpTaskBO.getTaskStatus() && amEmpTaskBO.getTaskStatus() == 0) {
            amEmpTaskBO.setTaskStatus(null);
        }

        List<String> companys = baseMapper.queryAmEmpTaskCompanys(amEmpTaskBO);

        for (String companyId:companys) {

            param.add("a.company_id='"+companyId+"'");

            PageInfo pageInfo  = new PageInfo();
            pageInfo.setPageSize(pageCount);

            List<AmEmpTaskBO> list = new ArrayList<>();
            try {

                list = baseMapper.taskCount(amEmpTaskBO);
            }catch (Exception e){
                e.printStackTrace();
            }
            Integer count = 0;
            for (AmEmpTaskBO bo:list) {
                count += bo.getCount();
            }
            Integer pageSize = (count-1)/pageCount +1;
            for (int i = 1;i<=pageSize;i++){
                pageInfo.setPageNum(i);
                AmEmpDispatchExportPageDTO dtoList = new AmEmpDispatchExportPageDTO();

                List<AmEmpDispatchExportDTO> exportList = new ArrayList<>();
                PageRows<AmEmpTaskBO> pageRows = null;
                if(amEmpTaskBO.getTaskStatus()!=null&&amEmpTaskBO.getTaskStatus()==6){
                    amEmpTaskBO.setTaskStatusOther(0);
                }

                pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmEmpTask(amEmpTaskBO));

                List<AmEmpTaskBO> amList = pageRows.getRows();
                for (AmEmpTaskBO b:amList ) {
                    AmEmpDispatchExportDTO dto = getAmEmpDispatchExportDTO(b);
                    if(dtoList.getCreatedBy() == null){// 用工操作员
                        dtoList.setCreatedBy(b.getEmployOperateMan());
                    }
                    if(dtoList.getCreatedTime() == null){// 开F单日期
                        dtoList.setCreatedTime(b.getOpenAfDate());
                    }
                    if(dto.getLaborStartDate()==null||dto.getLaborEndDate()==null){
                        dto.setTimeLimitForDispatch("5年以上");
                    }else{
                        dto.setTimeLimitForDispatch(ReasonUtil.getCondemnationYears(dto.getLaborStartDate(),dto.getLaborEndDate()));
                    }
                    dto.setSendOut(b.getCompanyType());
                    exportList.add(dto);
                }
                if(exportList.size()!=0){
                    // 独立户公司title信息
                    com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO> companyDto = companyProxy.afDetail(companyId);

                    dtoList.setSuperiorDepartment("无");// 上级部门主管
                    dtoList.setCompanyName(companyDto.getObject().getCompanyName());
                    dtoList.setCompanyType(companyDto.getObject().getCompanyTypeName());// 单位性质
                    dtoList.setOrganizationCode(companyDto.getObject().getOrganizationCode()==null || companyDto.getObject().getOrganizationCode().length()<9?"         "
                        :companyDto.getObject().getOrganizationCode());// 组织机构代码
                    dtoList.setCompanyAddress(companyDto.getObject().getBusinessAddress());// 营业地址
                    dtoList.setPostalCode(companyDto.getObject().getBusinessZipCode()==null || companyDto.getObject().getBusinessZipCode().length()<6?"         "
                        :companyDto.getObject().getBusinessZipCode());// 邮编
                    dtoList.setIndustryCategory(companyDto.getObject().getIndustryCategoryName());// 行业类别
                    dtoList.setMembership("");
                    dtoList.setLinkman(UserContext.getUser().getDisplayName());
                    dtoList.setLinkPhone("54594545");
                    com.ciicsh.common.entity.JsonResult<SsComAccountDTO> accountResult = socApiProxy.getSsComAccountByComId(companyId);
                    if(accountResult.getData()!=null){
                        String account = accountResult.getData().getSsAccount();
                        dtoList.setSsAccount(account==null||account.length()<8?"        ":account);//社保登记码
                        dtoList.setSettlementArea(accountResult.getData().getSettlementArea());
                    }
                    dtoList.setList(exportList);
                    result.add(dtoList);
                }
            }
            param.remove(param.size()-1);
        }
        return result;
    }

    @Override
    public List<AmEmpTaskBO> queryAmEmpTaskCompanyNames(AmEmpTaskBO amEmpTaskBO) {


        return null;
    }

    @Override
    public List<AmEmpCollectExportPageDTO> queryExportOptCollect(AmEmpTaskBO amEmpTaskBO, Integer employCode) {

        List<AmEmpCollectExportPageDTO> result = new ArrayList<>();

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if (!StringUtil.isEmpty(amEmpTaskBO.getParams())) {
            String arr[] = amEmpTaskBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
            if(amEmpTaskBO.getParams().indexOf("material_name")!=-1){
                amEmpTaskBO.setMaterial("1");
            }
        }

        // 中智大库 还是外包
        param.add("a.employ_code=" + employCode);
        amEmpTaskBO.setParam(param);
        amEmpTaskBO.setOrderParam(orderParam);

        if (null != amEmpTaskBO.getTaskStatus() && amEmpTaskBO.getTaskStatus() == 0) {
            amEmpTaskBO.setTaskStatus(null);
        }
        PageInfo pageInfo  = new PageInfo();
        pageInfo.setPageSize(30);
        List<AmEmpTaskBO> list = baseMapper.taskCount(amEmpTaskBO);
        Integer count = 0;
        for (AmEmpTaskBO bo:list) {
            count += bo.getCount();
        }
        Integer pageSize = (count-1)/30 +1;
        for (int i = 1;i<=pageSize;i++){
            pageInfo.setPageNum(i);
            PageRows<AmEmpTaskBO> pageRows = null;
            if(amEmpTaskBO.getTaskStatus()!=null&&amEmpTaskBO.getTaskStatus()==6){
                amEmpTaskBO.setTaskStatusOther(0);
            }
            pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmEmpTask(amEmpTaskBO));
            AmEmpCollectExportPageDTO pageDTO = new AmEmpCollectExportPageDTO();
            if(employCode == 2){
                pageDTO.setCompanyName("中智上海经济技术合作公司");
                pageDTO.setSsAccount("00048926");
                pageDTO.setSettlementArea("徐汇");
            }
            if(employCode == 3){
                pageDTO.setCompanyName("上海中智项目外包咨询服务有限公司");
                pageDTO.setSsAccount("00309096");
                pageDTO.setSettlementArea("徐汇");
            }
            List<AmEmpTaskBO> boList = pageRows.getRows();
            List<AmEmpCollectExportDTO> list1 = new ArrayList<>();
            List<AmEmpCollectExportDTO> list2 = new ArrayList<>();
            List<AmEmpCollectExportDTO> list3 = new ArrayList<>();
             for (int n = 0;n<boList.size();n++){
                AmEmpCollectExportDTO collectDto = new AmEmpCollectExportDTO();
                collectDto.setId((n+1));
                 collectDto.setIdNum(boList.get(n).getIdNum());
                 collectDto.setEmployeeName(boList.get(n).getEmployeeName());
                if(n<=9){
                    list1.add(collectDto);
                }else if(n>9 && n<=19){
                    list2.add(collectDto);
                }else if(n>19){
                    list3.add(collectDto);
                }
            }
            pageDTO.setList1(list1);
            pageDTO.setList2(list2);
            pageDTO.setList3(list3);
            if(list1.size()>0){
                result.add(pageDTO);
            }
        }
        return result;
    }

    @Override
    public List<AmEmpCollectExportPageDTO> queryExportOptCollect(AmEmpTaskBO amEmpTaskBO) {

        List<AmEmpCollectExportPageDTO> result = new ArrayList<>();

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if (!StringUtil.isEmpty(amEmpTaskBO.getParams())) {
            String arr[] = amEmpTaskBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
            if(amEmpTaskBO.getParams().indexOf("material_name")!=-1){
                amEmpTaskBO.setMaterial("1");
            }
        }

        if (null != amEmpTaskBO.getTaskStatus() && amEmpTaskBO.getTaskStatus() == 0) {
            amEmpTaskBO.setTaskStatus(null);
        }

        // 独立户
        param.add("a.employ_code=" + 1);
        amEmpTaskBO.setOrderParam(orderParam);
        amEmpTaskBO.setParam(param);
        List<String> companys = baseMapper.queryAmEmpTaskCompanys(amEmpTaskBO);
        for (String company:companys) {
            param.add("a.company_id='"+company+"'");
            amEmpTaskBO.setParam(param);
            PageInfo pageInfo  = new PageInfo();
            pageInfo.setPageSize(30);
            List<AmEmpTaskBO> list = baseMapper.taskCount(amEmpTaskBO);
            Integer count = 0;
            for (AmEmpTaskBO bo:list) {
                count += bo.getCount();
            }
            Integer pageSize = (count-1)/30 +1;
            for (int i = 1;i<=pageSize;i++){
                pageInfo.setPageNum(i);
                PageRows<AmEmpTaskBO> pageRows = null;
                if(amEmpTaskBO.getTaskStatus()!=null&&amEmpTaskBO.getTaskStatus()==6){
                    amEmpTaskBO.setTaskStatusOther(0);
                }
                pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmEmpTask(amEmpTaskBO));
                AmEmpCollectExportPageDTO pageDTO = new AmEmpCollectExportPageDTO();
                // 独立户公司title信息
                com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO> companyDto = companyProxy.afDetail(company);
                pageDTO.setCompanyName(companyDto.getObject().getCompanyName());
                com.ciicsh.common.entity.JsonResult<SsComAccountDTO> accountResult = socApiProxy.getSsComAccountByComId(company);
                if(accountResult.getData()!=null){
                    String account = accountResult.getData().getSsAccount();
                    pageDTO.setSsAccount(account==null||account.length()<8?"        ":account);//社保登记码
                    pageDTO.setSettlementArea(accountResult.getData().getSettlementArea());
                }
                List<AmEmpTaskBO> boList = pageRows.getRows();
                List<AmEmpCollectExportDTO> list1 = new ArrayList<>();
                List<AmEmpCollectExportDTO> list2 = new ArrayList<>();
                List<AmEmpCollectExportDTO> list3 = new ArrayList<>();
                for (int n = 0;n<boList.size();n++){
                    AmEmpCollectExportDTO collectDto = new AmEmpCollectExportDTO();
                    collectDto.setId((n+1));
                    collectDto.setEmployeeName(boList.get(n).getEmployeeName());
                    collectDto.setIdNum(boList.get(n).getIdNum());
                    if(n<=9){
                        list1.add(collectDto);
                    }else if(n>9 && n<=19){
                        list2.add(collectDto);
                    }else if(n>19){
                        list3.add(collectDto);
                    }
                }
                pageDTO.setList1(list1);
                pageDTO.setList2(list2);
                pageDTO.setList3(list3);
                result.add(pageDTO);
            }
            param.remove(param.size()-1);
        }

        return result;
    }

    @Override
    public List<AmEmpTaskBO> jobCount(AmEmpTaskBO amEmpTaskBO) {
        return  baseMapper.jobCount(amEmpTaskBO);
    }

    @Override
    public Map<String, Object> batchCheckArchive(EmployeeBatchBO employeeBatchBO) {
        Map<String,Object> resultMap = new HashMap<>();
//        List<AmEmpTaskBO> amEmpTaskBOList = baseMapper.queryIsFinish(employeeBatchBO);
//        if(null!=amEmpTaskBOList&&amEmpTaskBOList.size()>0)
//        {
//            resultMap.put("empTask",amEmpTaskBOList.size());
//            return  resultMap;
//        }
        List<AmEmploymentBO> amEmploymentBOList = amEmploymentService.queryAmEmploymentCount(employeeBatchBO);
        if(null!=amEmploymentBOList)
        {
           if(amEmploymentBOList.size()<employeeBatchBO.getEmpTaskIds().size())
           {
               Integer num = employeeBatchBO.getEmpTaskIds().size()-amEmploymentBOList.size();
               resultMap.put("employmentCount",num);
               return  resultMap;
           }
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> batchSaveArchive(AmArchiveBO amArchiveBO) {
        Map<String,Object> map = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        boolean result = false;
        Map<String,Object> param = new HashMap<>();
        List<AmEmploymentBO> amEmploymentBOList = amEmploymentService.queryAmEmploymentBatch(Arrays.asList(amArchiveBO.getEmpTaskIds()));
        if(amEmploymentBOList==null||amEmploymentBOList.size()==0){
            map.put("message","请先保存用工");
            return  map;
        }
        List<AmArchive> amArchiveList = new ArrayList<>();
        for(AmEmploymentBO temp:amEmploymentBOList)
        {
            param.put("employmentId",temp.getEmploymentId());
            List<AmArchiveBO> amArchiveBOList = amArchiveService.queryAmArchiveList(param);
            AmArchive entity = new AmArchive();
            BeanUtils.copyProperties(amArchiveBO,entity);
            if(null!=amArchiveBOList&&amArchiveBOList.size()>0)
            {
                AmArchiveBO amArchiveBO1 = amArchiveBOList.get(0);
                entity.setArchiveId(amArchiveBO1.getArchiveId());
                entity.setCreatedTime(amArchiveBO1.getCreatedTime());
                entity.setCreatedBy(amArchiveBO1.getCreatedBy());
            }else{
                entity.setCreatedTime(now);
                entity.setCreatedBy(ReasonUtil.getUserId());
            }
            entity.setEmploymentId(temp.getEmploymentId());
            entity.setEmployeeId(temp.getEmployeeId());
            entity.setCompanyId(temp.getCompanyId());
            entity.setModifiedTime(now);
            entity.setModifiedBy(ReasonUtil.getUserId());
            entity.setIsActive(1);

            amArchiveList.add(entity);
        }
        result =  amArchiveService.insertOrUpdateBatch(amArchiveList);
        map.put("result",result);
        return map;
    }


}



