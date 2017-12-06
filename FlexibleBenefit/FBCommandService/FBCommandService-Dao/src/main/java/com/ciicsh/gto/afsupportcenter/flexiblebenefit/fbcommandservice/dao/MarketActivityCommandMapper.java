package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;

/**
 * @author xiweizhen
 * @date 2017/12/6 10:44
 */
public interface MarketActivityCommandMapper {
    /**
     * @param record
     * @return
     */
    int insertSelective(MarketActivityPO record);

    /**
     * @param id
     * @return
     */
    MarketActivityPO selectByPrimaryKey(Integer id);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MarketActivityPO record);

}