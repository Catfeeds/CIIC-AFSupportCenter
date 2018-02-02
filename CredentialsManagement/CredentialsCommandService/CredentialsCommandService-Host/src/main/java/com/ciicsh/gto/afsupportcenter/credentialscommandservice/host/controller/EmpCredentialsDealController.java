package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.CompanyExtService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.MaterialTypeRelationService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskFollowService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.CompanyExtDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskDetialDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskFollowDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskListDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.CompanyExt;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.MaterialTypeRelation;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskFollow;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 9:34 2018/1/17
 */
@RestController
@RequestMapping("/api/empCredentialsDeal")
public class EmpCredentialsDealController {

    @Autowired
    private TaskFollowService taskFollowService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CompanyExtService companyExtService;
    @Autowired
    private TaskMaterialService taskMaterialService;
    @Autowired
    private MaterialTypeRelationService materialTypeRelationService;

    /**
     * 查询任务单跟进记录
     * @param taskId
     * @return
     */
    @GetMapping("/find/taskFollow/{taskId}")
    public JsonResult getTaskFollow(@PathVariable("taskId")int taskId){
        List<TaskFollow> taskFollows = taskFollowService.selectList(taskId);
        List<TaskFollowDTO> taskFollowDTOs = new ArrayList<>();
        taskFollows.stream().forEach(i -> {
            TaskFollowDTO taskFollowDTO = new TaskFollowDTO();
            BeanUtils.copyProperties(i,taskFollowDTO);
            taskFollowDTOs.add(taskFollowDTO);
        });
        return JsonResult.success(taskFollowDTOs);
    }

    /**
     * 保存或更新跟进记录
     * @param taskFollowDTO
     * @return
     */
    @PostMapping("/saveOrUpdate/taskFollow")
    public JsonResult saveOrUpdateFollow(@RequestBody TaskFollowDTO taskFollowDTO){
        TaskFollow taskFollow = new TaskFollow();
        BeanUtils.copyProperties(taskFollowDTO,taskFollow);
        //TODO
        if (taskFollowDTO.getTaskFollowId() == null) {
            taskFollow.setCreatedBy("gu");
            taskFollow.setCreatedTime(new Date());
        }
        taskFollow.setModifiedBy("gu");
        taskFollow.setModifiedTime(new Date());
        return JsonResult.success(taskFollowService.insertOrUpdate(taskFollow));
    }

    /**
     * 根据雇员id获取任务单列表
     * @param empId
     * @return
     */
    @GetMapping("/find/task/{empId}")
    public JsonResult getTaskList(@PathVariable("empId") String empId){
        List<TaskListDTO> taskListDTOs = new ArrayList<>();
        List<Task> tasks = taskService.selectByempId(empId);
        tasks.stream().forEach(i -> {
            TaskListDTO taskListDTO = new TaskListDTO();
            BeanUtils.copyProperties(i,taskListDTO);
            if (i.getCredentialsType() != null){
                taskListDTO.setCredentialsTypeN(SelectionUtils.credentials(i.getCredentialsType()));
            }
            if (i.getCredentialsDealType() != null){
                taskListDTO.setCredentialsDealTypeN(SelectionUtils.credentialsDeal(i.getCredentialsDealType()));
            }
            if (i.getPayType() != null) {
                taskListDTO.setPayType(String.valueOf(i.getPayType()));
            }
            taskListDTOs.add(taskListDTO);
        });
        return JsonResult.success(taskListDTOs);
    }

    /**
     * 根据客户和办证类型查询办证信息
     * @param companyId
     * @param credentialsType
     * @return
     */
    @GetMapping("/find/companyExt/{companyId}/{credentialsType}")
    public JsonResult getCompanyExt(@PathVariable("companyId") String companyId, @PathVariable("credentialsType") String credentialsType){
        CompanyExtDTO companyExtDTO = new CompanyExtDTO();
        CompanyExt companyExt = companyExtService.selectItem(companyId, credentialsType);
        BeanUtils.copyProperties(companyExt,companyExtDTO);
        if (companyExt.getOperateType() != null) {
            companyExtDTO.setOperateTypeN(SelectionUtils.operateType(companyExt.getOperateType()));
        }
        if (companyExt.getChargeType() != null) {
            companyExtDTO.setChargeTypeN(SelectionUtils.chargeType(companyExt.getChargeType()));
        }
        if (companyExt.getPayType() != null) {
            companyExtDTO.setPayTypeN(SelectionUtils.payType(companyExt.getPayType()));
        }
        return JsonResult.success(companyExtDTO);
    }

