package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 工伤比例 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsAccountRatioMapper extends BaseMapper<SsAccountRatio> {

    public Integer updateEndMonthByAccId(SsAccountRatio ssAccountRatio);
}
