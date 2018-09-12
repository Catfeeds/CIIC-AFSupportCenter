package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAccountComRelationBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAccountComRelationService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAccountRatioService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 企业社保账户信息表
 * 企业社保账户分类 : 大库（中智大库、外包库）、独立户 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssComAccount")
public class SsComAccountController extends BasicController<SsComAccountService> {

    @Autowired
    private SsAccountRatioService ssAccountRatioService;
    @Autowired
    private SsAccountComRelationService iSsAccountComRelationService;

    /**
     * 根据雇员任务ID查询企业社保账户信息
     *
     * @param empTaskId
     * @return
     */
    @RequestMapping("/queryByEmpTaskId")
    public JsonResult<SsComAccountBO> queryByEmpTaskId(@RequestParam("empTaskId") String empTaskId,
                                                       @RequestParam("operatorType") String operatorType) {

        SsComAccountBO ssComAccountBO = business.queryByEmpTaskId(empTaskId, operatorType);
        return JsonResultKit.of(ssComAccountBO);
    }

    /**
     * 查询企业社保账户信息
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/accountQuery")
    public JsonResult<List<SsComAccountBO>> accountQuery(PageInfo pageInfo) {
        PageRows<SsComAccountBO> pageRows = business.accountQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    /**
     * 企业社保账户信息导出
     */
    @RequestMapping("/accountExport")
    public void accountExport(HttpServletResponse response, SsComAccountBO accountBo) {
        Date date = new Date();
        String fileNme = "企业社保账户_" + StringUtil.getDateString(date) + ".xls";
        List<SsComAccountBO> accountBos = business.getAccounts(accountBo);
        ExcelUtil.exportExcel(accountBos, SsComAccountBO.class, fileNme, response);
    }


    /**
     * 企业社保管理详情查询
     *
     * @param comAccountId
     * @return
     */
    @RequestMapping("/comSocialSecurityManageInfo")
    public JsonResult<SsComAccountBO> comSocialSecurityManageInfo(String comAccountId) {

        if (StringUtils.isBlank(comAccountId)) return JsonResultKit.ofError("id为空!");

        /**
         * 因都是一对多关系，所以只能分开查询
         */
        //查询账户和账户对应的任务单结果
        SsComAccountBO ssComAccountBO = business.querySocialSecurityManageInfo(comAccountId);
        //再查询工伤比例变更
        List<SsAccountRatio> ssAccountRatioList = ssAccountRatioService.queryRatioByAccountId(comAccountId);
        //查询 账户关联的公司
        List<SsAccountComRelationBO> ssAccountComRelationBOList = iSsAccountComRelationService.queryByAccountId(comAccountId);
        ssComAccountBO.setSsAccountRatioList(ssAccountRatioList);
        ssComAccountBO.setSsAccountComRelationBOList(ssAccountComRelationBOList);

        return JsonResultKit.of(ssComAccountBO);
    }

//    @Override
//    @RequestMapping("/getSsComAccountList")
//    @Log("获取企业社保账户信息表")
//    public com.ciicsh.common.customer.JsonResult getSsComAccountList(@RequestBody SsComAccountParamDTO paramDto) {
//        // 根据 客户ID和账户类型查询
//        List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO> ssComAccountList =
//            business.getSsComAccountList(paramDto);
//
//        return com.ciicsh.common.customer.JsonResult.success(ssComAccountList);
//    }


    /**
     * 根据企业社保账户获取公司信息
     *
     * @param ssAccount
     * @return 返回信息
     */
    @RequestMapping("/getAccountByAccountId")
    public JsonResult<SsComAccountDTO> getAccountByAccountId(@RequestParam("ssAccount") String ssAccount,
                                                             @RequestParam("companyId") String companyId) {
        SsComAccountDTO comAccount = business.getAccountById(ssAccount,companyId);

        return JsonResultKit.of(comAccount);
    }

    @PostMapping("/ssComAccountSave")
    public JsonResult ssComAccountSave(@RequestBody SsComAccount comAccount) {
        business.updateById(comAccount);
        return JsonResultKit.of();
    }

}

