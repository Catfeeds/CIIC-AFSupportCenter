package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBaseDetail;

import java.util.List;

/**
 * <p>
 * 雇员社保汇缴基数明细表，
 * 该表细化到每一个社保险种的月度段的基数、比例、公司缴纳金额、个人缴纳金额 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpBaseDetailService extends IService<SsEmpBaseDetail> {

    /**
     * 保存，根据雇员社保汇缴基数明细
     *
     * @param details
     * @param detail
     */
    void saveForSsEmpBaseDetail(List<SsEmpBaseDetail> details, SsEmpBaseDetail detail);
}
