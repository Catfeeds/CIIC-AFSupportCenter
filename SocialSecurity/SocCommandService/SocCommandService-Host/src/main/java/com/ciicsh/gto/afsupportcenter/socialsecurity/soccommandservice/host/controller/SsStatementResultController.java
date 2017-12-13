package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsStatementResultService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementResultDTO;
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
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssStatementResult")
public class SsStatementResultController  extends BasicController<ISsStatementResultService> {

    /**
     * <p>Description: 对账单结果查询(列表页)</p>
     *
     * @author wengxk
     * @date 2017-12-12
     * @param ssStatementResultDTO 对账单结果检索条件
     * @return  JsonResult<>
     */
    @Log("对账单结果查询")
    @PostMapping("/statementResultQuery")
    public JsonResult<List<SsStatementResultDTO>> statementResultQuery(SsStatementResultDTO ssStatementResultDTO) {

        List<SsStatementResultDTO> resultList =business.statementResultQuery(ssStatementResultDTO);
        JsonResult<List<SsStatementResultDTO>> jsonResult= new JsonResult<>();
        jsonResult.setData(resultList);

        return jsonResult;
    }
}

