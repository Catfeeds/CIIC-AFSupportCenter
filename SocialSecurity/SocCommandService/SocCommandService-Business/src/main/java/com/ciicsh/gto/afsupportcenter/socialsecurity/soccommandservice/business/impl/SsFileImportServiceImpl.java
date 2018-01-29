package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsFileImportDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsFileImport;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsFileImportMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsFileImportService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class SsFileImportServiceImpl extends ServiceImpl<SsFileImportMapper, SsFileImport> implements SsFileImportService<T> {

    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void executeExcelImport(boolean isDelete,
                                   String deleteDataKeyField,
                                   int importType,
                                   Long relatedUnitId,
                                   ImportParams importParams,
                                   IService<T> iTempService,
                                   MultiValueMap<String, MultipartFile> files) throws Exception {
        if (isDelete) {
            // delete data from temporary table by last upload
            Map<String, Object> condition = new HashMap<>();
            condition.put(deleteDataKeyField, relatedUnitId);
            iTempService.deleteByMap(condition);
        }

        // read the imported file, and insert data into the temporary table, then merge to real table
        SsFileImportDTO ssFileImportDTO = new SsFileImportDTO();
        ssFileImportDTO.setImportType(importType);
        ssFileImportDTO.setRelatedUnitId(relatedUnitId);
        Long importBatchId = 0L;
        String storageFileId = null;

        Class<T> entityClass = (Class <T>)((ParameterizedType) iTempService.getClass().getSuperclass().getInterfaces()[0].getGenericInterfaces()[0]).getActualTypeArguments()[0];
        try {
            for (Map.Entry<String, List<MultipartFile>> entry : files.entrySet()) {
                List<MultipartFile> list = entry.getValue();


                for (MultipartFile multipartFile : list) {
                    // TODO sync to file server, and return the file id.

                    ExcelImportResult<T> result = ExcelImportUtil.importExcelMore(multipartFile.getInputStream(), entityClass, importParams);
//                    if(result.isVerfiyFail()){
//                    }else{
//                    }
                    if (result.getList() != null && result.getList().size() > 0) {
                        iTempService.insertBatch(result.getList(), 3000);
                    }

                    reentrantLock.lock();
                    if (importBatchId == 0) {
                        importBatchId = baseMapper.getMaxBatchIdByRelatedUnitId(ssFileImportDTO) + 1;
                    }
                    insertFileImport(importType, relatedUnitId, importBatchId, storageFileId, multipartFile.getOriginalFilename(), "12");
                    reentrantLock.unlock();
                }
            }
        } finally {
            if (reentrantLock.isLocked()) {
                reentrantLock.unlock();
            }
        }
    }

    private void insertFileImport(int importType, Long annualAdjustCompanyId, Long importBatchId, String storageFileId, String fileName, String createdBy) {
        SsFileImport ssFileImport = new SsFileImport();
        ssFileImport.setImportType(importType);
        ssFileImport.setRelatedUnitId(annualAdjustCompanyId);
        ssFileImport.setImportBatchId(importBatchId);
        ssFileImport.setStorageFileId(storageFileId);
        ssFileImport.setFileName(fileName);
        ssFileImport.setCreatedBy(createdBy);
        this.insert(ssFileImport);
    }
}
