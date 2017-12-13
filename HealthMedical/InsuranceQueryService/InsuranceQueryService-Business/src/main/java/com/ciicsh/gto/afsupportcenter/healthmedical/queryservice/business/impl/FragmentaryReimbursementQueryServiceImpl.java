package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.FragmentaryReimbursementMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.FragmentaryReimbursementQueryService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.math.BigDecimal;

/**
 * <p>
 * 零星报销表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Service
@Transactional
public class FragmentaryReimbursementQueryServiceImpl extends ServiceImpl<FragmentaryReimbursementMapper, FragmentaryReimbursementPO> implements FragmentaryReimbursementQueryService {

    @Autowired
    private FragmentaryReimbursementMapper fragmentaryReimbursementMapper;

    @Override
    public FragmentaryReimbursementPO getById(String id) {
        return fragmentaryReimbursementMapper.getById(id);
    }

    @Override
    public PageRows<FragmentaryReimbursementPO> getEntityList(PageInfo pageInfo) {

        FragmentaryReimbursementPO po = pageInfo.toJavaObject(FragmentaryReimbursementPO.class);
        PageRows<FragmentaryReimbursementPO> pageRow = PageKit.doSelectPage(pageInfo, () -> baseMapper.fragmentaryReimbursementMapperQuery(po) );
        return pageRow;

    }

}

