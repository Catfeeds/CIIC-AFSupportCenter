package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;

/**
 * <p>
 * 未投保医疗 服务类
 * </p>
 *
 * @author xiweizhen
 */
public interface UninsuredMedicalService extends IService<UninsuredMedical> {

    /**
     * 受理单分页查询
     *
     * @param page
     * @param uninsuredMedical
     * @return
     */
    Page<UninsuredMedical> queryAcceptanceList(Page<UninsuredMedical> page, UninsuredMedical uninsuredMedical);
}
