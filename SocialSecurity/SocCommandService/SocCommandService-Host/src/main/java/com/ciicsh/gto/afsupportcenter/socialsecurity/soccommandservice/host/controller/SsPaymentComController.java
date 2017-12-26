package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsPaymentComDTO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.GetMapping;
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
public class SsPaymentComController  extends BasicController<ISsPaymentComService> {
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

        PageRows<SsPaymentComDTO> pageRows = business.paymentComQuery(pageInfo);
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
        JsonResult<String> json = new JsonResult<String>();
        json.setCode(200);
        json.setMessage("成功");

        //数据校验

        //计算并保存
        business.saveAdjustment(ssPaymentComDTO);



        return json;
    }
}

