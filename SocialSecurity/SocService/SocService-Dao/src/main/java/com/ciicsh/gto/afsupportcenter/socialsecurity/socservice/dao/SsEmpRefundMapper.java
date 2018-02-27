package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpRefundBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpRefund;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 雇员社保退账受理表。
如果为某雇员多缴纳了社保金额，则需向社保局提出退账申请，退账申请受理后落地到此表。
                                                 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpRefundMapper extends BaseMapper<SsEmpRefund> {

    /**
     * 退费明细
     * @param ssEmpRefundBO
     * @return
     */
    List<SsEmpRefundBO> selectRefundDetail(SsEmpRefundBO ssEmpRefundBO);

}
