package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountParamDTO;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
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
     * 根据雇员任务 ID 查询 企业社保账户信息（新进和转入之外）
     *
     * @param empTaskId
     * @return
     */
    SsComAccountBO queryByEmpTaskId(@Param("empTaskId") String empTaskId);

    /**
     * 根据雇员任务 ID 查询 企业社保账户信息(新进和转入)
     *
     * @param empTaskId
     * @return
     */
    SsComAccountBO queryNewOrIntoByEmpTaskId(@Param("empTaskId") String empTaskId);

    /**
     * 查询企业社保账户信息
     *
     * @param dto
     * @return
     */
    List<SsComAccountBO> accountQuery(SsComAccountBO dto);

    /**
     * 查询企业社保管理详细信息
     *
     * @param comAccountId
     */
    SsComAccountBO querySocialSecurityManageInfo(@Param("comAccountId") String comAccountId);

    /**
     * 查询企业社保账户信息 接口用
     *
     * @param dto
     * @return
     */
    List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO> getSsComAccountList(SsComAccountParamDTO dto);

    int checkComAccountDuplicateaSSAccount(SsComAccount ssComAccount);
    int checkComAccountDuplicateaSSAccountName(SsComAccount ssComAccount);
}
