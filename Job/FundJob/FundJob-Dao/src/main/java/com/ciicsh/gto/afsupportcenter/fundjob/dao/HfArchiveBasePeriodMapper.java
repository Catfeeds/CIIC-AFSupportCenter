package com.ciicsh.gto.afsupportcenter.fundjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfArchiveBasePeriodUpdateBO;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfArchiveBasePeriod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 雇员公积金汇缴月份段 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface HfArchiveBasePeriodMapper extends BaseMapper<HfArchiveBasePeriod> {
    /**
     * 根据任务单ID删除雇员公积金汇缴月份段记录
     *
     * @param empTaskIdList
     * @return
     */
    int deleteHfArchiveBasePeriods(List<Long> empTaskIdList);

    /**
     * 更新雇员公积金汇缴月份段记录
     * @param hfArchiveBasePeriodUpdateBO
     * @return
     */
    int updateHfArchiveBasePeriods(HfArchiveBasePeriodUpdateBO hfArchiveBasePeriodUpdateBO);


    List<HfArchiveBasePeriod> getArchiveBasePeriods(@Param("hfType") Integer hfType, @Param("comAccountId") Long comAccountId, @Param("paymentMonth")String paymentMonth, @Param("belongMonth")String belongMonth);
}
