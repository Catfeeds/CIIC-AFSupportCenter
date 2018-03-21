package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpArchive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfEmpArchiveBo extends HfEmpArchive{
    private String empArchiveIdBc;
    private String companyId;
    private String title;
    private String belongVendor;
    private String hfAccountTyp;
    private String serviceCenter;
    private String empStatus;
    private String hfAccountType;
    private String employeeId;
    private String employeeName;
    private String paymentBank;
    private String idNum;
    private String hfEmpAccount;
    private String hfEmpAccountBc;
    private String archiveTaskStatusBc;
    private String serviceManager;
    private LocalDate inDate;
    private LocalDate outDate;
    private String hfComAccount;

}
