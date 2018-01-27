package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsFileImport;
import com.baomidou.mybatisplus.service.IService;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;



/**
 * <p>
 *  服务类
 * </p>
 */
public interface ISsFileImportService<T> extends IService<SsFileImport> {

    void executeExcelImport(boolean isDelete,
                            String deleteDataKeyField,
                            int importType,
                            Long relatedUnitId,
                            ImportParams importParams,
                            IService<T> iService,
                            MultiValueMap<String, MultipartFile> files) throws Exception;
}
