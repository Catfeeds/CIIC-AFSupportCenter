package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAuthorityService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsDataauthCompanyMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsDataauthTaskCategoryMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsDataauthWelfareUnitMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.dataauth.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsDataauthCompany;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsDataauthTaskCategory;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsDataauthWelfareUnit;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMDepartmentProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMUserInfoProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMUserInfoDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.CompanyManagementDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 社保限权管理服务实现类
 * </p>
 *
 * @author LiYueLong
 * @since 2018-4-04
 */
@Service
public class SsAuthorityServiceImpl extends ServiceImpl<SsDataauthCompanyMapper, SsDataauthCompany> implements SsAuthorityService {

    @Autowired
    private SMUserInfoProxy smUserInfoProxy;

    @Autowired
    private SMDepartmentProxy smDepartmentProxy;

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private SsDataauthWelfareUnitMapper ssDataauthWelfareUnitMapper;

    @Autowired
    SsDataauthTaskCategoryMapper ssDataauthTaskCategoryMapper;

    @Override
    public List<SsUserInfoDTO> queryUsersByDepartmentId() {
        List<SsUserInfoDTO> resultDate = new ArrayList<>();
        JsonResult<List<SMUserInfoDTO>> result = smUserInfoProxy.getUsersByDeptId(SocialSecurityConst.DEP_WELFARE_DEPARTMENT_ID);
        List<SMUserInfoDTO> smDtos = result.getData();
        for (SMUserInfoDTO dto: smDtos) {
            SsUserInfoDTO d = new SsUserInfoDTO();
            BeanUtils.copyProperties(dto,d);
            resultDate.add(d);
        }

        // 查询上海社保 配置客户的人员列表
        result = smUserInfoProxy.getUsersByDepartmentId(SocialSecurityConst.DEP_SOCIAL_SECURITY_TEAM_ID);
        // 转换成社保DTO
        smDtos = result.getData();
        for (SMUserInfoDTO dto: smDtos) {
            SsUserInfoDTO d = new SsUserInfoDTO();
            BeanUtils.copyProperties(dto,d);
            resultDate.add(d);
        }
        return resultDate;
    }



