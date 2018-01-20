package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.CompanyExtService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.CompanyExtDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.CompanyExt;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 9:32 2018/1/17
 */
@RestController
@RequestMapping("/api/companyExt")
public class CompanyExtController {

    @Autowired
    private CompanyExtService companyExtService;

    /**
     * 根据客户code查询证件办理信息
     * @return
     */
    @GetMapping("/find/{companyCode}")
    public JsonResult getCompanyExt(@PathVariable("companyCode")String id){
        if (StringUtils.isNotBlank(id)) {
            List<CompanyExt> companyExts = companyExtService.selectBycompanyId(id);
            CompanyExtDTO companyExtDTO = new CompanyExtDTO();
            companyExts.stream().forEach(i -> {
                if (i.getCredentialsType() != null) {
                    companyExtDTO.setLab(SelectionUtils.credentials(i.getCredentialsType()));
                }
                if (i.getOperateType() != null) {
                    companyExtDTO.setOperateTypeN(SelectionUtils.operateType(i.getOperateType()));
                }
                if (i.getChargeType() != null) {
                    companyExtDTO.setChargeTypeN(SelectionUtils.chargeType(i.getChargeType()));
                }
                if (i.getPayType() != null) {
                    companyExtDTO.setPayTypeN(SelectionUtils.payType(i.getPayType()));
                }
            });
            BeanUtils.copyProperties(companyExts,companyExtDTO);
            return JsonResult.success(companyExtDTO);
        }else {
            return JsonResult.faultMessage("请求失败");
        }
    }


    /**
     * 保存或更新客户证件办理信息
     * @param companyExtDTO
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public JsonResult saveCompanyExt(@RequestBody CompanyExtDTO companyExtDTO){
        CompanyExt companyExt = new CompanyExt();
        BeanUtils.copyProperties(companyExtDTO,companyExt);
        return JsonResult.success(companyExtService.insertOrUpdate(companyExt));
    }

}
