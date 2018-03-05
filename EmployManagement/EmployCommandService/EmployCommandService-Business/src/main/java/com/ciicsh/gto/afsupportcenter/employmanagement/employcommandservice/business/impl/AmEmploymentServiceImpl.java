package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmEmploymentMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmploymentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.archiveSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工主表 服务实现类
 * </p>
 */
@Service
public class AmEmploymentServiceImpl extends ServiceImpl<AmEmploymentMapper, AmEmployment> implements IAmEmploymentService {

    @Override
    public List<AmEmploymentBO> queryAmEmployment(Map<String, Object> param) {
        return baseMapper.queryAmEmployment(param);
    }

    @Override
    public PageRows<AmEmploymentBO> queryAmArchive(PageInfo pageInfo) {
        AmEmploymentBO amEmploymentBO = pageInfo.toJavaObject(AmEmploymentBO.class);

        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amEmploymentBO.setParam(param);

        if(null!=amEmploymentBO.getTaskStatus()&&amEmploymentBO.getTaskStatus()==0){
            amEmploymentBO.setTaskStatus(null);
        }

        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(amEmploymentBO));
    }

    @Override
    public List<AmEmploymentBO> taskCountEmployee(PageInfo pageInfo) {
        AmEmploymentBO amEmploymentBO = pageInfo.toJavaObject(AmEmploymentBO.class);

        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amEmploymentBO.setParam(param);
        return baseMapper.taskCountEmployee(amEmploymentBO);
    }

    @Override
    public List<AmEmploymentBO> taskCountResign(PageInfo pageInfo) {
        AmEmploymentBO amEmploymentBO = pageInfo.toJavaObject(AmEmploymentBO.class);

        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amEmploymentBO.setParam(param);
        return baseMapper.taskCountResign(amEmploymentBO);
    }

    @Override
    public List<archiveSearchExportOpt> queryAmArchiveList(AmEmploymentBO amEmploymentBO) {
        return baseMapper.queryAmArchiveList(amEmploymentBO);
    }

}
