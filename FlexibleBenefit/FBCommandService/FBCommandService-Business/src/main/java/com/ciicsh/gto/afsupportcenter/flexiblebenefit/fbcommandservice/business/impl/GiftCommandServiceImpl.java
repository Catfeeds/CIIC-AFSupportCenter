package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gt1.FileHandler;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao.GiftCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 礼品库 服务实现类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@Service
public class GiftCommandServiceImpl extends ServiceImpl<GiftCommandMapper, GiftPO> implements GiftCommandService {
    private static Logger logger = LoggerFactory.getLogger(GiftCommandServiceImpl.class);

    @Override
    public GiftPO findById(Integer id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateGift(GiftPO giftPO) {
        /**
         * 更新礼品数量为0时，自动下架
         */
        if (giftPO.getNumber() == 0) {
            giftPO.setStatus(1);
        }
        Integer t = baseMapper.updateByPrimaryKeySelective(giftPO);
        return null != t && t.intValue() >= 1;
    }

    @Override
    public String fileUpdate(InputStream stream) {
        String filepath = "";
        try {
            filepath = FileHandler.uploadFile(stream);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return filepath;
    }

    @Override
    public void deletePicture(String filePath) {
        FileHandler.deleteFile(filePath);
    }
}
