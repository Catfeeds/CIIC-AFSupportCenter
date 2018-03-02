package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ComAccountExtPO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.settlementcenter.invoicecommandservice.api.ComeAccountCommandProxy;
import com.ciicsh.gto.settlementcenter.invoicecommandservice.api.dto.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

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
public class SsComAccountServiceImpl extends ServiceImpl<SsComAccountMapper, SsComAccount> implements
    SsComAccountService {

    @Autowired
    ComeAccountCommandProxy comeAccountCommandProxy;

    @Override
    public SsComAccountBO queryByEmpTaskId(String empTaskId, String type) {
        if ("1".equals(type) || "2".equals(type)) {
            //表示新进和转入
            return baseMapper.queryNewOrIntoByEmpTaskId(empTaskId);

        } else {
            //新进和转入之外
            return baseMapper.queryByEmpTaskId(empTaskId);
        }

    }

    @Override
    public PageRows<SsComAccountBO> accountQuery(PageInfo pageInfo) {
        SsComAccountBO dto = pageInfo.toJavaObject(SsComAccountBO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.accountQuery(dto));
    }

    @Override
    public List<SsComAccountBO> getAccounts(SsComAccountBO accountBo) {
        return baseMapper.accountQuery(accountBo);
    }

    /**
     * 查询企业社保管理详细信息
     *
     * @param comAccountId
     */
    public SsComAccountBO querySocialSecurityManageInfo(String comAccountId) {

        return baseMapper.querySocialSecurityManageInfo(comAccountId);
    }


    /**
     * 根据企业社保账户ID查询企业社保信息
     * @param comAccountId
     * @return
     */
    @Override
    public SsComAccount getAccountById(Long comAccountId) {
        return baseMapper.selectById(comAccountId);
    }

    /**
     * 查询企业社保账户信息表
     * @param paramBO
     * @return
     */
    @Override
    public List<ComAccountExtPO> getSsComAccountList(ComAccountParamBO paramBO) {
        return baseMapper.getSsComAccountList(paramBO);
    }

    /**
     * 调用银行接口保存社保企业开户转入的银行信息
     *
     * @param map
     * @return
     */
    @Override
    public boolean addBankAccount(@RequestBody Map<String, Object> map) throws Exception {
        Long comAccountId = Long.parseLong(map.get("com_account_id").toString());
        JsonResult jr = comeAccountCommandProxy.addAccount(map);
        boolean res = false;

        //更新返回的银行账号ID
        SsComAccount ele = this.selectById(comAccountId);
        if (ele != null) {
            ele.setBankAccountId(jr.getBankAccountId().longValue());
            res = this.updateById(ele);
        }

        return res;
    }

    @Override
    public SsComAccount selectAccountByCompanyId(String companyId) {
        return baseMapper.selectAccountByCompanyId(companyId);
    }

}
