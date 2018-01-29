package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthChargeItemBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthChargeItem;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 雇员月度费用明细项目 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-23
 */
public interface SsMonthChargeItemService extends IService<SsMonthChargeItem> {
    /**
     * 查询雇员月度缴费明细
     * @param ssMonthChargeItemBO
     * @return
     */
    List<SsMonthChargeItemBO> queryEmlpyeeMonthFeeDetail(SsMonthChargeItemBO ssMonthChargeItemBO);
}
