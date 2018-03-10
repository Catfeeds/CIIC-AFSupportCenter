package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.MedicalRelationTransformQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.MedicalRelationTransformDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel.FragmentaryReimbursementExcelDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel.MedicalRelationTransformExcelDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.MedicalRelationTransformPO;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 医疗关系转移表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */
@RestController
@RequestMapping("/api/afsupportcenter/healthmedical/MedicalRelationTransform")
public class MedicalRelationTransformController  extends BasicController<MedicalRelationTransformQueryService> {

    @Autowired
    private MedicalRelationTransformQueryService medicalRelationTransformQueryService;

    @Log("新增")
    @PostMapping("/save")
    public Result save(@RequestBody MedicalRelationTransformPO po) {
        try {
            Boolean code = medicalRelationTransformQueryService.insert(po);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("更新")
    @PostMapping("/edit")
    public  Result edit(@RequestBody MedicalRelationTransformPO po) {
        try {
            Boolean code = medicalRelationTransformQueryService.updateAllColumnById(po);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("医疗关系转移单条记录查询")
    @GetMapping("/getEntityById")
    public Result getEntityById(@RequestBody String id) {
        try {
            MedicalRelationTransformPO code = medicalRelationTransformQueryService.getById(id);
            return ResultGenerator.genSuccessResult(code);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @Log("医疗关系转移查询")
    @PostMapping("/getEntityList")

    public Result getEntityList(@RequestBody MedicalRelationTransformDTO  medicalRelationTransformDTO) {
        try {
            Page<MedicalRelationTransformPO> page = new Page<>(medicalRelationTransformDTO.getCurrent(), medicalRelationTransformDTO.getSize());
            MedicalRelationTransformPO medicalRelationTransformPO = new MedicalRelationTransformPO();
            BeanUtils.copyProperties(medicalRelationTransformDTO, medicalRelationTransformPO);
            page = business.medicalRelationTransformMapperQuery(page, medicalRelationTransformPO);
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 医疗关系转移导出
     * @param medicalRelationTransformDTO
     * @param response
     * @return
     */
    @GetMapping("/export")
    public Result export(MedicalRelationTransformDTO  medicalRelationTransformDTO, HttpServletResponse response) {
        Page<MedicalRelationTransformPO> page = new Page<>(medicalRelationTransformDTO.getCurrent(), medicalRelationTransformDTO.getTotal());
        MedicalRelationTransformPO medicalRelationTransformPO = new MedicalRelationTransformPO();
        BeanUtils.copyProperties(medicalRelationTransformDTO, medicalRelationTransformPO);
        List<MedicalRelationTransformPO> list = business.medicalRelationTransformMapperQuery(page, medicalRelationTransformPO).getRecords();

        ArrayList<MedicalRelationTransformExcelDTO> dtos = new ArrayList<>();
        list.stream().forEach(i -> {
            MedicalRelationTransformExcelDTO  medicalRelationTransformExcelDTO= new MedicalRelationTransformExcelDTO();
            BeanUtils.copyProperties(i,medicalRelationTransformExcelDTO);
            dtos.add(medicalRelationTransformExcelDTO);
        });

        ExcelUtil.exportExcel(dtos,MedicalRelationTransformExcelDTO.class,"医疗关系转移.xls", response);
        return ResultGenerator.genSuccessResult(null);
    }
}
