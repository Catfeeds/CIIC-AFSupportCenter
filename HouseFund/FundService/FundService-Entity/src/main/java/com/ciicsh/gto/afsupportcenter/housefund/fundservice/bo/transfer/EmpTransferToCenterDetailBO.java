package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import lombok.Data;

@Data
public class EmpTransferToCenterDetailBO {
    private Integer idx;
    private String employeeName;
    private String hfEmpAccount;
    private String[] idNumArr;
}
