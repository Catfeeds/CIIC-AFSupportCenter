package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthChargeItem;
import com.ciicsh.gto.afsupportcenter.socjob.entity.bo.PaymentDetailBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 雇员月度费用明细项目 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsMonthChargeItemMapper extends BaseMapper<SsMonthChargeItem> {

    /**
     * 根据monthChargeId 删除雇员月度费用明细项目信息
     * @param monthChargeId 雇员月度费用明细ID
     * @return 返回删除影响的行数
     */
    Integer delByMonthChargeId(@Param("monthChargeId")long monthChargeId);

    /**
     * 根据monthChargeId 获取雇员月度费用明细项目信息
     * @param monthChargeId 雇员月度费用明细ID
     * @return 返回雇员月度费用明细项目信息
     */
    List<SsMonthChargeItem> getMonthChargeItemByMonthChargeId(@Param("monthChargeId")long monthChargeId);

    List<PaymentDetailBO> sumComAmountOrigInSsType(@Param("comAccountId") Long comAccountId, @Param("companyId") String companyId, @Param("ssMonth") String ssMonth, @Param("isLastYear") Integer isLastYear);
}
