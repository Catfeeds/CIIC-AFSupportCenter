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
    private String ssSerialStart;
    private String ssSerialEnd;
    private String companyId;
    private String employeeId;
    private Integer archiveTaskStatus;
}
