package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskRemarkBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.convertor.EmpTaskCategoryConverter;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class HfEmpTaskHandleVo {
   private EmpTaskTransferBo empTaskTransferBo;
    private Long empTaskId;
    private Long empArchiveId;
    private Integer hfType;
    private String hfTypeName;
    private Integer processCategory;
    private Integer taskCategory;
    private Integer dictTaskCategory;
    private Integer taskStatus;
    private Integer isChange;
    private String comHandleStatusName;
    private String companyId;
    private String companyName;
    private Integer paymentBank;
    private String paymentBankName;
    private Integer state;
    private String stateName;
    private Integer paymentWay;
    private String paymentWayName;
    private Integer ukeyStore;
    private String ukeyStoreName;
    private Integer comAccountId;
    private String comAccountName;
    private Long basicComAccountClassId;
    private Long addedComAccountClassId;
    private Integer basicComTaskStatus;
//    private String basicComTaskStatusName;
    private Integer addedComTaskStatus;
//    private String addedComTaskStatusName;
    private Integer hfAccountType;
    private String hfAccountTypeName;
    private String basicHfComAccount;
    private Integer basicComHfMonth;
    private String basicEndMonth;
    private String addedHfComAccount;
    private Integer addedComHfMonth;
    private String addedEndMonth;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private LocalDate inDate;
    private LocalDate outDate;
    private String hfEmpAccount;
    private String startMonth;
    private String endMonth;
    private BigDecimal amount;
    private BigDecimal empBase;
    private BigDecimal ratioCom;
    private BigDecimal ratioEmp;
    private String basicHfEmpAccount;
    private Long basicEmpArchiveId;
    private Integer basicArchiveStatus;
    private String basicArchiveStatusName;
    private String addedHfEmpAccount;
    private Long addedEmpArchiveId;
    private Integer addedArchiveStatus;
    private String addedArchiveStatusName;
    private String handleRemark;
    private String rejectionRemark;
    private Integer operationRemind;
    private LocalDate operationRemindDate;
    private List<HfArchiveBasePeriod> basicArchiveBasePeriods;
    private List<HfArchiveBasePeriod> addedArchiveBasePeriods;
    private List<HfEmpTaskPeriod> empTaskPeriods;
    private List<HfEmpTaskRemarkBo> empTaskRemarks;
    private boolean canHandle;


}
