package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsFileImport;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.util.fileImportService.FileImportDTO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface SsFileImportMapper extends BaseMapper<SsFileImport> {

    Long getMaxBatchIdByRelatedUnitId(FileImportDTO fileImportDTO);
}