    @Override
    public List<SsDepartmentDTO> querySubDepartmentsOfLevel() {
        // 调用内控接口,查询上海社保的组织架构树
        JsonResult<List<SMDepartmentDTO>> result
            = smDepartmentProxy.getChildDepartments(SocialSecurityConst.DEP_COMPANY_CENTER_ID);
        List<SMDepartmentDTO> list = result.getData();
        //转换成上海社保的DTO
        List<SsDepartmentDTO> resultList = new ArrayList<>();
        for (com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO dto : list) {
            SsDepartmentDTO smDto = new SsDepartmentDTO();
            BeanUtils.copyProperties(dto, smDto);
            resultList.add(smDto);
        }
        return resultList;
    }
    @Override
    public List<SsDepartmentDTO> querySubDepartmentsOfLevel(Integer[] levels) {
        // 调用内控接口,查询上海社保的组织架构树
        JsonResult<List<SMDepartmentDTO>> result
            = smDepartmentProxy.getChildDepartments(SocialSecurityConst.DEP_COMPANY_CENTER_ID);
        List<SMDepartmentDTO> list = result.getData();
        //转换成上海社保的DTO
        List<SsDepartmentDTO> resultList = new ArrayList<>();
        for (com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO dto : list) {
            if (ArrayUtils.isNotEmpty(levels) && !ArrayUtils.contains(levels, dto.getDepartmentLevel())) {
                continue;
            }
            SsDepartmentDTO smDto = new SsDepartmentDTO();
            BeanUtils.copyProperties(dto, smDto);
            resultList.add(smDto);
        }
        return resultList;
    }
    @Override
    public SsCompanyManagementListDTO queryAfCompanyByUidAndServiceCenterId(String userId, Long serviceCenterId) {

        // 查询客服中心接口根据用户ID和组织ID查客户列表
        com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<List<CompanyManagementDTO>>
            result = companyProxy.getAfCompanyByServiceCenterId(serviceCenterId.intValue());

        // 转换成社保项目的DTO
        List<CompanyManagementDTO> dtoList = result.getObject();
        List<SsCompanyManagementDTO> list = new ArrayList<>();
        for (CompanyManagementDTO dto : dtoList) {
            SsCompanyManagementDTO newDto = new SsCompanyManagementDTO();
            BeanUtils.copyProperties(dto, newDto);
            list.add(newDto);
        }
        SsCompanyManagementListDTO resultData = new SsCompanyManagementListDTO();
        resultData.setDtoList(list);

        // 查询已配置的客户权限
        SsDataauthCompany dataauthCompany = new SsDataauthCompany();
        dataauthCompany.setUserId(userId);
        dataauthCompany.setServiceCenterId(serviceCenterId);
        // 从本地库查询已配置的客户
        List<SsDataauthCompany> boList = baseMapper.selectList(new EntityWrapper<>(dataauthCompany));
        List<String> companyIds = new ArrayList<>();
        for (SsDataauthCompany bo : boList) {
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
    public boolean saveSsDataauth(SsDataauthCompanyDTO dto) {
        SsDataauthCompany dataauthCompany = new SsDataauthCompany();
        dataauthCompany.setUserId(dto.getUserId());
        dataauthCompany.setServiceCenterId(dto.getServiceCenterId());
        // 删除已配置的客户
        baseMapper.delete(new EntityWrapper<>(dataauthCompany));
        for (String companyId : dto.getCompanyIds()) {
            SsDataauthCompany dataauth = new SsDataauthCompany();
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

    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveSsDataauthWelfareUnit(SsDataauthWelfareUnitDTO dto) {
        SsDataauthWelfareUnit uni = new SsDataauthWelfareUnit();
        uni.setUserId(dto.getUserId());
        ssDataauthWelfareUnitMapper.delete(new EntityWrapper<>(uni));
        List<Integer> units = dto.getWelfareUnits();
        for (Integer unitId: units) {
            SsDataauthWelfareUnit unit = new SsDataauthWelfareUnit();
            // 用户ID
            unit.setUserId(dto.getUserId());
            // 福利方ID
            unit.setWelfareUnit(unitId);
            unit.setCreatedBy(UserContext.getUserId());
            unit.setModifiedBy(UserContext.getUserId());
            unit.setCreatedTime(new Date());
            unit.setModifiedTime(new Date());
            ssDataauthWelfareUnitMapper.insert(unit);
        }
        return true;
    }


    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveSsDataauthTaskCategory(SsDataauthWelfareUnitDTO dto) {

        SsDataauthTaskCategory task1 = new SsDataauthTaskCategory();
        task1.setUserId(dto.getUserId());
        ssDataauthTaskCategoryMapper.delete(new EntityWrapper<>(task1));
        List<Integer> units = dto.getWelfareUnits();
        for (Integer unitId: units) {

            SsDataauthTaskCategory task = new SsDataauthTaskCategory();
            // 用户ID
            task.setUserId(dto.getUserId());
            // 福利方ID
            task.setTaskCategory(unitId);
            task.setCreatedBy(UserContext.getUserId());
            task.setModifiedBy(UserContext.getUserId());
            task.setCreatedTime(new Date());
            task.setModifiedTime(new Date());
            ssDataauthTaskCategoryMapper.insert(task);
        }
        return true;
    }

    @Override
    public SsDataauthWelfareUnitDTO querySsDataauthWelfareUnit(String userId) {
        SsDataauthWelfareUnitDTO dto = new SsDataauthWelfareUnitDTO();
        dto.setUserId(userId);
        List<Integer> welfareUnits = new ArrayList<>();
        SsDataauthWelfareUnit po = new SsDataauthWelfareUnit();
        po.setUserId(userId);
        List<SsDataauthWelfareUnit> boList = ssDataauthWelfareUnitMapper.selectList(new EntityWrapper<>(po));
        for (SsDataauthWelfareUnit bo : boList) {
            // 福利办理方
            welfareUnits.add(bo.getWelfareUnit());
        }
        dto.setWelfareUnits(welfareUnits);
        return dto;
    }

    @Override
    public SsDataauthWelfareUnitDTO queryAuthorityTaskCategory(String userId) {
        SsDataauthWelfareUnitDTO dto = new SsDataauthWelfareUnitDTO();
        dto.setUserId(userId);
        List<Integer> ids = new ArrayList<>();
        SsDataauthTaskCategory task = new SsDataauthTaskCategory();
        task.setUserId(userId);
        List<SsDataauthTaskCategory> list = ssDataauthTaskCategoryMapper.selectList(new EntityWrapper<>(task));
        for (SsDataauthTaskCategory po:list ) {
            //任务单类型
            ids.add(po.getTaskCategory());
        }
        dto.setWelfareUnits(ids);
        return dto;
    }


}
