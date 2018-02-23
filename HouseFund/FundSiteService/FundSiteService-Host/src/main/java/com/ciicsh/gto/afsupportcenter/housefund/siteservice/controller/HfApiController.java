package com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.HfComProxy;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.HfComTaskProxy;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountParamDto;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.ResultDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTask;
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
import java.util.List;

/**
 * <p>
 * 公积金 接口控制器
 * </p>
 *
 * @author zxj
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/ai/soccommandservice/hfApi")
public class HfApiController implements HfComTaskProxy, HfComProxy {

    @Autowired
    HfComAccountService hfComAccountService;

    @Autowired
    HfComTaskService hfComTaskService;

    /**
     * 企业社保账户开户、变更、转移、转出的 创建任务单接口
     *
     * @param hfComTaskDTO
     * @return
     */
    @Log("企业社保账户开户、变更、转移、转出的 创建任务单接口")
    @Override
    @PostMapping("/saveHfComTask")
    public ResultDTO saveHfComTask(@RequestBody HfComTaskDTO hfComTaskDTO) {
        ResultDTO json = new ResultDTO(false, null);
        try {
            if (hfComTaskDTO.getComAccountId() == 0L) {
                json.faultMessage("企业社保账户Id不能为空！");
                return json;
            }
            if (StringUtils.isBlank(hfComTaskDTO.getCompanyId())) {
                json.faultMessage("客户Id不能为空！");
                return json;
            }
            if (hfComTaskDTO.getTaskCategory() == null || hfComTaskDTO.getTaskCategory() == 0) {
                json.faultMessage("任务类型不能为空！");
                return json;
            }
//            if (StringUtils.isBlank(hfComTaskDTO.getBusinessInterfaceId())) {
//                return ResultGenerator.genServerFailResult("业务接口ID不能为空！");
//            }
            HfComTask ssComTask = new HfComTask();
            BeanUtils.copyProperties(hfComTaskDTO, ssComTask);
            int cnt = hfComTaskService.countComTaskByCond(ssComTask);
            if (cnt > 0) {
                json.faultMessage("该企业已存在相同类型的处理中任务单，不能重复添加！");
                return json;
            }
            Long newComTaskId = insertHfComTask(hfComTaskDTO);
            json.success(newComTaskId);
            return json;
        } catch (Exception e) {
            json.faultMessage(e.getMessage());
            return json;
        }
    }

    //保存企业任务单
    private Long insertHfComTask(@RequestBody HfComTaskDTO hfComTaskDTO) {
        boolean result = false;
        //数据转换
        HfComTask hfComTask = CommonTransform.convertToEntity(hfComTaskDTO, HfComTask.class);
        hfComTask.setTaskStatus(0);
        hfComTask.setActive(true);
        hfComTask.setCreatedTime(LocalDateTime.now());
        hfComTask.setModifiedTime(LocalDateTime.now());
        hfComTask.setCreatedBy("system");
        hfComTask.setModifiedBy("system");

        //任务单上前道系统传递过来的内容，Json格式
//        hfComTask.setTaskFormContent(JSONObject.toJSONString(hfComTaskDTO));

        hfComTaskService.insertComTask(hfComTask);
        return hfComTask.getComTaskId();
    }

    @Override
    @RequestMapping("/getHfComAccountList")
    @Log("获取企业社保账户信息表")
    public ResultDTO getHfComAccountList(@RequestBody HfComAccountParamDto paramDto) {
        // 根据 客户ID和账户类型查询
        List<HfComAccountDTO> ssComAccountList =
            hfComAccountService.getHfComAccountList(paramDto);

        ResultDTO json = new ResultDTO(true, null);
        json.success(ssComAccountList);
        return json;
    }
}