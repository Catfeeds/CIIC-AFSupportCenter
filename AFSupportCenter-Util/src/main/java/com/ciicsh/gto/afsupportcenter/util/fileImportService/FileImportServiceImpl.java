package com.ciicsh.gto.afsupportcenter.util.fileImportService;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gt1.FileHandler;
import com.ciicsh.gto.afsupportcenter.util.logService.LogContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
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
public abstract class FileImportServiceImpl<M extends BaseMapper<E>, E> extends ServiceImpl<M, E> implements FileImportService<T, E> {
    @Autowired
    private LogService logService;
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void executeExcelImport(final int importType,
                                   final Long relatedUnitId,
                                   IService<T> iService,
                                   ImportService<T> importService,
                                   List<T> successList,
                                   List<T> failList,
                                   ImportParams importParams,
                                   MultiValueMap<String, MultipartFile> files,
                                   final String createdBy) throws Exception {
        // read the imported file, and insert data into the table
        String storageFileId = null;
        Class<T> entityClass;

        if (iService != null) {
            entityClass = (Class<T>) ((ParameterizedType) iService.getClass().getSuperclass().getInterfaces()[0].getGenericInterfaces()[0]).getActualTypeArguments()[0];
        } else {
            entityClass = (Class<T>) ((ParameterizedType) importService.getClass().getSuperclass().getInterfaces()[0].getGenericInterfaces()[0]).getActualTypeArguments()[0];
        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (Map.Entry<String, List<MultipartFile>> entry : files.entrySet()) {
            final List<MultipartFile> list = entry.getValue();
            List<T> subSuccessList = new ArrayList<>();
            List<T> subFailList = new ArrayList<>();

            for (MultipartFile multipartFile : list) {
                ExcelImportResult<T> result = ExcelImportUtil.importExcelMore(multipartFile.getInputStream(), entityClass, importParams);
                if (result.getList() != null && result.getList().size() > 0) {
                    subSuccessList.addAll(result.getList());
                    handleSingleSuccessList(iService, importService, subSuccessList);
                }

                if (result.getFailList() != null && result.getFailList().size() > 0) {
                    subFailList.addAll(result.getFailList());
                    handleSingleFailList(iService, importService, subFailList);
                }
            }

            if (successList != null) {
                successList.addAll(subSuccessList);
            }

            if (failList != null) {
                failList.addAll(subFailList);
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

                    reentrantLock.lock();
                    long importBatchId = getLastImportBatchId(importType, relatedUnitId) + 1;

                    for (int i = 0; i < fileNames.size(); i++) {
                        String url = urls.get(i);
                        url = url.substring(url.lastIndexOf('/') + 1);
                        insertFileImport(importType, relatedUnitId, importBatchId, storageFileId, url, fileNames.get(i), createdBy);
                    }
                    reentrantLock.unlock();
                } catch (IOException e) {
                    LogContext logContext = LogContext.of().setTitle("文件上传")
                        .setTextContent("上传文件到文件服务器并新增上传文件记录表时异常")
                        .setExceptionContent(e);
                    logService.error(logContext);
                } finally {
                    if (reentrantLock.isLocked()) {
                        reentrantLock.unlock();
                    }
                }
            });
        }
    }

    @Override
    public void deleteExistData(IService<T> iService, Map<String, Object> condition) {
        iService.deleteByMap(condition);
    }

    protected void handleSingleSuccessList(IService<T> iService, ImportService<T> importService, List<T> successList) {
        iService.insertBatch(successList, 3000);
    }

    protected void handleSingleFailList(IService<T> iService, ImportService<T> importService, List<T> failList) {
//        iService.insertBatch(failList, 3000);
    }

    protected abstract long getLastImportBatchId(int importType, Long relatedUnitId);

    protected abstract void insertFileImport(int importType, Long annualAdjustCompanyId, Long importBatchId, String storageFileId, String url, String fileName, String createdBy);
}
