package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.GetComFundAccountListRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountPO;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fundcommandservice/companyFundAccount")
public class CompanyFundAccountController extends BasicController<HfComAccountService> {

    /**
     * 根据查询条件获取企业公积金账户信息
     *
     * @param request
     * @return
     */
    @PostMapping("/getComFundAccountList")
    public JsonResult<List<ComFundAccountDTO>> GetComFundAccountList(@RequestBody GetComFundAccountListRequestDTO request) {
        List<ComFundAccountPO> lst = business.getComFundAccountList(request);
        List<ComFundAccountDTO> result = new ArrayList<>();
        for (ComFundAccountPO po : lst) {
            ComFundAccountDTO dto = new ComFundAccountDTO();
            BeanUtils.copyProperties(po, dto);
            result.add(dto);
        }
        return JsonResultKit.ofList(result);
    }


    @GetMapping("/get")
    public JsonResult<String> GetComFundAccountList(@RequestParam("param1") String param1) {

        return JsonResultKit.of(param1);

    }
}