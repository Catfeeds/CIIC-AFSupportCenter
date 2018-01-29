package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmResignMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmResignService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用工表 服务实现类
 * </p>
 */
@Service
public class AmResignServiceImpl extends ServiceImpl<AmResignMapper, AmResign> implements IAmResignService {
    public PageRows<AmResignBO> queryAmResign(PageInfo pageInfo){

        AmResignBO  amResignBO = pageInfo.toJavaObject(AmResignBO.class);

        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));

    }
}
