package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.ApplyRecordBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.GiftApplyBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.MarketApplyGrantBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.ApplyDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.ApplyRecordQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发放管理query服务
 *
 * @author xiweizhen
 */
@RestController
@RequestMapping("/grantQueryService")
public class GrantQueryController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GrantQueryController.class);

    @Autowired
    private ApplyRecordQueryService applyRecordQueryService;

    /**
     * 礼品分页列表查询
     *
     * @param applyDTO
     * @return
     */
    @RequestMapping("/applyList")
    public Result applyList(@RequestBody ApplyDTO applyDTO) {
        try {
            Page<ApplyRecordBO> page = new Page<>(applyDTO.getCurrent(), applyDTO.getSize());

            ApplyRecordBO applyRecordBO = new ApplyRecordBO();
            BeanUtils.copyProperties(applyDTO, applyRecordBO);
            if (applyRecordBO.getApplyType() == 1) {
                page = applyRecordQueryService.selectGiftList(page, applyRecordBO);
            } else {
                page = applyRecordQueryService.selectMarketList(page, applyRecordBO);
            }

            BeanUtils.copyProperties(page, applyDTO);
            logger.info("查询申请记录分页列表");
            return ResultGenerator.genSuccessResult(applyDTO);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 礼品发放详细信息
     *
     * @param applyDTO
     * @return
     */
    @RequestMapping("/queryGiftInformation")
    public Result queryGiftInformation(@RequestBody ApplyDTO applyDTO) {
        try {
            ApplyRecordPO applyRecordPO = new ApplyRecordPO();
            BeanUtils.copyProperties(applyDTO, applyRecordPO);

            GiftApplyBO giftApplyBO = applyRecordQueryService.queryGiftApplyInformation(applyRecordPO);

            logger.info("查询申请记录分页列表");
            return ResultGenerator.genSuccessResult(giftApplyBO);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 活动发放详细信息
     *
     * @param applyDTO
     * @return
     */
    @RequestMapping("/queryMarketInformation")
    public Result queryMarketInformation(@RequestBody ApplyDTO applyDTO) {
        try {
            ApplyRecordPO applyRecordPO = new ApplyRecordPO();
            BeanUtils.copyProperties(applyDTO, applyRecordPO);
            MarketApplyGrantBO marketApplyGrantBO = applyRecordQueryService.queryMarketApplyInformation(applyRecordPO);

            logger.info("查询申请记录分页列表");
            return ResultGenerator.genSuccessResult(marketApplyGrantBO);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
