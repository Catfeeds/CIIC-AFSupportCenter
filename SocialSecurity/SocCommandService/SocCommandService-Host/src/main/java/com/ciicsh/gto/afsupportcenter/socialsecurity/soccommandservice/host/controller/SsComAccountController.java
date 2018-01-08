package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAccountComRelationService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAccountRatioService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsAccountComRelationBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class SsComAccountController extends BasicController<ISsComAccountService> {

    @Autowired
    private ISsAccountRatioService iSsAccountRatioService;
    @Autowired
    private ISsAccountComRelationService iSsAccountComRelationService;
    /**
     * 根据雇员任务 ID 查询 企业社保账户信息
     *
     * @param empTaskId
     * @return
     */
    @RequestMapping("/queryByEmpTaskId")
    @Log("根据雇员任务 ID 查询")
    public JsonResult<SsComAccountBO> queryByEmpTaskId(@RequestParam("empTaskId") String empTaskId,
                                                       @RequestParam("operatorType") String operatorType) {

        SsComAccountBO ssComAccountBO = business.queryByEmpTaskId(empTaskId,operatorType);
        return JsonResultKit.of(ssComAccountBO);
    }

    /**
     * 查询企业社保账户信息
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/accountQuery")
    @Log("查询")
    public JsonResult<List<SsComAccountBO>> accountQuery(PageInfo pageInfo) {
        PageRows<SsComAccountBO> pageRows = business.accountQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }
    @Log("企业社保管理详情查询")
    @RequestMapping("/comSocialSecurityManageInfo")
    public JsonResult<SsComAccountBO>  comSocialSecurityManageInfo(String comAccountId){

        if(StringUtils.isBlank(comAccountId))return JsonResultKit.ofError("id为空!");

        /**
         * 因都是一对多关系，所以只能分开查询
         */
        //查询账户和账户对应的任务单结果
        SsComAccountBO ssComAccountBO = business.querySocialSecurityManageInfo(comAccountId);
        //再查询工伤比例变更
        List<SsAccountRatio> ssAccountRatioList = iSsAccountRatioService.queryRatioByAccountId(comAccountId);
        //查询 账户关联的公司
        List<SsAccountComRelationBO> ssAccountComRelationBOList =  iSsAccountComRelationService.queryByAccountId(comAccountId);
        ssComAccountBO.setSsAccountRatioList(ssAccountRatioList);
        ssComAccountBO.setSsAccountComRelationBOList(ssAccountComRelationBOList);

        return JsonResultKit.of(ssComAccountBO);
    }
}

