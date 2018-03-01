package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SsComTaskProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.ComTaskExtDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.ComTaskParamDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComTaskParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.SsComTaskExtPO;
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
@RequestMapping("/api/soc/comtask")
public class ComTaskController implements SsComTaskProxy{

    @Autowired
    private SsComTaskService taskService;
    @Autowired
    private SsComAccountService ssComAccountService;

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
            ssComTaskDTO.setTaskCategory("1");
            SsComTaskBO ssComTask = new SsComTaskBO();
            BeanUtils.copyProperties(ssComTaskDTO, ssComTask);
            //查询是否有处理中的任务单
            int cnt = taskService.countComTaskByCond(ssComTask);
            if (cnt > 0) {
                return JsonResult.faultMessage("该企业已存在相同类型的处理中任务单，不能重复添加！");
            }else{
                //查找是否有已完成的任务单
                int result = taskService.countFinishComTaskByCond(ssComTask);
                if(result>0){
                    //查询企业账户状态
                    SsComAccount ssComAccount = ssComAccountService.selectAccountByCompanyId(ssComTaskDTO.getCompanyId());
                    if(null!=ssComAccount && ssComAccount.getState()!=2)
                        return JsonResult.faultMessage("该企业已存在有效开户信息，不能再次开户！");
                }
            }
            Long newComTaskId = insertSsComTask(ssComTaskDTO);
            return JsonResult.success(newComTaskId);
        } catch (Exception e) {
            return JsonResult.faultMessage(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getComTask")
    public JsonResult getComTask(ComTaskParamDTO paramDTO) {
        ComTaskParamBO paramBO = new ComTaskParamBO();
        BeanUtils.copyProperties(paramDTO,paramBO);
        SsComTaskExtPO extPO = taskService.getComTask(paramBO);
        ComTaskExtDTO extDTO = new ComTaskExtDTO();
        if(null != extPO){
            BeanUtils.copyProperties(extPO,extDTO);
        }
        return JsonResult.success(extDTO);
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

    //TODO 这里应新增查看企业账户接口
}
