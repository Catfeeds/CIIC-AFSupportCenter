package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountCompanyDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountDetailDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountClassNameDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountNameDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.GetComFundAccountListRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountCompanyPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountDetailPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountClassNamePO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountNamePO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountPO;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
    public JsonResult<List<ComFundAccountDTO>> getComFundAccountList(@RequestBody PageInfo pageInfo) {
        JSONObject params = pageInfo.getParams();
        GetComFundAccountListRequestDTO request = new GetComFundAccountListRequestDTO();
        /**
         * "companyId": "KH0000002",
         "companyName": "",
         "hfType": 0,
         "comHfMonth": "",
         "accountNumber": ""
         */
        request.setCompanyId(params.getString("companyId"));
        request.setCompanyName(params.getString("companyName"));
        request.setHfType(params.getByte("hfType"));
        request.setComHfMonth(params.getString("comHfMonth"));
        request.setAccountNumber(params.getString("accountNumber"));
        request.setLeaderShipName(params.getString("leaderShipName"));
        request.setPayBankValue(params.getString("payBankValue"));
        request.setServiceCenterValue(params.getString("serviceCenterValue"));
        PageRows<ComFundAccountPO> lst = PageKit.doSelectPage(pageInfo,()->business.getComFundAccountList(request));
        List<ComFundAccountDTO> dtos = JsonKit.castToList(lst.getRows(), ComFundAccountDTO.class);

        PageRows<ComFundAccountDTO> result = new PageRows<>();
        result.setRows(dtos);
        result.setTotal(lst.getTotal());
        return JsonResultKit.ofPage(result);

    }

    @RequestMapping("/getComFundAccountListExp")
    public void getComFundAccountList(HttpServletResponse response, PageInfo pageInfo) {
        Date date = new Date();
        String fileNme = "企业账户信息_" + StringUtil.getDateString(date) + ".xls";
        GetComFundAccountListRequestDTO hfComAccountBo = pageInfo.toJavaObject(GetComFundAccountListRequestDTO.class);
        List<ComFundAccountPO>  hfComAccountBos = business.getComFundAccountList(hfComAccountBo);
        ExcelUtil.exportExcel(hfComAccountBos, ComFundAccountPO.class, fileNme, response);
    }
    /**
     * 根据筛选条件检索企业公积金账户名称信息
     * @param pageInfo
     * @return
     */
    @PostMapping("/getComFundAccountClassNameList")
    public JsonResult<List<ComFundAccountClassNameDTO>> getComFundAccountClassNameList(@RequestBody PageInfo pageInfo){
        //企业公积金账户名称
        String comAccountName = pageInfo.getParams().getString("comAccountName").trim();
        //企业公积金账号
        String hfComAccount = pageInfo.getParams().getString("hfComAccount").trim();
        PageRows<ComFundAccountClassNamePO> lst = PageKit.doSelectPage(pageInfo,()->business.getComFundAccountClassNameList(comAccountName,hfComAccount));
        List<ComFundAccountClassNameDTO> dtos = JsonKit.castToList(lst.getRows(), ComFundAccountClassNameDTO.class);

        PageRows<ComFundAccountClassNameDTO> result = new PageRows<>();
        result.setRows(dtos);
        result.setTotal(lst.getTotal());
        return JsonResultKit.ofPage(result);
    }

    /**
     * 根据筛选条件取出有效的对账单记录，新建对账用
     * @param pageInfo
     * @return
     */
    @PostMapping("/getComFundAccountNameList")
    public JsonResult<List<ComFundAccountNameDTO>> getComFundAccountNameList(@RequestBody PageInfo pageInfo) {
        //企业公积金账户名称
        String comAccountName = pageInfo.getParams().getString("comAccountName").trim();
        //企业账户类型 1 大库 2 外包 3 独立户
        Byte hfAccountType = pageInfo.getParams().getByteValue("hfAccountType");
        // 公积金类型 1 基本公积金、2 补充公积金
        Byte hfType = pageInfo.getParams().getByteValue("hfType");
        PageRows<ComFundAccountNamePO> lst = PageKit.doSelectPage(pageInfo,()->business.getComFundAccountNameList(comAccountName,hfType, hfAccountType));
        List<ComFundAccountNameDTO> dtos = JsonKit.castToList(lst.getRows(), ComFundAccountNameDTO.class);

        PageRows<ComFundAccountNameDTO> result = new PageRows<>();
        result.setRows(dtos);
        result.setTotal(lst.getTotal());
        return JsonResultKit.ofPage(result);
    }


        /**
         * 获取企业公积金账户明细
         * @param comAccountId
         * @param hfType
         * @return
         */
    @GetMapping("/getConFundAccountDetail/{comAccountId}/{hfType}")
    public JsonResult<ComFundAccountDetailDTO> getComFundAccountDetail(@PathVariable("comAccountId") int comAccountId,@PathVariable ("hfType") Byte hfType){
        ComFundAccountDetailPO po = business.getComFundAccountDetail(comAccountId,hfType);
        ComFundAccountDetailDTO dto = new ComFundAccountDetailDTO();
        BeanUtils.copyProperties(po,dto);
        return JsonResultKit.of(dto);
    }


    /**
     * 获取企业公积金账户绑定的客户列表
     * @param comAccountId
     * @return
     */
    @GetMapping("/getComFundAccountCompanyList/{comAccountId}")
    public JsonResult<List<ComFundAccountCompanyDTO>> getComFundAccountCompanyList(@PathVariable("comAccountId") int comAccountId){
        List<ComFundAccountCompanyPO> lst = business.getComFundAccountCompanyList(comAccountId);
        List<ComFundAccountCompanyDTO> result = JsonKit.castToList(lst,ComFundAccountCompanyDTO.class);
        return JsonResultKit.ofList(result);
    }

    /**
     * 提交编辑表单信息
     * @param comFundAccountDetailDTO
     * @return
     */
    @PostMapping("/submitCompanyFundAccount")
    public JsonResult submitCompanyFundAccount(@RequestBody ComFundAccountDetailDTO comFundAccountDetailDTO) {
        return business.submitCompanyFundAccount(comFundAccountDetailDTO);
    }

    @PostMapping("/checkTransferUnitIsOnly")
    public JsonResult<Integer> checkTransferUnitIsOnly(@RequestBody PageInfo pageInfo) {
        //企业公积金账户名称
        String transferOutUnit = pageInfo.getParams().getString("transferOutUnit").trim();
        String transferInUnit = pageInfo.getParams().getString("transferInUnit").trim();
        //企业公积金账号
        String hfComAccount = "";
        PageRows<ComFundAccountClassNamePO> transferOutUnitList = PageKit.doSelectPage(pageInfo,()->business.getComFundAccountClassNameList(transferOutUnit,hfComAccount));
        List<ComFundAccountClassNameDTO> transferOutUnitDTOList = JsonKit.castToList(transferOutUnitList.getRows(), ComFundAccountClassNameDTO.class);
        PageRows<ComFundAccountClassNamePO> transferInUnitList = PageKit.doSelectPage(pageInfo,()->business.getComFundAccountClassNameList(transferInUnit,hfComAccount));
        List<ComFundAccountClassNameDTO> transferInUnitDTOList = JsonKit.castToList(transferInUnitList.getRows(), ComFundAccountClassNameDTO.class);
        Integer rtn = 0;


        if (CollectionUtils.isEmpty(transferOutUnitDTOList)) {
            rtn = 1;
        } else if (transferOutUnitDTOList.size() > 1) {
            rtn = 4;
        } else if (SocialSecurityConst.FUND_OUT_UNIT_LIST.get(1).equals(transferOutUnitDTOList.get(0).getComAccountName())) {
            rtn = 16;
        }

        if (CollectionUtils.isEmpty(transferInUnitDTOList)) {
            rtn += 2;
        } else if (transferInUnitDTOList.size() > 1) {
            rtn += 8;
        } else if (SocialSecurityConst.FUND_OUT_UNIT_LIST.get(1).equals(transferInUnitDTOList.get(0).getComAccountName())) {
            rtn += 32;
        }

        if (rtn == 0
            && transferOutUnitDTOList.get(0).getComAccountName().equals(transferInUnitDTOList.get(0).getComAccountName())
            ) {
            rtn = 5;
        }

        return JsonResultKit.of(rtn);
    }
}