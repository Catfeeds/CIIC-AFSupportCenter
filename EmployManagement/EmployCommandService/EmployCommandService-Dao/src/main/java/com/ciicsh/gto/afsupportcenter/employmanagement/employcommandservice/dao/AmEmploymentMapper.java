package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用工主表 Mapper 接口
 * </p>
 */
public interface AmEmploymentMapper extends BaseMapper<AmEmployment> {

     List<AmEmploymentBO> queryAmEmployment(AmEmploymentBO amEmploymentBO);

     AmEmploymentBO queryAmEmploymentById(@Param("amEmploymentId") String amEmploymentId);

     List<AmEmploymentBO>  queryAmArchive(AmEmploymentBO amEmploymentBO);

     List<AmEmploymentBO>  taskCountEmployee(AmEmploymentBO amEmploymentBO);

     List<AmEmploymentBO>  taskCountResign(AmEmploymentBO amEmploymentBO);


}
