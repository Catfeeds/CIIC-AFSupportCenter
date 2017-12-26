package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;



import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsStatementResultService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsStatementResultBO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 对账差异结果表 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@RestController
@RequestMapping("/api/soccommandservice/ssStatementResult")
public class SsStatementResultController extends BasicController<ISsStatementResultService> {

    /**
     * <p>Description: 对账单结果查询(列表页)</p>
     *
     * @author wengxk
     * @date 2017-12-12
     * @param ssStatementResultBO 对账单结果检索条件
     * @return  JsonResult<>
     */
    @Log("对账单结果查询")
    @PostMapping("/statementResultQuery")
    public JsonResult<List<SsStatementResultBO>> statementResultQuery(SsStatementResultBO ssStatementResultBO) {

        List<SsStatementResultBO> resultList =business.statementResultQuery(ssStatementResultBO);
        JsonResult<List<SsStatementResultBO>> jsonResult= new JsonResult<>();
        jsonResult.setData(resultList);

        return jsonResult;
    }

    @Log("重算对账结果")
    @PostMapping("/calculateSstatementResult")
    public JsonResult<String> calculateSstatementResult(Long statementId) {
        JsonResult<String> json = new JsonResult<>();
        json.setData("调用成功");
        business.calculateSstatementResult(statementId);
        return json;
    }
}

