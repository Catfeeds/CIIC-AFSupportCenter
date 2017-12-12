package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 企业社保账户信息表
企业社保账户分类 : 大库（中智大库、外包库）、独立户 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsComAccountMapper extends BaseMapper<SsComAccount> {
    public List<SsComAccountDTO> queryComAccount(SsComAccountDTO ssComAccountDTO);
}
