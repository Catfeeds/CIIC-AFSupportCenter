package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;



import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.FragmentaryReimbursementCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 零星报销表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */
@RestController
@RequestMapping("/api/afsupportcenter/healthmedical/commandservice/FragmentaryReimbursement")
   public class FragmentaryReimbursementController extends BasicController<FragmentaryReimbursementCommandService> {


    @Log("新增")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public JsonResult<Integer> save(FragmentaryReimbursementPO po) {
        int code = business.save(po);
        if(code == 0){
            return JsonResultKit.of(400, "无数据更新",  (Integer) null);
        }else{
            return JsonResultKit.of(400, "操作成功",  (Integer) null);
        }
    }

    @Log("更新")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public JsonResult<Integer> edit(FragmentaryReimbursementPO po) {
        int code = business.edit(po);
        if(code == 0){
            return JsonResultKit.of(400, "无数据更新",  (Integer) null);
        }else{
            return JsonResultKit.of(400, "操作成功",  (Integer) null);
        }
    }
}
