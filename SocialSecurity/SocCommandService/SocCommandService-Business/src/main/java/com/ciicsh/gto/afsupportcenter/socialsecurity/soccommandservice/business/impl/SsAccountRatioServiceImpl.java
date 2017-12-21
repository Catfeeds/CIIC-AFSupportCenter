package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsAccountRatioMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAccountRatioService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 工伤比例 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsAccountRatioServiceImpl extends ServiceImpl<SsAccountRatioMapper, SsAccountRatio> implements ISsAccountRatioService {
    /**
     * 通过accountId 查询
     * @param comAccountId
     * @return
     */
    public List<SsAccountRatio> queryRatioByAccountId(@Param("comAccountId") String comAccountId){

        return baseMapper.queryRatioByAccountId(comAccountId);
    }
}
