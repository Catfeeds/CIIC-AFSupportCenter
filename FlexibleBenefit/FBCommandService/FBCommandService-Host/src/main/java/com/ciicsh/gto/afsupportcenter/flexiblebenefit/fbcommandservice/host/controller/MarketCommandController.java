package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyMarketActivityRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.MarketActivityDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.MarketApplyDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyMarketActivityRecordCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordDetailCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiweizhen
 */
@RestController
@RequestMapping("/marketCommandService")
public class MarketCommandController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketCommandController.class);

    @Autowired
    private MarketActivityCommandService marketActivityCommandService;
    @Autowired
    private ApplyRecordCommandService applyRecordCommandService;
    @Autowired
    private ApplyRecordDetailCommandService applyRecordDetailCommandService;
    @Autowired
    private ApplyMarketActivityRecordCommandService applyMarketActivityRecordCommandService;

    /**
     * 单条查询
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id) {
        try {
            MarketActivityPO entity = marketActivityCommandService.selectById(id);
            logger.info("根据主键查询活动信息");
            return ResultGenerator.genSuccessResult(entity);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 活动新增
     *
     * @param marketActivityDTO
     * @return
     */
    @PostMapping("/marketActivityAdd")
    public Result marketActivityAdd(@RequestBody MarketActivityDTO marketActivityDTO) {
        MarketActivityPO marketActivityPO = new MarketActivityPO();
        BeanUtils.copyProperties(marketActivityDTO, marketActivityPO);
        try {
            boolean flag = marketActivityCommandService.insert(marketActivityPO);
            logger.info("活动新增");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 活动修改
     *
     * @param marketActivityDTO
     * @return
     */
    @PostMapping("/marketActivityUpdate")
    public Result marketActivityUpdate(@RequestBody MarketActivityDTO marketActivityDTO) {
        MarketActivityPO marketActivityPO = new MarketActivityPO();
        BeanUtils.copyProperties(marketActivityDTO, marketActivityPO);
        try {
            boolean flag = marketActivityCommandService.updateById(marketActivityPO);
            logger.info("活动修改");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 活动修改
     *
     * @param list
     * @return
     */
    @PostMapping(value = "/insertMarketApplyList", consumes = {"application/json"})
    public Result insertMarketApplyList(@RequestBody List<MarketApplyDTO> list) {
        try {
            int t = 0;
            ApplyRecordPO applyRecordPO = new ApplyRecordPO();
            /**前端校验数据大于0条*/
            BeanUtils.copyProperties(list.get(0), applyRecordPO);
            applyRecordPO.setApplyType(2);
            applyRecordCommandService.insertSelective(applyRecordPO);
            logger.info("新增活动申请ApplyRecordPO主键" + applyRecordPO.getApplyRecordId());

            for (MarketApplyDTO item : list) {
                ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
                ApplyMarketActivityRecordPO applyMarketActivityRecordPO = new ApplyMarketActivityRecordPO();
                BeanUtils.copyProperties(item, applyRecordDetailPO);
                BeanUtils.copyProperties(item, applyMarketActivityRecordPO);

                /**返回申请主键*/
                applyRecordDetailPO.setApplyRecordId(applyRecordPO.getApplyRecordId());
                applyRecordDetailCommandService.insertSelective(applyRecordDetailPO);
                logger.info("新增活动申请ApplyRecordPO主键" + applyRecordDetailPO.getApplyRecordDetailId());

                /**返回申请详情主键*/
                applyMarketActivityRecordPO.setApplyRecordDetailId(applyRecordDetailPO.getApplyRecordDetailId());
                applyMarketActivityRecordCommandService.insertSelective(applyMarketActivityRecordPO);
                t++;
            }
            boolean flag = (t == list.size());
            logger.info("新增活动申请");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

}
