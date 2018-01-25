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

import java.util.ArrayList;
import java.util.Date;
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

    private static final int SPECIAL_CHARGE = 3;

    /**
     * 根据客户code查询证件办理信息
     * @return
     */
    @GetMapping("/find/{companyCode}")
    public JsonResult getCompanyExt(@PathVariable("companyCode")String id){
        if (StringUtils.isNotBlank(id)) {
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
                companyExtDTOS.add(companyExtDTO);
            });
            return JsonResult.success(companyExtDTOS);
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
        //TODO 创建人...
        if (companyExt.getCompanyExtId() == null) {
            companyExt.setCreatedBy("gu");
        }
        companyExt.setModifiedBy("gu");
        companyExt.setModifiedTime(new Date());
        return JsonResult.success(companyExtService.insertOrUpdate(companyExt));
    }

}
