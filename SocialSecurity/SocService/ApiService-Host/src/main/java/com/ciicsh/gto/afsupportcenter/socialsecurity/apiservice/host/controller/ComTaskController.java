package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SsComTaskProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/2/26.
 */
@RestController
@RequestMapping("/api/comtask")
public class ComTaskController implements SsComTaskProxy{

    @Autowired
    private SsComTaskService taskService;

    /**
     * 企业社保账户开户、变更、转移、转出的 创建任务单接口
     *
     * @param ssComTaskDTO
     * @return
     */
    @Log("企业社保账户开户、变更、转移、转出的 创建任务单接口")
    @PostMapping("/saveComTask")
    public JsonResult saveSsComTask(@RequestBody SsComTaskDTO ssComTaskDTO) {
        try {
            if (StringUtils.isBlank(ssComTaskDTO.getCompanyId())) {
                return JsonResult.faultMessage("客户Id不能为空！");
            }
            if (StringUtils.isBlank(ssComTaskDTO.getTaskCategory())) {
                return JsonResult.faultMessage("任务类型不能为空！");
            }
            SsComTaskBO ssComTask = new SsComTaskBO();
            BeanUtils.copyProperties(ssComTaskDTO, ssComTask);
            int cnt = taskService.countComTaskByCond(ssComTask);
            if (cnt > 0) {
                return JsonResult.faultMessage("该企业已存在相同类型的处理中任务单，不能重复添加！");
            }
            Long newComTaskId = insertSsComTask(ssComTaskDTO);
            return JsonResult.success(newComTaskId);
        } catch (Exception e) {
            return JsonResult.faultMessage(e.getMessage());
        }
    }

    //保存企业任务单
    private Long insertSsComTask(@RequestBody SsComTaskDTO ssComTaskDTO) {
        boolean result = false;
        //数据转换
        SsComTask ssComTask = CommonTransform.convertToEntity(ssComTaskDTO, SsComTask.class);
        ssComTask.setTaskStatus(0);
        ssComTask.setActive(true);
        ssComTask.setCreatedTime(LocalDateTime.now());
        ssComTask.setModifiedTime(LocalDateTime.now());
        ssComTask.setCreatedBy("system");
        ssComTask.setModifiedBy("system");

        //任务单上前道系统传递过来的内容，Json格式
        ssComTask.setTaskFormContent(JSONObject.toJSONString(ssComTaskDTO));

        taskService.insertComTask(ssComTask);
        return ssComTask.getComTaskId();
    }
}
