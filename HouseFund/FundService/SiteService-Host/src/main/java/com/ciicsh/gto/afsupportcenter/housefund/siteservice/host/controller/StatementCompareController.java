package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HFStatementCompareService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HFStatementCompareDTO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;


@RestController
@RequestMapping("/api/fundcommandservice/statement")
public class StatementCompareController extends BasicController<HFStatementCompareService> {

    /**
     * 根据筛选条件获取公积金对账单
     *
     * @param pageInfo
     * @return
     */
    @PostMapping("/getStatements")
    public JsonResult<List<HFStatementCompareDTO>> getStatements(@RequestBody PageInfo pageInfo) {

        String hfMonth = pageInfo.getParams().getString("hfMonth");
        String hfComAccount = pageInfo.getParams().getString("hfComAccount");
        PageRows<HFStatementCompareBO> users = PageKit.doSelectPage(pageInfo, () -> business.getHFStatementCompareRecord(hfMonth, hfComAccount));

        List<HFStatementCompareDTO> dtos = JsonKit.castToList(users.getRows(), HFStatementCompareDTO.class);
        PageRows<HFStatementCompareDTO> result = new PageRows<>();
        result.setRows(dtos);
        result.setTotal(users.getTotal());
        return JsonResultKit.ofPage(result);


    }
}
