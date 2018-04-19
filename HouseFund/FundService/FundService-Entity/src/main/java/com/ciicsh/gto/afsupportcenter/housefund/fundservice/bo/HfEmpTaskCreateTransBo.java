package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class HfEmpTaskCreateTransBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private String companyId;
    private String employeeId;
    private Integer hfType;
    private Integer taskStatus;
    private String transferOutUnit;
    private String transferOutUnitAccount;
    private String transferInUnit;
    private String transferInUnitAccount;
    private LocalDate transferDate;
    private String modifiedBy;
    private String modifiedDisplayName;
}
