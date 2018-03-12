package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.FragmentaryReimbursementQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel.FragmentaryReimbursementExcelDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel.SupplyMedicalAcceptanceExcelDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;

import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 零星报销表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */

@RestController
@RequestMapping("/api/afsupportcenter/healthmedical/FragmentaryReimbursement")

    public class FragmentaryReimbursementController extends BasicController<FragmentaryReimbursementQueryService> {
    private static Logger logger = LoggerFactory.getLogger(SupplyMedicalController.class);
    @Autowired
    private FragmentaryReimbursementQueryService fragmentaryReimbursementQueryService;
    @Autowired
    private EmployeeInfoProxy employeeInfoProxy;

    @Log("新增")
    @PostMapping("/save")
    public Result save(@RequestBody FragmentaryReimbursementPO po) {
        try {
            Boolean code = fragmentaryReimbursementQueryService.insert(po);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("更新")
    @PostMapping("/edit")
    public Result edit(@RequestBody FragmentaryReimbursementPO po) {
        try {
            Boolean code = fragmentaryReimbursementQueryService.updateAllColumnById(po);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("零星报销单条记录查询")
    @GetMapping("/getEntityById")
    public Result getEntityById(String id) {
        try {
            FragmentaryReimbursementPO code = fragmentaryReimbursementQueryService.getById(id);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @PostMapping("/getEmployeeInfo")
    public Result getEmployeeInfo(@RequestBody FragmentaryReimbursementDTO fragmentaryReimbursementDTO) {
        try {
            EmployeeHireInfoQueryDTO queryDTO = new EmployeeHireInfoQueryDTO();
            queryDTO.setCompanyId(fragmentaryReimbursementDTO.getCompanyId());
            queryDTO.setEmployeeId(fragmentaryReimbursementDTO.getEmployeeId());
            com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeHireInfoDTO> info = employeeInfoProxy.getEmployeeHireInfo(queryDTO);
            return ResultGenerator.genSuccessResult(info);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @PostMapping("/getEntityList")
    public Result getEntityList(@RequestBody FragmentaryReimbursementDTO fragmentaryReimbursementDTO) {
        try {
            Page<FragmentaryReimbursementPO> page = new Page<>(fragmentaryReimbursementDTO.getCurrent(), fragmentaryReimbursementDTO.getSize());
            page = fragmentaryReimbursementQueryService.getEntityList(page, fragmentaryReimbursementDTO);
            logger.info("零星报销分页查询");
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 零星报销导出
     * @param fragmentaryReimbursementDTO
     * @param response
     * @return
     */
    @GetMapping("/export")
    public Result export(FragmentaryReimbursementDTO fragmentaryReimbursementDTO, HttpServletResponse response) {
//        Page<FragmentaryReimbursementPO> page = new Page<>(fragmentaryReimbursementDTO.getCurrent(), fragmentaryReimbursementDTO.getTotal());
//        List<FragmentaryReimbursementPO> list = fragmentaryReimbursementQueryService.getEntityList(page, fragmentaryReimbursementDTO).getRecords();
        FragmentaryReimbursementPO params = new FragmentaryReimbursementPO();
        BeanUtils.copyProperties(fragmentaryReimbursementDTO,params);
        List<FragmentaryReimbursementPO> list = fragmentaryReimbursementQueryService.selectAll(params);

        ArrayList<FragmentaryReimbursementExcelDTO> dtos = new ArrayList<>();
        list.stream().forEach(i -> {
            FragmentaryReimbursementExcelDTO  fragmentaryReimbursementExcelDTO= new FragmentaryReimbursementExcelDTO();
            BeanUtils.copyProperties(i,fragmentaryReimbursementExcelDTO);
            dtos.add(fragmentaryReimbursementExcelDTO);
        });

        ExcelUtil.exportExcel(dtos,FragmentaryReimbursementExcelDTO.class,"零星报销.xls", response);
        return ResultGenerator.genSuccessResult(null);
    }
}


