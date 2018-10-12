package com.ciicsh.gto.afsupportcenter.employmanagement.messageservice.host.message;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmpTaskService;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by songjt on 17/12/18.
 */
@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {
    private final static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private IAmEmpTaskService iAmEmpTaskService;

    @Autowired
    private LogApiUtil logApiUtil;

    /**
     * 订阅用工办理任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveEmpIn(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //用工办理
        boolean res = false;
        if (TaskSink.HIRE.equals(taskMsgDTO.getTaskType())) {
            logger.info("receive empIn: " + JSON.toJSONString(taskMsgDTO));
            LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(JSON.toJSONString(taskMsgDTO));
            logApiUtil.info(logMessage);
            try {
                res = insertAmEmpTaskTb(taskMsgDTO, 1);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            logger.info("收到消息 用工办理: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }
    }

    /**
     * 订阅退工任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_OUT)
    public void receiveEmpOut(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //退工
        boolean res = false;
        if (TaskSink.FIRE.equals(taskMsgDTO.getTaskType())) {
            logger.info("receive empOut: " + JSON.toJSONString(taskMsgDTO));
            LogMessage logMessage = LogMessage.create().setTitle("退工任务单").setContent(JSON.toJSONString(taskMsgDTO));
            logApiUtil.info(logMessage);
            try {
                res = iAmEmpTaskService.insertTaskFire(taskMsgDTO, 2);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            logger.info("收到消息 退工: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }
    }

    @StreamListener(TaskSink.AF_EMP_COMPANY_CHANGE )
    public void companyChange(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();

        boolean res = false;
        //用工办理
        if (TaskSink.HIRE.equals(taskMsgDTO.getTaskType())) {
            logger.info("receive empIn: " + JSON.toJSONString(taskMsgDTO));
            LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(JSON.toJSONString(taskMsgDTO));
            logApiUtil.info(logMessage);
            try {
                res = insertAmEmpTaskTb(taskMsgDTO, 1);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            logger.info("收到消息 用工办理: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }

        //退工

        if (TaskSink.FIRE.equals(taskMsgDTO.getTaskType())) {
            logger.info("receive empOut: " + JSON.toJSONString(taskMsgDTO));
            LogMessage logMessage = LogMessage.create().setTitle("退工任务单").setContent(JSON.toJSONString(taskMsgDTO));
            logApiUtil.info(logMessage);
            try {
                res = iAmEmpTaskService.insertTaskFire(taskMsgDTO, 2);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            logger.info("收到消息 退工: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }

    }

    @StreamListener(TaskSink.AF_EMP_AGREEMENT_ADJUST)
    public void agreementAdjust(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();

        boolean res = false;
        //用工办理
        if (TaskSink.HIRE.equals(taskMsgDTO.getTaskType())) {
            logger.info("receive empIn: " + JSON.toJSONString(taskMsgDTO));
            LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(JSON.toJSONString(taskMsgDTO));
            logApiUtil.info(logMessage);
            try {
                res = insertAmEmpTaskTb(taskMsgDTO, 1);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            logger.info("收到消息 用工办理: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }

        //退工

        if (TaskSink.FIRE.equals(taskMsgDTO.getTaskType())) {
            logger.info("receive empOut: " + JSON.toJSONString(taskMsgDTO));
            LogMessage logMessage = LogMessage.create().setTitle("退工任务单").setContent(JSON.toJSONString(taskMsgDTO));
            logApiUtil.info(logMessage);
            try {
                res = iAmEmpTaskService.insertTaskFire(taskMsgDTO, 2);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            logger.info("收到消息 退工: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }

    }


    @StreamListener(TaskSink.AF_EMP_AGREEMENT_UPDATE)
    public void agreementUpdate(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();

        boolean res = false;
        //用工办理
        if (TaskSink.HIRE.equals(taskMsgDTO.getTaskType())) {
            logger.info("receive empIn: " + JSON.toJSONString(taskMsgDTO));
            LogMessage logMessage = LogMessage.create().setTitle("用工任务单").setContent(JSON.toJSONString(taskMsgDTO));
            logApiUtil.info(logMessage);
            try {
                res = insertAmEmpTaskTb(taskMsgDTO, 1);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            logger.info("收到消息 用工办理: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }

        //退工

        if (TaskSink.FIRE.equals(taskMsgDTO.getTaskType())) {
            logger.info("receive empOut: " + JSON.toJSONString(taskMsgDTO));
            LogMessage logMessage = LogMessage.create().setTitle("退工任务单").setContent(JSON.toJSONString(taskMsgDTO));
            logApiUtil.info(logMessage);
            try {
                res = iAmEmpTaskService.insertTaskFire(taskMsgDTO, 2);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            logger.info("收到消息 退工: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }

    }

    @StreamListener(TaskSink.AF_EMP_IN_UPDATE)
    public void updateEmpIn(Message<TaskCreateMsgDTO> message) {

    }

    /**
     * 从接口获取数据并保存到雇员任务单表
     *
     * @param taskMsgDTO
     * @param taskCategory
     * @return
     */
    private boolean insertAmEmpTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) {
        boolean result = false;

        try {
            result = iAmEmpTaskService.insertTaskTb(taskMsgDTO, taskCategory);
        } catch (Exception e) {
            result = false;
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
