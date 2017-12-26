package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsStatementBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsStatementService;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录） 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssStatement")
public class SsStatementController  extends BasicController<ISsStatementService> {

    /**
     * <p>Description: 对账单查询(列表页)</p>
     *
     * @author wengxk
     * @date 2017-12-08
     * @param pageInfo 翻页检索条件
     * @return  JsonResult<>
     */
    @Log("对账单查询")
    @PostMapping("/statementQuery")
    public JsonResult<List<SsStatementBO>> statementQuery(PageInfo pageInfo) {

       /* PageRows<SsStatement> pagePORows = business.statementQuery(pageInfo);
        PageRows<SsStatementBO> pageRows = new PageRows<SsStatementBO>();
        BeanUtils.copyProperties(pagePORows,pageRows);*/
        PageRows<SsStatementBO> pageRows =business.statementQuery(pageInfo);


        return JsonResultKit.ofPage(pageRows);
    }


    /**
     * <p>Description: 社保对账主表查询导出</p>
     *
     * @author wengxk
     * @date 2017-12-11
     * @param ssStatementBO 下载条件
     * @return
     */
    @Log("社保对账主表查询导出")
    @ResponseBody
    @RequestMapping("/exportStatementQuery")
    public void exportStatementQuery(SsStatementBO ssStatementBO) {

    }

    /**
     * <p>Description: 对账单查询</p>
     *
     * @author wengxk
     * @date 2017-12-08
     * @param ssStatementBO 检索条件
     * @return  JsonResult<SsStatementBO>
     */
    @Log("对账单查询")
    @PostMapping("/serachStatementData")
    public JsonResult<SsStatementBO> serachStatementData(SsStatementBO ssStatementBO) {

        SsStatementBO resultDto = business.serachStatementData(ssStatementBO);
        JsonResult<SsStatementBO> result = new JsonResult<>();
        result.setData(resultDto);

        return result;
    }


}

