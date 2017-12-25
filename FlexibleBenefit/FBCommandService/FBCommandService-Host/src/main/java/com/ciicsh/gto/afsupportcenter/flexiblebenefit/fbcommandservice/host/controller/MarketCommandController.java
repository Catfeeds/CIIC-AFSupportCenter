package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.MarketActivityDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result marketActivityAdd(MarketActivityDTO marketActivityDTO) {
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
    public Result marketActivityUpdate(MarketActivityDTO marketActivityDTO) {
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

}
