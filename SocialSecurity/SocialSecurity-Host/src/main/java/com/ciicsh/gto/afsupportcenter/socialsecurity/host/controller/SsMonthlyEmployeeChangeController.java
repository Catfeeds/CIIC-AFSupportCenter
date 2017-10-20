package com.ciicsh.gto.afsupportcenter.socialsecurity.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsMonthlyEmployeeChangeBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsMonthlyEmployeeChange;
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
 * 《雇员月度变更主表》微服务控制器
 */
@RestController
@RequestMapping("/api/socialsecurity/ssmonthlyemployeechange")
@Log("雇员月度变更主表")
public class SsMonthlyEmployeeChangeController extends BasicController<ISsMonthlyEmployeeChangeBusiness> {


  @RequestMapping(value = "/finds", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询")
  public PageTip<SsMonthlyEmployeeChange> finds(PageInfo pageInfo) {
    PageRows<SsMonthlyEmployeeChange> pageRows = business.finds(pageInfo);
    return TipKit.ofPage(pageRows);
  }

  @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询列表")
  public ListTip<SsMonthlyEmployeeChange> list(SsMonthlyEmployeeChange entity) {
    List<SsMonthlyEmployeeChange> list = business.finds(entity);
    return TipKit.ofList(list);
  }

  /**
   * 保存
   */
  @PostMapping("/save")
  @Log("添加")
  public Tip<String> save(SsMonthlyEmployeeChange entity) {
    business.save(entity);
    // 返回 ID 方便做后续操作
    return TipKit.of();
  }

  /**
   * 更新
   */
  @PostMapping("/update")
  @Log("更新")
  public Tip<String> update(SsMonthlyEmployeeChange entity) {
    business.update(entity);
    return TipKit.of();
  }

}
