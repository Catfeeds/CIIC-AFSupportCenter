package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyRenewBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveUkeyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.ukeySearchExportOpt;
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
 * Created by liyuelong on 2018/6/14.
 */

@RestController
@RequestMapping("/api/employservice/amArchiveUkey")
public class AmArchiveUkeyController extends BasicController<IAmArchiveUkeyService> {


    @RequestMapping("/queryAmArchiveUkeyList")
    public JsonResult<PageRows> queryAmArchiveUkeyList(PageInfo pageInfo){
        PageRows<AmArchiveUkeyBO> result = business.queryAmArchiveUkeyList(pageInfo);

        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryAmArchiveUkey")
    public JsonResult<AmArchiveUkeyBO> queryAmArchiveUkey(Long id){
        AmArchiveUkeyBO result = business.queryAmArchiveUkey(id);

        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryAmArchiveUkeyRenew")
    public JsonResult<List<AmArchiveUkeyRenewBO>> queryAmArchiveUkeyRenew(Long id){
        List<AmArchiveUkeyRenewBO> result = business.queryAmArchiveUkeyRenew(id);

        return JsonResultKit.of(result);
    }

    @RequestMapping("/deleteAmArchiveUkey")
    public JsonResult<Boolean> deleteAmArchiveUkey(Long id){

        Boolean result = business.deleteAmArchiveUkey(id);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/saveAmArchiveUkey")
    public JsonResult<Integer> saveAmArchiveUkey(AmArchiveUkeyBO amArchiveUkeyBO){
        if(amArchiveUkeyBO.isFlag()){
            AmArchiveUkeyBO bo = business.queryAmArchiveUkey(amArchiveUkeyBO.getOrganizationCode());
            if(bo!=null){
                return JsonResultKit.of(0);
            }
        }
        business.saveAmArchiveUkey(amArchiveUkeyBO);
        return JsonResultKit.of(1);
    }

    @RequestMapping("/amArchiveUkeyRenew")
    public JsonResult<Boolean> amArchiveUkeyRenew(AmArchiveUkeyBO amArchiveUkeyBO){
        Boolean result = business.amArchiveUkeyRenew(amArchiveUkeyBO);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/uekySearchExportOpt")
    public void uekySearchExportOpt(HttpServletResponse response, AmArchiveUkeyBO amArchiveUkeyBO) {

        Date date = new Date();
        String fileNme = "Ukey列表_"+ StringUtil.getDateString(date)+".xls";

        List<ukeySearchExportOpt> opts = business.queryAdvanceSearchExportOpt(amArchiveUkeyBO);

        ExcelUtil.exportExcel(opts,ukeySearchExportOpt.class,fileNme,response);
    }
}
