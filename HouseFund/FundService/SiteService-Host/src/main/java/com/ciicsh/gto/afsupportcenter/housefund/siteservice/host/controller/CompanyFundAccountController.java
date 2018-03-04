package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.GetComFundAccountListRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountPO;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
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
     * @param pageInfo
     * @return
     */
    @PostMapping("/getComFundAccountList")
    public JsonResult<List<ComFundAccountDTO>> GetComFundAccountList(PageInfo pageInfo) {
        GetComFundAccountListRequestDTO request = (GetComFundAccountListRequestDTO)pageInfo.getParams().get("requestParam");
        PageRows<ComFundAccountPO> lst = PageKit.doSelectPage(pageInfo,()->business.getComFundAccountList(request));
        List<ComFundAccountDTO> dtos = JsonKit.castToList(lst.getRows(), ComFundAccountDTO.class);

        PageRows<ComFundAccountDTO> result = new PageRows<>();
        result.setRows(dtos);
        result.setTotal(dtos.size());
        return JsonResultKit.ofPage(result);

    }



}