package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.ApplyRecordBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordPO;

/**
 * <p>
 * 申请记录表 服务类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface ApplyRecordQueryService extends IService<ApplyRecordPO> {

    /**
     * 自定义的分页查询，查询申请记录
     *
     * @param page
     * @param applyRecordBO
     * @return
     */
    Page<ApplyRecordBO> selectGiftList(Page<ApplyRecordBO> page, ApplyRecordBO applyRecordBO);
}
