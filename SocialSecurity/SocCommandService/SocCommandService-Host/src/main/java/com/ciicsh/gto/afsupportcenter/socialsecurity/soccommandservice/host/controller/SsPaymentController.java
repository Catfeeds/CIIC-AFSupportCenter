package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsPaymentService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsPaymentDTO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 社保支付批次 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssPayment")
public class SsPaymentController  extends BasicController<ISsPaymentService> {

    /**
     * <p>Description: 查询社保支付-支付批次(列表页)</p>
     *
     * @author wengxk
     * @date 2017-12-22
     * @param pageInfo 翻页检索条件
     * @return  JsonResult<>
     */
    @Log("查询社保支付-支付批次(列表页)")
    @PostMapping("/paymentQuery")
    public JsonResult<List<SsPaymentDTO>> paymentComQuery(PageInfo pageInfo) {

        PageRows<SsPaymentDTO> pageRows = business.paymentQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }
}

