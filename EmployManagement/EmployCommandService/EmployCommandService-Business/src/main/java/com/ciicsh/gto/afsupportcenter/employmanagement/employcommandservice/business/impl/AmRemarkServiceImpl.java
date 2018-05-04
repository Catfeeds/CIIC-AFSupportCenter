package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmRemarkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmRemarkService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmRemarkMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmRemark;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/1/30.
 */
@Service
public class AmRemarkServiceImpl extends ServiceImpl<AmRemarkMapper, AmRemark> implements IAmRemarkService{

    @Override
    public PageRows<AmRemarkBO> queryAmRemark(PageInfo pageInfo) {

        AmRemarkBO amRemarkBO = pageInfo.toJavaObject(AmRemarkBO.class);
        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmRemark(amRemarkBO));
    }

    @Override
    public boolean deleteAmRemark(Long remarkId) {
        AmRemark amRemark = this.selectById(remarkId);
        String userId = ReasonUtil.getUserId();
        if(!userId.equals(amRemark.getCreatedBy()))
        {
            return false;
        }
       int i =  baseMapper.deleteAmRemark(remarkId);
       if(i>0){
           return  true;
       }

        return false;
    }

    @Override
    public List<AmRemarkBO> getAmRemakList(AmRemarkBO amRemarkBO) {
        return baseMapper.queryAmRemark(amRemarkBO);
    }
}