    /**
     * 保存或更新任务单
     * @param taskDetialDTO
     * @return
     */
    @PostMapping("/saveOrUpdate/task")
    public JsonResult saveOrUpdateTask(@RequestBody TaskDetialDTO taskDetialDTO){
        Task task = new Task();
        BeanUtils.copyProperties(taskDetialDTO,task);
        if (StringUtils.isNotBlank(taskDetialDTO.getCredentialsType())) {
            task.setCredentialsType(Integer.parseInt(taskDetialDTO.getCredentialsType()));
        }
        if (StringUtils.isNotBlank(taskDetialDTO.getCredentialsDealType())) {
            task.setCredentialsType(Integer.parseInt(taskDetialDTO.getCredentialsDealType()));
        }
        //TODO
        if (taskDetialDTO.getTaskId() == null) {
            task.setCreatedBy("gu");
            task.setCreatedTime(new Date());
        }
        task.setModifiedBy("gu");
        task.setModifiedTime(new Date());
        boolean b = taskService.insertOrUpdate(task);
        if (b) {
            TaskMaterial taskMaterial = new TaskMaterial();
            taskMaterial.setMaterialIds(taskDetialDTO.getMaterialIds());
            taskMaterial.setTaskId(String.valueOf(taskDetialDTO.getTaskId()));
            taskMaterial.setCompanyId(taskDetialDTO.getCompanyId());
            taskMaterial.setEmployeeId(taskDetialDTO.getEmployeeId());
            //TODO
            if (taskDetialDTO.getTaskId() == null) {
                taskMaterial.setCreatedBy("gu");
                taskMaterial.setCreatedTime(new Date());
                taskMaterial.setModifiedBy("gu");
                taskMaterial.setModifiedTime(new Date());
                return JsonResult.success(taskMaterialService.insert(taskMaterial));
            } else {
                taskMaterial.setModifiedBy("gu");
                taskMaterial.setModifiedTime(new Date());
                return JsonResult.success(taskMaterialService.updateTaskMaterials(taskMaterial));
            }
        } else {
            return JsonResult.faultMessage();
        }
    }

    /**
     * 获取任务单材料收缴信息{level:[materialsId],...}
     * @param taskId
     * @return
     */
    @GetMapping("/find/meterials/{taskId}")
    public JsonResult getMaterials(@PathVariable("taskId") String taskId) {
        TaskMaterial taskMaterial = taskMaterialService.selectByTaskId(taskId);
        HashMap<String, List<String>> resultMap = new HashMap<>(7);
        List list00 = new ArrayList<>();
        List list11 = new ArrayList<>();
        List list12 = new ArrayList<>();
        List list21 = new ArrayList<>();
        List list22 = new ArrayList<>();
        List list30 = new ArrayList<>();
        List list40 = new ArrayList<>();
        if (taskMaterial != null) {
            List<MaterialTypeRelation> materials = materialTypeRelationService.selectList(taskMaterial.getMaterialIds());
            materials.stream().forEach(i -> {
                if ("0-0".equals(i.getLevel())) { list00.add(i.getMaterialId());}
                if ("1-1".equals(i.getLevel())) { list11.add(i.getMaterialId());}
                if ("1-2".equals(i.getLevel())) { list12.add(i.getMaterialId());}
                if ("2-1".equals(i.getLevel())) { list21.add(i.getMaterialId());}
                if ("2-2".equals(i.getLevel())) { list22.add(i.getMaterialId());}
                if ("3-0".equals(i.getLevel())) { list30.add(i.getMaterialId());}
                if ("4-0".equals(i.getLevel())) { list40.add(i.getMaterialId());}
            });
        }
        resultMap.put("main",list00);
        resultMap.put("dh",list11);
        resultMap.put("zh",list12);
        resultMap.put("marryWithoutChild",list21);
        resultMap.put("marryWithChild",list22);
        resultMap.put("remarry",list30);
        resultMap.put("settle",list40);
        return JsonResult.success(resultMap);
    }

    /**
     * 生成材料收缴菜单
     * @param credentialsType
     * @param credentialsDealType
     * @return
     */
    @GetMapping("/find/meterialsMenu")
    public JsonResult materialsMenu(String credentialsType, String credentialsDealType) {
        List<MaterialTypeRelation> materials = materialTypeRelationService.selectMaterials(credentialsType,credentialsDealType);
        return JsonResult.success(materials);
    }
}
