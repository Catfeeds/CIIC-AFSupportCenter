package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthCharge;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsMonthChargeExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成， Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsMonthChargeMapper extends BaseMapper<SsMonthCharge> {

    /**
     * 判断月度缴费明细是否首次生成
     * @param comAccountId 企业社保账户
     * @param ssMonth 实际社保缴纳发生月份
     * @return 是否是首次生成
     */
    Integer ifFirstCreate(@Param("comAccountId") long comAccountId,@Param("ssMonth") String ssMonth);


    /**
     * 根据条件获取月度缴费明细信息
     * @param comAccountId 企业社保账户
     * @param ssMonth 实际社保缴纳发生月份
     * @return 月度缴费明细信息
     */
    List<SsMonthCharge> getMonthChangesByCondition(@Param("comAccountId") long comAccountId,@Param("ssMonth") String ssMonth,@Param("costCategory") Integer costCategory);


    /**
     * 根据条件获取月度缴费明细非标准扩展信息
     * @param comAccountId 企业社保账户
     * @param ssMonth 实际社保缴纳发生月份
     * @return 月度缴费明细非标准扩展信息
     */
    List<SsMonthChargeExt> getSsMonthChargeExts(@Param("comAccountId") long comAccountId, @Param("ssMonth") String ssMonth);


    /**
     * 根据条件删除月度缴费明细信息
     * @param comAccountId 企业社保账户
     * @param ssMonth 实际社保缴纳发生月份
     * @return 返回删除影响的行数
     */
    Integer delByCondition(@Param("comAccountId") long comAccountId,@Param("ssMonth") String ssMonth,@Param("costCategory") Integer costCategory);
}
