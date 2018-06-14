package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankExportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankQueryBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 雇员月度汇缴明细库，每个雇员每一月份一条记录
当任务单状态为已办，?该表就应该有对应的明细数据，包含调整数据 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface HfMonthChargeMapper extends BaseMapper<HfMonthCharge> {

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
     * 查询雇员月度汇缴明细库
     *
     * @param hfMonthChargeQueryBO
     * @return
     */
    List<HFMonthChargeReportBO> queryHfMonthChargeReport(HFMonthChargeQueryBO hfMonthChargeQueryBO);

    List<HFNetBankExportBO> queryNetBankData(HFNetBankQueryBO hfNetBankQueryBO);
    /**
     * 导出公积金汇缴支付详情报表
     *
     * @param hfPaymentAccountBo 查询条件
     * @return 导出结果
     */
    List<HfPaymentAccountReportBo> getOperateDetailReport(HfPaymentAccountBo hfPaymentAccountBo);

    /**
     * 查询雇员转入或转出汇缴明细
     *
     * @param hfMonthChargeBo
     * @return
     */
    HfMonthChargeBo getMonthChargeByIn(HfMonthChargeBo hfMonthChargeBo);
    HfMonthChargeBo getMonthChargeByOut(HfMonthChargeBo hfMonthChargeBo);
    HfMonthChargeBo getMonthChargeByInOut(HfMonthChargeBo hfMonthChargeBo);
}
