package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsFileImportDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsFileImport;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface SsFileImportMapper extends BaseMapper<SsFileImport> {

    Long getMaxBatchIdByRelatedUnitId(SsFileImportDTO ssFileImportDTO);
}
