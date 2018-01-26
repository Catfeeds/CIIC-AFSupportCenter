package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfComAccount;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfComAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfComAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业公积金账户：存储中智大库、中智外包、独立户企业的账号，含基本公积金和补充公积金
HF：House Fun 服务实现类
 * </p>
 */
@Service
public class HfComAccountServiceImpl extends ServiceImpl<HfComAccountMapper, HfComAccount> implements IHfComAccountService {

}
