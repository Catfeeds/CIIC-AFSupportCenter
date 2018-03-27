package com.ciicsh.gto.afsupportcenter.util.fileImportService;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 */
public interface FileImportService<T, E> extends IService<E> {
    Long DEFAULT_RELATED_UNIT_ID = 0L;

    void deleteExistData(IService<T> iService, Map<String, Object> condition);

    void executeExcelImport(int importType,
                            Long relatedUnitId,
                            IService<T> iService,
                            ImportService<T> importService,
                            List<T> successList,
                            List<T> failList,
                            ImportParams importParams,
                            MultiValueMap<String, MultipartFile> files,
                            final String createdBy) throws Exception;
}
