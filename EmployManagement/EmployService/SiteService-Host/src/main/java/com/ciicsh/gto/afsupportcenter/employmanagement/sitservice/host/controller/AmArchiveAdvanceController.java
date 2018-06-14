package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.advanceSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.archiveSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.resignSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmArchiveDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    public JsonResult<Boolean> saveAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO){
        if(amArchiveAdvanceBO.isExist()){
            AmArchiveAdvanceBO isHave = business.queryAmArchiveAdvanceByNameIdcard(amArchiveAdvanceBO.getEmployeeName(),amArchiveAdvanceBO.getEmployeeIdcardNo());
            if(isHave!=null){
                return JsonResultKit.of(false);
            }
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
