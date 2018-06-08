package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAddPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsDelPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.empSSSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录 前端控制器
 */
@RestController
@RequestMapping("/api/soccommandservice/ssPaymentCom")
public class SsPaymentComController  extends BasicController<SsPaymentComService> {
    /**
     * 查询社保支付-企业账户(列表页)
     * @param pageInfo 翻页检索条件
     * @return  JsonResult<>
     */
    @PostMapping("/paymentComQuery")
    public JsonResult<List<SsPaymentComBO>> paymentComQuery(PageInfo pageInfo) {

        PageRows<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentComBO> pageBORows = business.paymentComQuery(pageInfo);
        PageRows<SsPaymentComBO> pageRows = new PageRows<SsPaymentComBO>();
        BeanUtils.copyProperties(pageBORows,pageRows);

        //PageRows<SsPaymentComBO> pageRows = business.paymentComQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @RequestMapping("/paymentComQueryExport")
    public void paymentComQueryExport(HttpServletResponse response, SsPaymentComBO ssPaymentComBO) {
        Date date = new Date();
        String fileNme = "社保支付_"+ StringUtil.getDateString(date)+".xls";
        List<SsPaymentComBO> opts = business.paymentComQueryExport(ssPaymentComBO);
        ExcelUtil.exportExcel(opts,SsPaymentComBO.class,fileNme,response);
    }
    /**
     * 保存调整结果
     * @param ssPaymentComDTO 保存的数据
     * @return  JsonResult<>
     */
    @PostMapping("/saveAdjustment")
    public JsonResult<String> saveAdjustment(SsPaymentComBO ssPaymentComDTO) {

        //数据转换
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentComBO ssPaymentComBO = CommonTransform.convertToEntity(ssPaymentComDTO, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentComBO.class);


        JsonResult<String> json = new JsonResult<String>();
        json.setCode(0);
        json.setMessage("成功");

        //数据校验

        //计算并保存
        business.saveAdjustment(ssPaymentComBO);
        return json;
    }

    /**
     * 添加至支付批次
     * @param ssAddPaymentDTO 添加条件
     * @return  JsonResult<>
     */
    @PostMapping("/doAddBatch")
    public JsonResult<String> doAddBatch(SsAddPaymentBO ssAddPaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAddPaymentBO ssAddPaymentBO = CommonTransform.convertToEntity(ssAddPaymentDTO, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAddPaymentBO.class);
        //执行业务
        json = business.doAddBatch(ssAddPaymentBO);

        return json;
    }

    /**
     * 从支付批次移出
     * @param ssDelPaymentDTO 移出条件条件
     * @return  JsonResult<>
     */
    @PostMapping("/doDelBatch")
    public JsonResult<String> doDelBatch(SsDelPaymentBO ssDelPaymentDTO) {
        JsonResult<String> json = new JsonResult<String>();
        //数据转换
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsDelPaymentBO ssDelPaymentBO = CommonTransform.convertToEntity(ssDelPaymentDTO, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsDelPaymentBO.class);
        //执行业务
        json = business.doDelBatch(ssDelPaymentBO);

        return json;
    }

    /**
     * 根据ID获取客户费用信息及扩展信息
     * @param ssPaymentComDTO 查询条件
     * @return  JsonResult<>
     */
    @PostMapping("/getPaymentComDtoByPaymentId")
    public JsonResult<SsPaymentComBO> getPaymentComDtoByPaymentId(SsPaymentComBO ssPaymentComDTO) {
        JsonResult<SsPaymentComBO> json = new JsonResult<SsPaymentComBO>();
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentComBO ssPaymentComBO = business.getPaymentComBoByPaymentId(ssPaymentComDTO.getPaymentComId());
        SsPaymentComBO resultDTO = CommonTransform.convertToDTO(ssPaymentComBO,SsPaymentComBO.class);
        json.setData(resultDTO);
        return json;
    }


}

