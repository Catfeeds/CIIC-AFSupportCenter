package com.ciicsh.gto.afsupportcenter.fundjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfEmpMonthCharge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员月度汇缴明细库，每个雇员每一月份一条记录
当任务单状态为已办，?该表就应该有对应的明细数据，包含调整数据 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface HfEmpMonthChargeMapper extends BaseMapper<HfEmpMonthCharge> {

    //按照财务服务契约提供雇员级信息
    List<HfMonthChargeBO> getPaymentEmpListEnquireFinance(Map map);

    //根据接口返回的数据批量更新月度明细的支付状态
    void updateMonthCharge(Map map);

    //根据接口返回的数据批量更新客户的支付状态
    void updatePaymentCom(Map map);

    //查询 客户下有多少 不可付的记录
    Integer countByEmpPaymentStatus(Map map);

}
