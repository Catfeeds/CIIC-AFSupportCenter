package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.system.AfUserPermissionDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfUserCompanyRefProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.ISalCompanyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.IndependentExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.SalCompanyMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.SalCompany;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMDepartmentProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMDepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if (!StringUtil.isEmpty(salCompanyBO.getParams())) {
            String arr[] = salCompanyBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }
        salCompanyBO.setParam(param);

        if (null != salCompanyBO.getJob() && salCompanyBO.getJob() == 0) {
            salCompanyBO.setJob(null);
        }

        PageRows<SalCompanyBO> result = PageKit.doSelectPage(pageInfo, () -> baseMapper.querySalCompanyList(salCompanyBO));

        return result;
    }

    @Override
    public List<SalCompanyBO> getSalCompanyList(SalCompanyBO salCompanyBO) {
        return baseMapper.querySalCompanyList(salCompanyBO);
    }

    @Override
    public List<SalCompanyBO> taskCount(PageInfo pageInfo) {
        SalCompanyBO salCompanyBO = pageInfo.toJavaObject(SalCompanyBO.class);

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if (!StringUtil.isEmpty(salCompanyBO.getParams())) {
            String arr[] = salCompanyBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }
        salCompanyBO.setParam(param);
        return baseMapper.taskCount(salCompanyBO);
    }

    @Override
    public List<IndependentExportOpt> querySalOptList(SalCompanyBO salCompanyBO) {
        return baseMapper.querySalOptList(salCompanyBO);
    }
}
