package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsEmpBaseDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 雇员社保汇缴基数明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司缴纳金额、个人缴纳金额 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsEmpBaseDetailMapper extends BaseMapper<SsEmpBaseDetail> {

    /**
     * 根据雇员社保汇缴基数Id获取雇员社保汇缴基数明信息
     * @param empBasePeriodId 雇员社保汇缴基数Id
     * @return 雇员社保汇缴基数明细信息
     */
    List<SsEmpBaseDetail> getEmpBaseDetailsByPeriodId(@Param("empBasePeriodId")long empBasePeriodId);
}
