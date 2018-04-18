package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.system.AfUserPermissionDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.system.AfUserPermissionRequestDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfUserCompanyRefProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.ISalCompanyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.SalCompanyMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.SalCompany;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMDepartmentProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/20.
 */
@Service
public class SalCompanyServiceImpl extends ServiceImpl<SalCompanyMapper, SalCompany> implements ISalCompanyService {


    @Autowired
    private AfUserCompanyRefProxy afUserCompanyRefProxy;

    @Autowired
    private SMDepartmentProxy SMDepartmentProxy;

    @Override
    public PageRows<SalCompanyBO> querySalCompanyList(PageInfo pageInfo) {
        SalCompanyBO salCompanyBO = pageInfo.toJavaObject(SalCompanyBO.class);

        PageRows<SalCompanyBO> result = PageKit.doSelectPage(pageInfo,() -> baseMapper.querySalCompanyList(salCompanyBO));

        List<SalCompanyBO> boList = result.getRows();
        // 获取客服经理名字
        for (SalCompanyBO bo: boList) {
            AfUserPermissionRequestDTO requestDTO = new AfUserPermissionRequestDTO();
            requestDTO.setCompanyId(bo.getCompanyId());
            List<AfUserPermissionDTO> dtoList = afUserCompanyRefProxy.queryUserCompanyByCompanyId(requestDTO);
            if(dtoList != null && dtoList.size() > 0){
                JsonResult<List<SMDepartmentDTO>> resultDto = SMDepartmentProxy.getDepartmentsOfUser(dtoList.get(0).getUserId(), 7);
                List<SMDepartmentDTO> dList = resultDto.getData();
                if(dList != null && dList.size() > 0){
                    bo.setSalManagerName(dList.get(0).getDepartmentName());
                }
            }
        }
        return result;
    }

    @Override
    public List<SalCompanyBO> getSalCompanyList(SalCompanyBO salCompanyBO) {
        return baseMapper.querySalCompanyList(salCompanyBO);
    }
}
