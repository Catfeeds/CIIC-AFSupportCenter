package com.ciicsh.gto.afsupportcenter.socjob.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthEmpChangeDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 雇员月度变更表明细
    该表结果有可能需要调整 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsMonthEmpChangeDetailMapper extends BaseMapper<SsMonthEmpChangeDetail> {
    /**
     * 根据 monthEmpChangeId 删除雇员月度变更表明细
     * @param monthEmpChangeId 雇员月度变更主表ID
     * @return 返回删除影响的行数
     */
    Integer delByMonthEmpChangeId(@Param("monthEmpChangeId")long monthEmpChangeId);
}
