package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpBasePeriod;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员正常汇缴社保的基数分段表(每段一个基数)， 每次社保基数变更（比如年调）或补缴都会更新这张表 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpBasePeriodMapper extends BaseMapper<SsEmpBasePeriod> {
    /**
     * 通过社保档案ID 查询 雇员汇缴信息
     * @param empArchiveId
     * @return
     */
    List<SsEmpBasePeriod> queryPeriodByEmpArchiveId(@Param("empArchiveId")String empArchiveId);

    void updateEndMonthById(SsEmpBasePeriod ssEmpBasePeriod);
}
