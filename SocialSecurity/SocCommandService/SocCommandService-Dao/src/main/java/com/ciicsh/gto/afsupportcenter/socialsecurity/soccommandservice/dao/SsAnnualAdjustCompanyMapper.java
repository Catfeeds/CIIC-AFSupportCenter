package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustCompany;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  年调客户信息Mapper接口
 * </p>
 */
public interface SsAnnualAdjustCompanyMapper extends BaseMapper<SsAnnualAdjustCompany> {

    /**
     * 查询年调客户信息（根据社保账户类型，账户编号，客户编号查询）
     * @param ssAnnualAdjustCompanyDTO
     * @return
     */
    List<SsAnnualAdjustCompany> queryAnnualAdjustCompany(SsAnnualAdjustCompanyDTO ssAnnualAdjustCompanyDTO);

    /**
     * 根据社保账户ID更新年调客户信息的社保账户单位平均工资相关部分
     * @param ssAnnualAdjustCompany
     */
    void updateAnnualAdjustCompanysByComAccountId(SsAnnualAdjustCompany ssAnnualAdjustCompany);
}
