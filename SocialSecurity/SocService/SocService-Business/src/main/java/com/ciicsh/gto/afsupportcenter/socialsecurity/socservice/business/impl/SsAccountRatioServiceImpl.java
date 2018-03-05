package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAccountRatioMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAccountRatioService;
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
public class SsAccountRatioServiceImpl extends ServiceImpl<SsAccountRatioMapper, SsAccountRatio> implements SsAccountRatioService {
    /**
     * 通过accountId 查询
     * @param comAccountId
     * @return
     */
    public List<SsAccountRatio> queryRatioByAccountId(@Param("comAccountId") String comAccountId){

        return baseMapper.queryRatioByAccountId(comAccountId);
    }
}
