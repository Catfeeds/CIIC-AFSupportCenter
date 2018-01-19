package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsAccountComExt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 企业社保账户信息表
 * 企业社保账户分类 : 大库（中智大库、外包库）、独立户 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsComAccountMapper extends BaseMapper<SsComAccount> {

    /**
     * 获取企业账户扩展信息
     * @return
     */
    List<SsAccountComExt> getSsComAccounts();
}
