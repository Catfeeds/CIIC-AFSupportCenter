package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;

/**
 * <p>
 * 补充医疗受理单表 服务类
 * </p>
 *
 * @author xiweizhen
 */
public interface SupplyMedicalAcceptanceService extends IService<SupplyMedicalAcceptance> {
    /**
     * 定时同步智灵通受理单数据
     *
     * @return
     */
    boolean syncAcceptanceSummaryDetail();
}
