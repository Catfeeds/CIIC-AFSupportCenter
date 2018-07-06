package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.sheetservice.api.dto.Result;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;


public class TaskCommonUtils {

    private final static Logger logger = LoggerFactory.getLogger(TaskCommonUtils.class);

    /**
     * 处理工作流结果
     *
     * @param result
     */
    private static void handleWorkflowResult(Result result) {
        //int code，接口调用成功=0，错误码=其他值
        //T object，具体返回值  TRUE,FALSE
        //String error，字符串错误码，可选
        //String message，错误消息
        Assert.notNull(result, "任务单操作调用工作流结果为空");
        if (0 != result.getCode()) {
            throw new BusinessException(result.getMessage());
        }
    }

    /**
     * 任务单处理调用工作流
     *
     * @param taskId
     * @param commonApiUtils
     * @return
     */
    public static void completeTask(String taskId, CommonApiUtils commonApiUtils, Map<String, Object> variables) {

        TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
        taskRequestDTO.setTaskId(taskId);
        taskRequestDTO.setAssignee(variables.get("assignee").toString());
        taskRequestDTO.setVariables(variables);
        try {
            logger.info(JSON.toJSONString(taskRequestDTO));
            Result result = commonApiUtils.completeTask(taskRequestDTO);
            handleWorkflowResult(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BusinessException("调用工作流异常");
        }
    }


}
