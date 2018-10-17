package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfCalcSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HfCalcSettingMapper extends BaseMapper<HfCalcSetting> {

    List<HfCalcSetting> getShComSettingByMonth(@Param("hfType") Integer hfType, @Param("startMonth") String startMonth);
}
