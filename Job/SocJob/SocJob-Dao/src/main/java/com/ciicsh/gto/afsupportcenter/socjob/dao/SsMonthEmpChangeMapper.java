package com.ciicsh.gto.afsupportcenter.socjob.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthEmpChange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 雇员月度变更主表 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsMonthEmpChangeMapper extends BaseMapper<SsMonthEmpChange> {
    /**
     * 根据条件获取雇员月度变更信息
     * @param comAccountId 企业社保账户
     * @param ssMonth 实际社保缴纳发生月份
     * @return 月度缴费明细信息
     */
    List<SsMonthEmpChange> getMonthEmpChangesByCondition(@Param("comAccountId") long comAccountId, @Param("ssMonth") String ssMonth);

    /**
     * 根据条件删除雇员月度变更信息
     * @param comAccountId 企业社保账户
     * @param ssMonth 实际社保缴纳发生月份
     * @return 返回删除影响的行数
     */
    Integer delMonthEmpChangeByfCondition(@Param("comAccountId") long comAccountId, @Param("ssMonth") String ssMonth);
}
