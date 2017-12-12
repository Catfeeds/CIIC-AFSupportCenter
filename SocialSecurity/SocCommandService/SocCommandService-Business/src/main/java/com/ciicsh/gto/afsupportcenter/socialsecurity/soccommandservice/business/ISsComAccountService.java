package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

/**
 * <p>
 * 企业社保账户信息表
企业社保账户分类 : 大库（中智大库、外包库）、独立户 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface ISsComAccountService extends IService<SsComAccount> {
    /**
     * 获得企业社保账户列表
     * linhui
     * @return
     */
    public PageRows<SsComAccountDTO> queryComAccount(PageInfo pageInfo);
}
