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
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskFollow;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
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
            //TODO
            if (taskDetialDTO.getTaskId() == null) {
                taskMaterial.setCreatedBy("gu");
                taskMaterial.setCreatedTime(new Date());
            }
            taskMaterial.setModifiedBy("gu");
            taskMaterial.setModifiedTime(new Date());
            return JsonResult.success(taskMaterialService.insertOrUpdate(taskMaterial));
        } else {
            return JsonResult.faultMessage();
        }
    }
}
