package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;


import lombok.Data;

/**
 * 雇员日常操作-打印信息BO
 *
 * @author sunjian
 * @since 2018-9-7
 */
@Data
public class SsEmpPrintInfoBO {
    private String companyId;
    private String employeeId;
    private String ssAccount;
    private Long ssSerialBegin;
    private Long ssSerialEnd;
    private Integer archiveTaskStatus;
    private Integer handleWay;

}
