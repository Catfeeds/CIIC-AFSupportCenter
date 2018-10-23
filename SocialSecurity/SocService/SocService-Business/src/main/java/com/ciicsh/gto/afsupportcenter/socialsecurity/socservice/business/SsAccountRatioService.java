package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAccountRatioBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountRatio;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工伤比例 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsAccountRatioService extends IService<SsAccountRatio> {

    /**
     * 通过accountId 查询
     *
     * @param comAccountId
     * @return
     */
    List<SsAccountRatio> queryRatioByAccountId(@Param("comAccountId") String comAccountId);

    /**
     * 查询企业工伤比例
     *
     * @param companyId
     * @param date
     * @return
     */
    List<SsAccountRatioBO> getSsComRatioByDate(@Param("companyId") String companyId, @Param("date") String date);
}
