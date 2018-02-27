package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.statement.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.statement.SsMonthEmpChangeDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthEmpChangeDetailService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.GsyExportOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.YysExportOpt;

import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 雇员月度变更表明细
该表结果有可能需要调整 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssMonthEmpChangeDetail")
public class SsMonthEmpChangeDetailController  extends BasicController<SsMonthEmpChangeDetailService> {
    /**
     * <p>Description: 社保汇总明细数据展示</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeDTO 社保总汇检索条件
     * @return  JsonResult<SsMonthEmpChangeBO>
     */
    @Log("社保汇总明细数据展示")
    @PostMapping("/showMonthEmpChangeDetail")
    public JsonResult<List<SsMonthEmpChangeDetailBO>> showMonthEmpChangeDetail(SsMonthEmpChangeBO ssMonthEmpChangeDTO) {

        //转换格式
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO ssMonthEmpChangeBO = CommonTransform.convertToEntity(ssMonthEmpChangeDTO, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO.class);
        List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> resultBoList = business.showMonthEmpChangeDetailByStatementId(ssMonthEmpChangeBO);

        //转换格式
        List<SsMonthEmpChangeDetailBO> resultDtoList = CommonTransform.convertToDTOs(resultBoList,SsMonthEmpChangeDetailBO.class);
        JsonResult<List<SsMonthEmpChangeDetailBO>> result = new JsonResult<>();
        result.setData(resultDtoList);

        return result;
    }
    @Log("变更总汇明细(养保、医保、失保)导出")
    @RequestMapping("/yysExport")
    public void yysExport(HttpServletResponse response, @RequestParam Long statementId){
        Date date = new Date();
        String fileNme = "YYS_"+ StringUtil.getDateString(date)+".xls";
        List<YysExportOpt> opts = business.yysExportQuery(statementId);
        ExcelUtil.exportExcel(opts,YysExportOpt.class,fileNme,response);
    }

    @Log("变更总汇明细(养保、医保、失保)导出")
    @RequestMapping("/gsyExport")
    public void gsyExport(HttpServletResponse response,@RequestParam Long statementId){
        Date date = new Date();
        String fileNme = "GSY_"+ StringUtil.getDateString(date)+".xls";
        List<GsyExportOpt> opts = business.gsyExportQuery(statementId);
        ExcelUtil.exportExcel(opts,GsyExportOpt.class,fileNme,response);
    }
}

