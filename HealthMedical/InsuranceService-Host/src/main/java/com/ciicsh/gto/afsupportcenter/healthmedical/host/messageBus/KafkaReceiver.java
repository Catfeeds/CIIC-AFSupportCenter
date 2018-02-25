package com.ciicsh.gto.afsupportcenter.healthmedical.host.messageBus;


import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpInsuranceDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfFullEmployeeDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfTpaTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalInvoiceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeeBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfTpaTask;
import com.ciicsh.gto.afsupportcenter.util.web.convert.JsonUtil;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeMemberDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.employeecenter.util.JsonResult;
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
    private EmployeeInfoProxy employeeInfoProxy;

    @Autowired
    private SupplyMedicalInvoiceService supplyMedicalInvoiceService;

    @StreamListener(MsgConstants.AFCompanyCenter.AF_EMP_IN)
    public void receiveBaseAdjustYearlyNonlocal(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        String missionID = taskMsgDTO.getMissionId();
        //   taskMsgDTO.getVariables("");
        // logger.info("收到消息from BASE_ADJUST_YEARLY_NONLOCAL-useWork: " + returnInfo);

        boolean res = false;
        // 判断是否投保任务单
        if (taskMsgDTO.getTaskType() != "insurance_new") {
            // 读客服中心接口，插入任务单表
            res = insertTaskTb(taskMsgDTO, 1);
        }

        // 判断是否退保任务单


    }

    private AfEmployeeInfoDTO callInf(TaskCreateMsgDTO taskMsgDTO) {

        AfEmployeeInfoDTO resDto = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            String missionId = taskMsgDTO.getMissionId();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(missionId));
            resDto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);
        } catch (Exception e) {

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
            List<AfTpaTask> afTaskList = new ArrayList<>();

            empTaskList.forEach(item -> {
                AfTpaTask task = new AfTpaTask();

                // <editor-fold desc="2.0 转换service_item">
                String serviceItem = item.getServiceItems();
                // serviceItem="[{\"remark\": \"\", \"options\": [{\"title\": \"赔付比例\", \"values\": 90}, {\"title\": \"被保障人\", \"values\": [\"配偶\"]}], \"itemName\": \"特需医疗保障\", \"basicServiceItemId\": 12}]";
                List<String> list = null;
                try {
                    list = JsonUtil.fromJsonToList(JsonUtil.fromJsonToObject(serviceItem, String.class), List.class, String.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // </editor-fold>

                // <editor-fold desc="2.1 通用字段赋值">
                EmployeeBO employeeBO = supplyMedicalInvoiceService.queryEmployeeInfo(empDTO.getEmployeeId());

                task.setCompanyId(item.getCompanyId());
                // 投保任务单
                if (serviceItem.contains("雇员")) {
                    task.setType(1);
                } else {
                    if (serviceItem.contains("子女")) {
                        task.setType(2);
                    } else if (serviceItem.contains("配偶")) {
                        task.setType(3);
                    }
                    /* 验证信息，如果存在，状态为-待处理
                    * 如果不存在，状态为信息待完善*/

                }

                task.setEmployeeId(empDTO.getEmployeeId());
                task.setEmployeeName(empDTO.getEmployeeName());
                task.setCompanyId(item.getCompanyId());
                //通过接口依据companyID查companyName，现在暂时为固定值
                task.setCompanyName(employeeBO.getCompanyName());
                task.setAfProductId(item.getProductId());
                task.setProductName(item.getProductName());
                // 投保日期
                task.setStartConfirmDate(item.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                task.setPrice(item.getPrice());
                // </editor-fold>


                // List<AfEmployeeInfoDTO> list = JsonUtil.fromJsonToList(JsonUtil.fromJsonToObject(serviceItem,String.class),List.class,String.class);

                // 2.1   判断，如果是投保给子女或者配偶，需要判断是否有信息
                // 2.1.1 如果没有，需要调用雇员中心接口新增一个任务单
                // 2.1.2 如果有，获取读取亲属信息
                // 2.2   查询，投保任务单数据完善和补充


                JsonResult<List<EmployeeMemberDTO>> employeeMemberInfoList = employeeInfoProxy.getEmployeeMemberInfo("667064237877603");


                //依据services_item确定status，type
                task.setServiceItems(item.getServiceItems());
                task.setKeyValue(item.getKeyValue());
                // task.setStartConfirmDate(item.getStartDate());


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

    public static void main(String[] args) {
        String serviceItem = "[{\"remark\": \"\", \"options\": [{\"title\": \"赔付比例\", \"values\": 90}, {\"title\": \"被保障人\", \"values\": [\"配偶\"]}], \"itemName\": \"特需医疗保障\", \"basicServiceItemId\": 12}]";
        List<String> list = null;
        try {
            list = JsonUtil.fromJsonToList(JsonUtil.fromJsonToObject(serviceItem, String.class), List.class, String.class);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
