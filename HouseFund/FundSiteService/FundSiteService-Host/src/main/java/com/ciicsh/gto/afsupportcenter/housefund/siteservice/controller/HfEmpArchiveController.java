package com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller;


import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 前端控制器
 * </p>
 */
@Controller
@RequestMapping("/api/fundcommandservice/hfEmpArchive")
public class HfEmpArchiveController extends  BasicController<HfEmpArchiveService>{

    /**
     * 雇员公积金查询
     *
     * @param
     * @return
     */
    @RequestMapping("/queryEmpArchive")
    @ResponseBody
    public JsonResult<PageRows> queryEmpArchive(PageInfo pageInfo) {
        PageRows<HfEmpArchiveBo> result = business.queryEmpArchive(pageInfo);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/viewEmpArchiveInfo")
    @ResponseBody
    public JsonResult viewEmpArchiveInfo(@RequestParam("empArchiveId") String empArchiveId, @RequestParam("companyId") String companyId) {
       if(null==empArchiveId)return JsonResultKit.ofError("ID不能为空");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap= business.viewEmpArchiveInfo(empArchiveId,companyId);
        return JsonResultKit.of(resultMap);

    }

}

