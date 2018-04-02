package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.SsFileImport;
import com.ciicsh.gto.afsupportcenter.util.fileImportService.FileImportService;

public interface HfFileImportService<T> extends FileImportService<T, SsFileImport> {

    int IMPORT_TYPE_EMP_TASK_TRANS_FEEDBACK_DATE = 11; // 转移任务单回单日期导入
}
