package com.ciicsh.gto.afsupportcenter.socialsecurity.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsStatementResultBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsStatementResult;
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
 * 《对账差异结果表》微服务控制器
 */
@RestController
@RequestMapping("/api/socialsecurity/ssstatementresult")
@Log("对账差异结果表")
public class SsStatementResultController extends BasicController<ISsStatementResultBusiness> {


  @RequestMapping(value = "/finds", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询")
  public PageTip<SsStatementResult> finds(PageInfo pageInfo) {
    PageRows<SsStatementResult> pageRows = business.finds(pageInfo);
    return TipKit.ofPage(pageRows);
  }

  @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询列表")
  public ListTip<SsStatementResult> list(SsStatementResult entity) {
    List<SsStatementResult> list = business.finds(entity);
    return TipKit.ofList(list);
  }

  /**
   * 保存
   */
  @PostMapping("/save")
  @Log("添加")
  public Tip<String> save(SsStatementResult entity) {
    business.save(entity);
    // 返回 ID 方便做后续操作
    return TipKit.of();
  }

  /**
   * 更新
   */
  @PostMapping("/update")
  @Log("更新")
  public Tip<String> update(SsStatementResult entity) {
    business.update(entity);
    return TipKit.of();
  }

}
