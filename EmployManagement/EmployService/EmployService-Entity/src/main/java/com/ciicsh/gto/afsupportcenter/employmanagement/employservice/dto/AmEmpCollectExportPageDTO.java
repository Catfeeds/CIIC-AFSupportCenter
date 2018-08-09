package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import java.util.List;

public class AmEmpCollectExportPageDTO {

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司社保登记码
     */
    private String ssAccount;

    List<AmEmpCollectExportDTO> list1;

    List<AmEmpCollectExportDTO> list2;

    List<AmEmpCollectExportDTO> list3;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public List<AmEmpCollectExportDTO> getList1() {
        return list1;
    }

    public void setList1(List<AmEmpCollectExportDTO> list1) {
        this.list1 = list1;
    }

    public List<AmEmpCollectExportDTO> getList2() {
        return list2;
    }

    public void setList2(List<AmEmpCollectExportDTO> list2) {
        this.list2 = list2;
    }

    public List<AmEmpCollectExportDTO> getList3() {
        return list3;
    }

    public void setList3(List<AmEmpCollectExportDTO> list3) {
        this.list3 = list3;
    }
}
