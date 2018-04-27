package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsOperatePaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentSrarchBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPayment;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * 社保支付批次 前端控制器
 */
@RestController
@RequestMapping("/api/soccommandservice/ssPayment")
public class SsPaymentController extends BasicController<SsPaymentService> {

    @Autowired
    private SsPaymentComService ssPaymentComService;

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

//        try {
//            Thread.currentThread().sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssOperatePaymentDTO, SsPayment.class);
        //执行业务
        JsonResult<String> json = business.doReviewdePass(ssPayment);

        //如果返回失败 不更新
        if (json.getCode() == 0) {
            ssPaymentComService.saveReviewdePassResult(ssPayment);
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
}

