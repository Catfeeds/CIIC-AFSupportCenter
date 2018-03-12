package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gt1.FileHandler;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalAuditService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.UninsuredMedicalAuditDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedicalAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private UninsuredMedicalService uninsuredMedicalService;

    /**
     * 查询受理单列表
     *
     * @param uninsuredMedicalAuditDTO
     * @return
     */
    @PostMapping("/queryAcceptanceList")
    public Result queryUninsuredList(@RequestBody UninsuredMedicalAuditDTO uninsuredMedicalAuditDTO) {
        try {
            Page<UninsuredMedical> page = new Page<>(uninsuredMedicalAuditDTO.getCurrent(), uninsuredMedicalAuditDTO.getSize());
            if (uninsuredMedicalAuditDTO.getStatus()) {
                // 查询已审核数据
                page = uninsuredMedicalAuditService.queryAcceptanceAuditList(page, uninsuredMedicalAuditDTO);
                logger.info("查询受已审核理单分页列表");
                return ResultGenerator.genSuccessResult(page);
            } else {
                // 查询未审核数据
                page = uninsuredMedicalAuditService.queryAcceptanceList(page, uninsuredMedicalAuditDTO);
                logger.info("查询未审核受理单分页列表");
                return ResultGenerator.genSuccessResult(page);
            }
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 未投保审核,同事更新未投保状态
     *
     * @param uninsuredMedicalAuditDTO
     * @return
     */
    @PostMapping("/addUninsuredAudit")
    public Result addUninsuredAudit(UninsuredMedicalAuditDTO uninsuredMedicalAuditDTO) {
        try {
            MultipartFile file = uninsuredMedicalAuditDTO.getAttachment();
            String filePathUrl = null;
            if (file != null && !file.isEmpty()) {
                /**上传图片*/
                filePathUrl = FileHandler.uploadFile(file.getInputStream());
            }
            UninsuredMedicalAudit uninsuredMedicalAudit = new UninsuredMedicalAudit();
            BeanUtils.copyProperties(uninsuredMedicalAuditDTO, uninsuredMedicalAudit);
            if (filePathUrl != null) {
                uninsuredMedicalAudit.setAttachment(filePathUrl);
            }
            boolean flag = uninsuredMedicalAuditService.insert(uninsuredMedicalAudit);
            if (flag) {
                UninsuredMedical uninsuredMedical = new UninsuredMedical();
                uninsuredMedical.setUmAcceptanceId(uninsuredMedicalAuditDTO.getUmAcceptanceId());
                // 状态为--已审核未同步
                uninsuredMedical.setStatus(3);
                flag = uninsuredMedicalService.updateById(uninsuredMedical);
            }
            logger.info("未投保审核");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
