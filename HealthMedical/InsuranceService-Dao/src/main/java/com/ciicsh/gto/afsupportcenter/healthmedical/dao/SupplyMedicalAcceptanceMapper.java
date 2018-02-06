package com.ciicsh.gto.afsupportcenter.healthmedical.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.AcceptanceStatisticsBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalAcceptanceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 补充医疗受理单表 Mapper 接口
 * </p>
 *
 * @author xiweizhen
 */
public interface SupplyMedicalAcceptanceMapper extends BaseMapper<SupplyMedicalAcceptance> {

    /**
     * 补充医疗分页查询
     *
     * @param page
     * @param supplyMedicalAcceptanceDTO
     * @return
     */
    List<SupplyMedicalAcceptance> queryAcceptancePage(Page<SupplyMedicalAcceptance> page, SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO);

    /**
     * 查询汇总信息
     *
     * @param supplyMedicalAcceptanceDTO
     * @return
     */
    AcceptanceStatisticsBO queryAcceptanceTotal(SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO);

    /**
     * 更新状态
     * @param acceptanceId
     * @param status
     * @param modifiedBy
     * @return
     */
    Integer updateStatus(@Param("acceptanceId") String acceptanceId, @Param("status") Integer status, @Param("modifiedBy") String modifiedBy);

    /**
     * 更新受理单状态为已同步
     * @param batchId
     * @param businessId
     * @param modifiedBy
     * @return
     */
    Integer syncStatus(@Param("batchId") Integer batchId, @Param("businessId") Integer businessId, @Param("status") Integer status, @Param("modifiedBy") String modifiedBy);

}
