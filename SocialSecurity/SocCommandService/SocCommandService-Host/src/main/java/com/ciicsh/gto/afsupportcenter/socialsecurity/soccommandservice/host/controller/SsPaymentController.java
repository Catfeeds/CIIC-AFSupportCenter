package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.payment.SsOperatePaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.payment.SsPaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.payment.SsPaymentSrarchDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentSrarchBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsPaymentService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPayment;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
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
 * <p>
 * 社保支付批次 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssPayment")
public class SsPaymentController extends BasicController<SsPaymentService> {

    @Autowired
    private SsPaymentComService ssPaymentComService;

    /**
     * <p>Description: 查询社保支付-支付批次(列表页)</p>
     *
     * @param pageInfo 翻页检索条件
     * @return JsonResult<>
     * @author wengxk
     * @date 2017-12-22
     */
    @Log("查询社保支付-支付批次(列表页)")
    @PostMapping("/paymentQuery")
    public JsonResult<List<SsPaymentDTO>> paymentQuery(PageInfo pageInfo) {


        PageRows<SsPaymentBO> pageBORows = business.paymentQuery(pageInfo);
        PageRows<SsPaymentDTO> pageRows = new PageRows<SsPaymentDTO>();
        BeanUtils.copyProperties(pageBORows, pageRows);


        //PageRows<SsPaymentBO> pageRows = business.paymentQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }


    /**
     * <p>Description: 按照条件显示可加入的批次</p>
     *
     * @param paymentSrarchDTO 检索条件
     * @return JsonResult<>
     * @author wengxk
     * @date 2017-12-26
     */
    @Log("按照条件显示可加入的批次")
    @PostMapping("/showAddBatch")
    public JsonResult<List<SsPaymentDTO>> showAddBatch(SsPaymentSrarchDTO paymentSrarchDTO) {

        SsPaymentSrarchBO ssPaymentSrarchBO = CommonTransform.convertToEntity(paymentSrarchDTO, SsPaymentSrarchBO
            .class);

        //将要检索的状态写入查询条件
        List<String> paymentStateList = new ArrayList<>();
        //3 ,可付
        paymentStateList.add("3");
        //5,内部审批批退
        paymentStateList.add("5");
        ssPaymentSrarchBO.setPaymentStateList(paymentStateList);

        List<SsPaymentBO> resultList = business.showAddBatch(ssPaymentSrarchBO);

        List<SsPaymentDTO> resultDTOList = CommonTransform.convertToDTOs(resultList, SsPaymentDTO.class);


        JsonResult<List<SsPaymentDTO>> jsonResult = new JsonResult<>();
        jsonResult.setData(resultDTOList);

        return jsonResult;
    }

    /**
     * <p>Description: 申请支付</p>
     *
     * @param ssOperatePaymentDTO 批次操作参数
     * @return JsonResult<>
     * @author wengxk
     * @date 2017-12-28
     */
    @Log("申请支付")
    @PostMapping("/doApplyPay")
    public JsonResult<String> doApplyPay(SsOperatePaymentDTO ssOperatePaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssOperatePaymentDTO, SsPayment.class);
        //执行业务
        json = business.doApplyPay(ssPayment);

        return json;
    }


    /**
     * <p>Description: 删除批次</p>
     *
     * @param ssOperatePaymentDTO 批次操作参数
     * @return JsonResult<>
     * @author wengxk
     * @date 2017-12-28
     */
    @Log("删除批次")
    @PostMapping("/doDelPayment")
    public JsonResult<String> doDelPayment(SsOperatePaymentDTO ssOperatePaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssOperatePaymentDTO, SsPayment.class);
        //执行业务
        json = business.doDelPayment(ssPayment);

        return json;
    }

    /**
     * <p>Description: 获取支付年月</p>
     *
     * @return JsonResult<>
     * @author wengxk
     * @date 2017-12-28
     */
    @Log("获取支付年月")
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
     * <p>Description: 添加批次</p>
     *
     * @param ssPaymentDTO 保存批次参数
     * @return JsonResult<>
     * @author wengxk
     * @date 2017-12-28
     */
    @Log("添加批次")
    @PostMapping("/addPayment")
    public JsonResult<String> addPayment(SsPaymentDTO ssPaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssPaymentDTO, SsPayment.class);
        //执行业务
        json = business.addPayment(ssPayment);

        return json;
    }

    /**
     * <p>Description: 查询社保支付审核-审核批次(列表页)</p>
     *
     * @param pageInfo 翻页检索条件
     * @return JsonResult<>
     * @author wengxk
     * @date 2017-01-05
     */
    @Log("查询社保支付-支付批次(列表页)")
    @PostMapping("/paymentReviewedQuery")
    public JsonResult<List<SsPaymentDTO>> paymentReviewedQuery(PageInfo pageInfo) {

        //处理参数
        //如果页面没有选择支付状态则放入默认支付状态
        SsPaymentBO ssPaymentBO = pageInfo.toJavaObject(SsPaymentBO.class);
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

        PageRows<SsPaymentBO> pageBORows = business.paymentQuery(pageInfo);
        PageRows<SsPaymentDTO> pageRows = new PageRows<SsPaymentDTO>();
        BeanUtils.copyProperties(pageBORows, pageRows);


        return JsonResultKit.ofPage(pageRows);
    }

    /**
     * <p>Description: 社保支付申请审批通过</p>
     *
     * @param ssOperatePaymentDTO 批次操作参数
     * @return JsonResult<>
     * @author zhangxj
     * @date 2018-01-05
     */
    @Log("批次审批通过")
    @PostMapping("/doReviewdePass")
    public JsonResult<String> doReviewdePass(SsOperatePaymentDTO ssOperatePaymentDTO) {
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
     * <p>Description: 批次批退</p>
     *
     * @param ssOperatePaymentDTO 批次操作参数
     * @return JsonResult<>
     * @author wengxk
     * @date 2018-01-05
     */
    @Log("批次批退")
    @PostMapping("/doRejection")
    public JsonResult<String> doRejection(SsOperatePaymentDTO ssOperatePaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsPayment ssPayment = CommonTransform.convertToEntity(ssOperatePaymentDTO, SsPayment.class);
        //执行业务
        json = business.doRejection(ssPayment);

        return json;
    }
}

