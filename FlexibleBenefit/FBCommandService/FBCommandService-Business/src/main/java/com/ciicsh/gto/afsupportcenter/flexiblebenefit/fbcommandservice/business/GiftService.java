package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;

import java.io.File;

/**
 * @author created by xiweizhen 2017-11-16 14:02:26
 */
public interface GiftService {

    /**
     * 根据主键查询礼品数据
     *
     * @param id
     * @return GiftDTO
     */
    GiftPO findById(Integer id);

    /**
     * 新增礼品
     *
     * @param entity
     * @return int
     */
    int insertGift(GiftPO entity);

    /**
     * 上传文件方法
     * @param file
     * @return String
     */
    String fileUpdate(File file);
}
