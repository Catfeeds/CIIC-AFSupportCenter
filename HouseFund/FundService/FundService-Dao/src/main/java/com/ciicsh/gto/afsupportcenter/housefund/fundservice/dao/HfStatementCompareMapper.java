package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfStatementComparePO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 公积金对账主表 Mapper 接口
 * </p>
 */
public interface HfStatementCompareMapper extends BaseMapper<HfStatementComparePO> {

    /**
     * 根据筛选条件查询公积金对账单记录。
     * @param hfMonth
     * @param hfComAccount
     * @return
     */
    List<HFStatementCompareBO> getHFStatementCompareRecord(@Param("hfMonth") String hfMonth, @Param("hfComAccount") String hfComAccount);

}
