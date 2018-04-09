package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentProcessParmBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankComAccountBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankExportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankQueryBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentComService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentService;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.LogMessage;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfComAccountConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfMonthChargeConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HfFundPayCreatePaymentAccountPara;
import com.ciicsh.gto.afsupportcenter.util.ZipUtil;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
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
import java.util.*;

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
    @Autowired
    private HfMonthChargeService hfMonthChargeService;
    @Autowired
    private LogApiUtil logApiUtil;

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
    @RequestMapping("/generateBankRepair")
    public void generateBankRepair(HttpServletResponse response, String paymentId) throws BusinessException {
        List<HFNetBankComAccountBO> hfNetBankComAccountBOList = hfPaymentAccountService.getComAccountByPaymentId(Long.valueOf(paymentId));

        try {
            if (CollectionUtils.isNotEmpty(hfNetBankComAccountBOList)) {
                Map<String, String> repairMap = new HashMap<>();
                HFNetBankQueryBO hfNetBankQueryBO;

                String fileName = URLEncoder.encode("网银文件_补缴TXT.zip", "UTF-8");
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/zip");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

                for (HFNetBankComAccountBO hfNetBankComAccountBO : hfNetBankComAccountBOList) {
                    hfNetBankQueryBO = new HFNetBankQueryBO();
                    hfNetBankQueryBO.setComAccountClassId(hfNetBankComAccountBO.getComAccountClassId());
                    hfNetBankQueryBO.setHfMonth(hfNetBankComAccountBO.getPaymentMonth());
                    hfNetBankQueryBO.setHfType(hfNetBankComAccountBO.getHfType());
                    hfNetBankQueryBO.setPaymentTypes(String.join(",", new String[]{
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_REPAIR),
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_DIFF_REPAIR)
                    }));

                    List<HFNetBankExportBO> repairList = hfMonthChargeService.queryNetBankData(hfNetBankQueryBO);

                    if (CollectionUtils.isNotEmpty(repairList)) {
                        if (hfNetBankComAccountBO.getHfAccountType() == HfComAccountConstant.HF_ACCOUNT_TYPE_INDEPENDENT) {
                            hfPaymentAccountService.setIndependentNetBankRepairData(repairMap,
                                hfNetBankComAccountBO.getHfComAccount(),
                                hfNetBankComAccountBO.getHfType(),
                                hfNetBankComAccountBO.getPaymentMonth(),
                                repairList);
                        } else {
                            hfPaymentAccountService.setBigStorageNetBankRepairData(repairMap,
                                hfNetBankComAccountBO.getHfComAccount(),
                                hfNetBankComAccountBO.getHfType(),
                                hfNetBankComAccountBO.getPaymentMonth(),
                                repairList);
                        }
                    }
                }

                if (!repairMap.isEmpty()) {
                    ZipUtil.createZipFileWithTxtFiles(response.getOutputStream(), repairMap);
                }
            }
        } catch (Exception e) {
            logApiUtil.error(LogMessage.create().setTitle("生成网银文件补缴").setContent(e.getMessage()));
            throw new BusinessException(e);
        }
    }
    @Log("公积金汇缴支付-生成网银文件,变更.txt")
    @RequestMapping("/generateBankChange")
    public void generateBankChange(HttpServletResponse response, String paymentId) throws BusinessException {
        List<HFNetBankComAccountBO> hfNetBankComAccountBOList = hfPaymentAccountService.getComAccountByPaymentId(Long.valueOf(paymentId));

        try {
            if (CollectionUtils.isNotEmpty(hfNetBankComAccountBOList)) {
                Map<String, String> changeMap = new HashMap<>();
                HFNetBankQueryBO hfNetBankQueryBO;

                String fileName = URLEncoder.encode("网银文件_变更TXT.zip", "UTF-8");
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/zip");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

                for (HFNetBankComAccountBO hfNetBankComAccountBO : hfNetBankComAccountBOList) {
                    hfNetBankQueryBO = new HFNetBankQueryBO();
                    hfNetBankQueryBO.setComAccountClassId(hfNetBankComAccountBO.getComAccountClassId());
                    hfNetBankQueryBO.setHfMonth(hfNetBankComAccountBO.getPaymentMonth());
                    hfNetBankQueryBO.setHfType(hfNetBankComAccountBO.getHfType());
                    hfNetBankQueryBO.setPaymentTypes(String.join(",", new String[]{
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_NEW)
                    }));
                    List<HFNetBankExportBO> newList = hfMonthChargeService.queryNetBankData(hfNetBankQueryBO);

                    hfNetBankQueryBO.setPaymentTypes(String.join(",", new String[]{
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_TRANS_IN),
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_OPEN),
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_OPEN)
                    }));
                    List<HFNetBankExportBO> inList = hfMonthChargeService.queryNetBankData(hfNetBankQueryBO);

                    hfNetBankQueryBO.setPaymentTypes(String.join(",", new String[]{
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_CLOSE),
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_CLOSE)
                    }));
                    List<HFNetBankExportBO> outList = hfMonthChargeService.queryNetBankData(hfNetBankQueryBO);

                    if (hfNetBankComAccountBO.getHfAccountType() == HfComAccountConstant.HF_ACCOUNT_TYPE_INDEPENDENT) {
                        hfPaymentAccountService.setIndependentNetBankChangeData(changeMap,
                            hfNetBankComAccountBO.getHfComAccount(),
                            hfNetBankComAccountBO.getHfType(),
                            hfNetBankComAccountBO.getPaymentMonth(),
                            newList,
                            inList,
                            outList);
                    } else {
                        hfPaymentAccountService.setBigStorageNetBankChangeData(changeMap,
                            hfNetBankComAccountBO.getHfComAccount(),
                            hfNetBankComAccountBO.getHfType(),
                            hfNetBankComAccountBO.getPaymentMonth(),
                            newList,
                            inList,
                            outList);
                    }
                }

                if (!changeMap.isEmpty()) {
                    ZipUtil.createZipFileWithTxtFiles(response.getOutputStream(), changeMap);
                }
            }
        } catch (Exception e) {
            logApiUtil.error(LogMessage.create().setTitle("生成网银文件变更").setContent(e.getMessage()));
            throw new BusinessException(e);
        }
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
