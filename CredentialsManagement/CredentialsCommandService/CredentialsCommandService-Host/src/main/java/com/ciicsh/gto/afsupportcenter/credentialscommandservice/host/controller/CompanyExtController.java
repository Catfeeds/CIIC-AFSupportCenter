package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.CompanyExtService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.CompanyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.OrgPolicyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.CompanyDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.CompanyExtDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Company;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.CompanyExt;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicy;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.basicdataservice.api.DicItemServiceProxy;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
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
 * @Description: 客户办证信息维护控制器
 * @Date: Created in 9:32 2018/1/17
 */
@RestController
@RequestMapping("/api/companyExt")
public class CompanyExtController {

    @Autowired
    private CompanyExtService companyExtService;

    @Autowired
    private OrgPolicyService orgPolicyService;

    @Autowired
    private CompanyService companyService;

    private static final int SPECIAL_CHARGE = 3;

    /**
     * 获取客户列表
     * @param pageNum
     * @param pageSize
     * @param companyName
     * @param companyId
     * @return
     */
    @GetMapping("/get")
    public JsonResult getCompanyPage(Integer pageNum, Integer pageSize, String companyName, String companyId){
        Page page = new Page(PageUtil.setPageNum(pageNum), PageUtil.setPageSize(pageSize));
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyName(companyName);
        List<Company> list = companyService.select(page, company);
        ArrayList<CompanyDTO> resultList = new ArrayList<CompanyDTO>();
        list.stream().forEach(i -> {
            CompanyDTO companyDTO = new CompanyDTO();
            BeanUtils.copyProperties(i,companyDTO);
            resultList.add(companyDTO);
        });
        page.setRecords(resultList);
        return JsonResult.success(page);
    }

    /**
     * 根据客户code查询证件办理信息
     * @return
     */
    @GetMapping("/find/{companyCode}")
    public JsonResult getCompanyExt(@PathVariable("companyCode")String id){
        Assert.notNull(id,"客户编号不能为空");

        List<CompanyExt> companyExts = companyExtService.selectBycompanyId(id);
        List<CompanyExtDTO> companyExtDTOS = new ArrayList<>();
        companyExts.stream().forEach(i -> {
            CompanyExtDTO companyExtDTO = new CompanyExtDTO();
            if (i.getCredentialsType() != null) {
                companyExtDTO.setLab(SelectionUtils.credentials(i.getCredentialsType()));
            }
            if (i.getOperateType() != null) {
                companyExtDTO.setOperateTypeN(SelectionUtils.operateType(i.getOperateType()));
                companyExtDTO.setOperateType(String.valueOf(i.getOperateType()));
            }
            if (i.getChargeType() != null) {
                companyExtDTO.setChargeTypeN(SelectionUtils.chargeType(i.getChargeType()));
                companyExtDTO.setChargeType(String.valueOf(i.getChargeType()));
            }
            if (i.getPayType() != null) {
                companyExtDTO.setPayTypeN(SelectionUtils.payType(i.getPayType()));
                companyExtDTO.setPayType(String.valueOf(i.getPayType()));
            }
            BeanUtils.copyProperties(i,companyExtDTO);
            OrgPolicy orgPolicy = orgPolicyService.selectById(companyExtDTO.getOrgPoilcyId());
            companyExtDTO.setName(orgPolicy == null ? "" : orgPolicy.getName());
            companyExtDTOS.add(companyExtDTO);
        });
        return JsonResult.success(companyExtDTOS);
    }

    /**
     * 保存或更新客户证件办理信息
     * @param companyExtDTO
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdateCompanyExt(@RequestBody CompanyExtDTO companyExtDTO){
        CompanyExt companyExt = new CompanyExt();
        BeanUtils.copyProperties(companyExtDTO,companyExt);
        if (StringUtils.isNotBlank(companyExtDTO.getOperateType())) {
            companyExt.setOperateType(Integer.parseInt(companyExtDTO.getOperateType()));
        }
        if (StringUtils.isNotBlank(companyExtDTO.getChargeType())) {
            companyExt.setChargeType(Integer.parseInt(companyExtDTO.getChargeType()));
            if (companyExt.getChargeType() != SPECIAL_CHARGE){
                companyExt.setSpecialChargeRemark("");
            }
        }
        if (StringUtils.isNotBlank(companyExtDTO.getPayType())) {
            companyExt.setPayType(Integer.parseInt(companyExtDTO.getPayType()));
        }
        if (companyExt.getCompanyExtId() == null) {
            companyExt.setCreatedBy(UserContext.getUser().getDisplayName());
        }
        companyExt.setModifiedBy(UserContext.getUser().getDisplayName());
        companyExt.setModifiedTime(new Date());
        return JsonResult.success(companyExtService.insertOrUpdate(companyExt));
    }

}
