package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.controller;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.translator.ApiTranslator;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.FundApiProxy;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.ComAccountExtDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComAccountParamDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@Api(value = "fund-api-service",description = "support interface for other center")
public class FundApiController implements FundApiProxy{
    @Autowired
    HfComAccountService hfComAccountService;

    @Autowired
    HfComTaskService hfComTaskService;

    /**
     * 企业公积金账户开户、变更、转移、转出的 创建任务单接口
     * @param comTaskDTO
     * @return
     */
    @Override
    @ApiOperation(value = "企业公积金账户开户、变更、转移、转出的 创建任务单接口",notes = "根据ComTask对象创建")
    @ApiImplicitParam(name = "comTaskDTO",value = "企业任务单对象 comTaskDTO",required = true,dataType = "HfComTaskDTO")
    @PostMapping("/saveComTask")
    public JsonResult saveComTask(@RequestBody HfComTaskDTO comTaskDTO) {
        try {
            if (StringUtils.isBlank(comTaskDTO.getCompanyId())) {
                return JsonResult.faultMessage("客户Id不能为空！");
            }
            if (comTaskDTO.getTaskCategory() == null || comTaskDTO.getTaskCategory() == 0) {
                return JsonResult.faultMessage("任务类型不能为空！");
            }
            boolean flag = checkIsExistAccount(comTaskDTO);
            if(flag){
                return JsonResult.faultMessage("该企业已存在相同类型的处理中任务单，不能重复添加！");
            }
            else{
                HfComTask ssComTask = new HfComTask();
                BeanUtils.copyProperties(comTaskDTO, ssComTask);
                Long newComTaskId = addComTask(comTaskDTO);
                return JsonResult.success(newComTaskId);
            }
        } catch (Exception e) {
            return JsonResult.faultMessage("exception: "+e.getMessage());
        }
    }

    private boolean checkIsExistAccount(HfComTaskDTO comTask){
        Integer isExist = hfComAccountService.isExistAccount(comTask.getCompanyId(),comTask.getHfType());
        if(isExist <= 0){
            isExist = hfComTaskService.isExistComTask(comTask.getCompanyId(),comTask.getHfType(),comTask.getTaskCategory());
        }
        return isExist > 0 ? true : false;
    }

    //保存企业任务单
    private Long addComTask(HfComTaskDTO hfComTaskDTO) {
        HfComTask hfComTask = new HfComTask();
        BeanUtils.copyProperties(hfComTaskDTO,hfComTask);
        hfComTask.setTaskStatus(0);
        hfComTask.setActive(true);
        hfComTask.setCreatedTime(new Date());
        hfComTask.setModifiedTime(new Date());
        hfComTask.setCreatedBy("system");
        hfComTask.setModifiedBy("system");
        hfComTaskService.addComTask(hfComTask);
        return hfComTask.getComTaskId();
    }

    @Override
    @ApiOperation(value = "获取企业公积金账户信息",notes = "根据HfComAccountParamDTO对象获取")
    @ApiImplicitParam(name = "paramDto",value = "企业任务单对象 paramDto",required = true,dataType = "HfComAccountParamDTO")
    @PostMapping("/getAccountList")
    public JsonResult<List<HfComAccountDTO>> getComAccountList(@RequestBody HfComAccountParamDTO paramDto) {

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

    /**
     * 根据公司ID和公积金类别获取公积金账户信息
     * @param companyId 公司ID
     * @param hfType 公积金类型（基本公积金或者补充公积金）
     * @return
     */
    @Override
    @ApiOperation(value = "获取公积金账户信息",notes = "根据公司ID和公积金类别获取公积金账户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "companyId", value = "公司ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "hfType", value = "公积金类型(基本公积金或者补充公积金)", required = true, dataType = "int")
    })
    @GetMapping("/getAccountByCompany")
    public JsonResult<ComAccountExtDTO> getAccountByCompany(@RequestParam("companyId") String companyId, @RequestParam("hfType") Integer hfType) {
        AccountInfoBO info = hfComAccountService.getAccountByCompany(companyId,hfType);
        ComAccountExtDTO extDTO = null;
        if(null != info){
            extDTO = new ComAccountExtDTO();
            BeanUtils.copyProperties(info,extDTO);
        }
        return JsonResult.success(extDTO);
    }
}
