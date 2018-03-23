package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsFileImport;
import com.baomidou.mybatisplus.service.IService;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;



/**
 * <p>
 *  服务类
 * </p>
 */
public interface SsFileImportService<T> extends IService<SsFileImport> {

    int IMPORT_TYPE_SS_ANNUAL_ADJUST_COMPANY_EMP = 1; // 年调客户雇员工资采集
    int IMPORT_TYPE_SS_ANNUAL_ADJUST_ACCOUNT_EMP = 2; // 年调社保账户雇员列表

    void executeExcelImport(boolean isDelete,
                            String deleteDataKeyField,
                            int importType,
                            Long relatedUnitId,
                            ImportParams importParams,
                            IService<T> iService,
                            MultiValueMap<String, MultipartFile> files,
                            final String createdBy) throws Exception;
}
