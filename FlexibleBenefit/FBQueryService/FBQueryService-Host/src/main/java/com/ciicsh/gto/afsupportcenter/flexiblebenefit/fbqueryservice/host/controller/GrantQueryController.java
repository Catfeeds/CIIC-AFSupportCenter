package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.ApplyRecordBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.GiftApplyBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.MarketApplyBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.*;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.ApplyDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 发放管理query服务
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
    @Autowired
    private ApplyRecordDetailQueryService applyRecordDetailQueryService;
    /**
     * 礼品的查询
     */
    @Autowired
    private ApplyGiftRecordQueryService applyGiftRecordQueryService;
    @Autowired
    private GiftQueryService giftQueryService;
    /**
     * 活动的查询
     */
    @Autowired
    private ApplyMarketActivityRecordQueryService applyMarketActivityRecordQueryService;
    @Autowired
    private MarketActivityQueryService marketActivityQueryService;

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
            page = applyRecordQueryService.selectGiftList(page, applyRecordBO);

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
            GiftApplyBO giftApplyBO = new GiftApplyBO();

            ApplyRecordPO applyRecordPO = new ApplyRecordPO();
            BeanUtils.copyProperties(applyDTO, applyRecordPO);
            //查询出record表信息
            applyRecordPO = applyRecordQueryService.selectById(applyRecordPO.getApplyRecordId());

            //查询出fb_apply_record_detail表信息
            ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
            applyRecordDetailPO.setApplyRecordId(applyRecordPO.getApplyRecordId());
            applyRecordDetailPO = applyRecordDetailQueryService.queryApplyRecordDetail(applyRecordDetailPO);

            //查询出fb_apply_gift_record表信息
            ApplyGiftRecordPO applyGiftRecordPO = new ApplyGiftRecordPO();
            applyGiftRecordPO.setApplyRecordDetailId(applyRecordDetailPO.getApplyRecordDetailId());
            applyGiftRecordPO = applyGiftRecordQueryService.queryApplyGiftRecord(applyGiftRecordPO);

            //查询礼品表信息
            GiftPO giftPO = new GiftPO();
            giftPO.setId(applyGiftRecordPO.getGiftId());
            giftPO = giftQueryService.queryGiftInformation(giftPO);

            giftApplyBO.setApplyRecordPO(applyRecordPO);
            giftApplyBO.setApplyRecordDetailPO(applyRecordDetailPO);
            giftApplyBO.setApplyGiftRecordPO(applyGiftRecordPO);
            giftApplyBO.setGiftPO(giftPO);

            logger.info("查询申请记录分页列表");
            return ResultGenerator.genSuccessResult(giftApplyBO);
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
    @RequestMapping("/queryMarketInformation")
    public Result queryMarketInformation(@RequestBody ApplyDTO applyDTO) {
        try {
            MarketApplyBO marketApplyBO = new MarketApplyBO();

            ApplyRecordPO applyRecordPO = new ApplyRecordPO();
            BeanUtils.copyProperties(applyDTO, applyRecordPO);
            //查询出record表信息
            applyRecordPO = applyRecordQueryService.selectById(applyRecordPO.getApplyRecordId());

            //查询出fb_apply_record_detail表信息集合
            ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
            applyRecordDetailPO.setApplyRecordId(applyRecordPO.getApplyRecordId());
            List<ApplyRecordDetailPO> applyRecordDetailList = applyRecordDetailQueryService.queryApplyRecordDetailList(applyRecordDetailPO);

            //查询出fb_apply_market_activity_record表信息集合,遍历查询
            List<ApplyMarketActivityRecordPO> applyMarketActivityRecordList = new ArrayList<>();
            for(ApplyRecordDetailPO entity:applyRecordDetailList){
                ApplyMarketActivityRecordPO applyMarketActivityRecordPO = new ApplyMarketActivityRecordPO();
                applyMarketActivityRecordPO.setApplyRecordDetailId(entity.getApplyRecordDetailId());
                applyMarketActivityRecordPO=applyMarketActivityRecordQueryService.queryApplyMarketRecord(applyMarketActivityRecordPO);

                applyMarketActivityRecordList.add(applyMarketActivityRecordPO);
            }


            //查询活动表信息
            MarketActivityPO marketActivityPO = new MarketActivityPO();
            marketActivityPO.setId(applyMarketActivityRecordList.get(0).getActivityId());
            marketActivityPO = marketActivityQueryService.queryMarketInformation(marketActivityPO);

            marketApplyBO.setApplyRecordPO(applyRecordPO);
            marketApplyBO.setRecordDetailList(applyRecordDetailList);
            marketApplyBO.setApplyMarketActivityRecordList(applyMarketActivityRecordList);
            marketApplyBO.setMarketActivityPO(marketActivityPO);

            logger.info("查询申请记录分页列表");
            return ResultGenerator.genSuccessResult(marketApplyBO);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
