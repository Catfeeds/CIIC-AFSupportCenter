package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpWithdraw;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpWithdrawMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpWithdrawService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 记录本地社保和全国委托社保中，向社保局提取雇员社保金额的业务记录，这是一种特殊业务。 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpWithdrawServiceImpl extends ServiceImpl<SsEmpWithdrawMapper, SsEmpWithdraw> implements ISsEmpWithdrawService {

}
