package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import java.util.List;

public class AmEmpExplainExportPageDTO {


    /**
     * 结算区县
     */
    private String settlementArea;

    // 单位名称
    private String companyName;

    // 原因
    private String remark;

    // 是否入职 1 入职 0 离职
    private Integer isEntry;

    private List<AmEmpExplainExportDTO> list;

    public Integer getIsEntry() {
        return isEntry;
    }

    public void setIsEntry(Integer isEntry) {
        this.isEntry = isEntry;
    }

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<AmEmpExplainExportDTO> getList() {
        return list;
    }

    public void setList(List<AmEmpExplainExportDTO> list) {
        this.list = list;
    }
}
