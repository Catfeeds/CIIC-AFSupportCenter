package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客服系统接口调用
 * </p>
 *
 * @author zhangxj
 * @since 2018-1-4
 */
@RestController
@RequestMapping("/api/soccommandservice/taskSheetApi")
public class TaskSheetController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Autowired
    private SheetServiceProxy sheetServiceProxy;
//    @Autowired
//    private EntityIdServiceProxy entityIdServiceProxy;
//
//    @Autowired
//    private ISsEmpTaskService ssEmpTaskService;
//    @Autowired
//    private ISsEmpTaskFrontService ssEmpTaskFrontService;

    @PostMapping(value = "/task/complete", consumes = {"application/json"})
    public com.ciicsh.gto.commonservice.util.dto.Result completeTask(@RequestBody TaskSheetRequestDTO taskSheetRequestDTO) throws Exception {
        logger.info("customer系统调用完成任务接口：" + taskSheetRequestDTO.toString());
        com.ciicsh.gto.commonservice.util.dto.Result restResult =null;
        try {
            TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
            taskRequestDTO.setTaskId(taskSheetRequestDTO.getTaskId());
            taskRequestDTO.setAssignee(taskSheetRequestDTO.getAssignee());
            taskRequestDTO.setVariables(taskSheetRequestDTO.getVariable());

            restResult= sheetServiceProxy.completeTask(taskRequestDTO);
            logger.info("customer系统收到完成任务接口返回：" + String.valueOf("code:" + restResult.getCode() + "message:") + restResult.getMessage());
//            return ResultGenerator.genSuccessResult(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
//            return ResultGenerator.genServerFailResult();
        }
        return restResult;
    }
