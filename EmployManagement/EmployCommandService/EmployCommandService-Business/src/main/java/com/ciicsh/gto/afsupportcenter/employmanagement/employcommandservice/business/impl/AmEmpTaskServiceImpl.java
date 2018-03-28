package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.*;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCustomBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmTaskParamBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmCompanySetService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpCustomService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpCustom;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.employSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMUserInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeQueryDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.CompanyTypeDTO;
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
    private IAmEmpMaterialService  amEmpMaterialService;

    @Autowired
    private CommonApiUtils employeeInfoProxy;

    @Autowired
    AfEmployeeCompanyProxy afEmployeeCompanyProxy;

    @Autowired
    private  IAmCompanySetService  iAmCompanySetService;

    @Autowired
    private  IAmCompanySetService amCompanySetService;

    @Autowired
    private IAmEmpCustomService  amEmpCustomService;


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
        String submitterId = null;//提交人
        try {
            Map<String, Object> map = taskMsgDTO.getVariables();
            List<String> list = (List<String>) map.get("materialList");
            archiveDirection = (String)map.get("archiveDirection");
            employeeNature = (String)map.get("employeeNature");
            amEmpTask.setArchiveDirection(archiveDirection);
            amEmpTask.setEmployeeNature(employeeNature);
            submitterId = map.get("submitterId")==null?"":map.get("submitterId").toString();
            SMUserInfoDTO smUserInfoDTO = null;
            if(!StringUtil.isEmpty(submitterId))
            {
                smUserInfoDTO = employeeInfoProxy.getUserInfo(submitterId);
            }

            List<AmEmpMaterial> amEmpMaterialsList = new ArrayList<>();
            for(String str:list)
            {
                AmEmpMaterial amEmpMaterial = new AmEmpMaterial();
                amEmpMaterial.setMaterialName(str);
                amEmpMaterial.setEmployeeId(bo.getEmployeeId());
                amEmpMaterial.setOperateType(1);
                amEmpMaterial.setSubmitterId(submitterId);
                amEmpMaterial.setActive(true);
                amEmpMaterial.setCreatedBy("sys");
                amEmpMaterial.setCreatedTime(LocalDateTime.now());
                amEmpMaterial.setModifiedBy("sys");
                amEmpMaterial.setSubmitterDate(LocalDate.now());
                amEmpMaterial.setSubmitterName(smUserInfoDTO==null?"":smUserInfoDTO.getDisplayName());
                amEmpMaterialsList.add(amEmpMaterial);
            }
            amEmpMaterialService.insertBatch(amEmpMaterialsList);
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
                if(employeeCompany.getTemplateType()!=null)
                {
                    amEmpTask.setEmployCode(employeeCompany.getTemplateType());
                    amEmpTask.setEmployProperty(ReasonUtil.getYgsx(employeeCompany.getTemplateType().toString()));
                }
            }

            this.saveEmpCustom(employeeCompany,taskMsgDTO.getTaskId(),bo.getCompanyId());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        amEmpTask.setActive(true);
        amEmpTask.setModifiedTime(LocalDateTime.now());
        amEmpTask.setCreatedTime(LocalDateTime.now());

        baseMapper.insert(amEmpTask);

        return true;
    }

    @Override
    public boolean insertTaskFire(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception {

        AmEmpTask amEmpTask = new AmEmpTask();
        amEmpTask.setTaskId(taskMsgDTO.getTaskId());
        amEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        amEmpTask.setTaskCategory(taskCategory);
        amEmpTask.setTaskStatus(1);

        Object empCompanyId = taskMsgDTO.getVariables().get("empCompanyId");
        if(StringUtil.isEmpty(empCompanyId))
        {
            logger.info("empCompanyId is null ...");
            return false;
        }
        amEmpTask.setEmpCompanyId(empCompanyId.toString());
        AmEmpTaskBO bo = this.queryAmEmpTaskBO(empCompanyId);
        if(null!=bo){
            amEmpTask.setCompanyId(bo.getCompanyId());
            amEmpTask.setEmployeeId(bo.getEmployeeId());
            amEmpTask.setTaskFormContent(JSON.toJSONString(taskMsgDTO.getVariables()));
        }

        //TODO 调用吴敬磊接口传入taskMsgDTO.getMissionId()返回数据
        AfEmployeeInfoDTO dto = null;
        try {
            dto = employeeInfoProxy.callInf(taskMsgDTO);
            AfEmployeeCompanyDTO employeeCompany = dto.getEmployeeCompany();

            amEmpTask.setOutDate(employeeCompany==null?null:employeeCompany.getOutDate());

            if(null!=employeeCompany&&null!=employeeCompany.getOutReason()){
                amEmpTask.setOutReason(ReasonUtil.getReasonOut(employeeCompany.getOutReason().toString()));
            }else{
                if(employeeCompany!=null){
                    logger.info(JSON.toJSONString(employeeCompany));
                }
                logger.info("outReason is null "+"  MissionId is "+taskMsgDTO.getMissionId());
            }
            if(employeeCompany!=null&&employeeCompany.getTemplateType()!=null)
            {
                amEmpTask.setEmployCode(employeeCompany.getTemplateType());
                amEmpTask.setEmployProperty(ReasonUtil.getYgsx(employeeCompany.getTemplateType().toString()));
            }
            if(employeeCompany!=null)
            {
                amEmpTask.setCreatedBy(employeeCompany.getCreatedBy());
                amEmpTask.setModifiedBy(employeeCompany.getModifiedBy());
                amEmpTask.setSubmitterId(employeeCompany.getCreatedBy());
            }

            this.saveEmpCustom(employeeCompany,taskMsgDTO.getTaskId(),bo.getCompanyId());

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
            empTaskBO = baseMapper.queryEmpTask(amEmpTaskBO);
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
    public Map<String, Object> getInformation(AmTaskParamBO param) {

        Map<String,Object> map = new HashMap<>();

        AmCustomBO customBO = new AmCustomBO();//客户信息
        AmEmpTaskBO employeeBO = new AmEmpTaskBO();//雇佣信息
        AmEmpTask amEmpTask = null;
        customBO.setCompanyId(param.getCompanyId());
        try {
            if(null!=param.getEmpTaskId())
            {
                amEmpTask = super.selectById(param.getEmpTaskId());
                employeeBO.setArchiveDirection(amEmpTask==null?"":amEmpTask.getArchiveDirection());
                employeeBO.setEmployeeNature(amEmpTask==null?"":amEmpTask.getEmployeeNature());
                employeeBO.setEmployProperty(amEmpTask==null?"":amEmpTask.getEmployProperty());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        //调用前道接口
        AfEmployeeCompanyDTO afEmployeeCompanyDTO = null;
        try {
            TaskCreateMsgDTO taskMsgDTO = new TaskCreateMsgDTO();
            taskMsgDTO.setMissionId(amEmpTask.getBusinessInterfaceId());
            AfEmployeeInfoDTO afEmployeeInfoDTO = employeeInfoProxy.callInfByMissId(taskMsgDTO);
            afEmployeeCompanyDTO = afEmployeeInfoDTO.getEmployeeCompany();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        if(afEmployeeCompanyDTO!=null)
        {
            employeeBO.setTemplateType(ReasonUtil.getYgsx(afEmployeeCompanyDTO.getTemplateType()==null?"":afEmployeeCompanyDTO.getTemplateType().toString()));
            employeeBO.setPosition(afEmployeeCompanyDTO.getPosition());

            Map<String,Object> param0 = new HashMap<>();
            if(afEmployeeCompanyDTO.getTemplateType()==2){
                param0.put("companyId",param.getCompanyId());
            }else {
                param0.put("templateType",afEmployeeCompanyDTO.getTemplateType());
            }
            List<AmEmpTaskBO> list = baseMapper.querySocial(param0);

            if(list!=null&&list.size()>0)
            {
                employeeBO.setSsAccount(list.get(0).getSsAccount());
                employeeBO.setComAccountName(list.get(0).getComAccountName());
                employeeBO.setAccountRepairDate(list.get(0).getAccountRepairDate());
                employeeBO.setSsPwd(list.get(0).getSsPwd());
            }
        }

        EmployeeQueryDTO var1 = new EmployeeQueryDTO();
        var1.setBusinessType(1);
        var1.setIdCardType(param.getIdCardType());
        var1.setIdNum(param.getIdNum());
        com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeInfoDTO> jsonResult = null;//雇佣信息接口

        try {
            jsonResult = employeeInfoProxy.getEmployeeInfo(var1);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        if(null!=jsonResult&&null!=jsonResult.getData()){
            EmployeeInfoDTO employeeInfoDTO = jsonResult.getData();
            employeeBO.setEmployeeId(employeeInfoDTO.getEmployeeId());
            employeeBO.setIdNum(employeeInfoDTO.getIdNum());
            employeeBO.setEmployeeName(employeeInfoDTO.getEmployeeName());
            employeeBO.setSex(employeeInfoDTO.getGender()==0?"女":"男");
            employeeBO.setMobile(employeeInfoDTO.getMobile());
            employeeBO.setResidenceAddress(employeeInfoDTO.getResidenceAddress());
        }

        EmployeeHireInfoQueryDTO employeeHireInfoQueryDTO = new EmployeeHireInfoQueryDTO();
        employeeHireInfoQueryDTO.setCompanyId(param.getCompanyId());
        employeeHireInfoQueryDTO.setEmployeeId(param.getEmployeeId());
        com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeHireInfoDTO> employeeHireInfo = null;//雇佣雇佣信息接口

        try {
            employeeHireInfo = employeeInfoProxy.getEmployeeHireInfo(employeeHireInfoQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(employeeHireInfo!=null&&null!=employeeHireInfo.getData()){
            EmployeeHireInfoDTO employeeHireInfoDTO = employeeHireInfo.getData();
            employeeBO.setFirstInDate(employeeHireInfoDTO.getFirstInDate()==null?"":sdf.format(employeeHireInfoDTO.getFirstInDate()));
            employeeBO.setFirstInCompanyDate(employeeHireInfoDTO.getFirstInCompanyDate()==null?"":sdf.format(employeeHireInfoDTO.getFirstInCompanyDate()));
            employeeBO.setOrganizationCode(employeeHireInfoDTO.getOrganizationCode());
            employeeBO.setPosition(employeeHireInfoDTO.getPosition());
            employeeBO.setLaborStartDate(employeeHireInfoDTO.getLaborStartDate()==null?"":sdf.format(employeeHireInfoDTO.getLaborStartDate()));
            employeeBO.setLaborEndDate(employeeHireInfoDTO.getLaborEndDate()==null?"":sdf.format(employeeHireInfoDTO.getLaborEndDate()));
            if(employeeHireInfoDTO.getLaborEndDate()==null)
            {
                employeeBO.setIsUnlimitedContract("是");
            }else{
                employeeBO.setIsUnlimitedContract("否");

                if(employeeHireInfoDTO.getLaborStartDate()!=null){
                    String d = ReasonUtil.getCondemnationYears(employeeHireInfoDTO.getLaborStartDate(),employeeHireInfoDTO.getLaborEndDate());
                    employeeBO.setSendCondemnationYears(d);
                }
            }

            customBO.setEmployeeCenterOperator(employeeHireInfoDTO.getEmployeeCenterOperator());

        }

        AmCompanySetBO amCompanySetBO = new AmCompanySetBO();
        amCompanySetBO.setCompanyId(param.getCompanyId());
        AmCompanySetBO amCompanySetBO1 = amCompanySetService.queryAmCompanySet(amCompanySetBO);
        if(amCompanySetBO1!=null)
        {
            employeeBO.setEmploySpecial(ReasonUtil.removeMark(amCompanySetBO1.getEmploySpecial()));
            employeeBO.setKeyType(amCompanySetBO1.getKeyType());
            employeeBO.setKeyCode(amCompanySetBO1.getKeyCode());
            employeeBO.setKeyPwd(amCompanySetBO1.getKeyPwd());
            employeeBO.setKeyStatus(amCompanySetBO1.getKeyStatus());
        }

        //获取单位性质
        CompanyTypeDTO companyTypeDTO = employeeInfoProxy.getCompanyType(param.getCompanyId());
        employeeBO.setCompanyType(companyTypeDTO==null?"":companyTypeDTO.getTypeName());
        if(companyTypeDTO!=null&&companyTypeDTO.isHasFileService()){
            employeeBO.setFileFree("有");
        }


        //获取服务中心
        AfCompanyDetailResponseDTO afCompanyDetailResponseDTO = employeeInfoProxy.getCompanyDetail(param.getCompanyId());
        customBO.setServiceCenter(afCompanyDetailResponseDTO==null?"":afCompanyDetailResponseDTO.getServiceCenter());//服务中心
        String companyName = afCompanyDetailResponseDTO==null?"":afCompanyDetailResponseDTO.getCompanyName();
        if(amEmpTask!=null&&amEmpTask.getEmployCode()!=null)
        {
            if(amEmpTask.getEmployCode()==2){//代理也就是独立
                customBO.setCompanyName(companyName);
            }else if(amEmpTask.getEmployCode()==1){
                customBO.setCompanyName("中智上海经济技术合作公司");
            }else if(amEmpTask.getEmployCode()==3){
                customBO.setCompanyName(companyName);
                customBO.setCici("上海中智项目外包咨询服务有限公司");
            }
            customBO.setTaskId(amEmpTask.getTaskId());
        }else{
            customBO.setCompanyName(companyName);
        }

        try {
            AmCustomBO amCustomBO  = amEmpCustomService.getCustom(customBO);
            customBO.setLeaderShipName(amCustomBO==null?"":amCustomBO.getLeaderShipName());//客服经理
            customBO.setCreatedDisplayName(amCustomBO==null?"":amCustomBO.getCreatedDisplayName());//客服专员
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }


        map.put("customBO",customBO);
        map.put("employeeBO",employeeBO);

        return map;
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
        AmEmpTask amEmpTask = super.selectById(amEmpTaskBO.getEmpTaskId());
        AmEmpTaskBO amEmpTaskBO1 = new AmEmpTaskBO();
        BeanUtils.copyProperties(amEmpTask,amEmpTaskBO1);

        amEmpTaskBO1 = defaultRule(amEmpTaskBO1);

        EmployeeHireInfoQueryDTO employeeHireInfoQueryDTO = new EmployeeHireInfoQueryDTO();
        employeeHireInfoQueryDTO.setCompanyId(amEmpTaskBO.getCompanyId());
        employeeHireInfoQueryDTO.setEmployeeId(amEmpTaskBO.getEmployeeId());
        com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeHireInfoDTO> employeeHireInfo = null;//雇佣雇佣信息接口

        try {
            employeeHireInfo = employeeInfoProxy.getEmployeeHireInfo(employeeHireInfoQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        //调用前道接口
        AfEmployeeCompanyDTO afEmployeeCompanyDTO = null;
        try {
            TaskCreateMsgDTO taskMsgDTO = new TaskCreateMsgDTO();
            taskMsgDTO.setMissionId(amEmpTask.getBusinessInterfaceId());
            AfEmployeeInfoDTO afEmployeeInfoDTO = employeeInfoProxy.callInfByMissId(taskMsgDTO);
            afEmployeeCompanyDTO = afEmployeeInfoDTO.getEmployeeCompany();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        if(afEmployeeCompanyDTO!=null)
        {
            /**
             * 用工属性根据雇佣类型  派遣默认中智
              */
          if(afEmployeeCompanyDTO.getTemplateType()==1)
          {
              amEmpTaskBO1.setEmployProperty("中智");
          }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        amEmpTaskBO1.setFirstInDate(sdf.format(employeeHireInfo.getData().getLaborStartDate()));//实际录用日期,合同开始日期
        amEmpTaskBO1.setOpenAfDate(sdf.format(new Date()));
        amEmpTaskBO1.setEmployStyle("1");//默认全日制
        return amEmpTaskBO1;
    }

    AmEmpTaskBO  defaultRule(AmEmpTaskBO amEmpTaskBO){
        if("外来从业人员".equals(amEmpTaskBO.getEmployeeNature())||"居住证".equals(amEmpTaskBO.getEmployeeNature()))
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
        if("退休".equals(amEmpTaskBO.getEmployeeNature())&&"退休".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType("退休");
            amEmpTaskBO.setArchivePlace("退休");
        }
        if("协保".equals(amEmpTaskBO.getEmployeeNature())&&"协保".equals(amEmpTaskBO.getArchiveDirection()))
        {
            amEmpTaskBO.setHandleType("协保");
            amEmpTaskBO.setArchivePlace("协保");
        }
        if("非全日制".equals(amEmpTaskBO.getEmployeeNature())&&"非全日制".equals(amEmpTaskBO.getArchiveDirection()))
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
    void saveEmpCustom(AfEmployeeCompanyDTO employeeCompany,String taskId,String companyId){

        try {
            AfCompanyDetailResponseDTO afCompanyDetailResponseDTO = employeeInfoProxy.getCompanyDetail(companyId);
            AmEmpCustom amEmpCustom = new AmEmpCustom();
            amEmpCustom.setTaskId(taskId);
            amEmpCustom.setCreatedDisplayName(employeeCompany==null?"":employeeCompany.getCreatedDisplayName());//客服专员
            amEmpCustom.setModifiedDisplayName(employeeCompany==null?"":employeeCompany.getCreatedDisplayName());
            amEmpCustom.setLeaderShipId(employeeCompany==null?"":employeeCompany.getLeadershipId());
            amEmpCustom.setLeaderShipName(employeeCompany==null?"":employeeCompany.getLeadershipName());//客服经理
            amEmpCustom.setCreatedBy(employeeCompany==null?"":employeeCompany.getCreatedBy());
            amEmpCustom.setServiceCenter(afCompanyDetailResponseDTO.getServiceCenter());
            amEmpCustom.setActive(true);
            amEmpCustom.setCreatedTime(LocalDateTime.now());
            amEmpCustom.setModifiedTime(LocalDateTime.now());

            amEmpCustomService.insert(amEmpCustom);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

    }


}
