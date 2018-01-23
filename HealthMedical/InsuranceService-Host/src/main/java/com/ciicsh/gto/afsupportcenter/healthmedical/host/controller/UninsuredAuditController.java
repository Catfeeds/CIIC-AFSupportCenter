package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalAuditService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalAuditDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 未投保审核controller
 * </p>
 *
 * @author xiweizhen
 */
@RestController
@RequestMapping("/uninsuredAuditService")
public class UninsuredAuditController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(UninsuredAuditController.class);

    @Autowired
    private UninsuredMedicalAuditService uninsuredMedicalAuditService;

    /**
     * 查询受理单列表
     *
     * @param uninsuredMedicalAuditDTO
     * @return
     */
    @PostMapping("/queryAcceptanceList")
    public Result queryUninsuredList(@RequestBody UninsuredMedicalAuditDTO uninsuredMedicalAuditDTO) {
        try {
            if (uninsuredMedicalAuditDTO.getStatus()) {
                return ResultGenerator.genSuccessResult();
            } else {
                Page<UninsuredMedical> page = new Page<>(uninsuredMedicalAuditDTO.getCurrent(), uninsuredMedicalAuditDTO.getSize());
                page = uninsuredMedicalAuditService.queryAcceptanceList(page, uninsuredMedicalAuditDTO);
                logger.info("查询受理单分页列表");
                return ResultGenerator.genSuccessResult(page);
            }
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
