package com.ciicsh.gto.afsupportcenter.socialsecurity.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsPaymentDetailBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsPaymentDetail;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.tips.ListTip;
import com.ciicsh.gto.afsupportcenter.util.tips.PageTip;
import com.ciicsh.gto.afsupportcenter.util.tips.Tip;
import com.ciicsh.gto.afsupportcenter.util.tips.TipKit;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 《本地社保应付金额交易记录明细表》微服务控制器
 */
@RestController
@RequestMapping("/api/socialsecurity/sspaymentdetail")
@Log("本地社保应付金额交易记录明细表")
public class SsPaymentDetailController extends BasicController<ISsPaymentDetailBusiness> {


  @RequestMapping(value = "/finds", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询")
  public PageTip<SsPaymentDetail> finds(PageInfo pageInfo) {
    PageRows<SsPaymentDetail> pageRows = business.finds(pageInfo);
    return TipKit.ofPage(pageRows);
  }

  @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询列表")
  public ListTip<SsPaymentDetail> list(SsPaymentDetail entity) {
    List<SsPaymentDetail> list = business.finds(entity);
    return TipKit.ofList(list);
  }

  /**
   * 保存
   */
  @PostMapping("/save")
  @Log("添加")
  public Tip<String> save(SsPaymentDetail entity) {
    business.save(entity);
    // 返回 ID 方便做后续操作
    return TipKit.of();
  }

  /**
   * 更新
   */
  @PostMapping("/update")
  @Log("更新")
  public Tip<String> update(SsPaymentDetail entity) {
    business.update(entity);
    return TipKit.of();
  }

}
