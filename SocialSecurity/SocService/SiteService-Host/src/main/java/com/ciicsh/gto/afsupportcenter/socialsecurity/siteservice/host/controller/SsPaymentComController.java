package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.payment.SsAddPaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.payment.SsDelPaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.payment.SsPaymentComDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAddPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsDelPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
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

import java.util.List;

/**
 * <p>
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssPaymentCom")
public class SsPaymentComController  extends BasicController<SsPaymentComService> {
    /**
     * <p>Description: 查询社保支付-企业账户(列表页)</p>
     *
     * @author wengxk
     * @date 2017-12-21
     * @param pageInfo 翻页检索条件
     * @return  JsonResult<>
     */
    @Log("查询社保支付-企业账户(列表页)")
    @PostMapping("/paymentComQuery")
    public JsonResult<List<SsPaymentComDTO>> paymentComQuery(PageInfo pageInfo) {

        PageRows<SsPaymentComBO> pageBORows = business.paymentComQuery(pageInfo);
        PageRows<SsPaymentComDTO> pageRows = new PageRows<SsPaymentComDTO>();
        BeanUtils.copyProperties(pageBORows,pageRows);

        //PageRows<SsPaymentComBO> pageRows = business.paymentComQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }


    /**
     * <p>Description: 保存调整结果</p>
     *
     * @author wengxk
     * @date 2017-12-23
     * @param ssPaymentComDTO 保存的数据
     * @return  JsonResult<>
     */
    @Log("保存调整结果")
    @PostMapping("/saveAdjustment")
    public JsonResult<String> saveAdjustment(SsPaymentComDTO ssPaymentComDTO) {

        //数据转换
        SsPaymentComBO ssPaymentComBO = CommonTransform.convertToEntity(ssPaymentComDTO,SsPaymentComBO.class);


        JsonResult<String> json = new JsonResult<String>();
        json.setCode(0);
        json.setMessage("成功");

        //数据校验

        //计算并保存
        business.saveAdjustment(ssPaymentComBO);
        return json;
    }

    /**
     * <p>Description: 添加至支付批次</p>
     *
     * @author wengxk
     * @date 2017-12-26
     * @param ssAddPaymentDTO 添加条件
     * @return  JsonResult<>
     */
    @Log("添加至支付批次")
    @PostMapping("/doAddBatch")
    public JsonResult<String> doAddBatch(SsAddPaymentDTO ssAddPaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsAddPaymentBO ssAddPaymentBO = CommonTransform.convertToEntity(ssAddPaymentDTO,SsAddPaymentBO.class);
        //执行业务
        json = business.doAddBatch(ssAddPaymentBO);

        return json;
    }
    /**
     * <p>Description: 从支付批次移出</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param ssDelPaymentDTO 移出条件条件
     * @return  JsonResult<>
     */
    @Log("从支付批次移出")
    @PostMapping("/doDelBatch")
    public JsonResult<String> doDelBatch(SsDelPaymentDTO ssDelPaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        SsDelPaymentBO ssDelPaymentBO = CommonTransform.convertToEntity(ssDelPaymentDTO,SsDelPaymentBO.class);
        //执行业务
        json = business.doDelBatch(ssDelPaymentBO);

        return json;
    }

    /**
     * <p>Description: 根据ID获取客户费用信息及扩展信息</p>
     *
     * @author wengxk
     * @date 2018-01-02
     * @param ssPaymentComDTO 查询条件
     * @return  JsonResult<>
     */
    @Log("根据ID获取客户费用信息及扩展信息")
    @PostMapping("/getPaymentComDtoByPaymentId")
    public JsonResult<SsPaymentComDTO> getPaymentComDtoByPaymentId(SsPaymentComDTO ssPaymentComDTO) {
        JsonResult<SsPaymentComDTO> json = new JsonResult<SsPaymentComDTO>();

        SsPaymentComBO ssPaymentComBO = business.getPaymentComBoByPaymentId(ssPaymentComDTO.getPaymentComId());
        SsPaymentComDTO resultDTO = CommonTransform.convertToDTO(ssPaymentComBO,SsPaymentComDTO.class);
        json.setData(resultDTO);

        return json;
    }


}

