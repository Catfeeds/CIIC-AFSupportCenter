package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.baomidou.mybatisplus.service.IService;
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
     * @param comAccountId
     * @return
     */
    List<SsAccountRatio> queryRatioByAccountId(@Param("comAccountId") String comAccountId);
}
