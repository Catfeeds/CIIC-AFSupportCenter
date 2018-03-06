package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.controller;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.translator.ApiTranslator;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.FundApiProxy;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComAccountParamDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by houwanhua on 2018/2/27.
 */
@RestController
@RequestMapping("/api/fund")
public class FundApiController implements FundApiProxy{
    @Autowired
    HfComAccountService hfComAccountService;

    @Autowired
    HfComTaskService hfComTaskService;

    /**
     * 企业社保账户开户、变更、转移、转出的 创建任务单接口
     * @param hfComTaskDTO
     * @return
     */
    @Log("企业社保账户开户、变更、转移、转出的 创建任务单接口")
    @Override
    @PostMapping("/saveComTask")
    public JsonResult saveHfComTask(@RequestBody HfComTaskDTO hfComTaskDTO) {
        try {
            if (StringUtils.isBlank(hfComTaskDTO.getCompanyId())) {
                return JsonResult.faultMessage("客户Id不能为空！");
            }
            if (hfComTaskDTO.getTaskCategory() == null || hfComTaskDTO.getTaskCategory() == 0) {
                return com.ciicsh.common.entity.JsonResult.faultMessage("任务类型不能为空！");
            }
            HfComTask ssComTask = new HfComTask();
            BeanUtils.copyProperties(hfComTaskDTO, ssComTask);
            int cnt = hfComTaskService.countComTaskByCond(ssComTask);
            if (cnt > 0) {
                return JsonResult.faultMessage("该企业已存在相同类型的处理中任务单，不能重复添加！");
            }
            Long newComTaskId = insertHfComTask(hfComTaskDTO);
            return JsonResult.success(newComTaskId);
        } catch (Exception e) {
            return JsonResult.faultMessage(e.getMessage());
        }
    }

    //保存企业任务单
    private Long insertHfComTask(@RequestBody HfComTaskDTO hfComTaskDTO) {
        boolean result = false;
        //数据转换
        HfComTask hfComTask = CommonTransform.convertToEntity(hfComTaskDTO, HfComTask.class);
        hfComTask.setTaskStatus(0);
        hfComTask.setActive(true);
        hfComTask.setCreatedTime(new Date());
        hfComTask.setModifiedTime(new Date());
        hfComTask.setCreatedBy("system");
        hfComTask.setModifiedBy("system");

        //任务单上前道系统传递过来的内容，Json格式
//        hfComTask.setTaskFormContent(JSONObject.toJSONString(hfComTaskDTO));

        hfComTaskService.insertComTask(hfComTask);
        return hfComTask.getComTaskId();
    }

    @Override
    @RequestMapping("/getAccountList")
    @Log("获取企业社保账户信息表")
    public JsonResult getHfComAccountList(@RequestBody HfComAccountParamDTO paramDto) {

        ComAccountParamExtBo paramBO = new ComAccountParamExtBo();
        BeanUtils.copyProperties(paramDto,paramBO);

        // 根据 客户ID和账户类型查询
        List<ComAccountExtBo> ssComAccountList = hfComAccountService.getHfComAccountList(paramBO);
        List<HfComAccountDTO> accountDTOS = new ArrayList<>();
        if(null != ssComAccountList && ssComAccountList.size() > 0){
            accountDTOS = ssComAccountList.stream().map(ApiTranslator::toComAccountDTO).collect(Collectors.toList());
        }
        return JsonResult.success(accountDTOS);
    }
}
