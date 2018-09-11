package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import lombok.Data;

import java.util.List;

@Data
public class EmpTransferToCenterBO {
    private String transferOutUnit;
    private String transferOutUnitAccount;
    private String totalCnt;
    private String currentPageNo;
    private String totalPageCnt;
    private String fillYear;
    private String fillMonth;
    private String fillDay;
    private String endMonth;
    private String lastHandleMonth;
    private String paymentBank;
    private String companyIds;
    private List<EmpTransferToCenterDetailBO> tableList;
}
