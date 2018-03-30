package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComMaterialService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComMaterial;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
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
 * 企业材料收缴 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssComMaterial")
public class SsComMaterialController  extends BasicController<SsComMaterialService> {

    @Log("材料全部签收")
    @RequestMapping(value="signMaterials")
    public JsonResult<Boolean> signMaterials(RejectionRequestParam params){

            if(null!=params.paramsList){
                List<SsComMaterial> ssComMaterialList = new ArrayList<SsComMaterial>();
                for (String paramStr:params.paramsList) {
                    SsComMaterial ssComMaterial = JsonKit.parseObject(paramStr, SsComMaterial.class);
                    ssComMaterial.setModifiedBy(UserContext.getUserId());
                    ssComMaterial.setModifiedTime(LocalDateTime.now());
                    ssComMaterialList.add(ssComMaterial);
                }
                boolean result = business.updateBatchById(ssComMaterialList);
                return JsonResultKit.of(result);
            }else return JsonResultKit.ofError("参数错误");

    }

    private static class RejectionRequestParam{
        private List<String> paramsList;

        public List<String> getParamsList() {
            return paramsList;
        }

        public void setParamsList(List<String> paramsList) {
            this.paramsList = paramsList;
        }
    }

}

