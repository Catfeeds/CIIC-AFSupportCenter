package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.CompanyExtService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskFollowService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskTypeService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TimedTaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.CompanyExtDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.EmpBasePeriodRequestDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskDetialDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskFollowDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskListDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.CompanyExt;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskFollow;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskType;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TimedTask;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SocApiProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsEmpBasePeriodDTO;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.basicdataservice.api.DicItemServiceProxy;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.quotation.QuotationProductResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.QuotationProxy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: guwei
 * @Description: 雇员证件办理控制器
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
    private TaskTypeService taskTypeService;

    @Autowired
    private DicItemServiceProxy dicItemServiceProxy;

    @Autowired
    private SocApiProxy socApiProxy;

    @Autowired
    private QuotationProxy quotationProxy;

    @Autowired
    private TimedTaskService timedTaskService;

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
        if (taskFollowDTO.getTaskFollowId() == null) {
            taskFollow.setCreatedBy(UserContext.getUser().getDisplayName());
            taskFollow.setCreatedTime(new Date());
        }
        taskFollow.setModifiedBy(UserContext.getUser().getDisplayName());
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
        tasks.stream().forEach((Task i) -> {
            TaskListDTO taskListDTO = new TaskListDTO();
            BeanUtils.copyProperties(i,taskListDTO);
            taskListDTO.setDegree(i.getDegree() == null ? "" : i.getDegree().toString());
            taskListDTO.setQualification(i.getQualification() == null ? "" : i.getQualification().toString());
            if (i.getCredentialsType() != null){
                taskListDTO.setCredentialsTypeN(SelectionUtils.credentials(i.getCredentialsType()));
            }
            if (i.getCredentialsDealType() != null){
                taskListDTO.setCredentialsDealTypeN(SelectionUtils.credentialsDeal(i.getCredentialsDealType()));
            }
            if (i.getPayType() != null) {
                taskListDTO.setPayType(String.valueOf(i.getPayType()));
            }

            List<DicItemDTO> qualification = dicItemServiceProxy.listByDicValue("education");
            Map<String, String> qualificationMap = qualification.stream().collect(Collectors.toMap(DicItemDTO::getDicItemValue, DicItemDTO::getDicItemText));
            List<DicItemDTO> degree = dicItemServiceProxy.listByDicValue("degree");
            Map<String, String> degreeMap = degree.stream().collect(Collectors.toMap(DicItemDTO::getDicItemValue, DicItemDTO::getDicItemText));
            if (taskListDTO.getQualification() != null) {
                taskListDTO.setQualificationName(
                    Optional.ofNullable(qualificationMap).map(qualificationItem -> qualificationItem.get(String.valueOf(taskListDTO.getQualification()))).orElse(""));
            }
            if (taskListDTO.getDegree() != null) {
                taskListDTO.setDegreeName(
                    Optional.ofNullable(degreeMap).map(degreeItem -> degreeItem.get(String.valueOf(taskListDTO.getDegree()))).orElse(""));
            }
            Integer taskType = i.getCredentialsType();
            Integer taskDealType = i.getCredentialsDealType();
            TaskType taskTypeInfo = taskTypeService.selectById(taskDealType == null ? taskType : taskDealType);
            if (StringUtils.isNotBlank(taskTypeInfo.getBasicProductId())) {
                taskListDTO.setBasicProductId(taskTypeInfo.getBasicProductId());
            }
            if (StringUtils.isNotBlank(taskTypeInfo.getProductId())) {
                taskListDTO.setProductId(taskTypeInfo.getProductId());
            }
            List<QuotationProductResponseDTO> products = quotationProxy.queryProductsByIdAndCompanyId(taskListDTO.getCompanyId(), taskListDTO.getProductId()).getObject();
            if (products != null && !products.isEmpty()) {
                taskListDTO.setMoney(new BigDecimal(products.get(0).getPrice()));
            }
            TimedTask timeTask = timedTaskService.select(taskListDTO.getTaskId());
            taskListDTO.setImplement(Optional.ofNullable(timeTask).map(timedTask -> timedTask.getImplement()).orElse(false));
            taskListDTOs.add(taskListDTO);
        });
        return JsonResult.success(taskListDTOs);
    }

    @GetMapping("/getProductPrice")
    public JsonResult getProductPrice(String companyId, String taskType, String taskDealType) {
        TaskListDTO taskListDTO = new TaskListDTO();
        taskListDTO.setCompanyId(companyId);
        TaskType taskTypeInfo = taskTypeService.selectById("null".equals(taskDealType) ? taskType : taskDealType);
        if (StringUtils.isNotBlank(taskTypeInfo.getBasicProductId())) {
            taskListDTO.setBasicProductId(taskTypeInfo.getBasicProductId());
        }
        if (StringUtils.isNotBlank(taskTypeInfo.getProductId())) {
            taskListDTO.setProductId(taskTypeInfo.getProductId());
        }
        List<QuotationProductResponseDTO> products = quotationProxy.queryProductsByIdAndCompanyId(taskListDTO.getCompanyId(), taskListDTO.getProductId()).getObject();
        if (products != null && !products.isEmpty()) {
            taskListDTO.setMoney(new BigDecimal(products.get(0).getPrice()));
        }
        return JsonResult.success(taskListDTO);
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
        return JsonResult.success(taskService.saveOrUpdateTask(taskDetialDTO));
    }

    @GetMapping("/findTaskTypeDetial")
    public JsonResult findTaskType(String taskTypeId) {
        TaskType taskType = taskTypeService.selectById(taskTypeId);
        return JsonResult.success(taskType);
    }

    /**
     * 获取近四年雇员社保变更记录
     * @param empBasePeriodRequestDTO
     * @return
     */
    @PostMapping("/getEmpBasePeriodInfo")
    public JsonResult getEmpBasePeriodInfo(@RequestBody EmpBasePeriodRequestDTO empBasePeriodRequestDTO) {
        Assert.notNull(empBasePeriodRequestDTO,"请求参数不能为空");
        List<SsEmpBasePeriodDTO> data = socApiProxy.getEmpBasePeriodInfo(empBasePeriodRequestDTO.getCompanyId(), empBasePeriodRequestDTO.getEmployeeId()).getData();
        return JsonResult.success(data);
    }

    /**
     * 删除任务单
     * @param taskId
     * @return
     */
    @PostMapping("/del/{taskId}")
    public JsonResult delTaskById(@PathVariable("taskId") String taskId) {
        return JsonResult.success(taskService.deleteTaskById(taskId));
    }

}
