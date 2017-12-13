package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;



import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.MedicalRelationTransformCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.MedicalRelationTransformPO;
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

/**
 * <p>
 * 医疗关系转移表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */
@RestController
@RequestMapping("/api/afsupportcenter/healthmedical/commandservice//MedicalRelationTransform")

public class MedicalRelationTransformController extends BasicController<MedicalRelationTransformCommandService> {

    @Log("新增")
    @RequestMapping(value = "/save", method = { RequestMethod.POST})
    public JsonResult<Integer> save(MedicalRelationTransformPO po) {
        JsonResult jr = new JsonResult();
        Integer num = business.save(po);
        if (num==0)
        {
            jr.setCode(400);
            jr.setTotal((long) 0);
            jr.setMessage("无数据更新");
        }
        else
        {
            jr.setCode(200);
            jr.setTotal((long) num);
            jr.setMessage("操作成功");

        }
        return jr;
    }

    @Log("更新")
    @RequestMapping(value = "/edit", method = { RequestMethod.POST})
    public  JsonResult<Integer>  edit(MedicalRelationTransformPO po) {
        JsonResult jr = new JsonResult();
        Integer num = business.edit(po);
        if (num == 0) {
            jr.setCode(400);
            jr.setTotal((long) 0);
            jr.setMessage("无数据更新");
        } else {
            jr.setCode(200);
            jr.setTotal((long) num);
            jr.setMessage("操作成功");
        }
        return jr;
    }
}
