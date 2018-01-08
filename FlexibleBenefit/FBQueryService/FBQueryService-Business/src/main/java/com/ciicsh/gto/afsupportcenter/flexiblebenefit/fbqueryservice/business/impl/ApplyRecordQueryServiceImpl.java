package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.ApplyRecordBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.GiftApplyBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.MarketApplyGrantBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.MarketGrantApprovalBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.*;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.*;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.ApplyRecordQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 申请记录表 服务实现类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@Service
public class ApplyRecordQueryServiceImpl extends ServiceImpl<ApplyRecordQueryMapper, ApplyRecordPO> implements ApplyRecordQueryService {
    /**
     * 申请记录详情
     */
    @Autowired
    private ApplyRecordDetailQueryService applyRecordDetailQueryService;
    /**
     * 审批记录
     */
    @Autowired
    private ApprovalStepQueryService approvalStepQueryService;
    /**
     * 礼品申请详情记录表
     */
    @Autowired
    private ApplyGiftRecordQueryService applyGiftRecordQueryService;

    /**
     * 活动信息
     */
    @Autowired
    private MarketActivityQueryService marketActivityQueryService;

    /**
     * 礼品信息
     */
    @Autowired
    private GiftQueryService giftQueryService;

    /**
     * 礼品发放列表信息
     *
     * @param page
     * @param applyRecordBO
     * @return
     */
    @Override
    public Page<ApplyRecordBO> selectGiftList(Page<ApplyRecordBO> page, ApplyRecordBO applyRecordBO) {
        page.setRecords(baseMapper.selectGiftApplyList(page, applyRecordBO));
        return page;
    }

    /**
     * 活动发放列表信息
     *
     * @param page
     * @param applyRecordBO
     * @return
     */
    @Override
    public Page<ApplyRecordBO> selectMarketList(Page<ApplyRecordBO> page, ApplyRecordBO applyRecordBO) {
        page.setRecords(baseMapper.selectMarketApplyList(page, applyRecordBO));
        return page;
    }

    @Override
    public GiftApplyBO queryGiftApplyInformation(ApplyRecordPO applyRecordPO) {
        GiftApplyBO giftApplyBO = new GiftApplyBO();

        //查询出fb_apply_record_detail表信息
        ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
        applyRecordDetailPO.setApplyRecordId(applyRecordPO.getApplyRecordId());
        applyRecordDetailPO = applyRecordDetailQueryService.queryApplyRecordDetail(applyRecordDetailPO);

        //查询出fb_apply_gift_record表信息
        ApplyGiftRecordPO applyGiftRecordPO = new ApplyGiftRecordPO();
        applyGiftRecordPO.setApplyRecordDetailId(applyRecordDetailPO.getApplyRecordDetailId());
        applyGiftRecordPO = applyGiftRecordQueryService.queryApplyGiftRecord(applyGiftRecordPO);

        //查询审批表信息
        ApprovalStepPO approvalStep = new ApprovalStepPO();
        approvalStep.setApplyRecordDetailId(applyRecordDetailPO.getApplyRecordDetailId());
        List<ApprovalStepPO> approvalStepList = approvalStepQueryService.selectApprovalStepList(approvalStep);

        //查询礼品表信息
        GiftPO giftPO = new GiftPO();
        giftPO.setId(applyGiftRecordPO.getGiftId());
        giftPO = giftQueryService.queryGiftInformation(giftPO);

        giftApplyBO.setApplyRecord(applyRecordPO);
        giftApplyBO.setApplyRecordDetail(applyRecordDetailPO);
        giftApplyBO.setApplyGiftRecord(applyGiftRecordPO);
        giftApplyBO.setGift(giftPO);
        giftApplyBO.setApprovalStepList(approvalStepList);
        return giftApplyBO;
    }

    @Override
    public MarketApplyGrantBO queryMarketApplyInformation(ApplyRecordPO applyRecordPO) {
        MarketApplyGrantBO marketApplyGrantBO = new MarketApplyGrantBO();
        //查询出record表信息
        applyRecordPO = baseMapper.selectById(applyRecordPO.getApplyRecordId());

        //查询出fb_apply_record_detail表信息集合
        ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
        applyRecordDetailPO.setApplyRecordId(applyRecordPO.getApplyRecordId());
        List<MarketGrantApprovalBO> applyRecordDetailList = applyRecordDetailQueryService.selectMarketApplyList(applyRecordPO.getApplyRecordId());
        for (MarketGrantApprovalBO entity : applyRecordDetailList) {
            ApprovalStepPO approvalStepPO = new ApprovalStepPO();
            approvalStepPO.setApplyRecordDetailId(entity.getApplyRecordDetailId());
            List<ApprovalStepPO> approvalStepList = approvalStepQueryService.selectApprovalStepList(approvalStepPO);
            entity.setApprovalStepList(approvalStepList);
        }

        marketApplyGrantBO.setApplyRecord(applyRecordPO);
        marketApplyGrantBO.setRecordDetailList(applyRecordDetailList);
        return marketApplyGrantBO;
    }

}
