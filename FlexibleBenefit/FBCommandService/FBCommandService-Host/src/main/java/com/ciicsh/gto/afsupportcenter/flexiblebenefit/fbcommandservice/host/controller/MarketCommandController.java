package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * 单挑查询
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
     * @param entity
     * @return
     */
    @PostMapping("/marketActivityAdd")
    public Result marketActivityAdd(MarketActivityPO entity) {
        try {
            boolean flag = marketActivityCommandService.insert(entity);
            logger.info("活动新增");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 活动修改
     *
     * @param entity
     * @return
     */
    @PostMapping("/marketActivityUpdate")
    public Result marketActivityUpdate(MarketActivityPO entity) {
        try {
            boolean flag = marketActivityCommandService.updateById(entity);
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

}
