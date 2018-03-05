package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.archiveSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工主表 服务类
 * </p>
 */
public interface IAmEmploymentService extends IService<AmEmployment> {

    List<AmEmploymentBO> queryAmEmployment(Map<String,Object> param);

    PageRows<AmEmploymentBO> queryAmArchive(PageInfo pageInfo);

    List<AmEmploymentBO> taskCountEmployee(PageInfo pageInfo);

    List<AmEmploymentBO> taskCountResign(PageInfo pageInfo);

    List<archiveSearchExportOpt>  queryAmArchiveList(AmEmploymentBO amEmploymentBO);

}
