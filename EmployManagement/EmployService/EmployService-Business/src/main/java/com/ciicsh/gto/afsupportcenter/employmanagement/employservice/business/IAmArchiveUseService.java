package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUse;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;


/**
 * Created by zhangzhiwen on 2018/2/8.
 */
public interface IAmArchiveUseService extends IService<AmArchiveUse> {

    public PageRows<AmArchiveUse> queryAmArchiveUse(PageInfo pageInfo);

    boolean deleteAmArchiveUse(AmArchiveUse amArchiveUse);

}
