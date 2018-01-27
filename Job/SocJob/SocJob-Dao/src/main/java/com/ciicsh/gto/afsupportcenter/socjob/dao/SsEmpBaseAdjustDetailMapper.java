package com.ciicsh.gto.afsupportcenter.socjob.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsEmpBaseAdjustDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 雇员社保基数调整记录明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司金额、个人金额、差额（与Em Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsEmpBaseAdjustDetailMapper extends BaseMapper<SsEmpBaseAdjustDetail> {
    /**
     * 获取雇员社保基数调整记录明细信息
     * @param empBaseAdjustId
     * @return 返回雇员社保基数调整记录明细信息
     */
    List<SsEmpBaseAdjustDetail> getEmpBaseAdjustDetailByBaseAdjustId(@Param("empBaseAdjustId") long empBaseAdjustId);
}
