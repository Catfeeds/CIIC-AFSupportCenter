package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsFileImport;
import com.ciicsh.gto.afsupportcenter.util.fileImportService.FileImportService;



/**
 * <p>
 *  服务类
 * </p>
 */
public interface SsFileImportService<T> extends FileImportService<T, SsFileImport> {

    int IMPORT_TYPE_SS_ANNUAL_ADJUST_COMPANY_EMP = 1; // 年调客户雇员工资采集
    int IMPORT_TYPE_SS_ANNUAL_ADJUST_ACCOUNT_EMP = 2; // 年调社保账户雇员列表

}
