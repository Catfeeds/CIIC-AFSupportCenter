package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.business.ZyTpaTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.ZyTpaTaskPO;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * TPA任务单记录表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2018-02-11
 */
@RestController
@RequestMapping("/api/afsupportcenter/healthmedical/zyTpaTask")
public class ZyTpaTaskController extends BasicController<ZyTpaTaskService> {
    @Autowired
    private ZyTpaTaskService zyTpaTaskService;

    @GetMapping("/getEntityById")
    public void getEntityById(String id) {

      zyTpaTaskService.getZyTpaTaskListBymonth("", "");

    }
}
