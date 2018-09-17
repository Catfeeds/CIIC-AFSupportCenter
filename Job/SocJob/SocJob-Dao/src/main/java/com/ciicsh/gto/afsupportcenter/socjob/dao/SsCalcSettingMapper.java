package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsCalcSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SsCalcSettingMapper extends BaseMapper<SsCalcSetting> {

    List<SsCalcSetting> getShComSettingByMonth(@Param("paymentPart") Integer paymentPart, @Param("startMonth") String startMonth);
}
