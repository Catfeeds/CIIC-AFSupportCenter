package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;



import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmInjuryBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmInjury;

import java.util.List;

/**
 * <p>
 * 档案工伤申报 Mapper 接口
 * </p>
 */
public interface AmInjuryMapper extends BaseMapper<AmInjury> {

    List<AmInjuryBO> queryAmInjury(AmInjuryBO amInjury);

    int  deleteAmInjury(Long injuryId);

}
