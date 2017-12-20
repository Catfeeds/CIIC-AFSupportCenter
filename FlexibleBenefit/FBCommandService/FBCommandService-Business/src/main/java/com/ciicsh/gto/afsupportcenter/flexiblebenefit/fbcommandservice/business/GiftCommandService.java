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
