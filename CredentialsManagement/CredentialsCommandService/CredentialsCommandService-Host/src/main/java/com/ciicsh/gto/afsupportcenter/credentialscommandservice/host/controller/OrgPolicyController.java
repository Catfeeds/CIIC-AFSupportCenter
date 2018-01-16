package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.OrgPolicyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.OrgPolicyPageDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.OrgPolicyQueryDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicyPO;
import com.ciicsh.gto.afsupportcenter.util.page.PageUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 17:06 2018/1/16
 */
@RestController
@RequestMapping("/api/")
public class OrgPolicyController {

    @Autowired
    private OrgPolicyService orgPolicyService;

    @GetMapping("/find")
    public JsonResult getPage(Integer pageNum, Integer pageSize, OrgPolicyQueryDTO orgPolicyQueryDTO){
        Page page = new Page(PageUtil.setPageNum(pageNum), PageUtil.setPageSize(pageSize));
        OrgPolicyPO orgPolicyPO = new OrgPolicyPO();
        if (orgPolicyQueryDTO != null) {
            orgPolicyPO.setName(orgPolicyQueryDTO.getName());
            orgPolicyPO.setType(orgPolicyQueryDTO.getType());
        }
        List<OrgPolicyPO> resultList = orgPolicyService.select(orgPolicyPO);
        resultList.stream().map(item -> {
            OrgPolicyPageDTO orgPolicyPageDTO = new OrgPolicyPageDTO();
            BeanUtils.copyProperties(item, orgPolicyPageDTO);
            return orgPolicyPageDTO;
        }).collect(Collectors.toList());
        page.setRecords(resultList);
        return JsonResult.success(page);
    }

    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdateItem(OrgPolicyPageDTO orgPolicyPageDTO){
        OrgPolicyPO orgPolicyPO = new OrgPolicyPO();
        BeanUtils.copyProperties(orgPolicyPageDTO, orgPolicyPO);
        return JsonResult.success(orgPolicyService.insertOrUpdate(orgPolicyPO));
    }

    @DeleteMapping("/delete")
    public JsonResult deleteItem(String id){
        return JsonResult.success(orgPolicyService.deleteById(id));
    }
}
