package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.OrgPolicyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.OrgPolicyPageDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicy;
import com.ciicsh.gto.afsupportcenter.util.page.PageUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 17:06 2018/1/16
 */
@RestController
@RequestMapping("/api/orgPolicy")
public class OrgPolicyController {

    @Autowired
    private OrgPolicyService orgPolicyService;

    @GetMapping("/find")
    public JsonResult getPage(Integer pageNum, Integer pageSize, String name, Integer type){
        Page page = new Page(PageUtil.setPageNum(pageNum), PageUtil.setPageSize(pageSize));
        OrgPolicy orgPolicy = new OrgPolicy();
        orgPolicy.setName(name);
        orgPolicy.setType(type);
        List<OrgPolicy> resultList = orgPolicyService.select(page, orgPolicy);
        resultList.stream().map(item -> {
            OrgPolicyPageDTO orgPolicyPageDTO = new OrgPolicyPageDTO();
            BeanUtils.copyProperties(item, orgPolicyPageDTO);
            return orgPolicyPageDTO;
        }).collect(Collectors.toList());
        page.setRecords(resultList);

        return JsonResult.success(page);
    }

    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdateItem(@RequestBody OrgPolicyPageDTO orgPolicyPageDTO){
        OrgPolicy orgPolicy = new OrgPolicy();
        BeanUtils.copyProperties(orgPolicyPageDTO, orgPolicy);
        if (orgPolicy.getOrgPoilcyId() == null ) {
            orgPolicy.setCreatedBy("gu");
            orgPolicy.setCreatedTime(new Date());
        }
        orgPolicy.setModifiedBy("gu");
        orgPolicy.setModifiedTime(new Date());
        return JsonResult.success(orgPolicyService.insertOrUpdate(orgPolicy));
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult deleteItem(@PathVariable("id") Integer id){
        return JsonResult.success(orgPolicyService.deleteById(id));
    }
}
