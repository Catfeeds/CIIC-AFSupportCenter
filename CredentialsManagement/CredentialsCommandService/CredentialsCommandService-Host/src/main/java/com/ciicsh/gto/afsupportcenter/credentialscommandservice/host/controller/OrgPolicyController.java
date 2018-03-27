package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.OrgPolicyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.OrgPolicyPageDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicy;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: guwei
 * @Description: 政策维护控制器
 * @Date: Created in 17:06 2018/1/16
 */
@RestController
@RequestMapping("/api/orgPolicy")
public class OrgPolicyController {

    @Autowired
    private OrgPolicyService orgPolicyService;

    /**
     * 办理机构政策维护列表页数据
     * @param pageNum
     * @param pageSize
     * @param name
     * @param type
     * @return
     */
    @GetMapping("/find")
    public JsonResult getPage(Integer pageNum, Integer pageSize, String name, Integer type){
        Page page = new Page(PageUtil.setPageNum(pageNum), PageUtil.setPageSize(pageSize));
        OrgPolicy orgPolicy = new OrgPolicy();
        orgPolicy.setName(name);
        orgPolicy.setType(type);
        List<OrgPolicy> orgPolicys = orgPolicyService.select(page, orgPolicy);
        ArrayList<OrgPolicyPageDTO> resultList = new ArrayList<>();
        orgPolicys.stream().forEach( i -> {
            OrgPolicyPageDTO orgPolicyPageDTO = new OrgPolicyPageDTO();
            BeanUtils.copyProperties(i,orgPolicyPageDTO);
            if (i.getType() != null) {
                orgPolicyPageDTO.setTypeN(SelectionUtils.credentials(i.getType()));
            }
            resultList.add(orgPolicyPageDTO);
        });
        page.setRecords(resultList);
        return JsonResult.success(page);
    }

    /**
     * 保存或更新政策信息
     * @param orgPolicyPageDTO
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdateItem(@RequestBody OrgPolicyPageDTO orgPolicyPageDTO){
        OrgPolicy orgPolicy = new OrgPolicy();
        BeanUtils.copyProperties(orgPolicyPageDTO, orgPolicy);
        //TODO
        if (orgPolicy.getOrgPoilcyId() == null ) {
            orgPolicy.setCreatedBy("gu");
            orgPolicy.setCreatedTime(new Date());
        }
        orgPolicy.setModifiedBy("gu");
        orgPolicy.setModifiedTime(new Date());
        return JsonResult.success(orgPolicyService.insertOrUpdate(orgPolicy));
    }

    /**
     * 删除政策信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public JsonResult deleteItem(@PathVariable("id") Integer id){
        int b = orgPolicyService.deleteById(id);
        if (b==0) {
            return JsonResult.success(null);
        }
        if (b ==1 ) {
            return JsonResult.errorsInfo("1","此政策正在使用中，删除失败！");
        }
        return JsonResult.faultMessage();
    }
}
