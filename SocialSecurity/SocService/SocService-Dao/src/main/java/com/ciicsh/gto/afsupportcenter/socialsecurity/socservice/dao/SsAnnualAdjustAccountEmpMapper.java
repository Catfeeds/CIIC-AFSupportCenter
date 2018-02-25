package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAnnualAdjustAccountEmpBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountEmpDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustAccountEmp;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface SsAnnualAdjustAccountEmpMapper extends BaseMapper<SsAnnualAdjustAccountEmp> {

    /**
     * 将年调社保账户雇员临时表中没有错误信息的数据插入正式表
     * @param ssAnnualAdjustAccountEmpTempDTO
     */
    void insertDataWithoutErrorMsg(SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO);

    /**
     * 查询年调社保账户雇员信息
     * @param ssAnnualAdjustAccountEmpDTO
     * @return
     */
    List<SsAnnualAdjustAccountEmpBO> queryAnnualAdjustAccountEmp(SsAnnualAdjustAccountEmpDTO ssAnnualAdjustAccountEmpDTO);

}
