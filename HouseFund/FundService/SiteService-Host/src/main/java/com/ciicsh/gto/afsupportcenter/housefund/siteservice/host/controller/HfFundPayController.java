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
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfComAccountConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfMonthChargeConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HfFundPayCreatePaymentAccountPara;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import com.ciicsh.gto.afsupportcenter.util.ZipUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.ExportResponseUtil;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyProxyDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private PayapplyServiceProxy payapplyServiceProxy;

    /**
     * 查询公积金汇缴支付列表
     * @param pageInfo
     * @return
     */
    @PostMapping("/fundPays")
    public JsonResult<List<HfPaymentBo>> postFundPays(PageInfo pageInfo) {
        PageRows<HfPaymentBo> pageRows = hfPaymentService.getFundPays(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    /**
     * 查询公积金制作汇缴清单列表
     * @param pageInfo
     * @return
     */
    @PostMapping("/makePayLists")
    public JsonResult<List<HfPaymentAccountBo>> postMakePayLists(PageInfo pageInfo) {
        PageRows<HfPaymentAccountBo> pageRows = hfPaymentAccountService.getMakePayLists(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    /**
     * 删除公积金支付批次
     * @param paymentId
     * @return
     */
    @PostMapping("/delHfPayment")
    public JsonResult delHfPayment(String paymentId) {
        return  hfPaymentAccountService.delHfPayment(paymentId);
    }

    /**
     * 公积金汇缴支付流程操作-送审
     * @param processParmBO
     * @return
     */
    @PostMapping("/approval")
    public JsonResult processApproval(PaymentProcessParmBO processParmBO){
        if(StringUtils.isBlank(processParmBO.getOperator())){
            processParmBO.setOperator(UserContext.getUserName());
        }
        return hfPaymentService.processApproval(processParmBO);
    }

    /**
     * 公积金汇缴支付流程操作-汇缴
     * @param processParmBO
     * @return
     */
    @PostMapping("/payment")
    public JsonResult processPayment(PaymentProcessParmBO processParmBO){
        if(StringUtils.isBlank(processParmBO.getOperator())){
            processParmBO.setOperator(UserContext.getUserName());
        }
        return hfPaymentService.processPayment(processParmBO);
    }

    /**
     * 公积金汇缴支付流程操作-出票
     * @param processParmBO
     * @return
     */
    @PostMapping("/ticket")
    public JsonResult processTicket(PaymentProcessParmBO processParmBO){
        if(StringUtils.isBlank(processParmBO.getOperator())){
            processParmBO.setOperator(UserContext.getUserName());
        }
        return hfPaymentService.processTicket(processParmBO);
    }

    /**
     * 公积金汇缴支付流程操作-回单
     * @param processParmBO
     * @return
     */
    @PostMapping("/receipt")
    public JsonResult processReceipt(PaymentProcessParmBO processParmBO){
        if(StringUtils.isBlank(processParmBO.getOperator())){
            processParmBO.setOperator(UserContext.getUserName());
        }
        return hfPaymentService.processReceipt(processParmBO);
    }

    /**
     * 公积金汇缴支付-生成汇缴支付客户名单
     * @param hfFundPayCreatePaymentAccountPara
     * @return
     */
    @PostMapping("/createPaymentComList")
    public JsonResult createPaymentComList(HfFundPayCreatePaymentAccountPara hfFundPayCreatePaymentAccountPara){
        //验证前端传递的数据是否合法,代码暂不写
        //开始生成支付客户名单

       return hfPaymentComService.createPaymentCom(hfFundPayCreatePaymentAccountPara);
    }
    //临时生成支付数据
    @Autowired
    HfPaymentAccountMapper hfPaymentAccountMapper;
    @PostMapping("/createPaymentComListImpData")
    public JsonResult createPaymentComListImpData(String month,String createdBy){
        //验证前端传递的数据是否合法,代码暂不写
        //开始生成支付客户名单
        HfFundPayCreatePaymentAccountPara hfFundPayCreatePaymentAccountPara=new HfFundPayCreatePaymentAccountPara();
        List list=new ArrayList();
        Map m=new HashMap();
        m.put("is_active","1");
        m.put("created_by","impdata");
        m.put("payment_month",month);
        List<HfPaymentAccount> account= hfPaymentAccountMapper.selectByMap(m);
        account.stream().forEach(dd->{
            list.add(dd.getComAccountId());
        });
        UserContext.getUser().setDisplayName(createdBy);
        hfFundPayCreatePaymentAccountPara.setListData(list);
        hfFundPayCreatePaymentAccountPara.setPaymentMonth(month);
        hfFundPayCreatePaymentAccountPara.setPaymentWay(3);
        hfFundPayCreatePaymentAccountPara.setPayee("住房资金归集待结算户");
        hfPaymentComService.createPaymentCom(hfFundPayCreatePaymentAccountPara);



        return JsonResultKit.of();
    }
    /**
     * 公积金汇缴支付-生成网银文件,补缴.txt
     * @param response
     * @param paymentId
     * @throws Exception
     */
    @RequestMapping("/generateBankRepair")
    public void generateBankRepair(HttpServletResponse response, String paymentId) throws Exception {
        List<HFNetBankComAccountBO> hfNetBankComAccountBOList = hfPaymentAccountService.getComAccountByPaymentId(Long.valueOf(paymentId));

        try {
            if (CollectionUtils.isNotEmpty(hfNetBankComAccountBOList)) {
                Map<String, String> repairMap = new HashMap<>();
                HFNetBankQueryBO hfNetBankQueryBO;

                for (HFNetBankComAccountBO hfNetBankComAccountBO : hfNetBankComAccountBOList) {
                    hfNetBankQueryBO = new HFNetBankQueryBO();
                    hfNetBankQueryBO.setComAccountClassId(hfNetBankComAccountBO.getComAccountClassId());
                    hfNetBankQueryBO.setHfMonth(hfNetBankComAccountBO.getPaymentMonth());
                    hfNetBankQueryBO.setHfType(hfNetBankComAccountBO.getHfType());
                    hfNetBankQueryBO.setPaymentTypes(String.join(",", new String[]{
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_REPAIR),
                        String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_DIFF_REPAIR)
                    }));
                    hfNetBankQueryBO.setExceptRepairReason(HfMonthChargeConstant.REPAIR_OFF_BALANCE_PAYMENT);

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

                String fileName = "网银文件_补缴TXT.zip";
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/zip");
//                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                ExportResponseUtil.encodeExportFileName(response, fileName);
                ZipUtil.createZipFileWithTxtFiles(response.getOutputStream(), repairMap);
            }
        } catch (Exception e) {
            logApiUtil.error(LogMessage.create().setTitle("生成网银文件补缴").setContent(e.getMessage()));
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "text/html");
            response.getWriter().write(e.getMessage() + "<a href=\"javascript:history.go(-1)\">返回</a>");
        }
    }

    /**
     * 公积金汇缴支付-生成网银文件,变更.txt
     * @param response
     * @param paymentId
     * @throws Exception
     */
    @RequestMapping("/generateBankChange")
    public void generateBankChange(HttpServletResponse response, String paymentId) throws Exception {
        List<HFNetBankComAccountBO> hfNetBankComAccountBOList = hfPaymentAccountService.getComAccountByPaymentId(Long.valueOf(paymentId));

        try {
            if (CollectionUtils.isNotEmpty(hfNetBankComAccountBOList)) {
                Map<String, String> changeMap = new HashMap<>();
                HFNetBankQueryBO hfNetBankQueryBO;

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

                String fileName = "网银文件_变更TXT.zip";
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/zip");
//                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                ExportResponseUtil.encodeExportFileName(response, fileName);
                ZipUtil.createZipFileWithTxtFiles(response.getOutputStream(), changeMap);
            }
        } catch (Exception e) {
            logApiUtil.error(LogMessage.create().setTitle("生成网银文件变更").setContent(e.getMessage()));
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "text/html");
            response.getWriter().write(e.getMessage() + "<a href=\"javascript:history.go(-1)\">返回</a>");
        }
    }
    /**
     * 打印汇缴书
     */
    @PostMapping("/printRemittedBook")
    public JsonResult printRemittedBook(@RequestParam(value = "paymentId", required = true) Long paymentId,
                                        @RequestParam(value = "hfType", required = true) Integer hfType){

        return hfPaymentService.printRemittedBook(paymentId,hfType);
    }

    /**
     * 付款凭证打印
     */
    @RequestMapping("/printFinancePayVoucher")
    public void printFinancePayVoucher(String payApplyCode,HttpServletResponse response) throws IOException {

        com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult<PayApplyProxyDTO> res =
            payapplyServiceProxy.downloadPayVoucher(payApplyCode);
        byte[] content = res.getContents();
        BufferedOutputStream bos = null;
        ServletOutputStream outputStream = null;
        BufferedInputStream bis = null;
        try{
            response.setCharacterEncoding("UTF-8");
            // response.setHeader("content-Type", "application/vnd.ms-excel");
            // response.setHeader("Content-Disposition", "attachment;filename=" +  URLEncoder.encode("付款凭证打印.xlsx", "UTF-8"));
            response.setContentType("application/octet-stream;charset=utf-8");
            ExportResponseUtil.encodeExportFileName(response, "付款凭证打印.xlsx");
            response.setContentLength(content.length);
            outputStream = response.getOutputStream();
            InputStream is = new ByteArrayInputStream(content);
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(outputStream);

            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        }catch (Exception e){
            logApiUtil.error(LogMessage.create().setTitle("HfFundPayController#printFinancePayVoucher").setContent(e.getMessage()));
//            e.printStackTrace();
        }finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
    /**
     * 公积金汇缴支付编辑操作数据
     * @param pageInfo 查询条件
     * @return 查询结果
     */
    @PostMapping("/fundPaysOperateEditData")
    public JsonResult<List<HfPaymentAccountBo>> postFundPaysOperateEditData(PageInfo pageInfo) {
        // 支付状态: 0,无需支付 1 ,可付(默认)   2,送审   3 汇缴(已申请到财务部 ) 4  财务部批退  5,财务部审批通过  6 出票 7  回单
        // 只有可付和送审才允许编辑
        String paymentState = pageInfo.getParams().getString("paymentState");
        if ("0".equals(paymentState) || "1".equals(paymentState) || "2".equals(paymentState) || "4".equals(paymentState) ){
            PageRows<HfPaymentAccountBo> pageRows = hfPaymentAccountService.getFundPaysEditOperationData(pageInfo);
            return JsonResultKit.ofPage(pageRows);
        }else{
            return JsonResultKit.of(0,"当前状态，不允许编辑！");
        }

    }

    /**
     * 公积金汇缴支付详细操作数据
     * @param pageInfo 查询条件
     * @return 查询结果
     */
    @PostMapping("/fundPaysOperateDetailData")
    public JsonResult<List<HfPaymentAccountBo>> postFundPaysOperateDetailData(PageInfo pageInfo) {
        PageRows<HfPaymentAccountBo> pageRows = hfPaymentAccountService.getFundPaysEditOperationData(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    /**
     * 公积金汇缴支付-编辑页面添加汇缴支付名单
     * @param hfFundPayCreatePaymentAccountPara 生成信息
     * @return 生成结果
     */
    @PostMapping("/createPaymentComListById")
    public JsonResult createPaymentComListById(HfFundPayCreatePaymentAccountPara hfFundPayCreatePaymentAccountPara){
        //开始生成支付客户名单
        return hfPaymentComService.createPaymentComById(hfFundPayCreatePaymentAccountPara);
    }

    /**
     * 删除公积金汇缴支付编辑操作数据
     *
     * @param hfPaymentAccountBo 删除条件
     * @return 删除结果
     */
    @PostMapping("/delOperateEditData")
    public JsonResult delOperateEditData(HfPaymentAccountBo hfPaymentAccountBo) {
        return hfPaymentAccountService.delOperateEditData(hfPaymentAccountBo);
    }
}
