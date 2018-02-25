package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gt1.FileHandler;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsFileImportDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsFileImport;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsFileImportMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsFileImportService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
                                   final int importType,
                                   final Long relatedUnitId,
                                   ImportParams importParams,
                                   IService<T> iTempService,
                                   MultiValueMap<String, MultipartFile> files,
                                   final String createdBy) throws Exception {
        if (isDelete) {
            // delete data from temporary table by last upload
            Map<String, Object> condition = new HashMap<>();
            condition.put(deleteDataKeyField, relatedUnitId);
            iTempService.deleteByMap(condition);
        }

        // read the imported file, and insert data into the temporary table, then merge to real table
        String storageFileId = null;

        Class<T> entityClass = (Class <T>)((ParameterizedType) iTempService.getClass().getSuperclass().getInterfaces()[0].getGenericInterfaces()[0]).getActualTypeArguments()[0];

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (Map.Entry<String, List<MultipartFile>> entry : files.entrySet()) {
            final List<MultipartFile> list = entry.getValue();

            for (MultipartFile multipartFile : list) {
                ExcelImportResult<T> result = ExcelImportUtil.importExcelMore(multipartFile.getInputStream(), entityClass, importParams);
                if (result.getList() != null && result.getList().size() > 0) {
                    iTempService.insertBatch(result.getList(), 3000);
                }

                if (result.getFailList() != null && result.getFailList().size() > 0) {
                    iTempService.insertBatch(result.getFailList(), 3000);
                }
            }

            executorService.execute(() -> {
                List<InputStream> inputStreams = new ArrayList<>();
                List<String> fileNames = new ArrayList<>();
                try {
                    for (MultipartFile multipartFile : list) {
                        inputStreams.add(multipartFile.getInputStream());
                        fileNames.add(multipartFile.getOriginalFilename());
                    }

                    List<String> urls = FileHandler.uploadInputStreams(inputStreams);
                    SsFileImportDTO ssFileImportDTO = new SsFileImportDTO();
                    ssFileImportDTO.setImportType(importType);
                    ssFileImportDTO.setRelatedUnitId(relatedUnitId);
                    reentrantLock.lock();
                    long importBatchId = baseMapper.getMaxBatchIdByRelatedUnitId(ssFileImportDTO) + 1;

                    for (int i = 0; i < fileNames.size(); i++) {
                        String url = urls.get(i);
                        url = url.substring(url.lastIndexOf('/') + 1);
                        insertFileImport(importType, relatedUnitId, importBatchId, storageFileId, url, fileNames.get(i), createdBy);
                    }
                    reentrantLock.unlock();
                } catch (IOException e) {
                    e.printStackTrace();
                    // TODO log
                } finally {
                    if (reentrantLock.isLocked()) {
                        reentrantLock.unlock();
                    }
                }
            });
        }
    }

    private void insertFileImport(int importType, Long annualAdjustCompanyId, Long importBatchId, String storageFileId, String url, String fileName, String createdBy) {
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
