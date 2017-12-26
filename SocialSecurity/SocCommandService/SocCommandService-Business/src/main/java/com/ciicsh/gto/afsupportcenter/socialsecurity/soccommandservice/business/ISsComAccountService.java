package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

/**
 * <p>
 * 企业社保账户信息表
 * 企业社保账户分类 : 大库（中智大库、外包库）、独立户 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface ISsComAccountService extends IService<SsComAccount> {

    /**
     * 根据雇员任务 ID 查询 企业社保账户信息
     *
     * @param empTaskId
     * @return
     */
    SsComAccountBO queryByEmpTaskId(String empTaskId);

    /**
     * 查询企业社保账户信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<SsComAccountBO> accountQuery(PageInfo pageInfo);

    /**
     * 查询企业社保管理详细信息
     * @param comAccountId
     */
    SsComAccountBO querySocialSecurityManageInfo(String comAccountId);
}
