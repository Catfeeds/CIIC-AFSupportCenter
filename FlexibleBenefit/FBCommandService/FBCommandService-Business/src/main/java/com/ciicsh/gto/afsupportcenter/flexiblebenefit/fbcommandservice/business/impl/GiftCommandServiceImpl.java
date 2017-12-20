package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gt1.FileHandler;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.GiftCommandMapper;
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
    @Override
    public String fileUpdate(InputStream stream) {
        String filepath = "";
        try {
            filepath = FileHandler.uploadFile(stream);
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
