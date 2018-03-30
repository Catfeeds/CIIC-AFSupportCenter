package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentProcessParmBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentComService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentService;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 公积金汇缴支付控制器：公积金汇缴支付所有相关的操作都集中于此控制器
 * </p>
 *
 * @author 沈健
 * @since 2018-03-14
 */
@RestController
@RequestMapping("/api/fundcommandservice/hfFundPay")
public class HfFundPayController {

    @Autowired
    private HfPaymentService hfPaymentService;
    @Autowired
    private HfPaymentAccountService hfPaymentAccountService;
    @Autowired
    private HfPaymentComService hfPaymentComService;

    @Log("查询公积金汇缴支付列表")
    @PostMapping("/fundPays")
    public JsonResult<List<HfPaymentBo>> postFundPays(PageInfo pageInfo) {
        PageRows<HfPaymentBo> pageRows = hfPaymentService.getFundPays(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询公积金制作汇缴清单列表")
    @PostMapping("/makePayLists")
    public JsonResult<List<HfPaymentAccountBo>> postMakePayLists(PageInfo pageInfo) {
        PageRows<HfPaymentAccountBo> pageRows = hfPaymentAccountService.getMakePayLists(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }


    @Log("公积金汇缴支付流程操作-送审")
    @PostMapping("/approval")
    public JsonResult processApproval(@RequestBody PaymentProcessParmBO processParmBO){
       return hfPaymentService.processApproval(processParmBO);
    }

    @Log("公积金汇缴支付流程操作-汇缴")
    @PostMapping("/payment")
    public JsonResult processPayment(@RequestBody PaymentProcessParmBO processParmBO){
        return hfPaymentService.processPayment(processParmBO);
    }

    @Log("公积金汇缴支付流程操作-出票")
    @PostMapping("/ticket")
    public JsonResult processTicket(@RequestBody PaymentProcessParmBO processParmBO){
        return hfPaymentService.processTicket(processParmBO);
    }

    @Log("公积金汇缴支付流程操作-回单")
    @PostMapping("/receipt")
    public JsonResult processReceipt(@RequestBody PaymentProcessParmBO processParmBO){
        return hfPaymentService.processReceipt(processParmBO);
    }

    @Log("公积金汇缴支付-生成汇缴支付客户名单")
    @PostMapping("/createPaymentCom")
    public JsonResult createPaymentCom(@RequestBody String[] comAccountIds){
        //验证前端传递的数据是否合法,代码暂不写
        //开始生成支付客户名单
        return hfPaymentComService.createPaymentCom( new ArrayList<String>(Arrays.asList(comAccountIds)));
    }


}
