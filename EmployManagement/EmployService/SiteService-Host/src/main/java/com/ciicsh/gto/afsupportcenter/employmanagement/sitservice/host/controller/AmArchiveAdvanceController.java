package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveAdvanceBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveAdvanceService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.advanceSearchExportOpt;
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
 * Created by liyuelong on 2018/5/25.
 */

@RestController
@RequestMapping("/api/employservice/amArchiveAdvance")
public class AmArchiveAdvanceController extends BasicController<IAmArchiveAdvanceService> {


    @RequestMapping("/queryAmArchiveAdvanceList")
    public JsonResult<PageRows> queryAmArchive(PageInfo pageInfo){
        PageRows<AmArchiveAdvanceBO> result = business.queryAmArchiveAdvanceList(pageInfo);

        return JsonResultKit.of(result);
    }

    @RequestMapping("/deleteAmArchiveAdvance")
    public JsonResult<Boolean> deleteAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO){

        Boolean result = business.deleteAmArchiveAdvance(amArchiveAdvanceBO);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/saveAmArchiveAdvance")
    public JsonResult<Object> saveAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO){
        // 是否已有姓名加身份证的雇员信息在预增表中
        if(amArchiveAdvanceBO.isExist()){
            AmArchiveAdvanceBO isHave = business.queryAmArchiveAdvanceByNameIdcard(amArchiveAdvanceBO.getEmployeeName(),amArchiveAdvanceBO.getEmployeeIdcardNo(),null);
            if(isHave!=null){
                return JsonResultKit.of(false);
            }
        }
        // 是否已有档案
        AmEmploymentBO bo = business.queryAmArchiveByEmployeeNameIdCard(amArchiveAdvanceBO.getEmployeeName(),amArchiveAdvanceBO.getEmployeeIdcardNo());
        if(bo != null){
            return JsonResultKit.of(bo);
        }
        Boolean result = business.saveAmArchiveAdvance(amArchiveAdvanceBO);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/advanceSearchExportOpt")
    public void advanceSearchExportOpt(HttpServletResponse response, AmArchiveAdvanceBO amArchiveAdvanceBO) {

        Date date = new Date();
        String fileNme = "档案预增列表单_"+ StringUtil.getDateString(date)+".xls";

        List<advanceSearchExportOpt> opts = business.queryAdvanceSearchExportOpt(amArchiveAdvanceBO);

        ExcelUtil.exportExcel(opts,advanceSearchExportOpt.class,fileNme,response);
    }
}
