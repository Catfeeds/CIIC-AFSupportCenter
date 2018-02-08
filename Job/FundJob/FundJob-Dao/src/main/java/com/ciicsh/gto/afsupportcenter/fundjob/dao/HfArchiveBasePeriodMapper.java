package com.ciicsh.gto.afsupportcenter.fundjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfArchiveBasePeriod;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 雇员公积金汇缴月份段 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface HfArchiveBasePeriodMapper extends BaseMapper<HfArchiveBasePeriod> {

}
