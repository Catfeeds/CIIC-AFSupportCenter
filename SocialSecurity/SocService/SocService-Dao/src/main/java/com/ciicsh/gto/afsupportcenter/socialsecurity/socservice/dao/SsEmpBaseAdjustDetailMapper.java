package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBaseAdjustDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 雇员社保基数调整记录明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司金额、个人金额、差额（与Em Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpBaseAdjustDetailMapper extends BaseMapper<SsEmpBaseAdjustDetail> {

}
