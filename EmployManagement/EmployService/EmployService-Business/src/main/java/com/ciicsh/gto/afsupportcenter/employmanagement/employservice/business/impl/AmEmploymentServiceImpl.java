package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmploymentService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmploymentMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.archiveSearchExportOpt;
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

        if(null!=amEmploymentBO.getTaskStatus()&&amEmploymentBO.getTaskStatus()==6)
        {
            amEmploymentBO.setTaskStatus(null);
            amEmploymentBO.setTaskStatusOther(0);
        }

        if(null!=amEmploymentBO.getTaskResignStatus()&&amEmploymentBO.getTaskResignStatus()==6){
            amEmploymentBO.setTaskResignStatus(null);
            amEmploymentBO.setTaskResignStatusOther(0);
        }

        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amEmploymentBO.setParam(param);

        //如果是查询用工总的数量就不需要状态了
        if(null!=amEmploymentBO.getTaskStatus()&&amEmploymentBO.getTaskStatus()==0){
            amEmploymentBO.setTaskStatus(null);
        }
        //如果是查询退工总的数量就不需要状态了
        if(null!=amEmploymentBO.getTaskResignStatus()&&amEmploymentBO.getTaskResignStatus()==0){
            amEmploymentBO.setTaskResignStatus(null);
        }

        if(amEmploymentBO.getTaskCategory()!=null&&amEmploymentBO.getTaskCategory()==2)
        {
            return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchiveResign(amEmploymentBO));
        }else {
            return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(amEmploymentBO));
        }


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

    @Override
    public List<AmEmploymentBO> queryAmEmploymentResign(Map<String, Object> param) {
        return baseMapper.queryAmEmploymentResign(param);
    }

}
