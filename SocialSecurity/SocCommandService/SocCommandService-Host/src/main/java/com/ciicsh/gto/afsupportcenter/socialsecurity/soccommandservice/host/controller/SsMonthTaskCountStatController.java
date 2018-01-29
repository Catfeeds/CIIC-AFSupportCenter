package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsMonthTaskCountStatService;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 首页中，系统用户执行的任务单数的月度分类统计。 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@RestController
@RequestMapping("/api/soccommandservice/ssMonthTaskCountStat")
public class SsMonthTaskCountStatController  extends BasicController<SsMonthTaskCountStatService> {

}

