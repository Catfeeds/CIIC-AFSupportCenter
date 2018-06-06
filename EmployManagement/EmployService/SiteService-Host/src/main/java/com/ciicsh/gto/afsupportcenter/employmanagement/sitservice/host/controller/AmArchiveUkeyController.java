package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveAdvanceBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveAdvanceService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveUkeyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.advanceSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUkey;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by liyuelong on 2018/5/31.
 */

@RestController
@RequestMapping("/api/employservice/amArchiveUkey")
public class AmArchiveUkeyController extends BasicController<IAmArchiveUkeyService> {


    @RequestMapping("/queryAmArchiveUkeyList")
    public JsonResult<PageRows> queryAmArchiveUkeyList(PageInfo pageInfo){
        PageRows<AmArchiveUkeyBO> result = business.queryAmArchiveUkeyList(pageInfo);

        return JsonResultKit.of(result);
    }

    @RequestMapping("/deleteAmArchiveUkey")
    public JsonResult<Boolean> deleteAmArchiveUkey(Long id){

        Boolean result = business.deleteAmArchiveUkey(id);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/saveAmArchiveUkey")
    public JsonResult<Boolean> saveAmArchiveUkey(AmArchiveUkeyBO amArchiveUkeyBO){
        Boolean result = business.saveAmArchiveUkey(amArchiveUkeyBO);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/amArchiveUkeyRenew")
    public JsonResult<Boolean> amArchiveUkeyRenew(Long keyId,String keyFee,String keyRenewFee){
        Boolean result = business.amArchiveUkeyRenew(keyId, keyFee, keyRenewFee);
        return JsonResultKit.of(result);
    }
}
