package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmArchiveUseService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmArchiveUseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchiveUse;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;


/**
 * Created by zhangzhiwen on 2018/2/8.
 */
@Service
public class AmArchiveUseServiceImpl extends ServiceImpl<AmArchiveUseMapper, AmArchiveUse>  implements IAmArchiveUseService {


    @Override
    public PageRows<AmArchiveUse> queryAmArchiveUse(PageInfo pageInfo) {

        AmArchiveUse amArchiveUse = pageInfo.toJavaObject(AmArchiveUse.class);

        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchiveUseList(amArchiveUse));
    }

    @Override
    public boolean deleteAmArchiveUse(AmArchiveUse amArchiveUse) {

        int i =0;
        i = baseMapper.deleteAmArchiveUse(amArchiveUse);
        if(i>0){
            return true;
        }
        return false;
    }
}
