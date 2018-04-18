package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmDataauthCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.AmAuthorityService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmDataauthCompanyMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmDataauthCompany;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMDepartmentProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMUserInfoProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMUserInfoDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.CompanyManagementDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 雇佣数据限权管理服务实现类
 * </p>
 *
 * @author LiYueLong
 * @since 2018-4-18
 */
@Service
public class AmAuthorityServiceImpl extends ServiceImpl<AmDataauthCompanyMapper, AmDataauthCompany> implements AmAuthorityService {

    @Autowired
    private SMUserInfoProxy smUserInfoProxy;

    @Autowired
    private SMDepartmentProxy smDepartmentProxy;

    @Autowired
    private CompanyProxy companyProxy;

    @Override
    public List<AmUserInfoDTO> queryUsersByDepartmentId() {
        // 查询上海社保 配置客户的人员列表
        JsonResult<List<SMUserInfoDTO>> result = smUserInfoProxy.getUsersByDepartmentId(515);

        List<AmUserInfoDTO> resultDate = new ArrayList<>();
        // 转换成社保DTO
        List<SMUserInfoDTO> smDtos = result.getData();
        for (SMUserInfoDTO dto: smDtos) {
            AmUserInfoDTO d = new AmUserInfoDTO();
            BeanUtils.copyProperties(dto,d);
            resultDate.add(d);
        }
        return resultDate;
    }



    @Override
    public List<AmDepartmentDTO> querySubDepartmentsOfLevel() {
        // 调用内控接口,查询上海社保的组织架构树
        JsonResult<List<com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO>> result
            = smDepartmentProxy.getChildDepartments(415);
        List<com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO> list = result.getData();
        //转换成上海社保的DTO
        List<AmDepartmentDTO> resultList = new ArrayList<>();
        for (com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO dto : list) {
            AmDepartmentDTO smDto = new AmDepartmentDTO();
            BeanUtils.copyProperties(dto, smDto);
            resultList.add(smDto);
        }
        return resultList;
    }

    @Override
    public AmCompanyManagementListDTO queryAfCompanyByUidAndServiceCenterId(String userId, Long serviceCenterId) {

        // 查询客服中心接口根据用户ID和组织ID查客户列表
        com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<List<CompanyManagementDTO>>
            result = companyProxy.getAfCompanyByServiceCenterId(serviceCenterId.intValue());

        // 转换成社保项目的DTO
        List<CompanyManagementDTO> dtoList = result.getObject();
        List<AmCompanyManagementDTO> list = new ArrayList<>();
        for (CompanyManagementDTO dto : dtoList) {
            AmCompanyManagementDTO newDto = new AmCompanyManagementDTO();
            BeanUtils.copyProperties(dto, newDto);
            list.add(newDto);
        }
        AmCompanyManagementListDTO resultData = new AmCompanyManagementListDTO();
        resultData.setDtoList(list);

        // 查询已配置的客户权限
        AmDataauthCompany dataauthCompany = new AmDataauthCompany();
        dataauthCompany.setUserId(userId);
        dataauthCompany.setServiceCenterId(serviceCenterId);
        // 从本地库查询已配置的客户
        List<AmDataauthCompanyBO> boList = baseMapper.queryListByUidAndSerCenterId(dataauthCompany);
        List<String> companyIds = new ArrayList<>();
        for (AmDataauthCompanyBO bo : boList) {
            companyIds.add(bo.getCompanyId());
        }
        // 已配置的客户ID
        resultData.setCompanyIds(companyIds);

        resultData.setCode(result.getCode());
        resultData.setMessage(result.getMessage());

        return resultData;
    }

    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveSsDataauth(AmDataauthCompanyDTO dto) {
        AmDataauthCompany dataauthCompany = new AmDataauthCompany();
        dataauthCompany.setUserId(dto.getUserId());
        dataauthCompany.setServiceCenterId(dto.getServiceCenterId());
        // 删除已配置的客户
        baseMapper.delByUidAndSerCenterId(dataauthCompany);
        for (String companyId : dto.getCompanyIds()) {
            AmDataauthCompany dataauth = new AmDataauthCompany();
            // 配置的用户ID
            dataauth.setUserId(dto.getUserId());
            // 配置的用户组织ID
            dataauth.setServiceCenterId(dto.getServiceCenterId());
            // 配置的客户公司ID
            dataauth.setCompanyId(companyId);
            dataauth.setCreatedTime(new Date());
            dataauth.setModifiedTime(new Date());
            dataauth.setCreatedBy(UserContext.getUserId());
            dataauth.setModifiedBy(UserContext.getUserId());
            // 重新配置客户
            baseMapper.insert(dataauth);
        }
        return true;
    }

}
