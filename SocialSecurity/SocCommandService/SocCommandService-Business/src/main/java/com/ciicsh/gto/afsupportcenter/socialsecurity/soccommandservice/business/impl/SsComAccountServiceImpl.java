package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业社保账户信息表
 * 企业社保账户分类 : 大库（中智大库、外包库）、独立户 服务实现类
 * </p>
 *
 * @author linhui
 * @since 2017-12-06
 */
@Service
public class SsComAccountServiceImpl extends ServiceImpl<SsComAccountMapper, SsComAccount> implements ISsComAccountService {

    @Override
    public SsComAccountDTO queryByEmpTaskId(String empTaskId) {
        return baseMapper.queryByEmpTaskId(empTaskId);
    }

    @Override
    public PageRows<SsComAccountDTO> accountQuery(PageInfo pageInfo) {
        SsComAccountDTO dto = pageInfo.toJavaObject(SsComAccountDTO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.accountQuery(dto));
    }
}
