package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsComAccountParamDto;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public SsComAccountBO queryByEmpTaskId(String empTaskId,String type) {
        if("1".equals(type) || "2".equals(type)){
            //表示新进和转入
            return baseMapper.queryNewOrIntoByEmpTaskId(empTaskId);

        }else{
            //新进和转入之外
            return baseMapper.queryByEmpTaskId(empTaskId);
        }

    }

    @Override
    public PageRows<SsComAccountBO> accountQuery(PageInfo pageInfo) {
        SsComAccountBO dto = pageInfo.toJavaObject(SsComAccountBO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.accountQuery(dto));
    }
    /**
     * 查询企业社保管理详细信息
     * @param comAccountId
     */
    public SsComAccountBO querySocialSecurityManageInfo(String comAccountId){

        return baseMapper.querySocialSecurityManageInfo(comAccountId);
    }

    /**
     *  查询企业社保账户信息表
     * @param dto
     * @return
     */
    @Override
    public List<com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsComAccountDTO> getSsComAccountList(SsComAccountParamDto dto){
        return baseMapper.getSsComAccountList(dto);
    }
}
