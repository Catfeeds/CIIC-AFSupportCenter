package com.ciicsh.gto.afsupportcenter.socialsecurity.host.controller;

import java.util.List;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsEmployeeTaskBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsEmployeeTask;
import org.springframework.web.bind.annotation.*;

import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.tips.ListTip;
import com.ciicsh.gto.afsupportcenter.util.tips.PageTip;
import com.ciicsh.gto.afsupportcenter.util.tips.Tip;
import com.ciicsh.gto.afsupportcenter.util.tips.TipKit;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;

/**
 * 《本地社保的雇员任务单》微服务控制器
 */
@RestController
@RequestMapping("/api/socialsecurity/ssemployeetask")
@Log("本地社保的雇员任务单")
public class SsEmployeeTaskController extends BasicController<ISsEmployeeTaskBusiness> {


  @RequestMapping(value = "/finds", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询")
  public PageTip<SsEmployeeTask> finds(PageInfo pageInfo) {
    PageRows<SsEmployeeTask> pageRows = business.finds(pageInfo);
    return TipKit.ofPage(pageRows);
  }

  @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
  @Log("查询列表")
  public ListTip<SsEmployeeTask> list(SsEmployeeTask entity) {
    List<SsEmployeeTask> list = business.finds(entity);
    return TipKit.ofList(list);
  }

  /**
   * 保存
   */
  @PostMapping("/save")
  @Log("添加")
  public Tip<String> save(SsEmployeeTask entity) {
    business.save(entity);
    // 返回 ID 方便做后续操作
    return TipKit.of();
  }

  /**
   * 更新
   */
  @PostMapping("/update")
  @Log("更新")
  public Tip<String> update(SsEmployeeTask entity) {
    business.update(entity);
    return TipKit.of();
  }

}
