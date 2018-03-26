package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfFileImportService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.SsFileImportMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.SsFileImport;
import com.ciicsh.gto.afsupportcenter.util.fileImportService.FileImportDTO;
import com.ciicsh.gto.afsupportcenter.util.fileImportService.FileImportServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.fileImportService.ImportService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HfFileImportServiceImpl extends FileImportServiceImpl<SsFileImportMapper, SsFileImport> implements HfFileImportService<T> {
    @Override
    protected void handleSingleFailList(IService<T> iService, ImportService<T> importService, List<T> failList) {
        importService.handleDataList(failList);
    }

    @Override
    protected long getLastImportBatchId(int importType, Long relatedUnitId) {
        FileImportDTO fileImportDTO = new FileImportDTO();
        fileImportDTO.setImportType(importType);
        fileImportDTO.setRelatedUnitId(relatedUnitId);
        return baseMapper.getMaxBatchIdByRelatedUnitId(fileImportDTO);
    }

    @Override
    protected void insertFileImport(int importType, Long annualAdjustCompanyId, Long importBatchId, String storageFileId, String url, String fileName, String createdBy) {
        SsFileImport ssFileImport = new SsFileImport();
        ssFileImport.setImportType(importType);
        ssFileImport.setRelatedUnitId(annualAdjustCompanyId);
        ssFileImport.setImportBatchId(importBatchId);
        ssFileImport.setStorageFileId(storageFileId);
        ssFileImport.setStorageFileUrl(url);
        ssFileImport.setFileName(fileName);
        ssFileImport.setCreatedBy(createdBy);
        this.insert(ssFileImport);
    }
}
