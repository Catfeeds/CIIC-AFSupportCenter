package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftQueryService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.GiftQueryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 礼品库 服务实现类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@Service
public class GiftQueryServiceImpl extends ServiceImpl<GiftQueryMapper, GiftPO> implements GiftQueryService {

    @Override
    public Page<GiftPO> queryGiftList(Page<GiftPO> page, GiftPO entity) {
        EntityWrapper<GiftPO> entityWrapper = new EntityWrapper<>();
        //排除礼品名称空数据
        entityWrapper.like(StringUtils.isNotEmpty(entity.getGiftName()), "gift_name", entity.getGiftName())
                .and(entity.getStatus() != null, "status={0}", entity.getStatus())
                .and(entity.getGiftType() != null, "gift_type={0}", entity.getGiftType());
        selectPage(page, entityWrapper);
        return page;
    }
}
