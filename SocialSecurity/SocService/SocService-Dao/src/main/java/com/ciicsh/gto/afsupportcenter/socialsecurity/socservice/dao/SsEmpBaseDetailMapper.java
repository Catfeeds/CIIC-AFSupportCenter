package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBaseDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 雇员社保汇缴基数明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司缴纳金额、个人缴纳金额 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpBaseDetailMapper extends BaseMapper<SsEmpBaseDetail> {

}
