package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFMonthChargeQueryBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFMonthChargeReportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfMonthChargeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfMonthChargeDiffBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;
import java.util.Map;

public interface HfMonthChargeService extends IService<HfMonthCharge> {
    /**
     * 更新雇员月度汇缴明细库记录
     *
     * @param hfMonthChargeBo
     * @return
     */
    int updateHfMonthCharge(HfMonthChargeBo hfMonthChargeBo);

    /**
     * 根据任务单ID删除雇员月度汇缴明细库记录
     * @param empTaskIdList
     * @return
     */
    int deleteHfMonthCharges(List<Long> empTaskIdList);

    /**
     * 获取当前客户当前雇员某公积金类型，某客户汇缴月之前所有的补缴合计
     *
     * @param hfMonthChargeBo
     * @return
     */
    HfMonthChargeDiffBo getHfMonthChargeDiffSum(HfMonthChargeBo hfMonthChargeBo);

    /**
     * 查询公积金报表
     *
     * @param pageInfo
     * @return
     */
    PageRows<HFMonthChargeReportBO> queryHfMonthChargeReport(PageInfo pageInfo);

    /**
     * 获取基本/补充公积金汇缴变更清册导出数据
     *
     * @param hfMonthChargeQueryBO
     * @param hfType
     * @return
     */
    List<Map<String, Object>> getChgDetailsPageList(HFMonthChargeQueryBO hfMonthChargeQueryBO, Integer hfType);
}
