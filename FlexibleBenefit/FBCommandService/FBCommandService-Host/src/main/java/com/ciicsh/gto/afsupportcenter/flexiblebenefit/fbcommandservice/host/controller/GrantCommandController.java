package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApprovalStepPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.GrantDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordDetailCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApprovalStepCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发放管理command服务
 * @author xiweizhen
 */
@RestController
@RequestMapping("/grantCommandService")
public class GrantCommandController {

    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GrantCommandController.class);

    @Autowired
    private ApprovalStepCommandService approvalStepCommandService;
    @Autowired
    private ApplyRecordDetailCommandService applyRecordDetailCommandService;

    /**
     * 发放功能
     *
     * @param grantDTO
     * @return
     */
    @PostMapping("/updateApplyGrant")
    public Result updateApplyGrant(@RequestBody GrantDTO grantDTO) {
        try {
            ApprovalStepPO approvalStepPO = new ApprovalStepPO();
            ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();

            BeanUtils.copyProperties(grantDTO, approvalStepPO);
            BeanUtils.copyProperties(grantDTO, applyRecordDetailPO);

            boolean flag = applyRecordDetailCommandService.updateById(applyRecordDetailPO);

            logger.info("弹性福利申请发放功能");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

}
