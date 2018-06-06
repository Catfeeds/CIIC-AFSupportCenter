package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveAdvanceBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveAdvanceService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveUkeyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.advanceSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveAdvanceMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveDocSeqMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveUkeyMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveUkeyRenewMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveAdvance;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveDocSeq;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUkey;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUkeyRenew;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 档案预增表 服务实现类
 * </p>
 *
 * @author lyl
 * @since 2018-05-31
 */

@Service
public class AmArchiveUkeyServiceImpl extends ServiceImpl<AmArchiveUkeyMapper, AmArchiveUkey> implements IAmArchiveUkeyService {

    @Autowired
    private AmArchiveUkeyRenewMapper amArchiveUkeyRenewMapper;

    @Override
    public PageRows<AmArchiveUkeyBO> queryAmArchiveUkeyList(PageInfo pageInfo) {

        PageRows<AmArchiveUkeyBO> result = new PageRows<>();
        AmArchiveUkey pojo = pageInfo.toJavaObject(AmArchiveUkey.class);
        pojo.setIsActive(1);
        PageRows<AmArchiveUkey> resultPo = PageKit.doSelectPage(pageInfo, () -> baseMapper.selectList(new EntityWrapper<>(pojo)));
        result.setTotal(resultPo.getTotal());
        List<AmArchiveUkeyBO> boList = new ArrayList<>();
        for (AmArchiveUkey po : resultPo.getRows()) {
            AmArchiveUkeyBO bo = new AmArchiveUkeyBO();
            BeanUtils.copyProperties(po, bo);
            boList.add(bo);
        }
        result.setRows(boList);
        return result;
    }

    @Override
    public boolean deleteAmArchiveUkey(Long id) {
        AmArchiveUkey po = new AmArchiveUkey();
        po.setId(id);
        po.setModifiedBy(UserContext.getUserName());
        po.setModifiedTime(new Date());
        po.setIsActive(0);
        Integer result = baseMapper.updateById(po);
        return result > 0;
    }

    @Override
    public boolean saveAmArchiveUkey(AmArchiveUkeyBO amArchiveUkeyBO) {
        AmArchiveUkey po = new AmArchiveUkey();
        BeanUtils.copyProperties(amArchiveUkeyBO, po);
        Date nowTime = new Date();
        if(amArchiveUkeyBO.getId() == null){
            po.setCreatedBy(UserContext.getUserName());
            po.setCreatedTime(nowTime);
        }
        po.setModifiedBy(UserContext.getUserName());
        po.setModifiedTime(nowTime);
        boolean result = this.insertOrUpdate(po);
        return result;
    }

    @Override
    public boolean amArchiveUkeyRenew(Long keyId, String keyFee, String keyRenewFee) {
        AmArchiveUkeyRenew renew = new AmArchiveUkeyRenew();
        renew.setKeyId(keyId);
        renew.setKeyFee(keyFee);
        renew.setKeyRenewFee(keyRenewFee);
        Date nowDate = new Date();
        renew.setKeyRenewTime(nowDate);
        renew.setCreatedBy(UserContext.getUserName());
        renew.setCreatedTime(nowDate);
        renew.setModifiedBy(UserContext.getUserName());
        renew.setModifiedTime(nowDate);
        renew.setIsActive(1);
        return amArchiveUkeyRenewMapper.insert(renew) > 0;
    }


}
