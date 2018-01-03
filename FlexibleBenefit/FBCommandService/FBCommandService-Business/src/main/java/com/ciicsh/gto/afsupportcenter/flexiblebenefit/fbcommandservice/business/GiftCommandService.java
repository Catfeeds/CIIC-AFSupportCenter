package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;

import java.io.InputStream;

/**
 * <p>
 * 礼品库 服务类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface GiftCommandService extends IService<GiftPO> {
    GiftPO findById(Integer id);

    /**
     * 修改礼品数据,不使用插件的方法，不便于维护
     *
     * @param giftPO
     * @return
     */
    Boolean updateGift(GiftPO giftPO);

    /**
     * 上传图片方法
     *
     * @param stream
     * @return
     */
    String fileUpdate(InputStream stream);

    /**
     * 删除图片
     *
     * @param filePath
     */
    void deletePicture(String filePath);
}
