package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfAuthorityService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfDataauthCompanyMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfDataauthTaskCategoryMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfDataauthWelfareUnitMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfDataauthCompany;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfDataauthTaskCategory;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfDataauthWelfareUnit;
import com.ciicsh.gto.afsupportcenter.util.constant.HouseFundConst;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMDepartmentProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMUserInfoProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult;
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
public class HfAuthorityServiceImpl extends ServiceImpl<HfDataauthCompanyMapper, HfDataauthCompany> implements HfAuthorityService {
    @Autowired
    private SMUserInfoProxy smUserInfoProxy;

    @Autowired
    private SMDepartmentProxy smDepartmentProxy;

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private HfDataauthWelfareUnitMapper hfDataauthWelfareUnitMapper;

    @Autowired
    private HfDataauthTaskCategoryMapper hfDataauthTaskCategoryMapper;

    @Override
    public List<HfUserInfoDTO> queryUsersByDepartmentId() {
        List<HfUserInfoDTO> resultDate = new ArrayList<>();
        JsonResult<List<SMUserInfoDTO>> result =  smUserInfoProxy.getUsersByDeptId(SocialSecurityConst.DEP_WELFARE_DEPARTMENT_ID);
        List<SMUserInfoDTO> smDtos = result.getData();
        for (SMUserInfoDTO dto: smDtos) {
            HfUserInfoDTO d = new HfUserInfoDTO();
            BeanUtils.copyProperties(dto,d);
            resultDate.add(d);
        }

        // 查询上海社保 配置客户的人员列表
        result = smUserInfoProxy.getUsersByDepartmentId(HouseFundConst.DEP_HOUSE_FUND_TEAM_ID);
        // 转换成社保DTO
        smDtos = result.getData();
        for (SMUserInfoDTO dto: smDtos) {
            HfUserInfoDTO d = new HfUserInfoDTO();
            BeanUtils.copyProperties(dto,d);
            resultDate.add(d);
        }
        return resultDate;
    }

    @Override
    public List<HfDepartmentDTO> querySubDepartmentsOfLevel() {
        return querySubDepartmentsOfLevel(null);
    }

    @Override
    public List<HfDepartmentDTO> querySubDepartmentsOfLevel(Integer[] levels) {
        // 调用内控接口,查询上海社保的组织架构树
        JsonResult<List<com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO>> result
            = smDepartmentProxy.getChildDepartments(SocialSecurityConst.DEP_COMPANY_CENTER_ID);
        List<com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO> list = result.getData();
        //转换成上海社保的DTO
        List<HfDepartmentDTO> resultList = new ArrayList<>();
        for (com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO dto : list) {
            if (ArrayUtils.isNotEmpty(levels) && !ArrayUtils.contains(levels, dto.getDepartmentLevel())) {
                continue;
            }
            HfDepartmentDTO smDto = new HfDepartmentDTO();
            BeanUtils.copyProperties(dto, smDto);
            resultList.add(smDto);
        }
        return resultList;
    }

