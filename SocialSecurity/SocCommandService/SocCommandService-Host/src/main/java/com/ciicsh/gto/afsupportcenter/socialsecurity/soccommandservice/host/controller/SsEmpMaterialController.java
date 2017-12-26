package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpMaterial;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 雇员材料收缴 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpMaterial")
public class SsEmpMaterialController extends BasicController<ISsEmpMaterialService> {

    @Log("查询雇员特殊操作材料信息")
    @RequestMapping("/queryEmpMaterialByTaskId")
    public JsonResult<List<SsEmpMaterial>> queryEmpMaterialByTaskId(String empTaskId) {

        List empMaterialList = business.accAndEmpDetailQuery(empTaskId);

        return JsonResultKit.of(empMaterialList);
    }
}

