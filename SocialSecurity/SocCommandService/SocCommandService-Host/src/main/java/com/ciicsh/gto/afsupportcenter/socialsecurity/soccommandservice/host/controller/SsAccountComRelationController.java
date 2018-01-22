package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsAccountComRelationDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAccountComRelationService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountComRelation;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户 前端控制器
 * </p>
 *
 * @author zhangxj
 * @since 2017-12-11
 */
@RestController
@RequestMapping("/api/soccommandservice/ssAccountComRelation")
public class SsAccountComRelationController extends BasicController<ISsAccountComRelationService> {

    @Autowired
    private ISsAccountComRelationService iSsAccountComRelationService;

    /**
     * <p>Description: 企业社保账户绑定接口</p>
     *
     * @param dto 保存的数据
     * @return JsonResult<>
     * @author zhangxj
     * @date 2017-12-23
     */
    @Log("企业社保账户绑定接口")
    @PostMapping("/saveAccountComRelation")
    public JsonResult<String> saveAccountComRelation(SsAccountComRelationDTO dto) {

        JsonResult<String> json = new JsonResult<String>();
        json.setCode(200);
        json.setMessage("成功");

        Map cond = new HashMap<>();
        cond.put("companyId", dto.getCompanyId());
        cond.put("comAccountId", dto.getComAccountId());
        //通过指定条件查询
        List<SsAccountComRelation> resList = business.queryByCond(cond);

        if ("1".equals(dto.getSaveflag())) {
            //查询到该账户
            if (resList.size() > 0) {
                SsAccountComRelation resEle = resList.get(0);
                if (resEle.getActive()) {
                    json.setCode(1);
                    json.setMessage("该账户已绑定，无需重复操作！");
                    return json;
                } else {
                    //已解绑再绑定
                    resEle.setActive(true);
                    resEle.setModifiedBy("system");
                    resEle.setModifiedTime(LocalDateTime.now());
                    //保存
                    business.updateById(resEle);
                }
            } else {
                //未查询到该账户直接插入数据绑定
                SsAccountComRelation ele = new SsAccountComRelation();
                ele.setCompanyId(dto.getCompanyId());
                ele.setComAccountId(dto.getComAccountId());
                ele.setMajorCom(0);
                ele.setActive(true);
                ele.setCreatedBy("system");
                ele.setCreatedTime(LocalDateTime.now());
                ele.setModifiedBy("system");
                ele.setModifiedTime(LocalDateTime.now());
                //保存
                business.insert(ele);
            }
        } else if ("2".equals(dto.getSaveflag())) {
            //解绑
            if (resList.size() > 0) {
                SsAccountComRelation resEle = resList.get(0);
                if (!resEle.getActive()) {
                    json.setCode(4);
                    json.setMessage("该账户已解绑，无需重复操作！");
                    return json;
                } else {
                    resEle.setActive(false);
                    resEle.setModifiedBy("system");
                    resEle.setModifiedTime(LocalDateTime.now());
                    //保存
                    business.updateById(resEle);
                }
            } else {
                json.setCode(2);
                json.setMessage("未查询到该绑定账户！");
                return json;
            }
        } else {
            json.setCode(3);
            json.setMessage("请指定操作类型，绑定还是解绑！");
            return json;
        }
        return json;
    }
}

