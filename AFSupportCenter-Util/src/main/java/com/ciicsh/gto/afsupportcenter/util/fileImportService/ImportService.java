package com.ciicsh.gto.afsupportcenter.util.fileImportService;

import java.util.List;

public interface ImportService<T> {

    void handleDataList(List<T> dataList);
}
