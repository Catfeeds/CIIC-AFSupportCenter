package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankExportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankQueryBO;
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
    List<HfMonthChargeDiffBo> getHfMonthChargeDiffSum(HfMonthChargeBo hfMonthChargeBo);

    /**
     * 查询公积金报表
     *
     * @param pageInfo
     * @return
     */
    PageRows<HFMonthChargeReportBO> queryHfMonthChargeReport(PageInfo pageInfo, String userId);

    /**
     * 获取基本/补充公积金汇缴变更清册导出数据
     *
     * @param hfMonthChargeQueryBO
     * @param isPageByComAccount
     * @return
     */
    List<Map<String, Object>> getChgDetailsPageList(HFMonthChargeQueryBO hfMonthChargeQueryBO, boolean isPageByComAccount);

    /**
     * 获取基本/补充公积金补缴清册导出数据
     *
     * @param hfMonthChargeQueryBO
     * @param isPageByComAccount
     * @return
     */
    List<Map<String, Object>> getRepairDetailsPageList(HFMonthChargeQueryBO hfMonthChargeQueryBO, boolean isPageByComAccount);

    List<HFNetBankExportBO> queryNetBankData(HFNetBankQueryBO hfNetBankQueryBO);

    /**
     * 导出公积金汇缴支付详情报表
     *
     * @param pageInfo  导出条件
     * @return 导出结果
     */
    PageRows<HfPaymentAccountReportBo> getOperateDetailReport(PageInfo pageInfo, String userId);

    /**
     * 查询雇员转入或转出相关信息
     *
     * @param hfEmpTaskHandleBo
     */
    void getMonthChargeByInOut(HfEmpTaskHandleBo hfEmpTaskHandleBo);

    List<HfEmpLastPaymentBO> searchByLastPaymentMonth(String companyId, String employeeId, String hfMonth);
}
