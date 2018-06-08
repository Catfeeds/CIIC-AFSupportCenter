package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthChargeItemBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthChargeItem;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 雇员月度费用明细项目 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-23
 */
@Mapper
@Component
public interface SsMonthChargeItemMapper extends BaseMapper<SsMonthChargeItem> {
    /**
     * 查询雇员月度缴费明细
     * @param ssMonthChargeItemBO
     * @return
     */
   List<SsMonthChargeItemBO> queryEmlpyeeMonthFeeDetail(SsMonthChargeItemBO ssMonthChargeItemBO);
   List<SsMonthEmpChangeDetailBO> searchYysDetailByComAccountIdAndSsMonth(@Param("comAccountId") Long comAccountId, @Param("ssMonth") String ssMonth);
}
