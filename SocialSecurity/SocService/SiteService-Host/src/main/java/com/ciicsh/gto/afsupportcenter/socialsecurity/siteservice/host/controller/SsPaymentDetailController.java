package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentDetailService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPaymentDetail;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 本地社保应付金额交易记录明细表 前端控制器
 */
@RestController
@RequestMapping("/api/soccommandservice/ssPaymentDetail")
public class SsPaymentDetailController  extends BasicController<SsPaymentDetailService> {

    /**
     * 付款通知查询(列表页)
     * @param ssPaymentDetailDTO 付款通知查询条件
     * @return  JsonResult<>
     */
    @PostMapping("/paymentDetailQuery")
    public JsonResult<List<SsPaymentDetailBO>> statementResultQuery(SsPaymentDetailBO ssPaymentDetailDTO) {
//        if(StringUtils.isBlank(ssPaymentDetailDTO.getPaymentMonth()) || null == ssPaymentDetailDTO.getSsAccount())
//            throw new BusinessException("缺少必要的传递参数");
//        if( null == ssPaymentDetailDTO.getCompanyId() && null == ssPaymentDetailDTO.getSsAccount())
//            throw new BusinessException("企业社保账号和客户编号必选一项");
       // SsPaymentDetail ssPaymentDetail = CommonTransform.convertToEntity(ssPaymentDetailDTO,SsPaymentDetail.class);

        List<SsPaymentDetail> resultList =business.paymentDetailQuery(ssPaymentDetailDTO);
        //转换格式
        List<SsPaymentDetailBO> resultDTOList  = CommonTransform.convertToDTOs(resultList,SsPaymentDetailBO.class);

        JsonResult<List<SsPaymentDetailBO>> jsonResult= new JsonResult<>();
        jsonResult.setData(resultDTOList);

        return jsonResult;


    }




}