    @Override
    public HfCompanyManagementListDTO queryAfCompanyByUidAndServiceCenterId(String userId, Long serviceCenterId) {

        // 查询客服中心接口根据用户ID和组织ID查客户列表
        com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<List<CompanyManagementDTO>>
            result = companyProxy.getAfCompanyByServiceCenterId(serviceCenterId.intValue());

        // 转换成社保项目的DTO
        List<CompanyManagementDTO> dtoList = result.getObject();
        List<HfCompanyManagementDTO> list = new ArrayList<>();
        for (CompanyManagementDTO dto : dtoList) {
            HfCompanyManagementDTO newDto = new HfCompanyManagementDTO();
            BeanUtils.copyProperties(dto, newDto);
            list.add(newDto);
        }
        HfCompanyManagementListDTO resultData = new HfCompanyManagementListDTO();
        resultData.setDtoList(list);

        // 查询已配置的客户权限
        HfDataauthCompany dataauthCompany = new HfDataauthCompany();
        dataauthCompany.setUserId(userId);
        dataauthCompany.setServiceCenterId(serviceCenterId);
        // 从本地库查询已配置的客户
        List<HfDataauthCompany> boList = baseMapper.selectList(new EntityWrapper<>(dataauthCompany));
        List<String> companyIds = new ArrayList<>();
        for (HfDataauthCompany bo : boList) {
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
    public boolean saveSsDataauth(HfDataauthCompanyDTO dto) {
        HfDataauthCompany dataauthCompany = new HfDataauthCompany();
        dataauthCompany.setUserId(dto.getUserId());
        dataauthCompany.setServiceCenterId(dto.getServiceCenterId());
        // 删除已配置的客户
        baseMapper.delete(new EntityWrapper<>(dataauthCompany));
        for (String companyId : dto.getCompanyIds()) {
            HfDataauthCompany dataauth = new HfDataauthCompany();
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
    public boolean saveHfDataauthWelfareUnit(HfDataauthWelfareUnitDTO dto) {
        HfDataauthWelfareUnit u = new HfDataauthWelfareUnit();
        u.setUserId(dto.getUserId());
        hfDataauthWelfareUnitMapper.delete(new EntityWrapper<>(u));
        List<Integer> units = dto.getWelfareUnits();
        for (Integer unitId: units) {
            HfDataauthWelfareUnit unit = new HfDataauthWelfareUnit();
            // 用户ID
            unit.setUserId(dto.getUserId());
            // 福利方ID
            unit.setWelfareUnit(unitId);
            unit.setCreatedBy(UserContext.getUserId());
            unit.setModifiedBy(UserContext.getUserId());
            unit.setCreatedTime(new Date());
            unit.setModifiedTime(new Date());
            hfDataauthWelfareUnitMapper.insert(unit);
        }
        return true;
    }


    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveHfDataauthTaskCategory(HfDataauthWelfareUnitDTO dto) {

        HfDataauthTaskCategory uni = new HfDataauthTaskCategory();
        uni.setUserId(dto.getUserId());
        hfDataauthTaskCategoryMapper.delete(new EntityWrapper<>(uni));
        List<Integer> units = dto.getWelfareUnits();
        for (Integer unitId: units) {

            HfDataauthTaskCategory task = new HfDataauthTaskCategory();
            // 用户ID
            task.setUserId(dto.getUserId());
            // 福利方ID
            task.setTaskCategory(unitId);
            task.setCreatedBy(UserContext.getUserId());
            task.setModifiedBy(UserContext.getUserId());
            task.setCreatedTime(new Date());
            task.setModifiedTime(new Date());
            hfDataauthTaskCategoryMapper.insert(task);
        }
        return true;
    }

    @Override
    public HfDataauthWelfareUnitDTO queryHfDataauthWelfareUnit(String userId) {
        HfDataauthWelfareUnitDTO dto = new HfDataauthWelfareUnitDTO();
        dto.setUserId(userId);
        List<Integer> welfareUnits = new ArrayList<>();
        HfDataauthWelfareUnit po = new HfDataauthWelfareUnit();
        po.setUserId(userId);
        List<HfDataauthWelfareUnit> boList = hfDataauthWelfareUnitMapper.selectList(new EntityWrapper<>(po));
        for (HfDataauthWelfareUnit bo : boList) {
            // 福利办理方
            welfareUnits.add(bo.getWelfareUnit());
        }
        dto.setWelfareUnits(welfareUnits);
        return dto;
    }

    @Override
    public HfDataauthWelfareUnitDTO queryAuthorityTaskCategory(String userId) {
        HfDataauthWelfareUnitDTO dto = new HfDataauthWelfareUnitDTO();
        dto.setUserId(userId);
        List<Integer> ids = new ArrayList<>();
        HfDataauthTaskCategory task = new HfDataauthTaskCategory();
        task.setUserId(userId);
        List<HfDataauthTaskCategory> list = hfDataauthTaskCategoryMapper.selectList(new EntityWrapper<>(task));
        for (HfDataauthTaskCategory po:list ) {
            //任务单类型
            ids.add(po.getTaskCategory());
        }
        dto.setWelfareUnits(ids);
        return dto;
    }
}
