package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsOperatePaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentSrarchBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPayment;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.ExportResponseUtil;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyProxyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 社保支付批次 前端控制器
 */
@RestController
@RequestMapping("/api/soccommandservice/ssPayment")
public class SsPaymentController extends BasicController<SsPaymentService> {

    @Autowired
    private SsPaymentComService ssPaymentComService;
    @Autowired
    private SsPaymentService ssPaymentService;
    @Autowired
    private LogApiUtil logApiUtil;
    @Autowired
    private PayapplyServiceProxy payapplyServiceProxy;
    /**
     * 查询社保支付-支付批次(列表页)
     * @param pageInfo 翻页检索条件
     * @return JsonResult<>
     */
    @PostMapping("/paymentQuery")
    public JsonResult<List<SsPaymentBO>> paymentQuery(PageInfo pageInfo) {
        PageRows<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentBO> pageBORows = business.paymentQuery(pageInfo);
        PageRows<SsPaymentBO> pageRows = new PageRows<SsPaymentBO>();
        BeanUtils.copyProperties(pageBORows, pageRows);
        return JsonResultKit.ofPage(pageRows);
    }


    /**
     * 按照条件显示可加入的批次
     * @param paymentSrarchDTO 检索条件
     * @return JsonResult<>
     */
    @PostMapping("/showAddBatch")
    public JsonResult<List<SsPaymentBO>> showAddBatch(SsPaymentSrarchBO paymentSrarchDTO) {
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentSrarchBO ssPaymentSrarchBO = CommonTransform.convertToEntity(paymentSrarchDTO, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentSrarchBO
            .class);
        //将要检索的状态写入查询条件
        List<String> paymentStateList = new ArrayList<>();
        paymentStateList.add("3");//3 ,可付
        paymentStateList.add("5");//5,内部审批批退
        paymentStateList.add("7");//财务部批退
        ssPaymentSrarchBO.setPaymentStateList(paymentStateList);
        List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentBO> resultList = business.showAddBatch(ssPaymentSrarchBO);
        List<SsPaymentBO> resultDTOList = CommonTransform.convertToDTOs(resultList, SsPaymentBO.class);
        JsonResult<List<SsPaymentBO>> jsonResult = new JsonResult<>();
        jsonResult.setData(resultDTOList);
        return jsonResult;
    }

    /**
     * 申请支付
     * @param ssOperatePaymentDTO 批次操作参数
     * @return JsonResult<>
     */
    @PostMapping("/doApplyPay")
    public JsonResult<String> doApplyPay(SsOperatePaymentBO ssOperatePaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssOperatePaymentDTO, SsPayment.class);
        //执行业务
        json = business.doApplyPay(ssPayment);
        return json;
    }


    /**
     * 删除批次
     * @param ssOperatePaymentDTO 批次操作参数
     * @return JsonResult<>
     */
    @PostMapping("/doDelPayment")
    public JsonResult<String> doDelPayment(SsOperatePaymentBO ssOperatePaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssOperatePaymentDTO, SsPayment.class);
        //执行业务
        json = business.doDelPayment(ssPayment);
        return json;
    }

    /**
     * 获取支付年月
     * @return JsonResult<>
     * @author wengxk
     */
    @PostMapping("/getPaymentMonth")
    public JsonResult<String> getPaymentMonth() {
        JsonResult<String> json = new JsonResult<String>();
        Calendar cal = Calendar.getInstance();
        //获得年月日
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        //小于规定日则取上月
        if (date < 1) {
            cal.add(Calendar.MONTH, -1);
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
        }
        //拼接字符
        String paymentMonth = year + "" + (String.valueOf(month).length()==1?"0"+month : month);

  /*      Map retMap = new HashMap();
        retMap.put("paymentMonth",paymentMonth);
        retMap.put("batchNum",paymentMonth);*/
        json.setData(paymentMonth);
        return json;
    }

    /**
     * 添加批次
     * @param ssPaymentDTO 保存批次参数
     * @return JsonResult<>
     */
    @PostMapping("/addPayment")
    public JsonResult<String> addPayment(SsPaymentBO ssPaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssPaymentDTO, SsPayment.class);
        //执行业务
        json = business.addPayment(ssPayment);
        return json;
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
     * 查询社保支付审核-审核批次(列表页)
     * @param pageInfo 翻页检索条件
     * @return JsonResult<>
     */
    @PostMapping("/paymentReviewedQuery")
    public JsonResult<List<SsPaymentBO>> paymentReviewedQuery(PageInfo pageInfo) {

        //处理参数
        //如果页面没有选择支付状态则放入默认支付状态
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentBO ssPaymentBO = pageInfo.toJavaObject(com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentBO.class);
        if (!Optional.ofNullable(ssPaymentBO.getPaymentState()).isPresent()) {
            List<Integer> paymentStateList = new ArrayList<>();
            //申请中
            paymentStateList.add(4);
            //已申请到财务部
            paymentStateList.add(6);
            //财务部支付成功
            paymentStateList.add(8);
            ssPaymentBO.setPaymentStateList(paymentStateList);
            pageInfo.put(ssPaymentBO);
        }

        PageRows<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentBO> pageBORows = business.paymentQuery(pageInfo);
        PageRows<SsPaymentBO> pageRows = new PageRows<SsPaymentBO>();
        BeanUtils.copyProperties(pageBORows, pageRows);
        return JsonResultKit.ofPage(pageRows);
    }

    /**
     * 社保支付申请审批通过
     * @param ssOperatePaymentDTO 批次操作参数
     * @return JsonResult<>
     */
    @PostMapping("/doReviewdePass")
    public JsonResult<String> doReviewdePass(SsOperatePaymentBO ssOperatePaymentDTO) {
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssOperatePaymentDTO, SsPayment.class);
        //执行业务
        JsonResult<String> json = business.doReviewdePass(ssPayment);
        //如果返回失败 不更新
        if (json.getCode() == 0) {
            ssPaymentComService.saveReviewdePassResult(ssPayment,json.getData());
        }
        return json;
    }

    /**
     * 批次批退
     * @param ssOperatePaymentDTO 批次操作参数
     * @return JsonResult<>
     */
    @PostMapping("/doRejection")
    public JsonResult<String> doRejection(SsOperatePaymentBO ssOperatePaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssOperatePaymentDTO, SsPayment.class);
        //执行业务
        json = business.doRejection(ssPayment);
        return json;
    }
    @PostMapping("/getLastMonth")
    public JsonResult<String> getLastMonth(){
        JsonResult<String> json = new JsonResult<String>();
        String yyyyMM = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy/MM"));
        json.setData(yyyyMM);
        return json;
    }

}

