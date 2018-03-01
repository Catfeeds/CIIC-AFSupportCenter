package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeQueryDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工退工任务单 服务实现类
 * </p>
 */
@Service
public class AmEmpTaskServiceImpl extends ServiceImpl<AmEmpTaskMapper, AmEmpTask> implements IAmEmpTaskService {

    @Autowired
    private IAmEmpMaterialService  amEmpMaterialService;

    @Autowired
    private CommonApiUtils employeeInfoProxy;

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

    @Override
    public List<AmEmpTaskBO> queryCustom(String companyId) {
        return null;
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
    public boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) throws Exception {

        AmEmpTask amEmpTask = new AmEmpTask();
        amEmpTask.setTaskId(taskMsgDTO.getTaskId());
        //TODO 调用刘玉庭接口传入taskMsgDTO.getMissionId()返回数据
        Integer empCompanyId = (Integer) taskMsgDTO.getVariables().get("empCompanyId");

        Map<String,Object> param = new HashMap<>();
        param.put("empCompanyId",empCompanyId);
        AmEmpTaskBO bo = baseMapper.selectEmployId(param);

        if(null!=bo){
            amEmpTask.setCompanyId(bo.getCompanyId());
            amEmpTask.setEmployeeId(bo.getEmployeeId());
            amEmpTask.setTaskFormContent(JSON.toJSONString(taskMsgDTO.getVariables()));
        }

        String archiveDirection = null;
        String employeeNature = null;

        //
        try {
            Map<String, Object> map = taskMsgDTO.getVariables();
            List<String> list = (List<String>) map.get("materialList");
            archiveDirection = (String)map.get("archiveDirection");
            employeeNature = (String)map.get("employeeNature");
            amEmpTask.setArchiveDirection(archiveDirection);
            amEmpTask.setEmployeeNature(employeeNature);
            List<AmEmpMaterial> amEmpMaterialsList = new ArrayList<>();
            for(String str:list)
            {
                AmEmpMaterial amEmpMaterial = new AmEmpMaterial();
                amEmpMaterial.setMaterialName(str);
                amEmpMaterial.setEmployeeId(bo.getEmployeeId());
                amEmpMaterial.setSubmitMan("sys");
                amEmpMaterial.setActive(true);
                amEmpMaterial.setCreatedBy("sys");
                amEmpMaterial.setCreatedTime(LocalDateTime.now());
                amEmpMaterial.setModifiedBy("sys");
                amEmpMaterial.setSubmitDate(LocalDate.now());

                amEmpMaterialsList.add(amEmpMaterial);
            }
            amEmpMaterialService.insertBatch(amEmpMaterialsList);
        } catch (Exception e) {

        }
        //

        amEmpTask.setSubmitterId("system");

        amEmpTask.setTaskCategory(taskCategory);
        amEmpTask.setTaskStatus(1);
        amEmpTask.setActive(true);
        amEmpTask.setModifiedBy("system");
        amEmpTask.setModifiedTime(LocalDateTime.now());
        amEmpTask.setCreatedBy("system");
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
    public List<Map<String, Object>> getInformation(Map<String, Object> param) {
        Long empTaskId = (Long)param.get("empTaskId");
        String employeeId = (String)param.get("employeeId");
        String companyId = (String)param.get("companyId");
        String idNum = (String)param.get("idNum");
        Integer idCardType = (Integer)param.get("idCardType");

        AmEmpTaskBO customBO = new AmEmpTaskBO();//客户信息
        AmEmpTaskBO employeeBO = new AmEmpTaskBO();//雇佣信息
        AmEmpTask amEmpTask = null;

        try {
            amEmpTask = super.selectById(empTaskId);
            Map<String, Object> map = JSON.parseObject(amEmpTask.getTaskFormContent(),Map.class);
            String archiveDirection = (String)map.get("archiveDirection");
            String employeeNature = (String)map.get("employeeNature");
            employeeBO.setArchiveDirection(archiveDirection);
            employeeBO.setEmployeeNature(employeeNature);
        } catch (Exception e) {

        }

        EmployeeQueryDTO var1 = new EmployeeQueryDTO();
        var1.setBusinessType(1);
        var1.setIdCardType(idCardType);
        var1.setIdNum(idNum);
        com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeInfoDTO> jsonResult = employeeInfoProxy.getEmployeeInfo(var1);//雇佣信息接口

        EmployeeInfoDTO employeeInfoDTO = jsonResult.getData();
        if(null!=employeeInfoDTO){
            employeeBO.setEmployeeId(employeeInfoDTO.getEmployeeId());
            employeeBO.setIdNum(employeeInfoDTO.getIdNum());
            employeeBO.setEmployeeName(employeeInfoDTO.getEmployeeName());
            employeeBO.setSex(employeeInfoDTO.getGender()==0?"男":"女");
            employeeBO.setMobile(employeeInfoDTO.getMobile());
            employeeBO.setResidenceAddress(employeeInfoDTO.getResidenceAddress());
        }

        EmployeeHireInfoQueryDTO employeeHireInfoQueryDTO = new EmployeeHireInfoQueryDTO();
        employeeHireInfoQueryDTO.setCompanyId(companyId);
        employeeHireInfoQueryDTO.setEmployeeId(employeeId);

        com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeHireInfoDTO> employeeHireInfo = employeeInfoProxy.getEmployeeHireInfo(employeeHireInfoQueryDTO);//雇佣雇佣信息接口
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(employeeHireInfo!=null&&null!=employeeHireInfo.getData()){
            EmployeeHireInfoDTO employeeHireInfoDTO = employeeHireInfo.getData();
            employeeBO.setFirstInDate(sdf.format(employeeHireInfoDTO.getFirstInDate()));
            employeeBO.setFirstInCompanyDate(sdf.format(employeeHireInfoDTO.getFirstInCompanyDate()));
            employeeBO.setOrganizationCode(employeeHireInfoDTO.getOrganizationCode());
            employeeBO.setPosition(employeeHireInfoDTO.getPosition());
            employeeBO.setLaborStartDate(sdf.format(employeeHireInfoDTO.getLaborStartDate()));
            employeeBO.setLaborEndDate(sdf.format(employeeHireInfoDTO.getLaborEndDate()));

            customBO.setServiceCenter(employeeHireInfoDTO.getServiceCenter());
            customBO.setEmployeeCenterOperator(employeeHireInfoDTO.getEmployeeCenterOperator());
            customBO.setCustomServiceOperator(employeeHireInfoDTO.getCustomServiceOperator());
            customBO.setCompanyName(employeeHireInfoDTO.getCompanyName());
            customBO.setCompanyId(employeeHireInfoDTO.getCompanyId());
        }
        return null;
    }


}
