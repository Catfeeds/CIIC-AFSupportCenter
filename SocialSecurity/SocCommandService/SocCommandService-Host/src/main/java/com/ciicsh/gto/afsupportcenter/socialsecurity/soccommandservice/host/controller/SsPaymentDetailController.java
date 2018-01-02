package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.payment.SsPaymentDetailDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsPaymentDetailService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPaymentDetail;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 本地社保应付金额交易记录明细表 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssPaymentDetail")
public class SsPaymentDetailController  extends BasicController<ISsPaymentDetailService> {

    /**
     * <p>Description: 付款通知查询(列表页)</p>
     *
     * @author wengxk
     * @date 2018-01-02
     * @param ssPaymentDetailDTO 付款通知查询条件
     * @return  JsonResult<>
     */
    @Log("付款通知查询")
    @PostMapping("/paymentDetailQuery")
    public JsonResult<List<SsPaymentDetailDTO>> statementResultQuery(SsPaymentDetailDTO ssPaymentDetailDTO) {

        SsPaymentDetail ssPaymentDetail = CommonTransform.convertToEntity(ssPaymentDetailDTO,SsPaymentDetail.class);

        List<SsPaymentDetail> resultList =business.paymentDetailQuery(ssPaymentDetail);
        //转换格式
        List<SsPaymentDetailDTO> resultDTOList  = CommonTransform.convertToDTOs(resultList,SsPaymentDetailDTO.class);

        JsonResult<List<SsPaymentDetailDTO>> jsonResult= new JsonResult<>();
        jsonResult.setData(resultDTOList);

        return jsonResult;


    }




}

