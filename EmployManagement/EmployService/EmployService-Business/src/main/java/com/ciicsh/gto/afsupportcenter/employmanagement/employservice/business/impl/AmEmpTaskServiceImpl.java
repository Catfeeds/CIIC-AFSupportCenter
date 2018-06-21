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
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMUserInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.JsonResult;
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




    @Override
    public PageRows<AmEmpTaskBO> queryAmEmpTask(PageInfo pageInfo) {

        AmEmpTaskBO amEmpTaskBO = pageInfo.toJavaObject(AmEmpTaskBO.class);

        List<String> param = new ArrayList<String>();

        if (!StringUtil.isEmpty(amEmpTaskBO.getParams())) {
            String arr[] = amEmpTaskBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                param.add(arr[i]);
            }
        }

        amEmpTaskBO.setParam(param);

        if (null != amEmpTaskBO.getTaskStatus() && amEmpTaskBO.getTaskStatus() == 0) {
            amEmpTaskBO.setTaskStatus(null);
        }

        if(amEmpTaskBO.getTaskStatus()!=null&&amEmpTaskBO.getTaskStatus()==6){
            return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmEmpTaskOther(amEmpTaskBO));
        }else{
            return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmEmpTask(amEmpTaskBO));
        }


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

        AmEmpTask amEmpTask = new AmEmpTask();
        amEmpTask.setTaskId(taskMsgDTO.getTaskId());
        amEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        amEmpTask.setTaskCategory(taskCategory);
        amEmpTask.setTaskStatus(1);

        if(StringUtil.isEmpty(taskMsgDTO.getVariables().get("empCompanyId")))
        {
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

            archiveDirection = (String)map.get("archiveDirection");
            employeeNature = (String)map.get("employeeNature");
            EmployeeBO personNature = baseMapper.queryNature(employeeNature);
            EmployeeBO employeeBO  = baseMapper.queryArchiveDriection(archiveDirection);
            if(null!=personNature){
                amEmpTask.setEmployeeNature(personNature.getPersonNature());
            }
            if(null!=employeeBO){
                amEmpTask.setArchiveDirection(employeeBO.getArchiveDirection());
            }
            submitterId = taskMsgDTO.getVariables().get("submitterId")==null?"":map.get("submitterId").toString();

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
                amEmpTask.setChange("是");
            }else{
                amEmpTask.setChange("否");
            }


        } catch (Exception e) {
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

        try {
            List<String> list = (List<String>) map.get("materialList");
            SMUserInfoDTO smUserInfoDTO = null;
            if(!StringUtil.isEmpty(submitterId))
            {
                smUserInfoDTO = employeeInfoProxy.getUserInfo(submitterId);
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
                    amEmpMaterial.setSubmitterDate(LocalDate.now());
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
            logger.error(e.getMessage(), e);
        }

        return true;
    }

    @Override
    public boolean insertTaskFire(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception {

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
                amEmpTask.setChange("是");
            }else{
                amEmpTask.setChange("否");
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

        List<AmArchive> amArchiveList = new ArrayList<>();
        List<AmEmpTask> amEmpTaskList = new ArrayList<>();
        String yuliuDocNum = amArchiveBO.getYuliuDocNum();
        String docNum = amArchiveBO.getDocNum();
        Integer yuliuDocNumInt = Integer.parseInt(yuliuDocNum);
        Integer docNumInt = Integer.parseInt(docNum);

        int i = 0;
        for(AmEmployment temp:amEmploymentBOList)
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

            yuliuDocNumInt = yuliuDocNumInt + i;
            docNumInt = docNumInt + i;
            entity.setYuliuDocNum(yuliuDocNumInt.toString());
            entity.setDocNum(docNumInt.toString());
            ++i;

            entity.setEmploymentId(temp.getEmploymentId());
            entity.setEmployeeId(temp.getEmployeeId());
            entity.setCompanyId(temp.getCompanyId());
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

        this.insertOrUpdateBatch(amEmpTaskList);
        result =  amArchiveService.insertOrUpdateAllColumnBatch(amArchiveList);
        if(result)
        {
            // 修改预留档案编号 seq
            if(amArchiveBO.getYuliuDocNum() != null && amArchiveBO.getYuliuDocType() != null){
                AmArchiveDocSeq seq = new AmArchiveDocSeq();
                seq.setType(1);
                seq.setDocType(amArchiveBO.getYuliuDocType());
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
                seq2.setDocSeq(docNumInt);
                List<AmArchiveDocSeqBO> list2 = amArchiveService.queryCountHaveAbove(seq2);
                // 比原有的seq要大
                if(list2.size() == 0){
                    amArchiveService.updateByTypeAndDocType(seq2);
                }
            }
        }
        map.put("result",result);
        map.put("taskIdList",taskIdList);
        return map;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean batchSaveEmployment(EmployeeBatchBO employeeBatchBO) {

        Map<String,Object> param = new HashMap<>();
        List<AmRemark> amRemarkList = new ArrayList<>();
        List<AmEmployment> list = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        String userId = ReasonUtil.getUserId();
        String userName = ReasonUtil.getUserName();
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

            entity.setModifiedTime(now);
            entity.setModifiedBy(userId);
            entity.setIsActive(1);

            AmRemark amRemark = new AmRemark();
            amRemark.setEmpTaskId(emTaskId);
            amRemark.setRemarkContent(employeeBatchBO.getRemarkContent());
            amRemark.setRemarkType(1);
            amRemark.setRemarkMan(userName);
            amRemark.setCreatedBy(userId);
            amRemark.setCreatedTime(now);
            amRemark.setModifiedBy(userId);
            amRemark.setModifiedTime(now);

            amRemarkList.add(amRemark);

            list.add(entity);
        }

        amRemarkService.insertBatch(amRemarkList);

        AmEmpMaterialBO amEmpMaterialBO = new AmEmpMaterialBO();
        amEmpMaterialBO.setEmpTaskIdList(employeeBatchBO.getEmpTaskIds());
        amEmpMaterialBO.setReceiveDate(employeeBatchBO.getReceiveDate());
        amEmpMaterialBO.setReceiveId(userId);
        amEmpMaterialBO.setReceiveName(userName);
        amEmpMaterialBO.setModifiedBy(userId);
        amEmpMaterialBO.setModifiedTime(now);
        amEmpMaterialService.updateMaterialBatch(amEmpMaterialBO);

        boolean b = amEmploymentService.insertOrUpdateAllColumnBatch(list);

        return b;
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

            if(amCompanySetBO1.getCompanySpecial8()!=null&&amCompanySetBO1.getCompanySpecial8()==1){
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
            logger.error(e.getMessage(),e);
        }

    }

    void saveEmpEmployee(TaskCreateMsgDTO taskMsgDTO,AmEmpTaskBO bo,Long empTaskId){

        EmployeeHireInfoQueryDTO employeeHireInfoQueryDTO = new EmployeeHireInfoQueryDTO();
        employeeHireInfoQueryDTO.setCompanyId(bo.getCompanyId());
        employeeHireInfoQueryDTO.setEmployeeId(bo.getEmployeeId());
        JsonResult<EmployeeHireInfoDTO> employeeHireInfo = null;//雇佣雇佣信息接口
        com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<CompanyTypeDTO> comDto = null;
        try {
            employeeHireInfo = employeeInfoProxy.getEmployeeHireInfo(employeeHireInfoQueryDTO);

            EmployeeHireInfoDTO employeeHireInfoDTO = employeeHireInfo.getData();

            AmEmpEmployee  amEmpEmployee = new AmEmpEmployee();
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
            logger.error(e.getMessage(),e);
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


}



