package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpInfoBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAccountComRelationMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAccountRatioMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountComRelation;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ComAccountExtPO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.settlementcenter.invoicecommandservice.api.ComeAccountCommandProxy;
import com.ciicsh.gto.settlementcenter.invoicecommandservice.api.dto.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    SsEmpArchiveMapper ssEmpArchiveMapper;

    @Autowired
    SsAccountComRelationMapper ssAccountComRelationMapper;

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
     * @param ssAccount
     * @return
     */
    @Override
    public SsComAccountDTO getAccountById(String ssAccount,String companyId) {
        Map<String, Object> condition = new HashMap<>();
        Long comAccountId =0L;

        if(ssAccount==null || ssAccount=="" ){
            condition.put("company_id",companyId);
            SsAccountComRelation ssAccountComRelation = ssAccountComRelationMapper.selectByMap(condition).get(0);
            comAccountId = ssAccountComRelation.getComAccountId();
            condition.put("com_account_id",comAccountId);
            SsComAccount ssComAccount= baseMapper.selectByMap(condition).get(0);
            ssAccount=ssComAccount.getSsAccount();
        }
        condition=new HashMap<>();
        condition.put("ss_account",ssAccount);
       // condition.put("com_account_id",comAccountId);
        SsComAccount ssComAccount= baseMapper.selectByMap(condition).get(0);

        SsComAccountDTO ssComAccountDTO=new SsComAccountDTO();
        BeanUtils.copyProperties(ssComAccount,ssComAccountDTO);
        Map<String, Object>  wr = new HashMap<>();
        wr.put("com_account_id",ssComAccount.getComAccountId());
        List<SsAccountComRelation> list = ssAccountComRelationMapper.selectByMap(wr);
        String comId = "";
        for(SsAccountComRelation ssAccountComRelation :list){
            comId +=ssAccountComRelation.getCompanyId()+",";
        }
        comId = comId.substring(0,comId.length()-1);
        if(companyId!=null && companyId!=""){
            ssComAccountDTO.setCompanyId(companyId);
        }else {
            ssComAccountDTO.setCompanyId(comId);
        }

        return ssComAccountDTO;
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
    public boolean isExistAccountInfo(String companyId) {
        return baseMapper.isExistAccountInfo(companyId) > 0 ? true : false;
    }

    @Override
    public void addSerial(Long comAccountId) {
        baseMapper.addSerial(comAccountId);
    }

    @Override
    public Long getSerialByComAccountId(Long comAccountId) {
        return baseMapper.getSerialByComAccountId(comAccountId);
    }

    @Override
    public ComAccountExtPO getSsComAccountByComId(String companyId) {
        ComAccountExtPO comAccountExtPO =baseMapper.getSsComAccountByComId(companyId);
        return comAccountExtPO;
    }

    @Override
    public SsEmpInfoBO getSsEmpInfoById(String companyId, String employeeId) {
        SsEmpInfoBO ssEmpInfoBO =ssEmpArchiveMapper.getSsEmpInfoById(companyId,employeeId);
        return ssEmpInfoBO;
    }

}
