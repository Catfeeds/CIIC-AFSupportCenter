package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAdditionalFee;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAdditionalFeeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAdditionalFeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 一次性社保、滞纳金等纯金额的产品险种，与五险一金不同 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsAdditionalFeeServiceImpl extends ServiceImpl<SsAdditionalFeeMapper, SsAdditionalFee> implements SsAdditionalFeeService {

}
