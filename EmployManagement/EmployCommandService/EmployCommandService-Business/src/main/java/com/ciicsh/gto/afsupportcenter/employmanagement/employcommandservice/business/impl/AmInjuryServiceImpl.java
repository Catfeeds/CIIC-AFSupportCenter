package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmInjuryService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmInjuryMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmInjury;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

/**
 * Created by zhangzhiwen on 2018/2/7.
 */

@Service
public class AmInjuryServiceImpl extends ServiceImpl<AmInjuryMapper, AmInjury> implements IAmInjuryService {

    @Override
    public PageRows<AmInjury> queryAmInjury(PageInfo pageInfo) {
        AmInjury amInjury = pageInfo.toJavaObject(AmInjury.class);
        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmInjury(amInjury));
    }

    @Override
    public boolean deleteAmInjury(Long injuryId) {
        int i =0;
        i = baseMapper.deleteAmInjury(injuryId);
        if(i>0){
            return  true;
        }else {
            return  false;
        }

    }
}
