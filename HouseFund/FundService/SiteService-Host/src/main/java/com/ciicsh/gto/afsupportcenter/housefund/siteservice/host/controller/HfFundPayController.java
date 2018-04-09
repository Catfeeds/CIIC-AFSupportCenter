package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentProcessParmBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentComService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentService;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HfFundPayCreatePaymentAccountPara;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyProxyDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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

    @Log("查询公积金汇缴支付编辑操作数据")
    @PostMapping("/fundPaysOperateEditData")
    public JsonResult<List<HfPaymentAccountBo>> postFundPaysOperateData(PageInfo pageInfo) {
        PageRows<HfPaymentAccountBo> pageRows = hfPaymentAccountService.getMakePayLists(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询公积金汇缴支付详细操作数据")
    @PostMapping("/fundPaysOperateDetailData")
    public JsonResult<List<HfPaymentComBo>> postFundPaysDetailData(PageInfo pageInfo) {
        PageRows<HfPaymentComBo> pageRows = hfPaymentComService.getFundPaysDetailOperationData(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询公积金制作汇缴清单列表")
    @PostMapping("/makePayLists")
    public JsonResult<List<HfPaymentAccountBo>> postMakePayLists(PageInfo pageInfo) {
        PageRows<HfPaymentAccountBo> pageRows = hfPaymentAccountService.getMakePayLists(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("删除公积金支付批次")
    @PostMapping("/delHfPayment")
    public JsonResult delHfPayment(String paymentId) {
        return  hfPaymentAccountService.delHfPayment(paymentId);
    }

    @Log("公积金汇缴支付流程操作-送审")
    @PostMapping("/approval")
    public JsonResult processApproval(PaymentProcessParmBO processParmBO){
        if(StringUtils.isBlank(processParmBO.getOperator())){
            processParmBO.setOperator(UserContext.getUserName());
        }
        return hfPaymentService.processApproval(processParmBO);
    }

    @Log("公积金汇缴支付流程操作-汇缴")
    @PostMapping("/payment")
    public JsonResult processPayment(PaymentProcessParmBO processParmBO){
        if(StringUtils.isBlank(processParmBO.getOperator())){
            processParmBO.setOperator(UserContext.getUserName());
        }
        return hfPaymentService.processPayment(processParmBO);
    }

    @Log("公积金汇缴支付流程操作-出票")
    @PostMapping("/ticket")
    public JsonResult processTicket(PaymentProcessParmBO processParmBO){
        if(StringUtils.isBlank(processParmBO.getOperator())){
            processParmBO.setOperator(UserContext.getUserName());
        }
        return hfPaymentService.processTicket(processParmBO);
    }

    @Log("公积金汇缴支付流程操作-回单")
    @PostMapping("/receipt")
    public JsonResult processReceipt(PaymentProcessParmBO processParmBO){
        if(StringUtils.isBlank(processParmBO.getOperator())){
            processParmBO.setOperator(UserContext.getUserName());
        }
        return hfPaymentService.processReceipt(processParmBO);
    }

    @Log("公积金汇缴支付-生成汇缴支付客户名单")
    @PostMapping("/createPaymentComList")
    public JsonResult createPaymentComList(HfFundPayCreatePaymentAccountPara hfFundPayCreatePaymentAccountPara){
        //验证前端传递的数据是否合法,代码暂不写
        //开始生成支付客户名单
        hfFundPayCreatePaymentAccountPara.getListData();
       return hfPaymentComService.createPaymentCom(hfFundPayCreatePaymentAccountPara);
    }

    @Log("公积金汇缴支付-生成网银文件,补缴.txt")
    @PostMapping("/generateBankBujiao")
    public void generateBankBujiao(String paymentId){

    }
    @Log("公积金汇缴支付-生成网银文件,变更.txt")
    @PostMapping("/generateBankChange")
    public void generateBankChange(String paymentId){

    }

    /**
     * 付款凭证打印
     */
    @Autowired
    private PayapplyServiceProxy payapplyServiceProxy;
    @RequestMapping("/printFinancePayVoucher")
    public void printFinancePayVoucher(String payApplyCode,HttpServletResponse response) throws IOException {

        com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult<PayApplyProxyDTO> res =
            payapplyServiceProxy.downloadPayVoucher(payApplyCode);
        byte[] cons = res.getContents();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");

        response.getOutputStream().write(cons);
    }
}
