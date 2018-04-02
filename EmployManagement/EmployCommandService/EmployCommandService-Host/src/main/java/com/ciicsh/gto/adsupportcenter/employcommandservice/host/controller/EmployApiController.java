package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.EmployApiProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/30.
 */

@RestController
@RequestMapping("/api/employ")
@Api(value = "soc-api-service",description = "support interface for other center")
public class EmployApiController implements EmployApiProxy {

   @Autowired
   private IAmEmpTaskService amEmpTaskService;

    @Override
    @ApiOperation(value = "根据任务Id查询用工信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据任务Id查询用工信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getEmploymentByTaskId")
    public JsonResult<EmploymentDTO> getEmploymentByTaskId(TaskParamDTO taskParamDTO) {
        JsonResult<EmploymentDTO> jsonResult = new   JsonResult<EmploymentDTO>();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        LocalDate now = LocalDate.now();
        employmentDTO.setEmployDate(now);
        employmentDTO.setEmployFeedback("用工成功");
        employmentDTO.setEmployFeedbackOptDate(now);
        employmentDTO.setEmployOperateMan("测试");
        employmentDTO.setEmployStyle("测试");
        employmentDTO.setHandleType("ceshi");
        employmentDTO.setOpenAfDate(now);

        jsonResult.setData(employmentDTO);

        return jsonResult;
    }

    @Override
    @ApiOperation(value = "根据任务Id查询退工信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据任务Id查询退工信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getResignByTaskId")
    public JsonResult<ResignDTO> getResignByTaskId(TaskParamDTO taskParamDTO) {
        JsonResult<ResignDTO> jsonResult = new    JsonResult<ResignDTO>();
        List<ResignFeedbackDTO> feedbackDTOList = new ArrayList<>();
        ResignDTO resignDTO = new ResignDTO();
        ResignFeedbackDTO resignFeedbackDTO = new ResignFeedbackDTO();
        resignFeedbackDTO.setCreatedTime(LocalDateTime.now());
        resignFeedbackDTO.setResignFeedback("测试");
        resignFeedbackDTO.setResignOperateMan("sdf");
        feedbackDTOList.add(resignFeedbackDTO);
        resignDTO.setFeedbackDTOList(feedbackDTOList);
        LocalDate now = LocalDate.now();
        resignDTO.setCacheDate(now);
        resignDTO.setJobCentreFeedbackDate(now);
        resignDTO.setResignDate(now);
        resignDTO.setResignFeedback("测试");
        resignDTO.setResignMaterialDeliveryDate(now);
        resignDTO.setReturnDocDate(now);
        resignDTO.setReturnDocDate(now);

        jsonResult.setData(resignDTO);

        return jsonResult;
    }

    @Override
    @ApiOperation(value = "根据雇员Id查询档案信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据雇员Id查询档案信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getArchiveByEmployeeId")
    public JsonResult<ArchiveDTO> getArchiveByEmployeeId(TaskParamDTO taskParamDTO) {
        JsonResult<ArchiveDTO> jsonResult = new    JsonResult<ArchiveDTO>();

        ArchiveDTO archiveDTO = new ArchiveDTO();
        archiveDTO.setArchiveCardState("ceshi");
        archiveDTO.setArchivePlace("ceshi");
        archiveDTO.setDocCode("ceshi");
        archiveDTO.setHukouCode("sfd");
        archiveDTO.setEmployDocPaymentTo("sdfsdf");
        archiveDTO.setLuyongHandleEnd("sadfsafd");

        jsonResult.setData(archiveDTO);

        return jsonResult;
    }
}