//
//    @PostMapping(value = "/task/completeTest")
//    public Result completeTaskTest(@RequestBody TaskSheetRequestDTO taskSheetRequestDTO) throws Exception {
//        logger.info("customer系统调用完成任务接口：");
//        try {
////            TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
////            taskRequestDTO.setTaskId(taskSheetRequestDTO.getTaskId());
////            taskRequestDTO.setAssignee(taskSheetRequestDTO.getAssignee());
////            taskRequestDTO.setVariable(taskSheetRequestDTO.getVariable());
////
////            Result restResult = sheetServiceProxy.completeTask(taskRequestDTO);
//
//            TaskMsgDTO dd = new TaskMsgDTO();
//            dd.setTaskId("1");
//            dd.setMissionId("11");
//            insertTaskTb(dd, 1);
//
//            logger.info("customer系统收到完成任务接口返回：");
//            return ResultGenerator.genSuccessResult(true);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return ResultGenerator.genServerFailResult();
//        }
//    }
//
//    private EmployeeInfoDTO callInf(TaskMsgDTO taskMsgDTO) {
////        logger.info("数据获取接口开始调用："+taskSheetRequestDTO.toString());
////        try {
////            TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
////            taskRequestDTO.setTaskId(taskSheetRequestDTO.getTaskId());
////            taskRequestDTO.setAssignee(taskSheetRequestDTO.getAssignee());
////            taskRequestDTO.setVariable(taskSheetRequestDTO.getVariable());
////            //
////            Result restResult = sheetServiceProxy.completeTask(taskRequestDTO);
////            logger.info("数据获取接口返回："+String.valueOf("code:"+restResult.getCode()+"message:")+restResult.getMessage());
////            return ResultGenerator.genSuccessResult(true);
////        } catch (Exception e) {
////            logger.error(e.getMessage(), e);
////            return ResultGenerator.genServerFailResult();
////        }
//        EmployeeInfoDTO bo = new EmployeeInfoDTO();
//        AFEmployeeCompanyDTO d1 = new AFEmployeeCompanyDTO();
//        d1.setCompanyId("1");
//        d1.setInDate(new Date());
//        d1.setOutDate(new Date());
//        bo.setEmployeeCompany(d1);
//
//        List<AFEmpSocialDTO> empSocial = new ArrayList<>();
//        AFEmpSocialDTO dd2 = new AFEmpSocialDTO();
//        dd2.setEmpBase(new BigDecimal(12));
//        empSocial.add(dd2);
//        dd2 = new AFEmpSocialDTO();
//        dd2.setEmpBase(new BigDecimal(13));
//        empSocial.add(dd2);
//        bo.setEmpSocial(empSocial);
//        return bo;
//    }
//
//    private void insertTaskTb(TaskMsgDTO taskMsgDTO, Integer taskCategory) {
//        //调用接口
//        EmployeeInfoDTO bo = callInf(taskMsgDTO);
//        AFEmployeeCompanyDTO companyDto = bo.getEmployeeCompany();
//
//        SsEmpTask ssEmpTask = new SsEmpTask();
//        ssEmpTask.setTaskId(taskMsgDTO.getTaskId());
//        ssEmpTask.setCompanyId(companyDto.getCompanyId());
//        ssEmpTask.setEmployeeId(companyDto.getEmpId());
//        ssEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
//        ssEmpTask.setSubmitterName(companyDto.getCreatedBy());
//        ssEmpTask.setSalary(companyDto.getSalary());
//        ssEmpTask.setSubmitterRemark(companyDto.getRemark());
//
//        ssEmpTask.setInDate(LocalDateTime.ofInstant(companyDto.getInDate().toInstant(), ZoneId.systemDefault()).toLocalDate());
//        ssEmpTask.setOutDate(LocalDateTime.ofInstant(companyDto.getOutDate().toInstant(), ZoneId.systemDefault()).toLocalDate());
//
//        ssEmpTask.setTaskCategory(taskCategory);
//
//        ssEmpTask.setActive(true);
//        ssEmpTask.setModifiedBy("system");
//        ssEmpTask.setModifiedTime(LocalDateTime.now());
//        ssEmpTask.setCreatedBy("system");
//        ssEmpTask.setCreatedTime(LocalDateTime.now());
//        ssEmpTaskService.insert(ssEmpTask);
//
//        List<AFEmpSocialDTO> socialList = bo.getEmpSocial();
//        List<SsEmpTaskFront> eleList = new ArrayList<>();
//        SsEmpTaskFront ssEmpTaskFront = null;
//        for (AFEmpSocialDTO socialDto : socialList) {
//            ssEmpTaskFront = new SsEmpTaskFront();
//            ssEmpTaskFront.setEmpTaskId(Long.parseLong(taskMsgDTO.getTaskId()));
//            ssEmpTaskFront.setItemDicId(socialDto.getItemDicId());
//            ssEmpTaskFront.setEmpCompanyBase(socialDto.getEmpBase());
//            ssEmpTaskFront.setPolicyId(socialDto.getPolicyId());
//
//            ssEmpTaskFront.setPolicyName(socialDto.getPolicyName());
//            ssEmpTaskFront.setCompanyRatio(socialDto.getCompanyRatio());
//            ssEmpTaskFront.setCompanyBase(socialDto.getCompanyBase());
//            ssEmpTaskFront.setCompanyAmount(socialDto.getCompanyAmount());
//
//            ssEmpTaskFront.setPersonalRatio(socialDto.getPersonalRatio());
//            ssEmpTaskFront.setPersonalBase(socialDto.getPersonalBase());
//            ssEmpTaskFront.setPersonalAmount(socialDto.getPersonalAmount());
//            ssEmpTaskFront.setStartMonth(socialDto.getStartMonth());
//            ssEmpTaskFront.setEndMonth(socialDto.getEndMonth());
//
//            ssEmpTaskFront.setActive(true);
//            ssEmpTaskFront.setModifiedBy(null);
//            ssEmpTaskFront.setModifiedTime(null);
//            ssEmpTaskFront.setCreatedBy("system");
//            ssEmpTaskFront.setCreatedTime(LocalDateTime.now());
//            eleList.add(ssEmpTaskFront);
//        }
//        if (eleList.size() > 0) {
//            ssEmpTaskFrontService.insertBatch(eleList);
//        }
//    }
}
