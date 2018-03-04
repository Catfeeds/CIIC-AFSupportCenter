package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmResignMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmResignService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.resignSearchExportOpt;
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
 * 用工表 服务实现类
 * </p>
 */
@Service
public class AmResignServiceImpl extends ServiceImpl<AmResignMapper, AmResign> implements IAmResignService {
    public PageRows<AmResignBO> queryAmResign(PageInfo pageInfo){

        AmResignBO  amResignBO = pageInfo.toJavaObject(AmResignBO.class);

        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amResignBO.setParam(param);

        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }

        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));

    }

    @Override
    public List<AmResignBO> taskCount(PageInfo pageInfo) {
        AmResignBO amResignBO = pageInfo.toJavaObject(AmResignBO.class);
        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }
        amResignBO.setParam(param);

        return baseMapper.taskCount(amResignBO);
    }

    @Override
    public List<AmResignBO> queryAmResignDetail(Map<String,Object> param) {
        return  baseMapper.queryAmResignDetail(param);
    }

    @Override
    public List<resignSearchExportOpt> queryAmResignList(AmResignBO amResignBO) {
        return baseMapper.queryAmResignList(amResignBO);
    }
}
