package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.GiftCommandProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.GiftDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gift")
public class GiftApiController implements GiftCommandProxy {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GiftApiController.class);

    @Autowired
    private GiftQueryService giftQueryService;

    @Override
    public Result findGiftList(GiftDTO entity) {
        return ResultGenerator.genSuccessResult();
    }
}
