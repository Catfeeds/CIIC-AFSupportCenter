package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmpMemberBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.UninsuredMedicalBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel.SupplyMedicalAcceptanceExcelDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel.UninsuredMedicalExcelDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.CompanyConsultantRelation;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 受理单controller
 * </p>
 *
 * @author xiweizhen
 */
@RestController
@RequestMapping("/uninsuredService")
public class UninsuredController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(UninsuredController.class);

    @Autowired
    private UninsuredMedicalService uninsuredMedicalService;

    /**
     * 查询受理单列表
     *
     * @param uninsuredMedicalDTO
     * @return
     */
    @PostMapping("/queryAcceptanceList")
    public Result queryAcceptanceList(@RequestBody UninsuredMedicalDTO uninsuredMedicalDTO) {
        try {
            Page<UninsuredMedical> page = new Page<>(uninsuredMedicalDTO.getCurrent(), uninsuredMedicalDTO.getSize());
            page = uninsuredMedicalService.queryAcceptanceList(page, uninsuredMedicalDTO);

            logger.info("查询受理单分页列表");
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 导出受理单
     * @param uninsuredMedicalDTO
     * @param response
     * @return
     */
    @GetMapping("/export")
    public Result export(UninsuredMedicalDTO uninsuredMedicalDTO, HttpServletResponse response) {
        List<UninsuredMedical> list = uninsuredMedicalService.selectAll(uninsuredMedicalDTO);

        List<UninsuredMedicalExcelDTO> dtos = new ArrayList<>();
        list.stream().forEach(i -> {
            UninsuredMedicalExcelDTO uninsuredMedicalExcelDTO = new UninsuredMedicalExcelDTO();
            BeanUtils.copyProperties(i,uninsuredMedicalExcelDTO);
            dtos.add(uninsuredMedicalExcelDTO);
        });

        ExcelUtil.exportExcel(dtos,UninsuredMedicalExcelDTO.class,"受理单.xls", response);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 查询雇员列表
     *
     * @param uninsuredMedicalBO
     * @return
     */
    @PostMapping("/queryEmployeeList")
    public Result queryEmployeeList(@RequestBody UninsuredMedicalBO uninsuredMedicalBO) {
        try {
            Page<UninsuredMedicalBO> page = new Page<>(uninsuredMedicalBO.getCurrent(), uninsuredMedicalBO.getSize());
            page = uninsuredMedicalService.queryEmployeeList(page, uninsuredMedicalBO);

            logger.info("查询雇员分页列表");
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 查询业务顾问
     *
     * @param uninsuredMedicalBO
     * @return
     */
    @PostMapping("/queryBusinessConsultant")
    public Result queryBusinessConsultant(@RequestBody CompanyConsultantRelation uninsuredMedicalBO) {
        try {
            List<CompanyConsultantRelation> companyConsultantRelation = uninsuredMedicalService.queryBusinessConsultant(uninsuredMedicalBO.getCompanyId());

            logger.info("查询公司业务顾问");
            return ResultGenerator.genSuccessResult(companyConsultantRelation);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 查询连带人下拉框数据
     *
     * @param uninsuredMedicalDTO
     * @return
     */
    @PostMapping("/queryEmpMember")
    public Result queryEmpMember(@RequestBody UninsuredMedicalDTO uninsuredMedicalDTO) {
        try {
            List<EmpMemberBO> list = uninsuredMedicalService.queryEmpMember(uninsuredMedicalDTO.getEmployeeId());
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 新增受理单
     *
     * @param uninsuredMedicalDTO
     * @return
     */
    @PostMapping("/addAcceptance")
    public Result addAcceptance(@RequestBody UninsuredMedicalDTO uninsuredMedicalDTO) {
        try {
            UninsuredMedical uninsuredMedical = new UninsuredMedical();
            BeanUtils.copyProperties(uninsuredMedicalDTO, uninsuredMedical);
            boolean flag = uninsuredMedicalService.insert(uninsuredMedical);
            logger.info("新增受理单");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 受理单更新
     *
     * @param uninsuredMedicalList
     * @return
     */
    @PostMapping("/updateAcceptanceList")
    public Result updateAcceptance(@RequestBody List<UninsuredMedicalDTO> uninsuredMedicalList) {
        try {
            int t = 0;
            for (UninsuredMedicalDTO uninsuredMedicalDTO : uninsuredMedicalList) {
                UninsuredMedical uninsuredMedical = new UninsuredMedical();
                BeanUtils.copyProperties(uninsuredMedicalDTO, uninsuredMedical);
                boolean flag = uninsuredMedicalService.updateById(uninsuredMedical);
                t = flag ? ++t : t;
            }
            boolean flag = t == uninsuredMedicalList.size();
            logger.info("受理单更新");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 根据主键查询收单数据
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id) {
        try {
            UninsuredMedical uninsuredMedical = uninsuredMedicalService.selectById(id);
            logger.info("代码测试");
            return ResultGenerator.genSuccessResult(uninsuredMedical);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
