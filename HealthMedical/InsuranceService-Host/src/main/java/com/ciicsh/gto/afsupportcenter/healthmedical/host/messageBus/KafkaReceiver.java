package com.ciicsh.gto.afsupportcenter.healthmedical.host.messageBus;


import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.company.AfCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpInsuranceDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfFullEmployeeDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfCompanyProxy;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfTpaTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.EmployeePaymentJobService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.HealthMedicalJobService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.enums.SysConstants;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfTpaTaskPO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeMemberDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.employeecenter.util.JsonResult;
import com.ciicsh.gto.productcenter.apiservice.api.dto.ProductExtDTO;
import com.ciicsh.gto.productcenter.apiservice.api.proxy.ProductProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyPayStatusDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyReturnTicketDTO;
import com.ciicsh.gto.sheetservice.api.MsgConstants;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaogang
 * @date 17/12/18
 */
@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {

    @Autowired
    private AfTpaTaskService afTpaTaskService;

    @Autowired
    private AfEmployeeCompanyProxy afEmployeeCompanyProxy;

    @Autowired
    private AfCompanyProxy afCompanyProxy;

    @Autowired
    private ProductProxy productProxy;

    @Autowired
    private EmployeeInfoProxy employeeInfoProxy;

    @Autowired
    private EmployeePaymentJobService employeePaymentService;

    @Autowired
    private HealthMedicalJobService healthMedicalJobService;

    @StreamListener(MsgConstants.AFCompanyCenter.AF_EMP_IN)
    public void receiveBaseAdjustYearlyNonlocal(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        String missionID = taskMsgDTO.getMissionId();
        //   taskMsgDTO.getVariables("");
        // logger.info("收到消息from BASE_ADJUST_YEARLY_NONLOCAL-useWork: " + returnInfo);

        boolean res = false;
        // 判断是否投保任务单
        if ("insurance_new".equals(taskMsgDTO.getTaskType())) {
            // 读客服中心接口，插入任务单表
            res = insertTaskTb(taskMsgDTO, 1);
        }
    }

    /**
     * 财务驳回
     *
     * @param dto
     */
    @StreamListener(TaskSink.Financial_Rejected)
    public void receiveFinancialRejected(PayApplyPayStatusDTO dto) {
        try {
            if (SysConstants.JobConstants.AF_EMPLOYEE_PAYMENT.getCode().equals(dto.getBusinessType())) {
                employeePaymentService.syncSettleCenterStatus(dto);
            } else if (SysConstants.JobConstants.MEDICAL_CLAIMS.getCode().equals(dto.getBusinessType())) {
                healthMedicalJobService.syncSettleCenterStatus(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 银行退票
     *
     * @param dto
     */
    @StreamListener(TaskSink.Return_Ticket)
    public void receiveReturn_Ticket(PayApplyReturnTicketDTO dto) {
        try {
            if (SysConstants.JobConstants.AF_EMPLOYEE_PAYMENT.getCode().equals(dto.getBusinessType())) {
                employeePaymentService.handlePaymentRefund(dto);
            } else if (SysConstants.JobConstants.MEDICAL_CLAIMS.getCode().equals(dto.getBusinessType())) {
                healthMedicalJobService.handlePaymentRefund(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取雇员信息api
     *
     * @param taskMsgDTO
     * @return
     */
    private AfEmployeeInfoDTO callInf(TaskCreateMsgDTO taskMsgDTO) {
        AfEmployeeInfoDTO resDto = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            String missionId = taskMsgDTO.getMissionId();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(missionId));
            resDto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDto;
    }

    private boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) {
        boolean result = false;
        try {
            //<editor-fold desc=" 1 调用接口，获取前道过来的投保任务单数据">
            AfEmployeeInfoDTO dto = callInf(taskMsgDTO);
            AfFullEmployeeDTO empDTO = dto.getEmployee();
            List<AfEmpInsuranceDTO> empTaskList = dto.getEmpInsuranceList();
            //</editor-fold>

            // <editor-fold desc="2 循环，处理前道投退任务单">
            List<AfTpaTaskPO> afTaskList = new ArrayList<>();

            empTaskList.forEach(item -> {
                AfTpaTaskPO task = new AfTpaTaskPO();
                // <editor-fold desc="2.0 转换service_item">
                /* 获取serviceItems */
                List<String> ids = new ArrayList<>();
                ids.add(item.getProductId());
                com.ciicsh.gto.productcenter.apiservice.api.dto.JsonResult jsonResult = productProxy.getProductByProductIDs(ids);
                List<ProductExtDTO> productExtDTOS = (List<ProductExtDTO>) jsonResult.getData();
                String serviceItem = productExtDTOS.get(0).getServiceItems();
                // </editor-fold>

                // <editor-fold desc="2.1 通用字段赋值">
                // 通过api接口查询公司信息
                AfCompanyDTO afCompanyDTO = afCompanyProxy.getCompanyByCompanyId(item.getCompanyId());

                // 2.1   判断，如果是投保给子女或者配偶，需要判断是否有信息
                // 2.1.1 如果没有，需要调用雇员中心接口新增一个任务单
                // 2.1.2 如果有，获取读取亲属信息
                // 2.2   查询，投保任务单数据完善和补充
                // 投保任务单
                if (serviceItem.contains("子女") || serviceItem.contains("配偶")) {
                    JsonResult<List<EmployeeMemberDTO>> employeeMemberInfoList = employeeInfoProxy.getEmployeeMemberInfo(empDTO.getEmployeeId());

                    /* 验证信息，如果存在，状态为-待处理
                     * 如果不存在，状态为信息待完善*/
                    if (serviceItem.contains("子女")) {
                        task.setType(2);
                        task.setStatus(2);
                        // 数字 1 配偶，2 子女，3 父母，4 其他,接口中代表的含义
                        List<EmployeeMemberDTO> employeeMemberDTOS = new ArrayList<>();
                        if (employeeMemberInfoList.getData() != null) {
                            employeeMemberDTOS = employeeMemberInfoList.getData().stream().filter(t -> t.getRelationShip() == 2).collect(Collectors.toList());
                        }
                        if (employeeMemberDTOS.size() == 0) {
                            //调用接口
                            task.setStatus(1);
                        }
                        EmployeeMemberDTO employeeMemberDTO = employeeMemberDTOS.get(0);

                        task.setAssociatedInsurantId(employeeMemberDTO.getEmployeeId());
                        task.setAssociatedInsurantName(employeeMemberDTO.getName());
                        task.setIdNum(employeeMemberDTO.getIdNum());
                        task.setGender(employeeMemberDTO.getGender());
                        task.setAge(12);
                        if (employeeMemberDTO.getBirthday() != null) {
                            task.setBirthDate(employeeMemberDTO.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        } else {
                            task.setBirthDate(null);
                        }
                    } else if (serviceItem.contains("配偶")) {
                        task.setType(3);
                        task.setStatus(2);

                        List<EmployeeMemberDTO> employeeMemberDTOS = new ArrayList<>();
                        if (employeeMemberInfoList.getData() != null) {
                            employeeMemberDTOS = employeeMemberInfoList.getData().stream().filter(t -> t.getRelationShip() == 1).collect(Collectors.toList());
                        }
                        if (employeeMemberDTOS.size() == 0) {
                            //调用接口
                            task.setStatus(1);
                        }
                        EmployeeMemberDTO employeeMemberDTO = employeeMemberDTOS.get(0);

                        task.setAssociatedInsurantId(employeeMemberDTO.getEmployeeId());
                        task.setAssociatedInsurantName(employeeMemberDTO.getName());
                        task.setIdNum(employeeMemberDTO.getIdNum());
                        task.setGender(employeeMemberDTO.getGender());
                        task.setAge(12);
                        if (employeeMemberDTO.getBirthday() != null) {
                            task.setBirthDate(employeeMemberDTO.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        } else {
                            task.setBirthDate(null);
                        }
                    }
                } else {
                    task.setType(1);
                    task.setStatus(2);
                    task.setAssociatedInsurantId(empDTO.getEmployeeId());
                    task.setAssociatedInsurantName(empDTO.getEmployeeName());
                    task.setIdNum(empDTO.getIdNum());
                    task.setGender(empDTO.getGender());
                    task.setAge(12);
                    if (empDTO.getBirthday() != null) {
                        task.setBirthDate(empDTO.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    } else {
                        task.setBirthDate(null);
                    }
                }
                task.setTaskType(taskCategory.toString());
                task.setProcessId(taskMsgDTO.getProcessId());
                task.setEmployeeId(empDTO.getEmployeeId());
                task.setEmployeeName(empDTO.getEmployeeName());
                task.setCompanyId(item.getCompanyId());
                task.setCompanyName(afCompanyDTO.getTitle());

                task.setAfProductId(item.getProductId());
                task.setProductName(item.getProductName());
                // 投保日期
                if (item.getStartDate() != null) {
                    task.setStartConfirmDate(item.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                } else {
                    task.setStartConfirmDate(null);
                }

                if (item.getEndDate() != null) {
                    task.setEndConfirmDate(item.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                } else {
                    task.setEndConfirmDate(null);
                }

                task.setPrice(item.getPrice());
                // </editor-fold>

                //依据services_item确定status，type
                task.setServiceItems(item.getServiceItems());
                if (serviceItem.contains("固定金额")) {
                    task.setKeyType(1);
                } else {
                    task.setKeyType(2);
                }
                task.setKeyValue(item.getKeyValue());

                afTaskList.add(task);
            });
            // </editor-fold>

            // <editor-fold desc="3 新增，投保任务单入库">
            result = afTpaTaskService.insertBatch(afTaskList, afTaskList.size());
            // </editor-fold>

        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 接收任务完成消息
     * @param message

     @StreamListener(MsgConstants.COMMON_TASKSERVICE_TASK_COMPLETE) public void commonTaskserviceTaskComplete(Message<TaskCompleteMsgDTO> message){
     TaskCompleteMsgDTO taskCompleteMsgDTO = message.getPayload();
     String returnInfo = "taskId="+taskCompleteMsgDTO.getTaskId()+
     ",taskType="+taskCompleteMsgDTO.getTaskType()+
     ",missionId="+taskCompleteMsgDTO.getMissionId()+
     ",processId="+taskCompleteMsgDTO.getProcessId()+
     ",processDefinitionKey="+taskCompleteMsgDTO.getProcessDefinitionKey()+
     ",Variables="+taskCompleteMsgDTO.getVariables();
     logger.info("收到任务完成消息: " + returnInfo);
     }
     */
    /**
     * 流程结束消息
     * @param message

     @StreamListener(MsgConstants.COMMON_TASKSERVICE_PROCESS_COMPLETE) public void commonTaskserviceProcessComplete(Message<ProcessCompleteMsgDTO> message){
     ProcessCompleteMsgDTO processCompleteMsgDTO = message.getPayload();
     String returnInfo = "processId="+processCompleteMsgDTO.getProcessId()+
     ",missionId="+processCompleteMsgDTO.getMissionId()+
     ",processDefinitionKey="+processCompleteMsgDTO.getProcessDefinitionKey();
     logger.info("收到流程结束消息: " + returnInfo);
     }
     */
}
