package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComMaterialService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComMaterial;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class SsComMaterialController  extends BasicController<ISsComMaterialService> {

    @Log("材料全部签收")
    @RequestMapping(value="signAllMaterials")
    public JsonResult<Boolean> signAllMaterials(@RequestParam(required = true) String materialId){
        if(StringUtils.isNotBlank(materialId)){
            //解析前台传过来的ID 字符串 为数组
            String [] materialIdArr = materialId.split(",");
            List<SsComMaterial> ssComMaterialList = new ArrayList<SsComMaterial>();
            for (int i=0;i<materialIdArr.length;i++){
                SsComMaterial ssComMaterial = new SsComMaterial();
                ssComMaterial.setComMaterialId(Long.parseLong(materialIdArr[i]));
                ssComMaterial.setStatus(1);
                ssComMaterialList.add(ssComMaterial);
            }
            boolean result = business.updateBatchById(ssComMaterialList);
            return JsonResultKit.of(result);
        }else  return JsonResultKit.of();
    }
}

