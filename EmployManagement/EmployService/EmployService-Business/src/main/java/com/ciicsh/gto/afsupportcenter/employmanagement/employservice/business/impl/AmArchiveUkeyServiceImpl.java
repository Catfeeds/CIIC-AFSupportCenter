package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyRenewBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveUkeyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.ukeySearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveUkeyMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveUkeyRenewMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.SalCompanyMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUkey;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUkeyRenew;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.CompanyTypeDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult;
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
 * 档案预增表 服务实现类
 * </p>
 *
 * @author lyl
 * @since 2018-05-31
 */

@Service
public class AmArchiveUkeyServiceImpl extends ServiceImpl<AmArchiveUkeyMapper, AmArchiveUkey> implements IAmArchiveUkeyService {

    @Autowired
    private AmArchiveUkeyRenewMapper amArchiveUkeyRenewMapper;

    @Autowired
    private SalCompanyMapper salCompanyMapper;

    @Autowired
    private CompanyProxy companyProxy;

    @Override
    public PageRows<AmArchiveUkeyBO> queryAmArchiveUkeyList(PageInfo pageInfo) {
        PageRows<AmArchiveUkeyBO> result = new PageRows<>();
        AmArchiveUkeyBO bo = pageInfo.toJavaObject(AmArchiveUkeyBO.class);
        List<String> param = new ArrayList<String>();
        if(!StringUtil.isEmpty(bo.getParams()))
        {
            String arr[] = bo.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }
        bo.setParam(param);
        PageRows<AmArchiveUkey> resultPo = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryUkeyList(bo));
        result.setTotal(resultPo.getTotal());
        List<AmArchiveUkeyBO> boList = new ArrayList<>();
        List<AmArchiveUkey> poList = resultPo.getRows();
        for (AmArchiveUkey ueky:poList) {
            AmArchiveUkeyBO b = new AmArchiveUkeyBO();
            BeanUtils.copyProperties(ueky,b);
            boList.add(b);
        }
        result.setRows(boList);
        return result;
    }

    @Override
    public AmArchiveUkeyBO queryAmArchiveUkey(Long id) {
        AmArchiveUkeyBO result = new AmArchiveUkeyBO();
        AmArchiveUkey ukey = new AmArchiveUkey();
        ukey.setId(id);
        AmArchiveUkey po = baseMapper.selectOne(ukey);
        if(po == null){
            return null;
        }
        BeanUtils.copyProperties(po,result);
        return result;
    }

    @Override
    public List<AmArchiveUkeyRenewBO> queryAmArchiveUkeyRenew(Long id) {
        AmArchiveUkeyRenew renew = new AmArchiveUkeyRenew();
        renew.setKeyId(id);
        List<AmArchiveUkeyRenew> renews = amArchiveUkeyRenewMapper.selectList(new EntityWrapper<>(renew));
        List<AmArchiveUkeyRenewBO> boList = new ArrayList<>();
        for (AmArchiveUkeyRenew r:renews) {
            AmArchiveUkeyRenewBO bo = new AmArchiveUkeyRenewBO();
            BeanUtils.copyProperties(r,bo);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public AmArchiveUkeyBO queryAmArchiveUkey(String organizationCode) {
        AmArchiveUkeyBO result = new AmArchiveUkeyBO();
        AmArchiveUkey ukey = new AmArchiveUkey();
        ukey.setOrganizationCode(organizationCode);
        AmArchiveUkey po = baseMapper.selectOne(ukey);
        if(po == null){
            return null;
        }
        BeanUtils.copyProperties(po,result);
        return result;
    }

    @Override
    public boolean deleteAmArchiveUkey(Long id) {
        AmArchiveUkey po = new AmArchiveUkey();
        po.setId(id);
        po.setModifiedBy(UserContext.getUserName());
        po.setModifiedTime(new Date());
        po.setIsActive(0);
        Integer result = baseMapper.updateById(po);
        return result > 0;
    }

    @Override
    public boolean saveAmArchiveUkey(AmArchiveUkeyBO amArchiveUkeyBO) {
        AmArchiveUkey po = new AmArchiveUkey();
        BeanUtils.copyProperties(amArchiveUkeyBO, po);
        Date nowTime = new Date();
        if(amArchiveUkeyBO.getId() == null){
            po.setCreatedBy(UserContext.getUserName());
            po.setCreatedTime(nowTime);
        }
        po.setModifiedBy(UserContext.getUserName());
        po.setModifiedTime(nowTime);
        po.setIsActive(1);
        po.setCreatedBy(UserContext.getUserName());
        po.setCreatedTime(nowTime);
        boolean result = this.insertOrUpdateAllColumn(po);
        return result;
    }

    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean amArchiveUkeyRenew(AmArchiveUkeyBO amArchiveUkeyBO) {
        AmArchiveUkeyRenew renew = new AmArchiveUkeyRenew();
        renew.setKeyId(amArchiveUkeyBO.getId());
        renew.setType(amArchiveUkeyBO.getType());
        renew.setDueDate(amArchiveUkeyBO.getRenewDueDate());
        renew.setRenewDate(amArchiveUkeyBO.getRenewDate());
        Date nowDate = new Date();
        renew.setIsActive(1);
        renew.setCreatedBy(UserContext.getUserName());
        renew.setCreatedTime(nowDate);
        renew.setModifiedBy(UserContext.getUserName());
        renew.setModifiedTime(nowDate);
        amArchiveUkeyRenewMapper.insert(renew);

        AmArchiveUkey ukey = new AmArchiveUkey();
        ukey.setId(amArchiveUkeyBO.getId());
        ukey.setDueDate(amArchiveUkeyBO.getRenewDueDate());
        ukey.setDueDate(amArchiveUkeyBO.getRenewDueDate());
        ukey.setModifiedBy(UserContext.getUserName());
        ukey.setModifiedTime(nowDate);
        baseMapper.updateById(ukey);
        return true;
    }

    @Override
    public List<ukeySearchExportOpt> queryAdvanceSearchExportOpt(AmArchiveUkeyBO bo) {
        List<ukeySearchExportOpt> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        if(!StringUtil.isEmpty(bo.getParams()))
        {
            String arr[] = bo.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }
        bo.setParam(param);
        List<AmArchiveUkey> ukeyList = baseMapper.queryUkeyList(bo);

        for (AmArchiveUkey ueky:ukeyList) {
            ukeySearchExportOpt opt = new ukeySearchExportOpt();
            BeanUtils.copyProperties(ueky,opt);
            result.add(opt);
        }
        return result;
    }


    @Override
    public AmArchiveUkeyBO queryOrganizationCodeByCid(String companyId, String companyName) {
        if(companyId == null){
            return null;
        }
        SalCompanyBO bo = new SalCompanyBO();
        bo.setCompanyId(companyId);
        List<SalCompanyBO> companyList = salCompanyMapper.querySalCompanyList(bo);
        AmArchiveUkeyBO result = new AmArchiveUkeyBO();
        if(companyList != null && companyList.size() > 0){
            result.setCompanyName(companyList.get(0).getTitle());
        }
        // 查询组织机构
        JsonResult<CompanyTypeDTO> comDto = companyProxy.getCompanyCoreInfo(companyId);
        if(comDto.getObject() != null){
            result.setOrganizationCode(comDto.getObject().getOrganizationCode());
        }
        // 查询服务中心
        JsonResult<AfCompanyDetailResponseDTO> detailDto = companyProxy.afDetail(companyId);
        if(detailDto.getObject() != null){
            result.setServiceCenter(detailDto.getObject().getServiceCenter());
        }
        return result;
    }
}
