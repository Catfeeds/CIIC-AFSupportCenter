package com.ciicsh.gto.afsupportcenter.util.fileImportService;

import java.io.Serializable;

public class FileImportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer importType;
    private Long relatedUnitId;

    public Integer getImportType() {
        return importType;
    }

    public void setImportType(Integer importType) {
        this.importType = importType;
    }

    public Long getRelatedUnitId() {
        return relatedUnitId;
    }

    public void setRelatedUnitId(Long relatedUnitId) {
        this.relatedUnitId = relatedUnitId;
    }
}
