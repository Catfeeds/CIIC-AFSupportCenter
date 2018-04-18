package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpMaterial;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class SsEmpMaterialController extends BasicController<SsEmpMaterialService> {

    /**
     * 查询雇员特殊操作材料信息
     * @param empTaskId
     * @return
     */
    @RequestMapping("/queryEmpMaterialByTaskId")
    public JsonResult<List<SsEmpMaterial>> queryEmpMaterialByTaskId(String empTaskId) {

        List empMaterialList = business.accAndEmpDetailQuery(empTaskId);

        return JsonResultKit.of(empMaterialList);
    }

    /**
     * 材料收缴
     * @param rejectionRequestParam
     * @return
     */
    @RequestMapping("/saveMaterial")
    public JsonResult<Boolean> saveMaterial(RejectionRequestParam rejectionRequestParam) {

        if (null == rejectionRequestParam.paramsList) return JsonResultKit.ofError("参数有误");
        List ssEmpMaterialList = new ArrayList<SsEmpMaterial>();
        for (String paramStr : rejectionRequestParam.paramsList) {
            SsEmpMaterial ssEmpMaterial =  JSON.parseObject(paramStr, SsEmpMaterial.class);
            ssEmpMaterial.setModifiedTime(LocalDateTime.now());
            ssEmpMaterial.setModifiedBy(UserContext.getUserId());
            ssEmpMaterialList.add(ssEmpMaterial);

        }
        boolean result = business.updateBatchById(ssEmpMaterialList);
        return JsonResultKit.of(result);
    }

    private static class RejectionRequestParam {
        //材料信息  前端组装成字符串的List
        private List<String> paramsList;

        public List<String> getParamsList() {
            return paramsList;
        }

        public void setParamsList(List<String> paramsList) {
            this.paramsList = paramsList;
        }
    }

}

