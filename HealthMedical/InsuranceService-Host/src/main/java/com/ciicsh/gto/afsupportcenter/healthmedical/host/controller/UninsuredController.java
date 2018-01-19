package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 补充医疗理赔表 前端控制器
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

    @PostMapping("/queryAcceptanceList")
    public Result queryAcceptanceList(@RequestBody UninsuredMedicalDTO uninsuredMedicalDTO) {
        try {
            Page<UninsuredMedical> page = new Page<>(uninsuredMedicalDTO.getCurrent(), uninsuredMedicalDTO.getSize());
            UninsuredMedical uninsuredMedical = new UninsuredMedical();
            BeanUtils.copyProperties(uninsuredMedicalDTO, uninsuredMedical);
            page = uninsuredMedicalService.queryAcceptanceList(page, uninsuredMedical);

            BeanUtils.copyProperties(page, uninsuredMedicalDTO);
            logger.info("查询礼品分页列表");
            return ResultGenerator.genSuccessResult(uninsuredMedicalDTO);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

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
