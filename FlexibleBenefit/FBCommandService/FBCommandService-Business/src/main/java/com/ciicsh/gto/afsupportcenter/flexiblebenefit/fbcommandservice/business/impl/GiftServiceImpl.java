package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.impl;

import com.ciicsh.gt1.FileHandler;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao.GiftCommandMapper;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author xiweizhen
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Resource
    private GiftCommandMapper giftMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GiftPO findById(Integer id) {
        return giftMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertGift(GiftPO entity) {
        //修改方法
        if (entity.getId() != null) {
            return giftMapper.updateByPrimaryKeySelective(entity);
        } else {
            return giftMapper.insertSelective(entity);
        }
    }

    @Override
    public String fileUpdate(File file) {
        String filepath = "";
        try {
            filepath = FileHandler.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }

    @Override
    public void deletePicture(String filePath) {
        try {
            FileHandler.deleteFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
