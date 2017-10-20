package com.ciicsh.gto.afsupportcenter.socialsecurity.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsEmpBaseAdjustDetailBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsEmpBaseAdjustDetail;
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
 * 《雇员社保基数调整记录明细表》微服务控制器
 */
@RestController
@RequestMapping("/api/socialsecurity/ssempbaseadjustdetail")
@Log("雇员社保基数调整记录明细表")
public class SsEmpBaseAdjustDetailController extends BasicController<ISsEmpBaseAdjustDetailBusiness> {


  @RequestMapping(value = "/finds", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询")
  public PageTip<SsEmpBaseAdjustDetail> finds(PageInfo pageInfo) {
    PageRows<SsEmpBaseAdjustDetail> pageRows = business.finds(pageInfo);
    return TipKit.ofPage(pageRows);
  }

  @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询列表")
  public ListTip<SsEmpBaseAdjustDetail> list(SsEmpBaseAdjustDetail entity) {
    List<SsEmpBaseAdjustDetail> list = business.finds(entity);
    return TipKit.ofList(list);
  }

  /**
   * 保存
   */
  @PostMapping("/save")
  @Log("添加")
  public Tip<String> save(SsEmpBaseAdjustDetail entity) {
    business.save(entity);
    // 返回 ID 方便做后续操作
    return TipKit.of();
  }

  /**
   * 更新
   */
  @PostMapping("/update")
  @Log("更新")
  public Tip<String> update(SsEmpBaseAdjustDetail entity) {
    business.update(entity);
    return TipKit.of();
  }

}
