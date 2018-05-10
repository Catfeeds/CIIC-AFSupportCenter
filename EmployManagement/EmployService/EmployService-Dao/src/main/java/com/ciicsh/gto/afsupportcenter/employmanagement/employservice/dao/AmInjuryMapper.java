package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;



import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmInjuryBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmInjury;

import java.util.List;

/**
 * <p>
 * 档案工伤申报 Mapper 接口
 * </p>
 */
public interface AmInjuryMapper extends BaseMapper<AmInjury> {

    List<AmInjuryBO> queryAmInjury(AmInjuryBO amInjury);

}
