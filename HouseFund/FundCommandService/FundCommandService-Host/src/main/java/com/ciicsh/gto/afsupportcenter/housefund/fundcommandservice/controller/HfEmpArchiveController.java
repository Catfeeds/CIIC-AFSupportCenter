package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.controller;


import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dto.HfEmpArchiveDto;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 前端控制器
 * </p>
 */
@Controller
@RequestMapping("/api/fundcommandservice/hfEmpArchive")
public class HfEmpArchiveController extends  BasicController<IHfEmpArchiveService>{

    /**
     * 雇员公积金查询
     *
     * @param
     * @return
     */
    @RequestMapping("/queryEmpArchive")
    @ResponseBody
    public JsonResult<PageRows> queryEmpArchive(PageInfo pageInfo) {
        PageRows<HfEmpArchiveDto> result = business.queryEmpArchive(pageInfo);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryEmpArchive1")
    public int queryEmpArchive1(PageInfo pageInfo) {
          return 1;
    }

}

