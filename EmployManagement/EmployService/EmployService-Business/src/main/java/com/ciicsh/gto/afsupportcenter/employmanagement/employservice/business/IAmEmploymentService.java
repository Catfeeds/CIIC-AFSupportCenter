package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.archiveSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmArchiveReturnPrintDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmReturnListExportDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工主表 服务类
 * </p>
 */
public interface IAmEmploymentService extends IService<AmEmployment> {

    List<AmEmploymentBO> queryAmEmployment(Map<String, Object> param);

    PageRows<AmEmploymentBO> queryAmArchive(PageInfo pageInfo);

    List<AmArchiveReturnPrintDTO> queryAmArchiveForeignerPritDate(PageInfo pageInfo);

    List<AmEmploymentBO> taskCountEmployee(PageInfo pageInfo);

    List<AmEmploymentBO> taskCountResign(PageInfo pageInfo);

    List<archiveSearchExportOpt>  queryAmArchiveList(AmEmploymentBO amEmploymentBO);

    List<AmReturnListExportDTO>  queryAmReturnList(AmEmploymentBO amEmploymentBO);

    List<AmEmploymentBO> queryAmEmploymentResign(Map<String, Object> param);

    List<AmEmploymentBO> queryAmEmploymentBatch(List<Long> empTaskIds);

}
