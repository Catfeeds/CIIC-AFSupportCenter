package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAccountRatioBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountRatio;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 通过accountId 查询
     * @param comAccountId
     * @return
     */
    List<SsAccountRatio> queryRatioByAccountId(@Param("comAccountId") String comAccountId);

    /**
     * 获取企业工伤比例
     * @param companyId
     * @param date
     * @return
     */
    List<SsAccountRatioBO> getSsComRatioByDate(@Param("companyId") String companyId, @Param("date") String date);
}
