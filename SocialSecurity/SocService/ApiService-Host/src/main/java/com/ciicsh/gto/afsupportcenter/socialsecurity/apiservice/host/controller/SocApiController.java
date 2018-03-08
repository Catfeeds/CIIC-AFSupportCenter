package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.translator.ApiTranslator;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SocApiProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.ComAccountExtDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.ComTaskParamDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountParamDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountExtBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComTaskParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ComAccountExtPO;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by houwanhua on 2018/3/8.
 */
@RestController
@RequestMapping("/api/soc")
@Api(value = "soc-api-service",description = "support interface for other center")
public class SocApiController implements SocApiProxy{
    @Autowired
    private SsComAccountService accountService;

    @Autowired
    private SsComTaskService taskService;


    @Override
    @ApiOperation(value = "企业社保账户开户、变更、转移、转出的 创建任务单接口",notes = "根据ComTask对象创建")
    @ApiImplicitParam(name = "ssComTaskDTO",value = "企业任务单对象 ssComTaskDTO",required = true,dataType = "SsComTaskDTO")
    @PostMapping("/saveComTask")
    public JsonResult saveComTask(@RequestBody SsComTaskDTO ssComTaskDTO) {
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
                    SsComAccount ssComAccount = accountService.selectAccountByCompanyId(ssComTaskDTO.getCompanyId());
                    if(null!=ssComAccount && ssComAccount.getState()!=2)
                        return JsonResult.faultMessage("该企业已存在有效开户信息，不能再次开户！");
                }
            }
            //结算中心转变成字符串
            if(StringUtils.isNotBlank(ssComTaskDTO.getSettlementArea())){
                String settlementArea = SocialSecurityConst.DISTRICT_MAP.get(ssComTaskDTO.getSettlementArea());
                if(StringUtils.isNotBlank(settlementArea))ssComTaskDTO.setSettlementArea(settlementArea);
            }
            Long newComTaskId = addComTask(ssComTaskDTO);
            return JsonResult.success(newComTaskId);
        } catch (Exception e) {
            return JsonResult.faultMessage(e.getMessage());
        }
    }

    //保存企业任务单
    private Long addComTask(SsComTaskDTO ssComTaskDTO) {
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


    @Override
    @ApiOperation(value = "获取企业社保账户信息列表",notes = "根据SsComAccountParamDTO对象获取")
    @ApiImplicitParam(name = "paramDto",value = "企业任务单对象 paramDto",required = true,dataType = "SsComAccountParamDTO")
    @PostMapping("/getAccountList")
    public JsonResult<List<SsComAccountDTO>> getSsComAccountList(@RequestBody SsComAccountParamDTO paramDto) {
        ComAccountParamBO paramBO = new ComAccountParamBO();
        BeanUtils.copyProperties(paramDto,paramBO);
        // 根据 客户ID和账户类型查询
        List<ComAccountExtPO> ssComAccountList = accountService.getSsComAccountList(paramBO);
        List<SsComAccountDTO> accountDTOS = new ArrayList<>();
        if(null != ssComAccountList && ssComAccountList.size() > 0){
            accountDTOS = ssComAccountList.stream().map(ApiTranslator::toComAccountDTO).collect(Collectors.toList());
        }
        return JsonResult.success(accountDTOS);
    }


    @Override
    @ApiOperation(value = "获取企业社保账户信息",notes = "根据ComTaskParamDTO对象获取")
    @ApiImplicitParam(name = "paramDTO",value = "企业任务单对象 paramDTO",required = true,dataType = "ComTaskParamDTO")
    @PostMapping("/getAccountByCompany")
    public JsonResult<ComAccountExtDTO> getAccountByCompany(@RequestBody ComTaskParamDTO paramDTO) {
        ComTaskParamBO paramBO = new ComTaskParamBO();
        BeanUtils.copyProperties(paramDTO,paramBO);
        ComAccountExtBO extBO = taskService.getComAccountInfo(paramBO);
        ComAccountExtDTO extDTO = null;
        if(null != extBO){
            extDTO = new ComAccountExtDTO();
            BeanUtils.copyProperties(extBO,extDTO);
        }
        return JsonResult.success(extDTO);
    }
}
