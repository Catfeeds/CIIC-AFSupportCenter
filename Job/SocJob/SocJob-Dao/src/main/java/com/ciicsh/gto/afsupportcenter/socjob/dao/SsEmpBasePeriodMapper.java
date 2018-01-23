package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsEmpBaseArchiveExt;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsEmpBasePeriodExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 雇员正常汇缴社保的基数分段表(每段一个基数)， 每次社保基数变更（比如年调）或补缴都会更新这张表 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsEmpBasePeriodMapper extends BaseMapper<SsEmpBasePeriod> {
    /**
     * 获取雇员正常汇缴社保的扩展信息
     * @param comAccountId 企业社保账户
     * @param ssMonth 支付年月
     * @return 雇员正常汇缴社保的扩展信息
     */
    List<SsEmpBaseArchiveExt> getEmpBaseArchiveExts(@Param("comAccountId") long comAccountId, @Param("ssMonth") String ssMonth);

    /**
     * 获取雇员正常汇缴社保的基数分段扩展信息
     * @param comAccountId 企业社保账户
     * @param ssMonth 支付年月
     * @return 雇员正常汇缴社保的基数分段扩展信息
     */
    List<SsEmpBasePeriodExt> getEmpBasePeriodExts(@Param("comAccountId") long comAccountId, @Param("ssMonth") String ssMonth);
}
