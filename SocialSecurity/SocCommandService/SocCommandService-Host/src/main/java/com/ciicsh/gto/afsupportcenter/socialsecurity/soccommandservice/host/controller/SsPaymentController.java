package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.payment.SsPaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.statement.SsPaymentSrarchDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsPaymentService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentSrarchBO;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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


        PageRows<SsPaymentBO> pageBORows = business.paymentQuery(pageInfo);
        PageRows<SsPaymentDTO> pageRows = new PageRows<SsPaymentDTO>();
        BeanUtils.copyProperties(pageBORows,pageRows);


        //PageRows<SsPaymentBO> pageRows = business.paymentQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }


    /**
     * <p>Description: 按照条件显示可加入的批次</p>
     *
     * @author wengxk
     * @date 2017-12-26
     * @param paymentSrarchDTO 检索条件
     * @return  JsonResult<>
     */
    @Log("按照条件显示可加入的批次")
    @PostMapping("/showAddBatch")
    public JsonResult<List<SsPaymentDTO>> showAddBatch(SsPaymentSrarchDTO paymentSrarchDTO ) {

        SsPaymentSrarchBO ssPaymentSrarchBO = CommonTransform.convertToEntity(paymentSrarchDTO,SsPaymentSrarchBO.class);

        //将要检索的状态写入查询条件
        List<String> paymentStateList = new ArrayList<>();
        //3 ,可付
        paymentStateList.add("3");
        //5,内部审批批退
        paymentStateList.add("5");
        ssPaymentSrarchBO.setPaymentStateList(paymentStateList);

        List<SsPaymentBO> resultList = business.showAddBatch(ssPaymentSrarchBO);

        List<SsPaymentDTO> resultDTOList = CommonTransform.convertToDTOs(resultList,SsPaymentDTO.class);



        JsonResult<List<SsPaymentDTO>> jsonResult = new JsonResult<>();
        jsonResult.setData(resultDTOList);

        return jsonResult;
    }
}

