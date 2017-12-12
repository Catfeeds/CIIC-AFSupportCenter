package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业社保账户信息表
 * 企业社保账户分类 : 大库（中智大库、外包库）、独立户 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsComAccountMapper extends BaseMapper<SsComAccount> {

    /**
     * 根据雇员任务 ID 查询 企业社保账户信息
     *
     * @param empTaskId
     * @return
     */
    SsComAccountDTO queryByEmpTaskId(@Param("empTaskId") String empTaskId);

    /**
     * 查询企业社保账户信息
     *
     * @param dto
     * @return
     */
    List<SsComAccount> accountQuery(SsComAccount dto);
}
