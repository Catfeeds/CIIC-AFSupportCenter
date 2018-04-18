package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsStatementService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.StatementExportArgs;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.StatementExportOpt;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 本地社保中，中智公司与社保局的对账单（各一条记录） 前端控制器
 */
@RestController
@RequestMapping("/api/soccommandservice/ssStatement")
public class SsStatementController  extends BasicController<SsStatementService> {

    /**
     * 对账单查询(列表页)
     * @param pageInfo 翻页检索条件
     * @return  JsonResult<>
     */
    @PostMapping("/statementQuery")
    public JsonResult<List<SsStatementBO>> statementQuery(PageInfo pageInfo) {

        PageRows<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementBO> pageBORows = business.statementQuery(pageInfo);
        PageRows<SsStatementBO> pageRows = new PageRows<SsStatementBO>();
        BeanUtils.copyProperties(pageBORows,pageRows);
        return JsonResultKit.ofPage(pageRows);
    }


    /**
     * 社保对账主表查询导出
     */
    @RequestMapping("/statementExport")
    public void statementExport(HttpServletResponse response,StatementExportArgs args) {
        Date date = new Date();
        String fileNme = "社保对账_"+ StringUtil.getDateString(date)+".xls";
        List<StatementExportOpt> opts = business.statementExportQuery(args);
        ExcelUtil.exportExcel(opts,StatementExportOpt.class,fileNme,response);
    }

    /**
     * 对账单查询
     * @param ssStatementDTO 检索条件
     * @return  JsonResult<SsStatementBO>
     */
    @PostMapping("/serachStatementData")
    public JsonResult<SsStatementBO> serachStatementData(SsStatementBO ssStatementDTO) {
        //入口转换格式
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementBO ssStatementBO = CommonTransform.convertToEntity(ssStatementDTO, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementBO.class);

        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementBO resultBO = business.serachStatementData(ssStatementBO);

        //出口转换格式
        SsStatementBO resultDto = new SsStatementBO();
        BeanUtils.copyProperties(resultBO,resultDto);


        JsonResult<SsStatementBO> result = new JsonResult<>();
        result.setData(resultDto);

        return result;
    }


}

