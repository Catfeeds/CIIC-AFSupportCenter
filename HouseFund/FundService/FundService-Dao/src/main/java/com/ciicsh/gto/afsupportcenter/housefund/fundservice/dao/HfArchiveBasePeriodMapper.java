package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfArchiveBasePeriodUpdateBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
     * @param hfArchiveBasePeriodUpdateBo
     * @return
     */
    int updateHfArchiveBasePeriods(HfArchiveBasePeriodUpdateBo hfArchiveBasePeriodUpdateBo);


    List<HfArchiveBasePeriod> getArchiveBasePeriods(@Param("hfType") Integer hfType,@Param("comAccountId") Long comAccountId,@Param("paymentMonth")String paymentMonth,@Param("belongMonth")String belongMonth);

    void updateEndMonAndHandleMon(HfArchiveBasePeriod hfArchiveBasePeriod);
}
