package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpRefundBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpRefundService;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 雇员社保退账受理表。如果为某雇员多缴纳了社保金额，则需向社保局提出退账申请，退账申请受理后落地到此表。
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpRefund")
public class SsEmpRefundController  extends BasicController<SsEmpRefundService> {

    /**
     * 查询退账详情
     * @param ssEmpRefundBO
     * @return
     */
   @RequestMapping("/queryRefundDetails")
    public JsonResult<List<SsEmpRefundBO>> queryRefundDetails(SsEmpRefundBO ssEmpRefundBO){
        if(StringUtils.isBlank(ssEmpRefundBO.getSsMonth()) || null == ssEmpRefundBO.getSsAccount())
            throw new BusinessException("条件不足");
        ssEmpRefundBO.setSsMonth(ssEmpRefundBO.getSsMonth().substring(0,6));
        List<SsEmpRefundBO> ssEmpRefundBOList= business.selectRefundDetail(ssEmpRefundBO);
        return JsonResultKit.of(ssEmpRefundBOList);
    }
}

