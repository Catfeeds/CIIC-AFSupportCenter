package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.messageBus;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.AFEmpSocialDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.AFEmployeeCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.EmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskFront;
import com.ciicsh.gto.afsupportcenter.util.web.convert.StringToLocalDateConverter;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.TaskMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by songjt on 17/12/18.
 */
@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {
    private final static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private ISsEmpTaskService ssEmpTaskService;
    @Autowired
    private ISsEmpTaskFrontService ssEmpTaskFrontService;

//    @Autowired
//    private SheetServiceProxy sheetServiceProxy;

    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveEmpIn(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {

            //TODO 从刘玉庭那边取DTO再从中取任务类型
//            insertTaskTb(taskMsgDTO, ((TestDto)taskMsgDTO.getVariables().get("content")).getTaskCategory());
            res = insertTaskTb(taskMsgDTO, 1);
        }

        logger.info("收到消息from AF_EMP_IN-useWork: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.AF_EMP_OUT)
    public void receiveEmpOut(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息from AF_EMP_OUT-useWork: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.CHARGE_RESUME)
    public void receiveChargeResume(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息from CHARGE_RESUME-useWork: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.CHARGE_STOP)
    public void receiveChargeStop(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息from CHARGE_STOP-useWork: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.EMP_COMPANY_CHANGE)
    public void receiveEmpCompanyChange(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息from EMP_COMPANY_CHANGE-useWork: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.NONLOCAL_TO_SH)
    public void receiveNonlocalToSh(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();

        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }

        logger.info("收到消息from NONLOCAL_TO_SH-useWork: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.SH_TO_NONLOCAL)
    public void receiveSh(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息from SH_TO_NONLOCAL-useWork: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    private EmployeeInfoDTO callInf(TaskMsgDTO taskMsgDTO) {
//        logger.info("数据获取接口开始调用："+taskSheetRequestDTO.toString());
//        try {
//            TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
//            taskRequestDTO.setTaskId(taskSheetRequestDTO.getTaskId());
//            taskRequestDTO.setAssignee(taskSheetRequestDTO.getAssignee());
//            taskRequestDTO.setVariable(taskSheetRequestDTO.getVariable());
//            //
//            Result restResult = sheetServiceProxy.completeTask(taskRequestDTO);
//            logger.info("数据获取接口返回："+String.valueOf("code:"+restResult.getCode()+"message:")+restResult.getMessage());
//            return ResultGenerator.genSuccessResult(true);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return ResultGenerator.genServerFailResult();
//        }
        EmployeeInfoDTO dto = new EmployeeInfoDTO();
        AFEmployeeCompanyDTO d1 = new AFEmployeeCompanyDTO();
        d1.setCompanyId("1");
        d1.setInDate(new Date());
        d1.setOutDate(new Date());
        dto.setEmployeeCompany(d1);

        List<AFEmpSocialDTO> empSocial = new ArrayList<>();
        AFEmpSocialDTO dd2 = new AFEmpSocialDTO();
        dd2.setEmpBase(new BigDecimal(12));
        empSocial.add(dd2);
        dd2 = new AFEmpSocialDTO();
        dd2.setEmpBase(new BigDecimal(13));
        empSocial.add(dd2);
        dto.setEmpSocial(empSocial);
        return dto;
    }

    /**
     * <p>Description: 从接口获取数据并保存到雇员任务单表</p>
     *
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    private boolean insertTaskTb(TaskMsgDTO taskMsgDTO, Integer taskCategory) {
        //调用接口
        EmployeeInfoDTO dto = callInf(taskMsgDTO);

        boolean result = ssEmpTaskFrontService.insertTaskTb(taskMsgDTO, taskCategory, dto);

        return result;
    }
}
