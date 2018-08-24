package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.EmployeeBatchBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.archiveSearchExportOpt;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工主表 Mapper 接口
 * </p>
 */
public interface AmEmploymentMapper extends BaseMapper<AmEmployment> {

     List<AmEmploymentBO> queryAmEmployment(Map<String, Object> param);

     List<AmEmploymentBO>  queryAmArchive(AmEmploymentBO amEmploymentBO);

     List<AmEmploymentBO>  taskCountEmployee(AmEmploymentBO amEmploymentBO);

     List<AmEmploymentBO>  taskCountResign(AmEmploymentBO amEmploymentBO);

     List<archiveSearchExportOpt>  queryAmArchiveList(AmEmploymentBO amEmploymentBO);

    List<AmEmploymentBO> queryAmEmploymentResign(Map<String, Object> param);

    List<AmEmploymentBO>  queryAmArchiveResign(AmEmploymentBO amEmploymentBO);

    List<AmEmploymentBO> queryAmEmploymentBatch(EmployeeBatchBO employeeBatchBO);

    List<AmEmploymentBO>   queryArchiveTaskCount(AmEmploymentBO amEmploymentBO);

    List<AmEmploymentBO>   queryTaskCount(AmEmploymentBO amEmploymentBO);

}
